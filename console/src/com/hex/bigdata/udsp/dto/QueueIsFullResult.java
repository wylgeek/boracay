package com.hex.bigdata.udsp.dto;

import java.io.Serializable;

public class QueueIsFullResult implements Serializable{
    /**
     * 等待队列是否满了，满了返回true，没满返回false
     */
    private boolean waitQueueIsFull = false;

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
