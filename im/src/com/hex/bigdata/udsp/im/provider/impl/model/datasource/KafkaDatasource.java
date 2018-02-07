package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class KafkaDatasource extends Datasource {
    public KafkaDatasource(List<Property> properties) {
        super(properties);
    }

    public KafkaDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public KafkaDatasource(Datasource datasource) {
        super(datasource);
    }

    public int getThreadNum() {
        String value = getProperty("thread.num").getValue();
        if (StringUtils.isBlank(value))
            value = "1";
        return Integer.valueOf(value);
    }

    public String getZookeeperConnect() {
        return getProperty("zookeeper.connect").getValue();
    }

    public String getMetadataBrokerList() {
        return getProperty("metadata.broker.list").getValue();
    }

    public String getZookeeperSessionTimeoutMs() {
        return getProperty("zookeeper.session.timeout.ms").getValue();
    }

    public String getZookeeperConnectionTimeoutMs() {
        return getProperty("zookeeper.connection.timeout.ms").getValue();
    }

    public String getZookeeperSyncTimeMs() {
        return getProperty("zookeeper.sync.time.ms").getValue();
    }

    public String getConsumerTimeoutMs() {
        return getProperty("consumer.timeout.ms").getValue();
    }

    public String getAutoCommitEnable() {
        return getProperty("auto.commit.enable").getValue();
    }

    public String getAutoCommitIntervalMs() {
        return getProperty("auto.commit.interval.ms").getValue();
    }

    public String getAutoOffsetReset() {
        return getProperty("auto.offset.reset").getValue();
    }

    public String getRebalanceMaxRetries() {
        return getProperty("rebalance.max.retries").getValue();
    }

    public String getRebalanceBackoffMs() {
        return getProperty("rebalance.backoff.ms").getValue();
    }

    public String getGroupId() {
        return getProperty("group.id").getValue();
    }

    public String getSerializerClass() {
        String value = getProperty("serializer.class").getValue();
        if (StringUtils.isBlank(value))
            value = "kafka.serializer.StringEncoder";
        return value;
    }

    public String getKeySerializerClass() {
        String value = getProperty("key.serializer.class").getValue();
        if (StringUtils.isBlank(value))
            value = "kafka.serializer.StringEncoder";
        return value;
    }

    public String getRequestRequiredAcks() {
        String value = getProperty("request.required.acks").getValue();
        if (StringUtils.isBlank(value))
            value = "0";
        return value;
    }
}
