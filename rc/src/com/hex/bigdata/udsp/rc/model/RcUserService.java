package com.hex.bigdata.udsp.rc.model;

import java.io.Serializable;

public class RcUserService implements Serializable {
    private String pkId;

    private String userId;

    private String serviceId;

    private String ipSection;

    private int maxSyncNum;

    private int maxAsyncNum;

    /**
     * 等待队列大小（同步）
     */
    private int maxSyncWaitNum;

    /**
     * 等待队列大小（异步）
     */
    private int maxAsyncWaitNum;

    /**
     * 同步最大等待时间
     */
    private int maxSyncWaitTimeout;

    /**
     * 异步最大等待时间
     */
    private int maxAsyncWaitTimeout;

    /**
     * 同步最大运行时间
     */
    private int maxSyncExecuteTimeout;

    /**
     *异步最大运行时间
     */
    private int maxAsyncExecuteTimeout;

    private String delFlg;

    private String crtUser;

    private String crtTime;

    private String uptUser;

    private String uptTime;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getIpSection() {
        return ipSection;
    }

    public void setIpSection(String ipSection) {
        this.ipSection = ipSection;
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

    public int getMaxSyncWaitNum() {
        return maxSyncWaitNum;
    }

    public void setMaxSyncWaitNum(int maxSyncWaitNum) {
        this.maxSyncWaitNum = maxSyncWaitNum;
    }

    public int getMaxAsyncWaitNum() {
        return maxAsyncWaitNum;
    }

    public void setMaxAsyncWaitNum(int maxAsyncWaitNum) {
        this.maxAsyncWaitNum = maxAsyncWaitNum;
    }

    public int getMaxSyncWaitTimeout() {
        return maxSyncWaitTimeout;
    }

    public void setMaxSyncWaitTimeout(int maxSyncWaitTimeout) {
        this.maxSyncWaitTimeout = maxSyncWaitTimeout;
    }

    public int getMaxAsyncWaitTimeout() {
        return maxAsyncWaitTimeout;
    }

    public void setMaxAsyncWaitTimeout(int maxAsyncWaitTimeout) {
        this.maxAsyncWaitTimeout = maxAsyncWaitTimeout;
    }

    public int getMaxSyncExecuteTimeout() {
        return maxSyncExecuteTimeout;
    }

    public void setMaxSyncExecuteTimeout(int maxSyncExecuteTimeout) {
        this.maxSyncExecuteTimeout = maxSyncExecuteTimeout;
    }

    public int getMaxAsyncExecuteTimeout() {
        return maxAsyncExecuteTimeout;
    }

    public void setMaxAsyncExecuteTimeout(int maxAsyncExecuteTimeout) {
        this.maxAsyncExecuteTimeout = maxAsyncExecuteTimeout;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getUptUser() {
        return uptUser;
    }

    public void setUptUser(String uptUser) {
        this.uptUser = uptUser;
    }

    public String getUptTime() {
        return uptTime;
    }

    public void setUptTime(String uptTime) {
        this.uptTime = uptTime;
    }

}