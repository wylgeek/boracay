package com.hex.bigdata.udsp.ed.connect.sql;

import com.hex.bigdata.udsp.ed.connect.sql.impala.ImpalaSql;
import com.hex.bigdata.udsp.ed.connect.sql.model.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class CommonClient2 {
    private static Logger logger = LoggerFactory.getLogger(CommonClient2.class);
    private static final String ROW_FORMAT_DEFAULT_SEPARATOR = "\\007";
    private static final String FILE_FORMAT_TEXTFILE = "TEXTFILE";

    public static Map<String, Statement> stmtPool = new HashMap<String, Statement>();

    protected String driverClass = "";
    protected String jdbcUrl = "";
    protected String username = "";
    protected String password = "";

    public CommonClient2() {
        super();
    }

    public CommonClient2(String driverClass, String jdbcUrl, String username,
                         String password) {
        super();
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取链接
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnection() throws SQLException,
            ClassNotFoundException {
        Class.forName(driverClass);
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    /**
     * 获取Statement
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException, ClassNotFoundException {
        return getConnection().createStatement();
    }

    /**
     * 释放资源
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 释放资源
     *
     * @param stmt
     * @param conn
     */
    public void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 释放资源
     *
     * @param rs
     */
    public void release(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
    }

    /**
     * 释放资源
     *
     * @param stmt
     */
    public void release(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
    }

    /**
     * 释放资源
     *
     * @param conn
     */
    public void release(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 执行查询操作
     *
     * @param querySql
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet executeQuery(String querySql, Statement... statement)
            throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        if (statement == null || statement.length == 0) {
            stmt = getStatement();
        } else {
            stmt = statement[statement.length - 1];
        }
        logger.debug(querySql);
        return stmt.executeQuery(querySql);
    }

    /**
     * 执行修改操作
     *
     * @param updateSql
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int executeUpdate(String updateSql, Statement... statement)
            throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        if (statement == null || statement.length == 0) {
            stmt = getStatement();
        } else {
            stmt = statement[statement.length - 1];
        }
        logger.debug(updateSql);
        return stmt.executeUpdate(updateSql);
    }

    /**
     * 查看当前环境参数值
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<String> set(Statement... statement) throws SQLException,
            ClassNotFoundException {
        ResultSet rs = executeQuery(ImpalaSql.set(), statement);
        return getInfos(rs);
    }

    /**
     * 获取SQL执行情况详细信息
     * <p>
     * （可以当SQL语句检查来使用，有返回值说明SQL可执行，报错说明语句有问题。）
     *
     * @param sql
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> explain(String sql, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(ImpalaSql.explain(sql), statement);
        return getInfos(rs);
    }

    /**
     * 显示表的建表语句
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showCreateTable(String tableName,
                                        Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showCreateTable(tableName),
                statement);
        return getInfos(rs);
    }

    /**
     * 显示表的分区
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> showPartitions(String tableName,
                                                    Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showPartitions(tableName),
                statement);
        return getListInfos(rs);
    }

    /**
     * 查询数据库列表
     *
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showDatabases(Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showDatabases(), statement);
        return getInfos(rs);
    }

    /**
     * 查询default数据库的表列表
     *
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showTables(Statement... statement) throws SQLException,
            ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showTables(), statement);
        return getInfos(rs);
    }

    /**
     * 查询指定数据库的表列表
     *
     * @param databaseName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showTables(String databaseName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showTables(databaseName),
                statement);
        return getInfos(rs);
    }

    /**
     * 显示default数据库，满足匹配要求的列表
     *
     * @param pattern   如：*test*或test*或*test
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showTablesLike(String pattern, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showTablesLike(pattern),
                statement);
        return getInfos(rs);
    }

    /**
     * 显示指定数据库，满足匹配要求的列表
     *
     * @param databaseName
     * @param pattern      如：*test*或test*或*test
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<String> showTablesLike(String databaseName, String pattern,
                                       Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.showTablesLike(databaseName,
                pattern), statement);
        return getInfos(rs);
    }

    /**
     * 查询表所有数据
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> queryTableAllDatas(String tableName,
                                                        Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.queryTableAllDatas(tableName),
                statement);
        return getListInfos(rs);
    }

    /**
     * 查询表前几条数据
     *
     * @param tableName
     * @param limit
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> queryTableLimitN(String tableName,
                                                      int limit, Statement... statement) throws SQLException,
            ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql
                .queryTableLimitN(tableName, limit), statement);
        return getListInfos(rs);
    }

    /**
     * 查询表记录数
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int queryTableDataCount(String tableName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.queryTableDataCount(tableName),
                statement);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * 查询表简易信息
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> descTable(String tableName,
                                               Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.descTable(tableName), statement);
        return getListInfos(rs);
    }

    /**
     * 查询表详细信息
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> descFormatTable(String tableName,
                                                     Statement... statement) throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.descFormatTable(tableName),
                statement);
        return getListInfos(rs);
    }

    /**
     * 创建数据库
     *
     * @param databaseName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createDatabase(String databaseName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createDatabase(databaseName), statement);
    }

    /**
     * 拷贝表结构创建新表
     *
     * @param ifNotExists
     * @param oldTableName
     * @param newTableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createLikeTable(boolean ifNotExists, String oldTableName,
                               String newTableName, Statement... statement) throws SQLException,
            ClassNotFoundException {
        return executeUpdate(CommonSql.createLikeTable(ifNotExists,
                oldTableName, newTableName), statement);
    }

    /**
     * 字段分割符是\007的文本格式的简易版内表
     *
     * @param tableName
     * @param columns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createTable(String tableName, List<TableColumn> columns,
                           Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createTable(tableName, columns,
                ROW_FORMAT_DEFAULT_SEPARATOR, FILE_FORMAT_TEXTFILE), statement);
    }

    /**
     * 简易版建内表
     *
     * @param tableName
     * @param columns
     * @param separator
     * @param fileFormat
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createTable(String tableName, List<TableColumn> columns,
                           String separator, String fileFormat, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createTable(tableName, columns,
                separator, fileFormat), statement);
    }

    /**
     * 字段分割符是\007的文本格式的简易版分区内表
     *
     * @param tableName
     * @param columns
     * @param partitions
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createPartitionTable(String tableName,
                                    List<TableColumn> columns, List<TableColumn> partitions,
                                    Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(
                CommonSql.createPartitionTable(tableName, columns, partitions,
                        ROW_FORMAT_DEFAULT_SEPARATOR, FILE_FORMAT_TEXTFILE),
                statement);
    }

    /**
     * 简易版建内分区表
     *
     * @param tableName
     * @param columns
     * @param partitions
     * @param separator
     * @param fileFormat
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createPartitionTable(String tableName,
                                    List<TableColumn> columns, List<TableColumn> partitions,
                                    String separator, String fileFormat, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createPartitionTable(tableName, columns,
                partitions, separator, fileFormat), statement);
    }

    /**
     * 删除表
     *
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int dropTable(String tableName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.dropTable(tableName), statement);
    }

    /**
     * 删除表
     *
     * @param tableName
     * @param ifExists
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int dropTable(String tableName, boolean ifExists,
                         Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.dropTable(tableName, ifExists),
                statement);
    }

    /**
     * 删除数据库
     *
     * @param databaseName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int dropDatabase(String databaseName, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.dropDatabase(databaseName), statement);
    }

    /**
     * 查询表指定字段所有数据
     *
     * @param tableName
     * @param columns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> queryTableAllDatas(String tableName,
                                                        List<String> columns, Statement... statement) throws SQLException,
            ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.queryTableAllDatas(tableName,
                columns), statement);
        return getListInfos(rs);
    }

    /**
     * 查询表指定字段Top N
     *
     * @param tableName
     * @param limit
     * @param columns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Map<String, String>> queryTableLimitN(String tableName,
                                                      int limit, List<String> columns, Statement... statement)
            throws SQLException, ClassNotFoundException {
        ResultSet rs = executeQuery(CommonSql.queryTableLimitN(tableName,
                limit, columns), statement);
        return getListInfos(rs);
    }

    /**
     * 创建简单视图
     *
     * @param ifNotExists
     * @param viewName
     * @param tableName
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createView(boolean ifNotExists, String viewName,
                          String tableName, Statement... statement) throws SQLException,
            ClassNotFoundException {
        return executeUpdate(CommonSql.createView(ifNotExists, viewName,
                tableName), statement);
    }

    /**
     * 创建常规视图
     *
     * @param ifNotExists
     * @param viewName
     * @param viewColumns
     * @param tableName
     * @param tableColumns
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createView(boolean ifNotExists, String viewName,
                          List<String> viewColumns, String tableName,
                          List<String> tableColumns, Statement... statement)
            throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createView(ifNotExists, viewName,
                viewColumns, tableName, tableColumns), statement);
    }

    /**
     * 创建复杂视图
     *
     * @param ifNotExists
     * @param viewName
     * @param columns
     * @param selectStatement
     * @param statement
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int createView(boolean ifNotExists, String viewName,
                          List<String> columns, String selectStatement,
                          Statement... statement) throws SQLException, ClassNotFoundException {
        return executeUpdate(CommonSql.createView(ifNotExists, viewName,
                columns, selectStatement), statement);
    }

    protected List<Map<String, String>> getListInfos(ResultSet rs)
            throws SQLException {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        while (rs.next()) {
            Map<String, String> map = new LinkedHashMap<String, String>();
            for (int i = 1; i <= columnCount; i++) {
                map.put(rsmd.getColumnName(i), rs.getString(i));
            }
            list.add(map);
        }
        release(rs);
        return list;
    }

    protected List<String> getInfos(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        release(rs);
        return list;
    }
}
