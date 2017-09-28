package com.hex.bigdata.udsp.im.model;

import java.io.Serializable;

public class ImModel implements Serializable {
    private String pkId;

    private String name;

    private String describe;

    private String sDsId;

    private String tMdId;

    private String note;

    private String delFlg;

    private String crtUser;

    private String crtTime;

    private String uptUser;

    private String uptTime;

    private String type;

    private String buildMode;

    private String updateMode;

    private String updateKey;

    private String eDsId;

    private String status;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
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

    public String getsDsId() {
        return sDsId;
    }

    public void setsDsId(String sDsId) {
        this.sDsId = sDsId;
    }

    public String gettMdId() {
        return tMdId;
    }

    public void settMdId(String tMdId) {
        this.tMdId = tMdId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuildMode() {
        return buildMode;
    }

    public void setBuildMode(String buildMode) {
        this.buildMode = buildMode;
    }

    public String getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(String updateMode) {
        this.updateMode = updateMode;
    }

    public String getUpdateKey() {
        return updateKey;
    }

    public void setUpdateKey(String updateKey) {
        this.updateKey = updateKey;
    }

    public String geteDsId() {
        return eDsId;
    }

    public void seteDsId(String eDsId) {
        this.eDsId = eDsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}