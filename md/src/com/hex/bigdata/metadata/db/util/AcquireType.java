package com.hex.bigdata.metadata.db.util;

/**
 * Created by junjiem on 2016-6-22.
 */
public enum AcquireType {
    JDBCSQL("通过管理员账号访问元数据库获取元信息", Constant.ACQUIRE_TYPE_JDBCSQL),
    JDBCAPI("通过JDBC的DatabaseMetaData获取元信息", Constant.ACQUIRE_TYPE_JDBCAPI),
    JDBCINFO("通过SQL语句获取简单的元信息", Constant.ACQUIRE_TYPE_JDBCINFO);

    private String value;
    private String name;

    private AcquireType(String name, String value) {
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
