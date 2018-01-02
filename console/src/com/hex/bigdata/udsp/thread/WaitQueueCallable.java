package com.hex.bigdata.udsp.thread;

import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.RunQueueService;
import com.hex.bigdata.udsp.mc.service.WaitQueueService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class WaitQueueCallable<T> implements Callable<Boolean> {

    private static Logger logger = LoggerFactory.getLogger(WaitQueueService.class);

    private Current mcCurrent;

    private String waitQueueTaskId;

    private RunQueueService runQueueService;

    private WaitQueueService mcWaitQueueService;

    private long sleepTime = 200;

    public WaitQueueCallable() {
    }

    public WaitQueueCallable(Current mcCurrent) {
        this.mcCurrent = mcCurrent;
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");
        this.mcWaitQueueService = (WaitQueueService) WebApplicationContextUtil.getBean("waitQueueService");
    }

    public WaitQueueCallable(Current mcCurrent, String waitQueueTaskId, long sleepTime) {
        this.mcCurrent = mcCurrent;
        this.waitQueueTaskId = waitQueueTaskId;
        this.sleepTime = sleepTime;
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");
        this.mcWaitQueueService = (WaitQueueService) WebApplicationContextUtil.getBean("waitQueueService");
    }

    public Current getMcCurrent() {
        return mcCurrent;
    }

    public void setMcCurrent(Current mcCurrent) {
        this.mcCurrent = mcCurrent;
    }

    public RunQueueService getRunQueueService() {
        return runQueueService;
    }

    public void setRunQueueService(RunQueueService runQueueService) {
        this.runQueueService = runQueueService;
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
            checkFlg = runQueueService.checkCurrent(this.mcCurrent);
            //执行队列不空闲
            if (!checkFlg) {
                continue;
            }
            //检查任务是否是第一个
            isFirst = mcWaitQueueService.checkWaitQueueIsFirst(mcCurrent, waitQueueTaskId);
            if (isFirst) {
                return true;
            }
            //Thread.sleep(sleepTime);
        }
    }
}
