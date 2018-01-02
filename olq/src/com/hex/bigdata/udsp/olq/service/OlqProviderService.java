package com.hex.bigdata.udsp.olq.service;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.model.OlqRequest;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by junjiem on 2017-3-2.
 */
@Service
public class OlqProviderService extends BaseService {
    @Autowired
    private ComDatasourceService comDatasourceService;

    @Autowired
    private ComPropertiesService comPropertiesService;

    @Autowired
    private GFDictMapper gfDictMapper;

    /**
     * 查询
     *
     * @param consumeId
     * @param dsId
     * @param sql
     * @param page
     * @return
     */
    public OlqResponse select(String consumeId, String dsId, String sql, Page page) {
        Datasource datasource = getDatasource(dsId);
        OlqRequest request = new OlqRequest(datasource, sql, page);
        Provider provider = getProviderImpl(datasource);
        OlqResponse response = provider.execute(consumeId, request);
        return response;
    }

    /**
     * 查询（结果集一批批抽取）
     *
     * @param dsId
     * @param sql
     * @param page
     * @return
     */
    public OlqResponseFetch selectFetch(String consumeId, String dsId, String sql, Page page) {
        Datasource datasource = getDatasource(dsId);
        OlqRequest request = new OlqRequest(datasource, sql, page);
        Provider provider = getProviderImpl(datasource);
        OlqResponseFetch response = provider.executeFetch(consumeId, request);
        return response;
    }

    /**
     * 获取数据源
     *
     * @param dsId
     * @return
     */
    private Datasource getDatasource(String dsId) {
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comPropertiesList);
        return datasource;
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        Provider provider = getProviderImpl(datasource);
        return provider.testDatasource(datasource);
    }

    /**
     * 得到生产接口的实例
     *
     * @param datasource
     * @return
     */
    private Provider getProviderImpl(Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("OLQ_IMPL_CLASS", datasource.getType());
            implClass = gfDict.getDictName();
        }
        return (Provider) ObjectUtil.newInstance(implClass);
    }
}
