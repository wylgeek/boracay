package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.factory.SolrConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrHBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class SolrWrapper extends Wrapper implements BatchSourceProvider, BatchTargetProvider, RealtimeTargetProvider {
    private static Logger logger = LogManager.getLogger(SolrWrapper.class);
    private static Map<String, SolrConnectionPoolFactory> dataSourcePool;

    protected String getSourcePrimaryKey(List<ModelMapping> modelMappings) {
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            if (metadataCol.isPrimary()) {
                return mapping.getName();
            }
        }
        return null;
    }

    protected String getTargetPrimaryKey(List<ModelMapping> modelMappings) {
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            if (metadataCol.isPrimary()) {
                return metadataCol.getName();
            }
        }
        return null;
    }

    protected List<TblProperty> getTblProperties(SolrDatasource datasource, String pkName, String collectionName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("solr.url", datasource.getSolrUrl())); // zookeeper地址、端口和目录
        tblProperties.add(new TblProperty("solr.query", "*:*")); // Solr查询语句
        tblProperties.add(new TblProperty("solr.cursor.batch.size", "1000")); // 批量大小
        tblProperties.add(new TblProperty("solr.primary.key", pkName)); // Solr Collection 主键字段名
        tblProperties.add(new TblProperty("is.solrcloud", "1")); // 0：单机模式，1：集群模式，Default：0
        tblProperties.add(new TblProperty("collection.name", collectionName)); // Solr Collection Name
        return tblProperties;
    }

    protected List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            String dataType = getDataType(metadataCol.getType(), metadataCol.getLength());
            columns.add(new TableColumn(metadataCol.getName(), dataType, metadataCol.getDescribe()));
        }
        return columns;
    }

    protected List<TableColumn> getSourceColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            String dataType = getDataType(mapping.getType(), mapping.getLength());
            columns.add(new TableColumn(mapping.getName(), dataType, mapping.getDescribe()));
        }
        return columns;
    }

    protected void update(SolrDatasource solrDatasource, String tableName, String idName, List<Map<String, String>> list, List<ValueColumn> valueColumns) {
        // 获得满足条件主键值集合
        List<String> ids = new ArrayList<>();
        for (Map<String, String> m : list) {
            ids.add(m.get(idName));
        }
        // 获得更新字段信息
        Map<String, String> map = new HashMap<>();
        for (ValueColumn column : valueColumns) {
            if (!idName.equals(column.getColName()))
                map.put(column.getColName(), column.getValue());
        }
        // 更新满足条件数据信息
        SolrUtil.updateDocument(solrDatasource, tableName, idName, ids, map);
    }

    protected String getIdName(List<ModelMapping> modelMappings) {
        String idName = "";
        for (ModelMapping mapping : modelMappings) {
            MetadataCol col = mapping.getMetadataCol();
            if (col.isPrimary()) {
                idName = col.getName();
                break;
            }
        }
        return idName;
    }
}
