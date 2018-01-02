package com.hex.bigdata.udsp.iq.provider.impl.factory;

import com.hex.bigdata.udsp.iq.provider.impl.model.RedisDatasource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by PC on 2017/6/28.
 */
public class RedisConnectionPoolFactory {
    private JedisPool jedisPool;

    //构造redis的连接池
    public RedisConnectionPoolFactory(RedisDatasource dataSource){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(dataSource.getMax_idle());
        config.setMaxTotal(dataSource.getMax_total());
        config.setMaxWaitMillis(dataSource.getMax_wait());
        config.setTestOnBorrow(dataSource.isTest_on_brrow());
        this.jedisPool = new JedisPool(config,dataSource.getIp(),dataSource.getPort(),dataSource.getTimeOut(),dataSource.getPassword());
    }

    //获取连接
    public Jedis getConnection(){
        if(jedisPool != null){
            return jedisPool.getResource();
        }
        return null;
    }

    //释放连接
    public  boolean release(Jedis jedis){
        if(jedis != null){
            jedisPool.returnResource(jedis);
        }
        return  false;
    }
}
