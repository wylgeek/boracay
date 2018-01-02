package com.hex.bigdata.udsp.rc.alarm.model;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Base;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.util.PropertyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 警报的配置参数类
 */
public class Config extends Base {

    public Config(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public Config(List<ComProperties> comPropertieList) {
        properties = PropertyUtil.convertToPropertyList(comPropertieList);
        if (propertyMap == null) propertyMap = new HashMap<>();
        for (Property property : properties) propertyMap.put(property.getName(), property);
    }
}
