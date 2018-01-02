package com.hex.bigdata.udsp.common.provider.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.util.PropertyUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-3-2.
 */
public class Datasource extends Base {
    private String name;

    private String describe;

    private String type;  // 类型

    private String note;

    private String implClass;

    public Datasource(List<Property> properties) {
        super(properties);
    }

    public Datasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public Datasource(Datasource datasource) {
        super(datasource.getPropertyMap());
        this.type = datasource.getType();
        this.name = datasource.getName();
        this.describe = datasource.getDescribe();
        this.note = datasource.getNote();
        this.implClass = datasource.getImplClass();
    }

    public Datasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        name = comDatasource.getName();
        type = comDatasource.getType();
        describe = comDatasource.getDescribe();
        note = comDatasource.getNote();
        implClass = comDatasource.getImplClass();
        properties = PropertyUtil.convertToPropertyList(comPropertieList);
        if (propertyMap == null)
            propertyMap = new HashMap<>();
        for (Property property : properties) {
            propertyMap.put(property.getName(), property);
        }
    }

    public String getImplClass() {
        return implClass;
    }

    public void setImplClass(String implClass) {
        this.implClass = implClass;
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        if (StringUtils.isBlank(describe))
            throw new IllegalArgumentException("describe不能为空");
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        if (StringUtils.isBlank(type))
            throw new IllegalArgumentException("type不能为空");
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
