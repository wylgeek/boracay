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

    private String tMdName;

    private String sDsName;

    private String isBuilded;

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

    public String gettMdName() {
        return tMdName;
    }

    public void settMdName(String tMdName) {
        this.tMdName = tMdName;
    }

    public String getsDsName() {
        return sDsName;
    }

    public void setsDsName(String sDsName) {
        this.sDsName = sDsName;
    }

    public String getIsBuilded() {
        return isBuilded;
    }

    public void setIsBuilded(String isBuilded) {
        this.isBuilded = isBuilded;
    }
}
