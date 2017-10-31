package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.dto.WaitNumResult;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.mc.service.McCurrentService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.rc.util.RcConstant;
import com.hex.bigdata.udsp.thread.WaitQueueCallable;
import com.hex.bigdata.udsp.thread.async.IqAsyncCallable;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 异步交互查询的服务
 */
public class IqAsyncService implements Runnable {
    private static final ExecutorService executorService = new ThreadPoolExecutor(32, 128, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(50));
    private static Logger logger = LoggerFactory.getLogger(IqAsyncService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private IqSyncService iqSyncService;
    private McConsumeLogService mcConsumeLogService;
    private McCurrentCountService mcCurrentCountService;
    private McCurrentService mcCurrentService;
    private InitParamService initParamService;

    private String appId;
    private Map<String, String> paraMap;
    private Page page;
    private String fileName;
    private ConsumeRequest consumeRequest;
    private long asyncCycleTimeInterval;

    public IqAsyncService(ConsumeRequest consumeRequest, String appId, Map<String, String> paraMap, String fileName) {

        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService = (McCurrentService) WebApplicationContextUtil.getBean("mcCurrentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.paraMap = paraMap;
        this.fileName = fileName;
    }

    public IqAsyncService(ConsumeRequest consumeRequest, String appId, Map<String, String> paraMap, String fileName, long asyncCycleTimeInterval) {

        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService = (McCurrentService) WebApplicationContextUtil.getBean("mcCurrentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.paraMap = paraMap;
        this.fileName = fileName;
        this.asyncCycleTimeInterval = asyncCycleTimeInterval;
    }

    public IqAsyncService(ConsumeRequest consumeRequest, String appId, Map<String, String> paraMap, Page page, String fileName) {

        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService = (McCurrentService) WebApplicationContextUtil.getBean("mcCurrentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.paraMap = paraMap;
        this.page = page;
        this.fileName = fileName;
    }

    public IqAsyncService(ConsumeRequest consumeRequest, String appId, Map<String, String> paraMap, Page page, String fileName, long asyncCycleTimeInterval) {

        this.iqSyncService = (IqSyncService) WebApplicationContextUtil.getBean("iqSyncService");
        this.mcConsumeLogService = (McConsumeLogService) WebApplicationContextUtil.getBean("mcConsumeLogService");
        this.mcCurrentCountService = (McCurrentCountService) WebApplicationContextUtil.getBean("mcCurrentCountService");
        this.mcCurrentService = (McCurrentService) WebApplicationContextUtil.getBean("mcCurrentService");
        this.initParamService = (InitParamService) WebApplicationContextUtil.getBean("initParamService");

        this.consumeRequest = consumeRequest;
        this.appId = appId;
        this.paraMap = paraMap;
        this.page = page;
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

        String status = McConstant.MCLOG_STATUS_SUCCESS;
        String errorCode = "";
        String message = "成功";
        Calendar calendar = Calendar.getInstance();

        //判断是否进入等待队列，进入等待队列则进行等待
        WaitNumResult waitNumResult = consumeRequest.getWaitNumResult();
        RcUserService rcUserService = consumeRequest.getRcUserService();
        boolean passFlg = true;
        if (waitNumResult != null && waitNumResult.isIntoWaitQueue()) {//任务进入等待队列
            passFlg = false;
            Future<Boolean> futureTask = executorService.submit(new WaitQueueCallable(mcCurrent, asyncCycleTimeInterval));
            try {
                long maxAsyncWaitTimeout = rcUserService == null ?
                        initParamService.getMaxAsyncWaitTimeout() : rcUserService.getMaxAsyncWaitTimeout();
                passFlg = futureTask.get(maxAsyncWaitTimeout, TimeUnit.MILLISECONDS);
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
        }

        //没有进入等待队列或从等待队列中出来，则进入执行队列
        IqResponse iqResponse = null;
        if (passFlg) {
            mcConsumeLog.setRunStartTime(format.format(calendar.getTime()));
            //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- start
            mcCurrentService.insert(mcCurrent);
            mcCurrentCountService.addAsyncCurrent(mcCurrent);
            //进入执行队列,增加信息并发队列信息   --Add 20170915 by tomnic -- end
            Future<IqResponse> iqFutureTask = executorService.submit(new IqAsyncCallable(mcCurrent, this.appId, this.paraMap, this.page, this.fileName));
            try {
                long maxAsyncExecuteTimeout = rcUserService == null ?
                        initParamService.getMaxAsyncExecuteTimeout() : rcUserService.getMaxAsyncExecuteTimeout();
                iqResponse = iqFutureTask.get(maxAsyncExecuteTimeout, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000014.getValue();
                message = ErrorCode.ERROR_000014.getName() + "：" + e.getMessage();
                mcConsumeLog.setRunEndTime(format.format(new Date()));
            } catch (Exception e) {
                status = McConstant.MCLOG_STATUS_FAILED;
                errorCode = ErrorCode.ERROR_000007.getValue();
                message = ErrorCode.ERROR_000007.getName() + "：" + e.getMessage();
                mcConsumeLog.setRunEndTime(format.format(new Date()));
            } finally {
                //删除执行队列中的请求信息
            }
            if (iqResponse != null) {
                mcConsumeLog.setResponseContent(iqResponse.getFilePath());
            }
        }

        // 写日志到数据库和本地日志文件
        mcConsumeLog.setRequestEndTime(format.format(new Date()));
        mcConsumeLog.setStatus(status);
        mcConsumeLog.setAppType(RcConstant.UDSP_SERVICE_TYPE_IQ);
        mcConsumeLog.setErrorCode(errorCode);
        mcConsumeLog.setRequestType(mcCurrent.getRequestType());
        mcConsumeLog.setAppName(mcCurrent.getAppName());
        mcConsumeLog.setMessage(message);

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
