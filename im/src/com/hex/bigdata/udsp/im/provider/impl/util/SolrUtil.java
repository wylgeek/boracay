package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.constant.Operator;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.SolrDatasource;
import com.hex.bigdata.udsp.im.provider.impl.util.model.Page;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.zookeeper.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hj on 2017/9/11.
 */
public class SolrUtil {
    private static Logger logger = LogManager.getLogger(SolrUtil.class);

    private static final String SOLR_CONFIGS_TEMPLATE = "goframe/im/solr/template/conf";
    private static final String SOLR_CONFIGS_TEMPLATE_SCHEMA = SOLR_CONFIGS_TEMPLATE + "/schema.xml";
    private static final String SOLR_CONFIG_ZOOKEEPER_DIR = "/configs";

    /**
     * 上传配置文件
     *
     * @param metadata
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void uploadSolrConfig(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        ZooKeeper zkClient = getZkClient(solrDatasource.getSolrUrl());
        upload(zkClient, getSolrConfigPath(), SOLR_CONFIG_ZOOKEEPER_DIR, metadata.getTbName(), metadata.getMetadataCols());
    }

    /**
     * 获取zookeeper链接
     *
     * @param zkConnectString
     * @return
     * @throws IOException
     */
    public static ZooKeeper getZkClient(String zkConnectString) {
        ZooKeeper zkClient = null;
        try {
            CountDownLatch connectedLatch = new CountDownLatch(1);
            Watcher watcher = new ConnectedWatcher(connectedLatch);
            zkClient = new ZooKeeper(zkConnectString, 20000, watcher);
            waitUntilConnected(zkClient, connectedLatch);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zkClient;
    }

    public static void waitUntilConnected(ZooKeeper zooKeeper, CountDownLatch connectedLatch) {
        if (ZooKeeper.States.CONNECTING == zooKeeper.getState()) {
            try {
                connectedLatch.await();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static class ConnectedWatcher implements Watcher {
        private CountDownLatch connectedLatch;

        ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;
        }

        @Override
        public void process(WatchedEvent event) {
            if (event.getState() == Event.KeeperState.SyncConnected) {
                connectedLatch.countDown();
            }
        }
    }

    /**
     * 上传配置目录及文件（递归）
     *
     * @param zkClient
     * @param filePath       配置文件路径
     * @param solrConfigPath 上传zookeeper的路径
     * @param configName     配置名称 collection1
     * @param metadataCols
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void upload(ZooKeeper zkClient, String filePath, String solrConfigPath, String configName, List<MetadataCol> metadataCols) throws Exception {
        File file = new File(filePath);
        if (file.isFile()) {
            byte[] bytes = getSchemaXMLBytes(configName, metadataCols, file);
            zkClient.create(solrConfigPath + "/" + file.getName(), bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            solrConfigPath += "/" + (configName != null ? configName : file.getName());
            // 配置文件目录已存在
            if (zkClient.exists(solrConfigPath, false) != null) {
//                // 删除原配置目录及文件
//                delPath(zkClient, solrConfigPath);
//                zkClient.delete(solrConfigPath, -1);
                throw new Exception("该名称的配置文件已存在！");
            }
            zkClient.create(solrConfigPath, file.getName().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            File[] files = file.listFiles();
            for (File e : files) {
                if (e.isDirectory()) {
                    upload(zkClient, e.getPath(), solrConfigPath, null, metadataCols);
                } else {
                    byte[] bytes = getSchemaXMLBytes(configName, metadataCols, e);
                    zkClient.create(solrConfigPath + "/" + e.getName(), bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    continue;
                }
            }
        }
    }

    private static byte[] getSchemaXMLBytes(String configName, List<MetadataCol> metadataCols, File file) {
        return "schema.xml".equals(file.getName()) ? setSchemaField(configName, metadataCols) : readFileByBytes(file.getPath());
    }

    /**
     * 修改schema的字段
     *
     * @param metadataCols
     * @return
     */
    public static byte[] setSchemaField(String collectionName, List<MetadataCol> metadataCols) {
        URL url = ClassLoader.getSystemResource(SOLR_CONFIGS_TEMPLATE_SCHEMA);
        File file = new File(url.getPath());
        Document document = File2Doc(file);
        Element root = document.getRootElement();
        root.addAttribute("name", collectionName);
        Element fields = root.element("fields");
        for (MetadataCol metadataCol : metadataCols) {
            if (metadataCol.isPrimary()) { // 主键
                Element field = DocumentHelper.createElement("field");
                field.addAttribute("name", metadataCol.getName());
                field.addAttribute("type", getSolrType(metadataCol.getType()));
                field.addAttribute("indexed", "true");
                field.addAttribute("stored", "true");
                field.addAttribute("required", "true");
                field.addAttribute("multiValued", "false");
                fields.add(field);
                Element uniqueKey = DocumentHelper.createElement("uniqueKey");
                uniqueKey.setText(metadataCol.getName());
                root.add(uniqueKey);
            } else { // 非主键
                Element field = DocumentHelper.createElement("field");
                field.addAttribute("name", metadataCol.getName());
                field.addAttribute("type", getSolrType(metadataCol.getType()));
                field.addAttribute("indexed", metadataCol.isIndexed() ? "true" : "false");
                field.addAttribute("stored", metadataCol.isStored() ? "true" : "false");
                fields.add(field);
            }
        }
        return root.asXML().getBytes();
    }

    public static String getSolrType(DataType type) {
        if (DataType.STRING == type || DataType.VARCHAR == type || DataType.CHAR == type || DataType.TIMESTAMP == type) {
            return "string";
        } else if (DataType.DOUBLE == type || DataType.DECIMAL == type) {
            return "double";
        } else if (DataType.INT == type || DataType.SMALLINT == type || DataType.TINYINT == type) {
            return "int";
        } else if (DataType.FLOAT == type) {
            return "float";
        } else if (DataType.BIGINT == type) {
            return "long";
        } else if (DataType.BOOLEAN == type) {
            return "boolean";
        } else {
            return "string";
        }
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

    /**
     * 将给定文件的内容或者给定 URI 的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象
     *
     * @param file 文件
     * @return DOM的Document对象
     * @throws Exception
     */
    public static Document File2Doc(File file) {
        Document doc = null;
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(file);
            return doc;
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] readFileByBytes(String fileName) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(fileName);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
        return out.toByteArray();
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (StringUtils.isNotEmpty(param)) {
                urlNameString += "?" + param;
            }
            logger.info("solrUrlApi: " + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                logger.debug(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.warn("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getSolrConfigPath() {
        URL url = ClassLoader.getSystemResource(SOLR_CONFIGS_TEMPLATE);
        return url.getPath();
    }

    /**
     * 删除solr配置
     *
     * @param metadata
     * @throws InterruptedException
     * @throws KeeperException
     */
    public static void deleteZnode(Metadata metadata) throws Exception {
        Datasource datasource = metadata.getDatasource();
        SolrDatasource solrDatasource = new SolrDatasource(datasource.getPropertyMap());
        ZooKeeper zkClient = getZkClient(solrDatasource.getSolrUrl());
        String path = SOLR_CONFIG_ZOOKEEPER_DIR + "/" + metadata.getTbName();
        delPath(zkClient, path);
        zkClient.delete(path, -1);
    }


    /**
     * 删除配置目录及文件（递归）
     *
     * @param zk
     * @param path
     * @throws Exception
     */
    public static void delPath(ZooKeeper zk, String path) throws Exception {
        List<String> files = zk.getChildren(path, false);
        for (String file : files) {
            delPath(zk, path + "/" + file);
        }
        for (String file : files) {
            zk.delete(path + "/" + file, -1);
        }
    }

    public static void checkSolrProperty(Metadata metadata) throws Exception {
        //检查主键是否合法
        List<MetadataCol> cols = metadata.getMetadataCols();
        int count = 0;
        for (MetadataCol col : cols)
            if (col.isPrimary()) count++;
        if (count != 1) throw new Exception("必须指定一个主键！");

//        //检查分片是否合法（使用代理地址时会有问题）
//        SolrMetadata solrMetadata = new SolrMetadata(metadata.getPropertyMap());
//        int shards = solrMetadata.getShards();
//        int replicas = solrMetadata.getReplicas();
//        int maxShardsPerNode = solrMetadata.getMaxShardsPerNode();
//        SolrDatasource solrDatasource = new SolrDatasource(metadata.getDatasource().getPropertyMap());
//        int nodesNum = solrDatasource.getSolrServers().split(",").length;
//        if (shards * replicas >= maxShardsPerNode * nodesNum) {
//            throw new Exception("【配置参数】中的参数必须需满足”分片数*副本数<=节点数*单节点最大分片数“，请修改后再提交！");
//        }

    }

    public static SolrServer getSolrServer(SolrDatasource datasource, String collectionName) {
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

    /**
     * 添加单条数据
     *
     * @param datasource
     * @param collectionName
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, Map<String, String> map) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            doc.addField(entry.getKey(), entry.getValue());
        }
        addDocument(solrServer, doc);
    }

    /**
     * 添加单条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValue
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, String idName, String idValue, Map<String, String> map) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField(idName, idValue);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            doc.addField(entry.getKey(), entry.getValue());
        }
        addDocument(solrServer, doc);
    }

    /**
     * 添加多条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValues
     * @param map
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, String idName, List<String> idValues, Map<String, String> map) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<>();
        SolrInputDocument doc = null;
        for (String idValue : idValues) {
            doc = new SolrInputDocument();
            doc.addField(idName, idValue);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                doc.addField(entry.getKey(), entry.getValue());
            }
            docs.add(doc);
        }
        addDocument(solrServer, docs);
    }

    /**
     * 添加多条数据
     *
     * @param datasource
     * @param collectionName
     * @param maps
     */
    public static void addDocument(SolrDatasource datasource, String collectionName, List<Map<String, String>> maps) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<>();
        SolrInputDocument doc = null;
        for (Map<String, String> map : maps) {
            doc = new SolrInputDocument();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                doc.addField(entry.getKey(), entry.getValue());
            }
            docs.add(doc);
        }
        addDocument(solrServer, docs);
    }

    /**
     * 添加单个对象
     *
     * @param datasource
     * @param collectionName
     * @param bean
     * @param <T>
     */
    public static <T> void addDocumentBean(SolrDatasource datasource, String collectionName, T bean) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        try {
            solrServer.addBean(bean);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    /**
     * 添加多个对象
     *
     * @param datasource
     * @param collectionName
     * @param beans
     * @param <T>
     */
    public static <T> void addDocumentBean(SolrDatasource datasource, String collectionName, List<T> beans) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        try {
            solrServer.addBeans(beans);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    /**
     * 更新单条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValue
     * @param map
     */
    public static void updateDocument(SolrDatasource datasource, String collectionName, String idName, String idValue, Map<String, String> map) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField(idName, idValue);
        Map<String, String> obj = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            obj = new HashMap<>();
            obj.put("set", entry.getValue());
            doc.addField(entry.getKey(), obj);
        }
        addDocument(solrServer, doc);
    }

    /**
     * 添加单个文档
     *
     * @param solrServer
     * @param doc
     */
    public static void addDocument(SolrServer solrServer, SolrInputDocument doc) {
        try {
            solrServer.add(doc);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    /**
     * 更新多条数据
     *
     * @param datasource
     * @param collectionName
     * @param idName
     * @param idValues
     * @param map
     */
    public static void updateDocument(SolrDatasource datasource, String collectionName, String idName, List<String> idValues, Map<String, String> map) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        List<SolrInputDocument> docs = new ArrayList<>();
        SolrInputDocument doc = null;
        Map<String, String> obj = null;
        for (String idValue : idValues) {
            doc = new SolrInputDocument();
            doc.addField(idName, idValue);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                obj = new HashMap<>();
                obj.put("set", entry.getValue());
                doc.addField(entry.getKey(), obj);
            }
            docs.add(doc);
        }
        addDocument(solrServer, docs);
    }

    /**
     * 添加多个文档
     *
     * @param solrServer
     * @param docs
     */
    public static void addDocument(SolrServer solrServer, List<SolrInputDocument> docs) {
        try {
            solrServer.add(docs);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    /**
     * 删除单条数据
     *
     * @param datasource
     * @param collectionName
     * @param ids
     */
    public static void deleteDocument(SolrDatasource datasource, String collectionName, List<String> ids) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        try {
            solrServer.deleteById(ids);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    /**
     * 删除多条数据
     *
     * @param datasource
     * @param collectionName
     * @param id
     */
    public static void deleteDocument(SolrDatasource datasource, String collectionName, String id) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        try {
            solrServer.deleteById(id);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (solrServer != null) {
                solrServer.shutdown();
            }
        }
    }

    public static String getQuery(List<WhereProperty> whereProperties) {
        StringBuffer queryConditons = new StringBuffer("*:*");
        String name = null;
        String value = null;
        Operator operator = null;
        for (WhereProperty property : whereProperties) {
            name = property.getName();
            value = property.getValue();
            operator = property.getOperator();
            if (StringUtils.isBlank(name) || StringUtils.isBlank(value) || operator == null)
                continue;
            queryConditons.append(" AND " + name + getCondition(value, operator));
        }
        return queryConditons.toString();
    }

    /**
     * 分页查询
     *
     * @param datasource
     * @param collectionName
     * @param whereProperties
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static Page searchPage(SolrDatasource datasource, String collectionName, List<WhereProperty> whereProperties, int pageIndex, int pageSize) {
        SolrQuery query = new SolrQuery();
        query.setQuery(getQuery(whereProperties));
        query.setStart((pageIndex - 1) * pageSize);
        query.setRows(pageSize);
        QueryResponse rsp = getQueryResponse(datasource, collectionName, query);
        if (rsp == null) {
            return new Page(pageIndex, pageSize, 0, null);
        } else {
            SolrDocumentList sdl = rsp.getResults();
            int totalCount = (int) sdl.getNumFound();
            List<Map<String, String>> list = getSolrReturnList(rsp);
            return new Page(pageIndex, pageSize, totalCount, list);
        }
    }

    /**
     * 查询
     *
     * @param datasource
     * @param collectionName
     * @param whereProperties
     * @return
     */
    public static List<Map<String, String>> search(SolrDatasource datasource, String collectionName, List<WhereProperty> whereProperties) {
        SolrQuery query = new SolrQuery();
        query.setQuery(getQuery(whereProperties));
        QueryResponse rsp = getQueryResponse(datasource, collectionName, query);
        return getSolrReturnList(rsp);
    }

    public static QueryResponse getQueryResponse(SolrDatasource datasource, String collectionName, SolrQuery query) {
        SolrServer solrServer = getSolrServer(datasource, collectionName);
        if (solrServer == null) {
            return null;
        }
        QueryResponse res = null;
        try {
            res = solrServer.query(query);
        } catch (SolrServerException e) {
            System.out.println(e.getMessage());
            res = null;
        }
        return res;
    }

    public static List<Map<String, String>> getSolrReturnList(QueryResponse rsp) {
        List<Map<String, String>> list = null;
        if (rsp == null) {
            return null;
        } else {
            Map<String, String> map = null;
            SolrDocumentList docs = rsp.getResults();
            for (int i = 0; i < docs.size(); i++) {
                SolrDocument doc = docs.get(i);
                map = new HashMap<>();
                for (Map.Entry<String, Object> entry : doc.entrySet()) {
                    map.put(entry.getKey(), (String) entry.getValue());
                }
                list.add(map);
            }
        }
        return list;
    }

    public static String getCondition(String value, Operator operator) {
        String str = "";
        if (Operator.EQ == operator) {
            str = ":" + value;
        } else if (Operator.NE == operator) {
            str = ":-" + value;
        } else if (Operator.GE == operator) {
            str = ":[" + value + " TO *]";
        } else if (Operator.GT == operator) {
            str = ":{" + value + " TO *]";
        } else if (Operator.LE == operator) {
            str = ":[* TO " + value + "]";
        } else if (Operator.LT == operator) {
            str = ":[* TO " + value + "}";
        } else if (Operator.IN == operator) {
            str = ":(" + value + ")";
        } else if (Operator.LK == operator) {
            str = ":*" + value + "*";
        } else if (Operator.RLIKE == operator) {
            str = ":" + value + "*";
        }
        return str;
    }
}
