package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-15.
 */
public class MqModel extends Model implements Serializable {
    public MqModel() {
    }

    public MqModel(Model model) {
        super(model);
    }

    public String getTopic() {
        String value = getProperty("topic").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("topic不能为空");
        return value;
    }

    public MqModel(List<Property> properties, Datasource srcDatasource) {
        super(properties, srcDatasource);
    }

    public String getConsumerTimeoutMs() {
        String value = getProperty("consumer.timeout.ms").getValue();
        if (StringUtils.isBlank(value))
            value = "1000";
        return value;
    }

    public String getConsumerCronExpression() {
        String value = getProperty("consumer.cron.expression").getValue();
        if (StringUtils.isBlank(value))
            value = "0/2 * * * * ?";
        return value;
    }
}
