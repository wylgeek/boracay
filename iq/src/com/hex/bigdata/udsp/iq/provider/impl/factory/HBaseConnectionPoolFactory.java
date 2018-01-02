package com.hex.bigdata.udsp.iq.provider.impl.factory;

import com.hex.bigdata.udsp.iq.provider.impl.model.HBaseDatasource;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by junjiem on 2017-2-20.
 */
public class HBaseConnectionPoolFactory {
    static {
        // 解决winutils.exe不存在的问题
        try {
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir",
                    workaround.getAbsolutePath());
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GenericObjectPool pool;

    public HBaseConnectionPoolFactory(Config config, HBaseDatasource datasource) {
        HBaseConnectionFactory factory = new HBaseConnectionFactory(datasource);
        pool = new GenericObjectPool(factory, config);
    }

    public HConnection getConnection() throws Exception {
        return (HConnection) pool.borrowObject();
    }

    public void releaseConnection(HConnection conn) {
        try {
            pool.returnObject(conn);
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closePool() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class HBaseConnectionFactory extends BasePoolableObjectFactory {

    private Configuration conf;

    public HBaseConnectionFactory(HBaseDatasource datasource) {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", datasource.getZkQuorum());
        conf.set("hbase.zookeeper.property.clientPort", datasource.getZkPort());
        conf.set("hbase.rpc.timeout", datasource.getRpcTimeout());
        conf.set("hbase.client.retries.number", datasource.getClientRetriesNumber());
        conf.set("hbase.client.pause", datasource.getClientPause());
        conf.set("zookeeper.recovery.retry", datasource.getZkRecoveryRetry());
        conf.set("zookeeper.recovery.retry.intervalmill", datasource.getZkRecoveryRetryIntervalmill());
        conf.set("hbase.client.operation.timeout", datasource.getClientOperationTimeout());
        conf.set("hbase.regionserver.lease.period", datasource.getRegionserverLeasePeriod());
    }

    @Override
    public Object makeObject() throws Exception {
        HConnection conn = HConnectionManager.createConnection(conf);
        return conn;
    }

    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof HConnection) {
            ((HConnection) obj).close();
        }
    }

    public boolean validateObject(Object obj) {
        if (obj instanceof HConnection) {
            HConnection conn = ((HConnection) obj);
            if (conn.isAborted()) {
                return false;
            }
            if (conn.isClosed()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
