package com.hex.bigdata.udsp;

import com.hex.bigdata.udsp.client.factory.ConsumerClientFactory;
import com.hex.bigdata.udsp.client.impl.SqlClient;
import com.hex.bigdata.udsp.constant.SdkConstant;
import com.hex.bigdata.udsp.constant.StatusCode;
import com.hex.bigdata.udsp.model.request.SqlRequest;
import com.hex.bigdata.udsp.model.request.StatusRequest;
import com.hex.bigdata.udsp.model.response.pack.AsyncPackResponse;
import com.hex.bigdata.udsp.model.response.pack.StatusPackResponse;
import com.hex.bigdata.udsp.model.response.pack.SyncPackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 联机查询测试
 */
public class OlqDatasourceTest {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OlqDatasourceTest.class);

    /**
     * SQL客户端查询同步start
     */
    @Test
    public void syncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class);

        //创建请求实体
        SqlRequest request = new SqlRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("dev_oracle");
        //基础参数设置-设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_SYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("IFE");
        request.setToken("000000");

        //设置业务参数-查询SQL
        request.setSql("SELECT * FROM (select * from MC_CONSUME_LOG order by app_type, sync_type) UDSP_VIEW where ROWNUM >=0 AND ROWNUM <= 2000");

        //调用并获取返回结果
        SyncPackResponse response = client.syncStart(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                // 耗时
                logger.debug("耗时：" + response.getConsumeTime());
                // 消费ID
                logger.debug("消费ID：" + response.getConsumeId());
                logger.debug("------------------------------------------------------------------");
                // 字段信息
                LinkedHashMap<String, String> returnColumns = response.getReturnColumns();
                for (Map.Entry<String, String> entry : returnColumns.entrySet()) {
                    logger.debug("名称：" + entry.getKey() + "，类型：" + entry.getValue());
                }
                logger.debug("------------------------------------------------------------------");
                // 数据信息
                List<Map<String, String>> records = response.getRecords();
                for (Map<String, String> record : records) {
                    for (Map.Entry<String, String> entry : record.entrySet()) {
                        logger.debug("名称：" + entry.getKey() + "，值：" + entry.getValue());
                    }
                }
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }

    /**
     * SQL客户端查询异步start
     */
    @Test
    public void asyncStart() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class);

        //创建请求实体
        SqlRequest request = new SqlRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("core02");
        //基础参数设置-设置调用start接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_START);
        //基础参数设置-设置异步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_ASYNC);
        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("000000");
        //设置业务参数-查询SQL
        request.setSql("select * from cmdata.c01_cd_acct limit 1");

        //调用并获取返回结果
        AsyncPackResponse response = client.asyncStart(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                // 耗时
                logger.debug("耗时：" + response.getConsumeTime());
                // 消费ID
                logger.debug("消费ID：" + response.getConsumeId());
                // 生成文件的FTP路径
                logger.debug("生成文件的FTP路径：" + response.getResponseContent());
                /**
                 * 成功说明异步任务已经调起
                 */
                // 可以继续循环执行查看状态的操作
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }

    /**
     * SQL客户端查询异步status
     */
    @Test
    public void asyncStatus() {
        //创建自定义客户端
//        String url = "http://127.0.0.1:8088/udsp/http/consume";
//        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class, url);
        //创建默认客户端,根据udsp.config.properties配置文件获取地址
        SqlClient client = ConsumerClientFactory.createCustomClient(SqlClient.class);

        //创建请求实体
        StatusRequest request = new StatusRequest();
        //基础参数设置-设置调用服务的名称
        request.setServiceName("smart01");
        //基础参数设置-设置调用status接口
        request.setEntity(SdkConstant.CONSUMER_ENTITY_STATUS);
        //基础参数设置-设置同步调用，同步调用为sync，异步调用为async
        request.setType(SdkConstant.CONSUMER_TYPE_ASYNC);

        //基础参数设置-设置UDSP校验用户信息，用户名及token，用户校验信息需UDSP下发
        request.setUdspUser("test");
        request.setToken("000000");

        //设置业务参数-消费id
        request.setConsumeId("a7f85c82a1c290c9316b7fa07a7a627");

        //调用并获取返回结果
        StatusPackResponse response = client.asyncStatus(request);

        // 拆包响应对象
        if (response == null) {
            logger.error("客户端异常");
        } else {
            if (StatusCode.SUCCESS == response.getStatusCode()) {
                logger.info("异步消费完成");
                // 可以继续执行FTP下载文件的操作
            }
            if (StatusCode.RUNING == response.getStatusCode()) {
                logger.info("异步消费正在执行");
                // 可以继续执行查看状态的操作
            } else {
                logger.error("状态：" + response.getStatus());
                logger.error("状态码：" + response.getStatusCode());
                logger.error("错误码：" + response.getErrorCode());
                logger.error("错误信息：" + response.getMessage());
            }
        }
    }
}
