package com.hex.bigdata.udsp.elsearch;

import com.hex.bigdata.udsp.util.HttpUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;

public class ElSearchServiceTest {

    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";

    private static final String CONTEXT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) {
        String url = "http://192.168.125.101:9200/megacorp";
        CloseableHttpClient httpClient = HttpUtils.getConnection();
        HttpPut httpPut = new HttpPut(url);
        httpPut.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        StringEntity stringEntity = new StringEntity("curl -XPUT '192.168.125.101:9200/megacorp/employee/1' -d '{\"acct_no\" : \"1000001\",\"acct_name\" : \"王石\",\"age\" : 25,\"acct_about\" : \"I love to go rock climbing\",\"acct_zcrq\":\"2016-09-22\",\"acct_zcsj\":\"2016-09-22 09:21:22\",\"acct_interests\": [ \"sports\", \"music\" ]}'","UTF-8");
        stringEntity.setContentType(CONTEXT_TYPE_TEXT_JSON);
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPut.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String returnString = analysisResponse(response);
        System.out.println(returnString);
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
