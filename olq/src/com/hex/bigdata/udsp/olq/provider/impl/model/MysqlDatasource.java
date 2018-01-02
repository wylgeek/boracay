package com.hex.bigdata.udsp.olq.provider.impl.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/3/10.
 */
public class MysqlDatasource extends Datasource {

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "com.mysql.jdbc.Driver";
         return value;
    }

    public String getJdbcUrl() {
        String value = getProperty("jdbc.url").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("jdbc.url不能为空");
        return value;
    }

    public String getUsername() {
        return getProperty("username").getValue();
    }

    public String getPassword() {
        return getProperty("password").getValue();
    }

    public String getInitialSize() {
        return getProperty("initial.size").getValue();
    }

    public String getMinIdle() {
        return getProperty("min.idle").getValue();
    }

    public String getMaxIdle() {
        return getProperty("max.idle").getValue();
    }

    public String getMaxWait() {
        return getProperty("max.wait").getValue();
    }

    public String getMaxActive() {
        return getProperty("max.active").getValue();
    }

    public String getValidationQueryTimeout() {
        return getProperty("validation.query.timeout").getValue();
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return getProperty("time.between.eviction.runs.millis").getValue();
    }

    public String getMinEvictableIdleTimeMillis() {
        return getProperty("min.evictable.idle.time.millis").getValue();
    }

    public String getValidationQuery() {
        return getProperty("validation.query").getValue();
    }

    public String getTestWhileIdle() {
        return getProperty("test.while.idle").getValue();
    }

    public String getTestOnBorrow() {
        return getProperty("test.on.borrow").getValue();
    }

    public String getTestOnReturn() {
        return getProperty("test.on.return").getValue();
    }

    public MysqlDatasource(List<Property> properties) {
        super(properties);
    }

    public MysqlDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public MysqlDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }
    public int getMaxNum() {
        String value = getProperty("max.data.size").getValue();
        if (StringUtils.isBlank(value)) {
            value = "65535";
        }
        return Integer.valueOf(value);
    }

}
