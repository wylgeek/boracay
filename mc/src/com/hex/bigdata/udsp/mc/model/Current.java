package com.hex.bigdata.udsp.mc.model;

import java.io.Serializable;

public class Current implements Serializable {
    private String pkId;

    private String userName;

    private String serviceName;

    private String requestContent;

    private String startTime;

    private String pid;

    private String appType;

    private String requestType;

    private String appName;

    private String appId;

    /**
     * SYNC,同步
     * ASYNC,异步
     */
    private String syncType;

    /**
     * 最大并发数
     */
    private int maxCurrentNum;

    /**
     * 服务器名
     */
    private String host;

    /**
     * 任务在等待队列中id
     */
    private String waitQueueTaskId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getMaxCurrentNum() {
        return maxCurrentNum;
    }

    public void setMaxCurrentNum(int maxCurrentNum) {
        this.maxCurrentNum = maxCurrentNum;
    }

    public String getWaitQueueTaskId() {
        return waitQueueTaskId;
    }

    public void setWaitQueueTaskId(String waitQueueTaskId) {
        this.waitQueueTaskId = waitQueueTaskId;
    }
}