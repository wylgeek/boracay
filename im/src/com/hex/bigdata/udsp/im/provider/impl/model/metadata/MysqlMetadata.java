package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class MysqlMetadata extends JdbcMetadata {
    public MysqlMetadata() {
    }

    public MysqlMetadata(List<Property> properties) {
        super(properties);
    }

    public MysqlMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public MysqlMetadata(Metadata metadata) {
        super(metadata);
    }
}
