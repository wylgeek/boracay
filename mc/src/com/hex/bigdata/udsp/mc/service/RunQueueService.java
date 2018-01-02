package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.mc.dao.RunQueueMapper;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.model.RunQueue;
import com.hex.bigdata.udsp.mc.util.McCommonUtil;
import com.hex.bigdata.udsp.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 运行队列信息的服务
 */
@Service
public class RunQueueService {
    private static Logger logger = LoggerFactory.getLogger(RunQueueService.class);

    /**
     * 运行队列信息的KEY
     */
    private static final String MC_RUNQUEUE_KEY = "RUNQUEUE";

    @Autowired
    private RunQueueMapper runQueueMapper;

    @Autowired
    private CurrentService mcCurrentService;

    @Autowired
    private InitParamService initParamService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;

    /**
     * 增加异步并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean addAsyncCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                RunQueue runQueue = this.select(key);
                if (runQueue == null) {
                    runQueue = this.initalByKey(key);
                }
                runQueue.setCurrentAsyncNum(runQueue.getCurrentAsyncNum() + 1);
                this.insert(key, runQueue);
                return true;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    /**
     * 增加同步并发
     * <p>
     * 注：这里不需要上锁，上层调用的服务已经上锁，如果上锁且是同一把锁会导致延迟锁问题！
     *
     * @param mcCurrent
     * @return
     */
    public boolean addSyncCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        RunQueue runQueue = this.select(key);
        if (runQueue == null) {
            runQueue = this.initalByKey(key);
        }
        runQueue.setCurrentSyncNum(runQueue.getCurrentSyncNum() + 1);
        this.insert(key, runQueue);
        return true;
    }

    /**
     * 减少异步并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean reduceAsyncCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                this.mcCurrentService.delete(mcCurrent.getPkId());
                RunQueue runQueue = this.select(key);
                if (runQueue != null) {
                    int currentAsyncNum = runQueue.getCurrentAsyncNum();
                    if (currentAsyncNum >= 1) {
                        currentAsyncNum = currentAsyncNum - 1;
                    } else {
                        currentAsyncNum = 0;
                    }
                    runQueue.setCurrentAsyncNum(currentAsyncNum);
                    this.insert(key, runQueue);
                }
                return true;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }


    /**
     * 减少同步并发
     */
    public boolean reduceSyncCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                this.mcCurrentService.delete(mcCurrent.getPkId());
                RunQueue runQueue = this.select(key);
                if (runQueue != null) {
                    int currentSyncNum = runQueue.getCurrentSyncNum();
                    if (currentSyncNum >= 1) {
                        currentSyncNum = currentSyncNum - 1;
                    } else {
                        currentSyncNum = 0;
                    }
                    runQueue.setCurrentSyncNum(currentSyncNum);
                    this.insert(key, runQueue);
                }
                return true;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    public boolean update(String key, RunQueue runQueue) {
        return this.runQueueMapper.update(key, runQueue);
    }

    public boolean insert(String key, RunQueue runQueue) {
        return this.runQueueMapper.insert(key, runQueue);
    }

    public RunQueue select(String key) {
        return runQueueMapper.select(key);
    }

    /**
     * key为userId:appId:appType
     * 即key为userId 用户id，appId应用id，appType
     *
     * @param key
     * @return
     */
    private RunQueue initalByKey(String key) {
        String[] keys = key.split("\\:");
        RunQueue runQueue = new RunQueue();
        if (keys.length >= 4) {
            runQueue.setUserId(keys[0]);
            runQueue.setAppId(keys[1]);
            runQueue.setAppType(keys[2]);
            runQueue.setSyncType(keys[3]);
        }
        return runQueue;
    }

    /**
     * 检查异步并发
     *
     * @param request
     * @param maxCurrentNum
     * @return
     */
    public Current checkAsyncCurrent(Request request, int maxCurrentNum) {
        String key = this.getKey(request);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                RunQueue runQueue = this.select(key);
                if (runQueue == null) {
                    Current mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                    this.addAsyncCurrent(mcCurrent);
                    this.mcCurrentService.insert(mcCurrent);
                    return mcCurrent;
                }
                if (runQueue.getCurrentAsyncNum() < maxCurrentNum) {
                    Current mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                    this.addAsyncCurrent(mcCurrent);
                    this.mcCurrentService.insert(mcCurrent);
                    return mcCurrent;
                }
                return null;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }

    }

    /**
     * 检查同步并发
     *
     * @param request
     * @param maxCurrentNum
     * @return
     */
    public Current checkSyncCurrent(Request request, int maxCurrentNum) {
        String key = this.getKey(request);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                RunQueue runQueue = this.select(key);
                if (runQueue == null) {
                    Current mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                    this.addSyncCurrent(mcCurrent);
                    this.mcCurrentService.insert(mcCurrent);
                    return mcCurrent;
                }
                if (runQueue.getCurrentSyncNum() < maxCurrentNum) {
                    Current mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                    this.addSyncCurrent(mcCurrent);
                    this.mcCurrentService.insert(mcCurrent);
                    return mcCurrent;
                }
                return null;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }


    /**
     * 检查并发
     *
     * @param mcCurrent 不为空
     * @param mcCurrent
     * @return
     */
    public boolean checkCurrent(Current mcCurrent) {
        String key = this.getKey(mcCurrent);
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                RunQueue runQueue = this.select(key);
                if (runQueue == null) {
                    return true;
                }
                if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
                    if (runQueue.getCurrentAsyncNum() < mcCurrent.getMaxCurrentNum()) {
                        return true;
                    }
                } else {
                    if (runQueue.getCurrentSyncNum() < mcCurrent.getMaxCurrentNum()) {
                        return true;
                    }
                }
                return false;
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    private String getKey(Request request) {
        return MC_RUNQUEUE_KEY + ":" + request.getUdspUser() + ":" + request.getAppId()
                + ":" + request.getAppType().toUpperCase() + ":" + request.getType().toUpperCase();
    }

    private String getKey(Current mcCurrent) {
        return MC_RUNQUEUE_KEY + ":" + mcCurrent.getUserName() + ":" + mcCurrent.getAppId()
                + ":" + mcCurrent.getAppType().toUpperCase() + ":" + mcCurrent.getSyncType().toUpperCase();
    }

}
