package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-21.
 */
public enum BatchStatus {
    READY_BUILD("准备构建", "READY_BUILD"),
    BUILDING("正在构建", "BUILDING"),
    BUILD_SUCCESS("构建成功", "BUILD_SUCCESS"),
    BUILD_FAIL("构建失败", "BUILD_FAIL");

    private String value;
    private String name;

    private BatchStatus(String name, String value) {
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
