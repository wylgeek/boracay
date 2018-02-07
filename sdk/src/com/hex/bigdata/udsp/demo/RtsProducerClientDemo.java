package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.RtsConsumerClient;
import com.hex.bigdata.udsp.client.impl.RtsProducerClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.request.RtsProducerRequest;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时流生产者调用示例
 */
public class RtsProducerClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsProducerClientDemo.class);

    public void syncStart() {

        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        RtsProducerClient client = ConsumerClientFactory.createCustomClient(RtsProducerClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        RtsProducerClient client = ConsumerClientFactory.createCustomClient(RtsProducerClient.class);

        //创建调用实体
        RtsProducerRequest request = new RtsProducerRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("rts_producer");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10071");
        //设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //设置同步调用
        request.setType(SdkConstant.CONSUMER_TYPE_SYNC);

        //设置UDSP校验用户信息，用户名及token
        request.setUdspUser("admin");
        request.setToken("000000");

        ///设置业务参数-设置需要插入队列的数据
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        Map<String, String> data = new HashMap<String, String>();
        data.put("test030101", "test1");
        data.put("test030102", "test2");
        data.put("test030103", "test3");
        data.put("test030104", "test4");
        datas.add(data);
        request.setDataStream(datas);

        //发起调用并获取返回信息
        SyncPackResponse response = client.syncStart(request);

    }

    public static void main(String[] args) {
        RtsProducerClientDemo demo = new RtsProducerClientDemo();
        demo.syncStart();
    }
}
