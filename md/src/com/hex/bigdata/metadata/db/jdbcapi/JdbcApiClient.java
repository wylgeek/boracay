package com.hex.bigdata.metadata.db.jdbcapi;

import com.hex.bigdata.metadata.db.BaseClient;
import com.hex.bigdata.metadata.db.jdbcapi.helper.*;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-21.
 */
public class JdbcApiClient extends BaseClient {

    public JdbcApiClient(Connection conn, DBType dbType) throws SQLException {
        super(conn, dbType);
    }

    public BaseJdbcApiHelper createHelper(DBType dbType) throws SQLException {
        BaseJdbcApiHelper helper = null;
        if (DBType.MYSQL == dbType) {
            helper = new MysqlJdbcApiHelper(this.conn);
        } else if (DBType.ORACLE == dbType) {
            helper = new OracleJdbcApiHelper(this.conn);
        } else if (DBType.AS400 == dbType) {
            helper = new AS400JdbcApiHelper(this.conn);
        } else if (DBType.DB2 == dbType) {
            helper = new DB2JdbcApiHelper(this.conn);
        } else if (DBType.PGSQL == dbType) {
            helper = new PgsqlJdbcApiHelper(this.conn);
        } else if (DBType.SQLSERVER == dbType) {
            helper = new SqlServerJdbcApiHelper(this.conn);
        } else if (DBType.TD == dbType) {
            helper = new TDJdbcApiHelper(this.conn);
        } else if (DBType.IMPALA == dbType) {
            helper = new ImpalaJdbcApiHelper(this.conn);
        } else if (DBType.HIVE == dbType) {
            helper = new HiveJdbcApiHelper(this.conn);
        }
        return helper;
    }

}
