package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dto.QueueIsFullResult;
import com.hex.bigdata.udsp.mc.dao.McCurrentCountMapper;
import com.hex.bigdata.udsp.mc.dao.McWaitQueueMapper;
import com.hex.bigdata.udsp.mc.dto.McWaitQueue;
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
public class McWaitQueueService {

    private static Logger logger = LoggerFactory.getLogger(McWaitQueueService.class);


    private static final String MC_WAITQUEUE_KEY = "WAITQUEUE";

    @Autowired
    private McWaitQueueMapper mcWaitQueueMapper;

    @Autowired
    private McCurrentService mcCurrentService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;


    /**
     * 检查等待队列是否满了
     *
     * @param request
     * @param maxWaitNum
     * @return
     */
    public QueueIsFullResult checkWaitQueueIsFull(Request request, int maxWaitNum) {
        String queueName = this.getWaitQueueKey(request);
        boolean isFull = false;
        QueueIsFullResult isFullResult = new QueueIsFullResult();
        synchronized (queueName.intern()) {
            logger.info(Thread.currentThread().getName() + "queueName ：" + queueName);
            redisLock.lock(queueName);// 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                McWaitQueue mcWaitQueue = this.select(queueName);
                if (null == mcWaitQueue) {
                    //为空则初始化
                    mcWaitQueue = initalByKey(request);
                }
                //若是等待队列未满，则请求进入
                if (maxWaitNum > mcWaitQueue.getCurrentLenth()) {
                    logger.info("等待队列最大长度：" + maxWaitNum + "，等待队列长度：" + mcWaitQueue.getCurrentLenth() + "，" + Thread.currentThread().getName() + "进入等待队列！");
                    //本次请求加入队列
                    McCurrent mcCurrent = McCommonUtil.getMcCurrent(request, maxWaitNum);
                    isFullResult.setWaitQueueTaskId(mcCurrent.getPkId());
                    //加入队列
                    mcWaitQueue.offerElement(mcCurrent.getPkId());
                    //插入统计队列
                    mcCurrentService.insertWaitQueue(mcCurrent);
                } else {
                    isFull = true;
                }
                isFullResult.setWaitQueueIsFull(isFull);
                //更新等待队列统计信息
                mcWaitQueueMapper.insert(queueName, mcWaitQueue);
                return isFullResult;
            } finally {
                redisLock.unlock(queueName); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    public boolean checkWaitQueueIsFirst(McCurrent mcCurrent) {
        String queueName = this.getWaitQueueKey(mcCurrent);
        synchronized (queueName.intern()) {
            redisLock.lock(queueName);// 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                McWaitQueue mcWaitQueue = this.select(queueName);
                //本次请求加入队列
                String requestKey = mcCurrent.getWaitQueueTaskId();

                boolean flg = mcWaitQueue.isFirstElement(requestKey);
                if (flg) {
                    //等待队列信息回写到缓存
                    logger.info("将任务从等待队列中移除：" + Thread.currentThread().getName());
                    mcWaitQueueMapper.insert(queueName, mcWaitQueue);
                    mcCurrentService.deleteWaitQueue(requestKey);
                }
                return flg;
            } finally {
                redisLock.unlock(queueName); // 分布式解锁 （主要防止多节点并发资源不同步问题）
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
    private McWaitQueue initalByKey(Request request) {
        McWaitQueue mcWaitQueue = new McWaitQueue(this.getWaitQueueKey(request).toString());
        return mcWaitQueue;
    }

    private String getWaitQueueKey(Request request) {
        StringBuffer queueName = new StringBuffer();
        queueName.append(MC_WAITQUEUE_KEY).append(":");
        queueName.append(request.getUdspUser()).append(":");
        queueName.append(request.getAppType()).append(":");
        queueName.append(request.getAppId()).append(":");
        queueName.append(request.getType());
        return queueName.toString();
    }

    private String getWaitQueueKey(McCurrent mcCurrent) {
        StringBuffer queueName = new StringBuffer();
        queueName.append(MC_WAITQUEUE_KEY).append(":");
        queueName.append(mcCurrent.getUserName()).append(":");
        queueName.append(mcCurrent.getAppType()).append(":");
        queueName.append(mcCurrent.getAppId()).append(":");
        queueName.append(mcCurrent.getSyncType());
        return queueName.toString();
    }

    private McWaitQueue select(String key) {
        return mcWaitQueueMapper.select(key);
    }

}
