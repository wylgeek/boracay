package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.RtsSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

public class RtsSyncConsumerCallable implements Callable<Response> {

    /**
     * 实时流服务
     */
    private RtsSyncService rtsSyncService;

    /**
     * 应用id
     */
    private String appId;

    public RtsSyncConsumerCallable(String appId, long timeout) {
        this.appId = appId;
        this.timeout = timeout;
        this.rtsSyncService = (RtsSyncService) WebApplicationContextUtil.getBean("rtsSyncService");
    }

    /**
     * 超时时间
     */
    private long timeout;

    @Override
    public Response call() throws Exception {
        return this.rtsSyncService.startConsumer(appId, timeout);
    }
}
