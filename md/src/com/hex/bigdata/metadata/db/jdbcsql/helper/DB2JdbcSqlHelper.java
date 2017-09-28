package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class DB2JdbcSqlHelper extends BaseJdbcSqlHelper {
    public DB2JdbcSqlHelper(Connection conn) {
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
                + " TABNAME " + Constant.TB_NAME
                + " FROM SYSCAT.TABLES"
                + " WHERE TABSCHEMA = '" + dbName.toUpperCase() + "'"
                + " ORDER BY TABNAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT"
                + "  A.NAME " + Constant.COL_NAME
                + ", A.REMARKS " + Constant.COL_COMMENT
                + ", A.NULLS " + Constant.COL_IS_NULLABLE
                + ", A.TYPENAME " + Constant.COL_DATA_TYPE
                + ", A.LENGTH " + Constant.COL_DATA_PRECISION
                + ", A.LONGLENGTH " + Constant.COL_DATA_LENGTH
                + ", A.SCALE " + Constant.COL_DATA_SCALE
                + ", (A.COLNO + 1) " + Constant.COL_SEQ
                + ", A.KEYSEQ " + Constant.COL_PK_SEQ
                + " FROM SYSIBM.SYSCOLUMNS A, SYSCAT.TABLES B"
                + " WHERE A.TBNAME = '" + tbName.toUpperCase() + "'"
                + "  AND A.TBNAME = B.TABNAME"
                + "  AND B.TABSCHEMA = '" + dbName.toUpperCase() + "'"
                + "  AND A.TBCREATOR = '" + dbName.toUpperCase() + "'"
                + " ORDER BY A.COLNO";
    }

    @Override
    public String getDbType() {
        return DBType.DB2.getValue();
    }
}
