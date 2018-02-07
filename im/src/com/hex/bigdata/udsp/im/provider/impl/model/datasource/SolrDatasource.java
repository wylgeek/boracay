package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrDatasource extends Datasource {

    public SolrDatasource(Datasource datasource) {
        super(datasource);
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