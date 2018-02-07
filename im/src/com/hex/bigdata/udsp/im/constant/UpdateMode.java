package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-12.
 */
public enum UpdateMode {
    MATCHING_UPDATE("匹配更新", "MATCHING_UPDATE"),
    UPDATE_INSERT("更新插入", "UPDATE_INSERT"),
    INSERT_INTO("增量插入", "INSERT_INTO");

    private String value;
    private String name;

    private UpdateMode(String name, String value) {
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
