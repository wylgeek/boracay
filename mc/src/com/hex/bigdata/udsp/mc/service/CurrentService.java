package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.mc.dao.CurrentMapper;
import com.hex.bigdata.udsp.mc.dto.CurrentView;
import com.hex.bigdata.udsp.mc.model.Current;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 运行和等待信息的服务
 */
@Service
public class CurrentService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(CurrentService.class);

    /**
     * 运行信息的KEY
     */
    private static final String MC_RUN_KEY = "RUN";

    /**
     * 等待信息的KEY
     */
    private static final String MC_WAIT_KEY = "WAIT";

    /**
     * 本机IP
     */
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private CurrentMapper mcCurrentMapper;

    @Transactional
    public String insert(Current mcCurrent) {
        mcCurrent.setHost(HOST_KEY);
        if (mcCurrentMapper.insert(MC_RUN_KEY + ":" + HOST_KEY + ":" + mcCurrent.getPkId(), mcCurrent)) {
            return mcCurrent.getPkId();
        }
        return "";
    }

    @Transactional
    public String insertWait(Current mcCurrent) {
        mcCurrent.setHost(HOST_KEY);
        if (mcCurrentMapper.insert(MC_WAIT_KEY + ":" + HOST_KEY + ":" + mcCurrent.getPkId(), mcCurrent)) {
            return mcCurrent.getPkId();
        }
        return "";
    }

    /**
     * 删除本机器的并发记录
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        return mcCurrentMapper.delete(MC_RUN_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    @Transactional
    public boolean deleteWait(String pkId) {
        return mcCurrentMapper.delete(MC_WAIT_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    /**
     * 删除指定机器的并发记录
     *
     * @param hostKey
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String hostKey, String pkId) {
        return mcCurrentMapper.delete(MC_RUN_KEY + ":" + hostKey + ":" + pkId);
    }

    @Transactional
    public boolean deleteWait(String hostKey, String pkId) {
        return mcCurrentMapper.delete(MC_WAIT_KEY + ":" + hostKey + ":" + pkId);
    }

    public Current select(String pkId) {
        return mcCurrentMapper.select(MC_RUN_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    public Current selectWait(String pkId) {
        return mcCurrentMapper.select(MC_WAIT_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    public List<Current> select(CurrentView mcCurrentView) {
        return this.selectJob(mcCurrentView, MC_RUN_KEY);
    }

    /**
     * 查询执行队列
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult select(CurrentView mcCurrentView, Page page) {
        return this.select(mcCurrentView, page, MC_RUN_KEY);
    }

    /**
     * 查询缓存队列
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult selectWait(CurrentView mcCurrentView, Page page) {
        return this.select(mcCurrentView, page, MC_WAIT_KEY);
    }

    /**
     * 分页查询
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult select(CurrentView mcCurrentView, Page page, String selectType) {
        List<Current> mcCurrentList = selectJob(mcCurrentView, selectType);
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        if (befNum < 0) befNum = 0;
        int count = 0;
        List<Current> list = new ArrayList<>();
        if (mcCurrentList == null || mcCurrentList.size() == 0) {
            return null;
        }
        for (int i = 0; i < mcCurrentList.size(); i++) {
            if (i >= befNum) {
                list.add(mcCurrentList.get(i));
                count++;
                if (count >= pageSize) {
                    break;
                }
            }
        }
        PageListResult pageListResult = new PageListResult(list);
        pageListResult.setTotal(mcCurrentList.size());
        return pageListResult;
    }

    /**
     * 参选逻辑
     *
     * @param mcCurrentView
     * @return
     */
    public List<Current> selectJob(CurrentView mcCurrentView, String selectType) {
        String serviceName = mcCurrentView.getServiceName();
        String userName = mcCurrentView.getUserName();
        String requestContent = mcCurrentView.getRequestContent();
        String pid = mcCurrentView.getPid();
        String startTimeStart = mcCurrentView.getStartTimeStart();
        String startTimeEnd = mcCurrentView.getStartTimeEnd();
        String syncType = mcCurrentView.getSyncType();
        String host = mcCurrentView.getHost();
        //解决ConcurrentModificationException
        List<Current> mcCurrentList = new CopyOnWriteArrayList<Current>();

        List<Current> tempList = this.selectCacheLike(selectType + ":");
        if (tempList == null || tempList.size() == 0) {
            return null;
        }
        mcCurrentList.addAll(tempList);

        //过滤
        for (Current mcCurrent : mcCurrentList) {
            if (StringUtils.isNotBlank(serviceName) && !mcCurrent.getServiceName().contains(serviceName)) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(userName) && !mcCurrent.getUserName().contains(userName)) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(requestContent) && !mcCurrent.getRequestContent().contains(requestContent)) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(host) && !mcCurrent.getHost().equals(host)) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(pid) && !mcCurrent.getPid().equals(pid)) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(startTimeStart) && mcCurrent.getStartTime().compareTo(startTimeStart) < 0) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(startTimeEnd) && mcCurrent.getStartTime().compareTo(startTimeEnd) > 0) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
            if (StringUtils.isNotBlank(syncType) && !syncType.equalsIgnoreCase(mcCurrent.getSyncType())) {
                mcCurrentList.remove(mcCurrent);
                continue;
            }
        }
        return mcCurrentList;
    }

    public List<Current> selectCacheLike(String key) {
        return mcCurrentMapper.selectLike(key);
    }

    /**
     * 根据hostKey地址查询该机器所执行的任务记录
     *
     * @param hostKey
     * @return
     */
    public List<Current> getRunByHost(String hostKey) {
        return mcCurrentMapper.selectLike(MC_RUN_KEY + ":" + hostKey + ":");
    }
}
