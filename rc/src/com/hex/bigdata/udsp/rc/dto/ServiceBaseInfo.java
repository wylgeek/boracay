package com.hex.bigdata.udsp.rc.dto;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/11
 * TIME:18:57
 */
public class ServiceBaseInfo {

    public ServiceBaseInfo(RcUserServiceView rcUserService,long maxNum,String udspRequestUrl) {
        this.serviceName = rcUserService.getServiceName();
        this.serviceDescribe = rcUserService.getServiceDescribe();
        this.maxNum = maxNum;
        this.userName = rcUserService.getUserName();
        this.userId = rcUserService.getUserId();
        this.maxAsyncNum = rcUserService.getMaxAsyncNum();
        this.maxSyncNum = rcUserService.getMaxSyncNum();
        this.maxSyncWaitNum = rcUserService.getMaxSyncWaitNum();
        this.maxAsyncWaitNum = rcUserService.getMaxAsyncWaitNum();
        this.udspRequestUrl = udspRequestUrl;
    }

    public ServiceBaseInfo(RcUserServiceView rcUserService) {
        this.serviceName = rcUserService.getServiceName();
        this.serviceDescribe = rcUserService.getServiceDescribe();
        this.userName = rcUserService.getUserName();
        this.userId = rcUserService.getUserId();
        this.maxAsyncNum = rcUserService.getMaxAsyncNum();
        this.maxSyncNum = rcUserService.getMaxSyncNum();
        this.maxSyncWaitNum = rcUserService.getMaxSyncWaitNum();
        this.maxAsyncWaitNum = rcUserService.getMaxAsyncWaitNum();
    }

    public ServiceBaseInfo() {
    }

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务说明
     */
    private String serviceDescribe;

    /**
     * 最大返回数
     */
    private Long maxNum;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录用户名
     */
    private String userId;

    /**
     * 执行队列最大线程数(同步)
     */
    private int maxSyncNum;

    /**
     * 执行队列最大线程数(异步)
     */
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
     * UDSP请求链接
     */
    private String udspRequestUrl;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescribe() {
        return serviceDescribe;
    }

    public void setServiceDescribe(String serviceDescribe) {
        this.serviceDescribe = serviceDescribe;
    }

    public Long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Long maxNum) {
        this.maxNum = maxNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUdspRequestUrl() {
        return udspRequestUrl;
    }

    public void setUdspRequestUrl(String udspRequestUrl) {
        this.udspRequestUrl = udspRequestUrl;
    }
}
