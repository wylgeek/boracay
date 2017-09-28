package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class TDJdbcSqlHelper extends BaseJdbcSqlHelper {
    public TDJdbcSqlHelper(Connection conn) {
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
                + "  TABLENAME " + Constant.TB_NAME
                + ", COMMENTSTRING " + Constant.TB_COMMENT
                + " FROM DBC.TABLES"
                + " WHERE DATABASENAME='" + dbName.toUpperCase() + "'"
                + " ORDER BY TABLENAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT"
                + "  A.COLUMNID " + Constant.COL_SEQ
                + ", A.COLUMNNAME " + Constant.COL_NAME
                + ", A.COLUMNTITLE " + Constant.COL_COMMENT
                + ", A.COLUMNTYPE " + Constant.COL_DATA_TYPE
                + ", A.COLUMNLENGTH " + Constant.COL_DATA_LENGTH
                + ", A.DECIMALTOTALDIGITS " + Constant.COL_DATA_PRECISION
                + ", A.DECIMALFRACTIONALDIGITS " + Constant.COL_DATA_SCALE
                + ", IF(A.COMPRESSIBLE=='N', 0, 1) " + Constant.COL_PK_SEQ
                + " FROM DBC.COLUMNS A"
                + " LEFT JOIN MPETL_SIT.LOAD_TABLE_INFO C"
                + "  ON C.ETL_JOB = A.TABLENAME"
                + " WHERE A.DATABASENAME = '" + dbName.toUpperCase() + "'"
                + "  AND A.TABLENAME = '" + tbName + "'"
                + " ORDER BY A.COLUMNID";
    }

    @Override
    public String getDbType() {
        return DBType.TD.getValue();
    }
}
