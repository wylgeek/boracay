package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.ImSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by PC on 2017/10/10.
 */
public class ImSyncServiceCallable<T> implements Callable<Response> {

    private ImSyncService imSyncService;

    private Map<String, String> data;

    private String appId;

    public ImSyncServiceCallable(String appId, Map<String, String> data) {
        this.appId = appId;
        this.data = data;
        this.imSyncService = (ImSyncService) WebApplicationContextUtil.getBean("imSyncService");
    }

    @Override
    public Response call() throws Exception {
        return imSyncService.start(appId, data);
    }
}
