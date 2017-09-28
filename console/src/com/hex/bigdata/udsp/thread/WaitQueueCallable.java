package com.hex.bigdata.udsp.thread;

import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.mc.service.McWaitQueueService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class WaitQueueCallable<T> implements Callable<Boolean> {

    private static Logger logger = LoggerFactory.getLogger(McWaitQueueService.class);

    private McCurrent mcCurrent;

    private McCurrentCountService mcCurrentCountService;

    private McWaitQueueService mcWaitQueueService;

    private long sleepTime = 200;

    public WaitQueueCallable() {
    }

    public WaitQueueCallable(McCurrent mcCurrent) {
        this.mcCurrent = mcCurrent;
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcWaitQueueService = (McWaitQueueService) WebApplicationContextUtil.getBean("mcWaitQueueService");
    }

    public WaitQueueCallable(McCurrent mcCurrent, long sleepTime) {
        this.mcCurrent = mcCurrent;
        this.sleepTime = sleepTime;
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcWaitQueueService = (McWaitQueueService) WebApplicationContextUtil.getBean("mcWaitQueueService");
    }

    public McCurrent getMcCurrent() {
        return mcCurrent;
    }

    public void setMcCurrent(McCurrent mcCurrent) {
        this.mcCurrent = mcCurrent;
    }

    public McCurrentCountService getMcCurrentCountService() {
        return mcCurrentCountService;
    }

    public void setMcCurrentCountService(McCurrentCountService mcCurrentCountService) {
        this.mcCurrentCountService = mcCurrentCountService;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public Boolean call() throws Exception {
        //a、检查任务是否超时->b、检查执行队列是否有空闲->c、检查任务是否是等待队列的第一个
        //任务超时则跳出循环，报任务超时错误；不超时则继续执行b
        //执行队列不空闲则跳出循环，空闲则任务进入执行队列执行，继续执行c
        //任务是等待队列的第一个则任务进入执行队列，否则继续循环
        while (true) {
            //检查执行队列是否有空闲
            boolean checkFlg = false;
            boolean isFirst = false;
            //检查并发
            checkFlg = mcCurrentCountService.checkCurrent(this.mcCurrent);
            //执行队列不空闲
            if (!checkFlg) {
                continue;
            }
            //检查任务是否是第一个
            isFirst = mcWaitQueueService.checkWaitQueueIsFirst(mcCurrent);
            if (isFirst) {
                return true;
            }
            //Thread.sleep(sleepTime);
        }
    }
}
