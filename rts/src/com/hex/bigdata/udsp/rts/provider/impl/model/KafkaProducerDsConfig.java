package com.hex.bigdata.udsp.rts.provider.impl.model;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.rts.provider.model.ProducerDatasource;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-20.
 */
public class KafkaProducerDsConfig extends ProducerDatasource {

    public KafkaProducerDsConfig(List<Property> propertyList) {
        super(propertyList);
    }

    public KafkaProducerDsConfig(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public String getMetadataBrokerList() {
        return getProperty("metadata.broker.list").getValue();
    }

    public String getSerializerClass() {
        return getProperty("serializer.class").getValue();
    }

    public String getKeySerializerClass() {
        return getProperty("key.serializer.class").getValue();
    }

    public String getRequestRequiredAcks() {
        return getProperty("request.required.acks").getValue();
    }
}
