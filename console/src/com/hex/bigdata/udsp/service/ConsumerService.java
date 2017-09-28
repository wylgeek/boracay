package com.hex.bigdata.udsp.service;


import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.dto.QueueIsFullResult;
import com.hex.bigdata.udsp.dto.WaitNumResult;
import com.hex.bigdata.udsp.common.constant.*;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.util.*;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.iq.model.IqAppQueryCol;
import com.hex.bigdata.udsp.iq.model.IqApplication;
import com.hex.bigdata.udsp.iq.service.IqAppQueryColService;
import com.hex.bigdata.udsp.iq.service.IqApplicationService;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.mc.service.McCurrentService;
import com.hex.bigdata.udsp.mc.service.McWaitQueueService;
import com.hex.bigdata.udsp.mc.util.McCommonUtil;
import com.hex.bigdata.udsp.mm.dto.MmFullAppInfoView;
import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmApplication;
import com.hex.bigdata.udsp.mm.service.MmApplicationService;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.InnerRequest;
import com.hex.bigdata.udsp.model.Request;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.olq.dto.OLQApplicationDto;
import com.hex.bigdata.udsp.olq.model.OLQApplication;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.olq.service.OLQApplicationService;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.bigdata.udsp.rc.service.RcUserServiceService;
import com.hex.bigdata.udsp.rc.util.RcConstant;
import com.hex.bigdata.udsp.rts.model.RtsConsumer;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.bigdata.udsp.rts.service.RtsConsumerService;
import com.hex.bigdata.udsp.rts.service.RtsMatedataColService;
import com.hex.bigdata.udsp.rts.service.RtsProducerService;
import com.hex.bigdata.udsp.thread.WaitQueueCallable;
import com.hex.bigdata.udsp.thread.sync.IqSyncServiceCallable;
import com.hex.bigdata.udsp.thread.sync.OlqSyncServiceCallable;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.service.UserService;
import com.hex.goframe.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 消费的服务
 */
@Service
public class ConsumerService {
    private static Logger logger = LogManager.getLogger(ConsumerService.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");
    private static final ExecutorService executorService = new ThreadPoolExecutor(256, 2048, 30, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(1000));
    @Autowired
    private RcServiceService rcServiceService;
    @Autowired
    private RcUserServiceService rcUserServiceService;
    @Autowired
    private McConsumeLogService mcConsumeLogService;
    @Autowired
    private IqApplicationService iqApplicationService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private RtsConsumerService rtsConsumerService;
    @Autowired
    private IqSyncService iqSyncService;
    @Autowired
    private OlqSyncService olqSyncService;
    @Autowired
    private RtsSyncService rtsSyncService;
    @Autowired
    private MmRequestService mmRequestService;
    @Autowired
    private MmApplicationService mmApplicationService;
    @Autowired
    private McCurrentCountService mcCurrentCountService;
    @Autowired
    private McCurrentService mcCurrentService;
    @Autowired
    private IqAppQueryColService iqAppQueryColService;
    @Autowired
    private RtsMatedataColService rtsMatedataColService;
    @Autowired
    private UserService userService;
    @Autowired
    private OLQApplicationService olqApplicationService;
    @Autowired
    private McWaitQueueService mcWaitQueueService;

    /**
     * 管理员用户最大同步并发数
     */
    @Value("${admin.sync.count:100}")
    private Integer adminMaxSyncNum;

    /**
     * 管理员用户最大异步并发数
     */
    @Value("${admin.async.count:100}")
    private Integer adminMaxAsyncNum;

    /**
     * 同步循环时间间隔，单位毫秒
     */
    @Value("${sync.cycle.time.interval:200}")
    private long syncCycleTimeInterval;

    /**
     * 异步循环时间间隔，单位秒
     */
    @Value("${async.cycle.time.interval:1000}")
    private long asyncCycleTimeInterval;

    /**
     * 外部请求消费
     *
     * @param externalRequest 外部请求内容
     * @return
     */
    public Response externalConsume(ExternalRequest externalRequest) {
        logger.debug("ExternalRequest=" + JSONUtil.parseObj2JSON(externalRequest));

        long bef = System.currentTimeMillis();

        Request request = new Request();
        ObjectUtil.copyObject(externalRequest, request);

        ConsumeRequest consumeRequest = checkBeforExternalConsume(request);
        Response response = consume(request, consumeRequest, bef);

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        return response;
    }

    /**
     * 外部消费前检查
     */
    private ConsumeRequest checkBeforExternalConsume(Request request) {

        ConsumeRequest consumeRequest = new ConsumeRequest();
        request.setRequestType(ConsumerConstant.CONSUMER_REQUEST_TYPE_OUTER);

        String serviceName = request.getServiceName();
        String udspUser = request.getUdspUser();
        String udspPass = request.getToken();
        String appUser = request.getAppUser();
        String entity = request.getEntity();
        String type = request.getType();

        McCurrent mcCurrent = null;
        //外部调用必输参数检查
        if (StringUtils.isBlank(serviceName) || StringUtils.isBlank(udspUser) || StringUtils.isBlank(appUser) || StringUtils.isBlank(udspPass) || StringUtils.isBlank(entity) || StringUtils.isBlank(type)) {
            consumeRequest.setError(ErrorCode.ERROR_000009);
            return consumeRequest;
        }
        //消费前公共输入参数检查
        //异同步类型检查和entity类型检查
        if (!(ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type) && (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity) || ConsumerConstant.CONSUMER_ENTITY_START.equalsIgnoreCase(entity)))
                && !(ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(type) && ConsumerConstant.CONSUMER_ENTITY_START.equalsIgnoreCase(entity))) {
            consumeRequest.setError(ErrorCode.ERROR_000010);
            return consumeRequest;
        }

        //检查用户身份合法性
        MessageResult messageResult = userService.validateUser(udspUser, udspPass);
        if (!messageResult.isStatus()) {
            consumeRequest.setError(ErrorCode.ERROR_000002);
            return consumeRequest;
        }

        boolean currentFlg = false;
        RcService rcService = rcServiceService.selectByServiceName(serviceName);
        if (rcService == null) {
            //没有注册服务
            consumeRequest.setError(ErrorCode.ERROR_000004);
            return consumeRequest;
        } else {
            consumeRequest.setRcService(rcService);
            String serviceId = rcService.getPkId();
            String appType = rcService.getType();
            String appId = rcService.getAppId();
            String appName = getAppName(appType, appId);
            request.setAppName(appName);
            request.setAppId(appId);
            request.setAppType(appType);

            RcUserService rcUserService = rcUserServiceService.selectByUserIdAndServiceId(udspUser, serviceId);
            if (rcUserService == null) {
                //没有授权服务
                consumeRequest.setError(ErrorCode.ERROR_000008);
                return consumeRequest;
            } else {
                //IP段控制
                consumeRequest.setRcUserService(rcUserService);
                if (StringUtils.isNotBlank(rcUserService.getIpSection())) {
                    //没有拿到IP
                    if (StringUtils.isBlank(request.getRequestIp())) {
                        consumeRequest.setError(ErrorCode.ERROR_000006);
                        return consumeRequest;
                    }
                    boolean ipFlg = rcUserServiceService.checkIpSuitForSections(request.getRequestIp(), rcUserService.getIpSection());
                    if (!ipFlg) {
                        consumeRequest.setError(ErrorCode.ERROR_000006);
                        return consumeRequest;
                    }
                }
                //检查等待队列长度
                WaitNumResult waitNumResult = this.checkWaitNum(request, rcUserService);
                if (waitNumResult.getWaitQueueIsExist()) {
                    //判断等待队列是否满了，满了则退出
                    if (waitNumResult.getWaitQueueIsFull()) {
                        consumeRequest.setError(ErrorCode.ERROR_000016);
                        return consumeRequest;
                    } else {
                        //判断等待队列是否满了，未满则请求进入等待队列
                        waitNumResult.setIntoWaitQueue(true);
                    }
                } else {
                    //不存在等待队列
                    mcCurrent = this.checkCurrentNum(request, rcUserService);
                    if (mcCurrent == null) {
                        //并发数量不够
                        consumeRequest.setError(ErrorCode.ERROR_000003);
                        return consumeRequest;
                    } else {
                        //进入执行队列
                        waitNumResult.setIntoExeQueue(true);
                    }
                }
                //设置结果到消费请求实体
                consumeRequest.setWaitNumResult(waitNumResult);
            }
        }
        consumeRequest.setMcCurrent(mcCurrent);
        consumeRequest.setError(null);
        consumeRequest.setRequest(request);
        return consumeRequest;
    }

    /**
     * 内部请求消费
     *
     * @param innerRequest 内部请求内容
     * @param isAdmin      是否是管理员
     * @return
     */
    public Response innerConsume(InnerRequest innerRequest, boolean isAdmin) {
        logger.debug("InnerRequest=" + JSONUtil.parseObj2JSON(innerRequest));

        long bef = System.currentTimeMillis();

        Request request = new Request();
        ObjectUtil.copyObject(innerRequest, request);

        ConsumeRequest consumeRequest = checkBeforInnerConsume(request, isAdmin);
        Response response = consume(request, consumeRequest, bef);

        if (response.getPage() != null && response.getPage().getPageIndex() >= 1) {
            response.getPage().setPageIndex(response.getPage().getPageIndex() - 1);
        }

        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        return response;
    }

    /**
     * 内部消费前检查
     */
    private ConsumeRequest checkBeforInnerConsume(Request request, boolean isAdmin) {

        ConsumeRequest consumeRequest = new ConsumeRequest();
        request.setRequestType(ConsumerConstant.CONSUMER_REQUEST_TYPE_INNER);

        String udspUser = request.getUdspUser();
        String appType = request.getAppType();
        String appId = request.getAppId();
        String appName = getAppName(appType, appId);
        request.setAppName(appName);
        request.setAppUser(udspUser);
        McCurrent mcCurrent = null;
        String type = request.getType() == null ? "" : request.getType().toUpperCase();
        String entity = request.getEntity() == null ? "" : request.getEntity().toUpperCase();

        if (request.getPage() != null && request.getPage().getPageIndex() >= 0) {
            request.getPage().setPageIndex(request.getPage().getPageIndex() + 1);
        }

        //消费前公共输入参数检查
        //异同步类型检查和entity类型检查
        if (!(ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type) && (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity) || ConsumerConstant.CONSUMER_ENTITY_START.equalsIgnoreCase(entity)))
                && !(ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(type) && ConsumerConstant.CONSUMER_ENTITY_START.equalsIgnoreCase(entity))) {
            consumeRequest.setError(ErrorCode.ERROR_000010);
            return consumeRequest;
        }

        //并发数是否合法
        if (isAdmin) {
            // 管理员用户，直接访问
            // 管理员用户并发数...常量
            //McCurrentCountService mcCurrentCountService = McCurrentCountService.getInstance();
            if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(type)) {
                //同步
                mcCurrent = mcCurrentCountService.checkSyncCurrent(request, adminMaxAsyncNum);
            } else if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type)) {
                //异步
                mcCurrent = mcCurrentCountService.checkAsyncCurrent(request, adminMaxSyncNum);
            }
            if (mcCurrent == null) {
                consumeRequest.setError(ErrorCode.ERROR_000003);
                return consumeRequest;
            }

        } else {
            // 非管理员用户，授权访问
            RcService rcService = rcServiceService.selectByAppTypeAndAppId(appType, appId);
            if (rcService == null) {
                //没有注册服务
                consumeRequest.setError(ErrorCode.ERROR_000004);
                return consumeRequest;
            } else {
                consumeRequest.setRcService(rcService);
                String serviceId = rcService.getPkId();
                String serviceName = rcService.getName();
                request.setServiceName(serviceName);
                RcUserService rcUserService = rcUserServiceService.selectByUserIdAndServiceId(udspUser, serviceId);
                if (rcUserService == null) {
                    // 没有授权服务
                    consumeRequest.setError(ErrorCode.ERROR_000008);
                    return consumeRequest;
                } else {
                    consumeRequest.setRcUserService(rcUserService);
                    //IP段控制
                    if (StringUtils.isBlank(rcUserService.getIpSection())) {
                        //没有拿到IP
                        if (StringUtils.isBlank(request.getRequestIp())) {
                            // TODO ...
                        }
                        boolean ipFlg = rcUserServiceService.checkIpSuitForSections(request.getRequestIp(), rcUserService.getIpSection());
                        if (!ipFlg) {
                            consumeRequest.setError(ErrorCode.ERROR_000006);
                            return consumeRequest;
                        }
                    }
                    //检查等待队列长度
                    WaitNumResult waitNumResult = this.checkWaitNum(request, rcUserService);
                    if (waitNumResult.getWaitQueueIsExist()) {
                        //判断等待队列是否满了，满了则退出
                        if (waitNumResult.getWaitQueueIsFull()) {
                            consumeRequest.setError(ErrorCode.ERROR_000016);
                            return consumeRequest;
                        } else {
                            //判断等待队列是否满了，未满则请求进入等待队列
                            waitNumResult.setIntoWaitQueue(true);
                        }
                    } else {
                        //不存在等待队列
                        mcCurrent = this.checkCurrentNum(request, rcUserService);
                        if (mcCurrent == null) {
                            //并发数量不够
                            consumeRequest.setError(ErrorCode.ERROR_000003);
                            return consumeRequest;
                        } else {
                            //进入执行队列
                            waitNumResult.setIntoExeQueue(true);
                        }
                    }
                    //设置结果到消费请求实体
                    consumeRequest.setWaitNumResult(waitNumResult);
                }
            }
        }
        consumeRequest.setMcCurrent(mcCurrent);
        consumeRequest.setError(null);
        consumeRequest.setRequest(request);
        return consumeRequest;
    }

    /**
     * 消费
     *
     * @param request
     * @param consumeRequest
     * @param bef
     * @return
     */
    private Response consume(Request request, ConsumeRequest consumeRequest, long bef) {
        McCurrent mcCurrent = consumeRequest.getMcCurrent();
        ErrorCode errorCode = consumeRequest.getError();
        RcUserService rcUserService = consumeRequest.getRcUserService();
        try {
            if (consumeRequest.getError() == null) {
                //如果并发对象为空，则从request获取
                if (mcCurrent == null) {
                    if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(request.getType())) {
                        mcCurrent = McCommonUtil.getMcCurrent(consumeRequest.getRequest(), rcUserService.getMaxAsyncNum());
                    } else {
                        mcCurrent = McCommonUtil.getMcCurrent(consumeRequest.getRequest(), rcUserService.getMaxSyncNum());
                    }
                    //设置mcCurrent到消费请求对象consumeRequest中
                    consumeRequest.setMcCurrent(mcCurrent);
                }
                return consume(request, consumeRequest);
            } else {
                Response response = new Response();
                this.setErrorResponse(response, request, bef, errorCode.getValue(), errorCode.getName());
                return response;
            }
        } finally {
            // 从内存数据库中修改并发信息
            if (mcCurrent != null) {
                logger.debug("从内存数据库中修改并发信息:" + mcCurrent.getPkId());
                if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(request.getEntity())) {//所有的status
                    this.mcCurrentCountService.reduceAsyncCurrent(mcCurrent);
                } else if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) { // 所有的同步
                    this.mcCurrentCountService.reduceSyncCurrent(mcCurrent);
                } else if (mcCurrent.getAppType().equalsIgnoreCase(RcConstant.UDSP_SERVICE_TYPE_MM)
                        && ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {// 模型的异步
                    this.mcCurrentCountService.reduceAsyncCurrent(mcCurrent);
                }
            }
        }
    }

    /**
     * 请求出错，设置错误码，错误信息
     *
     * @param response
     * @param errorCode
     * @param message
     * @return
     */
    public void setErrorResponse(Response response, Request request, long bef, String errorCode, String message) {
        String consumeId = UdspCommonUtil.getConsumeId(JSONUtil.parseObj2JSON(request));
        response.setStatus(Status.DEFEAT.getValue());
        response.setStatusCode(StatusCode.DEFEAT.getValue());
        response.setMessage(message);
        response.setErrorCode(errorCode);

        response.setConsumeId(consumeId);
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);
        McConsumeLog mcConsumeLog = new McConsumeLog();
        mcConsumeLog.setRequestEndTime(UdspDateUtil.getDateString(now));

        mcConsumeLog.setRequestStartTime(UdspDateUtil.getDateString(bef));
        mcConsumeLog.setPkId(consumeId);
        mcConsumeLog.setResponseContent("");
        mcConsumeLog.setErrorCode(errorCode);
        mcConsumeLog.setMessage(message);

        if (StringUtils.isBlank(request.getType())) {
            mcConsumeLog.setSyncType("NULL");
        } else if (CommonConstant.REQUEST_SYNC.equals(request.getType())) {
            mcConsumeLog.setSyncType(request.getType());
        }

        //日志信息入库
        this.consumerLogToDb(request, mcConsumeLog, McConstant.MCLOG_STATUS_FAILED);
    }

    /**
     * 向数据库写消费日志
     *
     * @param request      请求信息
     * @param mcConsumeLog
     * @param status       结果状态(0：成功1：失败)
     */
    private void consumerLogToDb(Request request, McConsumeLog mcConsumeLog, String status) {
        //服务名称
        if (StringUtils.isNotBlank(request.getServiceName())) {
            mcConsumeLog.setServiceName(request.getServiceName());
        }
        //用户名称
        if (StringUtils.isNotBlank(request.getUdspUser())) {
            mcConsumeLog.setUserName(request.getUdspUser());
        }
        //请求类型
        if (StringUtils.isNotBlank(request.getRequestType())) {
            mcConsumeLog.setRequestType(request.getRequestType());
        }
        //设置应用类型
        if (StringUtils.isNotBlank(request.getAppType())) {
            mcConsumeLog.setAppType(request.getAppType().toUpperCase());
        }
        //设置应用名称
        if (StringUtils.isNotBlank(request.getAppName())) {
            mcConsumeLog.setAppName(request.getAppName());
        }
        //设置结果状态(0：成功1：失败)
        mcConsumeLog.setStatus(status);
        mcConsumeLog.setRequestContent(JSONUtil.parseObj2JSON(request));
        mcConsumeLogService.insert(mcConsumeLog);
    }

    /**
     * 开始消费
     *
     * @param request
     * @param consumeRequest
     * @return
     */
    public Response consume(Request request, ConsumeRequest consumeRequest) {

        McCurrent mcCurrent = consumeRequest.getMcCurrent();
        RcUserService rcUserService = consumeRequest.getRcUserService();
        WaitNumResult waitNumResult = consumeRequest.getWaitNumResult();

        //如果任务在等待队列中，则mcCurrent带上等待任务的id
        if (null != waitNumResult && StringUtils.isNotBlank(waitNumResult.getWaitQueueTaskId())) {
            mcCurrent.setWaitQueueTaskId(waitNumResult.getWaitQueueTaskId());
        }
        //在等执行列中执行
        long bef = System.currentTimeMillis();
        boolean sync = true;
        long runStart = 0;
        long runEnd = 0;
        Response response = new Response();
        String appType = request.getAppType();
        String appId = request.getAppId();
        String type = request.getType() == null ? "" : request.getType().toUpperCase();
        String entity = request.getEntity() == null ? "" : request.getEntity().toUpperCase();
        Page page = request.getPage();
        String sql = request.getSql();
        String udspUser = request.getUdspUser();

        //在等待队列中等待执行(同步)
        if (null != waitNumResult && waitNumResult.isIntoWaitQueue() && CommonConstant.REQUEST_SYNC.equalsIgnoreCase(waitNumResult.getWaitQueueSyncType())) {
            Future<Boolean> futureTask = executorService.submit(new WaitQueueCallable(mcCurrent, syncCycleTimeInterval));
            try {
                futureTask.get(rcUserService.getMaxSyncWaitTimeout(), TimeUnit.SECONDS);
                mcCurrentService.insert(mcCurrent);
                mcCurrentCountService.addAsyncCurrent(mcCurrent);
            } catch (TimeoutException e) {
                e.printStackTrace();
                this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000014.getValue(), ErrorCode.ERROR_000014.getName());
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName());
                return response;
            } finally {
            }
        }
        //解决应用测试的时候，没有配置同步、异步执行超时时间，则必须先进行判断
        long maxSyncExecuteTimeout = rcUserService == null ? 10000 : rcUserService.getMaxSyncExecuteTimeout();

        //异步时文件
        String localFileName = "";
        //根据类型进入不同的处理逻辑
        if (RcConstant.UDSP_SERVICE_TYPE_IQ.equalsIgnoreCase(appType)) {
            //如果是查询状态的(entity=status),则检查ConsumeId，否则检查应用中必输参数。
            if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity)) {
                if (StringUtils.isBlank(request.getConsumeId())) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                    return response;
                }
            } else {
                //获取可输入参数列表并检查其中的必输参数是否为空
                boolean isError = false;
                StringBuffer needColsName = new StringBuffer();
                for (IqAppQueryCol iqAppQueryCol : iqAppQueryColService.selectByAppId(appId)) {
                    if (EnumTrans.transTrue(iqAppQueryCol.getIsNeed())) {
                        needColsName.append(iqAppQueryCol.getLabel() + ",");
                        if (StringUtils.isBlank(request.getData().get(iqAppQueryCol.getLabel()))) {
                            isError = true;
                        }
                    }
                }
                if (isError) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), "请检查以下参数的值:" + needColsName.substring(0, needColsName.length() - 1));
                    return response;
                }
            }


            //开始iq消费
            if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type)) {
                if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity)) {
                    logger.debug("execute IQ ASYNC STATUS");
                    runStart = System.currentTimeMillis();
                    response = status(request.getConsumeId());
                    runEnd = System.currentTimeMillis();
                } else {
                    logger.debug("execute IQ ASYNC START");
                    sync = false;
                    localFileName = CreateFileUtil.getFileName();
                    if (page != null && page.getPageIndex() > 0) {
                        //异步任务提交到线程池，然后返回
                        ThreadPool.execute(new IqAsyncService(consumeRequest, appId, request.getData(), page, localFileName, asyncCycleTimeInterval));
                    } else {
                        ThreadPool.execute(new IqAsyncService(consumeRequest, appId, request.getData(), localFileName, asyncCycleTimeInterval));
                    }
                }
            } else {
                logger.debug("execute IQ SYNC START");
                runStart = System.currentTimeMillis();
                Future<Response> iqFuture = executorService.submit(new IqSyncServiceCallable(request.getData(), appId, page));
                try {
                    response = iqFuture.get(maxSyncExecuteTimeout, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName());
                    return response;
                } catch (Exception e) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName());
                    return response;
                }
                runEnd = System.currentTimeMillis();
            }
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equalsIgnoreCase(appType)) {
            //开始olq消费
            if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type)) {
                if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity)) {
                    logger.debug("execute OLQ ASYNC STATUS");
                    if (StringUtils.isBlank(request.getConsumeId())) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                        return response;
                    }
                    runStart = System.currentTimeMillis();
                    response = status(request.getConsumeId());
                    runEnd = System.currentTimeMillis();
                } else {
                    logger.debug("execute OLQ ASYNC START");
                    if (StringUtils.isBlank(sql)) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                        return response;
                    }
                    sync = false;
                    localFileName = CreateFileUtil.getFileName();
                    ThreadPool.execute(new OlqAsyncService(consumeRequest, appId, sql, RcConstant.UDSP_SERVICE_TYPE_OLQ, localFileName, asyncCycleTimeInterval));
                }
            } else {
                logger.debug("execute OLQ SYNC START");
                if (StringUtils.isBlank(sql)) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                    return response;
                }
                runStart = System.currentTimeMillis();
                Future<Response> olqFuture = executorService.submit(new OlqSyncServiceCallable(appId, new OLQQuerySql(sql)));
                try {
                    response = olqFuture.get(maxSyncExecuteTimeout, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName());
                    return response;
                } catch (Exception e) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName());
                    return response;
                }
                runEnd = System.currentTimeMillis();
            }
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ_APP.equals(appType)) {
            //开始olqApp消费
            if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type)) {
                if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity)) {
                    logger.debug("execute OLQ_APP ASYNC STATUS");
                    if (StringUtils.isBlank(request.getConsumeId())) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                        return response;
                    }
                    runStart = System.currentTimeMillis();
                    response = status(request.getConsumeId());
                    runEnd = System.currentTimeMillis();
                } else {
                    logger.debug("execute OLQ_APP ASYNC START");
                    sync = false;
                    //通过OLQAppId进行查询
                    OLQApplicationDto olqApplicationDto = this.olqApplicationService.selectFullAppInfo(appId);
                    if (olqApplicationDto == null) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                        return response;
                    }
                    String dsId = olqApplicationDto.getOlqApplication().getOlqDsId();
                    //设置应用名称
                    mcCurrent.setAppName(olqApplicationDto.getOlqApplication().getName());
                    //获取查询SQL
                    MessageResult messageResult = this.olqApplicationService.getExecuteSQL(olqApplicationDto, request.getData());
                    if (messageResult.isStatus()) {
                        localFileName = CreateFileUtil.getFileName();
                        ThreadPool.execute(new OlqAsyncService(consumeRequest, dsId, (String) messageResult.getData(), RcConstant.UDSP_SERVICE_TYPE_OLQ_APP, localFileName, asyncCycleTimeInterval));
                    } else {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000013.getValue(), messageResult.getMessage());
                        return response;
                    }
                }
            } else {
                logger.debug("execute OLQ_APP SYNC START");
                //通过OLQAppId进行查询
                OLQApplicationDto olqApplicationDto = this.olqApplicationService.selectFullAppInfo(appId);
                if (olqApplicationDto == null) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                    return response;
                }
                String dsId = olqApplicationDto.getOlqApplication().getOlqDsId();
                MessageResult messageResult = this.olqApplicationService.getExecuteSQL(olqApplicationDto, request.getData(), request.getPage());
                if (messageResult.isStatus()) {
                    localFileName = CreateFileUtil.getFileName();
                    runStart = System.currentTimeMillis();
                    OLQQuerySql olqQuerySql = (OLQQuerySql) messageResult.getData();
                    Future<Response> olqAppFuture = executorService.submit(new OlqSyncServiceCallable(dsId, olqQuerySql));
                    try {
                        response = olqAppFuture.get(maxSyncExecuteTimeout, TimeUnit.SECONDS);
                    } catch (TimeoutException e) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000015.getValue(), ErrorCode.ERROR_000015.getName());
                        return response;
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000007.getValue(), ErrorCode.ERROR_000007.getName());
                        return response;
                    }
                    runEnd = System.currentTimeMillis();
                } else {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000013.getValue(), messageResult.getMessage());
                    return response;
                }
            }
        } else if (RcConstant.UDSP_SERVICE_TYPE_MM.equalsIgnoreCase(appType)) {
            if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type)) {
                if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(entity)) {
                    logger.debug("execute MM ASYNC STATUS");
                    if (StringUtils.isBlank(request.getConsumeId())) {
                        this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), ErrorCode.ERROR_000009.getName());
                        return response;
                    }
                    //设置消费id到request对象
                    request.setConsumeId(mcCurrent.getPkId());
                    //调用后台
                    response = mmRequestService.status(request, appId);
                } else {
                    logger.debug("execute MM ASYNC START");
                    response = mmRequestService.start(mcCurrent, appId, request);
                }
            } else {
                logger.debug("execute MM SYNC START");
                boolean isError = false;
                StringBuffer needColsName = new StringBuffer();
                MmFullAppInfoView mmFullAppInfoView = mmApplicationService.selectFullAppInfo(appId);
                for (MmAppExecuteParam mmAppExecuteParam : mmFullAppInfoView.getExecuteParams()) {
                    if (EnumTrans.transTrue(mmAppExecuteParam.getIsNeed())) {
                        needColsName.append(mmAppExecuteParam.getName() + ",");
                        if (StringUtils.isBlank(request.getData().get(mmAppExecuteParam.getName()))) {
                            isError = true;
                        }
                    }
                }
                if (isError) {
                    this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), "请检查以下参数的值:" + needColsName.substring(0, needColsName.length() - 1));
                    return response;
                }
                response = mmRequestService.start(mcCurrent, appId, request);

            }
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_PRODUCER.equalsIgnoreCase(appType)) {
            //实时流生产者输入参数检查
            boolean isError = false;
            StringBuffer colsName = new StringBuffer();
            for (RtsMatedataCol rtsMatedataCol : rtsMatedataColService.selectByProducerPkid(appId)) {
                colsName.append(rtsMatedataCol.getName() + ",");
                if (StringUtils.isBlank(request.getDataStream().get(0).get(rtsMatedataCol.getName()))) {
                    isError = true;
                }
            }
            if (isError) {
                this.setErrorResponse(response, request, bef, ErrorCode.ERROR_000009.getValue(), "请检查以下参数的值:" + colsName.substring(0, colsName.length() - 1));
                return response;
            }
            logger.debug("execute RTS_PRODUCER SYNC START");
            runStart = System.currentTimeMillis();
            response = rtsSyncService.startProducer(appId, request.getDataStream());
            runEnd = System.currentTimeMillis();
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_CONSUMER.equalsIgnoreCase(appType)) {
            logger.debug("execute RTS_CONSUMER SYNC START");
            runStart = System.currentTimeMillis();
            response = rtsSyncService.startConsumer(appId, request.getTimeout());
            runEnd = System.currentTimeMillis();
        }

        response.setConsumeId(mcCurrent.getPkId());
        long now = System.currentTimeMillis();
        long consumeTime = now - bef;
        response.setConsumeTime(consumeTime);

        if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(type) && StringUtils.isNotBlank(localFileName)) {
            String localDataFileName = localFileName + CreateFileUtil.DATA_FILE_SUFFIX;
            if ("admin".equals(udspUser)) udspUser = "udsp" + udspUser;
            String ftpDirPath = FTPClientConfig.getRootpath() + "/" + udspUser + "/" + FTPClientConfig.getUsername() + "/" + DateUtil.format(new Date(), "yyyyMMdd");
            String ftpFilePath = ftpDirPath + "/" + localDataFileName;
            response.setResponseContent(ftpFilePath);
        }

        if (sync) {
            this.writeSyncLog(mcCurrent, bef, now, runStart, runEnd, request, response);
        } else {
            //异步设置请求成功
            response.setStatusCode(StatusCode.SUCCESS.getValue());
            response.setStatus(Status.SUCCESS.getValue());
        }

        return response;
    }


    /**
     * 写同步日志到数据库
     */
    private void writeSyncLog(McCurrent mcCurrent, long bef, long now, long runStart, long runEnd, Request request, Response response) {
        McConsumeLog mcConsumeLog = new McConsumeLog();
        mcConsumeLog.setPkId(mcCurrent.getPkId());
        mcConsumeLog.setRequestStartTime(UdspDateUtil.getDateString(bef));
        mcConsumeLog.setRequestEndTime(UdspDateUtil.getDateString(now));
        if (runStart != 0) {
            mcConsumeLog.setRunStartTime(UdspDateUtil.getDateString(runStart));
        }
        if (runEnd != 0) {
            mcConsumeLog.setRunEndTime(UdspDateUtil.getDateString(runEnd));
        }
        if (StringUtils.isNotBlank(response.getErrorCode())) {
            mcConsumeLog.setErrorCode(response.getErrorCode());
        }
        if (StringUtils.isNotBlank(response.getMessage())) {
            mcConsumeLog.setMessage(response.getMessage());
        }
        if (CommonConstant.REQUEST_SYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else if (CommonConstant.REQUEST_ASYNC.equalsIgnoreCase(mcCurrent.getSyncType())) {
            mcConsumeLog.setSyncType(mcCurrent.getSyncType().toUpperCase());
        } else {
            mcConsumeLog.setSyncType("NULL");
        }
        if (StringUtils.isNotBlank(response.getResponseContent())) {
            mcConsumeLog.setResponseContent(response.getResponseContent());
        }
        if (Status.SUCCESS.getValue().equals(response.getStatus())) {
            consumerLogToDb(request, mcConsumeLog, McConstant.MCLOG_STATUS_SUCCESS);
        } else {
            consumerLogToDb(request, mcConsumeLog, McConstant.MCLOG_STATUS_FAILED);
        }
    }

    private String getAppName(String appType, String appId) {
        String appName = "";
        if (RcConstant.UDSP_SERVICE_TYPE_IQ.equalsIgnoreCase(appType)) {
            IqApplication iqApplication = iqApplicationService.select(appId);
            appName = iqApplication.getName();
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equalsIgnoreCase(appType)) {
            ComDatasource comDatasource = comDatasourceService.select(appId);
            appName = comDatasource.getName();
        } else if (RcConstant.UDSP_SERVICE_TYPE_MM.equalsIgnoreCase(appType)) {
            MmApplication mmApplication = mmApplicationService.select(appId);
            appName = mmApplication.getName();
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_PRODUCER.equalsIgnoreCase(appType)) {
            RtsProducer rtsProducer = rtsProducerService.select(appId);
            appName = rtsProducer.getName();
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_CONSUMER.equalsIgnoreCase(appType)) {
            RtsConsumer rtsConsumer = rtsConsumerService.select(appId);
            appName = rtsConsumer.getName();
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ_APP.equalsIgnoreCase(appType)) {
            OLQApplication olqApplication = olqApplicationService.select(appId);
            appName = olqApplication.getName();
        }
        return appName;
    }

    private Response status(String consumeId) {
        Status status = Status.OTHER;
        StatusCode statusCode = StatusCode.OTHER;
        String message = "没有消费";
        String errorCode = "";
        Response response = new Response();
        McCurrent selectMcCurrent = mcCurrentService.select(consumeId);
        if (selectMcCurrent != null) {// 正在消费
            status = Status.RUNING;
            statusCode = StatusCode.RUNING;
            message = ConsumerConstant.CONSUME_ACTION_RUNNING;
        } else {// 在写日志或已写完
            // 查询消费日志
            McConsumeLog mcConsumeLog = mcConsumeLogService.select(consumeId);
            if (mcConsumeLog != null) { // 消费完成
                status = Status.SUCCESS;
                statusCode = StatusCode.SUCCESS;
                if (ConsumerConstant.CONSUME_REQUEST_STATUS_SUCCESS.equals(mcConsumeLog.getStatus())) {
                    message = ConsumerConstant.CONSUME_ACTION_SUCCESS;
                    // 输出消费成功的文件位置信息
                    if (StringUtils.isNotBlank(mcConsumeLog.getRequestContent())) {
                        response.setResponseContent(mcConsumeLog.getResponseContent());
                    }
                } else {
                    message = ConsumerConstant.CONSUME_ACTION_FAILED;
                    //输出具体失败消息
                    if (StringUtils.isNotBlank(mcConsumeLog.getMessage())) {
                        response.setResponseContent("消费id为‘" + consumeId + "’的异步任务失败原因：" + mcConsumeLog.getMessage());
                    }
                }
            } else { // 正在消费
                status = Status.RUNING;
                statusCode = StatusCode.RUNING;
                message = ConsumerConstant.CONSUME_ACTION_RUNNING;
            }
        }
        response.setStatus(status.getValue());
        response.setStatusCode(statusCode.getValue());
        if (StringUtils.isNotBlank(message)) {
            response.setMessage(message);
        }
        if (StringUtils.isNotBlank(errorCode)) {
            response.setErrorCode(errorCode);
        }

        return response;
    }

    /**
     * 检查用户-服务并发数是否达到设定的最大并发数
     * 达到最大并发数返回 false
     *
     * @param request
     * @param rcUserService
     * @return
     */
    private McCurrent checkCurrentNum(Request request, RcUserService rcUserService) {
        McCurrent mcCurrent = null;
        logger.info(Thread.currentThread().getId() + "检查执行队列-开始");
        //并发控制
        if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(request.getType())) {
            //同步
            mcCurrent = mcCurrentCountService.checkSyncCurrent(request, rcUserService.getMaxSyncNum());
        } else if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(request.getType())) {
            //异步
            mcCurrent = mcCurrentCountService.checkAsyncCurrent(request, rcUserService.getMaxAsyncNum());
        }
        logger.info(Thread.currentThread().getId() + "检查执行队列-结束");
        return mcCurrent;
    }

    /**
     * 检查用户-服务并发数是否达到设定的最大并发数
     * 达到最大并发数返回 false
     *
     * @param request
     * @param rcUserService
     * @return
     */
    private WaitNumResult checkWaitNum(Request request, RcUserService rcUserService) {

        WaitNumResult waitNumResult = new WaitNumResult();
        int maxSyncWaitNum = rcUserService.getMaxSyncWaitNum();
        int maxAsyncWaitNum = rcUserService.getMaxAsyncWaitNum();
        //等待队列长度为0，则返回
        if (maxSyncWaitNum == 0 && ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(request.getType())) {
            waitNumResult.setWaitQueueIsExist(false);
            return waitNumResult;
        }

        if (maxAsyncWaitNum == 0 && ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(request.getType())) {
            waitNumResult.setWaitQueueIsExist(false);
            return waitNumResult;
        }

        //等待队列存在
        waitNumResult.setWaitQueueIsExist(true);
        logger.info(Thread.currentThread().getId() + "检查等待队列长度-开始");
        //判断请求的类型，根据不同的类型进行请求队列判断
        QueueIsFullResult isFullResult = null;
        if (ConsumerConstant.CONSUMER_TYPE_SYNC.equalsIgnoreCase(request.getType())) {
            //同步
            isFullResult = mcWaitQueueService.checkWaitQueueIsFull(request, maxSyncWaitNum);
        } else if (ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(request.getType())) {
            //异步
            isFullResult = mcWaitQueueService.checkWaitQueueIsFull(request, maxAsyncWaitNum);
        }
        if (null != isFullResult) {
            waitNumResult.setWaitQueueIsFull(isFullResult.isWaitQueueIsFull());
            waitNumResult.setIntoWaitQueue(!isFullResult.isWaitQueueIsFull());
            waitNumResult.setWaitQueueTaskId(isFullResult.getWaitQueueTaskId());
        }
        waitNumResult.setWaitQueueSyncType(request.getType().toUpperCase());
        logger.info(Thread.currentThread().getId() + "检查等待队列长度-完成");
        return waitNumResult;
    }

}
