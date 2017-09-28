package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-23.
 */
public interface Client {
    String getCurrentDbName() throws SQLException;

    Helper createHelper(DBType dbType) throws SQLException;

    Client use(String dbName);

    List<Database> getDatabases() throws SQLException;

    List<Column> getColumns(String dbName, String tbName) throws SQLException;

    List<Column> getColumns(String tbName) throws SQLException;

    List<Table> getTables(String dbName) throws SQLException;

    List<Table> getTables() throws SQLException;

    Table getTable(String dbName, String tbName) throws SQLException;

    Table getTable(String tbName) throws SQLException;

    public Database getDatabase(String dbName) throws SQLException;

    public Database getDatabase() throws SQLException;

    public List<ColumnType> getColumnTypes() throws SQLException;

    void close();

}
