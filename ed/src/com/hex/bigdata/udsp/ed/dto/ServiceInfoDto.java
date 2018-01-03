package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.ServiceInfo;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:11:32
 */
public class ServiceInfoDto extends ServiceInfo {
    private String startTime;
    private String endTime;

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
