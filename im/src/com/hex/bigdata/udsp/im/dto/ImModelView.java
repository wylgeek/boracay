package com.hex.bigdata.udsp.im.dto;

import com.hex.bigdata.udsp.im.model.ImModel;

import java.io.Serializable;

/**
 * Created by jintian on 2017/9/7.
 */
public class ImModelView extends ImModel implements Serializable{
    private String crtTimeStart;

    private String crtTimeEnd;

    private String uptTimeStart;

    private String uptTimeEnd;

    private String targetMdName;

    private String sourceDsName;

    private String status;

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

    public String getTargetMdName() {
        return targetMdName;
    }

    public void setTargetMdName(String targetMdName) {
        this.targetMdName = targetMdName;
    }

    public String getSourceDsName() {
        return sourceDsName;
    }

    public void setSourceDsName(String sourceDsName) {
        this.sourceDsName = sourceDsName;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
