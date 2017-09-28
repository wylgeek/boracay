package com.hex.bigdata.udsp.im.constant;

/**
 * Created by JunjieM on 2017-9-15.
 */
public enum RealtimeStatus {
    READY_START("准备启动", "READY_START"),
    STARTING("开始启动", "STARTING"),
    RUNNING("正在运行", "RUNNING"),
    START_FAIL("启动失败", "START_FAIL"),
    READY_STOP("准备停止", "READY_STOP"),
    STOPING("开始停止", "STOPING"),
    STOP_SUCCESS("停止成功", "STOP_SUCCESS"),
    STOP_FAIL("停止失败", "STOP_FAIL"),
    RUN_FAIL("运行失败", "RUN_FAIL");

    private String value;
    private String name;

    private RealtimeStatus(String name, String value) {
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
