package com.hex.bigdata.udsp.common.dto;


import com.hex.bigdata.udsp.common.model.ComDatasource;

public class ComDatasourceView extends ComDatasource {
    private String crtTimeStart;
    private String crtTimeEnd;
    private String uptTimeStart;
    private String uptTimeEnd;
    private String imDsTypeDictId; //目标 源 实时 批量

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

    public String getImDsTypeDictId() {
        return imDsTypeDictId;
    }

    public void setImDsTypeDictId(String imDsTypeDictId) {
        this.imDsTypeDictId = imDsTypeDictId;
    }
}