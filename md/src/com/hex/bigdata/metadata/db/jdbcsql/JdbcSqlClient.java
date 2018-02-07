package com.hex.bigdata.metadata.db.jdbcsql;

import com.hex.bigdata.metadata.db.BaseClient;
import com.hex.bigdata.metadata.db.jdbcsql.helper.*;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by junjiem on 2016-6-20.
 */
public class JdbcSqlClient extends BaseClient {

    public JdbcSqlClient(Connection conn, DBType dbType) throws SQLException {
        super(conn, dbType);
    }

    public BaseJdbcSqlHelper createHelper(DBType dbType) {
        BaseJdbcSqlHelper helper = null;
        if (DBType.MYSQL == dbType) {
            helper = new MysqlJdbcSqlHelper(this.conn);
        } else if (DBType.ORACLE == dbType) {
            helper = new OracleJdbcSqlHelper(this.conn);
        } else if (DBType.AS400 == dbType) {
            helper = new AS400JdbcSqlHelper(this.conn);
        } else if (DBType.DB2 == dbType) {
            helper = new DB2JdbcSqlHelper(this.conn);
        } else if (DBType.PGSQL == dbType) {
            helper = new PgsqlJdbcSqlHelper(this.conn);
        } else if (DBType.SQLSERVER == dbType) {
            helper = new SqlServerJdbcSqlHelper(this.conn);
        } else if (DBType.TD == dbType) {
            helper = new TDJdbcSqlHelper(this.conn);
        } else if (DBType.IMPALA_FOR_MYSQL == dbType) {
            helper = new ImpalaForMysqlJdbcSqlHelper(this.conn);
        } else if (DBType.IMPALA_FOR_ORACLE == dbType) {
            helper = new ImpalaForOracleJdbcSqlHelper(this.conn);
        } else if (DBType.IMPALA_FOR_PGSQL == dbType) {
            helper = new ImpalaForPgsqlJdbcSqlHelper(this.conn);
        } else if (DBType.HIVE_FOR_MYSQL == dbType) {
            helper = new HiveForMysqlJdbcSqlHelper(this.conn);
        } else if (DBType.HIVE_FOR_ORACLE == dbType) {
            helper = new HiveForOracleJdbcSqlHelper(this.conn);
        } else if (DBType.HIVE_FOR_PGSQL == dbType) {
            helper = new HiveForPgsqlJdbcSqlHelper(this.conn);
        }
        return helper;
    }
}
