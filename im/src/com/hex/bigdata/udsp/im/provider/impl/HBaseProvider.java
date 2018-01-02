package com.hex.bigdata.udsp.im.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.HBaseUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.wrapper.HBaseWrapper;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
@Component("com.hex.bigdata.udsp.im.provider.impl.HBaseProvider")
public class HBaseProvider extends HBaseWrapper {
    private static Logger logger = LogManager.getLogger(HBaseProvider.class);
    private static final String rkSep = "|";
    private static final String startStr = "";
    private static final String stopStr = "|";

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        // HBase无法获取字段信息，返回null
        return null;
    }

    @Override
    public void createSchema(Metadata metadata) throws Exception {
        HBaseMetadata hBaseMetadata = new HBaseMetadata(metadata);
        HBaseUtil.createHTable(hBaseMetadata, true);
    }

    @Override
    public void dropSchema(Metadata metadata) throws Exception {
        HBaseDatasource hBaseDatasource = new HBaseDatasource(metadata.getDatasource());
        HBaseUtil.dropHTable(hBaseDatasource, metadata.getTbName(), true);
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Metadata metadata = model.getTargetMetadata();
        HiveDatasource eHiveDs = new HiveDatasource(model.getEngineDatasource());
        String id = model.getId();
        HBaseMetadata hbaseMetadata = new HBaseMetadata(metadata);
        String fullTbName = hbaseMetadata.getTbName();
        String tableName = getTargetTableName(id);
        List<ModelMapping> modelMappings = model.getModelMappings();
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                getTargetColumns(modelMappings, hbaseMetadata), "目标的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, getSerDeProperties(modelMappings, hbaseMetadata),
                getTblProperties(fullTbName));
        JdbcUtil.createEngineSchema(eHiveDs, HIVE_ENGINE_DATABASE_NAME, sql);
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws Exception {
        HBaseDatasource datasource = new HBaseDatasource(metadata.getDatasource());
        String tableName = metadata.getTbName();
        return HBaseUtil.isTableAvailable(datasource, tableName);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource);
        HConnection conn = null;
        try {
            conn = HBaseUtil.getConnection(hBaseDatasource);
            if (conn != null && !conn.isAborted()) {
                canConnection = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HBaseUtil.release(hBaseDatasource, conn);
        }
        return canConnection;
    }

    @Override
    protected void emptyDatas(Metadata metadata) throws Exception {
        HBaseMetadata hBaseMetadata = new HBaseMetadata(metadata);
        HBaseUtil.emptyHTable(hBaseMetadata);
    }
}
