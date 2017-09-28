package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-21.
 */
public abstract class BaseHelper implements Helper {

    public Database getDatabase(String dbName) throws SQLException {
        List<Database> databases = getDatabases();
        for (Database database : databases) {
            if (dbName.equalsIgnoreCase(database.getName())) {
                List<Table> tables = getTables(dbName);
                for (Table table : tables) {
                    List<Column> columns = getColumns(dbName, table.getName());
                    table.setColumns(columns);
                    database.append(table);
                }
                return database;
            }
        }
        return null;
    }

    public Table getTable(String dbName, String tbName) throws SQLException {
        List<Table> tables = getTables(dbName);
        for (Table table : tables) {
            if (tbName.equalsIgnoreCase(table.getName())) {
                List<Column> columns = getColumns(dbName, tbName);
                table.setColumns(columns);
                return table;
            }
        }
        return null;
    }
}
