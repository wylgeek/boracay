
package com.hex.bigdata.udsp.ed.connect.sql.hive;

import com.hex.bigdata.udsp.ed.connect.sql.CommonSql;
import com.hex.bigdata.udsp.ed.connect.sql.model.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Hive和Impala的SQL还是有很大的差异的，建议像建表、添加分区等操作由Hive来执行。
 *
 * @author k0183
 */
public class HiveSql extends CommonSql {
    private static final String HBASE_COLUMN_FAMILY_DEFAULT = "d";

    /**
     * 添加单字段字符串类型分区
     *
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String addPartition(String tableName, String colName,
                                      String colValue) {
        return "ALTER TABLE " + tableName + " ADD PARTITION (" + colName + "='"
                + colValue + "')";
    }

    /**
     * 添加单字段字符串类型分区
     *
     * @param ifNotExists
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String addPartition(boolean ifNotExists, String tableName,
                                      String colName, String colValue) {
        return "ALTER TABLE " + tableName + " ADD"
                + getIfNotExists(ifNotExists) + " PARTITION (" + colName + "='"
                + colValue + "')";
    }

    /**
     * 删除单字段字符串类型分区
     *
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String dropPartition(String tableName, String colName,
                                       String colValue) {
        return "ALTER TABLE " + tableName + " DROP PARTITION (" + colName
                + "='" + colValue + "')";
    }

    /**
     * 删除单字段字符串类型分区
     *
     * @param ifExists
     * @param tableName
     * @param colName
     * @param colValue
     * @return
     */
    public static String dropPartition(boolean ifExists, String tableName,
                                       String colName, String colValue) {
        return "ALTER TABLE " + tableName + " DROP" + getIfExists(ifExists)
                + " PARTITION (" + colName + "='" + colValue + "')";
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
                + selectTableName + getWhere2(whereColumns)
                + getDistributeBy(partitionColumns);
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
     * 将Hive查询的结果输出到HDFS或Local
     *
     * @param isOverwrite
     * @param isLocal
     * @param directory
     * @param tableName
     * @param columns
     * @param whereColumns
     * @return
     */
    public static String insertDirectory(boolean isOverwrite, boolean isLocal,
                                         String directory, String tableName, List<String> columns,
                                         List<KeyAndValue> whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + getLocal(isLocal)
                + " DIRECTORY '" + directory + "' SELECT "
                + getSelectColumns(columns) + " FROM " + tableName
                + getWhere2(whereColumns);
    }

    /**
     * 将Hive查询的结果输出到HDFS或Local
     *
     * @param isOverwrite
     * @param isLocal
     * @param directory
     * @param tableName
     * @param columns
     * @param whereColumns
     * @return
     */
    public static String insertDirectory2(boolean isOverwrite, boolean isLocal,
                                          String directory, String tableName, List<String> columns,
                                          String whereColumns) {
        return "INSERT" + getOverwrite(isOverwrite) + getLocal(isLocal)
                + " DIRECTORY '" + directory + "' SELECT "
                + getSelectColumns(columns) + " FROM " + tableName + " WHERE "
                + whereColumns;
    }

    /**
     * 相对完整版建表（最常用版本1）
     *
     * @param isExternal
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param rowFormat
     * @param fileFormat
     * @return
     */
    public static String createTable(boolean isExternal, String tableName,
                                     List<TableColumn> columns, String tableComment,
                                     List<TableColumn> partitions, RowFormat rowFormat, String fileFormat) {
        return "CREATE" + getExternal(isExternal) + " TABLE " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormat(rowFormat)
                + getFileFormat(fileFormat);
    }

    /**
     * 相对完整版建表（最常用版本2）
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param rowFormat
     * @param fileFormat
     * @return
     */
    public static String createTable(boolean isExternal, boolean ifNotExists,
                                     String tableName, List<TableColumn> columns, String tableComment,
                                     List<TableColumn> partitions, RowFormat rowFormat, String fileFormat) {
        return "CREATE" + getExternal(isExternal) + " TABLE"
                + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormat(rowFormat)
                + getFileFormat(fileFormat);
    }

    /**
     * 创建HBase关联表（内、外关联表）
     * <p>
     * 1、当指定是外部关联HBase表时，HBase表不存在或列族不对应也没有报错或返回错误状态，这应该是JDBC中的一个BUG；
     * 所以使用外部关联HBase表时，建议一定要先确定关联的HBase存在，并且字段是正确对应的。
     * <p>
     * 2、不建议使用外部关联方式，这样HBase表删除没法被管理；
     * 但HBase可以先手动建Region，Hive自动创建Hbase表不会建Region。
     *
     * @param isExternal
     * @param ifNotExists
     * @param tableName
     * @param hiveColumns
     * @param tableComment
     * @param hbaseColumns
     * @param hbaseTableName
     * @return
     */
    public static String createHbaseCorrelationTable(boolean isExternal,
                                                     boolean ifNotExists, String tableName,
                                                     List<TableColumn> hiveColumns, String tableComment,
                                                     List<HBaseColumn> hbaseColumns, String hbaseTableName) {
        return "CREATE" + getExternal(isExternal) + " TABLE"
                + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(hiveColumns) + getTableComment(tableComment)
                + getHBaseStorageHandler()
                + getHBaseSerDeProperties(hbaseColumns)
                + getHbaseTblProperties(hbaseTableName);
    }

    /**
     * 创建HBase关联表（内表）
     * <p>
     * 1、创建Hive表的同时自动创建HBase表，Hive表字段首位对应HBase的rowkey，
     * 第二位开始作为HBase的f族的列（小写），HBase表名与Hive表名一致（小写）
     *
     * @param tableName
     * @param columns
     * @param tableComment
     * @return
     */
    public static String createHbaseCorrelationTable(String tableName,
                                                     List<TableColumn> columns, String tableComment) {
        TableColumn column = null;
        List<HBaseColumn> hbaseColumns = new ArrayList<HBaseColumn>();
        for (int i = 1; i < columns.size(); i++) {
            column = columns.get(i);
            hbaseColumns.add(new HBaseColumn(HBASE_COLUMN_FAMILY_DEFAULT,
                    column.getColName().toLowerCase()));
        }
        return createHbaseCorrelationTable(false, false, tableName, columns,
                tableComment, hbaseColumns, null);
    }

    /**
     * 创建Hive文本格式的表，可以实现多分隔符数据导入
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param fieldsTerminated 字段分隔符
     * @param isTrim           是否去除字段内容的头尾空格
     * @param encoding         源文件编码
     * @return
     */
    public static String createMultiDelimiterTable(boolean ifNotExists,
                                                   String tableName, List<TableColumn> columns, String tableComment,
                                                   List<TableColumn> partitions, String fieldsTerminated,
                                                   boolean isTrim, String encoding) {
        String serDeClassName = "com.hex.hive.serde.MultiDelimiterSerDe";
        List<KeyAndValue> serDeProperties = new ArrayList<KeyAndValue>();
        serDeProperties
                .add(new KeyAndValue("input.delimiter", fieldsTerminated));
        serDeProperties.add(new KeyAndValue("trim", String.valueOf(isTrim)));
        serDeProperties.add(new KeyAndValue("encoding", encoding));

        // 字段数据必须是STRING类型
        if (columns != null && columns.size() != 0) {
            for (TableColumn column : columns) {
                column.setDataType("STRING");
            }
        }
        if (partitions != null && partitions.size() != 0) {
            for (TableColumn column : partitions) {
                column.setDataType("STRING");
            }
        }

        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormatSerDe(serDeClassName)
                + getSerDeProperties(serDeProperties)
                + getFileFormat(FileFormat.IMPALA_FILE_FORMAT_TEXTFILE);
    }

    /**
     * 创建Hive文本格式的表，可以实现定长数据导入
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param fmtLengths   定长长度信息
     * @param isTrim       是否去除字段内容的头尾空格
     * @param encoding     源文件编码
     * @return
     */
    public static String createFixedLengthTable(boolean ifNotExists,
                                                String tableName, List<TableColumn> columns, String tableComment,
                                                List<TableColumn> partitions, String fmtLengths, boolean isTrim,
                                                String encoding) {

        String serDeClassName = "com.hex.hive.serde.FixedLengthSerDe";
        List<KeyAndValue> serDeProperties = new ArrayList<KeyAndValue>();
        serDeProperties.add(new KeyAndValue("fmt.lengths", fmtLengths));
        serDeProperties.add(new KeyAndValue("trim", String.valueOf(isTrim)));
        serDeProperties.add(new KeyAndValue("encoding", encoding));

        // 字段数据必须是STRING类型
        if (columns != null && columns.size() != 0) {
            for (TableColumn column : columns) {
                column.setDataType("STRING");
            }
        }
        if (partitions != null && partitions.size() != 0) {
            for (TableColumn column : partitions) {
                column.setDataType("STRING");
            }
        }

        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormatSerDe(serDeClassName)
                + getSerDeProperties(serDeProperties)
                + getFileFormat(FileFormat.IMPALA_FILE_FORMAT_TEXTFILE);
    }

    /**
     * 创建Hive文本格式的表，可以实现定长且带有分隔符的数据导入
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param fmtLengths   定长长度信息
     * @param isTrim       是否去除字段内容的头尾空格
     * @param encoding     源文件编码
     * @return
     */
    public static String createFixedLengthDelimiterTable(boolean ifNotExists,
                                                         String tableName, List<TableColumn> columns, String tableComment,
                                                         List<TableColumn> partitions, String fmtLengths, boolean isTrim,
                                                         String encoding, String delimiter) {

        String serDeClassName = "com.hex.hive.serde.FixedLengthDelimiterSerDe";
        List<KeyAndValue> serDeProperties = new ArrayList<KeyAndValue>();
        serDeProperties.add(new KeyAndValue("fmt.lengths", fmtLengths));
        serDeProperties.add(new KeyAndValue("trim", String.valueOf(isTrim)));
        serDeProperties.add(new KeyAndValue("encoding", encoding));
        if (StringUtils.isNotEmpty(delimiter)) {
            serDeProperties.add(new KeyAndValue("delimiter", delimiter));
        }

        // 字段数据必须是STRING类型
        if (columns != null && columns.size() != 0) {
            for (TableColumn column : columns) {
                column.setDataType("STRING");
            }
        }
        if (partitions != null && partitions.size() != 0) {
            for (TableColumn column : partitions) {
                column.setDataType("STRING");
            }
        }

        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormatSerDe(serDeClassName)
                + getSerDeProperties(serDeProperties)
                + getFileFormat(FileFormat.IMPALA_FILE_FORMAT_TEXTFILE);
    }

    /**
     * 创建Hive文本格式的表，可以实现CSV数据导入
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param separatorChar 分隔符
     * @param quoteChar     引用字符
     * @param escapeChar    忽略字符
     * @return
     */
    public static String createCsvTable(boolean ifNotExists, String tableName,
                                        List<TableColumn> columns, String tableComment,
                                        List<TableColumn> partitions, String separatorChar,
                                        String quoteChar, String escapeChar, boolean isTrim, String encoding) {
        String serDeClassName = "com.hex.hive.serde.OpenCSVSerDe";
        List<KeyAndValue> serDeProperties = new ArrayList<KeyAndValue>();
        serDeProperties.add(new KeyAndValue("separatorChar", separatorChar));
        serDeProperties.add(new KeyAndValue("quoteChar", quoteChar));
        serDeProperties.add(new KeyAndValue("escapeChar", escapeChar));
        serDeProperties.add(new KeyAndValue("encoding", encoding));
        serDeProperties.add(new KeyAndValue("trim", String.valueOf(isTrim)));

        // 字段数据必须是STRING类型
        if (columns != null && columns.size() != 0) {
            for (TableColumn column : columns) {
                column.setDataType("STRING");
            }
        }
        if (partitions != null && partitions.size() != 0) {
            for (TableColumn column : partitions) {
                column.setDataType("STRING");
            }
        }

        return "CREATE TABLE" + getIfNotExists(ifNotExists) + " " + tableName
                + getColumns(columns) + getTableComment(tableComment)
                + getPartitions(partitions) + getRowFormatSerDe(serDeClassName)
                + getSerDeProperties(serDeProperties)
                + getFileFormat(FileFormat.IMPALA_FILE_FORMAT_TEXTFILE);
    }

    /**
     * 加载数据到表
     *
     * @param isLocal     是否是本地数据
     * @param filePath    本地数据路径或HDFS数据路径
     * @param isOverwrite 是否覆盖
     * @param tableName   表名
     * @param partitions  分区集合
     * @return
     */
    public static String loadData(boolean isLocal, String filePath,
                                  boolean isOverwrite, String tableName, List<KeyAndValue> partitions) {
        return "LOAD DATA" + getLocal(isLocal) + " INPATH '" + filePath + "'"
                + getOverwrite(isOverwrite) + " INTO TABLE " + tableName
                + getPartitionKeyAndVaule(partitions);
    }

    /**
     * 查询表的字段集合
     *
     * @param tableName
     * @return
     */
    public static String showColumns(String tableName) {
        return "SHOW COLUMNS FROM " + tableName;
    }

    /**
     * 选中数据库
     *
     * @return
     */
    public static String useDatabase(String databaseName) {
        return "USE " + databaseName;
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

    private static String getHBaseStorageHandler() {
        return " STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'";
    }

    private static String getHBaseSerDeProperties(List<HBaseColumn> hbaseColumns) {
        String sql = "";
        HBaseColumn hbaseColumn = null;
        if (hbaseColumns != null && hbaseColumns.size() != 0) {
            sql = " WITH SERDEPROPERTIES ('hbase.columns.mapping'=':key";
            for (int i = 0; i < hbaseColumns.size(); i++) {
                hbaseColumn = hbaseColumns.get(i);
                sql += ", " + hbaseColumn.getFamily() + ":"
                        + hbaseColumn.getQualifier();
            }
            sql += "')";
        }
        return sql;
    }

    private static String getHbaseTblProperties(String hbaseTableName) {
        String sql = "";
        if (hbaseTableName != null && !hbaseTableName.trim().equals("")) {
            sql = " TBLPROPERTIES ('hbase.table.name'='" + hbaseTableName
                    + "')";
        }
        return sql;
    }

    private static String getRowFormatSerDe(String serDeClassName) {
        String sql = "";
        if (serDeClassName != null && !serDeClassName.trim().equals("")) {
            sql = " ROW FORMAT SERDE '" + serDeClassName + "'";
        }
        return sql;
    }

    private static String getDistributeBy(List<String> columns) {
        String sql = "";
        if (columns != null && columns.size() != 0) {
            sql = " DISTRIBUTE BY ";
            if (columns != null && columns.size() != 0) {
                for (int i = 0; i < columns.size(); i++) {
                    if (i == 0) {
                        sql += columns.get(i);
                    } else {
                        sql += ", " + columns.get(i);
                    }
                }
            }
        }
        return sql;
    }

}
