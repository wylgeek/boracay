package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:11:32
 */
public class InterfaceInfoDto extends InterfaceInfo {

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
