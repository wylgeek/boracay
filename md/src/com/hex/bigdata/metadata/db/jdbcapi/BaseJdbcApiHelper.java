package com.hex.bigdata.metadata.db.jdbcapi;

import com.hex.bigdata.metadata.db.BaseHelper;
import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.Function;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import com.hex.bigdata.metadata.db.util.ParameterUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.List;

/**
 * Created by junjiem on 2016-6-21.
 */
public abstract class BaseJdbcApiHelper extends BaseHelper implements JdbcApiHelper {

    private DatabaseMetaData dbmd;

    public BaseJdbcApiHelper(Connection conn) throws SQLException {
        super();
        ParameterUtil.notNull(conn, "Database Connection cannot be null! dbType=" + this.getDbType());
        this.dbmd = conn.getMetaData();
        ParameterUtil.notNull(this.dbmd, "It is not support jdbc metadata api to get Metadata info! dbType=" + this.getDbType());
    }

    public String getCurrentDbName() throws SQLException {
        return null;
    }

    public List<ColumnType> getColumnTypes() throws SQLException {
        ResultSet rs = this.dbmd.getTypeInfo();
        ParameterUtil.notNull(rs, "It is not support jdbc metadata api to get ColumnType meta info! dbType=" + this.getDbType());
        return JdbcUtil.convertRs2List(rs, new Function<ResultSet, ColumnType>() {
            @Override
            public ColumnType call(ResultSet rs) throws SQLException {
                ColumnType colType = new ColumnType();
                String typeName = null;
                int precision = 0;
                String createParams = null;
                int minScale = 0;
                int maxScale = 0;
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (Constant.TYPE_NAME.equals(columnName)) {
                        typeName = rs.getString(i);
                    } else if (Constant.PRECISION.equals(columnName)) {
                        try {
                            precision = rs.getInt(i);
                        } catch (SQLException e) {
                            precision = 0;
                        }
                    } else if (Constant.CREATE_PARAMS.equals(columnName)) {
                        createParams = rs.getString(i);
                    } else if (Constant.MINIMUM_SCALE.equals(columnName)) {
                        try {
                            minScale = rs.getInt(i);
                        } catch (SQLException e) {
                            minScale = 0;
                        }
                    } else if (Constant.MINIMUM_SCALE.equals(columnName)) {
                        try {
                            maxScale = rs.getInt(i);
                        } catch (SQLException e) {
                            maxScale = 0;
                        }
                    }
                }
                if (StringUtils.isNotEmpty(typeName)) {
                    colType.setName(typeName);
                }
                if (precision != 0) {
                    colType.setPrecision(precision);
                }
                if (StringUtils.isNotEmpty(createParams)) {
                    colType.setCreateParams(createParams);
                }
                if (minScale != 0) {
                    colType.setMinScale(minScale);
                }
                if (minScale != 0) {
                    colType.setMaxScale(maxScale);
                }
                return colType;
            }
        });
    }

    @Override
    public List<Database> getDatabases() throws SQLException {
        ResultSet rs = this.getDatabasesResultSet(this.dbmd);
        ParameterUtil.notNull(rs, "It is not support jdbc metadata api to get Database meta info! dbType=" + this.getDbType());
        return JdbcUtil.convertRs2List(rs, new Function<ResultSet, Database>() {
            @Override
            public Database call(ResultSet rs) throws SQLException {
                Database db = new Database();
                String dbName = null;
                String dbComment = null;
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (Constant.TABLE_CAT.equals(columnName)) {
                        dbName = rs.getString(i);
                    } else if (Constant.TABLE_CAT_COMMENT.equals(columnName)) {
                        dbComment = rs.getString(i);
                    } else if (Constant.TABLE_SCHEM.equals(columnName)) {
                        dbName = rs.getString(i);
                    } else if (Constant.TABLE_SCHEM_COMMENT.equals(columnName)) {
                        dbComment = rs.getString(i);
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

    @Override
    public List<Table> getTables(String dbName) throws SQLException {
        ResultSet rs = this.getTablesResultSet(this.dbmd, dbName);
        ParameterUtil.notNull(rs, "It is not support jdbc metadata api to get Tables meta info! dbType=" + this.getDbType());
        return JdbcUtil.convertRs2List(rs, new Function<ResultSet, Table>() {
            @Override
            public Table call(ResultSet rs) throws SQLException {
                Table tb = new Table();
                String tableName = null;
                String tableComment = null;
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (Constant.TABLE_NAME.equals(columnName)) {
                        tableName = rs.getString(i);
                    } else if (Constant.TABLE_REMARKS.equals(columnName)) {
                        tableComment = rs.getString(i);
                    }
                }
                if (StringUtils.isNotEmpty(tableName)) {
                    tb.setName(tableName);
                }
                if (tableComment != null) {
                    tb.setComment(tableComment);
                }
                // 判断表名是否含有非法字符，有则踢除
                if (tb.getName().matches("^[a-zA-Z0-9_-]+$")) {
                    return tb;
                }
                return null;
            }
        });
    }

    @Override
    public List<Column> getColumns(String dbName, String tbName) throws SQLException {
        ResultSet rs = this.getColumnsResultSet(this.dbmd, dbName, tbName);
        ParameterUtil.notNull(rs, "it is not support jdbc metadata api to get Columns meta info! dbType=" + this.getDbType());
        // 字段集合
        List<Column> columnList = JdbcUtil.convertRs2List(rs, new Function<ResultSet, Column>() {
            @Override
            public Column call(ResultSet rs) throws SQLException {
                Column col = new Column();
                String colName = null;
                String colComment = null;
                String colType = null;
                String isNullable = null;
                int colSeq = 0;
                int length = 0;
                int scale = 0;
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (Constant.COLUMN_NAME.equals(columnName)) {
                        colName = rs.getString(i);
                    } else if (Constant.REMARKS.equals(columnName)) {
                        colComment = rs.getString(i);
                    } else if (Constant.TYPE_NAME.equals(columnName)) {
                        colType = rs.getString(i);
                    } else if (Constant.ORDINAL_POSITION.equals(columnName)) {
                        colSeq = rs.getInt(i);
                    } else if (Constant.DECIMAL_DIGITS.equals(columnName)) {
                        scale = rs.getInt(i);
                    } else if (Constant.COLUMN_SIZE.equals(columnName)) {
                        try {
                            length = rs.getInt(i);
                        } catch (SQLException e) {
                            //e.printStackTrace();
                            length = 0;
                        }
                    } else if (Constant.IS_NULLABLE.equals(columnName)) {
                        isNullable = rs.getString(i);
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
                    if ("YES".equals(isNullable)) {
                        nullable = true;
                    } else if ("NO".equals(isNullable)) {
                        nullable = false;
                    }
                    col.setNullable(nullable);
                }
                // 字段长度
                String colLength = "";
                if (scale == 0 && length > 0) {
                    colLength = String.valueOf(length);
                } else if (scale > 0 && length > 0 && scale <= length) {
                    colLength = String.valueOf(length) + "," + String.valueOf(scale);
                }
                col.setLength(colLength);
                return col;
            }
        });

        rs = this.getPrimaryKeysResultSet(dbmd, dbName, tbName);
        ParameterUtil.notNull(rs, "It is not support jdbc metadata api to get PrimaryKeys meta info! dbType=" + this.getDbType());
        // 主键字段集合
        List<Column> pkList = JdbcUtil.convertRs2List(rs, new Function<ResultSet, Column>() {
            @Override
            public Column call(ResultSet rs) throws SQLException {
                Column col = new Column();
                String colName = null;
                int keySeq = 0;
                ResultSetMetaData rsmd = rs.getMetaData();
                int count = rsmd.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (Constant.COLUMN_NAME.equals(columnName)) {
                        colName = rs.getString(i);
                    } else if (Constant.KEY_SEQ.equals(columnName)) {
                        keySeq = rs.getInt(i);
                    }
                    // 字段名称
                    if (StringUtils.isNotEmpty(colName)) {
                        col.setName(colName);
                    }
                    // 主键位置
                    if (keySeq > 0) {
                        col.setPrimaryKeyN(keySeq);
                    }
                }
                return col;
            }
        });

        // 合并
        for (Column pkCol : pkList) {
            for (Column col : columnList) {
                if (pkCol.getName().equals(col.getName())) {
                    col.setPrimaryKeyN(pkCol.getPrimaryKeyN());
                }
            }
        }

        // 分区字段处理
        int size = columnList.size();
        if (size > 0) {
            int index = 0;
            for (int i = 0; i < size; i++) {
                Column col = columnList.get(i);
                int seq = col.getSeq();
                if (seq <= index) {
                    col.setPartitionFieldN(seq);
                }
                index = col.getSeq();
                col.setSeq(i + 1);
            }
        }

        return columnList;
    }
}
