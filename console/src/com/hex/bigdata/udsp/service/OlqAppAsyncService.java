package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.dto.WaitNumResult;
import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.mc.service.McCurrentService;
import com.hex.bigdata.udsp.mc.service.McWaitQueueService;
import com.hex.bigdata.udsp.olq.provider.model.OLQResponse;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.thread.WaitQueueCallable;
import com.hex.bigdata.udsp.thread.async.OlqAppAsyncCallable;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/10
 * TIME:18:38
 */
public class OlqAppAsyncService implements Runnable {
    private static final ExecutorService executorService = new ThreadPoolExecutor(32, 128, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(50));
    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private OlqSyncService olqSyncService;
    private McConsumeLogService mcConsumeLogService;
    private McCurrentCountService mcCurrentCountService;
    private McCurrentService mcCurrentService;

    private ConsumeRequest consumeRequest;
    private String appId;
    private String sql;
    private String fileName;
    private String appType;
    private long asyncCycleTimeInterval = 1000;

    public OlqAppAsyncService(ConsumeRequest consumeRequest, String appId, String sql, String appType, String fileName) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService =(McCurrentService)WebApplicationContextUtil.getBean("mcCurrentService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.sql = sql;
        this.appType = appType;
        this.fileName = fileName;
    }

    public OlqAppAsyncService(ConsumeRequest consumeRequest, String appId, String sql, String appType, String fileName, long asyncCycleTimeInterval) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService =(McCurrentService)WebApplicationContextUtil.getBean("mcCurrentService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.sql = sql;
        this.appType = appType;
        this.fileName = fileName;
        this.asyncCycleTimeInterval = asyncCycleTimeInterval;
    }

    @Override
    public void run() {
        try {
            logger.debug("IQ START 线程调用开始");
            this.exec();
            logger.debug(" IQ START 线程调用结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //减少异步并发统计
            mcCurrentCountService.reduceAsyncCurrent(consumeRequest.getMcCurrent());
        }
    }

    private void exec() {
        McCurrent mcCurrent = consumeRequest.getMcCurrent();

        McConsumeLog mcConsumeLog = new McConsumeLog();
        mcConsumeLog.setPkId(mcCurrent.getPkId());
        mcConsumeLog.setRequestContent(mcCurrent.getRequestContent());
        mcConsumeLog.setServiceName(mcCurrent.getServiceName());
        mcConsumeLog.setUserName(mcCurrent.getUserName());
        mcConsumeLog.setRequestStartTime(mcCurrent.getStartTime());

        String status = "0";
        String errorCode = "";
        String message = "成功";

        Calendar calendar = Calendar.getInstance();
        mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
        OLQResponse response = null;
        String sql = "";

        //判断是否进入等待队列，进入等待队列则进行等待
        WaitNumResult waitNumResult = consumeRequest.getWaitNumResult();
        RcUserService rcUserService = consumeRequest.getRcUserService();
        McWaitQueueService mcWaitQueueService = consumeRequest.getMcWaitQueueService();
        Boolean passFlg = false;
        if (waitNumResult.isIntoWaitQueue()) {
            //任务进入等待队列
            Future<Boolean> futureTask = executorService.submit(new WaitQueueCallable(mcCurrent, asyncCycleTimeInterval));
            try {
                passFlg = futureTask.get(rcUserService.getMaxAsyncWaitTimeout(), TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000014.getValue();
                message = ErrorCode.ERROR_000014.getName() + "：" + e.getMessage();
            } catch (Exception e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000007.getValue();
                message = ErrorCode.ERROR_000007.getName() + "：" + e.getMessage();
            } finally {
                //删除等待队列中的请求信息
            }
            if (passFlg) {
                mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
                //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- start
                mcCurrentService.insert(mcCurrent);
                mcCurrentCountService.addAsyncCurrent(mcCurrent);
                //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- end
                Future<OLQResponse> olqFutureTask = executorService.submit(new OlqAppAsyncCallable(mcCurrent, this.appId, this.sql, this.fileName));
                try {
                    response = olqFutureTask.get(rcUserService.getMaxAsyncWaitTimeout(), TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    status = McConstant.MCLOG_STATUS_FAILED;
                    errorCode = ErrorCode.ERROR_000015.getValue();
                    message = ErrorCode.ERROR_000015.getName() + "：" + e.getMessage();
                    mcConsumeLog.setRunEndTime(format.format(new Date()));
                } catch (Exception e) {
                    status = McConstant.MCLOG_STATUS_FAILED;
                    errorCode = ErrorCode.ERROR_000007.getValue();
                    message = ErrorCode.ERROR_000007.getName() + "：" + e.getMessage();
                    mcConsumeLog.setRunEndTime(format.format(new Date()));
                } finally {
                    //删除执行队列中的请求信息
                }
                if (response != null) {
                    mcConsumeLog.setResponseContent(response.getFilePath());
                }
                mcConsumeLog.setRunEndTime(format.format(new Date()));
            }
        } else {
            try {
                response = this.olqSyncService.asyncStart(this.appId, this.sql, this.fileName, mcCurrent.getUserName());
                message = response.getMessage();
                //内部调用失败
                if (response.getStatus().getValue().equals(Status.DEFEAT.getValue())) {
                    status = McConstant.MCLOG_STATUS_FAILED;
                    errorCode = ErrorCode.ERROR_000007.getValue();
                    message = ErrorCode.ERROR_000007.getName() + "：" + message;
                }
                calendar.add(Calendar.MILLISECOND, (int) response.getConsumeTime());
                mcConsumeLog.setRunEndTime(format.format(calendar.getTime()));
            } catch (Exception e) {
                e.printStackTrace();
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000007.getValue();
                message = ErrorCode.ERROR_000007.getName() + "：" + e.getMessage();
            }
            mcConsumeLog.setRunEndTime(format.format(new Date()));
            mcConsumeLog.setResponseContent(response.getFilePath());
        }

        // 写日志到数据库和本地日志文件
        logger.info("select " + appId + this.appType + " application sql=" + sql + " " + message);

        mcConsumeLog.setMessage(message);
        mcConsumeLog.setRequestEndTime(format.format(new Date()));
        mcConsumeLog.setStatus(status);
        mcConsumeLog.setAppType(this.appType);
        mcConsumeLog.setErrorCode(errorCode);
        mcConsumeLog.setRequestType(mcCurrent.getRequestType());
        mcConsumeLog.setAppName(mcCurrent.getAppName());
        if (CommonConstant.REQUEST_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else {
            mcConsumeLog.setSyncType("NULL");
        }

        this.mcConsumeLogService.insert(mcConsumeLog);
    }
}
