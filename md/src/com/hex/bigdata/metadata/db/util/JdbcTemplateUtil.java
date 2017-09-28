package com.hex.bigdata.metadata.db.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by junjiem on 2016-6-24.
 */
public class JdbcTemplateUtil {
    private static Properties props = new Properties();

    static {
        InputStream in = null;
        try {
            in = JdbcTemplateUtil.class.getClassLoader().getResourceAsStream(
                    "jdbc.template.properties");
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDriverClass(String dbType) {
        String key = dbType.toLowerCase() + ".driver.class";
        return getProperty(key);
    }

    public static String getJdbcUrlTemplate(String dbType) {
        String key = dbType.toLowerCase() + ".jdbc.url.template";
        return getProperty(key);
    }

    public static String getJdbcDefaultPort(String dbType) {
        String key = dbType.toLowerCase() + ".jdbc.default.port";
        String port = getProperty(key);
        String msg = "jdbc.template.properties配置文件中" + key + "值";
        ParameterUtil.notInteger(port, msg);
        return port;
    }

    private static String getProperty(String key) {
        String msg = "jdbc.template.properties配置文件中" + key + "值";
        String val = props.getProperty(key);
        ParameterUtil.notEmpty(val, msg);
        return val;
    }
}
