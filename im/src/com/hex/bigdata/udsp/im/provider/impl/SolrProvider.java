package com.hex.bigdata.udsp.im.provider.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.SolrMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.SolrModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrProvider")
public class SolrProvider extends SolrWrapper {
    private static Logger logger = LogManager.getLogger(SolrProvider.class);
    private static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "com.hex.hive.solr.SolrStorageHandler";

    public List<MetadataCol> getColumns(String collectionName, String solrServers) {
        if (StringUtils.isEmpty(collectionName) || StringUtils.isEmpty(solrServers)) {
            return null;
        }
        String response = "";
        String[] addresses = solrServers.split(",");
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/" + collectionName + "/schema/fields";
            response = SolrUtil.sendGet(url, "");
            if (StringUtils.isEmpty(response)) {
                continue;
            } else {
                break;
            }
        }
        JSONObject rs = JSONObject.parseObject(response);
        JSONArray fields = (JSONArray) rs.get("fields");
        List<MetadataCol> metadataCols = new ArrayList<>();
        MetadataCol mdCol = null;
        for (int i = 0; i < fields.size(); i++) {
            mdCol = new MetadataCol();
            mdCol.setSeq((short) i);
            mdCol.setName((String) fields.getJSONObject(i).get("name"));
            mdCol.setDescribe((String) fields.getJSONObject(i).get("name"));
            mdCol.setType(SolrUtil.getColType((String) fields.getJSONObject(i).get("type")));
            mdCol.setIndexed((boolean) fields.getJSONObject(i).get("indexed"));
            mdCol.setStored((boolean) fields.getJSONObject(i).get("stored"));
            mdCol.setPrimary(fields.getJSONObject(i).get("uniqueKey") == null ? false : true);
            metadataCols.add(mdCol);
        }
        return metadataCols;
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        String collectionName = metadata.getTbName();
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        String solrServers = solrDatasource.getSolrServers();
        return getColumns(collectionName, solrServers);
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        SolrMetadata solrMetadata = new SolrMetadata(metadata);
        SolrUtil.createCollection(solrMetadata, true);
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        SolrMetadata solrMetadata = new SolrMetadata(metadata);
        SolrUtil.dropCollection(solrMetadata, true);
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        String collectionName = metadata.getTbName();
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        String solrServers = solrDatasource.getSolrServers();
        return SolrUtil.checkCollection(solrServers, collectionName);
    }

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        SolrDatasource solrDatasource = new SolrDatasource(model.getSourceDatasource());
        String solrServers = solrDatasource.getSolrServers();
        SolrModel solrModel = new SolrModel(model.getProperties(), model.getSourceDatasource());
        String collectionName = solrModel.getCollectionName();
        return getColumns(collectionName, solrServers);
    }

    /**
     * 增量插入数据
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @throws Exception
     */
    @Override
    protected void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception {
        checkModelMappings(modelMappings);
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        Map<String, String> map = new HashMap<>();
        for (ValueColumn column : valueColumns) {
            map.put(column.getColName(), column.getValue());
        }
        SolrUtil.addDocument(solrDatasource, metadata.getTbName(), map);
    }

    /**
     * 更新数据或者增量插入数据
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @param whereProperties
     * @throws Exception
     */
    @Override
    protected void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        checkModelMappings(modelMappings);
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        String tableName = metadata.getTbName();
        String idName = getIdName(modelMappings);
        List<Map<String, String>> list = SolrUtil.search(solrDatasource, tableName, whereProperties);
        // 查询到条件数据，则进行更新，否则增量插入
        if (list != null && list.size() != 0) {
            update(solrDatasource, tableName, idName, list, valueColumns);
        } else {
            Map<String, String> map = new HashMap<>();
            for (ValueColumn column : valueColumns) {
                map.put(column.getColName(), column.getValue());
            }
            SolrUtil.addDocument(solrDatasource, metadata.getTbName(), map);
        }
    }

    /**
     * 更新数据
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @param whereProperties
     * @throws Exception
     */
    @Override
    protected void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        checkModelMappings(modelMappings);
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        String tableName = metadata.getTbName();
        String idName = getIdName(modelMappings);
        List<Map<String, String>> list = SolrUtil.search(solrDatasource, tableName, whereProperties);
        if (list != null && list.size() != 0) {
            update(solrDatasource, tableName, idName, list, valueColumns);
        }
    }

    private void checkModelMappings(List<ModelMapping> modelMappings) {
        boolean flg = false;
        for (ModelMapping modelMapping : modelMappings) {
            if (modelMapping.getMetadataCol().isPrimary()) {
                flg = true;
                break;
            }
        }
        if (!flg) {
            throw new IllegalArgumentException("【映射字段】的目标字段必须要有一个是主键字段！");
        }
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<String> selectColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            selectColumns.add(mapping.getName());
        return selectColumns;
    }

    @Override
    protected void emptyDatas(Metadata metadata) throws Exception {
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource());
        String collectionName = metadata.getTbName();
        SolrUtil.deleteAll(solrDatasource, collectionName);
    }

    @Override
    public void createSourceEngineSchema(Model model) throws Exception {
        HiveDatasource eHiveDs = new HiveDatasource(model.getEngineDatasource());
        String id = model.getId();
        SolrModel solrModel = new SolrModel(model);
        String collectionName = solrModel.getCollectionName();
        String engineSchemaName = getSourceTableName(id);
        SolrDatasource solrDs = new SolrDatasource(model.getSourceDatasource());
        List<ModelMapping> modelMappings = model.getModelMappings();
        String pkName = getSourcePrimaryKey(modelMappings);
        List<TblProperty> tblProperties = getSourceTblProperties(solrDs, pkName, collectionName, modelMappings, "*:*");
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, engineSchemaName,
                getSourceColumns(modelMappings), "源的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, tblProperties);
        JdbcUtil.createEngineSchema(eHiveDs, HIVE_ENGINE_DATABASE_NAME, sql);
    }

    @Override
    public void createSourceEngineSchema(Model model, String engineSchemaName) throws Exception {
        HiveDatasource eHiveDs = new HiveDatasource(model.getEngineDatasource());
        SolrModel solrModel = new SolrModel(model);
        String collectionName = solrModel.getCollectionName();
        SolrDatasource solrDs = new SolrDatasource(model.getSourceDatasource());
        List<ModelMapping> modelMappings = model.getModelMappings();
        String pkName = getSourcePrimaryKey(modelMappings);
        List<WhereProperty> whereProperties = getWhereProperties(model.getModelFilterCols());
        String solrQuery = SolrUtil.getQuery(whereProperties);
        List<TblProperty> tblProperties = getSourceTblProperties(solrDs, pkName, collectionName, modelMappings, solrQuery);
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, engineSchemaName,
                getSourceColumns(modelMappings), "源的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, tblProperties);
        JdbcUtil.createEngineSchema(eHiveDs, HIVE_ENGINE_DATABASE_NAME, sql);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Metadata metadata = model.getTargetMetadata();
        HiveDatasource eHiveDs = new HiveDatasource(model.getEngineDatasource());
        SolrMetadata solrMetadata = new SolrMetadata(metadata);
        SolrDatasource solrDs = new SolrDatasource(metadata.getDatasource());
        List<ModelMapping> modelMappings = model.getModelMappings();
        String pkName = getTargetPrimaryKey(modelMappings);
        String tableName = getTargetTableName(model.getId());
        String collectionName = solrMetadata.getTbName();
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                getTargetColumns(modelMappings), "目标的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTargetTblProperties(solrDs, pkName, collectionName, modelMappings));
        JdbcUtil.executeUpdate(eHiveDs, sql);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            SolrDatasource solrDatasource = new SolrDatasource(datasource);
            String[] tempServers = solrDatasource.getSolrServers().split(",");
            for (int i = 0; i < tempServers.length; i++) {
                try {
                    url = new URL("http://" + tempServers[i] + "/solr");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.connect();
                    if (connection != null) {
                        canConnection = true;
                        break;
                    }
                } catch (Exception e) {
                    logger.debug("获取solr连接失败的地址为：" + (url == null ? "" : url.toString()));
                    canConnection = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            canConnection = false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return canConnection;
    }
}
