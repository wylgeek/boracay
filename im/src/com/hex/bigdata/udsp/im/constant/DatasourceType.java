package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-8.
 */
public enum DatasourceType {
    HIVE("HIVE", "HIVE"),
    IMPALA("IMPALA", "IMPALA"),
    ORACLE("ORACLE", "ORACLE"),
    MYSQL("MYSQL", "MYSQL"),
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
