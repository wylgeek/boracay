package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.RtsConsumerClient;
import com.hex.bigdata.udsp.client.impl.RtsProducerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.RtsConsumerRequest;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 实时流消费者调用示例
 *
 */
public class RtsConsumerClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsConsumerClientDemo.class);

    public void syncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        RtsConsumerClient client = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        RtsConsumerClient client = ConsumerClientFactory.createCustomClient(RtsConsumerClient.class);

        //创建请求实体
        RtsConsumerRequest request = new RtsConsumerRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("rts_test_consumer2");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //基础参数设置-设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，异步调用为async，同步调用为sync
        request.setType(SdkConstant.CONSUMER_TYPE_SYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("admin");
        request.setToken("000000");

        //发起请求
        SyncPackResponse response = client.syncStart(request);

    }

    public static void main(String[] args) {
        RtsConsumerClientDemo demo = new RtsConsumerClientDemo();
        demo.syncStart();
    }
}
