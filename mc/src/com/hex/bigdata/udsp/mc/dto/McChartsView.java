package com.hex.bigdata.udsp.mc.dto;

import java.io.Serializable;

/**
 * Created by PC on 2017/3/23.
 */
public class McChartsView implements Serializable {
    private String datetime;
    private String appType;
    private String serviceName;
    private String userName;
    private int countNum;
    private String status; // 状态（0：成功，1：失败）
    private String realStatus;

    private String syncType; // 类型（SYNC：同步，ASYNC：异步）
    private float maxTime; // 最大耗时
    private float minTime; // 最小耗时
    private float avgTime; // 平均耗时

    private String timeStart;
    private String timeEnd;

    public McChartsView() {
    }

    public McChartsView(String timeStart, String timeEnd) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public McChartsView(String timeStart, String timeEnd, String serviceName) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.serviceName = serviceName;
    }

    public McChartsView(String timeStart, String timeEnd, String serviceName, String userName) {
        this.userName = userName;
        this.serviceName = serviceName;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealStatus() {
        return realStatus;
    }

    public void setRealStatus(String realStatus) {
        this.realStatus = realStatus;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public float getMinTime() {
        return minTime;
    }

    public void setMinTime(float minTime) {
        this.minTime = minTime;
    }

    public float getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(float avgTime) {
        this.avgTime = avgTime;
    }
}
