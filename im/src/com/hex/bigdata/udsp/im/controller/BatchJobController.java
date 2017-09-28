package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.dto.BatchInfoDto;
import com.hex.bigdata.udsp.im.dto.BatchInfoView;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JunjieM on 2017-9-22.
 */
@RequestMapping("/im/job/batch")
@Controller
public class BatchJobController {
    private static Logger logger = LogManager.getLogger(BatchJobController.class);

    @Autowired
    private BatchJobService batchJobService;

    @Autowired
    private ImModelService imModelService;

    /**
     * 开始构建
     *
     * @param pkId 模型的主键
     * @return
     */
    @RequestMapping(value = "start/{pkId}")
    @ResponseBody
    public MessageResult start(@PathVariable("pkId") String pkId,@RequestBody ModelFilterCol[] modelFilterCols) {
        boolean status = true;
        String message = "批量作业构建成功！";
        try {
            Model model = imModelService.getModel(pkId,modelFilterCols);
            batchJobService.start(model);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    /**
     * 停止构建
     *
     * @param id 批量作业的唯一键
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "stop/{id}")
    public MessageResult stop(@PathVariable("id") String id) {
        boolean status = true;
        String message = "批量作业停止成功！";
        try {
            batchJobService.stop(id);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    /**
     * 分页列表
     *
     * @param batchInfoView
     * @param page
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(BatchInfoView batchInfoView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(batchInfoView) + " page=" + JSONUtil.parseObj2JSON(page));
        PageListResult result = null;
        try {
            result = batchJobService.selectPage(batchInfoView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return result;
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    @RequestMapping({"/select/{id}"})
    @ResponseBody
    public MessageResult selectById(@PathVariable("id") String id) {
        boolean status = true;
        String message = "查询成功";
        BatchInfoDto dto = null;
        if (StringUtils.isBlank(id)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                dto = batchJobService.selectDto(id);
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message, dto);
    }
}
