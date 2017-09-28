package com.hex.bigdata.udsp.im.model;

import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 实时任务的汇总信息
 */
public class RealtimeTotalInfo implements Serializable {
    private String id; //
    private String startHost; // 发起启动命令的节点
    private String stopHost; // 发起停止命令的节点
    private RealtimeStatus status; // 准备启动、开始启动、正在运行、启动失败、准备停止、开始停止、停止成功、停止失败、运行失败
    private Date startTime; // 准备启动的时间
    private Date runTime; // 开始运行的时间
    private Date stopTime; // 准备停止的时间
    private Date endTime; // 任务停止的时间
    private Date updateTime; // 更新信息的时间
    private String startUser;
    private String stopUser;
    private String serviceName;
    private String requestType;
    private String appName;
    private String appId;
    private MqModel model; // MQ模型

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getStopUser() {
        return stopUser;
    }

    public void setStopUser(String stopUser) {
        this.stopUser = stopUser;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStartHost() {
        return startHost;
    }

    public void setStartHost(String startHost) {
        this.startHost = startHost;
    }

    public String getStopHost() {
        return stopHost;
    }

    public void setStopHost(String stopHost) {
        this.stopHost = stopHost;
    }

    public RealtimeStatus getStatus() {
        return status;
    }

    public void setStatus(RealtimeStatus status) {
        this.status = status;
    }

    public MqModel getModel() {
        return model;
    }

    public void setModel(MqModel model) {
        this.model = model;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
