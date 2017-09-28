package com.hex.bigdata.udsp.common.constant;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/17
 * TIME:14:09
 */
public enum DatasourceType {
    IQ("交互查询", "IQ"),
    OLQ("联机查询", "OLQ"),
    RTS("实时流", "RTS");

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
