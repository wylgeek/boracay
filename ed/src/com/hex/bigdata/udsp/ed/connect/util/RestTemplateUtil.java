package com.hex.bigdata.udsp.ed.connect.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jc.zhao
 * Date:2018/1/10
 * Time:14:56
 */
public class RestTemplateUtil {
    private static Logger logger = LogManager.getLogger(RestTemplateUtil.class);

    public String post(String reqParam, String url) throws Exception {
        // 核心返回结果报文字符串
        String returnJson = "";

        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);// 设置超时
        requestFactory.setReadTimeout(5000);

        //利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        //设置HTTP请求头信息，实现编码等
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        //利用容器实现数据封装，发送
        HttpEntity<String> entity = new HttpEntity<String>(reqParam, headers);
        returnJson = restTemplate.postForObject(url, entity, String.class);

        logger.info("RestTemplate返回结果：【开始】");
        logger.info(returnJson);
        logger.info("RestTemplate返回结果：【结束】");
        return returnJson;
    }
}
