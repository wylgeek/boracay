package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrMetadata extends Metadata {
    public SolrMetadata() {
    }

    public SolrMetadata(List<Property> properties) {
        super(properties);
    }

    public SolrMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public SolrMetadata(Metadata metadata) {
        super(metadata);
    }

    public int getShards() {
        String value = getProperty("solr.shards").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value))
            throw new IllegalArgumentException("solr.shards不能为空且必须是整数");
        return Integer.valueOf(value);
    }

    public int getReplicas() {
        String value = getProperty("solr.replicas").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value))
            //throw new IllegalArgumentException("solr.replicas不能为空且必须是整数");
            value = "2";
        return Integer.valueOf(value);
    }

    public int getMaxShardsPerNode() {
        String value = getProperty("solr.max.shards.per.node").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value))
            //throw new IllegalArgumentException("solr.max.shards.per.node不能为空且必须是整数");
            value = "2";
        return Integer.valueOf(value);
    }
}
