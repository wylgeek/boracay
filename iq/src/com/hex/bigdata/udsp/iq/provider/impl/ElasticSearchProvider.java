package com.hex.bigdata.udsp.iq.provider.impl;

import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.provider.model.Result;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.factory.ElasticSearchConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.ELSearchDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.elsearch.*;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.bigdata.udsp.iq.provider.model.Metadata;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component("com.hex.bigdata.udsp.iq.provider.impl.ElasticSearchProvider")
public class ElasticSearchProvider implements Provider {

    private static Logger logger = LogManager.getLogger(ElasticSearchProvider.class);

    private static Map<String, ElasticSearchConnectionPoolFactory> dataSourcePool;

    @Override
    public IqResponse query(IqRequest request) {
        long bef = System.currentTimeMillis();
        IqResponse response = new IqResponse();
        response.setRequest(request);

        Application application = request.getApplication();
        int maxNum = application.getMaxNum();
        Metadata metadata = application.getMetadata();
        List<QueryColumn> queryColumns = application.getQueryColumns();
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        List<OrderColumn> orderColumns = application.getOrderColumns();
        //表名，索引名称.类型名称
        String schemaName = metadata.getTbName();

        //数据源信息
        Datasource datasource = metadata.getDatasource();
        ELSearchDatasource elSearchDatasource = new ELSearchDatasource(datasource.getPropertyMap());

        //最大查询数量
        int maxSize = elSearchDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        Page page = new Page();
        page.setPageIndex(0);
        page.setPageSize(maxSize);

        String queryString = getQueryString(queryColumns, orderColumns, returnColumns, page);

        try {
            ELSearchPage elSearchPage = search(schemaName, elSearchDatasource, queryString, returnColumns);
            List<Map<String, Object>> list = elSearchPage.getRecords();
            List<Result> records = new ArrayList<Result>();
            for (Map<String, Object> map : list) {
                Result result = new Result();
                result.set(map);
                records.add(result);
            }
            response.setRecords(records);
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.toString());
        }
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        return response;
    }

    @Override
    public IqResponse query(IqRequest request, int pageIndex, int pageSize) {

        long bef = System.currentTimeMillis();
        IqResponse response = new IqResponse();
        response.setRequest(request);

        Application application = request.getApplication();
        int maxNum = application.getMaxNum();
        Metadata metadata = application.getMetadata();
        List<QueryColumn> queryColumns = application.getQueryColumns();
        List<ReturnColumn> returnColumns = application.getReturnColumns();
        List<OrderColumn> orderColumns = application.getOrderColumns();
        //表名，索引名称.类型名称
        String schemaName = metadata.getTbName();

        //数据源信息
        Datasource datasource = metadata.getDatasource();
        ELSearchDatasource elSearchDatasource = new ELSearchDatasource(datasource.getPropertyMap());

        int maxSize = elSearchDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        if (pageSize > maxSize) {
            pageSize = maxSize;
        }

        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        String queryString = getQueryString(queryColumns, orderColumns, returnColumns, page);

        try {
            ELSearchPage elSearchPage = search(schemaName, elSearchDatasource, queryString, returnColumns);
            List<Map<String, Object>> list = elSearchPage.getRecords();
            List<Result> records = new ArrayList<Result>();
            if (null != list && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    Result result = new Result();
                    result.set(map);
                    records.add(result);
                }
            }
            response.setRecords(records);
            response.setStatus(Status.SUCCESS);
            page.setTotalCount(elSearchPage.getTotalCount());
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (Exception e) {
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.toString());
        }
        response.setPage(page);
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        return response;
    }

    private ELSearchPage search(String schemaName, ELSearchDatasource datasource, String queryString, List<ReturnColumn> returnColumns) {
        ELSearchPage elSearchPage = new ELSearchPage();
        RestClient restClient = null;
        NStringEntity stringEntity = null;
        try {
            stringEntity = new NStringEntity(queryString, "utf-8");
            restClient = getConnection(datasource);
            logger.debug(queryString);
            String schemaUrl = schemaName;
            String[] schemaArray = schemaName.split("\\.");
            if (schemaArray.length == 2) {
                schemaUrl = schemaArray[0] + "/" + schemaArray[1];
            }
            Response response = restClient.performRequest("GET", "/" + schemaUrl + "/_search", Collections.<String, String>emptyMap(), stringEntity);
            String returnString = EntityUtils.toString(response.getEntity());
            JSONObject returnJsonObject = JSONUtil.parseJSON2Obj(returnString, JSONObject.class);
            JSONObject errorObject = (JSONObject) returnJsonObject.get("error");
            logger.debug("search_result:" + returnString);
            if (null != errorObject) {
                //查询报错抛出异常
                String errortype = (String) errorObject.get("type");
                String errorReason = (String) errorObject.get("reason");
                throw new RuntimeException(errortype + ":" + errorReason);
            }
            ELSearchResponse eLsearchResponse = JSONObject.parseObject(returnString, ELSearchResponse.class);
            ELOuterHits elOuterHits = eLsearchResponse.getHits();
            List<ELInnerHits> elInnerHits = elOuterHits.getHits();
            Map<String, String> colMap = getColMap(returnColumns);
            List<Map<String, Object>> recordes = new ArrayList<Map<String, Object>>();
            Map<String, Object> recorde = null;
            Map<String, Object> source = null;
            String label = null;
            for (ELInnerHits item : elInnerHits) {
                source = item.get_source();
                recorde = new HashMap<>();
                for (Map.Entry<String, Object> entity : source.entrySet()) {
                    label = colMap.get(entity.getKey());
                    if (StringUtils.isNotBlank(label)) {
                        recorde.put(label, entity.getValue());
                    }
                }
                recordes.add(recorde);
            }
            elSearchPage.setRecords(recordes);
            //设置总量
            elSearchPage.setTotalCount(elOuterHits.getTotal());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (restClient != null) {
                release(datasource, restClient);
            }
        }
        return elSearchPage;
    }

    private Map<String, String> getColMap(List<ReturnColumn> returnColumns) {
        Map<String, String> colMap = new HashMap<>();
        for (ReturnColumn column : returnColumns) {
            colMap.put(column.getName(), column.getLabel());
        }
        return colMap;
    }

    @Override
    public boolean testDatasource(Datasource datasource) {
        ELSearchDatasource elSearchDatasource = new ELSearchDatasource(datasource.getProperties());
        String[] tempServers = elSearchDatasource.getElasticsearchServers().split(",");
        if (tempServers.length == 0) {
            return false;
        }
        HttpHost[] hosts = getHttpHosts(tempServers);
        RestClient restClient = RestClient.builder(hosts).build();
        Response response = null;
        try {
            response = restClient.performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
            JSONObject responseObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            String tagline = (String) responseObject.get("tagline");
            if (StringUtils.isNotBlank(tagline)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        List<MetadataCol> list = null;
        MetadataCol col = null;
        ELSearchDatasource elSearchDatasource = new ELSearchDatasource(datasource.getPropertyMap());
        RestClient restClient = null;
        NStringEntity stringEntity = null;
        Response response = null;
        try {
            stringEntity = new NStringEntity("", "utf-8");
            restClient = getConnection(elSearchDatasource);
            String schemaUrl = schemaName;
            String[] schemaArray = schemaName.split("\\.");
            if (schemaArray.length == 2) {
                schemaUrl = schemaArray[0] + "/" + schemaArray[1];
            }
            response = restClient.performRequest("GET", "/" + schemaUrl, Collections.<String, String>emptyMap(), stringEntity);
            String returnStr = EntityUtils.toString(response.getEntity());
            JSONObject returnJsonObject = JSONUtil.parseJSON2Obj(returnStr, JSONObject.class);
            JSONObject errorObject = (JSONObject) returnJsonObject.get("error");
            logger.debug("search_result:" + returnStr);
            if (null != errorObject) {
                throw new RuntimeException(returnStr);
            }

            JSONObject defaultObj = null;
            if (schemaArray.length == 2) {
                JSONObject schemaObj = (JSONObject) returnJsonObject.get(schemaArray[0]);
                JSONObject mappingsObj = (JSONObject) schemaObj.get("mappings");
                defaultObj = (JSONObject) mappingsObj.get(schemaArray[1]);
            } else {
                JSONObject schemaObj = (JSONObject) returnJsonObject.get(schemaName);
                JSONObject mappingsObj = (JSONObject) schemaObj.get("mappings");
                defaultObj = (JSONObject) mappingsObj.get("default");
            }
            JSONObject propertiesObj = (JSONObject) defaultObj.get("properties");
            String propertiesStr = JSONUtil.parseObj2JSON(propertiesObj);
            Map<String, ELSearchProperty> properties = JSONUtil.parseJSON2Map(propertiesStr, ELSearchProperty.class);
            list = new ArrayList<>();
            short i = 0;
            for (Map.Entry<String, ELSearchProperty> entry : properties.entrySet()) {
                String name = entry.getKey();
                ELSearchProperty property = entry.getValue();
                String index = property.getIndex();
                String type = property.getType();
                boolean store = property.isStore();

                col = new MetadataCol();
                col.setSeq(i);
                col.setName(name);
                col.setType(getColType(type));
                col.setIndexed(StringUtils.isNotBlank(index) ? true : false);
                col.setStored(store);

                list.add(col);
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (restClient != null) {
                release(elSearchDatasource, restClient);
            }
        }
        return list;
    }

    public static DataType getColType(String type) {
        type = type.toUpperCase();
        DataType dataType = null;
        switch (type) {
            case "STRING":
                dataType = DataType.STRING;
                break;
            case "INT":
                dataType = DataType.INT;
                break;
            case "FLOAT":
                dataType = DataType.FLOAT;
                break;
            case "DOUBLE":
                dataType = DataType.DOUBLE;
                break;
            case "DATE":
                dataType = DataType.TIMESTAMP;
                break;
            case "BOOLEAN":
                dataType = DataType.BOOLEAN;
                break;
            default:
                dataType = DataType.STRING;
        }
        return dataType;
    }

    private void release(ELSearchDatasource datasource, RestClient restClient) {
        getDataSource(datasource).releaseConnection(restClient);
    }

    private RestClient getConnection(ELSearchDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private synchronized ElasticSearchConnectionPoolFactory getDataSource(ELSearchDatasource datasource) {
        String dsId = datasource.getId();
        if (dataSourcePool == null) {
            dataSourcePool = new HashMap<String, ElasticSearchConnectionPoolFactory>();
        }
        ElasticSearchConnectionPoolFactory factory = dataSourcePool.get(dsId);
        if (factory == null) {
            GenericObjectPool.Config config = new GenericObjectPool.Config();
            config.lifo = true;
            config.minIdle = 1;
            config.maxActive = 10;
            config.maxWait = 3000;
            config.maxActive = 5;
            config.timeBetweenEvictionRunsMillis = 30000;
            config.testWhileIdle = true;
            config.testOnBorrow = false;
            config.testOnReturn = false;
            factory = new ElasticSearchConnectionPoolFactory(config, datasource.getElasticsearchServers());
            dataSourcePool.put(dsId, factory);
        }
        return factory;
    }

    /**
     * 获取HttpHost对象
     *
     * @param tempServers
     * @return
     */
    private HttpHost[] getHttpHosts(String[] tempServers) {
        HttpHost[] hosts = new HttpHost[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            String item = tempServers[i];
            String[] array = item.split(":");
            hosts[i] = new HttpHost(array[0], Integer.valueOf(array[1]), "http");
        }
        return hosts;
    }

    public static void main(String[] args) {
        //getQueryString(getQueryColumns(), getOrderColumns());
    }

    /**
     * 根据查询参数、排序参数，分页参数，获取查询实体
     *
     * @param queryColumns
     * @param orderColumns
     * @param page
     * @return
     */
    public static String getQueryString(List<QueryColumn> queryColumns, List<OrderColumn> orderColumns, List<ReturnColumn> returnColumns, Page page) {
        JSONObject topObject = new JSONObject();
        JSONObject queryObject = new JSONObject();
        JSONObject boolObject = new JSONObject();
        List<JSONObject> mustObjects = new ArrayList<JSONObject>();
        List<JSONObject> mustNotObjects = new ArrayList<JSONObject>();
        JSONObject termObject = null;
        JSONObject rangeObject = null;
        JSONObject itemObject = null;
        for (QueryColumn queryColumn : queryColumns) {
            String name = queryColumn.getName();
            String value = queryColumn.getValue();
            Operator operator = queryColumn.getOperator();
            if (StringUtils.isNotBlank(value)) {
                if (Operator.EQ.equals(operator)) {
                    termObject = new JSONObject();
                    itemObject = new JSONObject();
                    itemObject.put(name, value);
                    termObject.put("term", itemObject);
                    mustObjects.add(termObject);
                } else if (Operator.GT.equals(operator)) {
                    rangeObject = new JSONObject();
                    itemObject = new JSONObject();
                    JSONObject operateOject = new JSONObject();
                    operateOject.put("gt", value);
                    itemObject.put(name, operateOject);
                    rangeObject.put("range", itemObject);
                    mustObjects.add(rangeObject);
                } else if (Operator.LT.equals(operator)) {
                    rangeObject = new JSONObject();
                    itemObject = new JSONObject();
                    JSONObject operateOject = new JSONObject();
                    operateOject.put("lt", value);
                    itemObject.put(name, operateOject);
                    rangeObject.put("range", itemObject);
                    mustObjects.add(rangeObject);
                } else if (Operator.GE.equals(operator)) {
                    rangeObject = new JSONObject();
                    itemObject = new JSONObject();
                    JSONObject operateOject = new JSONObject();
                    operateOject.put("gte", value);
                    itemObject.put(name, operateOject);
                    rangeObject.put("range", itemObject);
                    mustObjects.add(rangeObject);
                } else if (Operator.LE.equals(operator)) {
                    rangeObject = new JSONObject();
                    itemObject = new JSONObject();
                    JSONObject operateOject = new JSONObject();
                    operateOject.put("lte", value);
                    itemObject.put(name, operateOject);
                    rangeObject.put("range", itemObject);
                    mustObjects.add(rangeObject);
                } else if (Operator.RLIKE.equals(operator)) {
                    JSONObject rLikeObject = new JSONObject();
                    itemObject = new JSONObject();
                    itemObject.put(name, value);
                    rLikeObject.put("match_phrase_prefix", itemObject);
                    mustObjects.add(rLikeObject);
                } else if (Operator.LK.equals(operator)) {
                    JSONObject likeObject = new JSONObject();
                    itemObject = new JSONObject();
                    itemObject.put(name, value + "*");
                    likeObject.put("wildcard", itemObject);
                    mustObjects.add(likeObject);
                } else if (Operator.IN.equals(operator)) {
                    JSONObject inObject = new JSONObject();
                    itemObject = new JSONObject();
                    itemObject.put(name, value.split(","));
                    inObject.put("terms", itemObject);
                    mustObjects.add(inObject);
                } else if (Operator.NE.equals(operator)) {
                    termObject = new JSONObject();
                    itemObject = new JSONObject();
                    itemObject.put(name, value);
                    termObject.put("term", itemObject);
                    mustNotObjects.add(termObject);
                }
            }
        }

        List<JSONObject> orderObjects = new ArrayList<JSONObject>();
        JSONObject orderObject = null;
        //排序
        for (OrderColumn orderColumn : orderColumns) {
            orderObject = new JSONObject();
            itemObject = new JSONObject();
            itemObject.put("order", orderColumn.getOrder().getValue());
            orderObject.put(orderColumn.getName(), itemObject);
            orderObjects.add(orderObject);
            topObject.put("sort", orderObjects);
        }
        boolObject.put("must", mustObjects);
        boolObject.put("must_not", mustNotObjects);
        queryObject.put("bool", boolObject);

        topObject.put("query", queryObject);

        //Elasticsearch深度分页应该被禁止，推荐使用scroll 的方式遍历数据。
        if (null != page) {
            int pageIndex = page.getPageIndex() == 0 ? 0 : page.getPageIndex() - 1;
            int pageSize = page.getPageSize();
            topObject.put("from", pageIndex * pageSize);
            topObject.put("size", pageSize);
        }
        if (null != returnColumns && returnColumns.size() > 0) {
            String[] rColumns = new String[returnColumns.size()];
            for (int i = 0; i < returnColumns.size(); i++) {
                rColumns[i] = returnColumns.get(i).getName();
            }
            topObject.put("_source", rColumns);
        }

        //System.out.println(JSONObject.toJSONString(topObject));
        return JSONObject.toJSONString(topObject);
    }

    public static List<OrderColumn> getOrderColumns() {
        List<OrderColumn> orderColumns = new ArrayList<OrderColumn>();
        OrderColumn orderColumn1 = new OrderColumn();
        orderColumn1.setSeq(new Short("1"));
        orderColumn1.setName("acct_no");
        orderColumn1.setOrder(Order.ASC);
        orderColumn1.setDescribe("客户账号");
        orderColumn1.setType(DataType.STRING);
        orderColumns.add(orderColumn1);
        OrderColumn orderColumn2 = new OrderColumn();
        orderColumn2.setSeq(new Short("2"));
        orderColumn2.setName("age");
        orderColumn2.setOrder(Order.ASC);
        orderColumn2.setDescribe("客户年龄");
        orderColumn2.setType(DataType.INT);
        orderColumns.add(orderColumn2);
        return orderColumns;
    }

    public static List<QueryColumn> getQueryColumns() {

        List<QueryColumn> queryColumns = new ArrayList<QueryColumn>();

        //时间范围查询-开始日期
        QueryColumn queryColumn1 = new QueryColumn();
        queryColumn1.setSeq(new Short("1"));
        queryColumn1.setName("acct_zcrq");
        queryColumn1.setValue("");
        queryColumn1.setDescribe("开始日期");
        queryColumn1.setType(DataType.STRING);
        queryColumn1.setNeed(true);
        queryColumn1.setOperator(Operator.GE);
        queryColumn1.setLabel("start_zcrq");
        queryColumn1.setValue("2016-05-22");
        queryColumns.add(queryColumn1);

        //时间范围查询-开始日期
        QueryColumn queryColumn2 = new QueryColumn();
        queryColumn2.setSeq(new Short("2"));
        queryColumn2.setName("acct_zcrq");
        queryColumn2.setDescribe("开始日期");
        queryColumn2.setType(DataType.STRING);
        queryColumn2.setNeed(true);
        queryColumn2.setOperator(Operator.LE);
        queryColumn2.setLabel("end_zcrq");
        queryColumn2.setValue("2017-05-22");
        queryColumns.add(queryColumn2);

        //等于操作
        QueryColumn queryColumn3 = new QueryColumn();
        queryColumn3.setSeq(new Short("3"));
        queryColumn3.setName("acct_zcrq");
        queryColumn3.setDescribe("注册日期");
        queryColumn3.setType(DataType.STRING);
        queryColumn3.setNeed(true);
        queryColumn3.setOperator(Operator.EQ);
        queryColumn3.setLabel("end_zcrq");
        queryColumn3.setValue("2016-07-22");
        queryColumns.add(queryColumn3);

        //大于等于操作
        QueryColumn queryColumn4 = new QueryColumn();
        queryColumn4.setSeq(new Short("4"));
        queryColumn4.setName("age");
        queryColumn4.setDescribe("年龄");
        queryColumn4.setType(DataType.INT);
        queryColumn4.setNeed(true);
        queryColumn4.setOperator(Operator.GE);
        queryColumn4.setLabel("age");
        queryColumn4.setValue("25");
        queryColumns.add(queryColumn4);

        //in查询操作
        QueryColumn queryColumn5 = new QueryColumn();
        queryColumn5.setSeq(new Short("5"));
        queryColumn5.setName("acct_no");
        queryColumn5.setDescribe("客户账号");
        queryColumn5.setType(DataType.STRING);
        queryColumn5.setNeed(true);
        queryColumn5.setOperator(Operator.IN);
        queryColumn5.setLabel("acct_no");
        queryColumn5.setValue("1000008,1000009");
        queryColumns.add(queryColumn5);

        //模糊匹配 wildcard查询
        QueryColumn queryColumn6 = new QueryColumn();
        queryColumn6.setSeq(new Short("6"));
        queryColumn6.setName("acct_no");
        queryColumn6.setDescribe("客户账号");
        queryColumn6.setType(DataType.STRING);
        queryColumn6.setNeed(true);
        queryColumn6.setOperator(Operator.LK);
        queryColumn6.setLabel("acct_no");
        queryColumn6.setValue("100000");
        queryColumns.add(queryColumn6);

        //like右查询 prefix查询
        QueryColumn queryColumn7 = new QueryColumn();
        queryColumn7.setSeq(new Short("7"));
        queryColumn7.setName("acct_no");
        queryColumn7.setDescribe("客户账号");
        queryColumn7.setType(DataType.STRING);
        queryColumn7.setNeed(true);
        queryColumn7.setOperator(Operator.RLIKE);
        queryColumn7.setLabel("acct_no");
        queryColumn7.setValue("1000008");
        queryColumns.add(queryColumn7);

        //不等于
        QueryColumn queryColumn8 = new QueryColumn();
        queryColumn8.setSeq(new Short("7"));
        queryColumn8.setName("acct_no");
        queryColumn8.setDescribe("客户账号");
        queryColumn8.setType(DataType.STRING);
        queryColumn8.setNeed(true);
        queryColumn8.setOperator(Operator.NE);
        queryColumn8.setLabel("acct_no");
        queryColumn8.setValue("1000010");
        queryColumns.add(queryColumn8);


        return queryColumns;
    }
}
