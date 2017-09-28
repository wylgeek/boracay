package com.hex.bigdata.metadata.db.jdbcsql.helper;

import com.hex.bigdata.metadata.db.jdbcsql.BaseJdbcSqlHelper;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;

/**
 * Created by junjiem on 2016-6-20.
 */
public class SqlServerJdbcSqlHelper extends BaseJdbcSqlHelper {
    public SqlServerJdbcSqlHelper(Connection conn) {
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
                + " NAME " + Constant.TB_NAME
                + " FROM SYSOBJECTS"
                + " WHERE XTYPE = 'U'"
                + " ORDER BY NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "SELECT DISTINCT "
                + "  SC.NAME " + Constant.COL_NAME
                + ", SC.COLUMN_ID " + Constant.COL_SEQ
                + ", SS.NAME " + Constant.COL_DATA_TYPE
                + ", SC.MAX_LENGTH " + Constant.COL_DATA_LENGTH
                + ", SC.PRECISION " + Constant.COL_DATA_PRECISION
                + ", SC.SCALE " + Constant.COL_DATA_SCALE
                + ", SC.IS_NULLABLE " + Constant.COL_IS_NULLABLE
                + ", IF(SC.IS_IDENTITY==1, 0, 1) " + Constant.COL_PK_SEQ
                + " FROM SYS.TABLES ST, SYS.COLUMNS SC, SYS.TYPES SS"
                + " WHERE ST.NAME = '" + tbName + "'"
                + "  AND ST.OBJECT_ID = SC.OBJECT_ID"
                + "  AND SC.SYSTEM_TYPE_ID = SS.SYSTEM_TYPE_ID"
                + "  AND SS.NAME <> 'sysname'"
                + " ORDER BY SC.COLUMN_ID";
    }

    @Override
    public String getDbType() {
        return DBType.SQLSERVER.getValue();
    }
}
