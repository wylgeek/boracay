package com.hex.bigdata.udsp.olq.model;

import java.io.Serializable;

public class OlqApplicationParam implements Serializable {

    private int seq; // 位置

    private String pkId; // ID

    private String paramName; // 名称

    private String paramDesc; // 说明

    private String defaultValue; // 默认值

    private String isNeed; // 是否必填

    private String olqAppId; // APP ID

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getIsNeed() {
        return isNeed;
    }

    public void setIsNeed(String isNeed) {
        this.isNeed = isNeed;
    }

    public String getOlqAppId() {
        return olqAppId;
    }

    public void setOlqAppId(String olqAppId) {
        this.olqAppId = olqAppId;
    }

}