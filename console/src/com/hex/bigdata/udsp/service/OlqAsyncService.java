package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.dto.WaitNumResult;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.RunQueueService;
import com.hex.bigdata.udsp.mc.service.CurrentService;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.utils.OlqCommUtil;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.thread.WaitQueueCallable;
import com.hex.bigdata.udsp.thread.async.OlqAsyncCallable;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 异步联机查询的服务
 */
public class OlqAsyncService implements Runnable {
    private static final ExecutorService executorService = new ThreadPoolExecutor(32, 128, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(50));
    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private OlqSyncService olqSyncService;
    private McConsumeLogService mcConsumeLogService;
    private RunQueueService runQueueService;
    private CurrentService mcCurrentService;
    private InitParamService initParamService;

    private ConsumeRequest consumeRequest;
    private String dsId;
    private String sql;
    private Page page;
    private String fileName;
    private String appType;
    private long asyncCycleTimeInterval;

    public OlqAsyncService(ConsumeRequest consumeRequest, String dsId, String sql, Page page, String appType, String fileName) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");
        this.mcCurrentService = (CurrentService) WebApplicationContextUtil.getBean("currentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.dsId = dsId;
        this.sql = sql;
        this.page = page;
        this.appType = appType;
        this.fileName = fileName;
    }

    public OlqAsyncService(ConsumeRequest consumeRequest, String dsId, String sql, Page page, String appType, String fileName, long asyncCycleTimeInterval) {
        this.olqSyncService = (OlqSyncService) WebApplicationContextUtil.getBean("olqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.runQueueService = (RunQueueService) WebApplicationContextUtil.getBean("runQueueService");
        this.mcCurrentService = (CurrentService) WebApplicationContextUtil.getBean("currentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.dsId = dsId;
        this.sql = sql;
        this.page = page;
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
            runQueueService.reduceAsyncCurrent(consumeRequest.getMcCurrent());
        }
    }

    private void exec() {
        String consumeId = UdspCommonUtil.getConsumeId(JSONUtil.parseObj2JSON(consumeRequest));
        Current mcCurrent = consumeRequest.getMcCurrent();
        McConsumeLog mcConsumeLog = new McConsumeLog();
        mcConsumeLog.setPkId(mcCurrent.getPkId());
        mcConsumeLog.setRequestContent(mcCurrent.getRequestContent());
        mcConsumeLog.setServiceName(mcCurrent.getServiceName());
        mcConsumeLog.setUserName(mcCurrent.getUserName());
        mcConsumeLog.setRequestStartTime(mcCurrent.getStartTime());
        OlqResponse response = null;
        String status = "0";
        String errorCode = "";
        String message = "成功";
        Calendar calendar = Calendar.getInstance();

        //判断是否进入等待队列，进入等待队列则进行等待
        WaitNumResult waitNumResult = consumeRequest.getWaitNumResult();
        RcUserService rcUserService = consumeRequest.getRcUserService();
        Boolean passFlg = false;
        if (waitNumResult != null && !waitNumResult.isWaitQueueIsFull()) {
            //任务进入等待队列
            String waitQueueTaskId = waitNumResult.getWaitQueueTaskId();
            Future<Boolean> futureTask = executorService.submit(new WaitQueueCallable(mcCurrent, waitQueueTaskId, asyncCycleTimeInterval));
            try {
                long maxAsyncWaitTimeout = (rcUserService == null || rcUserService.getMaxAsyncWaitTimeout() == 0) ?
                        initParamService.getMaxAsyncWaitTimeout() : rcUserService.getMaxAsyncWaitTimeout();
                passFlg = futureTask.get(maxAsyncWaitTimeout, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000014.getValue();
                message = ErrorCode.ERROR_000014.getName() + "：" + e.getMessage();
            } catch (Exception e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000007.getValue();
                message = ErrorCode.ERROR_000007.getName() + "：" + e.getMessage();
            }
            if (passFlg) {
                mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
                //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- start
                mcCurrentService.insert(mcCurrent);
                runQueueService.addAsyncCurrent(mcCurrent);
                //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- end
                Future<OlqResponse> olqFutureTask = executorService.submit(new OlqAsyncCallable(consumeId, mcCurrent, this.dsId, this.sql, this.page, this.fileName));
                try {
                    long maxAsyncExecuteTimeout = (rcUserService == null || rcUserService.getMaxAsyncExecuteTimeout() == 0) ?
                            initParamService.getMaxAsyncExecuteTimeout() : rcUserService.getMaxAsyncExecuteTimeout();
                    response = olqFutureTask.get(maxAsyncExecuteTimeout, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    status = McConstant.MCLOG_STATUS_FAILED;
                    errorCode = ErrorCode.ERROR_000015.getValue();
                    message = ErrorCode.ERROR_000015.getName() + "：" + e.getMessage();
                    mcConsumeLog.setRunEndTime(format.format(new Date()));

                    // 杀死正在执行的SQL
                    Statement stmt = OlqCommUtil.removeStatement(consumeId);
                    if (stmt != null) {
                        try {
                            stmt.cancel();
                            stmt.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }

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
            mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
            try {
                response = this.olqSyncService.asyncStart(consumeId, this.dsId, this.sql, this.page, this.fileName, mcCurrent.getUserName());
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
        //logger.info("select " + appId + this.appType + " application sql=" + sql + " " + message);

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
