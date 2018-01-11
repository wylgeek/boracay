package com.hex.bigdata.udsp.ed.connect.util;

import org.apache.log4j.Logger;

/**
 * Created by k1335 on 2016/4/18.
 */
public class TableUtil {

    private static final Logger logger = Logger.getLogger(TableUtil.class.getName());

    //shell目录
    public static final String ROOTDIR = "/goframe/udsp/shell/";
    //模板目录
    public static final String TEMPLATEDIR = "/goframe/udsp/shell/template/";
    //占位符
    public static final String PLACEHOLDER = "${tableName}";

    /**
     * 根据存储名称、表前缀生成表名
     *
     * @param storeName
     * @param tablePrefix
     * @return
     */
    public static String getTableName(String storeName, String tablePrefix) {
        StringBuffer stringBuffer = new StringBuffer(tablePrefix);
        stringBuffer.append("_").append(storeName.toLowerCase());
        String tableName = stringBuffer.toString();
        return tableName;
    }

}
