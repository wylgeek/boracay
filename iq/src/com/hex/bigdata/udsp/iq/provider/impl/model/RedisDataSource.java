package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/6/28.
 */
public class RedisDatasource extends Datasource {
    public RedisDatasource(List<Property> properties) {
        super(properties);
    }

    public RedisDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public String getIp() {
        String value = getProperty("redis.connection.ip").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.connection.ip不能为空");
        return value;
    }


    public int getPort() {
        String value = getProperty("redis.connection.port").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.connection.port不能为空");
        return Integer.valueOf(value);
    }

    public String getUserName() {
        String value = getProperty("redis.connection.user").getValue();
        if (StringUtils.isBlank(value))
            return null;
        return value;
    }

    public int getMax_idle() {
        String value = getProperty("redis.max.idle").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.max.idle不能为空");
        return Integer.valueOf(value);
    }


    public int getMax_wait() {
        String value = getProperty("redis.max.wait").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("redis.max.wait不能为空");
        return Integer.valueOf(value);
    }

    public int getTimeOut() {
        String value = getProperty("redis.max.timeOut").getValue();
        if (StringUtils.isBlank(value))
            return 600000;
        return Integer.valueOf(value);
    }

    public boolean isTest_on_brrow() {
        String value = getProperty("redis.test.on.brrow").getValue();
        if (StringUtils.isBlank(value))
            return false;
        return Boolean.valueOf(value);
    }

    public int getMax_total() {
        String value = getProperty("redis.max.total").getValue();
        if (StringUtils.isBlank(value))
            return 10;
        return Integer.valueOf(value);
    }

    public String getPassword() {
        String value = getProperty("redis.connection.password").getValue();
        if (StringUtils.isBlank(value))
            return null;
        return value;
    }

    public String getSeprator() {
        String value = getProperty("redis.seprator").getValue();
        if (StringUtils.isBlank(value)) {
            value = "\\007";
        }
        return value;
    }

    public int getMaxNum() {
        String value = getProperty("redis.max.data.size").getValue();
        if (StringUtils.isBlank(value)) {
            value = "65536";
        }
        return Integer.valueOf(value);
    }
}
