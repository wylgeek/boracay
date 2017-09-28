package com.hex.bigdata.metadata.db.jdbcinfo.helper;

import com.hex.bigdata.metadata.db.jdbcinfo.BaseJdbcInfoHelper;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.util.DBType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by junjiem on 2016-6-29.
 */
public class TDJdbcInfoHelper extends BaseJdbcInfoHelper {
    public TDJdbcInfoHelper(Connection conn) {
        super(conn);
    }

    @Override
    public String getDbType() {
        return DBType.TD.getValue();
    }

    @Override
    public String getCurrentDbNameSql() {
        return null;
    }

    @Override
    public String getDatabasesSql() {
        return null;
    }

    @Override
    public String getTablesSql(String dbName) {
        return null;
    }

    @Override
    public String getColumnsSql(String dbName, String tbName) {
        return null;
    }

    @Override
    public List<ColumnType> getColumnTypes() throws SQLException {
        return null;
    }
}
