package com.hex.bigdata.udsp.rc.dto;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.rc.model.RcUserService;

import java.util.List;

/**
 * Created by JunjieM on 2017-11-22.
 */
public class RcUserServiceBatchDto {
    private RcUserServiceView rcUserServiceView;
    private List<ComProperties> comPropertiesList;

    public RcUserServiceView getRcUserServiceView() {
        return rcUserServiceView;
    }

    public void setRcUserServiceView(RcUserServiceView rcUserServiceView) {
        this.rcUserServiceView = rcUserServiceView;
    }

    public List<ComProperties> getComPropertiesList() {
        return comPropertiesList;
    }

    public void setComPropertiesList(List<ComProperties> comPropertiesList) {
        this.comPropertiesList = comPropertiesList;
    }
}
