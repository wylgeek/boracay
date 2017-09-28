package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.jdbcinfo.JdbcInfoClient;
import com.hex.bigdata.metadata.db.jdbcsql.JdbcSqlClient;
import com.hex.bigdata.metadata.db.jdbcapi.JdbcApiClient;
import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-23.
 */
public class ClientFactory {
    public static BaseClient createMetaClient(AcquireType acquireType, DBType dbType, Connection conn) throws SQLException {
        if (AcquireType.JDBCSQL == acquireType) {
            return new JdbcSqlClient(conn, dbType);
        } else if (AcquireType.JDBCAPI == acquireType) {
            return new JdbcApiClient(conn, dbType);
        } else if (AcquireType.JDBCINFO == acquireType) {
            return new JdbcInfoClient(conn, dbType);
        }
        return null;
    }
}
