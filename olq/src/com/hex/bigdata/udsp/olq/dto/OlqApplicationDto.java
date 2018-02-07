package com.hex.bigdata.udsp.olq.dto;

import com.hex.bigdata.udsp.olq.model.OlqApplication;
import com.hex.bigdata.udsp.olq.model.OlqApplicationParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/28
 * TIME:21:05
 */
public class OlqApplicationDto implements Serializable{

    /**
     * 应用信息
     */
    private OlqApplication olqApplication;

    /**
     * 应用参数信息
     */
    private List<OlqApplicationParam> params;


    public OlqApplication getOlqApplication() {
        return olqApplication;
    }

    public void setOlqApplication(OlqApplication olqApplication) {
        this.olqApplication = olqApplication;
    }

    public List<OlqApplicationParam> getParams() {
        return params;
    }

    public void setParams(List<OlqApplicationParam> params) {
        this.params = params;
    }
}
