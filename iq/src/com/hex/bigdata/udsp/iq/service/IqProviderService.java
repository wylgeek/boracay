package com.hex.bigdata.udsp.iq.service;

import com.hex.bigdata.udsp.common.constant.EnumTrans;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.model.*;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by junjiem on 2017-3-2.
 */
@Service
public class IqProviderService extends BaseService {
    @Autowired
    private IqApplicationService iqApplicationService;
    @Autowired
    private IqMetadataService iqMetadataService;
    @Autowired
    private IqMetadataColService iqMetadataColService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private IqAppQueryColService iqAppQueryColService;
    @Autowired
    private IqAppReturnColService iqAppReturnColService;
    @Autowired
    private IqAppOrderColService iqAppOrderColService;
    @Autowired
    private GFDictMapper gfDictMapper;

    private static Logger logger = LogManager.getLogger(IqProviderService.class);

    /**
     * 获取字段信息
     *
     * @param dsId
     * @param schemaName
     * @return
     */
    public List<MetadataCol> getColumnInfo(String dsId, String schemaName) {
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comPropertiesList);
        Provider provider = getProviderImpl(datasource);
        return provider.columnInfo(datasource, schemaName);
    }

    /**
     * 查询
     *
     * @param appId
     * @param paraMap
     * @return
     */
    public IqResponse select(String appId, Map<String, String> paraMap) {
        Application application = getApplication(appId, paraMap);
        IqRequest request = new IqRequest(application);
        Datasource datasource = application.getMetadata().getDatasource();
        Provider provider = getProviderImpl(datasource);
        IqResponse response = provider.query(request);
        //设置返回字段信息
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        response.setColumns(putColumnIntoMap(returnColumns));
        return response;
    }

    /**
     * 分页查询
     *
     * @param appId
     * @param paraMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public IqResponse select(String appId, Map<String, String> paraMap, int pageIndex, int pageSize) {
        Application application = getApplication(appId, paraMap);
        IqRequest request = new IqRequest(application);
        Datasource datasource = application.getMetadata().getDatasource();
        Provider provider = getProviderImpl(datasource);
        IqResponse response = provider.query(request, pageIndex, pageSize);
        //设置返回字段信息
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        response.setColumns(putColumnIntoMap(returnColumns));
        return response;
    }

    /**
     * 返回字段信息插入到Map
     *
     * @param returnColumns
     * @return
     */
    private LinkedHashMap<String, String> putColumnIntoMap(List<ReturnColumn> returnColumns) {
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        for (ReturnColumn returnColumn : returnColumns) {
            columnMap.put(returnColumn.getName(), returnColumn.getType().getValue());
        }
        return columnMap;
    }


    /**
     * 获取应用
     *
     * @param appId
     * @param paraMap
     * @return
     */
    private Application getApplication(String appId, Map<String, String> paraMap) {
        Application application = getApplication(appId);
        List<QueryColumn> queryColumns = application.getQueryColumns();
        for (QueryColumn queryColumn : queryColumns) {
            if (queryColumn.isNeed()) {
                if (paraMap == null || StringUtils.isBlank(paraMap.get(queryColumn.getLabel()))) {
                    throw new IllegalArgumentException(queryColumn.getLabel() + "不能为空");
                }
                queryColumn.setValue(paraMap.get(queryColumn.getLabel()));
            } else {
                if (paraMap != null && StringUtils.isNotBlank(paraMap.get(queryColumn.getLabel()))) {
                    queryColumn.setValue(paraMap.get(queryColumn.getLabel()));
                }
            }
        }
        application.setQueryColumns(queryColumns);
        return application;
    }

    /**
     * 获取应用
     *
     * @param appId
     * @return
     */
    private Application getApplication(String appId) {
        IqApplication iqApplication = iqApplicationService.select(appId);
        List<IqAppQueryCol> iqAppQueryColList = iqAppQueryColService.selectByAppId(appId);
        List<IqAppReturnCol> iqAppReturnColList = iqAppReturnColService.selectByAppId(appId);
        List<IqAppOrderCol> iqAppOrderColList = iqAppOrderColService.selectByAppId(appId);
        String mdId = iqApplication.getMdId();

        IqMetadata iqMetadata = iqMetadataService.select(mdId);
        List<IqMetadataCol> iqMetadataColList = iqMetadataColService.selectByMdId(mdId);
        String dsId = iqMetadata.getDsId();

        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(dsId);

        // ----------------数据封装-----------------------
        Application application = new Application();
        application.setName(iqApplication.getName());
        if (iqApplication.getMaxNum() != null)
            application.setMaxNum(Integer.valueOf(iqApplication.getMaxNum().toString()));
        application.setDescribe(iqApplication.getDescribe());
        application.setNote(iqApplication.getNote());
        List<QueryColumn> queryColumnList = new ArrayList<>();
        for (IqAppQueryCol iqAppQueryCol : iqAppQueryColList) {
            QueryColumn queryColumn = new QueryColumn();
            queryColumn.setSeq(iqAppQueryCol.getSeq());
            queryColumn.setDescribe(iqAppQueryCol.getDescribe());
            queryColumn.setName(iqAppQueryCol.getName());
            queryColumn.setDefaultVal(iqAppQueryCol.getDefaultVal());
            queryColumn.setLabel(iqAppQueryCol.getLabel());
            queryColumn.setLength(iqAppQueryCol.getLength());
            queryColumn.setNeed(EnumTrans.transTrue(iqAppQueryCol.getIsNeed()));
            queryColumn.setOfferOut(EnumTrans.transTrue(iqAppQueryCol.getIsOfferOut()));
            queryColumn.setOperator(EnumTrans.transOperator(iqAppQueryCol.getOperator()));
            queryColumn.setType(EnumTrans.transDataType(iqAppQueryCol.getType()));
            //queryColumn.setValue(paraMap.get(iqAppQueryCol.getName()));
            queryColumnList.add(queryColumn);
        }
        application.setQueryColumns(queryColumnList);
        List<ReturnColumn> returnColumnList = new ArrayList<>();
        for (IqAppReturnCol iqAppReturnCol : iqAppReturnColList) {
            ReturnColumn returnColumn = new ReturnColumn();
            returnColumn.setSeq(iqAppReturnCol.getSeq());
            returnColumn.setDescribe(iqAppReturnCol.getDescribe());
            returnColumn.setName(iqAppReturnCol.getName());
            returnColumn.setLabel(iqAppReturnCol.getLabel());
            returnColumn.setLength(iqAppReturnCol.getLength());
            returnColumn.setStats(EnumTrans.transStats(iqAppReturnCol.getStats()));
            returnColumn.setType(EnumTrans.transDataType(iqAppReturnCol.getType()));
            returnColumnList.add(returnColumn);
        }
        application.setReturnColumns(returnColumnList);
        List<OrderColumn> orderColumnList = new ArrayList<>();
        for (IqAppOrderCol iqAppOrderCol : iqAppOrderColList) {
            OrderColumn orderColumn = new OrderColumn();
            orderColumn.setSeq(iqAppOrderCol.getSeq());
            orderColumn.setDescribe(iqAppOrderCol.getDescribe());
            orderColumn.setName(iqAppOrderCol.getName());
            orderColumn.setType(EnumTrans.transDataType(iqAppOrderCol.getType()));
            orderColumn.setOrder(EnumTrans.transOrder(iqAppOrderCol.getOrderType()));
            orderColumnList.add(orderColumn);
        }
        application.setOrderColumns(orderColumnList);

        Metadata metadata = new Metadata();
        metadata.setName(iqMetadata.getName());
        metadata.setDescribe(iqMetadata.getDescribe());
        metadata.setNote(iqMetadata.getNote());
        metadata.setTbName(iqMetadata.getTbName());
        List<DataColumn> queryColumns = new ArrayList<>();
        List<DataColumn> returnColumns = new ArrayList<>();
        for (IqMetadataCol iqMetadataCol : iqMetadataColList) {
            DataColumn dataColumn = new DataColumn();
            dataColumn.setSeq(iqMetadataCol.getSeq());
            dataColumn.setName(iqMetadataCol.getName());
            dataColumn.setDescribe(iqMetadataCol.getDescribe());
            dataColumn.setNote(iqMetadataCol.getNote());
            dataColumn.setLength(iqMetadataCol.getLength());
            dataColumn.setType(EnumTrans.transDataType(iqMetadataCol.getColType()));
            queryColumns.add(dataColumn);
            if ("1".equals(iqMetadataCol.getType())) {
                queryColumns.add(dataColumn);
            } else {
                returnColumns.add(dataColumn);
            }
        }
        metadata.setQueryColumns(queryColumns);
        metadata.setReturnColumns(returnColumns);

        Datasource datasource = new Datasource(comDatasource, comPropertiesList);

        metadata.setDatasource(datasource);
        application.setMetadata(metadata);

        return application;
    }

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
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("IQ_IMPL_CLASS", datasource.getType());
            implClass = gfDict.getDictName();
        }
        return (Provider) ObjectUtil.newInstance(implClass);
    }
}
