package com.hex.bigdata.udsp.ed.connect.sql;

import com.hex.bigdata.udsp.ed.connect.sql.model.KeyAndValue;
import com.hex.bigdata.udsp.ed.connect.sql.model.RowFormat;
import com.hex.bigdata.udsp.ed.connect.sql.model.TableColumn;
import com.hex.bigdata.udsp.ed.connect.sql.model.TblProperties;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class CommonSql {
    /**
     * 查看当前环境参数值
     *
     * @return
     */
    public static String set() {
        return "SET";
    }

    /**
     * 获取SQL执行情况详细信息
     *
     * @param sql
     * @return
     */
    public static String explain(String sql) {
        return "EXPLAIN " + sql;
    }

    /**
     * 显示表的分区情况
     *
     * @param tableName
     * @return
     */
    public static String showPartitions(String tableName) {
        return "SHOW PARTITIONS " + tableName;
    }

    /**
     * 显示表的建表语句
     *
     * @param tableName
     * @return
     */
    public static String showCreateTable(String tableName) {
        return "SHOW CREATE TABLE " + tableName;
    }

    /**
     * 显示default数据库的表列表
     *
     * @return
     */
    public static String showTables() {
        return "SHOW TABLES";
    }

    /**
     * 显示指定数据库的表列表
     *
     * @param databaseName
     * @return
     */
    public static String showTables(String databaseName) {
        return "SHOW TABLES IN " + databaseName;
    }

    /**
     * 显示default数据库，满足匹配要求的列表
     *
     * @param pattern
     * @return
     */
    public static String showTablesLike(String pattern) {
        return "SHOW TABLES LIKE '" + pattern + "'";
    }

    /**
     * 显示指定数据库，满足匹配要求的列表
     *
     * @param databaseName
     * @param pattern
     * @return
     */
    public static String showTablesLike(String databaseName, String pattern) {
        return "SHOW TABLES IN " + databaseName + " LIKE '" + pattern + "'";
    }

    /**
     * 显示数据库列表
     *
     * @return
     */
    public static String showDatabases() {
        return "SHOW DATABASES";
    }

    /**
     * 查询表所有数据
     *
     * @param tableName
     * @return
     */
    public static String queryTableAllDatas(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    /**
     * 查询表Top N
     *
     * @param tableName
     * @param limit
     * @return
     */
    public static String queryTableLimitN(String tableName, int limit) {
        return "SELECT * FROM " + tableName + " LIMIT " + limit;
    }

    /**
     * 查询表记录数
     *
     * @param tableName
     * @return
     */
    public static String queryTableDataCount(String tableName) {
        return "SELECT COUNT(1) FROM " + tableName;
    }

    /**
     * 显示表简易信息
     *
     * @param tableName
     * @return
     */
    public static String descTable(String tableName) {
        return "DESCRIBE " + tableName;
    }

    /**
     * 显示表详细信息
     *
     * @param tableName
     * @return
     */
    public static String descFormatTable(String tableName) {
        return "DESCRIBE FORMATTED " + tableName;
    }

    /**
     * 创建数据库
     *
     * @param databaseName
     * @return
     */
    public static String createDatabase(String databaseName) {
        return "CREATE DATABASE " + databaseName;
    }

    /**
     * 创建数据库
     *
     * @param ifNotExists
     * @param databaseName
     * @return
     */
    public static String createDatabase(boolean ifNotExists, String databaseName) {
        return "CREATE DATABASE " + getIfNotExists(ifNotExists) + " "
                + databaseName;
    }

    /**
     * 动态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param selectTableName
     * @param selectColumns
     * @param partitionColumns
     * @param whereColumns
     * @return
     */
    public static String InsertTrendsTable(boolean isOverwrite,
                                           String insertTableName, String selectTableName,
                                           List<String> selectColumns, List<String> partitionColumns,
                                           List<KeyAndValue> whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + getPartitionKey(partitionColumns)
                + " SELECT " + getSelectColumns(selectColumns) + " FROM "
                + selectTableName + getWhere2(whereColumns);
    }

    /**
     * 动态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param insertColumns
     * @param selectTableName
     * @param selectColumns
     * @param partitionColumns
     * @param whereColumns
     * @return
     */
    public static String InsertTrendsTable(boolean isOverwrite,
                                           String insertTableName, List<String> insertColumns,
                                           String selectTableName, List<String> selectColumns,
                                           List<String> partitionColumns, List<KeyAndValue> whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + getInsertColumns(insertColumns)
                + getPartitionKey(partitionColumns) + " SELECT "
                + getSelectColumns(selectColumns) + " FROM " + selectTableName
                + getWhere2(whereColumns);
    }

    /**
     * 静态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param selectTableName
     * @param selectColumns
     * @param partitions
     * @param whereColumns
     * @return
     */
    public static String InsertStaticTable(boolean isOverwrite,
                                           String insertTableName, String selectTableName,
                                           List<String> selectColumns, List<KeyAndValue> partitions,
                                           List<KeyAndValue> whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + getPartitionKeyAndVaule(partitions)
                + " SELECT " + getSelectColumns(selectColumns) + " FROM "
                + selectTableName + getWhere2(whereColumns);
    }

    /**
     * 静态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param insertColumns
     * @param selectTableName
     * @param selectColumns
     * @param partitions
     * @param whereColumns
     * @return
     */
    public static String InsertStaticTable(boolean isOverwrite,
                                           String insertTableName, List<String> insertColumns,
                                           String selectTableName, List<String> selectColumns,
                                           List<KeyAndValue> partitions, List<KeyAndValue> whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + " " + getInsertColumns(insertColumns)
                + getPartitionKeyAndVaule(partitions) + " SELECT "
                + getSelectColumns(selectColumns) + " FROM " + selectTableName
                + getWhere2(whereColumns);
    }

    /**
     * 静态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param selectTableName
     * @param selectColumns
     * @param partitions
     * @param whereColumns
     * @return
     */
    public static String InsertStaticTable2(boolean isOverwrite,
                                            String insertTableName, String selectTableName,
                                            List<String> selectColumns, List<KeyAndValue> partitions,
                                            String whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + getPartitionKeyAndVaule(partitions)
                + " SELECT " + getSelectColumns(selectColumns) + " FROM "
                + selectTableName + getWhere(whereColumns);
    }

    protected static String getWhere(String whereColumns) {
        String sql = "";
        if (whereColumns != null && !"".equals(whereColumns)) {
            sql = " WHERE " + whereColumns;
        }
        return sql;
    }

    /**
     * 静态插入
     *
     * @param isOverwrite
     * @param insertTableName
     * @param insertColumns
     * @param selectTableName
     * @param selectColumns
     * @param partitions
     * @param whereColumns
     * @return
     */
    public static String InsertStaticTable2(boolean isOverwrite,
                                            String insertTableName, List<String> insertColumns,
                                            String selectTableName, List<String> selectColumns,
                                            List<KeyAndValue> partitions, String whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + " TABLE "
                + insertTableName + " " + getInsertColumns(insertColumns)
                + getPartitionKeyAndVaule(partitions) + " SELECT "
                + getSelectColumns(selectColumns) + " FROM " + selectTableName
                + " WHERE " + whereColumns;
    }

    /**
     * 拷贝表结构创建新表
     *
     * @return
     */
    public static String createLikeTable(boolean ifNotExists,
                                         String oldTableName, String newTableName) {
        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " "
                + newTableName + " LIKE " + oldTableName;
    }

    /**
     * 简易版建内表
     *
     * @param tableName
     * @param columns
     * @param separator
     * @param fileFormat
     * @return
     */
    public static String createTable(String tableName,
                                     List<TableColumn> columns, String separator, String fileFormat) {
        return "CREATE TABLE " + tableName + getColumns(columns)
                + " ROW FORMAT DELIMITED FIELDS TERMINATED BY '" + separator
                + "' STORED AS " + fileFormat;
    }

    /**
     * 简易版建内分区表
     *
     * @param tableName
     * @param columns
     * @param partitions
     * @param separator
     * @param fileFormat
     * @return
     */
    public static String createPartitionTable(String tableName,
                                              List<TableColumn> columns, List<TableColumn> partitions,
                                              String separator, String fileFormat) {
        return "CREATE TABLE " + tableName + getColumns(columns)
                + getPartitions(partitions)
                + " ROW FORMAT DELIMITED FIELDS TERMINATED BY '" + separator
                + "' STORED AS " + fileFormat;
    }

    /**
     * 删除数据库
     *
     * @param databaseName
     * @return
     */
    public static String dropDatabase(String databaseName) {
        return "DROP DATABASE " + databaseName;
    }

    /**
     * 删除表
     *
     * @param tableName
     * @return
     */
    public static String dropTable(String tableName) {
        return "DROP TABLE " + tableName;
    }

    /**
     * 删除表
     *
     * @param tableName
     * @return
     */
    public static String dropTable(String tableName, boolean ifExists) {
        return "DROP TABLE" + getIfExists(ifExists) + " " + tableName;
    }

    /**
     * 查询表指定字段所有数据
     *
     * @param tableName
     * @return
     */
    public static String queryTableAllDatas(String tableName,
                                            List<String> columns) {
        return "SELECT " + getSelectColumns(columns) + " FROM " + tableName;
    }

    /**
     * 查询表指定字段Top N
     *
     * @param tableName
     * @param limit
     * @return
     */
    public static String queryTableLimitN(String tableName, int limit,
                                          List<String> columns) {
        return "SELECT " + getSelectColumns(columns) + " FROM " + tableName
                + " LIMIT " + limit;
    }

    /**
     * 创建简单视图
     *
     * @param viewName
     * @param tableName
     * @return
     */
    public static String createView(boolean ifNotExists, String viewName,
                                    String tableName) {
        return "CREATE VIEW" + getIfNotExists(ifNotExists) + " " + viewName
                + " AS SELECT * FROM " + tableName;
    }

    /**
     * 创建常规视图
     *
     * @param ifNotExists
     * @param viewName
     * @param viewColumns
     * @param tableName
     * @param tableColumns
     * @return
     */
    public static String createView(boolean ifNotExists, String viewName,
                                    List<String> viewColumns, String tableName,
                                    List<String> tableColumns) {
        return "CREATE VIEW" + getIfNotExists(ifNotExists) + " " + viewName
                + getInsertColumns(viewColumns) + " AS SELECT "
                + getSelectColumns(tableColumns) + " FROM " + tableName;
    }

    /**
     * 创建复杂视图
     *
     * @param ifNotExists
     * @param viewName
     * @param columns
     * @param selectStatement
     * @return
     */
    public static String createView(boolean ifNotExists, String viewName,
                                    List<String> columns, String selectStatement) {
        return "CREATE VIEW" + getIfNotExists(ifNotExists) + " " + viewName
                + getInsertColumns(columns) + " AS " + selectStatement;
    }

    protected static String getExternal(boolean isExternal) {
        String sql = "";
        if (isExternal) {
            sql = " EXTERNAL";
        }
        return sql;
    }

    protected static String getOverwrite(boolean isOverwrite) {
        String sql = " INTO";
        if (isOverwrite) {
            sql = " OVERWRITE";
        }
        return sql;
    }

    protected static String getIfNotExists(boolean ifNotExists) {
        String sql = "";
        if (ifNotExists) {
            sql = " IF NOT EXISTS";
        }
        return sql;
    }

    protected static String getIfExists(boolean ifExists) {
        String sql = "";
        if (ifExists) {
            sql = " IF EXISTS";
        }
        return sql;
    }

    protected static String getTableComment(String tableComment) {
        String sql = "";
        if (tableComment != null && !tableComment.trim().equals("")) {
            sql = " COMMENT '" + tableComment + "'";
        }
        return sql;
    }

    protected static String getColumns(List<TableColumn> columns) {
        String sql = "";
        TableColumn column = null;
        String colName = "";
        String dataType = "";
        String colComment = "";
        if (columns != null && columns.size() != 0) {
            sql = " (";
            for (int i = 0; i < columns.size(); i++) {
                column = columns.get(i);
                colName = column.getColName();
                dataType = column.getDataType();
                colComment = column.getColComment();
                if (colName != null && !colName.trim().equals("")
                        && dataType != null && !dataType.trim().equals("")) {
                    if (i == 0) {
                        sql += colName + " " + dataType;
                    } else {
                        sql += ", " + colName + " " + dataType;
                    }
                    if (colComment != null && !colComment.trim().equals("")) {
                        sql += " COMMENT '" + colComment + "'";
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getSerDeProperties(List<KeyAndValue> serDeProperties) {
        String sql = "";
        KeyAndValue serDePropertie = null;
        String key = "";
        String value = "";
        if (serDeProperties != null && serDeProperties.size() != 0) {
            sql = " WITH SERDEPROPERTIES (";
            for (int i = 0; i < serDeProperties.size(); i++) {
                serDePropertie = serDeProperties.get(i);
                key = serDePropertie.getKey();
                value = serDePropertie.getValue();
                if (key != null && !key.trim().equals("") && value != null
                        && !value.trim().equals("")) {
                    if (i == 0) {
                        sql += "'" + key + "'='" + value + "'";
                    } else {
                        sql += ", '" + key + "'='" + value + "'";
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getTblProperties(List<TblProperties> tblProperties) {
        String sql = "";
        TblProperties tblPropertie = null;
        String key = "";
        String value = "";
        if (tblProperties != null && tblProperties.size() != 0) {
            sql = " TBLPROPERTIES (";
            for (int i = 0; i < tblProperties.size(); i++) {
                tblPropertie = tblProperties.get(i);
                key = tblPropertie.getKey();
                value = tblPropertie.getValue();
                if (key != null && !key.trim().equals("") && value != null
                        && !value.trim().equals("")) {
                    if (i == 0) {
                        sql += "'" + key + "'='" + value + "'";
                    } else {
                        sql += ", '" + key + "'='" + value + "'";
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getFileFormat(String fileFormat) {
        String sql = "";
        if (fileFormat != null && !fileFormat.trim().equals("")) {
            sql = " STORED AS " + fileFormat;
        }
        return sql;
    }

    protected static String getPartitions(List<TableColumn> partitions) {
        String sql = "";
        TableColumn partition = null;
        String colName = "";
        String dataType = "";
        String colComment = "";
        if (partitions != null && partitions.size() != 0) {
            sql = " PARTITIONED BY (";
            for (int i = 0; i < partitions.size(); i++) {
                partition = partitions.get(i);
                colName = partition.getColName();
                dataType = partition.getDataType();
                colComment = partition.getColComment();
                if (colName != null && !colName.trim().equals("")
                        && dataType != null && !dataType.trim().equals("")) {
                    if (i == 0) {
                        sql += colName + " " + dataType;
                    } else {
                        sql += ", " + colName + " " + dataType;
                    }
                    if (colComment != null && !colComment.trim().equals("")) {
                        sql += " COMMENT '" + colComment + "'";
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getSelectColumns(List<String> columns) {
        String sql = " * ";
        if (columns != null && columns.size() != 0) {
            for (int i = 0; i < columns.size(); i++) {
                if (i == 0) {
                    sql = " " + columns.get(i);
                } else {
                    sql += ", " + columns.get(i);
                }
            }
        }
        return sql;
    }

    protected static String getInsertColumns(List<String> columns) {
        String sql = " ";
        if (columns != null && columns.size() != 0) {
            sql = " (";
            for (int i = 0; i < columns.size(); i++) {
                if (i == 0) {
                    sql += columns.get(i);
                } else {
                    sql += ", " + columns.get(i);
                }
            }
            sql += ")";
        }
        return sql;
    }

    @Deprecated
    protected static String getColumns(LinkedHashMap<String, String> columns) {
        String sql = "";
        if (columns != null && columns.size() != 0) {
            sql = " (";
            int i = 0;
            for (Entry<String, String> entry : columns.entrySet()) {
                if (i == 0) {
                    sql += entry.getKey() + " " + entry.getValue();
                } else {
                    sql += ", " + entry.getKey() + " " + entry.getValue();
                }
                i++;
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getColumns2(List<KeyAndValue> columns) {
        String sql = "";
        KeyAndValue kv = null;
        if (columns != null && columns.size() != 0) {
            sql = " (";
            for (int i = 0; i < columns.size(); i++) {
                kv = columns.get(i);
                if (i == 0) {
                    sql += kv.getKey() + " " + kv.getValue();
                } else {
                    sql += ", " + kv.getKey() + " " + kv.getValue();
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getOrders(List<String> orders) {
        String sql = "";
        if (orders != null && orders.size() != 0) {
            sql = " ORDER BY ";
            for (int i = 0; i < orders.size(); i++) {
                if (i == 0) {
                    sql += orders.get(i);
                } else {
                    sql += ", " + orders.get(i);
                }
            }
        }
        return sql;
    }

    protected static String getLocation(String location) {
        String sql = "";
        if (location != null && !location.trim().equals("")) {
            sql = " LOCATION '" + location + "'";
        }
        return sql;
    }

    protected static String getLocal(boolean isLocal) {
        String sql = "";
        if (isLocal) {
            sql = " LOCAL";
        }
        return sql;
    }

    @Deprecated
    protected static String getWhere(LinkedHashMap<String, String> columns) {
        String sql = "";
        if (columns != null && columns.size() != 0) {
            sql = " WHERE ";
            int i = 0;
            for (Entry<String, String> entry : columns.entrySet()) {
                if (i == 0) {
                    sql += entry.getKey() + "='" + entry.getValue() + "'";
                } else {
                    sql += " AND " + entry.getKey() + "='" + entry.getValue()
                            + "'";
                }
                i++;
            }
        }
        return sql;
    }

    protected static String getWhere2(List<KeyAndValue> columns) {
        String sql = "";
        KeyAndValue kv = null;
        if (columns != null && columns.size() != 0) {
            sql = " WHERE ";
            for (int i = 0; i < columns.size(); i++) {
                kv = columns.get(i);
                if (i == 0) {
                    sql += kv.getKey() + "='" + kv.getValue() + "'";
                } else {
                    sql += " AND " + kv.getKey() + "='" + kv.getValue() + "'";
                }
                i++;
            }
        }
        return sql;
    }

    protected static String getRowFormat(RowFormat rowFormat) {
        String sql = "";
        if (rowFormat != null) {
            sql = " ROW FORMAT DELIMITED";
            String fieldsTerminated = rowFormat.getFieldsTerminated();
            String fieldsEscaped = rowFormat.getFieldsEscaped();
            String linesTerminated = rowFormat.getLinesTerminated();
            if (fieldsTerminated != null && !fieldsTerminated.trim().equals("")) {
                sql += " FIELDS TERMINATED BY '" + fieldsTerminated + "'";
                if (fieldsEscaped != null && !fieldsEscaped.trim().equals("")) {
                    sql += " ESCAPED BY '" + fieldsEscaped + "'";
                }
            }
            if (linesTerminated != null && !linesTerminated.trim().equals("")) {
                sql += " LINES TERMINATED BY '" + linesTerminated + "'";
            }
        }
        return sql;
    }

    protected static String getPartitionKeyAndVaule(List<KeyAndValue> partitions) {
        String sql = "";
        KeyAndValue partition = null;
        String colName = "";
        String colValue = "";
        if (partitions != null && partitions.size() != 0) {
            sql = " PARTITION (";
            for (int i = 0; i < partitions.size(); i++) {
                partition = partitions.get(i);
                colName = partition.getKey();
                colValue = partition.getValue();
                if (i == 0) {
                    sql += colName + "='" + colValue + "'";
                } else {
                    sql += ", " + colName + "='" + colValue + "'";
                }
            }
            sql += ")";
        }
        return sql;
    }

    protected static String getPartitionKey(List<String> columns) {
        String sql = "";
        if (columns != null && columns.size() != 0) {
            sql = " PARTITION (";
            if (columns != null && columns.size() != 0) {
                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        sql += columns.get(i);
                    } else {
                        sql += ", " + columns.get(i);
                    }
                }
            }
            sql += ")";
        }
        return sql;
    }
}
