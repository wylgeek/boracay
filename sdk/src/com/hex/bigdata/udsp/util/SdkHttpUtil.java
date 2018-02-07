package com.hex.bigdata.udsp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.model.request.UdspRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    /**
     * 调用UDSP
     *
     * @param udspRequest
     * @param url
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
        JSONObject jsonObject = (JSONObject) JSON.toJSON(udspRequest);
        String json = jsonObject.toJSONString();
        return HttpUtils.requestPost(url, json, null, clazz, charset, null, null);
    }
}
