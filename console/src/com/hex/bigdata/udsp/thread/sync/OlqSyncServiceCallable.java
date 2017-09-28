package com.hex.bigdata.udsp.thread.sync;

import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.service.OlqSyncService;
import com.hex.goframe.util.WebApplicationContextUtil;

import java.util.concurrent.Callable;

/**
 * 联机查询同步查询线程
 *
 * @param <T>
 */
public class OlqSyncServiceCallable<T> implements Callable<Response> {

    /**
     * 联机查询同步服务
     */
    private OlqSyncService olqSyncService;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 查询SQL实体
     */
    private OLQQuerySql olqQuerySql;

    public OlqSyncServiceCallable() {
    }

    public OlqSyncServiceCallable(String appId, OLQQuerySql olqQuerySql) {
        this.appId = appId;
        this.olqQuerySql = olqQuerySql;
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
    }

    @Override
    public Response call() throws Exception {
        return olqSyncService.syncStart(appId, olqQuerySql);
    }
}
