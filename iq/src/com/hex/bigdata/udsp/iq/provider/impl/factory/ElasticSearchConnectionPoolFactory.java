package com.hex.bigdata.udsp.iq.provider.impl.factory;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;


public class ElasticSearchConnectionPoolFactory {

    private GenericObjectPool pool;

    //构造ES的连接池
    public ElasticSearchConnectionPoolFactory(Config config, String elasticsearchServers){
        ElasticSearchConnectionFactory factory = new ElasticSearchConnectionFactory(elasticsearchServers);
        pool = new GenericObjectPool(factory,config );
    }

    public RestClient getConnection() throws Exception {
        return (RestClient) pool.borrowObject();
    }

    public void releaseConnection(RestClient restClient) {
        try {
            pool.returnObject(restClient);
        } catch (Exception e) {
            if (restClient != null) {
                try {
                    restClient.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
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


class ElasticSearchConnectionFactory extends BasePoolableObjectFactory {

    private HttpHost[] hosts;

    public ElasticSearchConnectionFactory(String elSearchServers) {
        String[] tempServers = elSearchServers.split(",");
        hosts = new HttpHost[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            String item = tempServers[i];
            String[] array = item.split("\\:");
            hosts[i] = new HttpHost(array[0], Integer.valueOf(array[1]), "http");
        }
    }

    @Override
    public Object makeObject() throws Exception {
        RestClient solrServer = RestClient.builder(hosts).build();
        return solrServer;
    }

    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof RestClient) {
            ((RestClient) obj).close();
        }
    }
}
