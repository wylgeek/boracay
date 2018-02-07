package com.hex.bigdata.udsp.im.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Quartz管理
 */
@Service
public class QuartzManager {
    private static Logger logger = LogManager.getLogger(QuartzManager.class);

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    /**
     * 添加一个定时任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass         任务
     * @param cron             时间设置，参考quartz说明文档
     * @Description: 添加一个定时任务
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addJob(String jobName, String jobGroupName,
                       String triggerName, String triggerGroupName, Class jobClass, String cron) {
        logger.debug("添加一个定时任务：jobName=>" + jobName + ", jobGroupName=>" + jobGroupName
                + ",triggerName=>" + triggerName + ",triggerGroupName=>" + triggerGroupName
                + ",jobClass=>" + jobClass + ",cron=>" + cron);
        try {
            // 任务名，任务组，任务执行类
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查一个触发器
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @return
     */
    public boolean checkTriggerExists(String triggerName, String triggerGroupName) {
        logger.debug("检查一个触发器：triggerName=>" + triggerName + ",triggerGroupName=>" + triggerGroupName);
        try {
            return scheduler.checkExists(TriggerKey.triggerKey(triggerName, triggerGroupName));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    /**
     * 检查一个任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     * @return
     */
    public boolean checkJobExists(String jobName, String jobGroupName) {
        logger.debug("检查一个任务：jobName=>" + jobName + ",jobGroupName=>" + jobGroupName);
        try {
            return scheduler.checkExists(JobKey.jobKey(jobName, jobGroupName));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return false;
        }
    }


    /**
     * 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param cron             时间设置，参考quartz说明文档
     * @Description: 修改一个任务的触发时间
     */
    public void modifyJobTime(String jobName,
                              String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        logger.debug("修改一个任务的触发时间：jobName=>" + jobName + ", jobGroupName=>" + jobGroupName
                + ",triggerName=>" + triggerName + ",triggerGroupName=>" + triggerGroupName
                + ",cron=>" + cron);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @Description: 移除一个任务
     */
    public void removeJob(String jobName, String jobGroupName,
                          String triggerName, String triggerGroupName) {
        logger.debug("移除一个任务：jobName=>" + jobName + ", jobGroupName=>" + jobGroupName
                + ",triggerName=>" + triggerName + ",triggerGroupName=>" + triggerGroupName);
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     *
     * @Description:启动所有定时任务
     */
    public void startJobs() {
        logger.debug("启动所有定时任务");
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     *
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        logger.debug("关闭所有定时任务");
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取调度器
     *
     * @return
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * 设置调度器
     *
     * @param scheduler
     */
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}