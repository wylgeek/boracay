package com.hex.bigdata.metadata.db.jdbcsql;

import com.hex.bigdata.metadata.db.Helper;

/**
 * Created by junjiem on 2016-6-21.
 */
public interface JdbcSqlHelper extends Helper {
    String getCurrentDbNameSql();

    String getColumnTypeSql();

    String getDatabasesSql();

    String getTablesSql(String dbName);

    String getColumnsSql(String dbName, String tbName);
}
