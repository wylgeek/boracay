package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-11.
 */
public class JdbcMetadata extends Metadata {
    public JdbcMetadata() {
    }

    public JdbcMetadata(List<Property> properties) {
        super(properties);
    }

    public JdbcMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public JdbcMetadata(Metadata metadata) {
        super(metadata);
    }
}
