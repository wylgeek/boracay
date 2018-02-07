package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.EdApplicationDto;
import com.hex.bigdata.udsp.ed.dto.EdApplicationParamDto;
import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.service.EdAppRequestParamService;
import com.hex.bigdata.udsp.ed.service.EdAppResponseParamService;
import com.hex.bigdata.udsp.ed.service.EdApplicationService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by jc.zhao
 * Date:2018/1/6
 * Time:21:47
 */
@Controller
@RequestMapping("/edApp")
public class EdApplicationController {
    private static Logger logger = LoggerFactory.getLogger(EdApplicationController.class);

    @Autowired
    private EdApplicationService edApplicationService;

    @Autowired
    private EdAppRequestParamService edAppRequestParamService;

    @Autowired
    private EdAppResponseParamService edAppResponseParamService;

    @RequestMapping("/getEdApplicationInfoPage")
    @ResponseBody
    public PageListResult getEdApplicationInfoPage(EdApplicationDto edApplicationDto, Page page) {
        try {
            List<EdApplicationDto> list = edApplicationService.getEdApplicationInfoPage(edApplicationDto, page);
            return new PageListResult(list);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addEdApplicationAndParam")
    @ResponseBody
    public MessageResult addEdApplicationAndParam(@RequestBody EdApplicationParamDto edApplicationParamDto) {
        if (edApplicationParamDto == null || edApplicationParamDto.getEdApplication() == null
                || edApplicationParamDto.getEdAppRequestParam() == null || edApplicationParamDto.getEdAppResponseParam() == null) {
            return new MessageResult(false, "参数为空");
        }
        try {
            return edApplicationService.addEdApplicationAndParam(edApplicationParamDto);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return new MessageResult(false, "请求错误，请重试！");
        }
    }

    @RequestMapping("/getEdAppRequestParamByAppId/{pkId}")
    @ResponseBody
    public PageListResult getEdAppRequestParamByAppId(@PathVariable("pkId") String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return null;
        }
        try {
            List<EdAppRequestParam> edAppRequestParams = edAppRequestParamService.getEdAppRequestParamByAppId(pkId);
            return new PageListResult(edAppRequestParams);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getEdAppResponseParamByAppId/{pkId}")
    @ResponseBody
    public PageListResult getEdAppResponseParamByAppId(@PathVariable("pkId") String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return null;
        }
        try {
            List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(pkId);
            return new PageListResult(edAppResponseParams);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/selectByPrimaryKey/{pkId}")
    @ResponseBody
    public MessageResult selectByPrimaryKey(@RequestBody @PathVariable("pkId") String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return new MessageResult(false, "参数为空");
        }
        try {
            EdApplication edApplication = edApplicationService.selectByPrimaryKey(pkId);
            return new MessageResult(true, edApplication);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return new MessageResult(false, "请求错误，请重试！");
        }
    }

    @RequestMapping("/getEdApplicationByName")
    @ResponseBody
    public MessageResult getEdApplicationByName(String name) {
        if (StringUtils.isBlank(name)) {
            return new MessageResult(false, "参数为空");
        }
        try {
            EdApplication edApplication = edApplicationService.getEdApplicationByName(name);
            if (edApplication == null) {
                return new MessageResult(false);
            }
            return new MessageResult(true, edApplication);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return new MessageResult(false, "请求错误，请重试！");
        }
    }

    @RequestMapping("/updateEdApplicationAndParam")
    @ResponseBody
    public MessageResult updateEdApplicationAndParam(@RequestBody EdApplicationParamDto edApplicationParamDto) {
        if (edApplicationParamDto == null || edApplicationParamDto.getEdApplication() == null
                || edApplicationParamDto.getEdAppRequestParam() == null || edApplicationParamDto.getEdAppResponseParam() == null) {
            return new MessageResult(false, "参数为空");
        }
        try {
            return edApplicationService.updateEdApplicationAndParam(edApplicationParamDto);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return new MessageResult(false, "请求错误，请重试！");
        }
    }

    @RequestMapping("/deleteEdApplicationAndParam")
    @ResponseBody
    public MessageResult deleteEdApplicationAndParam(@RequestBody EdApplication[] edApplications) {
        if (!(edApplications.length > 0)) {
            return new MessageResult(false, "参数为空");
        }

        try {
            return edApplicationService.deleteEdApplicationAndParam(edApplications);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return new MessageResult(false, "请求错误，请重试！");
        }
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public PageListResult selectAll() {
        try {
            List list = edApplicationService.selectAll();
            return new PageListResult(list);
        } catch (Exception e) {
            logger.info("请求错误！");
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile) {
        boolean status = true;
        String message = "上传成功";

        if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xlsx")) {
            return new MessageResult(false, "目前仅支持xls类型文件解析,请修改文件类型");
        }
        //判断结尾是否为xl或者xlsx
        if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xls")) {
            //将文件放到项目上传文件目录中
            String uploadFilePath = FileUtil.uploadFile(FileUtil
                    .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
            Map<String, String> result = edApplicationService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody EdApplication[] edApplications) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = edApplicationService.createExcel(edApplications);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}

