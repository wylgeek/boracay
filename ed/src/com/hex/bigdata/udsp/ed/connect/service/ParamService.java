package com.hex.bigdata.udsp.ed.connect.service;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

/**
 * Created by k1335 on 2016/5/11.
 */

@Service("paramService")
public class ParamService {

    //udsp相关参数
    // udsp存储的HBASE表和Solr Collection名称的前缀
    private String udspStorePrefix;


    //HBase相关参数
    // HBASE存储数据的族名称
    private String hbaseDataFamily;
    // HBASE存储数据的列名称
    private String hbaseDataColumn;
    // HBASE存储信息的列名称
    private String hbaseParamColumn;


    //Solr相关参数
    // SOLR服务的IP和端口集合
    private String solrServers;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public byte[] getHbaseDataFamily() {
        return Bytes.toBytes(hbaseDataFamily);
    }

    public void setHbaseDataFamily(String hbaseDataFamily) {
        this.hbaseDataFamily = hbaseDataFamily;
    }

    public byte[] getHbaseDataColumn() {
        return Bytes.toBytes(hbaseDataColumn);
    }

    public void setHbaseDataColumn(String hbaseDataColumn) {
        this.hbaseDataColumn = hbaseDataColumn;
    }

    public byte[] getHbaseParamColumn() {
        return Bytes.toBytes(hbaseParamColumn);
    }

    public void setHbaseParamColumn(String hbaseParamColumn) {
        this.hbaseParamColumn = hbaseParamColumn;
    }

    public String getSolrServers() {
        return solrServers;
    }

    public void setSolrServers(String solrServers) {
        this.solrServers = solrServers;
    }

    public static void main(String[] args) {
        ParamService paramService = new ParamService();
        System.out.println(paramService);
    }

    public String getUdspStorePrefix() {
        return udspStorePrefix;
    }

    public void setUdspStorePrefix(String udspStorePrefix) {
        this.udspStorePrefix = udspStorePrefix;
    }
}
