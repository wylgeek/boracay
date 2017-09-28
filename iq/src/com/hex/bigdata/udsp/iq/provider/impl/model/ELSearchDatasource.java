package com.hex.bigdata.udsp.iq.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Solr的数据源配置
 */
public class ELSearchDatasource extends Datasource {
    private String elasticsearchServers;
    private int maxNum = 65535;

    public ELSearchDatasource(List<Property> properties) {
        super(properties);
    }

    public ELSearchDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public String getElasticsearchServers() {
        String value = getProperty("elasticsearch.servers").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("elasticsearch.servers不能为空");
        return value;
    }

    public int getMaxNum() {
        String value = getProperty("elasticsearch.max.data.size").getValue();
        if (StringUtils.isNotBlank(value)) {
            try {
                maxNum = Integer.valueOf(value);
            } catch (Exception e) {
                throw new IllegalArgumentException("elasticsearch.max.data.size为一整数");
            }
        }
        return maxNum;
    }
}
