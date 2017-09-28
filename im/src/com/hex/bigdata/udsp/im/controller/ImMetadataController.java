package com.hex.bigdata.udsp.im.controller;


import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.dto.ImMetadataDto;
import com.hex.bigdata.udsp.im.dto.ImMetadataView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.service.ImMetadataService;
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
 * Created by JunjieM on 2017-9-4.
 */
@RequestMapping("/im/md")
@Controller
public class ImMetadataController {
    private static Logger logger = LogManager.getLogger(ImMetadataController.class);
    @Autowired
    private ImMetadataService imMetadataService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(ImMetadataView imMetadataView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(imMetadataView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<ImMetadataView> list = null;
        try {
            list = imMetadataService.select(imMetadataView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        ImMetadata imMetadata = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                imMetadata = imMetadataService.select(pkId);
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
        return new MessageResult(status, message, imMetadata);
    }

    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody ImMetadataDto imMetadataDto) {
        boolean status = true;
        String message = "添加成功";
        String pkId = "";
        if (imMetadataDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                pkId = imMetadataService.insert(imMetadataDto);
                if (StringUtils.isBlank(pkId)) {
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
        return new MessageResult(status, message, pkId);
    }

    @RequestMapping({"/insertAndCreate"})
    @ResponseBody
    public MessageResult insertAndCreate(@RequestBody ImMetadataDto imMetadataDto) {
        boolean status = true;
        String message = "保存并创建成功";
        String pkId = "";
        if (imMetadataDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(imMetadataService.insert(imMetadataDto)) || !imMetadataService.createTable(imMetadataDto.getImMetadata().getPkId())) {
                    status = false;
                    message = "保存并创建失败";
                } else {
                    pkId = imMetadataDto.getImMetadata().getPkId();
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
        return new MessageResult(status, message, pkId);
    }

    @RequestMapping({"/updateAndCreate"})
    @ResponseBody
    public MessageResult updateAndCreate(@RequestBody ImMetadataDto imMetadataDto) {
        boolean status = true;
        String message = "保存并创建成功";
        if (imMetadataDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.update(imMetadataDto) || !imMetadataService.createTable(imMetadataDto.getImMetadata().getPkId())) {
                    status = false;
                    message = "保存并创建失败";
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

    @RequestMapping({"/createTable/{pkId}"})
    @ResponseBody
    public MessageResult createTable(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "创建成功";
        if (pkId == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.createTable(pkId)) {
                    status = false;
                    message = "创建失败";
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

    @RequestMapping({"/dropTable/{pkId}"})
    @ResponseBody
    public MessageResult dropTable(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "删除成功";
        if (pkId == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.dropTable(pkId)) {
                    status = false;
                    message = "删除失败";
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

    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody ImMetadataDto imMetadataDto) {
        boolean status = true;
        String message = "更新成功";
        if (imMetadataDto == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.update(imMetadataDto)) {
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
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody ImMetadata[] imMetadatas) {
        boolean status = true;
        String message = "删除成功";
        if (imMetadatas.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            if (!imMetadataService.delete(imMetadatas)) {
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

    @RequestMapping({"/checkName/{name}"})
    @ResponseBody
    public MessageResult checkName(@PathVariable("name") String name) {
        boolean status = true;
        String message = "检查完成，名称存在！";
        if (StringUtils.isBlank(name)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.checkName(name)) {
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
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/checkSchemaExists/{dsId}/{tbName}"})
    @ResponseBody
    public MessageResult checkSchemaExists(@PathVariable("dsId") String dsId, @PathVariable("tbName") String tbName) {
        try {
            if (imMetadataService.checkSchemaExists(dsId, tbName)) {
                return new MessageResult(false, "表已经存在，不可使用！");
            }
        } catch (Exception e) {
            return new MessageResult(false, e.getMessage());
        }
        return new MessageResult(true, "表不存在，可以使用！");
    }

    @RequestMapping({"/getCloumnInfo/{dsId}/{tbName}"})
    @ResponseBody
    public MessageResult getCloumnInfo(@PathVariable("dsId") String dsId, @PathVariable("tbName") String tbName) {
        boolean status = true;
        String message = "获取外表字段信息成功！";
        List<MetadataCol> metadataCols = null;
        if (StringUtils.isBlank(dsId) || StringUtils.isBlank(tbName)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (!imMetadataService.checkSchemaExists(dsId, tbName)) {
                    return new MessageResult(false, "外表不存在，请检查后重新输入！");
                }
                metadataCols = imMetadataService.getCloumnInfo(dsId, tbName);
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message, metadataCols);
    }

    @ResponseBody
    @RequestMapping("selectAll")
    public MessageResult selectAll() {
        List<ImMetadata> imMetadatas = imMetadataService.selectAll();
        return new PageListResult(imMetadatas);
    }

    /**
     * 交互建模-元数据excel上传
     *
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public MessageResult upload(MultipartFile excelFile) {
        boolean status = true;
        String message = "上传成功";
        try {
            //判断结尾是否为xl或者xlsx
            if (((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xls")
                    || ((CommonsMultipartFile) excelFile).getFileItem().getName().endsWith(".xlsx")) {
                //将文件放到项目上传文件目录中
                String uploadFilePath = FileUtil.uploadFile(FileUtil
                        .getRealUploadPath("EXCEL_UPLOAD"), excelFile);
                Map<String, String> result = imMetadataService.uploadExcel(uploadFilePath);
                if ("false".equals(result.get("status"))) {
                    status = false;
                    message = result.get("message");
                }
            } else {
                status = false;
                message = "请上传正确格式的文件！";
            }
        } catch (Exception e) {
            message = e.getMessage();
            status = false;
        }
        return new MessageResult(status, message);
    }

    @ResponseBody
    @RequestMapping("/download")
    public String createExcel(@RequestBody ImMetadata[] imMetadatas) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = imMetadataService.createExcel(imMetadatas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 获取状态为已建的目标元数据（实时和批量）信息列表
     *
     * @param type
     * @return
     */
    @RequestMapping("getTargetMateData/{type}")
    @ResponseBody
    public MessageResult getTargetMateData(@PathVariable String type) {
        List<ImMetadata> imMetadatas = null;
        try {
            imMetadatas = imMetadataService.selectTargetMateData(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageListResult(imMetadatas);
    }
}
