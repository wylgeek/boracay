package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.mc.dao.McCurrentCountMapper;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.model.McCurrentCount;
import com.hex.bigdata.udsp.mc.util.McCommonUtil;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.InnerRequest;
import com.hex.bigdata.udsp.model.Request;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/10
 * TIME:14:36
 */
@Service
public class McCurrentCountService {
    private static Logger logger = LoggerFactory.getLogger(McCurrentCountService.class);

    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private McCurrentCountMapper mcCurrentCountMapper;

    @Autowired
    private McCurrentService mcCurrentService;

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
    public boolean addAsyncCurrent(McCurrent mcCurrent) {
        String key = mcCurrent.getUserName() + ":" + mcCurrent.getAppId() + ":" + mcCurrent.getAppType() + ":" + mcCurrent.getSyncType();
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                McCurrentCount mcCurrentCount = this.select(key);
                if (mcCurrentCount == null) {
                    mcCurrentCount = this.initalByKey(key);
                }
                mcCurrentCount.setCurrentAsyncNum(mcCurrentCount.getCurrentAsyncNum() + 1);
                this.insert(key, mcCurrentCount);
                return true;
            } finally {
                redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    /**
     * 增加同步并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean addSyncCurrent(McCurrent mcCurrent) {
        String key = mcCurrent.getUserName() + ":" + mcCurrent.getAppId() + ":" + mcCurrent.getAppType() + ":" + mcCurrent.getSyncType();
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                McCurrentCount mcCurrentCount = this.select(key);
                if (mcCurrentCount == null) {
                    mcCurrentCount = this.initalByKey(key);
                }
                mcCurrentCount.setCurrentSyncNum(mcCurrentCount.getCurrentSyncNum() + 1);
                this.insert(key, mcCurrentCount);
                return true;
            } finally {
                redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    /**
     * 减少异步并发
     *
     * @param mcCurrent
     * @return
     */
    public boolean reduceAsyncCurrent(McCurrent mcCurrent) {
        String key = mcCurrent.getUserName() + ":" + mcCurrent.getAppId() + ":" + mcCurrent.getAppType() + ":" + mcCurrent.getSyncType();
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                this.mcCurrentService.delete(mcCurrent.getPkId());
                McCurrentCount mcCurrentCount = this.select(key);
                if (mcCurrentCount != null) {
                    int currentAsyncNum = mcCurrentCount.getCurrentAsyncNum();
                    if (currentAsyncNum >= 1) {
                        currentAsyncNum = currentAsyncNum - 1;
                    } else {
                        currentAsyncNum = 0;
                    }
                    mcCurrentCount.setCurrentAsyncNum(currentAsyncNum);
                    this.insert(key, mcCurrentCount);
                }
                return true;
            } finally {
                redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }


    /**
     * 减少同步并发
     */
    public boolean reduceSyncCurrent(McCurrent mcCurrent) {
        String key = mcCurrent.getUserName() + ":" + mcCurrent.getAppId() + ":" + mcCurrent.getAppType() + ":" + mcCurrent.getSyncType();
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                this.mcCurrentService.delete(mcCurrent.getPkId());
                McCurrentCount mcCurrentCount = this.select(key);
                if (mcCurrentCount != null) {
                    int currentSyncNum = mcCurrentCount.getCurrentSyncNum();
                    if (currentSyncNum >= 1) {
                        currentSyncNum = currentSyncNum - 1;
                    } else {
                        currentSyncNum = 0;
                    }
                    mcCurrentCount.setCurrentSyncNum(currentSyncNum);
                    this.insert(key, mcCurrentCount);
                }
                return true;
            } finally {
                redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    public boolean update(String key, McCurrentCount mcCurrentCount) {
        return this.mcCurrentCountMapper.update(key, mcCurrentCount);
    }

    public boolean insert(String key, McCurrentCount mcCurrentCount) {
        return this.mcCurrentCountMapper.insert(key, mcCurrentCount);
    }

    public McCurrentCount select(String key) {
        return mcCurrentCountMapper.select(key);
    }

    /**
     * key为userId:appId:appType
     * 即key为userId 用户id，appId应用id，appType
     *
     * @param key
     * @return
     */
    private McCurrentCount initalByKey(String key) {
        String[] keys = key.split("\\:");
        McCurrentCount mcCurrentCount = new McCurrentCount();
        if (keys.length >= 4) {
            mcCurrentCount.setUserId(keys[0]);
            mcCurrentCount.setAppId(keys[1]);
            mcCurrentCount.setAppType(keys[2]);
            mcCurrentCount.setSyncType(keys[3]);
        }
        return mcCurrentCount;
    }

    /**
     * 检查异步并发
     *
     * @param request
     * @param maxCurrentNum
     * @return
     */
    public McCurrent checkAsyncCurrent(Request request, int maxCurrentNum) {
        synchronized (McCurrentCountService.class) {
            String userId = request.getUdspUser();
            String appId = request.getAppId();
            String appType = request.getAppType();
            String syncType = request.getType().toUpperCase();
            String key = userId + ":" + appId + ":" + appType + ":" + syncType;
            McCurrentCount mcCurrentCount = this.select(key);
            if (mcCurrentCount == null) {
                McCurrent mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                this.addAsyncCurrent(mcCurrent);
                this.mcCurrentService.insert(mcCurrent);
                return mcCurrent;
            }
            if (mcCurrentCount.getCurrentAsyncNum() < maxCurrentNum) {
                McCurrent mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                this.addAsyncCurrent(mcCurrent);
                this.mcCurrentService.insert(mcCurrent);
                return mcCurrent;
            }
            return null;
        }
    }

    /**
     * 检查同步并发
     *
     * @param request
     * @param maxCurrentNum
     * @return
     */
    public McCurrent checkSyncCurrent(Request request, int maxCurrentNum) {
        synchronized (McCurrentCountService.class) {
            String userId = request.getUdspUser();
            String appId = request.getAppId();
            String appType = request.getAppType();
            String syncType = request.getType().toUpperCase();
            String key = userId + ":" + appId + ":" + appType + ":" + syncType;
            McCurrentCount mcCurrentCount = this.select(key);
            if (mcCurrentCount == null) {
                McCurrent mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                this.addSyncCurrent(mcCurrent);
                this.mcCurrentService.insert(mcCurrent);
                return mcCurrent;
            }
            if (mcCurrentCount.getCurrentSyncNum() < maxCurrentNum) {
                McCurrent mcCurrent = McCommonUtil.getMcCurrent(request, maxCurrentNum);
                this.addSyncCurrent(mcCurrent);
                this.mcCurrentService.insert(mcCurrent);
                return mcCurrent;
            }
            return null;
        }
    }


    /**
     * 检查并发
     *
     * @param mcCurrent 不为空
     * @param mcCurrent
     * @return
     */
    public boolean checkCurrent(McCurrent mcCurrent) {
        synchronized (McCurrentCountService.class) {
            String key = this.getMcCountKey(mcCurrent);
            McCurrentCount mcCurrentCount = this.select(key);
            if (mcCurrentCount == null) {
                return true;
            }
            if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
                if (mcCurrentCount.getCurrentAsyncNum() < mcCurrent.getMaxCurrentNum()) {
                    return true;
                }
            } else {
                if (mcCurrentCount.getCurrentSyncNum() < mcCurrent.getMaxCurrentNum()) {
                    return true;
                }
            }
            return false;
        }
    }

    private String getMcCountKey(McCurrent mcCurrent) {
        String userId = mcCurrent.getUserName();
        String appId = mcCurrent.getAppId();
        String appType = mcCurrent.getAppType();
        String syncType = mcCurrent.getSyncType().toUpperCase();
        return userId + ":" + appId + ":" + appType + ":" + syncType;
    }

}
