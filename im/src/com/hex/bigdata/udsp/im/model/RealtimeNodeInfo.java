package com.hex.bigdata.udsp.im.model;

import com.hex.bigdata.udsp.im.constant.RealtimeStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 实时任务的节点信息
 */
public class RealtimeNodeInfo implements Serializable {
    private String id; //
    private String host; // 主机
    private RealtimeStatus status; // 开始启动、正在运行、启动失败、开始停止、停止成功、停止失败、运行失败
    private Date runTime; // 开始运行的时间
    private Date endTime; // 任务停止的时间
    private Date updateTime; // 更新信息的时间
    private String message; // 信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public RealtimeStatus getStatus() {
        return status;
    }

    public void setStatus(RealtimeStatus status) {
        this.status = status;
    }

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
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
