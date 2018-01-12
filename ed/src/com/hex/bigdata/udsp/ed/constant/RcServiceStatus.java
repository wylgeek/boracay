package com.hex.bigdata.udsp.ed.constant;

/**
 * Created by jc.zhao
 * Date:2018/1/10
 * Time:10:48
 */
public enum RcServiceStatus {

    START("启用", "0"),
    STOP("停止", "1");

    private String value;
    private String name;

    private RcServiceStatus(String name, String value) {
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
