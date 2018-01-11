package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.EdSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by PC on 2017/10/10.
 */
public class EdSyncServiceCallable<T> implements Callable<Response> {

    private EdSyncService edSyncService;

    private Map<String, String> data;

    private String appId;

    public EdSyncServiceCallable(String appId, Map<String, String> data) {
        this.appId = appId;
        this.data = data;
        this.edSyncService = (EdSyncService) WebApplicationContextUtil.getBean("edSyncService");
    }

    @Override
    public Response call() throws Exception {
        return edSyncService.start(appId, data);
    }
}
