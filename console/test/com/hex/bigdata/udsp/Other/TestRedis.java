package com.hex.bigdata.udsp.Other;

import redis.clients.jedis.Jedis;

/**
 * Created by PC on 2017/6/28.
 */
public class TestRedis {
    public static void  main(String args[]){
        Jedis jedis = new Jedis("192.168.1.61",6379);
        jedis.set("redisTest:name1|age1|","name1\\007age1");
        jedis.set("redisTest:name1|age2|","name1\\007age2");
        jedis.set("redisTest:name1|age3|","name2\\007age2");
        jedis.set("redisTest:name1|age4|","name3\\007age1");
        jedis.set("redisTest:name1|age5|","name4\\007age1");
        jedis.set("redisTest:name1|age6|","name1\\007age2");
        jedis.set("redisTest:name2|age7|","name2\\007age2");
        jedis.set("redisTest:name3|age8|","name3\\007age1");
        jedis.set("redisTest:name4|age9|","name4\\007age1");

        jedis.set("redisTest:name2|age1|","name1\\007age1");
        jedis.set("redisTest:name2|age2|","name1\\007age2");
        jedis.set("redisTest:name2|age3|","name2\\007age2");
        jedis.set("redisTest:name2|age4|","name3\\007age1");
        jedis.set("redisTest:name2|age5|","name4\\007age1");
        jedis.set("redisTest:name2|age6|","name1\\007age2");
        jedis.set("redisTest:name2|age7|","name2\\007age2");
        jedis.set("redisTest:name2|age8|","name3\\007age1");
        jedis.set("redisTest:name2|age9|","name4\\007age1");
        jedis.close();
    }
}
