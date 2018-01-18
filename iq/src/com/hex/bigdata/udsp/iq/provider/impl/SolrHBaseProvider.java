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
import com.hex.bigdata.udsp.iq.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.*;
import com.hex.bigdata.udsp.iq.provider.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by junjiem on 2017-2-15.
 */
@Component("com.hex.bigdata.udsp.iq.provider.impl.SolrHBaseProvider")
public class SolrHBaseProvider implements Provider {

    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Logger logger = LoggerFactory.getLogger(SolrHBaseProvider.class);

    private static Map<String, HBaseConnectionPoolFactory> dataSourcePool;

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
        SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource(datasource.getPropertyMap());
        int maxSize = solrHBaseDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        SolrQuery query = getSolrQuery(maxSize, queryColumns, orderColumns);
        Map<Integer, String> colMap = getColMap(metaReturnColumns);

        try {
            List<Map<String, String>> list = search(tbName, query, colMap, solrHBaseDatasource);

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
        SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource(datasource.getPropertyMap());

        int maxSize = solrHBaseDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        if (pageSize > maxSize) {
            pageSize = maxSize;
        }
        SolrQuery query = getSolrQuery(pageIndex, pageSize, queryColumns, orderColumns);
        Map<Integer, String> colMap = getColMap(metaReturnColumns);

        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        try {
            HBasePage hbasePage = searchPage(tbName, query, pageIndex, pageSize, colMap, solrHBaseDatasource);

            List<Map<String, String>> list = hbasePage.getRecords();

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

            response.setRecords(records);

            page.setTotalCount(hbasePage.getTotalCount());

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
    private synchronized HBaseConnectionPoolFactory getDataSource(SolrHBaseDatasource datasource) {
        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getPropertyMap());
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, HBaseConnectionPoolFactory>();
        }
        HBaseConnectionPoolFactory factory = dataSourcePool.get(dsId);
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
            factory = new HBaseConnectionPoolFactory(config, hBaseDatasource);
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    private HConnection getConnection(SolrHBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return null;
        }
    }

    private void release(SolrHBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    private Configuration getConfig(SolrHBaseDatasource datasource) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", datasource.getZkQuorum());
        conf.set("hbase.zookeeper.property.clientPort", datasource.getZkPort());
        return conf;
    }

    private SolrQuery getSolrQuery(int rows, List<QueryColumn> queryColumns, List<OrderColumn> orderColumns) {
        return new SolrQuery().setQuery(getQuery(queryColumns)) //
                .setStart(0) //
                .setRows(rows) //
                .setSorts(getSort(orderColumns));
    }

    private SolrQuery getSolrQuery(int pageIndex, int pageSize, List<QueryColumn> queryColumns, List<OrderColumn> orderColumns) {
        return new SolrQuery().setQuery(getQuery(queryColumns)) //
                .setStart((pageIndex - 1) * pageSize) //
                .setRows(pageSize) //
                .setSorts(getSort(orderColumns));
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
                        if (i < stringArray.length - 1) {
                            sb.append(" or ");
                        }
                        if (i == stringArray.length - 1) {
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

    private Map<Integer, String> getColMap(List<DataColumn> returnColumns) {
        Map<Integer, String> colMap = new HashMap<Integer, String>();
        Collections.sort(returnColumns, new Comparator<DataColumn>() {
            public int compare(DataColumn obj1, DataColumn obj2) {
                return new Integer(obj1.getSeq()).compareTo(new Integer(obj2.getSeq()));
            }
        });
        for (int i = 0; i < returnColumns.size(); i++) {
            colMap.put(i + 1, returnColumns.get(i).getName());
        }
        return colMap;
    }

    private HBasePage searchPage(String tableName, SolrQuery query, int pageIndex, int pageSize, Map<Integer, String> colMap, SolrHBaseDatasource datasource) throws Exception {
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        SolrHBasePage solrHBasePage = searchPage(tableName, query, pageIndex, pageSize, datasource);
        for (String id : solrHBasePage.getRecords()) {
            records.add(get(tableName, id, colMap, datasource));
        }
        return new HBasePage(records, solrHBasePage.getPageIndex(), solrHBasePage.getPageSize(), solrHBasePage.getTotalCount());
    }

    private List<Map<String, String>> search(String tableName, SolrQuery query, Map<Integer, String> colMap, SolrHBaseDatasource datasource) throws Exception {
        List<String> list = search(tableName, query, datasource);
        List<Map<String, String>> records = new ArrayList<Map<String, String>>();
        for (String id : list) {
            records.add(get(tableName, id, colMap, datasource));
        }
        return records;
    }

    // ---------------------------Solr------------------------------
    public SolrHBasePage searchPage(String collectionName, SolrQuery query, int pageIndex, int pageSize, SolrHBaseDatasource datasource) {
        // 获取QueryResponse
        QueryResponse rsp = getQueryResponse(collectionName, query, datasource);
        if (rsp == null) {
            return null;
        }
        // 总行数
        SolrDocumentList results = rsp.getResults();
        int totalCount = (int) results.getNumFound();
        // 唯一键集合
        List<String> records = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++) {
            String id = (String) results.get(i).get("id");
            if (StringUtils.isNotBlank(id)) {
                records.add(id);
            }
        }
        return new SolrHBasePage(records, pageIndex, pageSize, totalCount);
    }

    public List<String> search(String collectionName, SolrQuery query, SolrHBaseDatasource datasource) {
        // 获取QueryResponse
        QueryResponse rsp = getQueryResponse(collectionName, query, datasource);
        if (rsp == null) {
            return null;
        }
        // ID集合
        SolrDocumentList results = rsp.getResults();
        List<String> records = new ArrayList<String>();
        for (int i = 0; i < results.size(); i++) {
            String id = (String) results.get(i).get("id");
            if (StringUtils.isNotBlank(id)) {
                records.add(id);
            }
        }
        return records;
    }

    private QueryResponse getQueryResponse(String collectionName, SolrQuery query, SolrHBaseDatasource datasource) {
        SolrServer solrServer = getSolrServer(collectionName, datasource);
        if (solrServer == null) {
            return null;
        }
        QueryResponse res = null;
        try {
            res = solrServer.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return res;
    }

    private SolrServer getSolrServer(String collectionName, SolrHBaseDatasource datasource) {
        if (StringUtils.isBlank(collectionName)) {
            throw new IllegalArgumentException("collection name不能为空");
        }
        String[] tempServers = datasource.getSolrServers().split(",");
        String[] servers = new String[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            servers[i] = "http://" + tempServers[i] + "/solr/" + collectionName;
        }
        SolrServer solrServer = null;
        try {
            solrServer = new LBHttpSolrServer(servers);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return solrServer;
    }

    // ------------------------------HBase----------------------------------
    private Map<String, String> get(String tableName, String rowkey, Map<Integer, String> colMap, SolrHBaseDatasource datasource) throws Exception {
        HConnection conn = null;
        HTableInterface table = null;
        Map<String, String> map;
        try {
            conn = getConnection(datasource);
            table = conn.getTable(tableName);
            byte[] family = datasource.getFamilyName();
            byte[] qualifier = datasource.getQulifierName();
            String fqSep = datasource.getSeprator();
            map = get(table, rowkey, colMap, family, qualifier, fqSep);
        } finally {
            if (table != null) {
                table.close();
            }
            if (conn != null) {
                release(datasource, conn);
            }
        }
        return map;
    }

    private Map<String, String> get(HTableInterface table, String rowkey, Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep) throws Exception {
        Get get = new Get(Bytes.toBytes(rowkey));
        addColumn(get, family, qualifier);
        return get(table, get, colMap, family, qualifier, fqSep);
    }

    private Map<String, String> get(HTableInterface table, Get get, Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep) throws Exception {
        Result r = table.get(get);
        return getMap(r, colMap, family, qualifier, fqSep);
    }

    private void addColumn(Get get, byte[] family, byte[] qualifier) {
        get.addColumn(family, qualifier);
    }

    private Map<String, String> getMap(Result r, Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep) {
        Map<String, String> map = new HashMap<String, String>();
        String fqVal = Bytes.toString(r.getValue(family, qualifier));
        if (fqVal == null) fqVal = "";
        String[] fqVals = fqVal.split(fqSep, -1);
        // 注：如果上线后又修改需求，需要添加字段，则该检查需要注释掉
//        if (colMap.size() != fqVals.length) {
//            throw new RuntimeException("查询结果数与字段数不一致！");
//        }
        for (int i = 0; i < fqVals.length; i++) {
            String colName = colMap.get(i + 1);
            if (colName != null) {
                map.put(colName, JSONUtil.encode(fqVals[i]));
            }
        }
        return map;
    }

    public boolean testDatasource(Datasource datasource) {
        boolean HbaseCanConnection = true;
        boolean SolrCanConnection = false;
        SolrHBaseDatasource solrHBaseDatasource = new SolrHBaseDatasource(datasource.getProperties());
        HConnection hConnection = null;
        HttpURLConnection connection = null;
        URL url = null;
        try {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", solrHBaseDatasource.getZkQuorum());
            conf.set("hbase.zookeeper.property.clientPort", solrHBaseDatasource.getZkPort());
            conf.set("hbase.rpc.timeout", "2");
            conf.set("hbase.client.retries.number", "3");
            conf.set("zookeeper.recovery.retry", "1");
            hConnection = HConnectionManager.createConnection(conf);
            if (hConnection == null || hConnection.isAborted()) {
                HbaseCanConnection = false;
            } else {
                //尝试获取当中的表，如果获取抛异常则获取连接失败
                hConnection.getAdmin().tableExists(TableName.valueOf("TEST"));
            }
            if (HbaseCanConnection == true) {
                //测试获取solr连接
                String[] tempServers = solrHBaseDatasource.getSolrServers().split(",");

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
                            SolrCanConnection = true;
                            break;
                        }
                    } catch (Exception e) {
                        logger.debug("获取solr连接失败的地址为：" + (url == null ? "" : url.toString()));
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            HbaseCanConnection = false;
        } finally {
            if (hConnection != null) {
                try {
                    hConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                connection.disconnect();
            }
        }

        return HbaseCanConnection && SolrCanConnection;
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
