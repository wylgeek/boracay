package com.hex.bigdata.udsp.im.task;

import com.hex.bigdata.udsp.im.model.RealtimeTotalInfo;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;
import com.hex.bigdata.udsp.im.service.ImProviderService;
import com.hex.bigdata.udsp.im.service.RealtimeTotalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by JunjieM on 2017-9-15.
 */
public class RealtimeJob implements Job {
    private static Logger logger = LogManager.getLogger(RealtimeJob.class);
    @Autowired
    private ImProviderService imProviderService;
    @Autowired
    private RealtimeTotalService realtimeTotalService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.debug("一次实时构建【开始】");
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        RealtimeTotalInfo realtimeTotalInfo = realtimeTotalService.select(jobName);
        MqModel model = realtimeTotalInfo.getModel();
        try {
            imProviderService.buildRealtime(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("一次实时构建【结束】");
    }
}
