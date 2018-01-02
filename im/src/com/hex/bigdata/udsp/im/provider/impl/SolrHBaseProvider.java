package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.SolrHBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider extends SolrHBaseWrapper {
    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);

    @Autowired
    private SolrProvider solrProvider;
    @Autowired
    private HBaseProvider hbaseProvider;

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        String collectionName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();
        String solrServers = datasource.getPropertyMap().get("solr.servers").getValue();
        return solrProvider.getColumns(collectionName, solrServers);
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        boolean result;
        hbaseProvider.createSchema(metadata);
        try {
            solrProvider.createSchema(metadata);
        } catch (Exception e) {
            // 回滚删除hbase对应的表 删除失败怎么办？暂时不考虑
            hbaseProvider.dropSchema(metadata);
            throw new Exception(e);
        }
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        solrProvider.dropSchema(metadata);
        hbaseProvider.dropSchema(metadata);
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        return solrProvider.checkSchema(metadata) && hbaseProvider.checkSchema(metadata);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        String id = model.getId();
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseProvider.createTargetEngineSchema(hBaseModel);
        try {
            solrProvider.createTargetEngineSchema(solrModel);
        } catch (Exception e) {
            //删除回滚
            hbaseProvider.dropTargetEngineSchema(hBaseModel);
            throw new Exception(e);
        }
    }

    @Override
    public void dropTargetEngineSchema(Model model) throws SQLException {
        String id = model.getId();
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseProvider.dropTargetEngineSchema(solrModel);
        solrProvider.dropTargetEngineSchema(hBaseModel);
    }

    @Override
    public void buildRealtime(String key, Model model) throws Exception {
        String sDsType = model.getSourceDatasource().getType();
        if (DatasourceType.KAFKA.getValue().equals(sDsType)) {
            // 将配置的group.id参数删除，强制内部使用modeId作为group.id
            String id = model.getId();
            model.getPropertyMap().remove("group.id");

            Model hbaseModel = new Model(model);
            hbaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
            hbaseProvider.buildRealtime(key, hbaseModel);

            Model solrModel = new Model(model);
            solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
            solrProvider.buildRealtime(key, solrModel);
        }
    }

    @Override
    public void buildBatch(String key, Model model) throws Exception {
        String id = model.getId();

        // model的Id在建引擎表时会用到，所以需要区分
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        // build的key在监控中会用到，所以需要区分
        hbaseProvider.buildBatch("HBASE" + HIVE_ENGINE_TABLE_SEP + key, hBaseModel);

        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        solrProvider.buildBatch("SOLR" + HIVE_ENGINE_TABLE_SEP + key, solrModel);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseProvider.testDatasource(datasource) && solrProvider.testDatasource(datasource);
    }
}
