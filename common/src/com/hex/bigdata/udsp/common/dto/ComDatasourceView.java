package com.hex.bigdata.udsp.common.dto;


import com.hex.bigdata.udsp.common.model.ComDatasource;

public class ComDatasourceView extends ComDatasource {
    private String crtTimeStart;
    private String crtTimeEnd;
    private String uptTimeStart;
    private String uptTimeEnd;

    public String getCrtTimeStart() {
        return crtTimeStart;
    }

    public void setCrtTimeStart(String crtTimeStart) {
        this.crtTimeStart = crtTimeStart;
    }

    public String getCrtTimeEnd() {
        return crtTimeEnd;
    }

    public void setCrtTimeEnd(String crtTimeEnd) {
        this.crtTimeEnd = crtTimeEnd;
    }

    public String getUptTimeStart() {
        return uptTimeStart;
    }

    public void setUptTimeStart(String uptTimeStart) {
        this.uptTimeStart = uptTimeStart;
    }

    public String getUptTimeEnd() {
        return uptTimeEnd;
    }

    public void setUptTimeEnd(String uptTimeEnd) {
        this.uptTimeEnd = uptTimeEnd;
    }
}