package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.util.AcquireType;
import com.hex.bigdata.metadata.db.util.DBType;
import com.hex.bigdata.metadata.db.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by JunjieM on 2017-9-7.
 */
public class OracleJdbcTest {
    private final DBType dbType = DBType.ORACLE;

    private Connection getConnection() {
        String host = "dev.goupwith.com";
        String port = "1521";
        String dbName = "zctggl2";
        String username = "udsp";
        String password = "Udsp159357";
        Connection conn = JdbcUtil.newConnection(dbType, host, port, dbName, username, password);
        return conn;
    }

    @Test
    public void jdcbApiTest() throws SQLException {
        JdbcTestUtil.print(ClientFactory.createMetaClient(AcquireType.JDBCAPI, dbType, getConnection()));
    }

    @Test
    public void jdcbInfoTest() throws SQLException {
        JdbcTestUtil.print(ClientFactory.createMetaClient(AcquireType.JDBCINFO, dbType, getConnection()));
    }

    @Test
    public void jdcbSqlTest() throws SQLException {
        JdbcTestUtil.print(ClientFactory.createMetaClient(AcquireType.JDBCSQL, dbType, getConnection()));
    }
}
