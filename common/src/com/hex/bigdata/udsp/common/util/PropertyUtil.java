package com.hex.bigdata.udsp.common.util;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class PropertyUtil {
    /**
     * ComProperties类型转换为Property类型
     *
     * @param comProperties
     * @return
     */
    public static List<Property> convertToPropertyList(List<ComProperties> comProperties) {
        List<Property> propertyList = new ArrayList<>();
        for (ComProperties item : comProperties) {
            Property property = new Property();
            property.setName(item.getName());
            property.setValue(item.getValue());
            property.setDescribe(item.getDescribe());
            propertyList.add(property);
        }
        return propertyList;
    }
}
