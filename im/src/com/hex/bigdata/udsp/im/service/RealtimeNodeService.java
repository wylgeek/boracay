package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.RealtimeStatus;
import com.hex.bigdata.udsp.im.dao.RealtimeNodeMapper;
import com.hex.bigdata.udsp.im.model.RealtimeNodeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-15.
 */
@Service
public class RealtimeNodeService {
    private static Logger logger = LogManager.getLogger(RealtimeNodeService.class);
    private static final String REALTIME_NODE_INFO_KEY = "REALTIME_NODE";
    private static final String KEY_DELIMITER = ":";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private RealtimeNodeMapper realtimeNodeMapper;

    public void starting(String id) {
        logger.debug("添加实时作业的节点信息【开始启动】");
        try {
            RealtimeNodeInfo realtimeInfo = new RealtimeNodeInfo();
            realtimeInfo.setId(id);
            realtimeInfo.setHost(HOST_KEY);
            realtimeInfo.setStatus(RealtimeStatus.STARTING);
            realtimeInfo.setUpdateTime(new Date());
            insert(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void running(String id) {
        logger.debug("更新实时作业的节点信息【正在运行】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUNNING);
            Date nowDate = new Date();
            realtimeInfo.setRunTime(nowDate);
            realtimeInfo.setUpdateTime(nowDate);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void running(String id, String message) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUNNING);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void running(String id, long consumerNum, long meetNum, long storeNum) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUNNING);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setConsumerNum(consumerNum);
            realtimeInfo.setMeetNum(meetNum);
            realtimeInfo.setStoreNum(storeNum);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startFail(String id, String message) {
        logger.debug("更新实时作业的节点信息【启动失败】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.START_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stoping(String id) {
        logger.debug("更新实时作业的节点信息【开始停止】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOPING);
            realtimeInfo.setUpdateTime(new Date());
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopSuccess(String id) {
        logger.debug("更新实时作业的节点信息【停止成功】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_SUCCESS);
            Date nowDate = new Date();
            realtimeInfo.setUpdateTime(nowDate);
            realtimeInfo.setEndTime(nowDate);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopFail(String id, String message) {
        logger.debug("更新实时作业的节点信息【停止失败】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.STOP_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runFail(String id, String message) {
        logger.debug("更新实时作业的节点信息【运行失败】");
        try {
            RealtimeNodeInfo realtimeInfo = select(id);
            realtimeInfo.setStatus(RealtimeStatus.RUN_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runFail(String id, String host, String message) {
        try {
            RealtimeNodeInfo realtimeInfo = select(id, host);
            realtimeInfo.setStatus(RealtimeStatus.RUN_FAIL);
            realtimeInfo.setUpdateTime(new Date());
            realtimeInfo.setMessage(message);
            update(id, host, realtimeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String id, RealtimeNodeInfo realtimeInfo) {
        realtimeNodeMapper.insert(REALTIME_NODE_INFO_KEY + KEY_DELIMITER + id + KEY_DELIMITER + HOST_KEY, realtimeInfo);
    }

    public RealtimeNodeInfo select(String id) {
        return select(id, HOST_KEY);
    }

    public RealtimeNodeInfo select(String id, String host) {
        return realtimeNodeMapper.select(REALTIME_NODE_INFO_KEY + KEY_DELIMITER + id + KEY_DELIMITER + host);
    }

    public void update(String id, RealtimeNodeInfo realtimeInfo) {
        update(id, HOST_KEY, realtimeInfo);
    }

    public void update(String id, String host, RealtimeNodeInfo realtimeInfo) {
        realtimeNodeMapper.update(REALTIME_NODE_INFO_KEY + KEY_DELIMITER + id + KEY_DELIMITER + host, realtimeInfo);
    }

    public List<RealtimeNodeInfo> selectList(String id) {
        return realtimeNodeMapper.selectLike(REALTIME_NODE_INFO_KEY + KEY_DELIMITER + id + KEY_DELIMITER);
    }

    public void deleteList(String id) {
        for (RealtimeNodeInfo nodeInfo : selectList(id)) {
            delete(id, nodeInfo.getHost());
        }
    }

    public void delete(String id) {
        delete(id, HOST_KEY);
    }

    public void delete(String id, String host) {
        realtimeNodeMapper.delete(REALTIME_NODE_INFO_KEY + KEY_DELIMITER + id + KEY_DELIMITER + host);
    }
}
