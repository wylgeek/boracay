package com.hex.bigdata.udsp.ed.connect.sql.impala;

import com.hex.bigdata.udsp.ed.connect.sql.CommonSql;
import com.hex.bigdata.udsp.ed.connect.sql.model.KeyAndValue;
import com.hex.bigdata.udsp.ed.connect.sql.model.RowFormat;
import com.hex.bigdata.udsp.ed.connect.sql.model.TableColumn;
import com.hex.bigdata.udsp.ed.connect.sql.model.TblProperties;

import java.util.List;

/**
 * 基于CDH5.3.0 Impala V2.1.0编写 ，以后版本的新SQL语句将会进行标注。
 *
 * @author junjiem
 */
public class ImpalaSql extends CommonSql {
    /**
     * 添加单字段分区
     *
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String addPartition(String tableName, String colName,
                                      String colValue, String dataType) {
        return "ALTER TABLE " + tableName + " ADD PARTITION (" + colName
                + "=CAST('" + colValue + "' AS " + dataType + "))";
    }

    /**
     * 添加分区
     *
     * @param tableName
     * @param partitions
     * @return
     */
    public static String addPartition(String tableName,
                                      List<KeyAndValue> partitions) {
        return "ALTER TABLE " + tableName + " ADD"
                + getPartitionKeyAndVaule(partitions);
    }

    /**
     * 删除单字段分区
     *
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String dropPartition(String tableName, String colName,
                                       boolean ifExists, String colValue, String dataType) {
        return "ALTER TABLE " + tableName + " DROP " + getIfExists(ifExists)
                + " PARTITION  (" + colName + "=CAST('" + colValue + "' AS "
                + dataType + "))";
    }

    /**
     * 删除分区
     *
     * @param tableName
     * @param partitions
     * @return
     */
    public static String dropPartition(String tableName,
                                       List<KeyAndValue> partitions) {
        return "ALTER TABLE " + tableName + " DROP"
                + getPartitionKeyAndVaule(partitions);
    }

    /**
     * 单表单字段分区的动态生成分区插入
     * <p>
     * 注：两张表字段是一致的，且都有单个相同名称的日期分区字段
     *
     * @param isOverwrite     是否覆盖
     * @param insertTableName 目标表名
     * @param selectTableName 源表名
     * @param dateFiledName   单个分区字段名
     * @param dateStr         日期字符串
     * @return
     */
    public static String InsertSingTrendsPartitionTable(boolean isOverwrite,
                                                        String insertTableName, String selectTableName,
                                                        String dateFiledName, String dateStr) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + " PARTITION(" + dateFiledName
                + ") SELECT * FROM " + selectTableName + " WHERE "
                + dateFiledName + "='" + dateStr + "'";
    }

    /**
     * 单表单字段分区的静态指定分区插入
     * <p>
     * 注：两张表字段可以不一致，但都有单个相同名称的日期分区字段
     *
     * @param isOverwrite     是否覆盖
     * @param insertTableName 目标表名
     * @param insertColumns   目标表字段集
     * @param selectTableName 源表名
     * @param selectColumns   源表字段集
     * @param dateFiledName   单个分区字段名
     * @param dateStr         日期字符串
     * @return
     */
    public static String InsertSingStaticPartitionTable(boolean isOverwrite,
                                                        String insertTableName, List<String> insertColumns,
                                                        String selectTableName, List<String> selectColumns,
                                                        String dateFiledName, String dateStr) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + " " + getInsertColumns(insertColumns)
                + " PARTITION(" + dateFiledName + "='" + dateStr + "') SELECT "
                + getSelectColumns(selectColumns) + " FROM " + selectTableName
                + " WHERE " + dateFiledName + "='" + dateStr + "'";
    }

    /**
     * 全量统计
     *
     * @param tableName
     * @return
     */
    public static String computeStats(String tableName) {
        return "COMPUTE STATS " + tableName;
    }

    /**
     * 增量统计
     *
     * @param tableName
     * @param partitions
     * @return
     */
    public static String computeStats(String tableName,
                                      List<KeyAndValue> partitions) {
        return "COMPUTE INCREMENTAL STATS " + tableName
                + getPartitionKeyAndVaule(partitions);
    }

    /**
     * 显示表统计信息
     *
     * @param tableName
     * @return
     */
    public static String showTableStats(String tableName) {
        return "SHOW TABLE STATS " + tableName;
    }

    /**
     * 显示字段统计信息
     *
     * @param tableName
     * @return
     */
    public static String showColumnStats(String tableName) {
        return "SHOW COLUMN STATS " + tableName;
    }

    /**
     * 刷新表
     *
     * @param tableName
     * @return
     */
    public static String refreshTable(String tableName) {
        return "REFRESH " + tableName;
    }

    /**
     * 分页查询
     *
     * @param tableName
     * @param orders
     * @param limit
     * @param offset
     * @return
     */
    public static String queryTableLimitOffset(String tableName,
                                               List<String> orders, int limit, int offset) {
        return "SELECT * FROM " + tableName + getOrders(orders) + " LIMIT "
                + limit + " OFFSET " + offset;
    }

    /**
     * 分页查询指定字段
     *
     * @param tableName
     * @param orders
     * @param limit
     * @param offset
     * @return
     */
    public static String queryTableLimitOffset(String tableName,
                                               List<String> orders, int limit, int offset, List<String> columns) {
        return "SELECT " + getSelectColumns(columns) + " FROM " + tableName
                + getOrders(orders) + " LIMIT " + limit + " OFFSET " + offset;
    }

    /**
     * 拷贝表结构创建新表，并指定数据格式
     *
     * @return
     */
    public static String createLikeTable(boolean ifNotExists,
                                         String oldTableName, String newTableName, String fileFormat) {
        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " "
                + newTableName + " LIKE " + oldTableName + " STORED AS "
                + fileFormat;
    }

    /**
     * 完整版建表
     *
     * @param isExternal
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param serDeProperties
     * @param rowFormat
     * @param fileFormat
     * @param location
     * @param tblProperties
     * @param cachedPoolName
     * @return
     */
    public static String createTable(boolean isExternal, boolean ifNotExists,
                                     String tableName, List<TableColumn> columns, String tableComment,
                                     List<TableColumn> partitions, RowFormat rowFormat,
                                     String fileFormat, String location, String cachedPoolName,
                                     List<KeyAndValue> serDeProperties, List<TblProperties> tblProperties) {
        return "CREATE" + getExternal(isExternal) + " TABLE"
                + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getPartitions(partitions)
                + getTableComment(tableComment) + getRowFormat(rowFormat)
                + getSerDeProperties(serDeProperties)
                + getFileFormat(fileFormat) + getLocation(location)
                + getCached(cachedPoolName) + getTblProperties(tblProperties);
    }

    /**
     * 相对完整版建表（最常用版本）
     *
     * @param isExternal
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param rowFormat
     * @param fileFormat
     * @param location
     * @param cachedPoolName
     * @return
     */
    public static String createTable(boolean isExternal, String tableName,
                                     List<TableColumn> columns, String tableComment,
                                     List<TableColumn> partitions, RowFormat rowFormat,
                                     String fileFormat, String location, String cachedPoolName) {
        return "CREATE" + getExternal(isExternal) + " TABLE " + tableName
                + getColumns(columns) + getPartitions(partitions)
                + getTableComment(tableComment) + getRowFormat(rowFormat)
                + getFileFormat(fileFormat) + getLocation(location)
                + getCached(cachedPoolName);
    }

    /**
     * 从HDFS中加载数据到表
     *
     * @param hdfsFileOrDirPath
     * @param isOverwrite
     * @param tableName
     * @param partitions
     * @return
     */
    public static String loadData(String hdfsFileOrDirPath,
                                  boolean isOverwrite, String tableName, List<KeyAndValue> partitions) {
        return "LOAD DATA INPATH '" + hdfsFileOrDirPath + "'"
                + getOverwrite(isOverwrite) + " INTO TABLE " + tableName
                + getPartitionKeyAndVaule(partitions);
    }

    /**
     * 表改名
     *
     * @param oldTableName
     * @param newTableName
     * @return
     */
    public static String rename(String oldTableName, String newTableName) {
        return "ALTER TABLE " + oldTableName + " RENAME TO " + newTableName;
    }

    /**
     * 添加字段
     *
     * @param tableName
     * @param columnName
     * @param columnType
     * @return
     */
    public static String addOneColumn(String tableName, String columnName,
                                      String columnType) {
        return "ALTER TABLE " + tableName + " ADD COLUMNS (" + columnName + " "
                + columnType + ")";
    }

    /**
     * 添加字段
     *
     * @param tableName
     * @param columns
     * @return
     */
    public static String addColumns(String tableName, List<KeyAndValue> columns) {
        return "ALTER TABLE " + tableName + " ADD COLUMNS"
                + getColumns2(columns);
    }

    /**
     * 删除字段
     *
     * @param tableName
     * @param columnName
     * @return
     */
    public static String dropColumn(String tableName, String columnName) {
        return "ALTER TABLE " + tableName + " DROP COLUMN " + columnName;
    }

    /**
     * 改变字段
     *
     * @param tableName
     * @param oldColumnName
     * @param newColumnName
     * @param newColumnType
     * @return
     */
    public static String changeColumn(String tableName, String oldColumnName,
                                      String newColumnName, String newColumnType) {
        return "ALTER TABLE " + tableName + " CHANGE " + oldColumnName + " "
                + newColumnName + " " + newColumnType;
    }

    /**
     * 替换字段
     *
     * @param tableName
     * @param columnName
     * @param columnType
     * @return
     */
    public static String replaceOneColumns(String tableName, String columnName,
                                           String columnType) {
        return "ALTER TABLE " + tableName + " REPLACE COLUMNS (" + columnName
                + " " + columnType + ")";
    }

    /**
     * 替换字段
     *
     * @param tableName
     * @param columns
     * @return
     */
    public static String replaceColumns(String tableName,
                                        List<KeyAndValue> columns) {
        return "ALTER TABLE " + tableName + " REPLACE COLUMNS"
                + getColumns2(columns);
    }

    /**
     * 获取SQL执行信息(极少)
     *
     * @param sql
     * @return
     */
    public static String explainOfMinimal(String sql) {
        return "SET EXPLAIN_LEVEL=MINIMAL; EXPLAIN " + sql;
    }

    /**
     * 获取SQL执行信息(标准，默认)
     *
     * @param sql
     * @return
     */
    public static String explainOfStandard(String sql) {
        return "SET EXPLAIN_LEVEL=STANDARD; EXPLAIN " + sql;
    }

    /**
     * 获取SQL执行信息(扩展)
     *
     * @param sql
     * @return
     */
    public static String explainOfExtended(String sql) {
        return "SET EXPLAIN_LEVEL=EXTENDED; EXPLAIN " + sql;
    }

    /**
     * 获取SQL执行信息(冗长)
     *
     * @param sql
     * @return
     */
    public static String explainOfVerbose(String sql) {
        return "SET EXPLAIN_LEVEL=VERBOSE; EXPLAIN " + sql;
    }

    /**
     * 将所有元数据标记为无效
     *
     * @return
     */
    public static String invalidateMetadata() {
        return "INVALIDATE METADATA";
    }

    /**
     * 将指定表元数据标记为无效
     *
     * @param tableName
     * @return
     */
    public static String invalidateMetadata(String tableName) {
        return "INVALIDATE METADATA " + tableName;
    }

    /**
     * 刷新指定表元数据
     *
     * @param tableName
     * @return
     */
    public static String refreshMetadata(String tableName) {
        return "REFRESH " + tableName;
    }

    private static String getCached(String cachedPoolName) {
        String sql = "";
        if (cachedPoolName != null && !cachedPoolName.trim().equals("")) {
            sql = " CACHED IN '" + cachedPoolName + "'";
        }
        return sql;
    }

    // private static String getPartitionKeyAndVaule(
    // List<TablePartition> partitions) {
    // String sql = "";
    // TablePartition partition = null;
    // String colName = "";
    // String colValue = "";
    // String dataType = "";
    // if (partitions != null && partitions.size() != 0) {
    // sql = " PARTITION (";
    // for (int i = 0; i < partitions.size(); i++) {
    // partition = partitions.get(i);
    // colName = partition.getColName();
    // colValue = partition.getColValue();
    // dataType = partition.getDataType();
    // if (colName != null && !colName.trim().equals("")
    // && colValue != null && dataType != null
    // && !dataType.trim().equals("")) {
    // if (i == 0) {
    // sql += colName + "=CAST('" + colValue + "' AS "
    // + dataType + ")";
    // } else {
    // sql += ", " + colName + "=CAST('" + colValue + "' AS "
    // + dataType + ")";
    // }
    // }
    // }
    // sql += ")";
    // }
    // return sql;
    // }

}
