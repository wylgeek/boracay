package com.hex.bigdata.udsp.olq.model;

import java.io.Serializable;

public class OlqApplication implements Serializable{
    private String pkId;

    private String name;

    private String describe;

    private String olqSql;

    private String olqDsId;

    private Long maxNum;

    private String delFlg;

    private String crtUser;

    private String crtTime;

    private String uptUser;

    private String uptTime;

    private String note;

    private String olqDsName;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getOlqSql() {
        return olqSql;
    }

    public void setOlqSql(String olqSql) {
        this.olqSql = olqSql;
    }

    public String getOlqDsId() {
        return olqDsId;
    }

    public void setOlqDsId(String olqDsId) {
        this.olqDsId = olqDsId;
    }

    public Long getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Long maxNum) {
        this.maxNum = maxNum;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public String getUptUser() {
        return uptUser;
    }

    public void setUptUser(String uptUser) {
        this.uptUser = uptUser;
    }

    public String getUptTime() {
        return uptTime;
    }

    public void setUptTime(String uptTime) {
        this.uptTime = uptTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getOlqDsName() {
        return olqDsName;
    }

    public void setOlqDsName(String olqDsName) {
        this.olqDsName = olqDsName;
    }
}