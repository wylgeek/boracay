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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
        Datasource datasource = metadata.getDatasource();
        String collectionName = metadata.getTbName();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        String solrServers = solrDatasource.getSolrServers();
        return getColumns(collectionName, solrServers);
    }

    @Override
    public boolean createSchema(Metadata metadata) throws Exception {
        SolrUtil.checkSolrProperty(metadata); // 检查参数
        SolrUtil.uploadSolrConfig(metadata); // 添加配置
        SolrMetadata solrMetadata = new SolrMetadata(metadata);
        String[] addresses = getSolrServerStrings(metadata);
        String response = "";
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/admin/collections";
            String param = "action=CREATE" + "&name=" + metadata.getTbName() + "&replicationFactor=" + solrMetadata.getReplicas() +
                    "&numShards=" + solrMetadata.getShards() + "&maxShardsPerNode=" + solrMetadata.getMaxShardsPerNode() +
                    "&collection.configName=" + metadata.getTbName();
            response = SolrUtil.sendGet(url, param);
            if (StringUtils.isEmpty(response)) {
                continue;
            } else {
                break;
            }
        }
        return true;
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        SolrUtil.deleteZnode(metadata); // 删除配置
        String[] addresses = getSolrServerStrings(metadata);
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/admin/collections";
            String param = "action=DELETE" + "&name=" + metadata.getTbName();
            try {
                SolrUtil.sendGet(url, param);
            } catch (Exception e) {
                continue;
            }
            break;
        }
        return true;
    }

    private String[] getSolrServerStrings(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        return solrDatasource.getSolrServers().split(",");
    }

    @Override
    public boolean checkSchemaExists(Metadata metadata) throws Exception {
        String[] addresses = getSolrServerStrings(metadata);
        String response = "";
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/admin/collections?action=LIST";
            try {
                response = SolrUtil.sendGet(url, null);
            } catch (Exception e) {
                continue;
            }
            break;
        }
        Document document = DocumentHelper.parseText(response);
        Element root = document.getRootElement();
        Element arr = root.element("arr");
        List<Element> collections = arr.elements("str");
        boolean exists = false;
        for (Element e : collections) {
            if (e.getData().equals(metadata.getTbName())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        String solrServers = solrDatasource.getSolrServers();
        SolrModel solrModel = new SolrModel(model.getProperties(), model.getSourceDatasource());
        String collectionName = solrModel.getCollectionName();
        return getColumns(collectionName, solrServers);
    }

    @Override
    protected void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception {
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource().getPropertyMap());
        Map<String, String> map = new HashMap<>();
        for (ValueColumn column : valueColumns) {
            map.put(column.getColName(), column.getValue());
        }
        SolrUtil.addDocument(solrDatasource, metadata.getTbName(), map);
    }

    @Override
    protected void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource().getPropertyMap());
        String tableName = metadata.getTbName();
        String idName = getIdName(modelMappings);
        List<Map<String, String>> list = SolrUtil.search(solrDatasource, tableName, whereProperties);
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

    @Override
    protected void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource().getPropertyMap());
        String tableName = metadata.getTbName();
        String idName = getIdName(modelMappings);
        List<Map<String, String>> list = SolrUtil.search(solrDatasource, tableName, whereProperties);
        if (list != null && list.size() != 0) {
            update(solrDatasource, tableName, idName, list, valueColumns);
        }
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<java.lang.String> selectColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            selectColumns.add(mapping.getName());
        return selectColumns;
    }

    @Override
    protected List<String> getInsertColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<java.lang.String> insertColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            insertColumns.add(mapping.getMetadataCol().getName());
        return insertColumns;
    }

    @Override
    public boolean createSourceEngineSchema(Model model) throws Exception {
        Datasource datasource = model.getSourceDatasource();
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        SolrModel solrModel = new SolrModel(model);
        String collectionName = solrModel.getCollectionName();
        String tableName = getSourceTableName(id);
        SolrDatasource solrDs = new SolrDatasource(datasource.getPropertyMap());
        List<ModelMapping> modelMappings = model.getModelMappings();
        String pkName = getSourcePrimaryKey(modelMappings);
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                getSourceColumns(modelMappings), "源的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(solrDs, pkName, collectionName));
        return JdbcUtil.createEngineSchema(eHiveDs, HIVE_ENGINE_DATABASE_NAME, sql);
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        Metadata metadata = model.getTargetMetadata();
        Datasource datasource = metadata.getDatasource();
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        SolrMetadata solrMetadata = new SolrMetadata(metadata);
        SolrDatasource solrDs = new SolrDatasource(datasource.getPropertyMap());
        List<ModelMapping> modelMappings = model.getModelMappings();
        String pkName = getTargetPrimaryKey(modelMappings);
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, getTargetTableName(model.getId()),
                getTargetColumns(modelMappings), "目标的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(solrDs, pkName, solrMetadata.getTbName()));
        return JdbcUtil.executeUpdate(eHiveDs, sql);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            SolrDatasource solrDatasource = new SolrDatasource(datasource.getProperties());
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
