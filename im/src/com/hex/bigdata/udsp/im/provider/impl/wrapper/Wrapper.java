package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.constant.BuildMode;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.constant.UpdateMode;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.MysqlDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.HiveModel;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.KafkaModel;
import com.hex.bigdata.udsp.im.provider.impl.util.*;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.model.*;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by JunjieM on 2017-9-11.
 */
public abstract class Wrapper {
    private static Logger logger = LogManager.getLogger(Wrapper.class);

    protected static final String DATABASE_AND_TABLE_SEP = "\\.";
    protected static final String HIVE_ENGINE_DATABASE_NAME = "UDSP";
    protected static final String HIVE_ENGINE_TABLE_SEP = "_";
    protected static final String HIVE_ENGINE_SOURCE_TABLE_PREFIX = HIVE_ENGINE_DATABASE_NAME
            + ".E" + HIVE_ENGINE_TABLE_SEP + "S" + HIVE_ENGINE_TABLE_SEP;
    protected static final String HIVE_ENGINE_TARGET_TABLE_PREFIX = HIVE_ENGINE_DATABASE_NAME
            + ".E" + HIVE_ENGINE_TABLE_SEP + "T" + HIVE_ENGINE_TABLE_SEP;

    protected String getHiveEngineSourceTablePrefix(DataType type) {
        return HIVE_ENGINE_SOURCE_TABLE_PREFIX + type.getValue() + HIVE_ENGINE_TABLE_SEP;
    }

    protected String getHiveEngineTargetTablePrefix(DataType type) {
        return HIVE_ENGINE_TARGET_TABLE_PREFIX + type.getValue() + HIVE_ENGINE_TABLE_SEP;
    }

    protected String getSourceTableName(String id) {
        return HIVE_ENGINE_SOURCE_TABLE_PREFIX + id;
    }

    protected String getTargetTableName(String id) {
        return HIVE_ENGINE_TARGET_TABLE_PREFIX + id;
    }

    protected String getDataType(DataType type, String length) {
        String dataType = DataType.STRING.getValue();
        if (StringUtils.isBlank(length)) {
            dataType = type.getValue();
        } else {
            if (DataType.STRING == type || DataType.INT == type || DataType.SMALLINT == type
                    || DataType.BIGINT == type || DataType.BOOLEAN == type || DataType.DOUBLE == type
                    || DataType.FLOAT == type || DataType.TINYINT == type || DataType.TIMESTAMP == type) {
                dataType = type.getValue();
            } else if (DataType.CHAR == type || DataType.VARCHAR == type || DataType.DECIMAL == type) {
                dataType = type.getValue() + "(" + length + ")";
            }
        }
        return dataType;
    }

    /**
     * 删除源引擎Schema
     *
     * @param model
     * @return
     * @throws SQLException
     */
    public boolean dropSourceEngineSchema(Model model) throws SQLException {
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        String tableName = getSourceTableName(id);
        String sql = HiveSqlUtil.dropTable(true, tableName);
        return JdbcUtil.executeUpdate(eHiveDs, sql);
    }

    /**
     * 批量构建
     *
     * @param key
     * @param model
     * @throws SQLException
     */
    public void buildBatch(String key, Model model) throws SQLException {
        String id = model.getId();
        Metadata metadata = model.getTargetMetadata();
        String fullTbName = metadata.getTbName();
        List<ModelMapping> modelMappings = model.getModelMappings();
        boolean isOverwrite = (BuildMode.INSERT_OVERWRITE == model.getBuildMode() ? true : false);
        Datasource sDs = model.getSourceDatasource();
        String sDsId = sDs.getId();
        Datasource eDs = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(eDs.getPropertyMap());
        String eDsId = eDs.getId();
        Datasource tDs = metadata.getDatasource();
        String tDsId = tDs.getId();

        String selectSql = null;
        String selectTableName = getSourceTableName(id);
        String insertTableName = getTargetTableName(id);
        if (sDsId.equals(eDsId) && tDsId.equals(eDsId)) { // 源、引擎、目标的数据源相同
            HiveModel hiveModel = new HiveModel(model);
            selectSql = hiveModel.getSelectSql();
            selectTableName = hiveModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + hiveModel.getTableName();
            insertTableName = fullTbName;
        } else if (sDsId.equals(eDsId)) { // 源、引擎的数据源相同
            HiveModel hiveModel = new HiveModel(model);
            selectSql = hiveModel.getSelectSql();
            selectTableName = hiveModel.getDatabaseName() + DATABASE_AND_TABLE_SEP + hiveModel.getTableName();
            insertTableName = getTargetTableName(id);
        } else if (tDsId.equals(eDsId)) { // 目标、引擎的数据源相同
            selectTableName = getSourceTableName(id);
            insertTableName = fullTbName;
        }

        insert(key, eHiveDs, metadata, modelMappings, isOverwrite, selectSql, selectTableName, insertTableName, model.getModelFilterCols());
    }

    protected void insert(String key, HiveDatasource eHiveDs, Metadata metadata, List<ModelMapping> modelMappings, boolean isOverwrite, String selectSql, String selectTableName, String insertHBaseTableName, List<ModelFilterCol> modelFilterCols) throws SQLException {
        String insertSql = null;
        if (StringUtils.isNotBlank(selectSql)) {
            for (ModelMapping mapping : modelMappings) mapping.setName("UDSP_VIEW." + mapping.getName());
            insertSql = HiveSqlUtil.insert2(isOverwrite, insertHBaseTableName, getInsertColumns(modelMappings, metadata), null,
                    getSelectColumns(modelMappings, metadata), selectSql, getWhereProperties(modelFilterCols));
        } else {
            insertSql = HiveSqlUtil.insert(isOverwrite, insertHBaseTableName, getInsertColumns(modelMappings, metadata), null,
                    getSelectColumns(modelMappings, metadata), selectTableName, getWhereProperties(modelFilterCols));
        }
        HiveJdbcUtil.executeUpdate(key, eHiveDs, insertSql);
    }

    /**
     * 删除目标引擎Schema
     *
     * @param model
     * @return
     * @throws SQLException
     */
    public boolean dropTargetEngineSchema(Model model) throws SQLException {
        Datasource engineDatasource = model.getEngineDatasource();
        HiveDatasource eHiveDs = new HiveDatasource(engineDatasource.getPropertyMap());
        String id = model.getId();
        String tableName = getTargetTableName(id);
        String sql = HiveSqlUtil.dropTable(true, tableName);
        return JdbcUtil.executeUpdate(eHiveDs, sql);
    }

    protected List<WhereProperty> getWhereProperties(List<ModelFilterCol> modelFilterCols) {
        if (modelFilterCols == null || modelFilterCols.size() == 0)
            return null;
        List<WhereProperty> whereProperties = new ArrayList<>();
        for (ModelFilterCol filterCol : modelFilterCols) {
            WhereProperty whereProperty = new WhereProperty();
            whereProperty.setName(filterCol.getName());
            whereProperty.setValue(filterCol.getValue());
            whereProperty.setType(filterCol.getType());
            whereProperty.setOperator(filterCol.getOperator());
        }
        return whereProperties;
    }

    protected DataType javaTransDBType(Object value) {
        DataType type = DataType.STRING;
        if (value instanceof Integer) {
            type = DataType.INT;
        } else if (value instanceof BigDecimal) {
            type = DataType.DECIMAL;
        } else if (value instanceof Long) {
            type = DataType.BIGINT;
        } else if (value instanceof BigInteger) {
            type = DataType.BIGINT;
        } else if (value instanceof Double) {
            type = DataType.DOUBLE;
        } else if (value instanceof Float) {
            type = DataType.FLOAT;
        } else if (value instanceof Short) {
            type = DataType.SMALLINT;
        } else if (value instanceof Boolean) {
            type = DataType.BOOLEAN;
        } else if (value instanceof Timestamp) {
            type = DataType.TIMESTAMP;
        }
        return type;
    }

    protected List<WhereProperty> updateKeysToWhereProperties(List<MetadataCol> updateKeys) {
        List<WhereProperty> whereProperties = null;
        if (updateKeys != null && updateKeys.size() != 0) {
            for (MetadataCol metadataCol : updateKeys) {
                WhereProperty property = new WhereProperty();
                property.setName(metadataCol.getName());
                property.setOperator(Operator.EQ);
                property.setType(metadataCol.getType());
                property.setValue(metadataCol.getValue());
                whereProperties.add(property);
            }
        }
        return whereProperties;
    }

    /**
     * 实时构建
     *
     * @param model
     */
    public void buildRealtime(Model model) {
        String sDsType = model.getSourceDatasource().getType();
        UpdateMode updateMode = model.getUpdateMode();
        List<ModelMapping> modelMappings = model.getModelMappings();
        List<ModelFilterCol> modelFilterCols = model.getModelFilterCols();
        List<MetadataCol> updateKeys = model.getUpdateKeys();
        List<WhereProperty> whereProperties = updateKeysToWhereProperties(updateKeys);
        Metadata metadata = model.getTargetMetadata();
        Datasource datasource = metadata.getDatasource();
        String tableName = metadata.getTbName();
        // 源是Kafka
        if (DatasourceType.KAFKA.getValue().equals(sDsType)) {
            KafkaModel kafkaModel = new KafkaModel(model);
            List<KafkaStream<byte[], byte[]>> streams = KafkaUtil.outputData(kafkaModel);
            for (KafkaStream<byte[], byte[]> stream : streams) {
                ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
                while (iterator.hasNext()) {
                    String message = new String(iterator.next().message());
                    logger.debug("kafka接收的信息为：" + message);
                    try {
                        // 实时数据过滤
                        List<ValueColumn> valueColumns = new ArrayList<>();
                        Map<String, Object> map = JSONUtil.parseJSON2Map(message);
                        for (ModelMapping mapping : modelMappings) {
                            // 过滤值
                            DataType type = mapping.getType();
                            String name = mapping.getName();
                            Object v = map.get(mapping.getName());
                            String value = (v == null ? "" : (String) v);
                            // 过滤行
                            for (ModelFilterCol filterCol : modelFilterCols) {
                                if (filterCol.getName().equals(name)) {
                                    Operator operator = filterCol.getOperator();
                                    String val = filterCol.getValue();
                                    if (StringUtils.isNotBlank(val)) {
                                        if (Operator.EQ.equals(operator) && !val.equals(value)) {
                                            break;
                                        } else if (Operator.NE.equals(operator) && val.equals(value)) {
                                            break;
                                        } else if (Operator.GT.equals(operator) && val.compareTo(value) >= 0) {
                                            break;
                                        } else if (Operator.GE.equals(operator) && val.compareTo(value) > 0) {
                                            break;
                                        } else if (Operator.LT.equals(operator) && val.compareTo(value) <= 0) {
                                            break;
                                        } else if (Operator.LE.equals(operator) && val.compareTo(value) < 0) {
                                            break;
                                        } else if (Operator.LK.equals(operator) && !value.contains(val)) {
                                            break;
                                        } else if (Operator.RLIKE.equals(operator) && !value.startsWith(val)) {
                                            break;
                                        } else if (Operator.IN.equals(operator)) {
                                            String[] vs = val.split(",");
                                            if (!Arrays.asList(vs).contains(value)) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            // 封装
                            ValueColumn column = new ValueColumn();
                            column.setColName(name);
                            column.setValue(value);
                            column.setDataType(type);
                            valueColumns.add(column);
                        }
                        // 实时数据处理
                        if (UpdateMode.MATCHING_UPDATE == updateMode) { // 匹配更新
                            matchingUpdate(metadata, modelMappings, valueColumns, whereProperties);
                        } else if (UpdateMode.UPDATE_INSERT == updateMode) { // 更新插入
                            updateInsert(metadata, modelMappings, valueColumns, whereProperties);
                        } else { // 增量插入
                            insertInto(metadata, modelMappings, valueColumns);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 增量插入
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @throws Exception
     */
    protected abstract void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception;

    /**
     * 更新、插入
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @param whereProperties
     * @throws Exception
     */
    protected abstract void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception;

    /**
     * 匹配更新
     *
     * @param metadata
     * @param modelMappings
     * @param valueColumns
     * @param whereProperties
     * @throws Exception
     */
    protected abstract void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception;

    /**
     * 获取查询字段集合
     *
     * @param modelMappings
     * @param metadata
     * @return
     */
    protected abstract List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata);

    /**
     * 获取插入字段集合
     *
     * @param modelMappings
     * @param metadata
     * @return
     */
    protected abstract List<String> getInsertColumns(List<ModelMapping> modelMappings, Metadata metadata);
}
