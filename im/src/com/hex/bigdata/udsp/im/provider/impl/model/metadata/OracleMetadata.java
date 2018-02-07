package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class OracleMetadata extends JdbcMetadata {
    public OracleMetadata() {
    }

    public OracleMetadata(List<Property> properties) {
        super(properties);
    }

    public OracleMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public OracleMetadata(Metadata metadata) {
        super(metadata);
    }
}
