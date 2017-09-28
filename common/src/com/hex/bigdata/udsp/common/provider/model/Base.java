package com.hex.bigdata.udsp.common.provider.model;

import com.hex.goframe.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Base {

    protected List<Property> properties; // 配置参数集合

    protected Map<String, Property> propertyMap; // 配置参数Map

    public Base() {
    }

    public Base(List<Property> properties) {
        setProperties(properties);
    }

    public Base(Map<String, Property> propertyMap) {
        setPropertyMap(propertyMap);
    }

    public String getId() {
        StringBuffer sb = new StringBuffer();
        for (Property property : properties) {
            sb.append(property.getName() + "=" + property.getValue() + "\n");
        }
        return Util.MD5(sb.toString());
    }

    public Property getProperty(String key) {
        Property property = propertyMap.get(key);
        if (property == null) {
            property = new Property();
        }
        return property;
    }

    public Map<String, Property> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, Property> propertieMap) {
        this.propertyMap = propertieMap;
        if (properties == null)
            properties = new ArrayList<Property>();
        for (Map.Entry<String, Property> entry : propertieMap.entrySet()) {
            properties.add(entry.getValue());
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
        if (propertyMap == null)
            propertyMap = new HashMap<String, Property>();
        for (Property property : properties) {
            propertyMap.put(property.getName(), property);
        }
    }

}
