package com.hex.bigdata.metadata.db.util;

public enum DBType {
    ORACLE("Oracle数据库", Constant.DB_TYPE_ORACLE),
    MYSQL("MySql数据库", Constant.DB_TYPE_MYSQL),
    DB2("DB2数据库", Constant.DB_TYPE_DB2),
    TD("Teradata数据库", Constant.DB_TYPE_TD),
    SQLSERVER("SqlServer数据库", Constant.DB_TYPE_SQLSERVER),
    AS400("AS400数据库", Constant.DB_TYPE_AS400),
    PGSQL("PostgreSql数据库", Constant.DB_TYPE_PGSQL),
    HIVE("Hive数据库", Constant.DB_TYPE_HIVE),
    IMPALA("Impala数据库", Constant.DB_TYPE_IMPALA),
    IMPALA_FOR_MYSQL("Impala的MySql元数据库", Constant.DB_TYPE_IMPALA_FOR_MYSQL),
    IMPALA_FOR_ORACLE("Impala的Oracle元数据库", Constant.DB_TYPE_IMPALA_FOR_ORACLE),
    IMPALA_FOR_PGSQL("Impala的PostgreSql元数据库", Constant.DB_TYPE_IMPALA_FOR_PGSQL),
    HIVE_FOR_MYSQL("Hive的MySql元数据库", Constant.DB_TYPE_HIVE_FOR_MYSQL),
    HIVE_FOR_ORACLE("Hive的Oracle元数据库", Constant.DB_TYPE_HIVE_FOR_ORACLE),
    HIVE_FOR_PGSQL("Hive的PostgreSql元数据库", Constant.DB_TYPE_HIVE_FOR_PGSQL);

    private String value;
    private String name;

    private DBType(String name, String value) {
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
