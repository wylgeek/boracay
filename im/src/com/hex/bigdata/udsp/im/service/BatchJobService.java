package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.im.constant.BatchStatus;
import com.hex.bigdata.udsp.im.constant.DatasourceType;
import com.hex.bigdata.udsp.im.dao.BatchMapper;
import com.hex.bigdata.udsp.im.dto.BatchInfoDto;
import com.hex.bigdata.udsp.im.dto.BatchInfoView;
import com.hex.bigdata.udsp.im.model.BatchInfo;
import com.hex.bigdata.udsp.im.provider.impl.util.HiveJdbcUtil;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import httl.util.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.hive.jdbc.HiveStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by JunjieM on 2017-9-21.
 */
@Service
public class BatchJobService {
    private static Logger logger = LogManager.getLogger(BatchJobService.class);
    private static final String BATCH_INFO_KEY = "BATCH";
    private static final String KEY_DELIMITER = ":";
    private static final String HOST_KEY = UdspCommonUtil.getLocalIpFromInetAddress();
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    @Autowired
    private BatchMapper batchMapper;
    @Autowired
    private ImProviderService imProviderService;

    /**
     * 启动
     */
    public void start(Model model) throws Exception {
        String id = UdspCommonUtil.getConsumeId(model.getId());
        String tDsType = model.getTargetMetadata().getDatasource().getType();
        if (DatasourceType.SOLR_HBASE.getValue().equals(tDsType)) {
            String hbaseId = "HBASE_" + id;
            String solrId = "SOLR_" + id;
            readyBuild(hbaseId, model);
            readyBuild(solrId, model);
            try {
                imProviderService.buildBatch(id, model);
                buildSuccess(hbaseId);
                buildSuccess(solrId);
            } catch (Exception e) {
                buildFail(hbaseId, e.getMessage());
                buildFail(solrId, e.getMessage());
                throw new Exception(e);
            }
        } else {
            readyBuild(id, model);
            try {
                imProviderService.buildBatch(id, model);
                buildSuccess(id);
            } catch (Exception e) {
                buildFail(id, e.getMessage());
                throw new Exception(e);
            }
        }
    }

    /**
     * 停止
     *
     * @param id
     * @throws Exception
     */
    public void stop(String id) throws Exception {
        BatchInfo batchInfo = select(id);
        if (!HOST_KEY.equals(batchInfo.getHost())) {
            throw new Exception("请在运行作业的主机上停止该批量作业");
        }
        HiveStatement stmt = getHiveStatement(id);
        if (stmt == null || stmt.isClosed()) {
            throw new Exception("作业不存在或已经完成");
        }
        try {
            stmt.cancel();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("停止作业失败");
        }
    }

    public HiveStatement getHiveStatement(String id) {
        return HiveJdbcUtil.getHiveStatement(id);
    }

    /**
     * 准备构建
     */
    public void readyBuild(String id, Model model) {
        logger.debug("添加批量作业的信息【准备构建】");
        try {
            BatchInfo batchInfo = new BatchInfo();
            batchInfo.setId(id);
            batchInfo.setStatus(BatchStatus.READY_BUILD);
            batchInfo.setHost(HOST_KEY);
            Date nowDate = new Date();
            List<ModelFilterCol> modelFilterCols = model.getModelFilterCols();
            if (modelFilterCols != null) batchInfo.setRequestContent(JSONUtil.parseList2JSON(modelFilterCols));
            batchInfo.setStartTime(nowDate);
            batchInfo.setUpdateTime(nowDate);
            batchInfo.setModelId(model.getId());
            batchInfo.setModelName(model.getName());
            insert(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 正在构建
     */
    public void building(String id, int percent) {
        logger.debug("更新批量作业的信息【正在构建】");
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILDING);
            batchInfo.setUpdateTime(new Date());
            batchInfo.setPercent(percent);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建成功
     *
     * @param id
     */
    public void buildSuccess(String id) {
        logger.debug("更新批量作业的信息【构建成功】");
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILD_SUCCESS);
            Date nowDate = new Date();
            batchInfo.setUpdateTime(nowDate);
            batchInfo.setEndTime(nowDate);
            batchInfo.setPercent(100);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建失败
     *
     * @param id
     */
    public void buildFail(String id, String message) {
        logger.debug("更新批量作业的信息【构建失败】");
        try {
            BatchInfo batchInfo = select(id);
            batchInfo.setStatus(BatchStatus.BUILD_FAIL);
            Date nowDate = new Date();
            batchInfo.setUpdateTime(nowDate);
            batchInfo.setEndTime(nowDate);
            batchInfo.setMessage(message);
            update(id, batchInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空过时的批量作业信息
     */
    public void cleanOutmodedBatch() {
        List<BatchInfo> batchInfos = selectList();
        for (BatchInfo batchInfo : batchInfos) {
            String id = batchInfo.getId();
            BatchStatus status = batchInfo.getStatus();
            String host = batchInfo.getHost();
            if ((BatchStatus.BUILD_SUCCESS == status || BatchStatus.BUILD_FAIL == status)
                    && HOST_KEY.equals(host)) {
                logger.debug("删除失败或成功且过时的批量作业信息,ID:" + id);
                delete(id);
            }
        }
    }

    /**
     * 分页查询
     *
     * @param batchInfoView
     * @param page
     * @return
     */
    public PageListResult selectPage(BatchInfoView batchInfoView, Page page) {
        List<BatchInfoDto> infos = selectAll(batchInfoView);
        int pageIndex = page.getPageIndex();
        int pageSize = page.getPageSize();
        int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数
        if (befNum < 0) befNum = 0;
        int count = 0;
        List<BatchInfoDto> list = new ArrayList<>();
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
     * @param batchInfoView
     * @return
     */
    public List<BatchInfoDto> selectAll(BatchInfoView batchInfoView) {
        String modelName = batchInfoView.getModelName();
        String message = batchInfoView.getMessage();
        String host = batchInfoView.getHost();
        String status = batchInfoView.getStatus();
        String startTimeStart = batchInfoView.getStartTimeStart();
        String startTimeEnd = batchInfoView.getStartTimeEnd();
        String endTimeStart = batchInfoView.getEndTimeStart();
        String endTimeEnd = batchInfoView.getEndTimeEnd();
        String updateTimeStart = batchInfoView.getUpdateTimeStart();
        String updateTimeEnd = batchInfoView.getUpdateTimeEnd();

        List<BatchInfoDto> list = new ArrayList<>();
        List<BatchInfo> infos = selectList();
        if (infos == null || infos.size() == 0) {
            return null;
        }
        for (BatchInfo info : infos) {
            list.add(infoToDto(info));
        }

        // 判断查询条件，过滤数据
        List<BatchInfoDto> batchInfoList = new ArrayList<>();
        for (BatchInfoDto dto : list) {
            if (StringUtils.isNotBlank(modelName) && !dto.getModelName().contains(modelName)) {
                continue;
            }
            // 查询时，将原数据为空的先去除，以下字段也类似
            if (StringUtils.isNotBlank(message) && StringUtils.isBlank(dto.getMessage())) {
                continue;
            }
            if (StringUtils.isNotBlank(message) && !dto.getMessage().contains(message)) {
                continue;
            }
            if (StringUtils.isNotBlank(status) && !dto.getStatus().equals(status)) {
                continue;
            }
            if (StringUtils.isNotBlank(host) && !dto.getHost().equals(host)) {
                continue;
            }
            if (StringUtils.isNotBlank(startTimeStart) && dto.getStartTime().compareTo(startTimeStart) < 0) {
                continue;
            }
            if (StringUtils.isNotBlank(startTimeEnd) && dto.getStartTime().compareTo(startTimeEnd) > 0) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeStart) && StringUtils.isBlank(dto.getEndTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeStart) && dto.getEndTime().compareTo(endTimeStart) < 0) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeEnd) && StringUtils.isBlank(dto.getEndTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(endTimeEnd) && dto.getEndTime().compareTo(endTimeEnd) > 0) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeStart) && StringUtils.isBlank(dto.getUpdateTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeStart) && dto.getUpdateTime().compareTo(updateTimeStart) < 0) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeEnd) && StringUtils.isBlank(dto.getUpdateTime())) {
                continue;
            }
            if (StringUtils.isNotBlank(updateTimeEnd) && dto.getUpdateTime().compareTo(updateTimeEnd) > 0) {
                continue;
            }
            batchInfoList.add(dto);
        }

        // 按照开始的时间倒序
        Collections.sort(batchInfoList, new Comparator<BatchInfoDto>() {
            @Override
            public int compare(BatchInfoDto o1, BatchInfoDto o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return batchInfoList;
    }

    /**
     * 通过ID查询
     *
     * @param id
     * @return
     */
    public BatchInfoDto selectDto(String id) {
        return infoToDto(select(id));
    }

    private BatchInfoDto infoToDto(BatchInfo info) {
        BatchInfoDto dto = new BatchInfoDto();
        dto.setId(info.getId());
        dto.setStatus(info.getStatus().getValue());
        dto.setHost(info.getHost());
        dto.setMessage(info.getMessage());
        dto.setPercent(info.getPercent());
        dto.setModelName(info.getModelName());
        dto.setModelId(info.getModelId());
        Date startTime = info.getStartTime();
        dto.setRequestContent(info.getRequestContent());
        if (startTime != null) dto.setStartTime(format.format(startTime));
        Date endTime = info.getEndTime();
        if (endTime != null) dto.setEndTime(format.format(endTime));
        Date updateTime = info.getUpdateTime();
        if (updateTime != null) dto.setUpdateTime(format.format(updateTime));
        return dto;
    }

    public BatchInfo select(String id) {
        return batchMapper.select(BATCH_INFO_KEY + KEY_DELIMITER + id);
    }

    public List<BatchInfo> selectList() {
        return batchMapper.selectLike(BATCH_INFO_KEY + KEY_DELIMITER);
    }

    public void delete(String id) {
        batchMapper.delete(BATCH_INFO_KEY + KEY_DELIMITER + id);
    }

    public void insert(String id, BatchInfo batchInfo) {
        batchMapper.insert(BATCH_INFO_KEY + KEY_DELIMITER + id, batchInfo);
    }

    public void update(String id, BatchInfo batchInfo) {
        batchMapper.update(BATCH_INFO_KEY + KEY_DELIMITER + id, batchInfo);
    }

}
