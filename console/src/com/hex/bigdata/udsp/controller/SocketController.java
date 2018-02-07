package com.hex.bigdata.udsp.controller;

import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.service.ConsumerService;
import com.hex.bigdata.udsp.thread.SocketRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket Controller
 */
@Component
public class SocketController {
    private Logger log = LoggerFactory.getLogger(SocketController.class);

    private static ServerSocket serverSocket;

    @Value("${socket.thread.nums:15}")
    private int socketThreadNums;

    @Value("${socket.port:9089}")
    private int socketPort;

    @Value("${socket.timeout.ms:10000}")
    private int socketTimeoutMs;

    private static ExecutorService executorService;

    @Autowired
    private ConsumerService consumerService;

    private static boolean stopFlag = false;

    @PostConstruct
    public void startSocket() {
        /**
         * 启动服务时启动Socket服务
         */
        log.info("正在启动socket服务,端口号为：" + socketPort);
        try {
            serverSocket = new ServerSocket(socketPort);
            executorService = Executors.newFixedThreadPool(socketThreadNums);
            Thread e = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stopFlag) {
                        Socket server = null;
                        try {
                            server = serverSocket.accept();
                            server.setSoTimeout(socketTimeoutMs);
                            executorService.execute(new Thread(new SocketRunnable(server, consumerService)));
                        } catch (Exception e) {
                            if (server != null) {
                                try {
                                    server.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            e.printStackTrace();
                        }
                    }
                }
            });
            e.start();
            log.info("启动socket服务完成！");
        } catch (Exception e) {
            log.error("启动socket出现错误！" + ExceptionUtil.getMessage(e));
        }
    }

    @PreDestroy
    public void stopSocket() {
        try {
            serverSocket.close();
            log.info("销毁socket服务成功！");
        } catch (Exception e) {
            log.info("销毁socket服务失败！");
        }
        stopFlag = true;
    }

}
