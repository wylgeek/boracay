package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class OracleJdbcSqlHelper extends BaseJdbcSqlHelper {

    public OracleJdbcSqlHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getCurrentDbNameSql() {
        return "select database_name from v$database";
    }

    @Override
    public String getColumnTypeSql() {
        return null;
    }

    @Override
    public String getDatabasesSql() {
        return null;
    }

    @Override
    public String getTablesSql(String dbName) {
        return "SELECT"
                + "  A.TABLE_NAME " + Constant.TB_NAME // 表名称
                + ", B.COMMENTS " + Constant.TB_COMMENT // 表注释
                + " FROM SYS.ALL_TABLES A"
                + " LEFT JOIN SYS.ALL_TAB_COMMENTS B"
                + " ON A.TABLE_NAME = B.TABLE_NAME AND A.OWNER = B.OWNER"
                + " WHERE A.OWNER = '" + dbName.toUpperCase() + "'"
                + " ORDER BY A.TABLE_NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT DISTINCT"
                + "  ATC.COLUMN_ID " + Constant.COL_SEQ // 字段位置
                + ", ATC.COLUMN_NAME " + Constant.COL_NAME // 字段名称
                + ", ATC.DATA_TYPE " + Constant.COL_DATA_TYPE // 数据类型
                + ", ATC.DATA_LENGTH " + Constant.COL_DATA_LENGTH // 数据长度
                + ", ATC.DATA_PRECISION " + Constant.COL_DATA_PRECISION // 数据精度
                + ", ATC.DATA_SCALE " + Constant.COL_DATA_SCALE // 数据比例
                + ", ATC.NULLABLE " + Constant.COL_IS_NULLABLE // 是否允许为空 N:不为空 Y:可为空
                + ", ACC.COMMENTS " + Constant.COL_COMMENT // 字段注释
                + ", ACCM.POSITION " + Constant.COL_PK_SEQ // 主键位置
                + " FROM SYS.ALL_TAB_COLUMNS ATC"
                + " LEFT JOIN SYS.ALL_COL_COMMENTS ACC"
                + "  ON ATC.OWNER = ACC.OWNER"
                + "  AND ATC.TABLE_NAME = ACC.TABLE_NAME"
                + "  AND ATC.COLUMN_NAME = ACC.COLUMN_NAME"
                + " LEFT JOIN SYS.ALL_CONS_COLUMNS ACCM"
                + "  ON ATC.OWNER = ACCM.OWNER"
                + "  AND ATC.TABLE_NAME = ACCM.TABLE_NAME"
                + "  AND ATC.COLUMN_NAME = ACCM.COLUMN_NAME"
                + "  AND ACCM.POSITION IS NOT NULL"
                + " WHERE ATC.OWNER = '" + dbName.toUpperCase() + "'"
                + "  AND ATC.TABLE_NAME = '" + tbName.toUpperCase() + "'"
                + " ORDER BY ATC.COLUMN_ID";
    }

    @Override
    public String getDbType() {
        return DBType.ORACLE.getValue();
    }
}
