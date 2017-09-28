package com.hex.bigdata.udsp.thread.async;

import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.service.IqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.Map;
import java.util.concurrent.Callable;

public class IqAsyncCallable implements Callable<IqResponse> {

    private IqSyncService iqSyncService;

    private McCurrent mcCurrent;
    private String appId;
    private Map<String, String> paraMap;
    private Page page;
    private String fileName;

    public IqAsyncCallable() {
    }

    public IqAsyncCallable(McCurrent mcCurrent, String appId, Map<String, String> paraMap, Page page, String fileName) {
        iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.mcCurrent = mcCurrent;
        this.appId = appId;
        this.paraMap = paraMap;
        this.page = page;
        this.fileName = fileName;
    }

    @Override
    public IqResponse call() throws Exception {
        return this.iqSyncService.asyncStart(appId,paraMap,page,fileName,this.mcCurrent.getUserName());
    }
}
