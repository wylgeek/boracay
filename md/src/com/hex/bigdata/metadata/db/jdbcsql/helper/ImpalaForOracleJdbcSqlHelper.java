package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class ImpalaForOracleJdbcSqlHelper extends BaseJdbcSqlHelper {
    public ImpalaForOracleJdbcSqlHelper(Connection conn) {
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
        return "SELECT"
                + " NAME " + Constant.DB_NAME
                + " FROM DBS"
                + " ORDER BY NAME";
    }

    @Override
    public String getTablesSql(String dbName) {
        return "SELECT"
                + " TAB.TBL_NAME " + Constant.TB_NAME
                + " FROM TBLS TAB, DBS DB"
                + " WHERE TAB.DB_ID = DB.DB_ID"
                + "  AND DB.NAME = '" + dbName.toLowerCase() + "'"
                + " ORDER BY TAB.TBL_NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT"
                + "  COL.\"COMMENT\" " + Constant.COL_COMMENT
                + ", COL.TYPE_NAME " + Constant.COL_DATA_TYPE
                + ", COL.COLUMN_NAME " + Constant.COL_NAME
                + ", COL.INTEGER_IDX + 1 " + Constant.COL_SEQ
                + ", 0 " + Constant.COL_PF_SEQ
                + " FROM COLUMNS_V2 COL, DBS DB, TBLS TAB, SDS SDS"
                + " WHERE COL.CD_ID = SDS.CD_ID"
                + "  AND TAB.SD_ID = SDS.SD_ID"
                + "  AND TAB.DB_ID = DB.DB_ID"
                + "  AND DB.NAME = '" + dbName.toLowerCase() + "'"
                + "  AND TAB.TBL_NAME = '" + tbName.toLowerCase() + "'"
                + " UNION ALL "
                + " SELECT"
                + "  PF.PKEY_COMMENT " + Constant.COL_COMMENT
                + ", PF.PKEY_TYPE " + Constant.COL_DATA_TYPE
                + ", PF.PKEY_NAME " + Constant.COL_NAME
                + ", 0 " + Constant.COL_SEQ
                + ", PF.INTEGER_IDX + 1 " + Constant.COL_PF_SEQ
                + " FROM DBS DB, TBLS TAB, PARTITION_KEYS PF"
                + " WHERE TAB.DB_ID = DB.DB_ID"
                + "  AND PF.TBL_ID = TAB.TBL_ID"
                + "  AND DB.NAME = '" + dbName.toLowerCase() + "'"
                + "  AND TAB.TBL_NAME = '" + tbName.toLowerCase() + "'";
    }

    @Override
    public String getDbType() {
        return DBType.IMPALA_FOR_ORACLE.getValue();
    }
}
