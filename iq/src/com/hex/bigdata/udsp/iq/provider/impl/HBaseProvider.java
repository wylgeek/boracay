package com.hex.bigdata.udsp.iq.provider.impl;

import com.hex.bigdata.udsp.common.constant.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import com.hex.bigdata.udsp.iq.provider.impl.model.HBasePage;
import com.hex.bigdata.udsp.iq.provider.model.*;
import com.hex.goframe.util.GFStringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by junjiem on 2017-3-3.
 */
@Component("com.hex.bigdata.udsp.iq.provider.impl.HBaseProvider")
public class HBaseProvider implements Provider {

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

    private static Logger logger = LogManager.getLogger(HBaseProvider.class);
    private static final FastDateFormat format1 = FastDateFormat.getInstance("yyyy/MM/dd");
    private static final FastDateFormat format2 = FastDateFormat.getInstance("yyyy-MM-dd");
    private static final FastDateFormat format3 = FastDateFormat.getInstance("yyyyMMdd");
    private static final String rkSep = "|";
    private static final String startStr = "";
    private static final String stopStr = "|";
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
        //获取元数据返回字段
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns();

        String tbName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();

        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getPropertyMap());

        String startRow = getStartRow(queryColumns);
        String stopRow = getStopRow(queryColumns);
        Map<Integer, String> colMap = getColMap(metaReturnColumns);

        int maxSize = hBaseDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }
        byte[] family = hBaseDatasource.getFamilyName();
        byte[] qualifier = hBaseDatasource.getQulifierName();
        String fqSep = hBaseDatasource.getDsvSeprator();
        String dataType = hBaseDatasource.getFqDataType();
        List<Map<String, String>> list = null;
        HConnection conn = null;
        HTableInterface hTable = null;
        try {
            conn = getConnection(hBaseDatasource);
            hTable = conn.getTable(tbName);
            list = scan(hTable, startRow, stopRow, colMap, maxSize, family, qualifier, fqSep, dataType);
            // 排序处理
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
            response.setRecords(records);
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
            logger.warn(e.toString());
        } finally {
            if (hTable != null) {
                try {
                    hTable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                release(hBaseDatasource, conn);
            }
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime() + " recordsSize=" + response.getRecords().size());
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

        //获取元数据返回字段
        List<DataColumn> metaReturnColumns = metadata.getReturnColumns();

        String tbName = metadata.getTbName();
        Datasource datasource = metadata.getDatasource();

        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getPropertyMap());

        String startRow = getStartRow(queryColumns);
        String stopRow = getStopRow(queryColumns);
        Map<Integer, String> colMap = getColMap(metaReturnColumns);

        int maxSize = hBaseDatasource.getMaxNum();
        if (maxNum != 0) {
            maxSize = maxNum;
        }

        byte[] family = hBaseDatasource.getFamilyName();
        byte[] qualifier = hBaseDatasource.getQulifierName();
        String fqSep = hBaseDatasource.getDsvSeprator();
        String dataType = hBaseDatasource.getFqDataType();

        if (pageSize > maxSize) {
            pageSize = maxSize;
        }

        HBasePage hbasePage = new HBasePage();
        hbasePage.setPageIndex(pageIndex);
        hbasePage.setPageSize(pageSize);
        hbasePage.setStartRow(startRow);
        hbasePage.setStopRow(stopRow);

        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);

        HConnection conn = null;
        HTableInterface hTable = null;
        try {
            conn = getConnection(hBaseDatasource);
            hTable = conn.getTable(tbName);
            hbasePage = scanPage(hTable, hbasePage, colMap, family, qualifier, fqSep, dataType);
            List<Map<String, String>> list = hbasePage.getRecords();
            // 排序处理
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
            response.setRecords(records);
            page.setTotalCount(hbasePage.getTotalCount());
            response.setStatus(Status.SUCCESS);
            response.setStatusCode(StatusCode.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(e.getMessage());
            logger.warn(e.toString());
        } finally {
            if (hTable != null) {
                try {
                    hTable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                release(hBaseDatasource, conn);
            }
        }

        response.setPage(page);
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        logger.debug("consumeTime=" + response.getConsumeTime() + " recordsSize=" + response.getRecords().size());
        return response;
    }

    //-------------------------------------------分割线---------------------------------------------
    private synchronized HBaseConnectionPoolFactory getDataSource(HBaseDatasource datasource) {
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
            factory = new HBaseConnectionPoolFactory(config, datasource);
        }
        dataSourcePool.put(dsId, factory);
        return factory;
    }

    private HConnection getConnection(HBaseDatasource datasource) {
        try {
            return getDataSource(datasource).getConnection();
        } catch (Exception e) {
            logger.warn(ExceptionUtil.getMessage(e));
            return null;
        }
    }

    private void release(HBaseDatasource datasource, HConnection conn) {
        getDataSource(datasource).releaseConnection(conn);
    }

    private Configuration getConfig(HBaseDatasource datasource) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", datasource.getZkQuorum());
        conf.set("hbase.zookeeper.property.clientPort", datasource.getZkPort());
        return conf;
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
                if (str1.compareTo(str2) > 0) {
                    return -1;
                } else if (str1.compareTo(str2) == 0) {
                    return 0;
                }
                return 1;
            } else {
                if (str1.compareTo(str2) > 0) {
                    return 1;
                } else if (str1.compareTo(str2) == 0) {
                    return 0;
                }
                return -1;
            }
        } else {
            if (order != null && Order.DESC.equals(order)) {
                if (Double.valueOf(str1).compareTo(Double.valueOf(str2)) > 0) {
                    return -1;
                } else if (str1.compareTo(str2) == 0) {
                    return 0;
                }
                return 1;
            } else {
                if (Integer.valueOf(str1).compareTo(Integer.valueOf(str2)) > 0) {
                    return 1;
                } else if (str1.compareTo(str2) == 0) {
                    return 0;
                }
                return -1;
            }
        }
    }

    private String getStartRow(List<QueryColumn> queryColumns) {
        String startRow = getMd5Str(queryColumns);
        for (QueryColumn queryColumn : queryColumns) {
            String length = queryColumn.getLength();
            Operator operator = queryColumn.getOperator();
            String value = queryColumn.getValue();
            boolean must = queryColumn.isNeed();
            if (must && StringUtils.isBlank(value)) {
                throw new IllegalArgumentException("必输项值为空");
            }
            if (Operator.GE.equals(operator)) {
                startRow = rowStr(startRow, length, value);
                break;
            } else if (Operator.EQ.equals(operator)) {
                startRow = rowStr(startRow, length, value);
            }
        }
        return startRow;
    }

    private String getStopRow(List<QueryColumn> queryColumns) {
        String stopRow = getMd5Str(queryColumns);
        for (QueryColumn queryColumn : queryColumns) {
            String length = queryColumn.getLength();
            Operator operator = queryColumn.getOperator();
            String value = queryColumn.getValue();
            boolean must = queryColumn.isNeed();
            if (must && StringUtils.isBlank(value)) {
                throw new IllegalArgumentException("必输项值为空");
            }
            if (Operator.LE.equals(operator)) {
                stopRow = rowStr(stopRow, length, value);
                break;
            } else if (Operator.EQ.equals(operator)) {
                stopRow = rowStr(stopRow, length, value);
            }
        }
        return stopRow;
    }

    private String rowStr(String str, String length, String value) {
        if (StringUtils.isNotBlank(value)) {
            if (isValidDate(value)) {
                str += this.rkSep + replaceDateStr(value);
            } else {
                if (StringUtils.isNotBlank(length) && GFStringUtil.isNumeric(length)) {
                    int len = Integer.valueOf(length);
                    str += this.rkSep + realValue(value, len);
                } else {
                    str += this.rkSep + value;
                }
            }
        }
        return str;
    }

    private String getMd5Str(List<QueryColumn> queryColumns) {
        Collections.sort(queryColumns, new Comparator<QueryColumn>() {
            public int compare(QueryColumn obj1, QueryColumn obj2) {
                return obj1.getSeq().compareTo(obj2.getSeq());
            }
        });
        String sameStr = "";
        int count = 0;
        for (QueryColumn queryColumn : queryColumns) {
            Operator operator = queryColumn.getOperator();
            boolean must = queryColumn.isNeed();
            if (must && Operator.EQ.equals(operator)) {
                String value = queryColumn.getValue();
                if (value == null || value.equals("")) {
                    throw new IllegalArgumentException("必输项值为空");
                }
                if (count == 0)
                    sameStr += value;
                else
                    sameStr += this.rkSep + value;
                count++;
            } else {
                break;
            }
        }
        return md5_16(sameStr);
    }

    private Map<Integer, String> getColMap(List<DataColumn> returnColumns) {
        Map<Integer, String> colMap = new HashMap<Integer, String>();
        Collections.sort(returnColumns, new Comparator<DataColumn>() {
            public int compare(DataColumn obj1, DataColumn obj2) {
                return obj1.getSeq().compareTo(obj2.getSeq());
            }
        });
        for (int i = 0; i < returnColumns.size(); i++) {
            colMap.put(i + 1, returnColumns.get(i).getName());
        }
        return colMap;
    }

    private int count(HTableInterface table, String startRow,
                      String stopRow) throws IOException {
        Scan scan = new Scan();
        setRowScan(scan, startRow, stopRow);
        return count(table, scan);
    }

    private int count(HTableInterface table, Scan scan) throws IOException {
        scan.setCaching(500);
        scan.setCacheBlocks(false);
        scan.setFilter(new FirstKeyOnlyFilter());
        ResultScanner rs = table.getScanner(scan);
        int count = 0;
        while (rs.next() != null) {
            count++;
        }
        rs.close();
        return count;
    }

    private List<Map<String, String>> scan(HTableInterface table, Scan scan, Map<Integer, String> colMap,
                                           byte[] family, byte[] qualifier, String fqSep, String dataType) throws IOException {
        ResultScanner rs = table.getScanner(scan);
        return getMaps(rs, colMap, family, qualifier, fqSep, dataType);
    }

    private List<Map<String, String>> scan(HTableInterface table, String startRow, String stopRow, Map<Integer, String> colMap,
                                           long maxSize, byte[] family, byte[] qualifier, String fqSep, String dataType) throws IOException {
        Scan scan = new Scan();
        addColumn(scan, family, qualifier);
        setRowScan(scan, startRow, stopRow);
        scan.setFilter(new PageFilter(maxSize));
        return scan(table, scan, colMap, family, qualifier, fqSep, dataType);
    }

    private HBasePage scanPage(HTableInterface table, Scan scan, HBasePage HBasePage,
                               Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep, String dataType) throws IOException {
        int totalCount = count(table, HBasePage.getStartRow(), HBasePage.getStopRow());

        setRowScan(scan, HBasePage);
        ResultScanner rs = table.getScanner(scan);
        List<Map<String, String>> records = getMapsPage(rs, HBasePage, colMap, family, qualifier, fqSep, dataType);

        HBasePage.setRecords(records);
        HBasePage.setTotalCount(totalCount);
        return HBasePage;
    }

    private HBasePage scanPage(HTableInterface table, HBasePage HBasePage, Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep, String dataType) throws IOException {
        Scan scan = new Scan();
        addColumn(scan, family, qualifier);
        return scanPage(table, scan, HBasePage, colMap, family, qualifier, fqSep, dataType);
    }

    private void addColumn(Scan scan, byte[] family, byte[] qualifier) {
        scan.addColumn(family, qualifier);
    }

    private void setRowScan(Scan scan, String startRow, String stopRow) {
        if (startRow != null) {
            startRow = startRow + this.rkSep + this.startStr;
            scan.setStartRow(Bytes.toBytes(startRow));
        }
        if (stopRow != null) {
            stopRow = stopRow + this.rkSep + this.stopStr;
            scan.setStopRow(Bytes.toBytes(stopRow));
        }
    }

    private void setRowScan(Scan scan, HBasePage HBasePage) {
        // 添加行键范围
        String startRow = HBasePage.getStartRow();
        String stopRow = HBasePage.getStopRow();
        setRowScan(scan, startRow, stopRow);
        // 添加分页过滤器
        int pageIndex = HBasePage.getPageIndex();
        int pageSize = HBasePage.getPageSize();
        int scanNum = pageSize * pageIndex; // 扫描的数据条数
        Filter pageFilter = new PageFilter(scanNum);
        scan.setFilter(pageFilter);
    }

    private List<Map<String, String>> getMaps(ResultScanner rs, Map<Integer, String> colMap,
                                              byte[] family, byte[] qualifier, String fqSep, String dataType) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Result r : rs) {
            list.add(getMap(r, colMap, family, qualifier, fqSep, dataType));
        }
        rs.close();
        return list;
    }

    private List<Map<String, String>> getMapsPage(ResultScanner rs, HBasePage HBasePage,
                                                  Map<Integer, String> colMap, byte[] family, byte[] qualifier, String fqSep, String dataType) {
        int pageIndex = HBasePage.getPageIndex();
        int pageSize = HBasePage.getPageSize();
        int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        int count = 0;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Result r : rs) {
            count++;
            if (count > befNum) {
                list.add(getMap(r, colMap, family, qualifier, fqSep, dataType));
            }
        }
        rs.close();
        return list;
    }

    private Map<String, String> getMap(Result r, Map<Integer, String> colMap,
                                       byte[] family, byte[] qualifier, String fqSep, String dataType) {
        Map<String, String> map = new HashMap<String, String>();
        String fqVal = Bytes.toString(r.getValue(family, qualifier));
        if (fqVal == null) fqVal = "";
        if (dataType.equalsIgnoreCase("dsv")) { // 分隔符格式数据
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
        } else if (dataType.equalsIgnoreCase("json")) { // JSON MAP格式数据
            Map<String, Object> result = JSONUtil.parseJSON2Map(fqVal);
            Set<Integer> keys = colMap.keySet();
            for (Integer key : keys) {
                map.put(colMap.get(key), result.get(key).toString());
            }
        }
        return map;
    }

    //得到16位的MD5
    private String md5_16(String str) {
        return DigestUtils.md5Hex(str).substring(8, 24);
    }

    //得到需要的字符串
    private String realValue(String value, int length) {
        int len = 0;
        try {
            len = countCode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (len < length) {
            for (int i = 0; i < length - len; i++) {
                value = value + ' ';
            }
        }
        return value;
    }

    // 获取不同编码的字符串长度
    private int countCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes(code).length;
    }

    // 转为8位日期
    private String replaceDateStr(String dataStr) {
        dataStr = dataStr.replaceAll("-", "");
        dataStr = dataStr.replaceAll("/", "");
        return dataStr;
    }

    private boolean isValidDate(String dataStr) {
        boolean status = true;
        if (dataStr.length() == 8) {
            try {
                format3.parse(dataStr);
            } catch (ParseException e) {
                status = false;
            }
        } else if (dataStr.length() == 10) {
            try {
                format2.parse(dataStr);
            } catch (ParseException e) {
                try {
                    format1.parse(dataStr);
                } catch (ParseException e1) {
                    status = false;
                }
            }
        } else {
            status = false;
        }
        return status;
    }

    public boolean testDatasource(Datasource datasource) {
        boolean canConnection = true;
        HBaseDatasource hBaseDatasource = new HBaseDatasource(datasource.getProperties());
        HConnection hConnection = null;
        try {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", hBaseDatasource.getZkQuorum());
            conf.set("hbase.zookeeper.property.clientPort", hBaseDatasource.getZkPort());
            conf.set("hbase.rpc.timeout", "2");
            conf.set("hbase.client.retries.number", "3");
            conf.set("zookeeper.recovery.retry", "1");
            hConnection = HConnectionManager.createConnection(conf);
            if (hConnection == null || hConnection.isAborted()) {
                canConnection = false;
            } else {
                //尝试获取当中的表，如果获取抛异常则获取连接失败
                hConnection.getAdmin().tableExists(TableName.valueOf("TEST"));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            canConnection = false;
        } finally {
            if (hConnection != null) {
                try {
                    hConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return canConnection;
    }

    @Override
    public List<MetadataCol> columnInfo(Datasource datasource, String schemaName) {
        return null;
    }
}
