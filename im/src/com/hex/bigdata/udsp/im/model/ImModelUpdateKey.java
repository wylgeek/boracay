package com.hex.bigdata.udsp.im.model;

import java.io.Serializable;

public class ImModelUpdateKey implements Serializable {
    private String pkId;

    private String colId;

    private String modelId;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}