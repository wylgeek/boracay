package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-12.
 */
public enum BuildMode {
    INSERT_INTO("增量", "INSERT_INTO"),
    INSERT_OVERWRITE("全量", "INSERT_OVERWRITE");

    private String value;
    private String name;

    private BuildMode(String name, String value) {
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
