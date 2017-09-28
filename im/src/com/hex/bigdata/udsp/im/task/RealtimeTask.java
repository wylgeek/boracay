package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.common.constant.ServiceMode;
import com.hex.bigdata.udsp.im.service.RealtimeJobService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 实时作业的调度任务
 */
@Component
public class RealtimeTask {
    private static Logger logger = LogManager.getLogger(BatchTask.class);

    @Autowired
    private RealtimeJobService realtimeService;

    /**
     * 服务模式（single、cluster）
     */
    @Value("${service.mode:single}")
    private String serviceMode;

//    @Scheduled(cron = "${check.realtime.status.cron.expression:0/2 * * * * ?}")
    public void checkRealtimeStatus() {
        logger.debug("检查实时作业状态【开始】");
        realtimeService.checkRealtimeStatus();
        logger.debug("检查实时作业状态【结束】");
    }

    @Scheduled(cron = "${check.realtime.live.cron.expression:0 */5 * * * ?}")
    public void checkRealtimeLive() {
        if (ServiceMode.CLUSTER.getValue().equalsIgnoreCase(serviceMode)) {
            logger.debug("检测实时作业存活【开始】");
            realtimeService.checkRealtimeLive();
            logger.debug("检测实时作业存活【结束】");
        } else {
            logger.debug("单机模式不需要检测实时作业存活");
        }
    }

    @Scheduled(cron = "${clean.realtime.outmoded.cron.expression:0 0 0 * * ?}")
    public void cleanOutmodedRealtime() {
        logger.debug("清空过时实时作业【开始】");
        realtimeService.cleanOutmodedRealtime();
        logger.debug("清空过时实时作业【结束】");
    }

}
