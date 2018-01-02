package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.olq.provider.model.OlqQuerySql;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.bigdata.udsp.olq.provider.impl.model.OracleDatasource;
import com.hex.bigdata.udsp.olq.provider.model.OlqRequest;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import com.hex.bigdata.udsp.olq.utils.OlqCommUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by PC on 2017/6/26.
 */
@Component("com.hex.bigdata.udsp.olq.provider.impl.OracleProvider")
public class OracleProvider implements Provider {

    private Logger logger = LogManager.getLogger(OracleProvider.class);
    private static Map<String, BasicDataSource> dataSourcePool;

    private synchronized BasicDataSource getDataSource(OracleDatasource oracleDatasource) {
        String dsId = oracleDatasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, BasicDataSource>();
        }
        BasicDataSource dataSource = dataSourcePool.get(dsId);
        if (dataSource == null) {
            dataSource = new BasicDataSource();

            if (StringUtils.isNotBlank(oracleDatasource.getDriverClass()))
                dataSource.setDriverClassName(oracleDatasource.getDriverClass());
            if (StringUtils.isNotBlank(oracleDatasource.getJdbcUrl()))
                dataSource.setUrl(oracleDatasource.getJdbcUrl());
            if (StringUtils.isNotBlank(oracleDatasource.getUsername()))
                dataSource.setUsername(oracleDatasource.getUsername());
            if (StringUtils.isNotBlank(oracleDatasource.getPassword()))
                dataSource.setPassword(oracleDatasource.getPassword());
            if (StringUtils.isNotBlank(oracleDatasource.getInitialSize()))
                dataSource.setInitialSize(Integer.valueOf(oracleDatasource.getInitialSize()));// 数据库初始化时，创建的连接个数
            if (StringUtils.isNotBlank(oracleDatasource.getMinIdle()))
                dataSource.setMinIdle(Integer.valueOf(oracleDatasource.getMinIdle()));// 最小空闲连接数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxIdle()))
                dataSource.setMaxIdle(Integer.valueOf(oracleDatasource.getMaxIdle()));// 数据库最大连接数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxActive()))
                dataSource.setMaxActive(Integer.valueOf(oracleDatasource.getMaxActive()));// 设置最大并发数
            if (StringUtils.isNotBlank(oracleDatasource.getMaxWait()))
                dataSource.setMaxWait(Integer.valueOf(oracleDatasource.getMaxWait()));// 最长等待时间，单位毫秒
            if (StringUtils.isNotBlank(oracleDatasource.getValidationQuery()))
                dataSource.setValidationQuery(oracleDatasource.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
            if (StringUtils.isNotBlank(oracleDatasource.getValidationQueryTimeout()))
                dataSource.setValidationQueryTimeout(Integer.valueOf(oracleDatasource.getValidationQueryTimeout())); // 自动验证连接的时间
            if (StringUtils.isNotBlank(oracleDatasource.getTimeBetweenEvictionRunsMillis()))
                dataSource.setTimeBetweenEvictionRunsMillis(Integer.valueOf(oracleDatasource.getTimeBetweenEvictionRunsMillis())); // N毫秒检测一次是否有死掉的线程
            if (StringUtils.isNotBlank(oracleDatasource.getMinEvictableIdleTimeMillis()))
                dataSource.setMinEvictableIdleTimeMillis(Integer.valueOf(oracleDatasource.getMinEvictableIdleTimeMillis()));// 空闲连接N毫秒中后释放
            if (StringUtils.isNotBlank(oracleDatasource.getTestWhileIdle()))
                dataSource.setTestWhileIdle(Boolean.valueOf(oracleDatasource.getTestWhileIdle()));
            if (StringUtils.isNotBlank(oracleDatasource.getTestOnBorrow()))
                dataSource.setTestOnBorrow(Boolean.valueOf(oracleDatasource.getTestOnBorrow()));
            if (StringUtils.isNotBlank(oracleDatasource.getTestOnReturn()))
                dataSource.setTestOnReturn(Boolean.valueOf(oracleDatasource.getTestOnReturn()));

            dataSourcePool.put(dsId, dataSource);
        }
        return dataSource;
    }

    private Connection getConnection(OracleDatasource oracleDatasource) throws SQLException {
        Connection conn = null;
        BasicDataSource dataSource = getDataSource(oracleDatasource);
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }

    /**
     * 执行查询
     *
     * @param request
     * @return
     */
    public OlqResponse execute(String consumeId, OlqRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OlqResponse response = new OlqResponse();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        int count = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(oracleDatasource);
            stmt = conn.createStatement();

            OlqCommUtil.putStatement(consumeId, stmt);

            //获取查询信息
            OlqQuerySql olqQuerySql = getPageSql(request.getSql(), request.getPage());
            if (olqQuerySql.getPage() == null) {
                rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            } else {
                rs = stmt.executeQuery(olqQuerySql.getPageSql());
            }

            rs.setFetchSize(1000);
            ResultSetMetaData rsmd = rs.getMetaData();
            //response.setMetadata(rsmd);
            int columnCount = rsmd.getColumnCount();
            int max_num_size = oracleDatasource.getMaxNum();
            while (rs.next()) {
                map = new LinkedHashMap<String, String>();
                for (int i = 1; i <= columnCount; i++) {
                    // 值进行转义
                    map.put(rsmd.getColumnLabel(i), rs.getString(i) == null ? "" : JSONUtil.encode(rs.getString(i)));
                }
                list.add(map);
                count++;
                if (count >= max_num_size) {
                    break;
                }
            }

            //查询的记录数总数
            String totalSql = olqQuerySql.getTotalSql();
            if (StringUtils.isNotBlank(totalSql)) {
                rs = stmt.executeQuery(totalSql);
                int totalCount = 0;
                if (rs.next()) {
                    totalCount = rs.getInt(1);
                }
                //设置总记录数
                olqQuerySql.getPage().setTotalCount(totalCount);
                response.setPage(olqQuerySql.getPage());
            }
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
            //设置返回列信息
            response.setColumns(OlqCommUtil.putColumnIntoMap(rsmd));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        } finally {
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
            OlqCommUtil.removeStatement(consumeId);
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;

        response.setRecords(list);
        response.setConsumeTime(consumeTime);
        response.setTotalCount(count);

        logger.debug("consumeTime=" + response.getConsumeTime() + " recordsSize=" + response.getRecords().size());
        return response;
    }

    /**
     * 测试数据源连接
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = true;
        Connection connection = null;
        try {
            OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
            connection = getConnection(oracleDatasource);
            if (connection == null) {
                canConnection = false;
            } else {
                canConnection = true;
            }
        } catch (Exception e) {
            canConnection = false;
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return canConnection;
    }

    /**
     * 批处理查询
     *
     * @param request
     * @return
     */
    public OlqResponseFetch executeFetch(String consumeId, OlqRequest request) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();

        OlqResponseFetch response = new OlqResponseFetch();
        response.setRequest(request);

        Datasource datasource = request.getDatasource();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        OracleDatasource oracleDatasource = new OracleDatasource(datasource.getPropertyMap());
        try {
            conn = getConnection(oracleDatasource);
            stmt = conn.createStatement();

            OlqCommUtil.putStatement(consumeId, stmt);

            OlqQuerySql olqQuerySql = getPageSql(request.getSql(), request.getPage());
            if (olqQuerySql.getPage() == null) {
                rs = stmt.executeQuery(olqQuerySql.getOriginalSql());
            } else {
                rs = stmt.executeQuery(olqQuerySql.getPageSql());
            }
            rs.setFetchSize(1000);
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
        }
        response.setConnection(conn);
        response.setStatement(stmt);
        response.setResultSet(rs);

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }

    private OlqQuerySql getPageSql(String sql, Page page) {
        OlqQuerySql olqQuerySql = new OlqQuerySql(sql);
        if (page == null) {
            return olqQuerySql;
        }
        //分页sql组装
        int pageSize = page.getPageSize();
        int pageIndex = page.getPageIndex();
        pageIndex = pageIndex == 0 ? 1 : pageIndex;
        Integer startRow = (pageIndex - 1) * pageSize;
        Integer endRow = pageSize * pageIndex;
        StringBuffer pageSqlBuffer = new StringBuffer("SELECT * FROM (");
        pageSqlBuffer.append(sql);
        pageSqlBuffer.append(") UDSP_VIEW ");
        pageSqlBuffer.append(" WHERE ROWNUM >=");
        pageSqlBuffer.append(startRow);
        pageSqlBuffer.append(" AND ROWNUM <= ");
        pageSqlBuffer.append(endRow);
        olqQuerySql.setPageSql(pageSqlBuffer.toString());
        //总记录数查询SQL组装
        StringBuffer totalSqlBuffer = new StringBuffer("SELECT COUNT(1) FROM (");
        totalSqlBuffer.append(sql);
        totalSqlBuffer.append(") UDSP_VIEW");
        olqQuerySql.setTotalSql(totalSqlBuffer.toString());
        //page设置
        olqQuerySql.setPage(page);
        logger.debug("配置的源SQL:\n" + olqQuerySql.getOriginalSql());
        logger.debug("分页查询SQL:\n" + olqQuerySql.getPageSql());
        logger.debug("查询总数SQL:\n" + olqQuerySql.getTotalSql());
        return olqQuerySql;
    }
}
