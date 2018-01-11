package com.hex.bigdata.udsp.ed.connect.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

public class HBasePool {
    private static final Logger logger = Logger.getLogger(HBasePool.class.getName());
    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    private static Configuration conf = null;
    private static String zkQuorum = null;
    private static String zkPort = null;

    static {
        initHBaseConfig();
    }

    private static void initHBaseConfig() {
        if (conf == null) {
            conf = HBaseConfiguration.create();
            InputStream in = null;

            try {
                in = HBasePool.class.getClassLoader().getResourceAsStream("goframe/udsp/hbase.config.properties");
                Properties props = new Properties();
                props.load(in);

                zkQuorum = props.getProperty("hbase.zk.quorum");
                zkPort = props.getProperty("hbase.zk.port", "2181");

                conf.set("hbase.zookeeper.quorum", zkQuorum);
                conf.set("hbase.zookeeper.property.clientPort", zkPort);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //解决winutils.exe不存在的问题
            File workaround = new File(".");
            System.getProperties().put("hadoop.home.dir", workaround.getAbsolutePath());
            try {
                new File("./bin").mkdirs();
                new File("./bin/winutils.exe").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static Connection getConnection() {
        while (pool.size() > 10) {
            pool.remove();
        }
        logger.debug("Get HBase Connection...");
        if (pool.size() > 0) {
            Connection conn = pool.remove();
            Boolean isClosed = conn.isClosed();
//			Boolean isAborted = conn.isAborted();
//			Boolean isMasterRunning = conn.isMasterRunning();
            if (isClosed) {
                try {
                    return ConnectionFactory.createConnection(conf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return conn;
        } else {
            try {
                return ConnectionFactory.createConnection(conf);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static void release(Connection conn) {
        logger.debug("Release HBase Connection...");
        pool.add(conn);
    }

}