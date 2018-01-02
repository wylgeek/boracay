package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.constant.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.factory.RedisConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.RedisDatasource;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.util.IqCommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * redis接口实现类
 */
@Component("com.hex.bigdata.udsp.iq.provider.impl.RedisProvider")
public class RedisProvider implements Provider {
    private static Logger logger = LogManager.getLogger(RedisProvider.class);
    private static Map<String, RedisConnectionPoolFactory> dataSourcePool;
    private static final String rkSep = "|";
    private static final String tableColumnSeq = ":";

    public IqResponse query(IqRequest request) {
        return query(request, -1, -1);
    }

    private String getRedisQuery(List<DataColumn> metaDataQueryColumns, List<QueryColumn> queryColumns, String tableName) {
        Map<Short, String> canQueryColumn = new HashMap();
        for (DataColumn metaDataQueryColumn : metaDataQueryColumns) {
            canQueryColumn.put(metaDataQueryColumn.getSeq(), metaDataQueryColumn.getName());
        }
        Map<String, QueryColumn> queryColumnValueMap = new HashMap();
        for (QueryColumn queryColumn : queryColumns) {
            queryColumnValueMap.put(queryColumn.getName(), queryColumn);
        }
        StringBuffer queryString = new StringBuffer("");
        queryString.append(tableName);
        queryString.append(tableColumnSeq);
        Operator operator;
        for (Short i = 1; canQueryColumn.get(i) != null; i++) {

            QueryColumn queryColumn = queryColumnValueMap.get(canQueryColumn.get(i));
            if (queryColumn == null || StringUtils.isBlank(queryColumn.getValue())) {
                queryString.append("*");
                queryString.append(rkSep);
                continue;
            }
            operator = queryColumn.getOperator();
            if (Operator.LK.equals(operator)) {
                queryString.append("*");
                queryString.append(queryColumn.getValue());
                queryString.append("*");
            } else if (Operator.RLIKE.equals(operator)) {
                queryString.append(queryColumn.getValue());
                queryString.append("*");
            } else if (Operator.EQ.equals(operator)) {
                queryString.append(queryColumn.getValue());
            } else {
                throw new IllegalArgumentException("redis不支持" + IqCommonUtil.getOperatorName(operator) + "操作类型！");
            }
            //最后一个|不用去除的，防止模糊匹配时匹配到错误数据
            queryString.append(rkSep);

        }
        return queryString.toString();
    }


    public IqResponse query(IqRequest request, int pageIndex, int pageSize) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request));
        long bef = System.currentTimeMillis();
        IqResponse response = new IqResponse();
        response.setRequest(request);
        Application application = request.getApplication();
        int maxNum = application.getMaxNum();
        Metadata metadata = application.getMetadata();
        List<QueryColumn> queryColumns = application.getQueryColumns();
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        List<OrderColumn> orderColumns = application.getOrderColumns();
        Datasource datasource = metadata.getDatasource();
        //获取元数据返回字段
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns();
        RedisDatasource redisDatasource = new RedisDatasource(datasource.getPropertyMap());
        String tableName = metadata.getTbName();
        String query = getRedisQuery(metadata.getQueryColumns(), queryColumns, tableName);
        String fqSep = redisDatasource.getSeprator();
        int maxSize = redisDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        try {
            List<Map<String, String>> list = null;
            int startRow = -1;
            int endRow = -1;
            Page page = null;
            if (pageIndex != -1 && pageSize != -1) {
                startRow = (pageIndex - 1) * pageSize;
                endRow = pageIndex * pageSize;
                page = new Page();
                page.setPageIndex(pageIndex);
                page.setPageSize(pageSize);
                page.setTotalCount(getCountNum(query, redisDatasource));
                list = search(fqSep, query, redisDatasource, metaReturnColumns, startRow, endRow, maxSize);
            } else {
                list = search(fqSep, query, redisDatasource, metaReturnColumns, maxSize);
            }
            //排序
            list = orderBy(list, orderColumns);
            List<com.hex.bigdata.udsp.common.provider.model.Result> records = new ArrayList<com.hex.bigdata.udsp.common.provider.model.Result>();
            for (Map<String, String> map : list) {
                com.hex.bigdata.udsp.common.provider.model.Result result = new com.hex.bigdata.udsp.common.provider.model.Result();
                //字段过滤
                Map<String, String> returnDataMap = new HashMap<String, String>();
                for (ReturnColumn item : returnColumns) {
                    String colName = item.getName();
                    returnDataMap.put(colName, map.get(colName));
                }
                result.putAll(returnDataMap);
                //result.putAll(map);
                records.add(result);
            }
            response.setPage(page);
            response.setRecords(records);
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.toString());
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime() + " recordsSize=" + response.getRecords().size());
        return response;
    }

    //-------------------------------------------分割线---------------------------------------------

    private synchronized RedisConnectionPoolFactory getDataSource(RedisDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, RedisConnectionPoolFactory>();
        }
        RedisConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            factory = new RedisConnectionPoolFactory(datasource);
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    private Jedis getConnection(RedisDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            return null;
        }
    }


    private List<Map<String, String>> search(String fqSep, String queryString, RedisDatasource datasource, List<DataColumn> returnColumns, int startRow, int endRow, int maxNum) {
        RedisConnectionPoolFactory redisConnectionPoolFactory = getDataSource(datasource);
        Jedis jedis = redisConnectionPoolFactory.getConnection();

        List<Map<String, String>> records = new ArrayList<>();
        String[] returnResults;
        Map<String, String> record;
        try {
            //获取模糊匹配的key
            Set<String> keys = jedis.keys(queryString);

            String[] results = new String[keys.size()];
            results = keys.toArray(results);
            for (int i = 0; i < results.length && i <= endRow && i >= startRow; i++) {
                record = new HashMap<String, String>();
                returnResults = jedis.get(results[i]).split(fqSep);
                for (DataColumn returnColumn : returnColumns) {
                    record.put(returnColumn.getName(), JSONUtil.encode(returnResults[returnColumn.getSeq() - 1]));
                }
                records.add(record);
                if (records.size() >= maxNum) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("redis查询出错！查询的内容为：" + queryString);
        } finally {
            if (jedis != null) {
                redisConnectionPoolFactory.release(jedis);
            }
        }
        return records;
    }


    private int getCountNum(String queryString, RedisDatasource datasource) {
        RedisConnectionPoolFactory redisConnectionPoolFactory = getDataSource(datasource);
        Jedis jedis = redisConnectionPoolFactory.getConnection();
        //获取模糊匹配的key
        try {
            Set<String> keys = jedis.keys(queryString);
            return keys.size();
        } finally {
            if (jedis != null) {
                redisConnectionPoolFactory.release(jedis);
            }
        }
    }


    private List<Map<String, String>> search(String fqSep, String queryString, RedisDatasource datasource, List<DataColumn> returnColumns, int maxNum) {
        RedisConnectionPoolFactory redisConnectionPoolFactory = getDataSource(datasource);
        Jedis jedis = redisConnectionPoolFactory.getConnection();


        List<Map<String, String>> records = new ArrayList<>();
        String[] returnResults;
        Map<String, String> record;
        try {
            //获取模糊匹配的key
            Set<String> keys = jedis.keys(queryString);

            String[] results = new String[keys.size()];
            results = keys.toArray(results);
            for (int i = 0; i < results.length; i++) {
                record = new HashMap<String, String>();
                returnResults = jedis.get(results[i]).split(fqSep);
                for (DataColumn returnColumn : returnColumns) {
                    record.put(returnColumn.getName(), returnResults[returnColumn.getSeq() - 1]);
                }
                records.add(record);
                if (records.size() >= maxNum) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("redis查询出错！查询的内容为：" + queryString);
        } finally {
            if (jedis != null) {
                redisConnectionPoolFactory.release(jedis);
            }
        }
        return records;
    }


    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        Jedis jedis = null;
        RedisDatasource redisDatasource = new RedisDatasource(datasource.getPropertyMap());
        try {
            jedis = getConnection(redisDatasource);
            canConnection = jedis.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            canConnection = false;
        } finally {
            if (jedis != null) {
                getDataSource(redisDatasource).release(jedis);
            }
        }
        return canConnection;
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        return null;
    }


    private List<Map<String, String>> orderBy(List<Map<String, String>> records, final List<OrderColumn> orderColumns) {
        Collections.sort(orderColumns, new Comparator<OrderColumn>() {
            public int compare(OrderColumn obj1, OrderColumn obj2) {
                return obj1.getSeq().compareTo(obj2.getSeq());
            }
        });
        // 多字段混合排序
        Collections.sort(records, new Comparator<Map<String, String>>() {
            public int compare(Map<String, String> obj1, Map<String, String> obj2) {
                int flg = 0;
                for (OrderColumn orderColumn : orderColumns) {
                    String colName = orderColumn.getName();
                    Order order = orderColumn.getOrder();
                    DataType dataType = orderColumn.getType();
                    String val1 = obj1.get(colName);
                    String val2 = obj2.get(colName);
                    flg = compareTo(val1, val2, order, dataType);
                    if (flg != 0) break;
                }
                return flg;
            }
        });
        return records;
    }

    private int compareTo(String str1, String str2, Order order, DataType dataType) {
        if (dataType == null || DataType.STRING.equals(dataType) || DataType.VARCHAR.equals(dataType)
                || DataType.CHAR.equals(dataType) || DataType.TIMESTAMP.equals(dataType)) {
            if (order != null && Order.DESC.equals(order)) {
                return 0 - str1.compareTo(str2);
            } else {
                return str1.compareTo(str2);
            }
        } else {
            if (order != null && Order.DESC.equals(order)) {
                if (DataType.INT.equals(dataType) && DataType.BIGINT.equals(dataType) && DataType.TINYINT.equals(dataType)) {
                    return 0 - Integer.valueOf(str1).compareTo(Integer.valueOf(str2));
                } else {
                    return 0 - Double.valueOf(str1).compareTo(Double.valueOf(str2));
                }
            } else {
                if (DataType.INT.equals(dataType) && DataType.BIGINT.equals(dataType) && DataType.TINYINT.equals(dataType)) {
                    return Integer.valueOf(str1).compareTo(Integer.valueOf(str2));
                } else {
                    return Double.valueOf(str1).compareTo(Double.valueOf(str2));
                }
            }
        }
    }
}
