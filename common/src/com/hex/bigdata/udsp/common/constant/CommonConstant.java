package com.hex.bigdata.udsp.common.constant;

/**
 * 公共常量
 * Created by tomnic on 2017/2/27.
 */
public class CommonConstant {
    /**
     * 数据库记录删除标志-未删除
     */
    public static final String OBJECT_DEL_FLG_EXIST="0";
    /**
     * 数据库记录删除标志-已删除
     */
    public static final String OBJECT_DEL_FLG_DELETED="1";

    /**
     * 请求方式-异步
     */
    public static final String REQUEST_ASYNC="ASYNC";
    /**
     * 请求方式-同步
     */
    public static final String REQUEST_SYNC="SYNC";
    /**
     * 服务状态-启用（编码）
     */
    public static final String SERVICE_STATUS_ENABLED="0";
    /**
     * 服务状态-启用（文字）
     */
    public static final String SERVICE_STATUS_ENABLED_TEXT="启用";
    /**
     * 服务状态-禁用/停用（编码）
     */
    public static final String SERVICE_STATUS_DISABLED="1";
    /**
     * 服务状态-禁用/停用（文字）
     */
    public static final String SERVICE_STATUS_DISABLED_TEXT="停用";
}
