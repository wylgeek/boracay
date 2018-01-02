package com.hex.bigdata.udsp.common.lock;

import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 */
@Repository
public class RedisDistributedLock {
    private static Logger logger = LogManager.getLogger(RedisDistributedLock.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存储到redis中的标志
     */
    private static final String LOCKED = "LOCKED";

    /**
     * 请求锁超时时间（ms），为了避免死锁问题发生
     */
    private static final long TIME_OUT = 3000l;

    /**
     * 检查锁是否解锁的休眠时间（ms）
     */
    private static final long SLEEP_TIME = 1;

    /**
     * 锁的有效时间(s)
     */
    public static final int EXPIRE = 1;

    public final static String LOCK_KEY_PREFIX = "lock:";

    /**
     * 上锁
     *
     * @param key
     */
    public void lock(String key) {
        logger.debug(key + "准备上锁！");
        String newkey = LOCK_KEY_PREFIX + key;
        if (redisTemplate == null) {
            redisTemplate = (RedisTemplate) WebApplicationContextUtil.getBean("redisLockTemplate");
        }
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        try {
            long count = 0;
            long num = TIME_OUT / SLEEP_TIME;
            while (count < num) {
                try {
                    logger.debug(key + "请求锁！");
                    // 请求锁成功说明锁没被其他线程保持
                    if (redisConnection.setNX(newkey.getBytes(), LOCKED.getBytes())) {
                        logger.debug(key + "上锁！");
                        // 上锁
                        redisTemplate.expire(newkey, EXPIRE, TimeUnit.SECONDS);
                        break;
                    }
                    // 请求锁失败说明锁被其它线程保持，等待几毫秒后继续请求锁
                } catch (Exception e) {
                    logger.error(ExceptionUtil.getMessage(e) + " key: " + key);
                }
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (Exception e) {
                    logger.error(ExceptionUtil.getMessage(e) + " key: " + key);
                }
                count++;
            }
            if (count >= num) {
                logger.error("key: " + key + " lock time out");
                unlock(newkey);
            }
        } finally {
            redisConnection.close();
        }
    }

    /**
     * 解锁
     *
     * @param key
     */
    public void unlock(String key) {
        logger.debug(key + "解锁！");
        String newkey = LOCK_KEY_PREFIX + key;
        try {
            redisTemplate.delete(newkey);
        } catch (Exception e) {
            logger.error(ExceptionUtil.getMessage(e) + " key: " + key);
        }
    }

}
