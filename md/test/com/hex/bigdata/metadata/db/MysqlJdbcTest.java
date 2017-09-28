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
public class MysqlJdbcTest {

    private final DBType dbType = DBType.MYSQL;

    private Connection getConnection() {
        String host = "goupwith.mysql.rds.aliyuncs.com";
        String port = "3306";
        String dbName = "dcp";
        String username = "edh";
        String password = "edh159357";
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
