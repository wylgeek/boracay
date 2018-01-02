package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.ThreadPool;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dao.HeartbeatMapper;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.dto.WaitNumResult;
import com.hex.bigdata.udsp.mc.constant.McConstant;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.bigdata.udsp.mc.service.McConsumeLogService;
import com.hex.bigdata.udsp.mc.service.RunQueueService;
import com.hex.bigdata.udsp.mc.service.CurrentService;
import com.hex.bigdata.udsp.model.HeartbeatInfo;
import com.hex.bigdata.udsp.model.Request;
import com.hex.bigdata.udsp.olq.dto.OlqApplicationDto;
import com.hex.bigdata.udsp.olq.service.OlqApplicationService;
import com.hex.bigdata.udsp.rc.util.RcConstant;
import com.hex.goframe.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 心跳的服务
 */
@Service
public class HeartbeatService {

    private static Logger logger = LogManager.getLogger(HeartbeatService.class);

    /**
     * 心跳信息的KEY
     */
    private static final String HEARTBEAT_INFO_KEY = "HEARTBEAT";

    /**
     * 本机IP
     */
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    /**
     * 心跳误差阀值(毫秒)
     */
    @Value("${check.cluster.heartbeat.time.ms:60000}")
    private Long checkClusterHeartbeatTimeMs;

    @Autowired
    private HeartbeatMapper heartbeatMapper;

    @Autowired
    private CurrentService mcCurrentNewService;

    @Autowired
    private RunQueueService runQueueService;

    @Autowired
    private McConsumeLogService mcConsumeLogService;

    @Autowired
    private OlqApplicationService olqApplicationService;

    /**
     * 发送本服务心跳。
     */
    public void sendLocalHeartbeat() {
        try {
            HeartbeatInfo heartbeatInfo = new HeartbeatInfo(null, HOST_KEY, null, System.currentTimeMillis());
            heartbeatMapper.insert(HEARTBEAT_INFO_KEY + ":" + HOST_KEY, heartbeatInfo);
            logger.debug("发送心跳信息:" + JSONUtil.parseObj2JSON(heartbeatInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查集群服务心跳。
     * <p>
     * 检查状态更新时间，如果长时间未更新状态则认为宕机并将他们未执行完的任务移到下一个未宕机的服务上执行。
     *
     * @return
     */
    public void checkClusterHeartbeat() {
        try {
            List<HeartbeatInfo> list = heartbeatMapper.selectLike(HEARTBEAT_INFO_KEY);
            logger.debug("集群服务心跳信息:" + JSONUtil.parseList2JSON(list));
            if (list != null) {
                int count = -1;
                long compareTime = System.currentTimeMillis() - checkClusterHeartbeatTimeMs;
                Iterator<HeartbeatInfo> heartbeatIterator = list.iterator();
                for (int i = 0; heartbeatIterator.hasNext(); i++) {
                    //如果是到了本机器则判断前面一台机器是否宕机，如果未宕机则退出循环（有宕机任务会丢给前面一个机器非本机器）
                    HeartbeatInfo heartbeat = heartbeatIterator.next();
                    if (heartbeat.getIp().equals(HOST_KEY) && list.size() > 1) {
                        logger.debug("检测到本服务心跳！");
                        count = i;
                        if (i == 0) {
                            if (list.get(list.size() - 1).getTime() >= compareTime)
                                break;
                        } else {
                            if (list.get(i - 1).getTime() >= compareTime)
                                break;
                        }
                    }
                    //如果小于规定时间则认为该机器已经宕机了,如果单台机器就不做判断循环了
                    if (heartbeat.getTime() < compareTime && count != i && list.size() > 1) {
                        logger.info("检测到宕机服务！心跳信息为：" + JSONUtil.parseObj2JSON(heartbeat));
                        HeartbeatInfo heartbeatInfo = null;
                        logger.info("检测本服务是否允许接管宕机服务未完成的异步任务【开始】");
                        //遍历获取下一个未宕机的机器，并判断是否是本机
                        for (int k = i, t = 0; t < list.size(); k++, t++) {
                            //如果编号为最后一个则后一台机器编号为第一个即0
                            if (k == list.size() - 1) {
                                k = -1;
                            }
                            heartbeatInfo = list.get(k + 1);
                            if (heartbeatInfo.getTime() >= compareTime && !heartbeatInfo.getIp().equals(HOST_KEY)) {
                                break;
                            } else if (heartbeatInfo.getIp().equals(HOST_KEY)) {
                                logger.info("本服务接管宕机服务未完成的异步任务！");
                                //获取宕机机器上的任务转到本机器上运行
                                this.transferTask(heartbeat.getIp());
                                //从内存中移除宕机机器信息
                                heartbeatMapper.delete(HEARTBEAT_INFO_KEY + ":" + heartbeat.getIp());
                                break;
                            }
                        }
                        logger.info("检测本服务是否允许接管宕机服务未完成的异步任务【结束】");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转移任务到本服务
     * <p>
     * 注：同步任务杀死、异步任务重新加入队列，加入队列不成功（并发数达到最大值）则结束任务
     *
     * @param downHostKey 宕机主机key
     */
    private void transferTask(String downHostKey) {
        logger.info("转移服务ID为：" + downHostKey + "上的未完成的异步任务到本机【开始】");
        //获取宕机机器任务
        List<Current> mcCurrentList = mcCurrentNewService.getRunByHost(downHostKey);
        List<Current> newCurrents = new ArrayList<>();
        if (mcCurrentList == null || mcCurrentList.size() == 0) {
            return;
        }
        //遍历并发信息，异步的并发重做
        for (Current mcCurrent : mcCurrentList) {
            //如果是异步则进行处理
            if (CommonConstant.REQUEST_ASYNC.equals(mcCurrent.getSyncType())) {
                newCurrents.add(mcCurrent);
            }
            // 删除宕机机器的并发记录
            mcCurrentNewService.delete(downHostKey, mcCurrent.getPkId());
        }
        for (Current mcCurrent : newCurrents) {
            Map<String, Class> classMap = new HashMap<>();
            classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
            String requestContentJson = JSONUtil.parseObj2JSON(mcCurrent.getRequestContent());
            Request request = JSONUtil.parseJSON2Obj(requestContentJson, Request.class, classMap);
            request.setRequestType(mcCurrent.getRequestType());
            request.setAppType(mcCurrent.getAppType());
            request.setAppName(mcCurrent.getAppName());
            if (ConsumerConstant.CONSUMER_ENTITY_STATUS.equalsIgnoreCase(request.getEntity())) {
                continue;
            }
            //并发检查
            Current returnMcCurrent = runQueueService.checkAsyncCurrent(request, mcCurrent.getMaxCurrentNum());
            if (returnMcCurrent == null) {// 异常信息日志入库
                McConsumeLog mcConsumeLog = new McConsumeLog();
                mcConsumeLog.setRequestEndTime(DateUtil.format(new Date(), DateUtil.YYYY_MM_DDHHMISS));
                mcConsumeLog.setRequestStartTime(mcCurrent.getStartTime());
                mcConsumeLog.setPkId(mcCurrent.getPkId());
                mcConsumeLog.setResponseContent("");
                mcConsumeLog.setErrorCode(ErrorCode.ERROR_000003.getValue());
                mcConsumeLog.setMessage(ErrorCode.ERROR_000003.getName());
                mcConsumeLog.setSyncType(CommonConstant.REQUEST_ASYNC);
                mcConsumeLog.setStatus(McConstant.MCLOG_STATUS_FAILED);
                consumerLogToDb(request, mcConsumeLog);
            }

            mcCurrent.setPkId(returnMcCurrent.getPkId());
            String type = mcCurrent.getAppType();
            String appId = mcCurrent.getAppId();

            //根据不同的APP类型、重新建任务
            //异步时文件
            String localFileName = CreateFileUtil.getFileName();
            //新增消费请求类作为参数-start
            //add 20170908
            ConsumeRequest consumeRequest = new ConsumeRequest();
            consumeRequest.setMcCurrent(mcCurrent);
            WaitNumResult waitNumResult = new WaitNumResult();
            consumeRequest.setWaitNumResult(waitNumResult);
            //新增消费请求类作为参数-end
            if (RcConstant.UDSP_SERVICE_TYPE_IQ.equals(type)) {
                ThreadPool.execute(new IqAsyncService(consumeRequest, appId, request.getData(), request.getPage(), localFileName));
            } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equalsIgnoreCase(type)) {
                ThreadPool.execute(new OlqAsyncService(consumeRequest, appId, request.getSql(), request.getPage(),
                        RcConstant.UDSP_SERVICE_TYPE_OLQ, localFileName));
            } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ_APP.equals(type)) {
                OlqApplicationDto olqApplicationDto = this.olqApplicationService.selectFullAppInfo(appId);
                String dsId = olqApplicationDto.getOlqApplication().getOlqDsId();
                String sql = this.olqApplicationService.getExecuteSQL(olqApplicationDto, request.getData());
                ThreadPool.execute(new OlqAsyncService(consumeRequest, dsId, sql, request.getPage(),
                        RcConstant.UDSP_SERVICE_TYPE_OLQ_APP, localFileName));
            }
        }
        logger.info("转移服务IP为：" + downHostKey + "上的未完成的异步任务到本机【结束】");
    }

    /**
     * 向数据库写消费日志
     *
     * @param request      请求信息
     * @param mcConsumeLog
     */
    private void consumerLogToDb(Request request, McConsumeLog mcConsumeLog) {

        //服务名称
        if (StringUtils.isNotBlank(request.getServiceName())) {
            mcConsumeLog.setServiceName(request.getServiceName());
        } else if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getAppName())) {
            mcConsumeLog.setServiceName(request.getAppName());
        } else {
            mcConsumeLog.setServiceName("serviceName is null");
        }
        //用户名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getUdspUser())) {
            mcConsumeLog.setUserName(request.getUdspUser());
        }

        //请求类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getRequestType())) {
            mcConsumeLog.setRequestType(request.getRequestType());
        }

        //设置应用类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getAppType())) {
            mcConsumeLog.setAppType(request.getAppType().toUpperCase());
        }

        //设置应用名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(request.getAppName())) {
            mcConsumeLog.setAppName(request.getAppName());
        }
        mcConsumeLog.setRequestContent(JSONUtil.parseObj2JSON(request));

        mcConsumeLogService.insert(mcConsumeLog);
    }

    public List<HeartbeatInfo> selectList() {
        return heartbeatMapper.selectLike(HEARTBEAT_INFO_KEY);
    }

}
