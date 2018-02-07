package com.hex.bigdata.udsp.ed.dto;

/**
 * Created by wb_dsjpt_05 on 2018/1/29.
 */
public class EdInterfaceCountReqDto {

    private String serviceName;

    private String startTime;

    private String endTime;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
