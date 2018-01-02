package com.hex.bigdata.udsp.constant;

/**
 * 消费接口，常量类
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/24
 * TIME:18:49
 */
public class ConsumerConstant {

    /**
     * 请求类型-同步
     */
    public static final String CONSUMER_TYPE_SYNC="SYNC";
    /**
     * 请求类型-异步
     */
    public static final String CONSUMER_TYPE_ASYNC="ASYNC";


    /**
     *entity类型-START
     */
    public static final String CONSUMER_ENTITY_START="START";
    /**
     *entity类型-STOP
     */
    public static final String CONSUMER_ENTITY_STOP="STOP";
    /**
     *entity类型-STATUS
     */
    public static final String CONSUMER_ENTITY_STATUS="STATUS";

    /**
     * 服务请求类型-INNER内部请求
     */
    public static final String CONSUMER_REQUEST_TYPE_INNER="0";
    /**
     * 服务请求类型-OUTER外部请求
     */
    public static final String CONSUMER_REQUEST_TYPE_OUTER="1";

    /**
     * 消费成功
     */
    public static final String CONSUME_REQUEST_STATUS_SUCCESS="0";
    /**
     * 消费失败
     */
    public static final String CONSUME_REQUEST_STATUS_FAILED="1";
    /**
     * 消费状态-正在消费
     */
    public static final String CONSUME_ACTION_RUNNING="正在消费";
    /**
     * 消费状态-消费成功
     */
    public static final String CONSUME_ACTION_SUCCESS="消费成功";
    /**
     * 消费状态-消费失败
     */
    public static final String CONSUME_ACTION_FAILED="消费失败";

    public static final String CONSUME_RTS_DATASTREAM="dataStream";

    /**
     * 服务启停状态参数-启动
     */
    public static final String SERVICE_STATUS_START="0";
    /**
     * 服务启停状态参数-停用
     */
    public static final String SERVICE_STATUS_STOP="1";
}
