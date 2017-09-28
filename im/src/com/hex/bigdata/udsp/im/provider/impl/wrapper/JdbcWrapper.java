package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HiveMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.JdbcModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class JdbcWrapper extends Wrapper implements BatchTargetProvider, BatchSourceProvider {
    private static Logger logger = LogManager.getLogger(JdbcWrapper.class);

    protected static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "com.hex.hive.jdbc.JdbcStorageHandler";

    @Override
    public List<MetadataCol> columnInfo(Model model) {
        Datasource datasource = model.getSourceDatasource();
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        JdbcModel jdbcModel =  new JdbcModel(model.getProperties(), model.getSourceDatasource());
        return getColumnInfo(jdbcDatasource, jdbcModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        Datasource datasource = metadata.getDatasource();
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        String fullTbName = metadata.getTbName();
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
        } else if (strs.length == 2) {
            dbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[0];
            tbName = fullTbName.split(DATABASE_AND_TABLE_SEP)[1];
        }
        return getMetadataCols(jdbcDatasource, dbName, tbName);
    }

    private List<MetadataCol> getMetadataCols(JdbcDatasource datasource, String dbName, String tbName) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection(datasource);
            metadataCols = getMetadataCols(conn, dbName, tbName);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return metadataCols;
    }

    private List<MetadataCol> getColumnInfo(JdbcDatasource datasource, JdbcModel model) {
        String dbName = model.getDatabaseName();
        String tbName = model.getTableName();
        String selectSql = model.getSelectSql();
        if (StringUtils.isNotBlank(selectSql)) {
            selectSql = "SELECT * FROM (" + selectSql + ") UDSP_VIEW WHERE 1=0";
            return getMetadataCols(datasource, selectSql);
        }
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            return getMetadataCols(datasource, dbName, tbName);
        }
        return null;
    }

    protected List<MetadataCol> getMetadataCols(JdbcDatasource datasource, String querySql) {
        List<MetadataCol> metadataCols = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection(datasource);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            metadataCols = new ArrayList<>();
            if (columnCount >= 1) {
                for (int i = 1; i <= columnCount; i++) {
                    metadataCols.add(getMetadataCol(md, i));
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(rs);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return metadataCols;
    }

    @Override
    public boolean createSourceEngineSchema(Model model) throws SQLException {
        Datasource sDs = model.getSourceDatasource();
        String sDsId = sDs.getId();
        Datasource eDs = model.getEngineDatasource();
        String eDsId = eDs.getId();
        if (!sDsId.equals(eDsId)) { // 源、引擎的数据源不相同
            HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
            String id = model.getId();
            JdbcModel jdbcModel = new JdbcModel(model);
            String fullTbName = jdbcModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + jdbcModel.getTableName();
            String tableName = getSourceTableName(id);
            JdbcDatasource jdbcDs = new JdbcDatasource(sDs.getPropertyMap());
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getSourceColumns(model.getModelMappings()), "源的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDs, fullTbName));
            return JdbcUtil.createEngineSchema(eHiveDs, HIVE_ENGINE_DATABASE_NAME, sql);
        }
        return true;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection(new JdbcDatasource(datasource.getPropertyMap()));
            if (conn != null && !conn.isClosed()) canConnection = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return canConnection;
    }

    @Override
    public boolean createTargetEngineSchema(Model model) throws SQLException {
        Datasource eDs = model.getEngineDatasource();
        String eDsId = eDs.getId();
        Metadata metadata = model.getTargetMetadata();
        Datasource tDs = metadata.getDatasource();
        String tDsId = tDs.getId();
        if (!tDsId.equals(eDsId)) { // 目标、引擎的数据源不相同
            Datasource datasource = metadata.getDatasource();
            Datasource engineDatasource = model.getEngineDatasource();
            HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
            String id = model.getId();
            HiveMetadata hiveMetadata = new HiveMetadata(metadata);
            String fullTbName = hiveMetadata.getTbName();
            String tableName = getTargetTableName(id);
            JdbcDatasource jdbcDs = new JdbcDatasource(datasource.getPropertyMap());
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getTargetColumns(model.getModelMappings()), "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDs, fullTbName));
            return JdbcUtil.executeUpdate(eHiveDs, sql);
        }
        return true;
    }

    private List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            MetadataCol metadataCol = mapping.getMetadataCol();
            String dataType = getDataType(metadataCol.getType(), metadataCol.getLength());
            columns.add(new TableColumn(metadataCol.getName(), dataType, metadataCol.getDescribe()));
        }
        return columns;
    }

    private List<TableColumn> getSourceColumns(List<ModelMapping> modelMappings) {
        List<TableColumn> columns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            String dataType = getDataType(mapping.getType(), mapping.getLength());
            columns.add(new TableColumn(mapping.getName(), dataType, mapping.getDescribe()));
        }
        return columns;
    }

    private List<TblProperty> getTblProperties(JdbcDatasource datasource, String tableName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("mapred.jdbc.driver.class", datasource.getDriverClass()));
        tblProperties.add(new TblProperty("mapred.jdbc.url", datasource.getJdbcUrl()));
        if (StringUtils.isNotBlank(datasource.getUsername()))
            tblProperties.add(new TblProperty("mapred.jdbc.username", datasource.getUsername()));
        if (StringUtils.isNotBlank(datasource.getPassword()))
            tblProperties.add(new TblProperty("mapred.jdbc.password", datasource.getPassword()));
        tblProperties.add(new TblProperty("mapred.jdbc.input.table.name", tableName));
        tblProperties.add(new TblProperty("mapred.jdbc.output.table.name", tableName));
        tblProperties.add(new TblProperty("mapred.jdbc.hive.lazy.split", "false"));
        return tblProperties;
    }

    protected MetadataCol getMetadataCol(ResultSetMetaData md, int i) throws SQLException {
//        logger.debug("-----------------------------------------------------------");
//        logger.debug("getCatalogName:" + md.getCatalogName(i));
//        logger.debug("getSchemaName:" + md.getSchemaName(i));
//        logger.debug("getTableName:" + md.getTableName(i));
//        logger.debug("getColumnClassName:" + md.getColumnClassName(i));
//        logger.debug("getColumnName:" + md.getColumnName(i));
//        logger.debug("getColumnLabel:" + md.getColumnLabel(i));
//        logger.debug("getColumnDisplaySize:" + md.getColumnDisplaySize(i));
//        logger.debug("getColumnType:" + md.getColumnType(i));
//        logger.debug("getColumnTypeName:" + md.getColumnTypeName(i));
//        logger.debug("getPrecision:" + md.getPrecision(i));
//        logger.debug("getScale:" + md.getScale(i));
        MetadataCol metadataCol = new MetadataCol();
//        String columnName = md.getColumnName(i);
        String columnLabel = md.getColumnLabel(i);
        int columnType = md.getColumnType(i);
        String columnTypeName = md.getColumnTypeName(i);
//        int columnDisplaySize = 0;
//        try {
//            columnDisplaySize = md.getColumnDisplaySize(i);
//        } catch (SQLException e) {
//            columnDisplaySize = 0;
//        }
        int precision = 0;
        try {
            precision = md.getPrecision(i);
        } catch (SQLException e) {
            precision = 0;
        }
        int scale = 0;
        try {
            scale = md.getScale(i);
        } catch (SQLException e) {
            scale = 0;
        }
        if (columnLabel.contains(".")) {
            columnLabel = columnLabel.split("\\.")[1];
        }
        String colLength = "";
        if (scale == 0 && precision > 0) {
            colLength = String.valueOf(precision);
        } else if (scale > 0 && precision > 0 && scale <= precision) {
            colLength = String.valueOf(precision) + "," + String.valueOf(scale);
        }
        metadataCol.setSeq((short) i);
        metadataCol.setName(columnLabel);
        metadataCol.setType(getColType(columnTypeName));
        metadataCol.setLength(colLength);
        return metadataCol;
    }

    protected List<MetadataCol> getMetadataCols(Connection conn, String dbName, String tbName) throws SQLException {
        List<MetadataCol> metadataCols = null;
        List<Column> columns = getColumns(conn, dbName, tbName);
        metadataCols = new ArrayList<>();
        MetadataCol mdCol = null;
        for (Column col : columns) {
            mdCol = new MetadataCol();
            mdCol.setSeq((short) col.getSeq());
            mdCol.setName(col.getName());
            mdCol.setDescribe(col.getComment());
            mdCol.setType(getColType(col.getType()));
            mdCol.setLength(col.getLength());
            mdCol.setPrimary(col.getPrimaryKeyN() > 0 ? true : false);
            mdCol.setIndexed(col.getPrimaryKeyN() > 0 ? true : false);
            mdCol.setStored(true);
            metadataCols.add(mdCol);
        }
        return metadataCols;
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
    public boolean checkSchemaExists(Metadata metadata) throws SQLException {
        JdbcDatasource datasource = new JdbcDatasource(metadata.getDatasource().getPropertyMap());
        String tbName = metadata.getTbName();
        String sql = "select 1 from " + tbName;
        Connection conn = null;
        Statement stmt = null;
        boolean exists = true;
        try {
            conn = JdbcUtil.getConnection(datasource);
            stmt = conn.createStatement();
            stmt.executeQuery(sql);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getMessage().indexOf("doesn't exist") != -1
                    || e.getMessage().indexOf("ORA-00942") != -1
                    || e.getMessage().indexOf("Table not found") != -1) {
                exists = false;
            }
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return exists;
    }

    protected abstract DataType getColType(String type);

    protected abstract List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException;


}
