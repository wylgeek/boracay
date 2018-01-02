package com.hex.bigdata.udsp.olq.constant;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/10
 * TIME:15:01
 */
public class OlqConstant {
    public static final  String OLQ_IS_NEED_CODE_YES = "0";
    public static final  String OLQ_IS_NEED_CODE_NO = "1";
    public static final  String OLQ_IS_NEED_CN_YES = "是";
    public static final  String OLQ_IS_NEED_CN_NO = "否";
    public static final String REGEX_IMPALA_LIMIT = " (?i)LIMIT\\s+\\d+\\s*$";
    public static final String REGEX_CREATE_TABLE = "^\\s*(?i)CREATE\\s+TABLE\\s+";
    public static final String REGEX_ALTER_TABLE = "^\\s*(?i)ALTER\\s+TABLE\\s+";
    public static final String REGEX_ALTER = "^\\s*(?i)ALTER\\s+";
    public static final String REGEX_DROP = "^\\s*(?i)DROP\\s+";
    public static final String REGEX_INSERT = "^\\s*(?i)INSERT\\s+";
    public static final String REGEX_SELECT = "^\\s*(?i)SELECT\\s+(.)+FROM ";
}
