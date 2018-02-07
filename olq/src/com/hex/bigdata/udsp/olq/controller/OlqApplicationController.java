package com.hex.bigdata.udsp.olq.controller;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.olq.dto.OlqApplicationDto;
import com.hex.bigdata.udsp.olq.dto.OlqApplicationView;
import com.hex.bigdata.udsp.olq.model.OlqApplication;
import com.hex.bigdata.udsp.olq.service.OlqApplicationService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
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

import java.util.List;

/**
 * 联机查询应用管理控制器
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/26
 * TIME:10:49
 */
@RequestMapping("/olq/app")
@Controller
public class OlqApplicationController extends BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OlqApplicationController.class);

    /**
     * 联机查询-联机查询应用管理服务
     */
    @Autowired
    private OlqApplicationService olqApplicationService;

    /**
     * 分页多条件查询
     *
     * @param olqApplicationView 查询参数
     * @param page               分页参数
     * @return
     */
    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult queryRtsDatasources(OlqApplicationView olqApplicationView, Page page) {
        List<OlqApplicationView> list = olqApplicationService.select(olqApplicationView, page);
        logger.debug("selectPage search=" + JSONUtil.parseObj2JSON(olqApplicationView) + " page=" + JSONUtil.parseObj2JSON(page));
        return new PageListResult(list, page);
    }


    /**
     * 联机查询-新增联机查询记录
     *
     * @param olqApplicationDto
     * @return
     */
    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody OlqApplicationDto olqApplicationDto) {
        boolean status = true;
        String message = "添加成功";
        if (olqApplicationDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(olqApplicationService.insert(olqApplicationDto))) {
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
        String message = "检查成功";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!olqApplicationService.checekUniqueName(name)) {
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

    /**
     * 根据主键查询应用
     *
     * @param pkId
     * @return
     */
    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        OlqApplicationDto olqApplicationDto = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                olqApplicationDto = this.olqApplicationService.selectFullAppInfo(pkId);
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
        return new MessageResult(status, message, olqApplicationDto);
    }

    /**
     * 批量更新联机查询应用
     *
     * @param olqApplicationDto
     * @return
     */
    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody OlqApplicationDto olqApplicationDto) {
        boolean status = true;
        String message = "更新成功";
        if (olqApplicationDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!olqApplicationService.update(olqApplicationDto)) {
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

    /**
     * 批量删除联机查询应用
     *
     * @param olqApplications
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody OlqApplication[] olqApplications) {
        boolean status = true;
        String message = "删除成功";
        if (olqApplications.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            boolean result = olqApplicationService.delete(olqApplications);
            if (result) {
                message = "批量删除成功";
            } else {
                message = "批量删除失败";
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

    /**
     * 查询联机查询应用列表信息
     *
     * @param
     * @return
     */
    @RequestMapping({"/selectAll"})
    @ResponseBody
    public PageListResult select() {
        List<OlqApplication> list = null;
        try {
            list = olqApplicationService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    /**
     * 根据服务名称查找服务信息
     *
     * @param name
     * @return
     */
    @RequestMapping({"/selectByName/{name}"})
    @ResponseBody
    public MessageResult selectByName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "查询成功";
        OlqApplicationDto olqApplicationDto = null;
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                olqApplicationDto = this.olqApplicationService.selectFullInfoByName(name);
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
        return new MessageResult(status, message, olqApplicationDto);

    }

    /**
     * 上传Excel配置文件
     *
     * @param excelFile
     * @return
     */
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
            MessageResult result = olqApplicationService.uploadExcel(uploadFilePath);
            if (!result.isStatus()) {
                return result;
            }
        } else {
            status = false;
            message = "请上传正确格式的文件！";
        }
        return new MessageResult(status, message);
    }

    /**
     * 下载配置Excel文件
     *
     * @param olqApplications
     * @return
     */
    @ResponseBody
    @RequestMapping("/download")
    public String downloadExcel(@RequestBody OlqApplication[] olqApplications) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = olqApplicationService.downloadExcel(olqApplications);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 获取OLQ数据源
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/dslist")
    public List<ComDatasource> selectOlqDataSource() {
        return this.olqApplicationService.selectOlqDataSource();
    }

    /**
     * 解析并获取参数集合
     *
     * @param sql
     * @return
     */
    @ResponseBody
    @RequestMapping("/parseParams")
    public MessageResult parseParams(@RequestBody String sql) {
        return this.olqApplicationService.parseParams(sql);
    }

}
