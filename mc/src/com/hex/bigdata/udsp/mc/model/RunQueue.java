package com.hex.bigdata.udsp.mc.model;

import java.io.Serializable;

/**
 * 并发数控制实体
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/10
 * TIME:14:29
 */
public class RunQueue implements Serializable {

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 同步最大并发数
     */
    private int maxSyncNum;

    /**
     * 异步最大并发数
     */
    private int maxAsyncNum;

    /**
     * 当前同步并发数
     */
    private int currentSyncNum;

    /**
     * 当前异步并发数
     */
    private int currentAsyncNum;

    /**
     * SYNC,同步
     * ASYNC,异步
     */
    private String syncType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMaxSyncNum() {
        return maxSyncNum;
    }

    public void setMaxSyncNum(int maxSyncNum) {
        this.maxSyncNum = maxSyncNum;
    }

    public int getMaxAsyncNum() {
        return maxAsyncNum;
    }

    public void setMaxAsyncNum(int maxAsyncNum) {
        this.maxAsyncNum = maxAsyncNum;
    }

    public int getCurrentSyncNum() {
        return currentSyncNum;
    }

    public void setCurrentSyncNum(int currentSyncNum) {
        this.currentSyncNum = currentSyncNum;
    }

    public int getCurrentAsyncNum() {
        return currentAsyncNum;
    }

    public void setCurrentAsyncNum(int currentAsyncNum) {
        this.currentAsyncNum = currentAsyncNum;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }
}
