package com.hex.bigdata.udsp.mc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McCurrentView;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McCurrentService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by junjiem on 2017-3-1.
 */
@RequestMapping("/mc/current")
@Controller
public class McCurrentController extends BaseController {
    private static Logger logger = LogManager.getLogger(McCurrentController.class);

    @Autowired
    private McCurrentService mcCurrentNewService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(McCurrentView mcCurrentView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(mcCurrentView) + " page=" + JSONUtil.parseObj2JSON(page));
        PageListResult result = null;
        try {
            result = mcCurrentNewService.select(mcCurrentView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return result;
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(McCurrentView mcCurrentView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(mcCurrentView));
        List<McCurrent> list = null;
        try {
            list = mcCurrentNewService.select(mcCurrentView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        McCurrent mcCurrent = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                //mcCurrent = mcCurrentService.select(pkId);
                mcCurrent = mcCurrentNewService.select(pkId);
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
        return new MessageResult(status, message, mcCurrent);
    }

    @RequestMapping("/kill/{pkId}")
    @ResponseBody
    public MessageResult kill(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "杀死成功";
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                // TODO ......杀死消费任务
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
        return new MessageResult(status, message);
    }


    /**
     * 缓冲队列分页查询
     * @param mcCurrentView
     * @param page
     * @return
     */
    @RequestMapping({"/waitQueuePage"})
    @ResponseBody
    public PageListResult waitQueuePage(McCurrentView mcCurrentView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(mcCurrentView) + " page=" + JSONUtil.parseObj2JSON(page));
        PageListResult result = null;
        try {
            result = mcCurrentNewService.selectWaitQueue(mcCurrentView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return result;
    }

    /**
     * 缓冲队列单个任务查询
     * @param pkId
     * @return
     */
    @RequestMapping({"/waitQueueSelectByPkId/{pkId}"})
    @ResponseBody
    public MessageResult waitQueueSelectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        McCurrent mcCurrent = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                mcCurrent = mcCurrentNewService.selectWaitQueue(pkId);
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
        return new MessageResult(status, message, mcCurrent);
    }



}
