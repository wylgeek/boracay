package com.hex.bigdata.metadata.db.util;

public class Constant {
    /**
     * 数据库类型
     */
    public final static String DB_TYPE_ORACLE = "oracle";
    public final static String DB_TYPE_MYSQL = "mysql";
    public final static String DB_TYPE_DB2 = "db2";
    public final static String DB_TYPE_TD = "td";
    public final static String DB_TYPE_SQLSERVER = "mssql";
    public final static String DB_TYPE_AS400 = "as400";
    public final static String DB_TYPE_PGSQL = "pgsql";
    public final static String DB_TYPE_HIVE = "hive";
    public final static String DB_TYPE_IMPALA = "impala";
    public final static String DB_TYPE_IMPALA_FOR_MYSQL = "impala_for_mysql";
    public final static String DB_TYPE_IMPALA_FOR_ORACLE = "impala_for_oracle";
    public final static String DB_TYPE_IMPALA_FOR_PGSQL = "impala_for_pgsql";
    public final static String DB_TYPE_HIVE_FOR_MYSQL = "hive_for_mysql";
    public final static String DB_TYPE_HIVE_FOR_ORACLE = "hive_for_oracle";
    public final static String DB_TYPE_HIVE_FOR_PGSQL = "hive_for_pgsql";

    /**
     * MetaData
     */
    public final static String DB_NAME = "DB_NAME"; // 数据库名称
    public final static String DB_COMMENT = "DB_COMMENT"; // 数据库注释

    public final static String TB_NAME = "TB_NAME"; // 表名称
    public final static String TB_COMMENT = "TB_COMMENT"; // 表注释

    public final static String COL_NAME = "COL_NAME"; // 字段名称
    public final static String COL_SEQ = "COL_SEQ"; // 字段位置
    public final static String COL_DATA_TYPE = "COL_DATA_TYPE"; // 字段数据类型
    public final static String COL_DATA_LENGTH = "COL_DATA_LENGTH"; // 字段数据长度
    public final static String COL_DATA_PRECISION = "COL_DATA_PRECISION"; // 字段数据精度
    public final static String COL_DATA_SCALE = "COL_DATA_SCALE"; // 小数部分的位数
    public final static String COL_IS_NULLABLE = "COL_IS_NULLABLE"; // 字段是否允许为空
    public final static String COL_COMMENT = "COL_COMMENT"; // 字段注释
    public final static String COL_PK_SEQ = "COL_PK_SEQ"; // 主键字段位置
    public final static String COL_PF_SEQ = "COL_PF_SEQ"; // 分区字段位置

    public final static String COL_TYPE_NAME = "TYPE_NAME"; // 类型名称

    /**
     * MetaInfo
     */
    public final static String TABLE_CAT = "TABLE_CAT"; // Catalog名称
    public final static String TABLE_CAT_COMMENT = "TABLE_CAT_COMMENT"; // Catalog注释

    public final static String TABLE_SCHEM = "TABLE_SCHEM"; // Schema名称
    public final static String TABLE_SCHEM_COMMENT = "TABLE_SCHEM_COMMENT"; // Schema注释

    public final static String TABLE_NAME = "TABLE_NAME"; // Table名称
    public final static String TABLE_REMARKS = "REMARKS"; // Table注释

    public final static String COLUMN_NAME = "COLUMN_NAME"; // Column名称
    public final static String TYPE_NAME = "TYPE_NAME"; // Column类型
    public final static String ORDINAL_POSITION = "ORDINAL_POSITION"; // Column位置
    public final static String COLUMN_SIZE = "COLUMN_SIZE"; // Column长度
    public final static String DECIMAL_DIGITS = "DECIMAL_DIGITS"; // 小数部分的位数
    public final static String REMARKS = "REMARKS"; // Column注释
    public final static String IS_NULLABLE = "IS_NULLABLE"; // Column是否允许为空
    public final static String KEY_SEQ = "KEY_SEQ"; // 主键字段位置

    public final static String PRECISION = "PRECISION"; // 精度
    public final static String CREATE_PARAMS = "CREATE_PARAMS";
    public final static String MINIMUM_SCALE = "MINIMUM_SCALE";
    public final static String MAXIMUM_SCALE = "MAXIMUM_SCALE";


    /**
     * 获取元信息方式
     */
    public final static String ACQUIRE_TYPE_JDBCSQL = "JDBCSQL";
    public final static String ACQUIRE_TYPE_JDBCAPI = "JDBCAPI";
    public final static String ACQUIRE_TYPE_JDBCINFO = "JDBCINFO";
}
