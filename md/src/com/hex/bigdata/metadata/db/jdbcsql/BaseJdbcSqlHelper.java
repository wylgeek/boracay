package com.hex.bigdata.metadata.db.jdbcsql;

import com.hex.bigdata.metadata.db.BaseHelper;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.Function;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.List;

/**
 * Created by junjiem on 2016-6-20.
 */
public abstract class BaseJdbcSqlHelper extends BaseHelper implements JdbcSqlHelper {

    private Connection conn;

    public BaseJdbcSqlHelper(Connection conn) {
        super();
        this.conn = conn;
    }

    @Override
    public List<ColumnType> getColumnTypes() throws SQLException {
        String sql = this.getColumnTypeSql();
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        // TODO ...
        return null;
    }

    public String getCurrentDbName() throws SQLException {
        String sql = this.getCurrentDbNameSql();
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getCurrentDbName(this.conn, sql);
    }

    private String getCurrentDbName(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, String>() {
            @Override
            public String call(ResultSet rs) throws SQLException {
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                if (count >= 1) {
                    return rs.getString(1);
                }
                return null;
            }
        }).get(0);
    }

    public List<Database> getDatabases() throws SQLException {
        String sql = this.getDatabasesSql();
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getDatabases(this.conn, sql);
    }

    public List<Table> getTables(String dbName) throws SQLException {
        String sql = this.getTablesSql(dbName);
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getTables(this.conn, sql);
    }

    public List<Column> getColumns(String dbName, String tbName) throws SQLException {
        String sql = this.getColumnsSql(dbName, tbName);
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getColumns(this.conn, sql);
    }

    private List<Database> getDatabases(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, Database>() {
            @Override
            public Database call(ResultSet rs) throws SQLException {
                Database db = new Database();
                String dbName = null;
                String dbComment = null;
                ResultSetMetaData metadata = rs.getMetaData();
                int count = metadata.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnLabel = metadata.getColumnLabel(i);
                    if (Constant.DB_NAME.equals(columnLabel)) {
                        dbName = rs.getString(Constant.DB_NAME);
                    } else if (Constant.DB_COMMENT.equals(columnLabel)) {
                        dbComment = rs.getString(Constant.DB_COMMENT);
                    }
                }
                if (StringUtils.isNotEmpty(dbName)) {
                    db.setName(dbName);
                }
                if (dbComment != null) {
                    db.setComment(dbComment);
                }
                return db;
            }
        });
    }

    private List<Table> getTables(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, Table>() {
            @Override
            public Table call(ResultSet rs) throws SQLException {
                Table tb = new Table();
                String tableName = null;
                String tableComment = null;
                ResultSetMetaData metadata = rs.getMetaData();
                int count = metadata.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnLabel = metadata.getColumnLabel(i);
                    if (Constant.TB_NAME.equals(columnLabel)) {
                        tableName = rs.getString(Constant.TB_NAME);
                    } else if (Constant.TB_COMMENT.equals(columnLabel)) {
                        tableComment = rs.getString(Constant.TB_COMMENT);
                    }
                }
                if (StringUtils.isNotEmpty(tableName)) {
                    tb.setName(tableName);
                }
                if (tableComment != null) {
                    tb.setComment(tableComment);
                }
                return tb;
            }
        });
    }

    private List<Column> getColumns(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, Column>() {
            @Override
            public Column call(ResultSet rs) throws SQLException {
                Column col = new Column();
                String colName = null;
                String colComment = null;
                String colType = null;
                String isNullable = null;
                int colSeq = 0;
                int colPkSeq = 0;
                int colPfSeq = 0;
                int length = 0;
                int precision = 0;
                int scale = 0;
                ResultSetMetaData metadata = rs.getMetaData();
                int count = metadata.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnLabel = metadata.getColumnLabel(i);
                    if (Constant.COL_SEQ.equals(columnLabel)) {
                        colSeq = rs.getInt(Constant.COL_SEQ);
                    } else if (Constant.COL_NAME.equals(columnLabel)) {
                        colName = rs.getString(Constant.COL_NAME);
                    } else if (Constant.COL_COMMENT.equals(columnLabel)) {
                        colComment = rs.getString(Constant.COL_COMMENT);
                    } else if (Constant.COL_DATA_TYPE.equals(columnLabel)) {
                        colType = rs.getString(Constant.COL_DATA_TYPE);
                    } else if (Constant.COL_IS_NULLABLE.equals(columnLabel)) {
                        isNullable = rs.getString(Constant.COL_IS_NULLABLE);
                    } else if (Constant.COL_PK_SEQ.equals(columnLabel)) {
                        colPkSeq = rs.getInt(Constant.COL_PK_SEQ);
                    } else if (Constant.COL_PK_SEQ.equals(columnLabel)) {
                        colPfSeq = rs.getInt(Constant.COL_PF_SEQ);
                    } else if (Constant.COL_DATA_LENGTH.equals(columnLabel)) {
                        try {
                            length = rs.getInt(Constant.COL_DATA_LENGTH);
                        } catch (SQLException e) {
                            //e.printStackTrace();
                            length = 0;
                        }
                    } else if (Constant.COL_DATA_PRECISION.equals(columnLabel)) {
                        precision = rs.getInt(Constant.COL_DATA_PRECISION);
                    } else if (Constant.COL_DATA_SCALE.equals(columnLabel)) {
                        scale = rs.getInt(Constant.COL_DATA_SCALE);
                    }
                }
                // 字段位置
                if (colSeq != 0) {
                    col.setSeq(colSeq);
                }
                // 字段名称
                if (StringUtils.isNotEmpty(colName)) {
                    col.setName(colName);
                }
                // 字段注释
                if (colComment != null) {
                    col.setComment(colComment);
                }
                // 字段类型
                if (StringUtils.isNotEmpty(colType)) {
                    col.setType(colType);
                }
                // 是否允许为空
                if (StringUtils.isNotEmpty(isNullable)) {
                    boolean nullable = false;
                    if ("Y".equals(isNullable) || "YES".equals(isNullable)) {
                        nullable = true;
                    } else if ("N".equals(isNullable) || "NO".equals(isNullable)) {
                        nullable = false;
                    }
                    col.setNullable(nullable);
                }
                // 字段长度
                String colLength = "";
                if (precision == 0 && scale == 0 && length > 0) {
                    colLength = String.valueOf(length);
                } else if (precision > 0 && scale >= 0 && scale <= precision) {
                    colLength = String.valueOf(precision) + "," + String.valueOf(scale);
                } else if (precision == 0 && scale > 0 && length > 0 && scale <= length) {
                    colLength = String.valueOf(scale);
                }
                col.setLength(colLength);
                // 主键字段位置
                if (colPkSeq > 0) {
                    col.setPrimaryKeyN(colPkSeq);
                }
                // 分区字段位置
                if (colPfSeq > 0) {
                    col.setPartitionFieldN(colPfSeq);
                }
                return col;
            }
        });
    }

}
