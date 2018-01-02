package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.lock.RedisDistributedLock;
import com.hex.bigdata.udsp.common.service.InitParamService;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.dto.RealtimeNodeInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoView;
import com.hex.bigdata.udsp.im.model.RealtimeNodeInfo;
import com.hex.bigdata.udsp.im.model.RealtimeTotalInfo;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import com.hex.bigdata.udsp.im.task.QuartzManager;
import com.hex.bigdata.udsp.im.task.RealtimeJob;
import com.hex.bigdata.udsp.model.HeartbeatInfo;
import com.hex.bigdata.udsp.service.HeartbeatService;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by JunjieM on 2017-9-15.
 */
@Service
public class RealtimeJobService {
    private static Logger logger = LogManager.getLogger(RealtimeJobService.class);
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private RealtimeTotalService realtimeTotalService;

    @Autowired
    private RealtimeNodeService realtimeNodeService;

    @Autowired
    private HeartbeatService heartbeatService;

    @Autowired
    @Qualifier("quartzManager")
    private QuartzManager quartzManager;

    @Autowired
    private InitParamService initParamService;

    /**
     * Redis 分布式锁
     */
    @Autowired
    private RedisDistributedLock redisLock;

    /**
     * 启动
     *
     * @param model
     */
    public void start(Model model) throws Exception {
        String id = UdspCommonUtil.getConsumeId(model.getId());
        MqModel mqModel = new MqModel(model);
        realtimeTotalService.readyStart(id, mqModel);
    }

    /**
     * 停止
     *
     * @param id
     */
    public void stop(String id) throws Exception {
        realtimeTotalService.readyStop(id);
    }

    /**
     * 检查实时任务状态
     */
    public void checkRealtimeStatus() {
        String key = "CHECK_REALTIME_STATUS";
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                logger.debug(new Date() + ": Check Realtime Status Job doing something...");
                List<RealtimeTotalInfo> realtimeTotalInfos = realtimeTotalService.selectList();
                if (realtimeTotalInfos == null) {
                    return;
                }
                for (RealtimeTotalInfo totalInfo : realtimeTotalInfos) {
                    String id = totalInfo.getId();
                    MqModel model = totalInfo.getModel();
                    String consumerCronExpression = model.getConsumerCronExpression();
                    RealtimeStatus status = totalInfo.getStatus();
                    String startHost = totalInfo.getStartHost();
                    String stopHost = totalInfo.getStopHost();
                    if (RealtimeStatus.READY_START == status) { // 准备启动
                        // --------------------------------------------开始启动作业---------------------------------------------
                        logger.debug("开始启动作业...");
                        // 管理节点操作
                        if (HOST_KEY.equals(startHost)) {
                            realtimeTotalService.starting(id);
                        }
                        // 所有节点操作
                        realtimeNodeService.starting(id);
                        try {
                            if (!quartzManager.checkTriggerExists(id, id) && !quartzManager.checkJobExists(id, id)) {
                                quartzManager.addJob(id, id, id, id, RealtimeJob.class, consumerCronExpression);
                            }
                            realtimeNodeService.running(id);
                        } catch (Exception e) {
                            realtimeNodeService.startFail(id, e.getMessage());
                        }
                    } else if (RealtimeStatus.STARTING == status) { // 开始启动
                        // --------------------------------------------开始检查作业启动情况---------------------------------------------
                        logger.debug("开始检查作业启动情况...");
                        // 管理节点操作
                        if (HOST_KEY.equals(startHost)) {
                            List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
                            for (RealtimeNodeInfo nodeInfo : realtimeNodeInfos) {
                                if (RealtimeStatus.RUNNING == nodeInfo.getStatus()) {
                                    realtimeTotalService.running(id);
                                    break;
                                }
                            }
                        }
                    } else if (RealtimeStatus.RUNNING == status) { // 正在运行
                        // --------------------------------------------开始检查作业运行情况---------------------------------------------
                        logger.debug("开始检查作业运行情况...");
                        // 管理节点操作
                        if (HOST_KEY.equals(startHost)) {
                            boolean flg = true;
                            long consumerNum = 0;
                            long meetNum = 0;
                            long storeNum = 0;
                            List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
                            for (RealtimeNodeInfo nodeInfo : realtimeNodeInfos) {
                                consumerNum += nodeInfo.getConsumerNum();
                                meetNum += nodeInfo.getMeetNum();
                                storeNum += nodeInfo.getStoreNum();
                                if (RealtimeStatus.RUNNING == nodeInfo.getStatus()) {
                                    flg = false;
                                    break;
                                }
                            }
                            realtimeTotalService.running(id, consumerNum, meetNum, storeNum);
                            if (flg) realtimeTotalService.runFail(id);
                        }
                        // 所有节点操作
                        RealtimeNodeInfo nodeInfo = realtimeNodeService.select(id);
                        if (nodeInfo != null) {
                            if (RealtimeStatus.RUNNING == nodeInfo.getStatus()
                                    && !quartzManager.checkTriggerExists(id, id) && !quartzManager.checkJobExists(id, id)) {
                                realtimeNodeService.runFail(id, "检查任务和触发器发现都不存在");
                            }
                        }
                    } else if (RealtimeStatus.READY_STOP == status) { // 准备停止
                        // --------------------------------------------开始停止作业---------------------------------------------
                        logger.debug("开始停止作业...");
                        // 所有节点操作
                        realtimeNodeService.stoping(id);
                        try {
                            quartzManager.removeJob(id, id, id, id);
                            realtimeNodeService.stopSuccess(id);
                        } catch (Exception e) {
                            if (quartzManager.checkTriggerExists(id, id) && quartzManager.checkJobExists(id, id)) {
                                realtimeNodeService.running(id);
                            } else if (!quartzManager.checkTriggerExists(id, id) && !quartzManager.checkJobExists(id, id)) {
                                realtimeNodeService.stopSuccess(id);
                            } else {
                                realtimeNodeService.stopFail(id, e.getMessage());
                            }
                        }
                        // 管理节点操作
                        if (HOST_KEY.equals(stopHost)) {
                            realtimeTotalService.stoping(id);
                        }
                    } else if (RealtimeStatus.STOPING == status) { // 开始停止
                        // --------------------------------------------开始检查作业停止情况---------------------------------------------
                        logger.debug("开始检查作业停止情况...");
                        // 所有节点操作
                        RealtimeNodeInfo realtimeNodeInfo = realtimeNodeService.select(id);
                        if (realtimeNodeInfo != null) {
                            RealtimeStatus realtimeStatus = realtimeNodeInfo.getStatus();
                            if (RealtimeStatus.RUNNING == realtimeStatus || RealtimeStatus.STOPING == realtimeStatus) {
                                realtimeNodeService.stoping(id);
                                quartzManager.removeJob(id, id, id, id);
                                realtimeNodeService.stopSuccess(id);
                            }
                        }
                        // 管理节点操作
                        if (HOST_KEY.equals(stopHost)) {
                            List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
                            int stopSuccessCount = 0;
                            int stopFailCount = 0;
                            int runningCount = 0;
                            for (RealtimeNodeInfo nodeInfo : realtimeNodeInfos) {
                                if (RealtimeStatus.STOP_SUCCESS == nodeInfo.getStatus()) {
                                    stopSuccessCount++;
                                } else if (RealtimeStatus.STOP_FAIL == nodeInfo.getStatus()) {
                                    stopFailCount++;
                                } else if (RealtimeStatus.RUNNING == nodeInfo.getStatus()) {
                                    runningCount++;
                                }
                            }
                            if (stopSuccessCount == realtimeNodeInfos.size()) {
                                realtimeTotalService.stopSuccess(id);
                            } else if (stopFailCount > 0 && runningCount == 0) {
                                realtimeTotalService.stopFail(id);
                            }
                        }
                    } else if (RealtimeStatus.STOP_SUCCESS == status) { // 停止成功
                        // 所有节点操作
                        RealtimeNodeInfo realtimeNodeInfo = realtimeNodeService.select(id);
                        if (realtimeNodeInfo != null) {
                            RealtimeStatus realtimeStatus = realtimeNodeInfo.getStatus();
                            if (RealtimeStatus.RUNNING == realtimeStatus || RealtimeStatus.STOPING == realtimeStatus) {
                                realtimeNodeService.stoping(id);
                                quartzManager.removeJob(id, id, id, id);
                                realtimeNodeService.stopSuccess(id);
                            }
                        }
                    }
                }
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    /**
     * 检查每个节点心跳，删除宕机节点的作业信息
     */
    public void checkRealtimeLive() {
        String key = "CHECK_REALTIME_LIVE";
        synchronized (key.intern()) { // 单节点上锁（主要防止多线程并发资源不同步问题）
            if (initParamService.isUseClusterRedisLock())
                redisLock.lock(key); // 分布式上锁 （主要防止多节点并发资源不同步问题）
            try {
                List<HeartbeatInfo> heartbeatInfos = heartbeatService.selectList();
                List<String> list = new ArrayList<>();
                for (HeartbeatInfo info : heartbeatInfos) {
                    list.add(info.getIp());
                }
                List<RealtimeTotalInfo> realtimeTotalInfos = realtimeTotalService.selectList();
                for (RealtimeTotalInfo totalInfo : realtimeTotalInfos) {
                    String id = totalInfo.getId();
                    String startHost = totalInfo.getStartHost();
                    List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
                    for (RealtimeNodeInfo info : realtimeNodeInfos) {
                        String host = info.getHost();
                        if (!list.contains(host)) {
                            logger.debug("宕机节点的作业信息,ID:" + id + ",HOST:" + host);
                            //realtimeNodeService.delete(id, host);
                            realtimeNodeService.runFail(id, host, "该节点已宕机或通信中断！");
                            // 如果宕机节点是启动节点，则做更改启动节点为本机
                            if (startHost.equals(host)) {
                                logger.debug("更改实时任务ID:" + id + "的启动节点为本机HOST:" + HOST_KEY);
                                totalInfo.setStartHost(HOST_KEY);
                                realtimeTotalService.update(id, totalInfo);
                            }
                        }
                    }
                }
            } finally {
                if (initParamService.isUseClusterRedisLock())
                    redisLock.unlock(key); // 分布式解锁 （主要防止多节点并发资源不同步问题）
            }
        }
    }

    /**
     * 清空过时的实时作业信息
     */
    public void cleanOutmodedRealtime() {
        List<RealtimeTotalInfo> realtimeTotalInfos = realtimeTotalService.selectList();
        for (RealtimeTotalInfo totalInfo : realtimeTotalInfos) {
            String id = totalInfo.getId();
            RealtimeStatus status = totalInfo.getStatus();
            String startHost = totalInfo.getStartHost();
            String stopHost = totalInfo.getStopHost();
            if (((RealtimeStatus.START_FAIL == status || RealtimeStatus.RUN_FAIL == status) && HOST_KEY.equals(startHost))
                    || ((RealtimeStatus.STOP_FAIL == status || RealtimeStatus.STOP_SUCCESS == status) && HOST_KEY.equals(stopHost))
                    ) { // 异常或停止
                logger.debug("删除异常或停止且过时的实时作业信息,ID:" + id);
                realtimeNodeService.deleteList(id);
                realtimeTotalService.delete(id);
            }
        }
    }

    /**
     * 分页查询
     *
     * @param realtimeTotalInfoView
     * @param page
     * @return
     */
    public PageListResult selectPage(RealtimeTotalInfoView realtimeTotalInfoView, Page page) {
        List<RealtimeTotalInfoDto> infos = selectAll(realtimeTotalInfoView);
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        if (befNum < 0) befNum = 0;
        int count = 0;
        List<RealtimeTotalInfoDto> list = new ArrayList<>();
        if (infos == null || infos.size() == 0) {
            return null;
        }
        for (int i = 0; i < infos.size(); i++) {
            if (i >= befNum) {
                list.add(infos.get(i));
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        PageListResult pageListResult = new PageListResult(list);
        pageListResult.setTotal(infos.size());
        return pageListResult;
    }

    /**
     * 不分页查询
     *
     * @param realtimeTotalInfoView
     * @return
     */
    public List<RealtimeTotalInfoDto> selectAll(RealtimeTotalInfoView realtimeTotalInfoView) {
        String modelName = realtimeTotalInfoView.getModelName();
        String status = realtimeTotalInfoView.getStatus();
        String startTimeStart = realtimeTotalInfoView.getStartTimeStart();
        String endTimeStart = realtimeTotalInfoView.getEndTimeStart();
        String runTimeStart = realtimeTotalInfoView.getRunTimeStart();
        String stopTimeStart = realtimeTotalInfoView.getStopTimeStart();
        String updateTimeStart = realtimeTotalInfoView.getUpdateTimeStart();
        String startTimeEnd = realtimeTotalInfoView.getStartTimeEnd();
        String endTimeEnd = realtimeTotalInfoView.getEndTimeEnd();
        String runTimeEnd = realtimeTotalInfoView.getRunTimeEnd();
        String stopTimeEnd = realtimeTotalInfoView.getStopTimeEnd();
        String updateTimeEnd = realtimeTotalInfoView.getUpdateTimeEnd();
        String startHost = realtimeTotalInfoView.getStartHost();
        String stopHost = realtimeTotalInfoView.getStopHost();

        List<RealtimeTotalInfoDto> list = new ArrayList<>();
        List<RealtimeTotalInfo> infos = realtimeTotalService.selectList();
        if (infos == null || infos.size() == 0) {
            return null;
        }
        for (RealtimeTotalInfo info : infos) {
            list.add(infoToDto(info));
        }

        // 通过查询条件，过滤数据
        List<RealtimeTotalInfoDto> realtimeInfoList = new ArrayList<>();
        for (RealtimeTotalInfoDto dto : list) {
            if (StringUtils.isNotBlank(modelName) && !dto.getModelName().contains(modelName)) {
                continue;
            }
            if (StringUtils.isNotBlank(status) && !dto.getStatus().equals(status)) {
                continue;
            }
            // 查询时，将原数据可能为空的先去除，以下字段也类似
            // startTimeStart
            if (StringUtils.isNotBlank(startTimeStart) && StringUtils.isBlank(dto.getStartTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(startTimeStart) && dto.getStartTime().compareTo(startTimeStart) < 0) {
                continue;
            }
            // startTimeEnd
            if (StringUtils.isNotBlank(startTimeEnd) && StringUtils.isBlank(dto.getStartTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(startTimeEnd) && dto.getStartTime().compareTo(startTimeEnd) > 0) {
                continue;
            }
            // endTimeStart
            if (StringUtils.isNotBlank(endTimeStart) && StringUtils.isBlank(dto.getEndTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeStart) && dto.getEndTime().compareTo(endTimeStart) < 0) {
                continue;
            }
            // endTimeEnd
            if (StringUtils.isNotBlank(endTimeEnd) && StringUtils.isBlank(dto.getEndTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeEnd) && dto.getEndTime().compareTo(endTimeEnd) > 0) {
                continue;
            }
            // updateTimeStart
            if (StringUtils.isNotBlank(updateTimeStart) && StringUtils.isBlank(dto.getUpdateTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeStart) && dto.getUpdateTime().compareTo(updateTimeStart) < 0) {
                continue;
            }
            // updateTimeEnd
            if (StringUtils.isNotBlank(updateTimeEnd) && StringUtils.isBlank(dto.getUpdateTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeEnd) && dto.getUpdateTime().compareTo(updateTimeEnd) > 0) {
                continue;
            }
            // runTimeStart
            if (StringUtils.isNotBlank(runTimeStart) && StringUtils.isBlank(dto.getRunTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(runTimeStart) && dto.getRunTime().compareTo(runTimeStart) < 0) {
                continue;
            }
            // runTimeEnd
            if (StringUtils.isNotBlank(runTimeEnd) && StringUtils.isBlank(dto.getRunTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(runTimeEnd) && dto.getRunTime().compareTo(runTimeEnd) > 0) {
                continue;
            }
            // stopTimeStart
            if (StringUtils.isNotBlank(stopTimeStart) && StringUtils.isBlank(dto.getStopTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(stopTimeStart) && dto.getStopTime().compareTo(stopTimeStart) < 0) {
                continue;
            }
            // stopTimeEnd
            if (StringUtils.isNotBlank(stopTimeEnd) && StringUtils.isBlank(dto.getStopTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(stopTimeEnd) && dto.getStopTime().compareTo(stopTimeEnd) > 0) {
                continue;
            }
            // startHost
            if (StringUtils.isNotBlank(startHost) && StringUtils.isBlank(dto.getStartHost())) {
                continue;
            }
            if (StringUtils.isNotBlank(startHost) && dto.getStartHost().compareTo(startHost) < 0) {
                continue;
            }
            // stopHost
            if (StringUtils.isNotBlank(stopHost) && StringUtils.isBlank(dto.getStopHost())) {
                continue;
            }
            if (StringUtils.isNotBlank(stopHost) && dto.getStopHost().compareTo(stopHost) < 0) {
                continue;
            }
            realtimeInfoList.add(dto);
        }

        // 按照准备启动的时间倒序
        Collections.sort(realtimeInfoList, new Comparator<RealtimeTotalInfoDto>() {
            @Override
            public int compare(RealtimeTotalInfoDto o1, RealtimeTotalInfoDto o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return realtimeInfoList;
    }

    private RealtimeTotalInfoDto infoToDto(RealtimeTotalInfo info) {
        RealtimeTotalInfoDto dto = new RealtimeTotalInfoDto();
        dto.setId(info.getId());
        dto.setStatus(info.getStatus().getValue());
        dto.setModelName(info.getModel().getName());
        dto.setModelId(info.getModel().getId());
        Date startTime = info.getStartTime();
        if (startTime != null) dto.setStartTime(format.format(startTime));
        Date stopTime = info.getStopTime();
        if (stopTime != null) dto.setStopTime(format.format(stopTime));
        Date runTime = info.getRunTime();
        if (runTime != null) dto.setRunTime(format.format(runTime));
        Date updateTime = info.getUpdateTime();
        if (updateTime != null) dto.setUpdateTime(format.format(updateTime));
        Date endTime = info.getEndTime();
        if (endTime != null) dto.setEndTime(format.format(endTime));
        dto.setStartHost(info.getStartHost());
        dto.setStopHost(info.getStopHost());
        dto.setConsumerNum(info.getConsumerNum());
        dto.setMeetNum(info.getMeetNum());
        dto.setStoreNum(info.getStoreNum());
        List<ModelFilterCol> modelFilterCols = info.getModel().getModelFilterCols();
        if (modelFilterCols != null) dto.setRequestContent(JSONUtil.parseList2JSON(modelFilterCols));
        return dto;
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    public RealtimeTotalInfoDto selectDto(String id) {
        return infoToDto(realtimeTotalService.select(id));
    }

    /**
     * 查询子列表
     *
     * @param id
     * @return
     */
    public List<RealtimeNodeInfoDto> selectNodesDto(String id) {
        List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
        if (realtimeNodeInfos == null || realtimeNodeInfos.size() == 0) {
            return null;
        }
        List<RealtimeNodeInfoDto> list = new ArrayList<>();
        for (RealtimeNodeInfo info : realtimeNodeInfos) {
            list.add(infoToDto(info));
        }
        return list;
    }

    private RealtimeNodeInfoDto infoToDto(RealtimeNodeInfo info) {
        RealtimeNodeInfoDto dto = new RealtimeNodeInfoDto();
        dto.setId(info.getId());
        dto.setStatus(info.getStatus().getValue());
        dto.setMessage(info.getMessage());
        dto.setHost(info.getHost());
        Date runTime = info.getRunTime();
        if (runTime != null) dto.setRunTime(format.format(runTime));
        Date endTime = info.getEndTime();
        if (endTime != null) dto.setEndTime(format.format(endTime));
        Date updateTime = info.getUpdateTime();
        if (updateTime != null) dto.setUpdateTime(format.format(updateTime));
        dto.setConsumerNum(info.getConsumerNum());
        dto.setMeetNum(info.getMeetNum());
        dto.setStoreNum(info.getStoreNum());
        return dto;
    }
}
