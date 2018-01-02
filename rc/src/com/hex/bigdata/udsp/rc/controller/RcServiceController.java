package com.hex.bigdata.udsp.rc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.service.McChartsService;
import com.hex.bigdata.udsp.rc.dto.RcServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:15:10
 */
@RequestMapping("/rc/service")
@Controller
public class RcServiceController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RcServiceController.class);

    /**
     * 服务注册管理
     */
    @Autowired
    private RcServiceService rcServiceService;

    /**
     * 分页多条件查询
     *
     * @param rcServiceView 查询参数
     * @param page          分页参数
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult queryRtsDatasources(RcServiceView rcServiceView, Page page) {
        List<RcServiceView> list = rcServiceService.select(rcServiceView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(rcServiceView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }

    /**
     * 实时流-新增服务记录
     *
     * @param rcService
     * @return
     */
    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody RcService rcService) {
        boolean status = true;
        String message = "添加成功";
        if (rcService == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(rcServiceService.insert(rcService))) {
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

    /**
     * 检查名称唯一性
     *
     * @param name 服务名称
     * @return
     */
    @RequestMapping({"/checkName/{name}"})
    @ResponseBody
    public MessageResult checekName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "检查服务名称成功";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rcServiceService.checekUniqueName(name)) {
                    status = false;
                    message = "检查完成，名称不存在！";
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
        RcService rtsMatedata = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                rtsMatedata = this.rcServiceService.select(pkId);
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
    public MessageResult update(@RequestBody RcService rcService) {
        boolean status = true;
        String message = "更新成功";
        if (rcService == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rcServiceService.update(rcService)) {
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
    public MessageResult delete(@RequestBody RcService[] rcServices) {
        boolean status = true;
        String message = "删除成功";
        if (rcServices.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if (!rcServiceService.delete(rcServices)) {
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


    @RequestMapping("/shangeStatus/{serviceStatus}")
    @ResponseBody
    public MessageResult serviceStatus(@PathVariable("serviceStatus") String serviceStatus, @RequestBody RcService[] rcServices) {
        boolean status = true;
        String message = "服务状态修改成功";
        if (rcServices.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if (!rcServiceService.statusChange(rcServices, serviceStatus)) {
                status = false;
                message = "服务状态修改失败";
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


    @RequestMapping({"/selectApps/{type}"})
    @ResponseBody
    public MessageResult selectApps(@PathVariable("type") String type) {
        boolean status = true;
        String message = "查询成功";
        List apps = null;
        if (StringUtils.isBlank(type)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                apps = this.rcServiceService.selectApps(type);
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
        return new MessageResult(status, message, apps);
    }

    @RequestMapping({"/selectServices/{serviceType}"})
    @ResponseBody
    public MessageResult selectServices(@PathVariable("serviceType") String serviceType) {
        boolean status = true;
        String message = "查询成功";
        List services = null;
        if (StringUtils.isBlank(serviceType)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                services = this.rcServiceService.selectByType(serviceType);
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
        return new MessageResult(status, message, services);
    }

    /**
     * 服务类型和服务名称初始化
     */
    @RequestMapping({"/selectAuthInfo/{pkId}"})
    @ResponseBody
    public MessageResult selectAuthInfo(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        RcService rtsMatedata = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                rtsMatedata = this.rcServiceService.selectAuthInfo(pkId);
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

    /**
     * 查找是否有关联的服务授权信息
     *
     * @param rcServices 检查的对象集合
     * @return
     */
    @RequestMapping({"/checkServiceId"})
    @ResponseBody
    public MessageResult checekServiceId(@RequestBody RcService[] rcServices) {
        boolean status = true;
        String message = "检测完成";
        if (rcServices == null || rcServices.length == 0) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                return rcServiceService.selectRcUserService(rcServices);
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

    /**
     * 检测应用类型和应用名称是否具有唯一性
     *
     * @param type
     * @param appId
     * @return
     */
    @RequestMapping({"/checkAppIdAndType/{type}/{appId}"})
    @ResponseBody
    public MessageResult checkAppIdAndType(@PathVariable("type") String type, @PathVariable("appId") String appId) {
        boolean status = true;
        String message = "检查应用类型和应用名称成功";
        if (StringUtils.isBlank(appId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!rcServiceService.checkAppUsed(type, appId)) {
                    status = false;
                    message = "检查完成，应用类型和应用名称不存在重复！";
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

    /**
     * 检查应用是否被注册且是启用状态
     *
     * @param model
     * @param applications
     * @return
     */
    @RequestMapping({"/checkAppUsed/{model}"})
    @ResponseBody
    public MessageResult checkAppUsed(@PathVariable String model, @RequestBody Map<String, String>[] applications) {
        boolean status = false;
        String message = "";
        for (Map<String, String> application : applications) {
            if (rcServiceService.checkAppUsedAndStart(model, application.get("pkId"))) {
                status = true;
                message += "名称为：【" + application.get("name") + "】";
                if ("OLQ".equals(model)) {
                    message += "的数据源已被注册且为启动状态，请停用或删除服务注册再进行编辑或删除操作！\n";
                } else if ("IM".equals(model)) {
                    message += "的模型已被注册且为启动状态，请停用或删除服务注册再进行编辑或删除操作！\n";
                } else {
                    message += "的应用已被注册且为启动状态，请停用或删除服务注册再进行编辑或删除操作！\n";
                }
                //break;
            }
        }
        logger.debug(message);
        return new MessageResult(status, message);
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
            Map<String, String> result = rcServiceService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody RcService[] rcServices) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = rcServiceService.createExcel(rcServices);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
