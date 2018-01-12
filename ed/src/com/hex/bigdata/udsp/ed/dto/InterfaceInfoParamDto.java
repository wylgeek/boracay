package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/5
 * Time:16:28
 */
public class InterfaceInfoParamDto {
    private InterfaceInfo interfaceInfo;
    private List<EdInterfaceParam> edInterfaceParamsRequest;
    private List<EdInterfaceParam> edInterfaceParamsResponse;

    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public List<EdInterfaceParam> getEdInterfaceParamsRequest() {
        return edInterfaceParamsRequest;
    }

    public void setEdInterfaceParamsRequest(List<EdInterfaceParam> edInterfaceParamsRequest) {
        this.edInterfaceParamsRequest = edInterfaceParamsRequest;
    }

    public List<EdInterfaceParam> getEdInterfaceParamsResponse() {
        return edInterfaceParamsResponse;
    }

    public void setEdInterfaceParamsResponse(List<EdInterfaceParam> edInterfaceParamsResponse) {
        this.edInterfaceParamsResponse = edInterfaceParamsResponse;
    }
}
