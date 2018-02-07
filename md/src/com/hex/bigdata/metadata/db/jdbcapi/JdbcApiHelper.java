package com.hex.bigdata.metadata.db.jdbcapi;

import com.hex.bigdata.metadata.db.Helper;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-21.
 */
public interface JdbcApiHelper extends Helper {

     ResultSet getDatabasesResultSet(DatabaseMetaData dbmd) throws SQLException;

     ResultSet getTablesResultSet(DatabaseMetaData dbmd, String dbName) throws SQLException;

     ResultSet getColumnsResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException;

     ResultSet getPrimaryKeysResultSet(DatabaseMetaData dbmd, String dbName, String tbName) throws SQLException;
}
