package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-8.
 */
public enum ModelType {
    BATCH("批量", "BATCH"),
    REALTIME("实时", "REALTIME");

    private String value;
    private String name;

    private ModelType(String name, String value) {
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
