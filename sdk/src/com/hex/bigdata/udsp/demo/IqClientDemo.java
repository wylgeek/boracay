package com.hex.bigdata.udsp.demo;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.IqClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.model.Page;
import com.hex.bigdata.udsp.model.request.IqRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 交互查询调用示例
 *
 */
@Deprecated
public class IqClientDemo {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(IqClientDemo.class);

    /**
     * 交互查询-同步start接口示例
     */
    public void syncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class);

        //创建请求实体
        IqRequest request = new IqRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("soa_jyls_app1");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10940");
        //基础参数设置-设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_SYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("CRM");
        request.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("acct_no", "130000365600030");
        data.put("start_time", "2016-12-31");
        data.put("end_time", "2017-12-31");
        request.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageSize(20);
        request.setPage(page);

        //发起动用
        SyncPackResponse response = client.syncStart(request);
    }

    /**
     * 交互查询-异步start接口示例
     */
    public void asyncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class);

        //创建请求实体
        IqRequest request = new IqRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("soa_jyls_app");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10940");
        //基础参数设置-设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("CRM");
        request.setToken("000000");

        //设置业务参数-查询参数设置
        Map<String, String> data = new HashMap<>();
        data.put("acct_no", "130000365600030");
        data.put("start_time", "2016-12-31");
        data.put("end_time", "2017-12-31");
        request.setData(data);

        //设置业务参数-分页参数设置
        Page page = new Page();
        page.setPageIndex(1);
        page.setPageSize(20);
        request.setPage(page);

        //发起调用
        AsyncPackResponse response = client.asyncStart(request);
    }

    /**
     * 交互查询-异步status接口示例
     */
    public void asyncStatus() {
        //udsp请求连接

        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        IqClient client = ConsumerClientFactory.createCustomClient(IqClient.class);

        //创建请求实体
        StatusRequest request = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("soa_jyls_app");
        //基础参数设置-上层应用系统使用者工号
        request.setAppUser("10940");
        //基础参数设置-设置调用status接口，查看任务状态
        request.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("CRM");
        request.setToken("000000");

        //业务参数
        request.setConsumeId("955a0c14a55ebe727ff45f20939dfc97");

        //发起调用
        StatusPackResponse response = client.asyncStatus(request);
    }

    public static void main(String[] args) {
        IqClientDemo demo = new IqClientDemo();
        demo.syncStart();
        demo.asyncStatus();
        demo.asyncStart();
    }
}
