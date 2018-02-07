package com.hex.bigdata.udsp.iq.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.provider.model.IqRequest;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;

import java.util.List;

/**
 * Created by junjiem on 2017-2-15.
 */
public interface Provider {

    /**
     * 查询
     *
     * @param request
     * @return
     */
    IqResponse query(IqRequest request);

    /**
     * 分页查询
     *
     * @param request
     * @param pageIndex
     * @param pageSize
     * @return
     */
    IqResponse query(IqRequest request, int pageIndex, int pageSize);

    /**
     * 测试
     *
     * @param datasource
     * @return
     */
    boolean testDatasource(Datasource datasource);

    /**
     * 获取字段信息
     *
     * @param datasource
     * @param schemaName
     * @return
     */
    List<MetadataCol> columnInfo(Datasource datasource, String schemaName);

}
