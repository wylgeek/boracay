package com.hex.bigdata.udsp.common.controller;

import com.hex.bigdata.udsp.common.dto.ComDatasourcePropsView;
import com.hex.bigdata.udsp.common.dto.ComDatasourceView;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.util.JSONUtil;
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
import java.util.Map;

/**
 * Created by tomnic on 2017/2/20.
 */
@RequestMapping("/com/ds")
@Controller
public class ComDatasourceController extends BaseController {
    private static Logger logger = LogManager.getLogger(ComDatasourceController.class);

    @Autowired
    private ComDatasourceService comDatasourceService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(ComDatasourceView comDatasourceView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(comDatasourceView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<ComDatasource> list = null;
        try {
            list = comDatasourceService.select(comDatasourceView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(ComDatasourceView comDatasourceView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(comDatasourceView));
        List<ComDatasource> list = null;
        try {
            list = comDatasourceService.select(comDatasourceView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody ComDatasourcePropsView comDatasourcePropsView) {
        boolean status = true;
        String message = "添加成功";
        if (comDatasourcePropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(comDatasourceService.insert(comDatasourcePropsView))) {
                    status = false;
                    message = "添加失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/select/{model}/{name}"})
    @ResponseBody
    public MessageResult selectByModelAndName(@PathVariable("model") String model, @PathVariable("name") String name) {
        boolean status = true;
        String message = "查询成功！";
        ComDatasource comDatasource = null;
        try {
            comDatasource = comDatasourceService.selectByModelAndName(model, name);
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e;
        }
        return new MessageResult(status, message, comDatasource);
    }

    @RequestMapping({"/check/{model}/{name}"})
    @ResponseBody
    public MessageResult check(@PathVariable("model") String model, @PathVariable("name") String name) {
        boolean status = true;
        String message = "检查完成，名称存在！";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!comDatasourceService.checkModelAndName(model, name)) {
                    status = false;
                    message = "检查完成，名称不存在！";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult select(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        ComDatasource comDatasource = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                comDatasource = comDatasourceService.select(pkId);
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message, comDatasource);
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody ComDatasourcePropsView comDatasourcePropsView) {
        boolean status = true;
        String message = "更新成功";
        if (comDatasourcePropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {

                if (!comDatasourceService.update(comDatasourcePropsView)) {
                    status = false;
                    message = "更新失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e;
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody ComDatasource[] comDatasources) {
        boolean status = true;
        String message = "删除成功";
        if (comDatasources.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if ( !comDatasourceService.delete(comDatasources)) {
                status = false;
                message = "删除失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e;
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.info(message);
        }
        return new MessageResult(status, message);
    }


    @RequestMapping("upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile){
        boolean status = true;
        String message = "上传成功";
        //判断结尾是否为xl或者xlsx
        if (((CommonsMultipartFile)excelFile).getFileItem().getName().endsWith(".xls")
                || ((CommonsMultipartFile)excelFile).getFileItem().getName().endsWith(".xlsx")) {
            //将文件放到项目上传文件目录中
            String uploadFilePath = FileUtil.uploadFile(FileUtil
                    .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
            Map<String,String> result = comDatasourceService.uploadExcel(uploadFilePath);
            if("false".equals(result.get("status"))){
                status = false;
                message = result.get("message");
            }
        }else{
            status = false;
            message = "请上传正确格式的文件！";
        }
        return new MessageResult(status,message);
    }

    @ResponseBody
    @RequestMapping("/download")
    public String createExcel(@RequestBody ComDatasource[] comDatasources){
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = comDatasourceService.createExcel(comDatasources);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
