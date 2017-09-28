package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.im.service.BatchJobService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 批量作业的调度任务
 */
@Component
public class BatchTask {
    private static Logger logger = LogManager.getLogger(BatchTask.class);

    @Autowired
    private BatchJobService batchService;

    @Scheduled(cron = "${clean.batch.outmoded.cron.expression:0 0 0 * * ?}")
    public void cleanOutmodedBatch() {
        logger.debug("清空过时的批量作业信息【开始】");
        batchService.cleanOutmodedBatch();
        logger.debug("清空过时的批量作业信息【结束】");
    }
}
