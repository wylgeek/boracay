package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.RtsSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class RtsSyncProducerCallable implements Callable<Response> {

    /**
     * 实时流服务
     */
    private RtsSyncService rtsSyncService;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 数据流
     */
    private List<Map<String, String>> dataStream;

    public RtsSyncProducerCallable() {
    }

    public RtsSyncProducerCallable(RtsSyncService rtsSyncService, String appId, List<Map<String, String>> dataStream) {
        this.appId = appId;
        this.dataStream = dataStream;
        this.rtsSyncService = (RtsSyncService) WebApplicationContextUtil.getBean("rtsSyncService");
    }

    @Override
    public Response call() throws Exception {
        return this.rtsSyncService.startProducer(this.appId,this.dataStream);
    }
}
