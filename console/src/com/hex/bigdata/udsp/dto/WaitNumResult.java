package com.hex.bigdata.udsp.dto;

/**
 * 检查等待队列长度结果集
 */
public class WaitNumResult {

    /**
     * 等待队列是否存在
     */
    private boolean waitQueueIsExist;

    /**
     * 等待队列是否已经满了
     */
    private boolean waitQueueIsFull;

    /**
     * 队列同步类型
     */
    private String waitQueueSyncType;

    /**
     * 判断执行队列是否是满的
     */
    private boolean exeQueueIsFull;

    /**
     * 进入等待队列
     */
    private boolean intoWaitQueue = false;

    /**
     * 进入执行队列
     */
    private boolean intoExeQueue = false;

    /**
     * 等待队列中的任务id
     */
    private String waitQueueTaskId;


    public boolean getWaitQueueIsExist() {
        return waitQueueIsExist;
    }

    public void setWaitQueueIsExist(boolean waitQueueIsExist) {
        this.waitQueueIsExist = waitQueueIsExist;
    }

    public boolean getWaitQueueIsFull() {
        return waitQueueIsFull;
    }

    public void setWaitQueueIsFull(boolean waitQueueIsFull) {
        this.waitQueueIsFull = waitQueueIsFull;
    }

    public String getWaitQueueSyncType() {
        return waitQueueSyncType;
    }

    public void setWaitQueueSyncType(String waitQueueSyncType) {
        this.waitQueueSyncType = waitQueueSyncType;
    }

    public boolean isExeQueueIsFull() {
        return exeQueueIsFull;
    }

    public void setExeQueueIsFull(boolean exeQueueIsFull) {
        this.exeQueueIsFull = exeQueueIsFull;
    }

    public boolean isIntoWaitQueue() {
        return intoWaitQueue;
    }

    public void setIntoWaitQueue(boolean intoWaitQueue) {
        this.intoWaitQueue = intoWaitQueue;
    }

    public boolean isIntoExeQueue() {
        return intoExeQueue;
    }

    public void setIntoExeQueue(boolean intoExeQueue) {
        this.intoExeQueue = intoExeQueue;
    }

    public boolean isWaitQueueIsExist() {
        return waitQueueIsExist;
    }

    public boolean isWaitQueueIsFull() {
        return waitQueueIsFull;
    }

    public String getWaitQueueTaskId() {
        return waitQueueTaskId;
    }

    public void setWaitQueueTaskId(String waitQueueTaskId) {
        this.waitQueueTaskId = waitQueueTaskId;
    }
}
