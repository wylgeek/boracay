package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class KafkaModel extends MqModel{
    public KafkaModel(){
    }

    public KafkaModel(Model model) {
        super(model);
    }

    public KafkaModel(List<Property> properties, Datasource srcDatasource){
        super(properties,srcDatasource);
    }

    public String getGroupId() {
        String value = getProperty("group.id").getValue();
//        if (StringUtils.isBlank(value))
//            throw new IllegalArgumentException("group.id不能为空");
        if (StringUtils.isBlank(value))
            value = getId();
        return value;
    }
}
