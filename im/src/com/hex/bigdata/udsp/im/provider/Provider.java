package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface Provider {
    /**
     * 测试
     *
     * @param datasource
     * @return
     */
    boolean testDatasource(Datasource datasource);
}
