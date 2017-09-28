package com.hex.bigdata.udsp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.model.ExternalRequest;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/5
 * TIME:15:06
 */
public class HttpTestRunable implements Runnable{

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private static final String CONTEXT_TYPE_TEXT_JSON="text/json";

    @Override
    public void run() {
        //httpJsonTest_olpAsyncStart();
        httpJsonTest_olpSyncStatus();
    }

    private void httpJsonTest_olpSyncStatus(){
        String url = "http://127.0.0.1:8088/udsp/http//consume";
        ExternalRequest externalRequest = new ExternalRequest();
        externalRequest.setServiceName("tomnic_mysql");
        externalRequest.setAppUser("10071");
        externalRequest.setEntity("START");
        externalRequest.setType("SYNC");

        externalRequest.setUdspUser("tomnic");
        externalRequest.setToken("000000");
        externalRequest.setSql("select * from school.tb_user t");
        this.requestUdsp(externalRequest,url);
    }

    private void httpJsonTest_olpAsyncStart(){
        String url="http://10.1.97.18/udsp/http//consume";
        ExternalRequest externalRequest=new ExternalRequest();
        externalRequest.setServiceName("core02");
        externalRequest.setAppUser("CORE");
        externalRequest.setEntity("START");
        externalRequest.setType("ASYNC");

        externalRequest.setUdspUser("admin");
        externalRequest.setToken("000000");
        externalRequest.setAppUser("tomnic");
        //externalRequest.setSql("select * from test_wang limit 10");
        //externalRequest.setSql("select * from cmdata.c01_cd_acct limit 10");
        externalRequest.setSql("select zh,count(zh) from tbdata.s01_ffhz  GROUP BY zh limit 10");

        this.requestUdsp(externalRequest,url);
    }

    private void httpJsonTest_iqSyncStart(){
        //测试环境
        //String url="http://10.1.97.18/udsp/http/consume";

        //开发环境
        String url="http://10.1.97.2:9081/udsp/http/consume";
        ExternalRequest externalRequest= new ExternalRequest();
        //基础参数
        externalRequest.setServiceName("soa_jyls_app1");
        externalRequest.setAppUser("10940");
        externalRequest.setEntity("START");
        externalRequest.setType("ASYNC");
        externalRequest.setUdspUser("CRM");
        externalRequest.setToken("000000");

        //业务参数
        Map<String, String> data = new HashMap<>();
        data.put("acct_no", "130000365600030");
        data.put("start_time", "2016-12-31");
        data.put("end_time", "2017-12-31");
        externalRequest.setData(data);
        this.requestUdsp(externalRequest,url);
    }


    /**
     * 公共方法
     * @param externalRequest
     * @param url
     */
    public void requestUdsp(ExternalRequest externalRequest,String url){

        JSONObject jsonObject= (JSONObject) JSON.toJSON(externalRequest);

        //打印json参数
        System.out.println(jsonObject.toJSONString());

        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON);
        InputStream resultStream = null;
        try {
            StringEntity se=new StringEntity(jsonObject.toJSONString());
            se.setContentType(CONTEXT_TYPE_TEXT_JSON);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,APPLICATION_JSON));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            String returnString=analysisResponse(response);
            System.out.println(returnString);
            Response responseObject=JSON.parseObject(returnString, Response.class);
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
                returnString = StringEscapeUtils.unescapeJava(returnString);
            } catch (Exception e) {
                throw new RuntimeException("解析返回结果失败!");
            }
            return returnString;
        }
    }


}
