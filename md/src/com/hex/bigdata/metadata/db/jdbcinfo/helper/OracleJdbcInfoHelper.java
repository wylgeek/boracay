package com.hex.bigdata.metadata.db.jdbcinfo.helper;

import com.hex.bigdata.metadata.db.jdbcinfo.BaseJdbcInfoHelper;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.util.Constant;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-29.
 */
public class OracleJdbcInfoHelper extends BaseJdbcInfoHelper {

    public OracleJdbcInfoHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getCurrentDbNameSql() {
        // 无法获取当前数据库名称
        return null;
    }

    /**
     * 实际拿到的是Oracle Users List
     *
     * @return
     */
    @Override
    public String getDatabasesSql() {
        return "select username from all_users";
    }

    @Override
    public String getTablesSql(String dbName) {
        return "SELECT"
                + "  A.TABLE_NAME "
                + ", B.COMMENTS "
                + " FROM SYS.ALL_TABLES A"
                + " LEFT JOIN SYS.ALL_TAB_COMMENTS B"
                + " ON A.TABLE_NAME = B.TABLE_NAME AND A.OWNER = B.OWNER"
                + " WHERE A.OWNER = '" + dbName.toUpperCase() + "'"
                + " ORDER BY A.TABLE_NAME";
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return "select * from " + tbName + " where 1=0";
    }

    @Override
    public String getDbType() {
        return DBType.ORACLE.getValue();
    }

    @Override
    public List<ColumnType> getColumnTypes() throws SQLException {
        return null;
    }
}
