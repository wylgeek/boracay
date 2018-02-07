package com.hex.bigdata.udsp.olq.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.olq.provider.model.OlqRequest;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;

/**
 * Created by junjiem on 2017-2-15.
 */
public interface Provider {

    /**
     * 执行
     *
     * @param consumeId
     * @param request
     * @return
     */
    OlqResponse execute(String consumeId, OlqRequest request);

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    boolean testDatasource(Datasource datasource);

    /**
     * 执行（结果集一批批抽取）
     *
     * @param request
     * @return
     */
    OlqResponseFetch executeFetch(String consumeId, OlqRequest request);
}
