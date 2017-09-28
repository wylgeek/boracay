package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.im.provider.impl.model.datasource.JdbcDatasource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-11.
 */
public class JdbcUtil {
    private static Logger logger = LogManager.getLogger(JdbcUtil.class);
    private static Map<String, BasicDataSource> dataSourcePool = new HashMap<String, BasicDataSource>();

    public static synchronized BasicDataSource getDataSource(JdbcDatasource datasource) {
        String dsId = datasource.getId();
        BasicDataSource dataSource = dataSourcePool.get(dsId);
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            //Class.forName(datasource.getDriverClass());
            if (StringUtils.isNotBlank(datasource.getDriverClass()))
                dataSource.setDriverClassName(datasource.getDriverClass());
            if (StringUtils.isNotBlank(datasource.getJdbcUrl()))
                dataSource.setUrl(datasource.getJdbcUrl());
            if (StringUtils.isNotBlank(datasource.getUsername()))
                dataSource.setUsername(datasource.getUsername());
            if (StringUtils.isNotBlank(datasource.getPassword()))
                dataSource.setPassword(datasource.getPassword());
            if (StringUtils.isNotBlank(datasource.getInitialSize()))
                dataSource.setInitialSize(Integer.valueOf(datasource.getInitialSize()));// 数据库初始化时，创建的连接个数
            if (StringUtils.isNotBlank(datasource.getMinIdle()))
                dataSource.setMinIdle(Integer.valueOf(datasource.getMinIdle()));// 最小空闲连接数
            if (StringUtils.isNotBlank(datasource.getMaxIdle()))
                dataSource.setMaxIdle(Integer.valueOf(datasource.getMaxIdle()));// 数据库最大连接数
            if (StringUtils.isNotBlank(datasource.getMaxActive()))
                dataSource.setMaxActive(Integer.valueOf(datasource.getMaxActive()));// 设置最大并发数
            if (StringUtils.isNotBlank(datasource.getMaxWait()))
                dataSource.setMaxWait(Integer.valueOf(datasource.getMaxWait()));// 最长等待时间，单位毫秒
            if (StringUtils.isNotBlank(datasource.getValidationQuery()))
                dataSource.setValidationQuery(datasource.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
            if (StringUtils.isNotBlank(datasource.getValidationQueryTimeout()))
                dataSource.setValidationQueryTimeout(Integer.valueOf(datasource.getValidationQueryTimeout())); // 自动验证连接的时间
            if (StringUtils.isNotBlank(datasource.getTimeBetweenEvictionRunsMillis()))
                dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(datasource.getTimeBetweenEvictionRunsMillis())); // N毫秒检测一次是否有死掉的线程
            if (StringUtils.isNotBlank(datasource.getMinEvictableIdleTimeMillis()))
                dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(datasource.getMinEvictableIdleTimeMillis()));// 空闲连接N毫秒中后释放
            if (StringUtils.isNotBlank(datasource.getTestWhileIdle()))
                dataSource.setTestWhileIdle(Boolean.valueOf(datasource.getTestWhileIdle()));
            if (StringUtils.isNotBlank(datasource.getTestOnBorrow()))
                dataSource.setTestOnBorrow(Boolean.valueOf(datasource.getTestOnBorrow()));
            if (StringUtils.isNotBlank(datasource.getTestOnReturn()))
                dataSource.setTestOnReturn(Boolean.valueOf(datasource.getTestOnReturn()));
            dataSourcePool.put(dsId, dataSource);
        }
        return dataSource;
    }

    public static Connection getConnection(JdbcDatasource datasource) throws SQLException {
        Connection conn = null;
        BasicDataSource dataSource = getDataSource(datasource);
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    public static boolean createEngineSchema(JdbcDatasource datasource, String dbName, String updateSql) throws SQLException {
        String sql = HiveSqlUtil.createDatabase(true, dbName);
        return executeUpdate(datasource, sql) && executeUpdate(datasource, updateSql);
    }

    public static boolean executeUpdate(JdbcDatasource datasource, String updateSql) throws SQLException {
        return executeUpdate2(datasource, updateSql) == 0 ? true : false;
    }

    public static int executeUpdate2(JdbcDatasource datasource, String updateSql) throws SQLException {
        logger.debug("UPDATE SQL:" + updateSql);
        Connection conn = null;
        Statement stmt = null;
        int rs = -1;
        try {
            conn = JdbcUtil.getConnection(datasource);
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(updateSql);
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return rs;
    }

    public static boolean executeUpdate(JdbcDatasource datasource, List<String> updateSqls) throws SQLException {
        for (String updateSql : updateSqls) {
            if (!executeUpdate(datasource, updateSql)) {
                return false;
            }
        }
        return true;
    }
}
