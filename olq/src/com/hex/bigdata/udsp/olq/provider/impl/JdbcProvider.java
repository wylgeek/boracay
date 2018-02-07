package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.impl.model.JdbcDatasource;
import com.hex.bigdata.udsp.olq.provider.model.OlqQuerySql;
import com.hex.bigdata.udsp.olq.provider.model.OlqRequest;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import com.hex.bigdata.udsp.olq.utils.OlqCommUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by PC on 2018/1/11.
 */
public abstract class JdbcProvider implements Provider {
    private static Logger logger = LogManager.getLogger(JdbcProvider.class);
    private static Map<String, BasicDataSource> dataSourcePool;

    private synchronized BasicDataSource getDataSource(JdbcDatasource jdbcDatasource) {
        String dsId = jdbcDatasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<>();
        }
        BasicDataSource dataSource = dataSourcePool.get(dsId);
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            //Class.forName(jdbcDatasource.getDriverClass());
            if (StringUtils.isNotBlank(jdbcDatasource.getDriverClass()))
                dataSource.setDriverClassName(jdbcDatasource.getDriverClass());
            if (StringUtils.isNotBlank(jdbcDatasource.getJdbcUrl()))
                dataSource.setUrl(jdbcDatasource.getJdbcUrl());
            if (StringUtils.isNotBlank(jdbcDatasource.getUsername()))
                dataSource.setUsername(jdbcDatasource.getUsername());
            if (StringUtils.isNotBlank(jdbcDatasource.getPassword()))
                dataSource.setPassword(jdbcDatasource.getPassword());
            if (StringUtils.isNotBlank(jdbcDatasource.getInitialSize()))
                dataSource.setInitialSize(Integer.valueOf(jdbcDatasource.getInitialSize()));// 数据库初始化时，创建的连接个数
            if (StringUtils.isNotBlank(jdbcDatasource.getMinIdle()))
                dataSource.setMinIdle(Integer.valueOf(jdbcDatasource.getMinIdle()));// 最小空闲连接数
            if (StringUtils.isNotBlank(jdbcDatasource.getMaxIdle()))
                dataSource.setMaxIdle(Integer.valueOf(jdbcDatasource.getMaxIdle()));// 数据库最大连接数
            if (StringUtils.isNotBlank(jdbcDatasource.getMaxActive()))
                dataSource.setMaxActive(Integer.valueOf(jdbcDatasource.getMaxActive()));// 设置最大并发数
            if (StringUtils.isNotBlank(jdbcDatasource.getMaxWait()))
                dataSource.setMaxWait(Integer.valueOf(jdbcDatasource.getMaxWait()));// 最长等待时间，单位毫秒
            if (StringUtils.isNotBlank(jdbcDatasource.getValidationQuery()))
                dataSource.setValidationQuery(jdbcDatasource.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
            if (StringUtils.isNotBlank(jdbcDatasource.getValidationQueryTimeout()))
                dataSource.setValidationQueryTimeout(Integer.valueOf(jdbcDatasource.getValidationQueryTimeout())); // 自动验证连接的时间
            if (StringUtils.isNotBlank(jdbcDatasource.getTimeBetweenEvictionRunsMillis()))
                dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(jdbcDatasource.getTimeBetweenEvictionRunsMillis())); // N毫秒检测一次是否有死掉的线程
            if (StringUtils.isNotBlank(jdbcDatasource.getMinEvictableIdleTimeMillis()))
                dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(jdbcDatasource.getMinEvictableIdleTimeMillis()));// 空闲连接N毫秒中后释放
            if (StringUtils.isNotBlank(jdbcDatasource.getTestWhileIdle()))
                dataSource.setTestWhileIdle(Boolean.valueOf(jdbcDatasource.getTestWhileIdle()));
            if (StringUtils.isNotBlank(jdbcDatasource.getTestOnBorrow()))
                dataSource.setTestOnBorrow(Boolean.valueOf(jdbcDatasource.getTestOnBorrow()));
            if (StringUtils.isNotBlank(jdbcDatasource.getTestOnReturn()))
                dataSource.setTestOnReturn(Boolean.valueOf(jdbcDatasource.getTestOnReturn()));
            dataSourcePool.put(dsId, dataSource);
        }
        return dataSource;
    }

    private Connection getConnection(JdbcDatasource jdbcDatasource) throws SQLException {
        Connection conn = null;
        BasicDataSource dataSource = getDataSource(jdbcDatasource);
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    public OlqResponse execute(String consumeId, OlqRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OlqResponse response = new OlqResponse();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(jdbcDatasource);
            stmt = conn.createStatement();

            OlqCommUtil.putStatement(consumeId, stmt);

            OlqQuerySql olqQuerySql = getPageSql(request.getSql(), request.getPage());
            if (olqQuerySql.getPage() == null) {
                rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            } else {
                rs = stmt.executeQuery(olqQuerySql.getPageSql());
            }
            rs.setFetchSize(1000);

            LinkedHashMap<String, String> columns = getColumns(rs);
            List<Map<String, String>> records = getRecords(rs, jdbcDatasource.getMaxNum());
            response.setColumns(columns);
            response.setRecords(records);
            response.setTotalCount(records.size());
            response.setPage(getPage(stmt, olqQuerySql));

            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (SQLException e) {
            logger.error(ExceptionUtil.getMessage(e));
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        } finally {
            close(rs, stmt, conn);
            OlqCommUtil.removeStatement(consumeId);
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }

    private Page getPage(Statement stmt, OlqQuerySql olqQuerySql) throws SQLException {
        String totalSql = olqQuerySql.getTotalSql();
        if (StringUtils.isNotBlank(totalSql)) {
            ResultSet rs = stmt.executeQuery(totalSql);
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            olqQuerySql.getPage().setTotalCount(totalCount);
            return olqQuerySql.getPage();
        }
        return null;
    }

    private List<Map<String, String>> getRecords(ResultSet rs, int maxNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        int count = 0;
        while (rs.next()) {
            map = new LinkedHashMap<String, String>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                //map.put(rsmd.getColumnName(i), rs.getString(i));
                String columnName = rsmd.getColumnLabel(i);
                int index = columnName.indexOf(".");
                columnName = (index == 1 ? columnName : columnName.substring(index + 1));
                map.put(columnName, rs.getString(i) == null ? "" : JSONUtil.encode(rs.getString(i)));
            }
            list.add(map);
            count++;
            if (count >= maxNum) {
                break;
            }
        }
        return list;
    }

    private LinkedHashMap<String, String> getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        LinkedHashMap<String, String> columnMap = new LinkedHashMap<>();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            columnMap.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
        }
        return columnMap;
    }

    private void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        boolean status = true;
        Connection connection = null;
        try {
            JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
            connection = getConnection(jdbcDatasource);
            if (connection == null) {
                status = false;
            } else {
                status = true;
            }
        } catch (Exception e) {
            status = false;
            logger.error(ExceptionUtil.getMessage(e));
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(ExceptionUtil.getMessage(e));
                }
            }
        }
        return status;
    }

    @Override
    public OlqResponseFetch executeFetch(String consumeId, OlqRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OlqResponseFetch response = new OlqResponseFetch();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        JdbcDatasource jdbcDatasource = new JdbcDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(jdbcDatasource);
            stmt = conn.createStatement();

            OlqCommUtil.putStatement(consumeId, stmt);

            OlqQuerySql olqQuerySql = getPageSql(request.getSql(), request.getPage());
            if (olqQuerySql.getPage() == null) {
                rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            } else {
                rs = stmt.executeQuery(olqQuerySql.getPageSql());
            }
            rs.setFetchSize(1000);

            response.setConnection(conn);
            response.setStatement(stmt);
            response.setResultSet(rs);

            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }

    protected abstract OlqQuerySql getPageSql(String sql, Page page);
}
