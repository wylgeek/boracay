package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.provider.BatchSourceProvider;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HiveMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.JdbcMetadata;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.JdbcModel;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveSqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.JdbcUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.SqlUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TableColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.TblProperty;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
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
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource);
        JdbcModel jdbcModel = new JdbcModel(model.getProperties(), model.getSourceDatasource());
        return getColumnInfo(jdbcDatasource, jdbcModel);
    }

    @Override
    public List<MetadataCol> columnInfo(Metadata metadata) {
        JdbcDatasource jdbcDatasource = new JdbcDatasource(metadata.getDatasource());
        String fullTbName = metadata.getTbName();
        String[] strs = fullTbName.split(DATABASE_AND_TABLE_SEP_TRANS);
        String dbName = null;
        String tbName = null;
        if (strs.length == 1) {
            tbName = strs[0];
        } else if (strs.length == 2) {
            dbName = strs[0];
            tbName = strs[1];
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
            logger.warn(ExceptionUtil.getMessage(e));
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
        if (StringUtils.isNotBlank(tbName)) {
            // Oracle比较特殊，dbName实际上是Oracle用户名称（大写），tbName表名（大写）。
            if (DatasourceType.ORACLE.getValue().equals(datasource.getType())) {
                dbName = datasource.getUsername().toUpperCase();
                tbName = tbName.toUpperCase();
            }
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
            logger.warn(ExceptionUtil.getMessage(e));
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(rs);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return metadataCols;
    }

    @Override
    public void createSourceEngineSchema(Model model) throws Exception {
        Datasource sDs = model.getSourceDatasource();
        String sDsId = sDs.getId();
        Datasource eDs = model.getEngineDatasource();
        String eDsId = eDs.getId();
        if (!sDsId.equals(eDsId)) { // 源、引擎的数据源不相同

            // 查询表名
            String tableName = null;
            JdbcModel jdbcModel = new JdbcModel(model);
            String dbName = jdbcModel.getDatabaseName();
            String tbName = jdbcModel.getTableName();
            String selectSql = jdbcModel.getSelectSql();
            if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
                tableName = dbName + DATABASE_AND_TABLE_SEP + tbName;
            } else if (StringUtils.isBlank(dbName) && StringUtils.isNotBlank(tbName)) {
                tableName = tbName;
            } else if (StringUtils.isBlank(tbName) && StringUtils.isBlank(selectSql)) {
                throw new Exception("配置参数table.name和select.sql不能都为空");
            }

            // 查询SQL
            String inputQuery = null;
            if (StringUtils.isNotBlank(selectSql)) {
                inputQuery = selectSql;
            }

            // 引擎表名
            String engineSchemaName = getSourceTableName(model.getId());

            createSourceEngineSchema(model, engineSchemaName, tableName, inputQuery);
        }
    }

    @Override
    public void createSourceEngineSchema(Model model, String engineSchemaName) throws Exception {
        // 查询表名
        String tableName = null;
        JdbcModel jdbcModel = new JdbcModel(model);
        String dbName = jdbcModel.getDatabaseName();
        String tbName = jdbcModel.getTableName();
        String selectSql = jdbcModel.getSelectSql();
        if (StringUtils.isNotBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = dbName + DATABASE_AND_TABLE_SEP + tbName;
        } else if (StringUtils.isBlank(dbName) && StringUtils.isNotBlank(tbName)) {
            tableName = tbName;
        } else if (StringUtils.isBlank(tbName) && StringUtils.isBlank(selectSql)) {
            throw new Exception("配置参数table.name和select.sql不能都为空");
        }

        // 动态SQL
        String inputQuery = null;
        Metadata metadata = model.getTargetMetadata();
        List<ModelMapping> modelMappings = model.getModelMappings();
        List<String> selectColumns = getSelectColumns(modelMappings, metadata);
        List<WhereProperty> whereProperties = getWhereProperties(model.getModelFilterCols());
        if (StringUtils.isNotBlank(selectSql)) {
            inputQuery = SqlUtil.select2(selectColumns, selectSql, whereProperties);
        } else {
            inputQuery = SqlUtil.select(selectColumns, tableName, whereProperties);
        }

        createSourceEngineSchema(model, engineSchemaName, tableName, inputQuery);
    }

    private void createSourceEngineSchema(Model model, String engineSchemaName, String tableName, String inputQuery) throws Exception {
        Datasource sDs = model.getSourceDatasource();
        JdbcDatasource jdbcDatasource = new JdbcDatasource(sDs);
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource hiveDatasource = new HiveDatasource(eDs);
        String sql = HiveSqlUtil.createStorageHandlerTable(true, true, engineSchemaName,
                getSourceColumns(model.getModelMappings()), "源的Hive引擎表", null,
                HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDatasource, tableName, inputQuery));
        JdbcUtil.createEngineSchema(hiveDatasource, HIVE_ENGINE_DATABASE_NAME, sql);
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection(new JdbcDatasource(datasource));
            if (conn != null && !conn.isClosed()) canConnection = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return canConnection;
    }

    @Override
    public void createTargetEngineSchema(Model model) throws Exception {
        Datasource eDs = model.getEngineDatasource();
        String eDsId = eDs.getId();
        Metadata metadata = model.getTargetMetadata();
        Datasource tDs = metadata.getDatasource();
        String tDsId = tDs.getId();
        String tDsType = tDs.getType();
        if (!tDsId.equals(eDsId)) { // 目标、引擎的数据源不相同
            if (DatasourceType.HIVE.getValue().equals(tDsType)) { // 目标是Hive数据源
                /*
                可以把数据插入目标，但是有编码问题还会有碎文件的问题。
                 */
                throw new Exception("目标是HIVE类型时必须与引擎是同一个数据源");
            }
            HiveDatasource eHiveDs = new HiveDatasource(model.getEngineDatasource());
            String id = model.getId();
            HiveMetadata hiveMetadata = new HiveMetadata(metadata);
            String fullTbName = hiveMetadata.getTbName();
            String tableName = getTargetTableName(id);
            JdbcDatasource jdbcDs = new JdbcDatasource(metadata.getDatasource());
            String sql = HiveSqlUtil.createStorageHandlerTable(true, true, tableName,
                    getTargetColumns(model.getModelMappings()), "目标的Hive引擎表", null,
                    HIVE_ENGINE_STORAGE_HANDLER_CLASS, null, getTblProperties(jdbcDs, fullTbName, null));
            JdbcUtil.executeUpdate(eHiveDs, sql);
        }
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

    private List<TblProperty> getTblProperties(JdbcDatasource datasource, String tableName, String inputQuery) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("mapred.jdbc.driver.class", datasource.getDriverClass()));
        tblProperties.add(new TblProperty("mapred.jdbc.url", datasource.getJdbcUrl()));
        if (StringUtils.isNotBlank(datasource.getUsername()))
            tblProperties.add(new TblProperty("mapred.jdbc.username", datasource.getUsername()));
        if (StringUtils.isNotBlank(datasource.getPassword()))
            tblProperties.add(new TblProperty("mapred.jdbc.password", datasource.getPassword()));
        if (StringUtils.isNotBlank(tableName))
            tblProperties.add(new TblProperty("mapred.jdbc.input.table.name", tableName));
        if (StringUtils.isNotBlank(inputQuery)) {
            inputQuery = inputQuery.replaceAll("'", "\\\\'");
            tblProperties.add(new TblProperty("mapred.jdbc.input.query", inputQuery));
        }
        if (StringUtils.isNotBlank(tableName))
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
        List<String> selectColumns = new ArrayList<>();
        for (ModelMapping mapping : modelMappings)
            selectColumns.add(mapping.getName());
        return selectColumns;
    }

    @Override
    public boolean checkSchema(Metadata metadata) throws SQLException {
        JdbcDatasource datasource = new JdbcDatasource(metadata.getDatasource());
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
            logger.error(ExceptionUtil.getMessage(e));
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

    @Override
    protected void emptyDatas(Metadata metadata) throws Exception {
        JdbcDatasource jdbcDatasource = new JdbcDatasource(metadata.getDatasource());
        JdbcMetadata jdbcMetadata = new JdbcMetadata(metadata);
        String tableName = jdbcMetadata.getTbName();
        String updateSql = SqlUtil.truncateTable(tableName);
        JdbcUtil.executeUpdate(jdbcDatasource, updateSql);
    }

    protected abstract DataType getColType(String type);

    protected abstract List<Column> getColumns(Connection conn, String dbName, String tbName) throws SQLException;


}
