package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.dto.BatchInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeNodeInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoView;
import com.hex.bigdata.udsp.im.model.RealtimeNodeInfo;
import com.hex.bigdata.udsp.im.model.RealtimeTotalInfo;
import com.hex.bigdata.udsp.im.provider.impl.model.modeling.MqModel;
import com.hex.bigdata.udsp.im.provider.model.Model;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        logger.debug(new Date() + ": Check Realtime Status Job doing something...");
//        if (!quartzManager.checkTriggerExists("test", "test") && !quartzManager.checkJobExists("test", "test")) {
//            quartzManager.addJob("test", "test", "test", "test", JobTest.class, "0/1 * * * * ?");
//        }
//        if (!quartzManager.checkTriggerExists("test2", "test2") && !quartzManager.checkJobExists("test2", "test2")) {
//            quartzManager.addJob("test2", "test2", "test2", "test2", JobTest2.class, "0/2 * * * * ?");
//        }
        List<RealtimeTotalInfo> realtimeTotalInfos = realtimeTotalService.selectList();
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
                    List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
                    for (RealtimeNodeInfo nodeInfo : realtimeNodeInfos) {
                        if (RealtimeStatus.RUNNING == nodeInfo.getStatus()) {
                            flg = false;
                            break;
                        }
                    }
                    if (flg) realtimeTotalService.runFail(id);
                }
                // 所有节点操作
                RealtimeNodeInfo nodeInfo = realtimeNodeService.select(id);
                if (RealtimeStatus.RUNNING == nodeInfo.getStatus()
                        && !quartzManager.checkTriggerExists(id, id) && !quartzManager.checkJobExists(id, id)) {
                    realtimeNodeService.runFail(id, "检查任务和触发器发现都不存在");
                }
            } else if (RealtimeStatus.READY_STOP == status) { // 准备停止
                // --------------------------------------------开始停止作业---------------------------------------------
                logger.debug("开始停止作业...");
                // 管理节点操作
                if (HOST_KEY.equals(stopHost)) {
                    realtimeTotalService.stoping(id);
                }
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
            } else if (RealtimeStatus.STOPING == status) { // 开始停止
                // --------------------------------------------开始检查作业停止情况---------------------------------------------
                logger.debug("开始检查作业停止情况...");
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
            }
        }
    }

    /**
     * 检查每个节点心跳，删除宕机节点的作业信息
     */
    public void checkRealtimeLive() {
        List<HeartbeatInfo> heartbeatInfos = heartbeatService.selectList();
        List<String> list = new ArrayList<>();
        for (HeartbeatInfo info : heartbeatInfos) {
            list.add(info.getIp());
        }
        List<RealtimeTotalInfo> realtimeTotalInfos = realtimeTotalService.selectList();
        for (RealtimeTotalInfo totalInfo : realtimeTotalInfos) {
            MqModel model = totalInfo.getModel();
            String id = model.getId();
            List<RealtimeNodeInfo> realtimeNodeInfos = realtimeNodeService.selectList(id);
            for (RealtimeNodeInfo info : realtimeNodeInfos) {
                String host = info.getHost();
                if (!list.contains(host)) {
                    logger.debug("删除宕机节点的作业信息,ID:" + id + ",HOST:" + host);
                    realtimeNodeService.delete(id, host);
                }
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

        List<RealtimeTotalInfoDto> list = new ArrayList<>();
        List<RealtimeTotalInfo> infos = realtimeTotalService.selectList();
        if (infos == null || infos.size() == 0) {
            return null;
        }
        for (RealtimeTotalInfo info : infos) {
            list.add(infoToDto(info));
        }

        // 过滤
        for (RealtimeTotalInfoDto dto : list) {
            if (StringUtils.isNotBlank(modelName) && !dto.getModelName().contains(modelName)) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(status) && !dto.getStatus().equals(status)) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(startTimeStart) && dto.getStartTime().compareTo(startTimeStart) < 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(startTimeEnd) && dto.getStartTime().compareTo(startTimeEnd) > 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(endTimeStart) && dto.getEndTime().compareTo(endTimeStart) < 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(endTimeEnd) && dto.getEndTime().compareTo(endTimeEnd) > 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeStart) && dto.getUpdateTime().compareTo(updateTimeStart) < 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeEnd) && dto.getUpdateTime().compareTo(updateTimeEnd) > 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(runTimeStart) && dto.getRunTime().compareTo(runTimeStart) < 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(runTimeEnd) && dto.getRunTime().compareTo(runTimeEnd) > 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(stopTimeStart) && dto.getStopTime().compareTo(stopTimeStart) < 0) {
                list.remove(dto);
                continue;
            }
            if (StringUtils.isNotBlank(stopTimeEnd) && dto.getStopTime().compareTo(stopTimeEnd) > 0) {
                list.remove(dto);
                continue;
            }
        }
        return list;
    }

    private RealtimeTotalInfoDto infoToDto(RealtimeTotalInfo info) {
        RealtimeTotalInfoDto dto = new RealtimeTotalInfoDto();
        dto.setId(info.getId());
        dto.setStatus(info.getStatus().getValue());
        dto.setModelName(info.getModel().getName());
        dto.setModelId(info.getModel().getId());
        if(info.getStartTime()!=null){
            dto.setStartTime(format.format(info.getStartTime()));
        }
        if(info.getStopTime()!=null){
            dto.setStopTime(format.format(info.getStopTime()));
        }
        if(info.getRunTime()!=null){
            dto.setRunTime(format.format(info.getRunTime()));
        }
        if(info.getUpdateTime()!=null){
            dto.setUpdateTime(format.format(info.getUpdateTime()));
        }
        if(info.getEndTime()!=null){
            dto.setEndTime(format.format(info.getEndTime()));
        }
        dto.setStartHost(info.getStartHost());
        dto.setStopHost(info.getStopHost());
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
            /**测试数据**/
            List<RealtimeNodeInfoDto> list1 = new ArrayList<>();
            RealtimeNodeInfo realtimeNodeInfo = new RealtimeNodeInfo();
            realtimeNodeInfo.setStatus(RealtimeStatus.RUNNING);
            realtimeNodeInfo.setUpdateTime(new Date());
            realtimeNodeInfo.setMessage("text1...");
            realtimeNodeInfo.setHost("192.168.1.61");
            realtimeNodeInfo.setId("node_1");
            realtimeNodeInfo.setEndTime(new Date());
            realtimeNodeInfo.setRunTime(new Date());

            RealtimeNodeInfo noteInfo = realtimeNodeInfo;
            noteInfo.setId("node_2");
            noteInfo.setHost("192.168.1.62");
            list1.add(infoToDto(realtimeNodeInfo));
            list1.add(infoToDto(noteInfo));
            return list1;
            /**测试数据结束**/
          //  return null;
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
        dto.setRunTime(format.format(info.getRunTime()));
        dto.setEndTime(format.format(info.getEndTime()));
        dto.setUpdateTime(format.format(info.getUpdateTime()));
        return dto;
    }
}
