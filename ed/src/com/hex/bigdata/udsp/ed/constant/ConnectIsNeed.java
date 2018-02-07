package com.hex.bigdata.udsp.ed.constant;

/**
 * Created by jc.zhao
 * Date:2018/1/10
 * Time:10:48
 */
public enum ConnectIsNeed {

    NEED("需要", "0"),
    UN_NEED("不需要", "1");

    private String value;
    private String name;

    private ConnectIsNeed(String name, String value) {
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
