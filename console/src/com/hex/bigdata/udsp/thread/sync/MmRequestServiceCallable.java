package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.MmRequestService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

public class MmRequestServiceCallable implements Callable<Response> {

    private MmRequestService mmRequestService;
    private ConsumeRequest consumeRequest;
    private String appId;

    public MmRequestServiceCallable() {
    }

    public MmRequestServiceCallable(ConsumeRequest consumeRequest, String appId) {
        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.mmRequestService = (MmRequestService) WebApplicationContextUtil.getBean("mmRequestService");;
    }

    @Override
    public Response call() throws Exception {
        return null;
    }
}
