package com.hex.bigdata.udsp.common.provider.model;

/**
 * Created by junjiem on 2017-3-2.
 */
public class Property {

    private String name;

    private String value;

    private String describe;

    public Property() {
    }

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Property(String name, String value, String describe) {
        this.name = name;
        this.value = value;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
