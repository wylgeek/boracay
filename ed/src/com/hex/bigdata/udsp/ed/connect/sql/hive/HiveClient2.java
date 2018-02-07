package com.hex.bigdata.udsp.ed.connect.sql.hive;

import com.hex.bigdata.udsp.ed.connect.sql.CommonClient2;
import com.hex.bigdata.udsp.ed.connect.sql.model.HBaseColumn;
import com.hex.bigdata.udsp.ed.connect.sql.model.KeyAndValue;
import com.hex.bigdata.udsp.ed.connect.sql.model.TableColumn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 1、无数据库名参数的表名必须是表全名称，如：db_name.tb_name
 * <p>
 * 2、select * 时，返回的字段名是字段全名称，如：tb_name.col_name
 *
 * @author junjiem
 */
public class HiveClient2 extends CommonClient2 {
    public HiveClient2() {
        super();
        /*
		 * HiveService1
		 */
        // driverClass = "com.cloudera.hive.jdbc4.HS1Driver";
        // jdbcUrl = "jdbc:hive://8.99.5.202:10000/default";

		/*
		 * HiveService2
		 */
        driverClass = "com.cloudera.hive.jdbc4.HS2Driver";
        jdbcUrl = "jdbc:hive2://8.99.5.202:10000/default";
        username = "hive";
        password = "";
    }

    public HiveClient2(String driverClass, String jdbcUrl, String username,
                       String password) {
        super(driverClass, jdbcUrl, username, password);
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
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createHbaseCorrelationTable(boolean isExternal,
                                           boolean ifNotExists, String tableName,
                                           List<TableColumn> hiveColumns, String tableComment,
                                           List<HBaseColumn> hbaseColumns, String hbaseTableName,
                                           Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.createHbaseCorrelationTable(isExternal,
                ifNotExists, tableName, hiveColumns, tableComment,
                hbaseColumns, hbaseTableName), statement);
    }

    /**
     * 创建HBase关联内表（推荐）
     * <p>
     * 1、创建Hive表的同时自动创建HBase表，Hive表字段首位对应HBase的rowkey，
     * 第二位开始作为HBase的f族的列（小写），HBase表名与Hive表名一致（小写）
     *
     * @param tableName
     * @param hiveColumns
     * @param tableComment
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createHbaseCorrelationTable(String tableName,
                                           List<TableColumn> hiveColumns, String tableComment,
                                           Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.createHbaseCorrelationTable(tableName,
                hiveColumns, tableComment), statement);
    }

    /**
     * 创建Hive文本格式的表，可以实现数据导入时自动转码
     *
     * @param ifNotExists
     * @param tableName
     * @param columns
     * @param tableComment
     * @param partitions
     * @param fieldsTerminated 字段分隔符
     * @param isTrim           是否去除字段内容的头尾空格
     * @param encoding         源文件编码
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createTranscodTable(boolean ifNotExists, String tableName,
                                   List<TableColumn> columns, String tableComment,
                                   List<TableColumn> partitions, String fieldsTerminated,
                                   boolean isTrim, String encoding, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.createMultiDelimiterTable(ifNotExists,
                tableName, columns, tableComment, partitions, fieldsTerminated,
                isTrim, encoding), statement);
    }

    /**
     * 查询表的字段集合
     *
     * @param tableName
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showColumns(String tableName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(HiveSql.showColumns(tableName), statement);
        return getInfos(rs);
    }

    /**
     * 表改名
     *
     * @param databaseName
     * @param oldTableName
     * @param newTableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int rename(String databaseName, String oldTableName,
                      String newTableName, Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.rename(oldTableName, newTableName), statement);
    }

    /**
     * 添加字段
     *
     * @param databaseName
     * @param tableName
     * @param columnName
     * @param columnType
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int addOneColumn(String databaseName, String tableName,
                            String columnName, String columnType, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.addOneColumn(tableName, columnName, columnType), statement);
    }

    /**
     * 添加字段
     *
     * @param databaseName
     * @param tableName
     * @param columns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int addColumns(String databaseName, String tableName,
                          List<KeyAndValue> columns, Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.addColumns(tableName, columns), statement);
    }

    /**
     * 改变字段
     *
     * @param databaseName
     * @param tableName
     * @param oldColumnName
     * @param newColumnName
     * @param newColumnType
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int changeColumn(String databaseName, String tableName,
                            String oldColumnName, String newColumnName, String newColumnType,
                            Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName)
                + ";"
                + HiveSql.changeColumn(tableName, oldColumnName, newColumnName,
                newColumnType), statement);
    }

    /**
     * 替换字段
     *
     * @param databaseName
     * @param tableName
     * @param columnName
     * @param columnType
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int replaceOneColumns(String databaseName, String tableName,
                                 String columnName, String columnType, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                        + HiveSql.replaceOneColumns(tableName, columnName, columnType),
                statement);
    }

    /**
     * 替换字段
     *
     * @param databaseName
     * @param tableName
     * @param columns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int replaceColumns(String databaseName, String tableName,
                              List<KeyAndValue> columns, Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.replaceColumns(tableName, columns), statement);
    }

    /**
     * 添加分区
     *
     * @param databaseName
     * @param tableName
     * @param colName
     * @param colValue
     * @param dataType
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int addPartition(String databaseName, String tableName,
                            String colName, String colValue, String dataType,
                            Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.addPartition(tableName, colName, colValue), statement);
    }

    /**
     * 添加分区
     *
     * @param databaseName
     * @param tableName
     * @param partitions
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int addPartition(String databaseName, String tableName,
                            List<KeyAndValue> partitions, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.addPartition(tableName, partitions), statement);
    }

    /**
     * 删除分区
     *
     * @param databaseName
     * @param tableName
     * @param colName
     * @param colValue
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int dropPartition(String databaseName, String tableName,
                             String colName, String colValue, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.dropPartition(tableName, colName, colValue), statement);
    }

    /**
     * 删除分区
     *
     * @param databaseName
     * @param tableName
     * @param partitions
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int dropPartition(String databaseName, String tableName,
                             List<KeyAndValue> partitions, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(HiveSql.useDatabase(databaseName) + ";"
                + HiveSql.dropPartition(tableName, partitions), statement);
    }

}
