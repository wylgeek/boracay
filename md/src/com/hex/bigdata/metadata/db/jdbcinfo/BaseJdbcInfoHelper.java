package com.hex.bigdata.metadata.db.jdbcinfo;

import com.hex.bigdata.metadata.db.BaseHelper;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.Function;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by junjiem on 2016-6-29.
 */
public abstract class BaseJdbcInfoHelper extends BaseHelper implements JdbcInfoHelper {
    private Connection conn;

    public BaseJdbcInfoHelper(Connection conn) {
        super();
        this.conn = conn;
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

    @Override
    public List<Database> getDatabases() throws SQLException {
        String sql = this.getDatabasesSql();
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getDatabases(this.conn, sql);
    }

    private List<Database> getDatabases(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, Database>() {
            @Override
            public Database call(ResultSet rs) throws SQLException {
                Database db = new Database();
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                if (count == 1) {
                    db.setName(rs.getString(1));
                } else if (count >= 2) {
                    db.setName(rs.getString(1));
                    db.setComment(rs.getString(2));
                }
                return db;
            }
        });
    }

    @Override
    public List<Table> getTables(String dbName) throws SQLException {
        String sql = this.getTablesSql(dbName);
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getTables(this.conn, sql);
    }

    private List<Table> getTables(Connection conn, String sql) throws SQLException {
        return JdbcUtil.execSqlAndConvertRs2List(conn, sql, new Function<ResultSet, Table>() {
            @Override
            public Table call(ResultSet rs) throws SQLException {
                Table tb = new Table();
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                if (count == 1) {
                    tb.setName(rs.getString(1));
                } else if (count >= 2) {
                    tb.setName(rs.getString(1));
                    tb.setComment(rs.getString(2));
                }
                return tb;
            }
        });
    }

    @Override
    public List<Column> getColumns(String dbName, String tbName) throws SQLException {
        String sql = this.getColumnsSql(dbName, tbName);
        if (StringUtils.isEmpty(sql)) {
            return null;
        }
        return getColumns(this.conn, sql);
    }

    private List<Column> getColumns(Connection conn, String sql) throws SQLException {
        List<Column> list = new ArrayList<Column>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            for (int i = 1; i <= count; i++) {
                Column col = new Column();
                String columnName = rsmd.getColumnName(i);
                //String columnLabel = rsmd.getColumnLabel(i);
                //int columnType = rsmd.getColumnType(i);
                String columnTypeName = rsmd.getColumnTypeName(i);
//                int columnDisplaySize = 0;
//                try {
//                    columnDisplaySize = rsmd.getColumnDisplaySize(i);
//                } catch (SQLException e) {
//                    columnDisplaySize = 0;
//                }
                int precision = 0;
                try {
                    precision = rsmd.getPrecision(i);
                } catch (SQLException e) {
                    precision = 0;
                }
                int scale = 0;
                try {
                    scale = rsmd.getScale(i);
                } catch (SQLException e) {
                    scale = 0;
                }
//                System.out.println("columnName=" + columnName);
//               // System.out.println("columnLabel=" + columnLabel);
//               // System.out.println("columnType=" + columnType);
//                System.out.println("columnTypeName=" + columnTypeName);
//               // System.out.println("columnDisplaySize=" + columnDisplaySize);
//                System.out.println("precision=" + precision);
//                System.out.println("scale=" + scale);
//                System.out.println("---------------------------------------------");

                if (columnName.contains(".")) {
                    columnName = columnName.split("\\.")[1];
                }

                String colLength = "";
                if (scale == 0 && precision > 0) {
                    colLength = String.valueOf(precision);
                } else if (scale > 0 && precision > 0 && scale <= precision) {
                    colLength = String.valueOf(precision) + "," + String.valueOf(scale);
                }

                col.setSeq(i);
                col.setName(columnName);
                col.setType(columnTypeName);
                col.setLength(colLength);

                list.add(col);
            }
            return list;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

}
