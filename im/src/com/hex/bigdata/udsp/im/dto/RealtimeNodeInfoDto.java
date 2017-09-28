package com.hex.bigdata.udsp.im.dto;

/**
 * Created by JunjieM on 2017-9-25.
 */
public class RealtimeNodeInfoDto {
    private String id; //
    private String host; // 主机
    private String status; // 开始启动、正在运行、启动失败、开始停止、停止成功、停止失败、运行失败
    private String runTime; // 开始运行的时间
    private String endTime; // 任务停止的时间
    private String updateTime; // 更新信息的时间
    private String message; // 信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
