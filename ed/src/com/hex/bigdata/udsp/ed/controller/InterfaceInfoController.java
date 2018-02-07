package com.hex.bigdata.udsp.ed.controller;

import com.alibaba.fastjson.JSON;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoParamDto;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.ed.service.InterfaceInfoService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:49
 */
@Controller
@RequestMapping("/interfaceInfo")
public class InterfaceInfoController {
    private static Logger logger = LoggerFactory.getLogger(InterfaceInfoController.class);

    @Autowired
    private InterfaceInfoService interfaceInfoService;

    @RequestMapping("/getInterfaceInfoByPkId")
    @ResponseBody
    public MessageResult getInterfaceInfoByPkId(String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByPkId(pkId);
            return new MessageResult(true, interfaceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/getInterfaceInfoList")
    @ResponseBody
    public PageListResult getInterfaceInfoList(InterfaceInfoDto interfaceInfoDto, Page page) {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList(interfaceInfoDto, page);
            return new PageListResult(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addInterfaceInfo")
    @ResponseBody
    public MessageResult addInterfaceInfo(@RequestBody InterfaceInfoParamDto interfaceInfoParamDto) {
        if (interfaceInfoParamDto == null || StringUtils.isBlank(interfaceInfoParamDto.getInterfaceInfo().getInterfaceCode())) {
            return new MessageResult(false, "参数为空");
        }
        try {
            return interfaceInfoService.addInterfaceInfo(interfaceInfoParamDto);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/getInterfaceInfoByInterfaceCode")
    @ResponseBody
    public MessageResult getInterfaceInfoByInterfaceCode(String interfaceCode) {
        if (StringUtils.isBlank(interfaceCode)) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByInterfaceCode(interfaceCode);

            if (interfaceInfo == null) {
                return new MessageResult(false, "请求的数据不存在！");
            }
            return new MessageResult(true, interfaceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updateInterfaceInfoByPkId")
    @ResponseBody
    public MessageResult updateInterfaceInfoByPkId(@RequestBody InterfaceInfoParamDto interfaceInfoParamDto) {
        if (interfaceInfoParamDto == null) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            return interfaceInfoService.updateInterfaceInfoByPkId(interfaceInfoParamDto);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试!");
        }
    }

    @RequestMapping("/deleteInterfaceInfo")
    @ResponseBody
    public MessageResult deleteInterfaceInfo(@RequestBody InterfaceInfo[] interfaceInfos) {
        if (interfaceInfos.length <= 0) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            return interfaceInfoService.deleteInterfaceInfo(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/selectInterfaceInfoList")
    @ResponseBody
    public PageListResult selectInterfaceInfoList() {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList();
            return new PageListResult(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试使用接口
     *
     * @param name
     * @param sex
     * @return
     */
    @RequestMapping("/selectInterfaceInfoTest")
    @ResponseBody
    public Object selectInterfaceInfoTest(@RequestBody String name, String sex) {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList();
            String str = JSONUtil.parseList2JSON(interfaceInfos);
            return JSON.toJSON(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    public String getJsonStr() {
        String str = "{\"consumeId\":\"Cfsfgsgfdssdssfsgasfaf\",\"consumeTime\":361,\"retData\":{\"jkmxs2\":[{\"sfzh\":\"235235\",\"rq\":\"35234\",\"zy\":\"3425235\",\"srje\":\"235235\"},{\"sfzh\":\"235235\",\"rq\":\"23452\",\"zy\":\"2435235\",\"srje\":\"2535423\"},{\"sfzh\":\"234542345\",\"rq\":\"23523542\",\"zy\":\"252352\",\"srje\":\"25234234\"}],\"jnxx2\":{\"rq\":\"201344\",\"yjje\":\"34535\",\"fgsd\":\"453\"},\"dkxxs\":[]},\"status\":\"SUCCESS\",\"statusCode\":\"000000\"}";
        return str;
    }

    /**
     * 测试使用接口
     *
     * @param interfaceInfo
     * @return
     */
    @RequestMapping("/getInterfaceInfoTest")
    @ResponseBody
    public InterfaceInfo getInterfaceInfoTest(@RequestBody InterfaceInfo interfaceInfo) {
        String pkId = interfaceInfo.getPkId();
        if (StringUtils.isBlank(pkId)) {
            return null;
        }
        try {
            InterfaceInfo interfaceInfo2 = interfaceInfoService.getInterfaceInfoByPkId(pkId);
            return interfaceInfo2;
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile) {
        boolean status = true;
        String message = "上传成功";

        if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xlsx")) {
            return new MessageResult(false, "目前仅支持xls类型的文件，请修改后重试");
        }
        //判断结尾是否为xl或者xlsx
        if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xls")) {
            //将文件放到项目上传文件目录中
            String uploadFilePath = FileUtil.uploadFile(FileUtil
                    .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
            Map<String, String> result = interfaceInfoService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody InterfaceInfo[] interfaceInfos) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = interfaceInfoService.createExcel(interfaceInfos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
