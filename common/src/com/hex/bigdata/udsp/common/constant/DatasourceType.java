package com.hex.bigdata.udsp.common.constant;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/17
 * TIME:14:09
 */
public enum DatasourceType {
    HIVE("HIVE", "HIVE"),
    IMPALA("IMPALA", "IMPALA"),
    ORACLE("ORACLE", "ORACLE"),
    MYSQL("MYSQL", "MYSQL"),
    REDIS("REDIS", "REDIS"),
    PGSQL("PGSQL", "PGSQL"),
    DB2("DB2", "DB2"),
    KYLIN("KYLIN", "KYLIN"),
    SOLR("SOLR", "SOLR"),
    KAFKA("KAFKA", "KAFKA"),
    HBASE("HBASE", "HBASE"),
    SOLR_HBASE("SOLR_HBASE", "SOLR_HBASE");

    private String value;
    private String name;

    private DatasourceType(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
