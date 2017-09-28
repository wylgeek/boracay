package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
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
    public boolean createSchema(Metadata metadata) throws Exception {
        return hbaseProvider.createSchema(metadata) && solrProvider.createSchema(metadata);
    }

    @Override
    public boolean dropSchema(Metadata metadata) throws Exception {
        return solrProvider.dropSchema(metadata) && hbaseProvider.dropSchema(metadata);
    }

    @Override
    public boolean checkSchemaExists(Metadata metadata) throws Exception {
        return solrProvider.checkSchemaExists(metadata) && hbaseProvider.checkSchemaExists(metadata);
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws Exception {
        String id = model.getId();
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        return hbaseProvider.createTargetEngineSchema(hBaseModel) && solrProvider.createTargetEngineSchema(solrModel);
    }

    @Override
    public boolean dropTargetEngineSchema(Model model) throws SQLException {
        String id = model.getId();
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        return hbaseProvider.dropTargetEngineSchema(solrModel) && solrProvider.dropTargetEngineSchema(hBaseModel);
    }

    @Override
    public void buildRealtime(Model model) {
        // TODO ...
    }

    @Override
    public void buildBatch(String key, Model model) throws SQLException {
        String id = model.getId();
        Model hBaseModel = new Model(model);
        hBaseModel.setId("HBASE" + HIVE_ENGINE_TABLE_SEP + id);
        Model solrModel = new Model(model);
        solrModel.setId("SOLR" + HIVE_ENGINE_TABLE_SEP + id);
        hbaseProvider.buildBatch("HBASE" + HIVE_ENGINE_TABLE_SEP + key, hBaseModel);
        solrProvider.buildBatch("SOLR" + HIVE_ENGINE_TABLE_SEP + key, solrModel);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        return hbaseProvider.testDatasource(datasource) && solrProvider.testDatasource(datasource);
    }
}
