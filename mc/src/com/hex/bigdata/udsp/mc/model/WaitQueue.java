package com.hex.bigdata.udsp.mc.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class WaitQueue implements Serializable {

    public WaitQueue() {
    }

    public WaitQueue(String queueName) {
        this.queueName = queueName;
    }

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 队列限制长度
     */
    private int limitLength;

    /**
     * 队列长度
     */
    private int currentLenth;

    /**
     * 队列实体
     */
    private BlockingQueue<String> waitQueue = new LinkedBlockingQueue<String>();

    /**
     * 判断key是不是队列第一个，
     * 如果是第一个则移除并返回true
     * 如果不是第一个则返回false
     *
     * @param key
     * @return
     */
    public boolean isFirstElement(String key) {
        synchronized (key){
            String firstKey = waitQueue.peek();
            if (StringUtils.isNotBlank(key) && key.equals(firstKey)) {
                //获取并移除此队列的头，如果此队列为空，则返回 false。
                waitQueue.poll();
                return true;
            }
            return false;
        }
    }

    /**
     * 移除指定的element
     *
     * @param element
     * @return
     */
    public boolean removeElement(String element) {
        return waitQueue.remove(element);
    }

    /**
     * 将指定元素插入到此队列的尾部（如果立即可行且不会超出此队列的容量），
     * 在成功时返回 true，如果此队列已满，则返回 false。
     *
     * @param element
     * @return
     */
    public boolean offerElement(String element) {
        return waitQueue.offer(element);
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getLimitLength() {
        return limitLength;
    }

    public void setLimitLength(int limitLength) {
        this.limitLength = limitLength;
    }

    public int getCurrentLenth() {
        return this.waitQueue.size();
    }

    public void setCurrentLenth(int currentLenth) {
        this.currentLenth = currentLenth;
    }

    public BlockingQueue<String> getWaitQueue() {
        return waitQueue;
    }

    public void setWaitQueue(BlockingQueue<String> waitQueue) {
        this.waitQueue = waitQueue;
    }
}
