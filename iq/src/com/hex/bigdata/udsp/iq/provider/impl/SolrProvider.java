package com.hex.bigdata.udsp.iq.provider.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.constant.Order;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.SolrUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.factory.SolrConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.SolrPage;
import com.hex.bigdata.udsp.iq.provider.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by junjiem on 2017-3-3.
 */
@Component("com.hex.bigdata.udsp.iq.provider.impl.SolrProvider")
public class SolrProvider implements Provider {
    private static Logger logger = LogManager.getLogger(SolrProvider.class);
    private static Map<String, SolrConnectionPoolFactory> dataSourcePool;

    public IqResponse query(IqRequest request) {
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
        String tbName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();
        //获取元数据返回字段
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns();

        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());

        int maxSize = solrDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        SolrQuery query = getSolrQuery(maxSize, queryColumns, orderColumns, metaReturnColumns);

        try {
            List<Map<String, Object>> list = search(tbName, query, solrDatasource);

            List<com.hex.bigdata.udsp.common.provider.model.Result> records = new ArrayList<com.hex.bigdata.udsp.common.provider.model.Result>();
            for (Map<String, Object> map : list) {
                com.hex.bigdata.udsp.common.provider.model.Result result = new com.hex.bigdata.udsp.common.provider.model.Result();
                //字段过滤
                Map<String, Object> returnDataMap = new HashMap<String, Object>();
                for (ReturnColumn item : returnColumns) {
                    String colName = item.getName();
                    returnDataMap.put(colName, map.get(colName));
                }
                result.set(returnDataMap);
                //result.set(map);
                records.add(result);
            }

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

        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }

    public IqResponse query(IqRequest request, int pageIndex, int pageSize) {
        logger.debug("request=" + JSONUtil.parseObj2JSON(request) + " pageIndex=" + pageIndex + " pageSize=" + pageSize);
        long bef = System.currentTimeMillis();
        IqResponse response = new IqResponse();
        response.setRequest(request);

        Application application = request.getApplication();
        int maxNum = application.getMaxNum();
        Metadata metadata = application.getMetadata();
        List<QueryColumn> queryColumns = application.getQueryColumns();
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        List<OrderColumn> orderColumns = application.getOrderColumns();
        String tbName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();

        //获取元数据返回字段
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns();

        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());

        int maxSize = solrDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        if (pageSize > maxSize) {
            pageSize = maxSize;
        }
        SolrQuery query = getSolrQuery(pageIndex, pageSize, queryColumns, orderColumns, metaReturnColumns);

        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        try {
            SolrPage solrPage = searchPage(tbName, query, pageIndex, pageSize, solrDatasource);
            List<Map<String, Object>> list = solrPage.getRecords();

            List<com.hex.bigdata.udsp.common.provider.model.Result> records = new ArrayList<com.hex.bigdata.udsp.common.provider.model.Result>();
            for (Map<String, Object> map : list) {
                com.hex.bigdata.udsp.common.provider.model.Result result = new com.hex.bigdata.udsp.common.provider.model.Result();
                //字段过滤
                Map<String, Object> returnDataMap = new HashMap<String, Object>();
                for (ReturnColumn item : returnColumns) {
                    String colName = item.getName();
                    returnDataMap.put(colName, map.get(colName));
                }
                result.set(returnDataMap);
                //result.set(map);
                records.add(result);
            }

            response.setRecords(records);

            page.setTotalCount(solrPage.getTotalCount());

            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.toString());
        }

        response.setPage(page);
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime());
        return response;
    }
    
    //-------------------------------------------分割线---------------------------------------------

    private synchronized SolrConnectionPoolFactory getDataSource(String collectionName, SolrDatasource datasource) {
        String dsId = datasource.getId() + ":" + collectionName;
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, SolrConnectionPoolFactory>();
        }
        SolrConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxIdle = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new SolrConnectionPoolFactory(config, datasource.getSolrServers(), collectionName);
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    private SolrServer getConnection(String collectionName, SolrDatasource datasource) {
        try {
            return getDataSource(collectionName, datasource).getConnection();
        } catch (Exception e) {
            return null;
        }
    }

    private void release(String collectionName, SolrDatasource datasource, SolrServer solrServer) {
        getDataSource(collectionName, datasource).releaseConnection(solrServer);
    }

    private SolrQuery getSolrQuery(int rows, List<QueryColumn> queryColumns, List<OrderColumn> orderColumns, List<DataColumn> returnColumns) {
        return new SolrQuery().setQuery(getQuery(queryColumns)) //
                .setStart(0) //
                .setRows(rows) //
                .setSorts(getSort(orderColumns)) //
                .setFields(getFields(returnColumns));
    }

    private SolrQuery getSolrQuery(int pageIndex, int pageSize, List<QueryColumn> queryColumns, List<OrderColumn> orderColumns, List<DataColumn> returnColumns) {
        return new SolrQuery().setQuery(getQuery(queryColumns)) //
                .setStart((pageIndex - 1) * pageSize) //
                .setRows(pageSize) //
                .setSorts(getSort(orderColumns)) //
                .setFields(getFields(returnColumns));
    }

    private String getFields(List<DataColumn> returnColumns) {
        Collections.sort(returnColumns, new Comparator<DataColumn>() {
            public int compare(DataColumn obj1, DataColumn obj2) {
                return obj1.getSeq().compareTo(obj2.getSeq());
            }
        });
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (DataColumn returnColumn : returnColumns) {
            if (returnColumn != null && StringUtils.isNotBlank(returnColumn.getName())) {
                if (count == 0) {
                    sb.append(returnColumn.getName());
                } else {
                    sb.append("," + returnColumn.getName());
                }
                count++;
            }
        }
        return sb.toString();
    }

    private String getQuery(List<QueryColumn> queryColumns) {
        Collections.sort(queryColumns, new Comparator<QueryColumn>() {
            public int compare(QueryColumn obj1, QueryColumn obj2) {
                return obj1.getSeq().compareTo(obj2.getSeq());
            }
        });
        StringBuffer sb = new StringBuffer("*:*");
        for (QueryColumn queryColumn : queryColumns) {
            String name = queryColumn.getName();
            String value = queryColumn.getValue();
            Operator operator = queryColumn.getOperator();
            if (StringUtils.isNotBlank(value)) {
                if (Operator.EQ.equals(operator)) {
                    sb.append(" AND " + name + ":" + value);
                } else if (Operator.GT.equals(operator)) {
                    sb.append(" AND " + name + ":[" + value + " TO *] AND " + name + ":(* NOT " + value + ")");
                } else if (Operator.LT.equals(operator)) {
                    sb.append(" AND " + name + ":[* TO " + value + "] AND " + name + ":(* NOT " + value + ")");
                } else if (Operator.GE.equals(operator)) {
                    sb.append(" AND " + name + ":[" + value + " TO *]");
                } else if (Operator.LE.equals(operator)) {
                    sb.append(" AND " + name + ":[* TO " + value + "]");
                } else if (Operator.NE.equals(operator)) {
                    sb.append(" AND " + name + ":(* NOT " + value + ")");
                } else if (Operator.LK.equals(operator)) {
                    sb.append(" AND " + name + ":*" + value + "*");
                } else if (Operator.IN.equals(operator)) {
                    //条件切分
                    if (StringUtils.isBlank(value)) {
                        continue;
                    }
                    sb.append(" AND " + name + ":(");
                    String[] stringArray = value.split(",");
                    for (int i = 0; i < stringArray.length; i++) {
                        sb.append(stringArray[i]);
                        if (i<stringArray.length-1){
                            sb.append(" or ");
                        }
                        if (i == stringArray.length-1){
                            sb.append(")");
                        }
                    }
                } else if (Operator.RLIKE.equals(operator)) {
                    sb.append(" AND " + name + ":" + value + "*");
                }
            }
        }
        logger.debug("q=" + sb.toString());
        return sb.toString();
    }

    private List<SolrQuery.SortClause> getSort(List<OrderColumn> orderColumns) {
        List<SolrQuery.SortClause> list = new ArrayList<SolrQuery.SortClause>();
        for (OrderColumn orderColumn : orderColumns) {
            String colName = orderColumn.getName();
            Order order = orderColumn.getOrder();
            if (order != null && Order.DESC.equals(order)) {
                list.add(new SolrQuery.SortClause(colName, SolrQuery.ORDER.desc));
            } else {
                list.add(new SolrQuery.SortClause(colName, SolrQuery.ORDER.asc));
            }
        }
        return list;
    }

    private SolrPage searchPage(String collectionName, SolrQuery query, int pageIndex, int pageSize, SolrDatasource datasource) {
        QueryResponse rsp = getQueryResponse(collectionName, query, datasource);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults();
        int totalCount = (int) results.getNumFound();
        List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (int i = 0; i < results.size(); i++) {
            map = new HashMap<>();
            for (Map.Entry<String, Object> entry : results.get(i).entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            records.add(map);
            //records.add(results.get(i).getFieldValueMap());
        }
        return new SolrPage(records, pageIndex, pageSize, totalCount);
    }

    private List<Map<String, Object>> search(String collectionName, SolrQuery query, SolrDatasource datasource) {
        QueryResponse rsp = getQueryResponse(collectionName, query, datasource);
        if (rsp == null) {
            return null;
        }
        SolrDocumentList results = rsp.getResults();
        List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < results.size(); i++) {
            records.add(results.get(i).getFieldValueMap());
        }
        return records;
    }

    private QueryResponse getQueryResponse(String collectionName, SolrQuery query, SolrDatasource datasource) {
        SolrServer solrServer = null;
        QueryResponse res = null;
        try {
            solrServer = getConnection(collectionName, datasource);
            res = solrServer.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                release(collectionName, datasource, solrServer);
            }
        }
        return res;
    }

    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = false;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            SolrDatasource solrDatasource = new SolrDatasource(datasource.getProperties());
            String[] tempServers = solrDatasource.getSolrServers().split(",");
            for (int i = 0; i < tempServers.length; i++) {
                try {
                    url = new URL("http://" + tempServers[i] + "/solr");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.connect();
                    if (connection != null) {
                        canConnection = true;
                        break;
                    }
                } catch (Exception e) {
                    logger.debug("获取solr连接失败的地址为：" + (url == null ? "" : url.toString()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            canConnection = false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return canConnection;
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        String solrServers = solrDatasource.getSolrServers();
        return getColumns(schemaName, solrServers);
    }

    public List<MetadataCol> getColumns(String collectionName, String solrServers) {
        if (StringUtils.isEmpty(collectionName) || StringUtils.isEmpty(solrServers)) {
            return null;
        }
        String response = "";
        String[] addresses = solrServers.split(",");
        for (String solrServer : addresses) {
            String url = "http://" + solrServer + "/solr/" + collectionName + "/schema/fields";
            response = SolrUtil.sendGet(url, "");
            if (StringUtils.isEmpty(response)) {
                continue;
            } else {
                break;
            }
        }
        JSONObject rs = JSONObject.parseObject(response);
        JSONArray fields = (JSONArray) rs.get("fields");
        List<MetadataCol> metadataCols = new ArrayList<>();
        MetadataCol mdCol = null;
        for (int i = 0; i < fields.size(); i++) {
            mdCol = new MetadataCol();
            mdCol.setSeq((short) i);
            mdCol.setName((String) fields.getJSONObject(i).get("name"));
            mdCol.setDescribe((String) fields.getJSONObject(i).get("name"));
            mdCol.setType(SolrUtil.getColType((String) fields.getJSONObject(i).get("type")));
            mdCol.setIndexed((boolean) fields.getJSONObject(i).get("indexed"));
            mdCol.setStored((boolean) fields.getJSONObject(i).get("stored"));
            mdCol.setPrimary(fields.getJSONObject(i).get("uniqueKey") == null ? false : true);
            metadataCols.add(mdCol);
        }
        return metadataCols;
    }
}
