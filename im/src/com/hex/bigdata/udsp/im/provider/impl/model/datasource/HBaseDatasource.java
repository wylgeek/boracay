package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HBaseDatasource extends Datasource {

    public HBaseDatasource(List<Property> properties) {
        super(properties);
    }

    public HBaseDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public HBaseDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getZkQuorum() {
        String value = getProperty("hbase.zk.quorum").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("hbase.zk.quorum不能为空");
        return value;
    }

    public String getZkPort() {
        String value = getProperty("hbase.zk.port").getValue();
        if (StringUtils.isBlank(value))
            value = "2181";
        return value;
    }

    public String getRpcTimeout() {
        String value = getProperty("hbase.rpc.timeout").getValue();
        if (StringUtils.isBlank(value))
            value = "5000";
        return value;
    }

    public String getClientRetriesNumber() {
        String value = getProperty("hbase.client.retries.number").getValue();
        if (StringUtils.isBlank(value))
            value = "3";
        return value;
    }

    public String getClientPause() {
        String value = getProperty("hbase.client.pause").getValue();
        if (StringUtils.isBlank(value))
            value = "100";
        return value;
    }

    public String getZkRecoveryRetry() {
        String value = getProperty("zookeeper.recovery.retry").getValue();
        if (StringUtils.isBlank(value))
            value = "3";
        return value;
    }

    public String getZkRecoveryRetryIntervalmill() {
        String value = getProperty("zookeeper.recovery.retry.intervalmill").getValue();
        if (StringUtils.isBlank(value))
            value = "200";
        return value;
    }

    public String getClientOperationTimeout() {
        String value = getProperty("hbase.client.operation.timeout").getValue();
        if (StringUtils.isBlank(value))
            value = "30000";
        return value;
    }

    public String getRegionserverLeasePeriod() {
        String value = getProperty("hbase.regionserver.lease.period").getValue();
        if (StringUtils.isBlank(value))
            value = "60000";
        return value;
    }
}
