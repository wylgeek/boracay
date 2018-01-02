package com.hex.bigdata.udsp.dto;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.WaitQueueService;
import com.hex.bigdata.udsp.model.Request;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;

public class ConsumeRequest {

    private Request request;

    private Current mcCurrent;

    private ErrorCode error;

    private RcService rcService;

    private RcUserService rcUserService;

    private WaitNumResult waitNumResult;

    //private WaitQueueService mcWaitQueueService;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Current getMcCurrent() {
        return mcCurrent;
    }

    public void setMcCurrent(Current mcCurrent) {
        this.mcCurrent = mcCurrent;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }

//    public RcService getRcService() {
//        return rcService;
//    }
//
//    public void setRcService(RcService rcService) {
//        this.rcService = rcService;
//    }

    public RcUserService getRcUserService() {
        return rcUserService;
    }

    public void setRcUserService(RcUserService rcUserService) {
        this.rcUserService = rcUserService;
    }

    public WaitNumResult getWaitNumResult() {
        return waitNumResult;
    }

    public void setWaitNumResult(WaitNumResult waitNumResult) {
        this.waitNumResult = waitNumResult;
    }

//    public WaitQueueService getMcWaitQueueService() {
//        return mcWaitQueueService;
//    }
//
//    public void setMcWaitQueueService(WaitQueueService mcWaitQueueService) {
//        this.mcWaitQueueService = mcWaitQueueService;
//    }
}
