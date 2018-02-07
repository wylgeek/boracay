package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-21.
 */
public interface Helper {
    String getDbType();

    List<ColumnType> getColumnTypes() throws SQLException;

    List<Database> getDatabases() throws SQLException;

    List<Table> getTables(String dbName) throws SQLException;

    List<Column> getColumns(String dbName, String tbName) throws SQLException;

    Table getTable(String dbName, String tbName) throws SQLException;

    Database getDatabase(String dbName) throws SQLException;

    String getCurrentDbName() throws SQLException;
}
