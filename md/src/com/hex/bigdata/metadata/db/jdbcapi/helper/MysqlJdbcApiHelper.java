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
public class MysqlJdbcApiHelper extends BaseJdbcApiHelper {

    public MysqlJdbcApiHelper(Connection conn) throws SQLException {
        super(conn);
    }

    @Override
    public ResultSet getDatabasesResultSet(DatabaseMetaData dbmd) throws SQLException {
        return dbmd.getCatalogs();
    }

    @Override
    public ResultSet getTablesResultSet(DatabaseMetaData dbmd, String dbName) throws SQLException {
        checkDatabaseName(dbName);
        return dbmd.getTables(dbName, null, null, new String[]{"TABLE", "VIEW"});
    }

    @Override
    public ResultSet getColumnsResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException {
        checkDatabaseName(dbName);
        checkTableName(tbName);
        return dbmd.getColumns(dbName, null, tbName, null);
    }

    @Override
    public ResultSet getPrimaryKeysResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException {
        checkDatabaseName(dbName);
        checkTableName(tbName);
        return dbmd.getPrimaryKeys(dbName, null, tbName);
    }

    @Override
    public String getDbType() {
        return DBType.MYSQL.getValue();
    }
}
