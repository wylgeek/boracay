package com.hex.bigdata.udsp.task;

import com.hex.bigdata.udsp.common.constant.ServiceMode;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.service.HeartbeatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 心跳的任务
 */
@Component
public class HeartbeatTask {
    private static Logger logger = LogManager.getLogger(HeartbeatTask.class);

    @Autowired
    private HeartbeatService heartbeatService;
    @Autowired
    private InitParamService initParamService;

    /**
     * 发送本服务心跳的任务
     */
    @Scheduled(cron = "${send.local.heartbeat.task:*/20 * * * * ?}")
    public void sendLocalHeartbeatTask() {
        if (initParamService.isClusterMode()) {
            logger.debug("发送本服务心跳【开始】");
            heartbeatService.sendLocalHeartbeat();
            logger.debug("发送本服务心跳【结束】");
        } else {
            logger.debug("单机模式不需要发送心跳");
        }
    }

    /**
     * 检查集群服务心跳的任务
     */
    @Scheduled(cron = "${check.cluster.heartbeat.task:*/30 * * * * ?}")
    public void checkClusterHeartbeatTask() {
        if (initParamService.isClusterMode()) {
            logger.debug("检测集群服务心跳【开始】");
            heartbeatService.checkClusterHeartbeat();
            logger.debug("检测集群服务心跳【结束】");
        } else {
            logger.debug("单机模式不需要检查心跳");
        }
    }
}
