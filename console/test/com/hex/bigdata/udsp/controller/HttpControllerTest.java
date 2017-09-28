package com.hex.bigdata.udsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.MmTestRequest;
import com.hex.bigdata.udsp.model.Response;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/26
 * TIME:16:38
 */
public class HttpControllerTest {

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTEXT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) {
        HttpControllerTest test = new HttpControllerTest();

        //联机查询异步start
        //test.httpJsonTest_olpAsyncStart();
        //联机查询异步status
        //test.httpJsonTest_olpAsyncStatus();
        //联机查询同步start
        //test.httpJsonTest_olpSyncStart();

        //交互查询同步start
        //test.httpJsonTest_iqSyncStart();
        //交互查询异步start
        //test.httpJsonTest_iqAsyncStart();
        //交互查询异步status
        //test.httpJsonTest_iqAsyncStatus();

        //实时流-生产者
        //test.httpJsonTest_rtsProducerstart();
        //实时流-消费者

        //模型调用-同步start
        //test.httpJsonTest_mmSyncStart();
        //模型调用-异步start
        //test.httpJsonTest_mmAsyncStart();
        //模型调用-异步-status
        //test.httpJsonTest_mmAsyncStatus();
        test.forTest();

        //联机查询应用异步start
        //test.httpJsonTest_olpAppAsyncStart();
        //联机查询应用异步status
        //test.httpJsonTest_olpAppAsyncStatus();
        //联机查询应用同步start
        //test.httpJsonTest_olpAppSyncStart();
    }

    private void httpJsonTest_olpAppAsyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("olq_app_service2");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("start");
        externalRequest.setType("async");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");


        Map<String, String> data = new HashMap<>();
        data.put("WBJYM", "404");
        externalRequest.setData(data);

        this.requestUdsp(externalRequest, url);
    }

    private void httpJsonTest_olpAppAsyncStatus() {

    }

    private void httpJsonTest_olpAppSyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("olq_app_service2");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("start");
        externalRequest.setType("sync");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");


        Map<String, String> data = new HashMap<>();
        data.put("WBJYM", "404");
        externalRequest.setData(data);

        this.requestUdsp(externalRequest, url);
    }

    public void forTest() {

        ExecutorService executorService = Executors.newFixedThreadPool(200);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            executorService.submit(new HttpTestRunable());
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        long end = System.currentTimeMillis();
        System.out.println("=====================================");
        System.out.println((end - start) / 1000);
    }

    /**
     * 模型调用-同步start
     */
    public void httpJsonTest_mmSyncStart() {
        String url = "http://10.1.40.134:8088/udsp/http/consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("mmceshi002");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("start");
        externalRequest.setType("sync");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");

        Map<String, String> data = new HashMap<>();
        data.put("date", "20170503");
        data.put("age", "10");
        data.put("modelId", "10");
        data.put("pkId", "10");
        data.put("NAME", "yayahei!");
        externalRequest.setData(data);
        this.requestUdsp(externalRequest, url);
    }

    /**
     * 模型调用-异步start
     */
    public void httpJsonTest_mmAsyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("mmceshi002");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("start");
        externalRequest.setType("async");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");


        Map<String, String> data = new HashMap<>();
        data.put("date", "20170503");
        data.put("age", "10");
        data.put("modelId", "10");
        data.put("name", "10");
        data.put("pkId", "10");
        externalRequest.setData(data);
        this.requestUdsp(externalRequest, url);
    }


    /**
     * 交互查询同步start
     */
    public void httpJsonTest_iqSyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("smart01");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("start");
        externalRequest.setType("sync");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");


        Map<String, String> data = new HashMap<>();
        data.put("client_no", "1113829408");
        externalRequest.setData(data);

        this.requestUdsp(externalRequest, url);
    }


    /**
     * 交互查询异步start
     */
    public void httpJsonTest_iqAsyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("smart01");
        externalRequest.setEntity("start");
        externalRequest.setType("async");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic111");


        Map<String, String> data = new HashMap<>();
        data.put("client_no", "1113829408");
        externalRequest.setData(data);

        this.requestUdsp(externalRequest, url);

    }

    /**
     * 交互查询异步status
     */
    public void httpJsonTest_iqAsyncStatus() {
        ///String url="http://10.1.97.3:9081/udsp/http/consume";
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("smart01");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("status");
        externalRequest.setType("async");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");
        externalRequest.setConsumeId("00ac0d79ec2ae99a2c9931bbcf7bc0ab_f95cf8cc448e4a74a7aa4121a3554531");
        this.requestUdsp(externalRequest, url);
    }


    /**
     * 联机查询同步start
     */
    public void httpJsonTest_olpSyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("test_oracle_service");
        externalRequest.setAppUser("10071");
        externalRequest.setEntity("START");
        externalRequest.setType("SYNC");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setSql("select * from OLQ_APPLICATION t");
        for (int i = 0; i < 10; i++) {
            this.requestUdsp(externalRequest, url);
        }
    }

    /**
     * 联机查询异步start
     */
    public void httpJsonTest_olpAsyncStart() {
        String url = "http://127.0.0.1:8088/udsp/http/consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("core02");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("START");
        externalRequest.setType("ASYNC");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");
        externalRequest.setSql("select * from cmdata.c01_cd_acct limit 10");

        this.requestUdsp(externalRequest, url);
    }

    /**
     * 联机查询异步status
     */
    public void httpJsonTest_olpAsyncStatus() {
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("core02");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("STATUS");
        externalRequest.setType("ASYNC");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");
        externalRequest.setConsumeId("b43f3e6f2f676d7fefb80b3d6b1a06e2_7277d99705e146a58a7cf1b32dc0ed46");
        this.requestUdsp(externalRequest, url);

    }


    public void httpJsonTest_rtsProducerstart() {

        String url = "http://127.0.0.1:8088/udsp/http/consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("rts_producer");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("START");
        externalRequest.setType("SYNC");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");

        List<Map<String, String>> datas = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
        data.put("test030101", "test1");
        data.put("test030102", "test2");
        data.put("test030103", "test3");
        data.put("test030104", "test4");
        datas.add(data);
        externalRequest.setDataStream(datas);

        this.requestUdsp(externalRequest, url);

    }


    /**
     * 公共方法
     *
     * @param externalRequest
     * @param url
     */
    public void requestUdsp(ExternalRequest externalRequest, String url) {

        JSONObject jsonObject = (JSONObject) JSON.toJSON(externalRequest);

        //打印json参数
        System.out.println(jsonObject.toJSONString());

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        InputStream resultStream = null;
        try {
            StringEntity se = new StringEntity(jsonObject.toJSONString());
            se.setContentType(CONTEXT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            String returnString = analysisResponse(response);
            System.out.println(returnString);
            Response responseObject = JSON.parseObject(returnString, Response.class);
            System.out.println(responseObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String analysisResponse(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        String returnString = "";
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        if (statuscode == HttpStatus.SC_NOT_FOUND) {
            throw new RuntimeException("请检查URL!");
        } else if (statuscode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            throw new RuntimeException("服务器内部错误!");
        } else if (statuscode == HttpStatus.SC_BAD_REQUEST) {
            throw new RuntimeException("错误的请求!");
        } else {
            InputStream resultStream = null;
            BufferedReader br = null;
            StringBuffer buffer = new StringBuffer();
            HttpEntity responseEntity = httpResponse.getEntity();
            try {
                resultStream = responseEntity.getContent();
                br = new BufferedReader(new InputStreamReader(resultStream));
                String tempStr;
                while ((tempStr = br.readLine()) != null) {
                    buffer.append(tempStr);
                }
                returnString = buffer.toString();
                //returnString = StringEscapeUtils.unescapeJava(returnString);
            } catch (Exception e) {
                throw new RuntimeException("解析返回结果失败!");
            }
            return returnString;
        }
    }


    //#########################################################

    /**
     * 模型调用-异步status
     */
    public void httpJsonTest_mmAsyncStatus() {
        String url = "http://10.1.40.152:8080/modelManage/test";
        MmTestRequest externalRequest = new MmTestRequest();
        externalRequest.setServiceName("mmceshi002");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("status");
        externalRequest.setType("async");
        externalRequest.setUuid("46028675b4df723ed2ba5b378e574986");
        externalRequest.setModelName("ceshi002");
        this.requestUdsp(externalRequest, url);
    }

    /**
     * 公共方法
     *
     * @param mmTestRequest
     * @param url
     */
    public void requestUdsp(MmTestRequest mmTestRequest, String url) {

        JSONObject jsonObject = (JSONObject) JSON.toJSON(mmTestRequest);

        //打印json参数
        System.out.println(jsonObject.toJSONString());

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        InputStream resultStream = null;
        try {
            StringEntity se = new StringEntity(jsonObject.toJSONString());
            se.setContentType(CONTEXT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            String returnString = analysisResponse(response);
            System.out.println(returnString);
            Response responseObject = JSON.parseObject(returnString, Response.class);
            System.out.println(responseObject.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
