package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HiveMetadata extends JdbcMetadata {
    public HiveMetadata() {
    }

    public HiveMetadata(List<Property> properties) {
        super(properties);
    }

    public HiveMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public HiveMetadata(Metadata metadata) {
        super(metadata);
    }
}
