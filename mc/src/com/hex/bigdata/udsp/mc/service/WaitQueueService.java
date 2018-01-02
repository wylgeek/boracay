package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.dto.QueueIsFullResult;
import com.hex.bigdata.udsp.mc.dao.WaitQueueMapper;
import com.hex.bigdata.udsp.mc.model.WaitQueue;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.util.McCommonUtil;
import com.hex.bigdata.udsp.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 等待队列信息的服务
 */
@Service
public class WaitQueueService {

    private static Logger logger = LoggerFactory.getLogger(WaitQueueService.class);

    /**
     * 等待队列信息的KEY
     */
    private static final String MC_WAITQUEUE_KEY = "WAITQUEUE";

    @Autowired
    private WaitQueueMapper mcWaitQueueMapper;

    @Autowired
    private CurrentService mcCurrentService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;

    @Autowired
    private InitParamService initParamService;

    /**
     * 检查等待队列是否满了
     *
     * @param request
     * @param maxWaitNum
     * @return
     */
    public QueueIsFullResult checkWaitQueueIsFull(Request request, int maxWaitNum) {
        String key = this.getKey(request);
        boolean isFull = false;
        QueueIsFullResult isFullResult = new QueueIsFullResult();
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);// 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                WaitQueue mcWaitQueue = this.select(key);
                if (null == mcWaitQueue) {
                    //为空则初始化
                    mcWaitQueue = initalByKey(request);
                }
                //若是等待队列未满，则请求进入
                if (maxWaitNum > mcWaitQueue.getCurrentLenth()) {
                    logger.debug("等待队列最大长度：" + maxWaitNum + "，等待队列长度：" + mcWaitQueue.getCurrentLenth() + "，" + Thread.currentThread().getName() + "进入等待队列！");
                    //本次请求加入队列
                    Current mcCurrent = McCommonUtil.getMcCurrent(request, maxWaitNum);
                    String pkId = mcCurrent.getPkId();
                    isFullResult.setWaitQueueTaskId(pkId);
                    //加入队列
                    mcWaitQueue.offerElement(pkId);
                    //插入统计队列
                    mcCurrentService.insertWait(mcCurrent);
                } else {
                    isFull = true;
                }
                isFullResult.setWaitQueueIsFull(isFull);
                //更新等待队列统计信息
                mcWaitQueueMapper.insert(key, mcWaitQueue);
                return isFullResult;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    public boolean checkWaitQueueIsFirst(Current mcCurrent, String waitQueueTaskId) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) {
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key);// 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                WaitQueue mcWaitQueue = this.select(key);
                boolean flg = mcWaitQueue.isFirstElement(waitQueueTaskId);
                if (flg) {
                    //等待队列信息回写到缓存
                    logger.debug("将任务从等待队列中移除：" + Thread.currentThread().getName());
                    mcWaitQueueMapper.insert(key, mcWaitQueue);
                    mcCurrentService.deleteWait(waitQueueTaskId);
                }
                return flg;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }

        }
    }

    /**
     * 初始化等待队列对象
     * key为userId:appId:appType
     * 即key为userId 用户id，appId应用id，appType:应用类型，Type：同步异步
     *
     * @param request
     * @return
     */
    private WaitQueue initalByKey(Request request) {
        WaitQueue mcWaitQueue = new WaitQueue(this.getKey(request).toString());
        return mcWaitQueue;
    }

    private String getKey(Request request) {
        return MC_WAITQUEUE_KEY + ":" + request.getUdspUser() + ":" + request.getAppId()
                + ":" + request.getAppType().toUpperCase() + ":" + request.getType().toUpperCase();
    }

    private String getKey(Current mcCurrent) {
        return MC_WAITQUEUE_KEY + ":" + mcCurrent.getUserName() + ":" + mcCurrent.getAppId()
                + ":" + mcCurrent.getAppType().toUpperCase() + ":" + mcCurrent.getSyncType().toUpperCase();
    }

    private WaitQueue select(String key) {
        return mcWaitQueueMapper.select(key);
    }

}
