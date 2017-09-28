package com.hex.bigdata.metadata.db.jdbcapi.helper;

import com.hex.bigdata.metadata.db.jdbcapi.BaseJdbcApiHelper;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-21.
 */
public class OracleJdbcApiHelper extends BaseJdbcApiHelper {

    public OracleJdbcApiHelper(Connection conn) throws SQLException {
        super(conn);
    }

    /**
     * 实际拿到的是Oracle Users List
     *
     * @param dbmd
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet getDatabasesResultSet(DatabaseMetaData dbmd) throws SQLException {
        //return dbmd.getCatalogs(); // 拿到的都是[]
        return dbmd.getSchemas(); // 这个拿到的是Oracle User List
    }

    /**
     * 这里的dbName是Oracle User Name
     *
     * @param dbmd
     * @param dbName Oracle用户名称
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet getTablesResultSet(DatabaseMetaData dbmd, String dbName) throws SQLException {
        return dbmd.getTables(null, dbName, null, new String[]{"TABLE", "VIEW"});
    }

    /**
     * 这里的dbName是Oracle User Name
     *
     * @param dbmd
     * @param dbName Oracle用户名称
     * @param tbName
     * @return
     * @throws SQLException
     */
    @Override
    public ResultSet getColumnsResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException {
        return dbmd.getColumns(null, dbName, tbName, null);
    }

    @Override
    public ResultSet getPrimaryKeysResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException {
        return dbmd.getPrimaryKeys(null, dbName, tbName);
    }

    @Override
    public String getDbType() {
        return DBType.ORACLE.getValue();
    }
}
