package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class MysqlJdbcSqlHelper extends BaseJdbcSqlHelper {

    public MysqlJdbcSqlHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getCurrentDbNameSql() {
        return "select database()";
    }

    @Override
    public String getColumnTypeSql() {
        return null;
    }

    @Override
    public String getDatabasesSql() {
        return "SELECT"
                + " SCHEMA_NAME " + Constant.DB_NAME // 数据库名称
                + " FROM INFORMATION_SCHEMA.SCHEMATA"
                + " ORDER BY SCHEMA_NAME";
    }

    @Override
    public String getTablesSql(String dbName) {
        return "SELECT"
                + "  TABLE_NAME " + Constant.TB_NAME // 表名称
                + ", TABLE_COMMENT " + Constant.TB_COMMENT // 表注释
                + " FROM INFORMATION_SCHEMA.TABLES"
                + " WHERE TABLE_SCHEMA = '" + dbName.toLowerCase() + "'"
                + " ORDER BY TABLE_NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT DISTINCT"
                + "  A.ORDINAL_POSITION " + Constant.COL_SEQ // 字段位置
                + ", A.COLUMN_NAME " + Constant.COL_NAME // 字段名称
                + ", A.DATA_TYPE " + Constant.COL_DATA_TYPE // 数据类型
                + ", A.CHARACTER_MAXIMUM_LENGTH " + Constant.COL_DATA_LENGTH // 数据长度
                + ", A.NUMERIC_PRECISION " + Constant.COL_DATA_PRECISION // 数据精度
                + ", A.NUMERIC_SCALE " + Constant.COL_DATA_SCALE // 数据比例
                + ", A.IS_NULLABLE " + Constant.COL_IS_NULLABLE // 是否允许为空 N:不为空 Y:可为空
                + ", A.COLUMN_COMMENT " + Constant.COL_COMMENT // 字段注释
                + ", B.SEQ_IN_INDEX " + Constant.COL_PK_SEQ // 主键位置
                + " FROM INFORMATION_SCHEMA.COLUMNS A"
                + " LEFT JOIN INFORMATION_SCHEMA.STATISTICS B"
                + "  ON A.TABLE_SCHEMA = B.TABLE_SCHEMA"
                + "  AND A.TABLE_NAME = B.TABLE_NAME"
                + "  AND A.COLUMN_NAME = B.COLUMN_NAME"
                + "  AND B.INDEX_NAME='PRIMARY'"
                + " WHERE A.TABLE_SCHEMA='" + dbName.toLowerCase() + "'"
                + "  AND A.TABLE_NAME='" + tbName.toLowerCase() + "'"
                + " ORDER BY A.ORDINAL_POSITION";
    }

    @Override
    public String getDbType() {
        return DBType.MYSQL.getValue();
    }
}
