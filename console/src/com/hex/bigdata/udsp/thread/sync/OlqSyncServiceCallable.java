package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.OlqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

/**
 * 联机查询同步查询线程
 *
 * @param <T>
 */
public class OlqSyncServiceCallable<T> implements Callable<Response> {

    private OlqSyncService olqSyncService;
    private String consumeId;
    private String dsId;
    private String sql;
    private Page page;

    public OlqSyncServiceCallable(String consumeId, String dsId, String sql, Page page) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.consumeId = consumeId;
        this.dsId = dsId;
        this.sql = sql;
        this.page = page;
    }

    @Override
    public Response call() throws Exception {
        return olqSyncService.syncStart(this.consumeId, this.dsId, this.sql, this.page);
    }
}
