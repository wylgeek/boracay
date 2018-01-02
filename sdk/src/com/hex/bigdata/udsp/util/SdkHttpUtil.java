package com.hex.bigdata.udsp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:14:35
 */
public class SdkHttpUtil {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(SdkHttpUtil.class);

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    private static final String CONTEXT_TYPE_TEXT_JSON = "text/json";

    private static final int REQUEST_TIME_OUT = 5000;


    /**
     * 调用UDSP
     *
     * @param udspRequest
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T requestUdsp(UdspRequest udspRequest, String url, Class<T> clazz) {
        return requestUdsp(udspRequest, url, clazz, Charset.forName("UTF-8"));
    }

    /**
     * 调用UDSP
     *
     * @param udspRequest
     * @param url
     */
    public static <T> T requestUdsp(UdspRequest udspRequest, String url, Class<T> clazz, Charset charset) {

        T responseObject = null;

        JSONObject jsonObject = (JSONObject) JSON.toJSON(udspRequest);

        //打印json参数
        //logger.info("打印json参数：" + jsonObject.toJSONString());

        CloseableHttpClient httpClient = HttpUtils.getConnection();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        try {
            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), charset);
            stringEntity.setContentType(CONTEXT_TYPE_TEXT_JSON);
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpClient.execute(httpPost);
            String returnString = analysisResponse(response);
            logger.debug("打印返回的字符串：" + returnString);
            responseObject = JSON.parseObject(returnString, clazz);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseObject;
    }

    public static String analysisResponse(HttpResponse httpResponse) {
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
                br = new BufferedReader(new InputStreamReader(resultStream, Charset.forName("UTF-8")));
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
