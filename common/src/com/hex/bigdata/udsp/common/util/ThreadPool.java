package com.hex.bigdata.udsp.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private static int threadPoolSize = 100;
    public static ExecutorService service;

    static {
        InputStream in = null;
        try {
            in = FTPClientConfig.class.getClassLoader().getResourceAsStream(
                    "goframe/udsp/udsp.config.properties");
            Properties props = new Properties();
            props.load(in);
            String threadPoolSizeStr = props.getProperty("thread.pool.size", "100");
            if (StringUtils.isNotBlank(threadPoolSizeStr))
                threadPoolSize = Integer.valueOf(threadPoolSizeStr);
            service = Executors.newFixedThreadPool(threadPoolSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void execute(Runnable runnable) {
        service.execute(runnable);
    }
}
