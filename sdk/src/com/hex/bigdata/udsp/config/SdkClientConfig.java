package com.hex.bigdata.udsp.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/27
 * TIME:11:12
 */
public class SdkClientConfig {
    public static String consumeUrl;
    static {
        loadConf("udsp.config.properties");
    }
    public static void loadConf(String configFilePath) {
        InputStream in = null;
        try {
            in = SdkFtpClientConfig.class.getClassLoader().getResourceAsStream(
                    configFilePath);
            Properties props = new Properties();
            props.load(in);
            consumeUrl = props.getProperty("udsp.http.url");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConsumeUrl() {
        return consumeUrl;
    }

    public static void setConsumeUrl(String consumeUrl) {
        SdkClientConfig.consumeUrl = consumeUrl;
    }
}
