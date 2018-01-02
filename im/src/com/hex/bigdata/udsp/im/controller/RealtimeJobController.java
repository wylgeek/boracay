package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.dto.RealtimeNodeInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoDto;
import com.hex.bigdata.udsp.im.dto.RealtimeTotalInfoView;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelFilterCol;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.bigdata.udsp.im.service.RealtimeJobService;
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

import java.util.List;

/**
 * Created by JunjieM on 2017-9-22.
 */
@RequestMapping("/im/job/realtime")
@Controller
public class RealtimeJobController {
    private static Logger logger = LogManager.getLogger(RealtimeJobController.class);
    @Autowired
    private RealtimeJobService realtimeJobService;

    @Autowired
    private ImModelService imModelService;

    /**
     * 启动
     *
     * @param pkId 模型的主键
     * @return
     */
    @Deprecated
    @RequestMapping(value = "start/{pkId}")
    @ResponseBody
    public MessageResult start(@PathVariable("pkId") String pkId, @RequestBody ModelFilterCol[] modelFilterCols) {
        boolean status = true;
        String message = "实时作业启动成功！";
        try {
            Model model = imModelService.getModel(pkId, modelFilterCols);
            realtimeJobService.start(model);
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
     * 停止
     *
     * @param id 实时作业的唯一键
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "stop/{id}")
    public MessageResult stop(@PathVariable("id") String id) {
        boolean status = true;
        String message = "实时作业停止成功！";
        try {
            realtimeJobService.stop(id);
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
     * @param realtimeTotalInfoView
     * @param page
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(RealtimeTotalInfoView realtimeTotalInfoView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(realtimeTotalInfoView) + " page=" + JSONUtil.parseObj2JSON(page));
        PageListResult result = null;
        try {
            result = realtimeJobService.selectPage(realtimeTotalInfoView, page);
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
        logger.debug("select id=" + id);
        boolean status = true;
        String message = "查询成功";
        RealtimeTotalInfoDto dto = null;
        if (StringUtils.isBlank(id)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                dto = realtimeJobService.selectDto(id);
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

    /**
     * 查询子列表
     *
     * @param id
     * @return
     */
    @RequestMapping({"/select/nodes/{id}"})
    @ResponseBody
    public MessageResult selectNodes(@PathVariable("id") String id) {
        boolean status = true;
        String message = "查询成功";
        List<RealtimeNodeInfoDto> dtos = null;
        if (StringUtils.isBlank(id)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                dtos = realtimeJobService.selectNodesDto(id);
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
        return new MessageResult(status, message, dtos);
    }
}
