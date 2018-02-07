package com.hex.bigdata.udsp.rc.controller;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.service.McChartsService;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceBatchDto;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceDto;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.rc.service.RcUserServiceService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.*;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户服务关系
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:15:11
 */

@RequestMapping("/rc/userService/")
@Controller
public class RcUserServiceController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RcServiceController.class);
    /**
     * 用户服务关系-用户服务关系管理服务
     */
    @Autowired
    private RcUserServiceService rcUserServiceService;

    /**
     * 分页多条件查询
     *
     * @param rcServiceView 查询参数
     * @param page          分页参数
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult queryRtsDatasources(RcUserServiceView rcServiceView, Page page) {
        List<RcUserServiceView> list = rcUserServiceService.select(rcServiceView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(rcServiceView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }

    /**
     * 用户服务关系-新增服务记录
     *
     * @param rcUserServiceDto
     * @return
     */
    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody RcUserServiceDto rcUserServiceDto) {
        boolean status = true;
        String message = "添加成功";
        if (rcUserServiceDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(rcUserServiceService.insert(rcUserServiceDto))) {
                    status = false;
                    message = "添加失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/insertBatch"})
    @ResponseBody
    public MessageResult insertBatch(@RequestBody RcUserServiceBatchDto rcUserServiceBatchDto) {
        boolean status = true;
        String message = "添加成功";
        if (rcUserServiceBatchDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rcUserServiceService.insertBatch(rcUserServiceBatchDto)) {
                    status = false;
                    message = "添加失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }


    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        RcUserServiceView rtsMatedata = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                rtsMatedata = this.rcUserServiceService.selectFullResultMap(pkId);
            } catch (Exception e) {
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, rtsMatedata);
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody RcUserServiceDto rcUserServiceDto) {
        boolean status = true;
        String message = "更新成功";
        if (rcUserServiceDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rcUserServiceService.update(rcUserServiceDto)) {
                    status = false;
                    message = "更新失败";
                }
            } catch (Exception e) {
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody RcUserService[] rcServices) {
        boolean status = true;
        String message = "删除成功";
        if (rcServices.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if (!rcUserServiceService.delete(rcServices)) {
                status = false;
                message = "删除失败";
            }
        } catch (Exception e) {
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/checkExists/{userId}/{serviceId}")
    @ResponseBody
    public MessageResult checkExists(@PathVariable("userId") String userId, @PathVariable("serviceId") String serviceId) {
        boolean status = true;
        String message = "删除成功";
        boolean flg = false;
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(serviceId)) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            message = "删除失败";
            flg = rcUserServiceService.checkExists(userId, serviceId);
        } catch (Exception e) {
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, flg);
    }

    @RequestMapping("/checkExistsBatch/{userIds}/{serviceIds}")
    @ResponseBody
    public MessageResult checkExistsBatch(@PathVariable("userIds") String userIds, @PathVariable("serviceIds") String serviceIds) {
        boolean status = true;
        String message = "检查成功";
        boolean flg = false;
        if (StringUtils.isBlank(userIds) || StringUtils.isBlank(serviceIds)) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            message = "检查失败";
            flg = rcUserServiceService.checkExistsBatch(userIds, serviceIds);
        } catch (Exception e) {
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, flg);
    }

    /**
     * 通过条件查询与用户无关的信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @param page
     * @return
     */
    @RequestMapping("/selectNotRelationUsers")
    @ResponseBody
    public MessageResult selectUsers(RcUserServiceView rcUserServiceView, Page page) {
        boolean status = true;
        String message = "查询成功";
        List<GFUser> users = new ArrayList<>();
        if (rcUserServiceView == null) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            users = rcUserServiceService.selectNotRelationUsers(rcUserServiceView, page);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, users);
    }


    /**
     * 通过条件查询与服务有关的用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @return
     */
    @RequestMapping("/selectRelationUsers")
    @ResponseBody
    public MessageResult selectRelationUsers(@RequestBody RcUserServiceView rcUserServiceView) {
        boolean status = true;
        String message = "查询成功";
        List<GFUser> users = new ArrayList<>();
        if (rcUserServiceView == null) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            users = rcUserServiceService.selectRelationUsers(rcUserServiceView);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, users);
    }

    /**
     * 通过条件查询与服务有关的用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @return
     */
    @RequestMapping("/selectUsersByServiceName")
    @ResponseBody
    public MessageResult selectUsersByServiceName(@RequestBody RcUserServiceView rcUserServiceView) {
        boolean status = true;
        String message = "查询成功";
        List<GFUser> users = new ArrayList<>();
        if (rcUserServiceView == null) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            users = rcUserServiceService.selectUsersByServiceName(rcUserServiceView);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, users);
    }

    @RequestMapping("/selectServicesByUserName")
    @ResponseBody
    public MessageResult selectServicesByUserName(@RequestBody RcUserServiceView rcUserServiceView) {
        boolean status = true;
        String message = "查询成功";
        List<RcUserServiceView> users = new ArrayList<>();
        if (rcUserServiceView == null) {
            status = false;
            message = "请求参数为空";
        }
        try {
            status = true;
            users = rcUserServiceService.selectServicesByUserId(rcUserServiceView);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, users);
    }


    /**
     * 检查ip段表达式的合法性
     * 支持以下几种模式：
     * 1、正常的ip，如10.1.97.1
     * 2、以星号*代替0-255之间的任意数字，如10.1.97.*
     * 3、如10.1.97.[10-30]、10.1.97.[1-5,6-20]
     *
     * @param rcUserServiceView
     * @return
     */
    @RequestMapping("/checkModel")
    @ResponseBody
    public MessageResult checkModel(@RequestBody RcUserServiceView rcUserServiceView) {
        boolean status = true;
        boolean result = true;
        String message = "检查成功";
        if (rcUserServiceView == null) {
            status = false;
            message = "请求参数为空";
        }
        try {
            result = rcUserServiceService.checkModels(rcUserServiceView.getIpSection());
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e.getMessage();
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message, result);
    }

    @RequestMapping("upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile) {
        boolean status = true;
        String message = "上传成功";

        //判断结尾是否为xl或者xlsx
        if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xls")
                || ((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xlsx")) {
            //将文件放到项目上传文件目录中
            String uploadFilePath = FileUtil.uploadFile(FileUtil
                    .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
            Map<String, String> result = rcUserServiceService.uploadExcel(uploadFilePath);
            if ("false".equals(result.get("status"))) {
                status = false;
                message = result.get("message");
            }
        } else {
            status = false;
            message = "请上传正确格式的文件！";
        }
        return new MessageResult(status, message);
    }

    @ResponseBody
    @RequestMapping("/download")
    public String createExcel(@RequestBody RcUserService[] rcServices) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = rcUserServiceService.createExcel(rcServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }


    @ResponseBody
    @RequestMapping("/downStreamInfoDownload")
    public String downStreamInfoDownload(@RequestBody RcUserService[] rcServices) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = rcUserServiceService.downStreamInfoDownload(rcServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
