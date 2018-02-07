package com.hex.bigdata.udsp.im.provider.impl.factory;

import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by PC on 2017/9/29.
 */
public class JdbcConnectionPoolFactory {
    private GenericObjectPool pool;

    public JdbcConnectionPoolFactory(GenericObjectPool.Config config, JdbcDatasource datasource) {
        JdbcConnectionFactory factory = new JdbcConnectionFactory(datasource);
        pool = new GenericObjectPool(factory, config);
    }

    public Connection getConnection() throws Exception {
        return (Connection) pool.borrowObject();
    }

    public void releaseConnection(Connection connection) {
        try {
            pool.returnObject(connection);
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public void closePool() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class JdbcConnectionFactory extends BasePoolableObjectFactory {

    private String driverClass;
    private String jdbcUrl;
    private String username;
    private String password;

    public JdbcConnectionFactory(JdbcDatasource datasource) {
        driverClass = datasource.getDriverClass();
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        jdbcUrl = datasource.getJdbcUrl();
        username = datasource.getUsername();
        password = datasource.getPassword();
        if (StringUtils.isBlank(username)) username = "";
        if (StringUtils.isBlank(password)) password = "";
    }

    @Override
    public Object makeObject() throws Exception {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof Connection) {
            ((Connection) obj).close();
        }
    }

    public boolean validateObject(Object obj) {
        if (obj instanceof Connection) {
            Connection conn = ((Connection) obj);
            try {
                if (conn == null || conn.isClosed()) {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
