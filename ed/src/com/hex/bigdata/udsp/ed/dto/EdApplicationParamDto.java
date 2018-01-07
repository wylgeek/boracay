package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.bigdata.udsp.ed.model.EdApplication;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/6
 * Time:21:51
 */
public class EdApplicationParamDto {
    private EdApplication edApplication;
    private List<EdAppRequestParam> edAppRequestParam;
    private List<EdAppResponseParam> edAppResponseParam;

    public EdApplication getEdApplication() {
        return edApplication;
    }

    public void setEdApplication(EdApplication edApplication) {
        this.edApplication = edApplication;
    }

    public List<EdAppRequestParam> getEdAppRequestParam() {
        return edAppRequestParam;
    }

    public void setEdAppRequestParam(List<EdAppRequestParam> edAppRequestParam) {
        this.edAppRequestParam = edAppRequestParam;
    }

    public List<EdAppResponseParam> getEdAppResponseParam() {
        return edAppResponseParam;
    }

    public void setEdAppResponseParam(List<EdAppResponseParam> edAppResponseParam) {
        this.edAppResponseParam = edAppResponseParam;
    }
}
