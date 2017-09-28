package com.hex.bigdata.udsp.im.provider.impl.factory;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * Created by junjiem on 2017-2-20.
 */
public class SolrConnectionPoolFactory {
    private GenericObjectPool pool;

    public SolrConnectionPoolFactory(Config config, String solrServices, String collectionName) {
        SolrConnectionFactory factory = new SolrConnectionFactory(solrServices, collectionName);
        pool = new GenericObjectPool(factory, config);
    }

    public SolrServer getConnection() throws Exception {
        return (SolrServer) pool.borrowObject();
    }

    public void releaseConnection(SolrServer solrServer) {
        try {
            pool.returnObject(solrServer);
        } catch (Exception e) {
            if (solrServer != null) {
                solrServer.shutdown();
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

class SolrConnectionFactory extends BasePoolableObjectFactory {

    private String[] servers;

    public SolrConnectionFactory(String solrServices, String collectionName) {
        String[] tempServers = solrServices.split(",");
        servers = new String[tempServers.length];
        for (int i = 0; i < tempServers.length; i++) {
            servers[i] = "http://" + tempServers[i] + "/solr/" + collectionName;
        }
    }

    @Override
    public Object makeObject() throws Exception {
        SolrServer solrServer = new LBHttpSolrServer(servers);
        return solrServer;
    }

    public void destroyObject(Object obj) throws Exception {
        if (obj instanceof SolrServer) {
            ((SolrServer) obj).shutdown();
        }
    }

    public boolean validateObject(Object obj) {
        if (obj instanceof SolrServer) {
            SolrServer solrServer = ((SolrServer) obj);
            try {
                QueryResponse res = solrServer.query(new SolrQuery().setQuery("*:*").setStart(0).setRows(0));
                int status = res.getStatus();
                if (status != 0) {
                    return false;
                }
            } catch (SolrServerException e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
