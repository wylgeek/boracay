package com.hex.bigdata.udsp.dto;

/**
 * 检查等待队列长度结果集
 */
public class WaitNumResult {

    /**
     * 等待队列是否已经满了
     */
    private boolean waitQueueIsFull;

    /**
     * 等待队列中的任务id
     */
    private String waitQueueTaskId;

    public boolean isWaitQueueIsFull() {
        return waitQueueIsFull;
    }

    public void setWaitQueueIsFull(boolean waitQueueIsFull) {
        this.waitQueueIsFull = waitQueueIsFull;
    }

    public String getWaitQueueTaskId() {
        return waitQueueTaskId;
    }

    public void setWaitQueueTaskId(String waitQueueTaskId) {
        this.waitQueueTaskId = waitQueueTaskId;
    }
}
