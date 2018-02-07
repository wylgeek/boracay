package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.IqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.Map;
import java.util.concurrent.Callable;

public class IqSyncServiceCallable<T> implements Callable<Response> {

    private IqSyncService iqSyncService;
    private Map<String, String> data;
    private String appId;
    private Page page;

    public IqSyncServiceCallable(Map<String, String> data, String appId, Page page) {
        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.data = data;
        this.appId = appId;
        this.page = page;
    }

    @Override
    public Response call() throws Exception {
        return iqSyncService.syncStart(appId, data, page);
    }

}
