package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class AS400JdbcSqlHelper extends BaseJdbcSqlHelper {

    public AS400JdbcSqlHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getCurrentDbNameSql() {
        return null;
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
                + "  TABLE_NAME " + Constant.TB_NAME
                + ", TABLE_TEXT " + Constant.TB_COMMENT
                + " FROM QSYS2.SYSTABLES"
                + " WHERE TABLE_OWNER = '" + dbName.toUpperCase() + "'"
                + " ORDER BY TABLE_NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT DISTINCT"
                + "  T1.COLUMN_NAME " + Constant.COL_NAME
                + ", T1.ORDINAL_POSITION " + Constant.COL_SEQ
                + ", T1.DATA_TYPE " + Constant.COL_DATA_TYPE
                + ", T1.CHARACTER_MAXIMUM_LENGTH " + Constant.COL_DATA_LENGTH
                + ", T1.NUMERIC_PRECISION " + Constant.COL_DATA_PRECISION
                + ", T1.NUMERIC_SCALE " + Constant.COL_DATA_SCALE
                + ", T1.IS_NULLABLE " + Constant.COL_IS_NULLABLE
                + ", T2.COLUMN_TEXT " + Constant.COL_COMMENT
                + " FROM QSYS2.COLUMNS T1"
                + " LEFT JOIN QSYS2.SYSCOLUMNS2 T2"
                + " ON T1.TABLE_SCHEMA = T2.TABLE_SCHEMA"
                + "  AND T1.TABLE_NAME = T2.TABLE_NAME"
                + "  AND T1.COLUMN_NAME = T2.COLUMN_NAME"
                + " WHERE T1.TABLE_NAME = '" + tbName.toUpperCase() + "'"
                + " AND T1.TABLE_SCHEMA = '" + dbName.toUpperCase() + "'"
                + " ORDER BY T1.ORDINAL_POSITION";
    }

    @Override
    public String getDbType() {
        return DBType.AS400.getValue();
    }

}
