package com.hex.bigdata.udsp.ed.constant;

/**
 * 接口调用类型（实时、缓存。。。）
 * 根据字典表中：ED_INTERFACE_TYPE同步修改
 * Created by jc.zhao
 * Date:2018/1/10
 * Time:10:48
 */
public enum InterfaceType {

    CACHE_ACCESS("缓存访问", "1"),
    DIRECT_ACCESS("实时访问", "2");

    private String value;
    private String name;

    private InterfaceType(String name, String value) {
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
