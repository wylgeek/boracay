package com.hex.bigdata.udsp.ed.connect.sql;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Properties;

@Component
public class HiveClientConfig {
    public static String driverClass, jdbcUrl, username, password,
            validationQuery;
    public static int initialSize, minIdle, maxIdle, maxWait, maxActive,
            validationQueryTimeout, timeBetweenEvictionRunsMillis;
    public static boolean testWhileIdle, testOnBorrow, testOnReturn;

    public static void loadConf() {
        loadConf("classpath:hive.jdbc.config.properties");
    }

    public static void loadConf(String configFilePath) {
        InputStream in = null;
        try {
            in = HiveClientConfig.class.getClassLoader().getResourceAsStream(
                    configFilePath);
            Properties props = new Properties();
            props.load(in);
            driverClass = props.getProperty("hive.jdbc.driverClassName");
            jdbcUrl = props.getProperty("hive.jdbc.url");
            username = props.getProperty("hive.jdbc.username");
            password = props.getProperty("hive.jdbc.password");
            initialSize = Integer.valueOf(props.getProperty("hive.jdbc.initialSize"));
            minIdle = Integer.valueOf(props.getProperty("hive.jdbc.minIdle"));
            maxIdle = Integer.valueOf(props.getProperty("hive.jdbc.maxIdle"));
            maxWait = Integer.valueOf(props.getProperty("hive.jdbc.maxWait"));
            maxActive = Integer.valueOf(props.getProperty("hive.jdbc.maxActive"));
            validationQuery = props.getProperty("hive.jdbc.validationQuery");
            validationQueryTimeout = Integer.valueOf(props
                    .getProperty("hive.jdbc.validationQueryTimeout"));
            timeBetweenEvictionRunsMillis = Integer.valueOf(props
                    .getProperty("hive.jdbc.timeBetweenEvictionRunsMillis"));
            testWhileIdle = Boolean.valueOf(props.getProperty("hive.jdbc.testWhileIdle"));
            testOnBorrow = Boolean.valueOf(props.getProperty("hive.jdbc.testOnBorrow"));
            testOnReturn = Boolean.valueOf(props.getProperty("hive.jdbc.testOnReturn"));
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDriverClass() {
        return driverClass;
    }

    public static void setDriverClass(String driverClass) {
        HiveClientConfig.driverClass = driverClass;
    }

    public static String getJdbcUrl() {
        return jdbcUrl;
    }

    public static void setJdbcUrl(String jdbcUrl) {
        HiveClientConfig.jdbcUrl = jdbcUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        HiveClientConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        HiveClientConfig.password = password;
    }

    public static String getValidationQuery() {
        return validationQuery;
    }

    public static void setValidationQuery(String validationQuery) {
        HiveClientConfig.validationQuery = validationQuery;
    }

    public static int getInitialSize() {
        return initialSize;
    }

    public static void setInitialSize(int initialSize) {
        HiveClientConfig.initialSize = initialSize;
    }

    public static int getMinIdle() {
        return minIdle;
    }

    public static void setMinIdle(int minIdle) {
        HiveClientConfig.minIdle = minIdle;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(int maxIdle) {
        HiveClientConfig.maxIdle = maxIdle;
    }

    public static int getMaxWait() {
        return maxWait;
    }

    public static void setMaxWait(int maxWait) {
        HiveClientConfig.maxWait = maxWait;
    }

    public static int getMaxActive() {
        return maxActive;
    }

    public static void setMaxActive(int maxActive) {
        HiveClientConfig.maxActive = maxActive;
    }

    public static int getValidationQueryTimeout() {
        return validationQueryTimeout;
    }

    public static void setValidationQueryTimeout(int validationQueryTimeout) {
        HiveClientConfig.validationQueryTimeout = validationQueryTimeout;
    }

    public static int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public static void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        HiveClientConfig.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public static boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public static void setTestWhileIdle(boolean testWhileIdle) {
        HiveClientConfig.testWhileIdle = testWhileIdle;
    }

    public static boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public static void setTestOnBorrow(boolean testOnBorrow) {
        HiveClientConfig.testOnBorrow = testOnBorrow;
    }

    public static boolean isTestOnReturn() {
        return testOnReturn;
    }

    public static void setTestOnReturn(boolean testOnReturn) {
        HiveClientConfig.testOnReturn = testOnReturn;
    }
}
