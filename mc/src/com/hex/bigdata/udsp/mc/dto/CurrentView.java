package com.hex.bigdata.udsp.mc.dto;

import com.hex.bigdata.udsp.mc.model.Current;

import java.io.Serializable;

/**
 * Created by PC on 2017/3/9.
 */
public class CurrentView extends Current implements Serializable {
    private String startTimeStart;
    private String startTimeEnd;

    public String getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(String startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public String getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(String startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }
}
