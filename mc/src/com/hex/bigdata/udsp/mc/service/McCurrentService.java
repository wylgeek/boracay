package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.mc.dao.McCurrentMapper;
import com.hex.bigdata.udsp.mc.dto.McCurrentView;
import com.hex.bigdata.udsp.mc.model.McCurrent;
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
 * Created by junjiem on 2017-2-23.
 */
@Service
public class McCurrentService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(McCurrentService.class);

    /**
     * 并发信息的KEY
     */
    private static final String MC_CURRENT_KEY = "CURRENT";
    /**
     *
     */
    private static final String MC_WAIT_KEY = "WAIT";

    /**
     * 本机IP
     */
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();

    @Autowired
    private McCurrentMapper mcCurrentMapper;

    @Transactional
    public String insert(McCurrent mcCurrent) {
        mcCurrent.setHost(HOST_KEY);
        if (mcCurrentMapper.insert(MC_CURRENT_KEY + ":" + HOST_KEY + ":" + mcCurrent.getPkId(), mcCurrent)) {
            return mcCurrent.getPkId();
        }
        return "";
    }

    @Transactional
    public String insertWaitQueue(McCurrent mcCurrent) {
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
        return mcCurrentMapper.delete(MC_CURRENT_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    @Transactional
    public boolean deleteWaitQueue(String pkId) {
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
        return mcCurrentMapper.delete(MC_CURRENT_KEY + ":" + hostKey + ":" + pkId);
    }

    @Transactional
    public boolean deleteWaitQueue(String hostKey, String pkId) {
        return mcCurrentMapper.delete(MC_WAIT_KEY + ":" + hostKey + ":" + pkId);
    }

    public McCurrent select(String pkId) {
        return mcCurrentMapper.select(MC_CURRENT_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    public McCurrent selectWaitQueue(String pkId) {
        return mcCurrentMapper.select(MC_WAIT_KEY + ":" + HOST_KEY + ":" + pkId);
    }

    public List<McCurrent> select(McCurrentView mcCurrentView) {
        return this.selectJob(mcCurrentView, MC_CURRENT_KEY);
    }

    /**
     * 查询执行队列
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult select(McCurrentView mcCurrentView, Page page) {
        return this.select(mcCurrentView, page, MC_CURRENT_KEY);
    }

    /**
     * 查询缓存队列
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult selectWaitQueue(McCurrentView mcCurrentView, Page page) {
        return this.select(mcCurrentView, page, MC_WAIT_KEY);
    }

    /**
     * 分页查询
     *
     * @param mcCurrentView
     * @param page
     * @return
     */
    public PageListResult select(McCurrentView mcCurrentView, Page page, String selectType) {
        List<McCurrent> mcCurrentList = selectJob(mcCurrentView, selectType);
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        if (befNum < 0) befNum = 0;
        int count = 0;
        List<McCurrent> list = new ArrayList<>();
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
    public List<McCurrent> selectJob(McCurrentView mcCurrentView, String selectType) {
        String serviceName = mcCurrentView.getServiceName();
        String userName = mcCurrentView.getUserName();
        String requestContent = mcCurrentView.getRequestContent();
        String pid = mcCurrentView.getPid();
        String startTimeStart = mcCurrentView.getStartTimeStart();
        String startTimeEnd = mcCurrentView.getStartTimeEnd();
        String syncType = mcCurrentView.getSyncType();
        String host = mcCurrentView.getHost();
        //解决ConcurrentModificationException
        List<McCurrent> mcCurrentList = new CopyOnWriteArrayList<McCurrent>();

        List<McCurrent> tempList = this.selectCacheLike(selectType + ":");
        if (tempList == null || tempList.size() == 0) {
            return null;
        }
        mcCurrentList.addAll(tempList);

        //过滤
        for (McCurrent mcCurrent : mcCurrentList) {
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

    public List<McCurrent> selectCacheLike(String key) {
        return mcCurrentMapper.selectLike(key);
    }

    /**
     * 根据hostKey地址查询该机器所执行的任务记录
     *
     * @param hostKey
     * @return
     */
    public List<McCurrent> getMcCurrentByKey(String hostKey) {
        return mcCurrentMapper.selectLike(MC_CURRENT_KEY + ":" + hostKey + ":");
    }
}
