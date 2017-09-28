package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrDatasource extends Datasource {

    public SolrDatasource(List<Property> properties) {
        super(properties);
    }

    public SolrDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public SolrDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getSolrServers() {
        String value = getProperty("solr.servers").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.servers不能为空");
        return value;
    }

    public String getSolrUrl() {
        String value = getProperty("solr.url").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.url不能为空");
        return value;
    }
}