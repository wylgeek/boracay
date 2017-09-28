package com.hex.bigdata.metadata.db.jdbcinfo;

import com.hex.bigdata.metadata.db.BaseClient;
import com.hex.bigdata.metadata.db.Helper;
import com.hex.bigdata.metadata.db.jdbcinfo.helper.*;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-29.
 */
public class JdbcInfoClient extends BaseClient {
    public JdbcInfoClient(Connection conn, DBType dbType) throws SQLException {
        super(conn, dbType);
    }

    @Override
    public BaseJdbcInfoHelper createHelper(DBType dbType) {
        BaseJdbcInfoHelper helper = null;
        if (DBType.MYSQL == dbType) {
            helper = new MysqlJdbcInfoHelper(this.conn);
        } else if (DBType.ORACLE == dbType) {
            helper = new OracleJdbcInfoHelper(this.conn);
        } else if (DBType.AS400 == dbType) {
            helper = new AS400JdbcInfoHelper(this.conn);
        } else if (DBType.DB2 == dbType) {
            helper = new DB2JdbcInfoHelper(this.conn);
        } else if (DBType.PGSQL == dbType) {
            helper = new PgsqlJdbcInfoHelper(this.conn);
        } else if (DBType.SQLSERVER == dbType) {
            helper = new SqlServerJdbcInfoHelper(this.conn);
        } else if (DBType.TD == dbType) {
            helper = new TDJdbcInfoHelper(this.conn);
        } else if (DBType.IMPALA == dbType) {
            helper = new ImpalaJdbcInfoHelper(this.conn);
        } else if (DBType.HIVE == dbType) {
            helper = new HiveJdbcInfoHelper(this.conn);
        }
        return helper;
    }
}
