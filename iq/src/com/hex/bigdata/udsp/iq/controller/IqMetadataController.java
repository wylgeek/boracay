package com.hex.bigdata.udsp.iq.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.dto.IqMetadataPropsView;
import com.hex.bigdata.udsp.iq.dto.IqMetadataView;
import com.hex.bigdata.udsp.iq.model.IqMetadata;
import com.hex.bigdata.udsp.iq.service.IqMetadataService;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
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
@RequestMapping("/iq/md")
@Controller
public class IqMetadataController extends BaseController {
    private static Logger logger = LogManager.getLogger(IqMetadataController.class);

    @Autowired
    private IqMetadataService iqMetadataService;
    @Autowired
    private IqProviderService iqProviderService;

    @RequestMapping({"/page"})
    @ResponseBody
    public PageListResult page(IqMetadataView iqMetadataView, Page page) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqMetadataView) + " page=" + JSONUtil.parseObj2JSON(page));
        List<IqMetadataView> list = null;
        try {
            list = iqMetadataService.select(iqMetadataView, page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list, page);
    }

    @RequestMapping({"/select"})
    @ResponseBody
    public PageListResult select(IqMetadataView iqMetadataView) {
        logger.debug("select search=" + JSONUtil.parseObj2JSON(iqMetadataView));
        List<IqMetadataView> list = null;
        try {
            list = iqMetadataService.select(iqMetadataView);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/insert"})
    @ResponseBody
    public MessageResult insert(@RequestBody IqMetadataPropsView iqMetadataPropsView) {
        boolean status = true;
        String message = "添加成功";
        if (iqMetadataPropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (StringUtils.isBlank(iqMetadataService.insert(iqMetadataPropsView))) {
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
            logger.error(message);
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
                if (!iqMetadataService.checkName(name)) {
                    status = false;
                    message = "检查完成，名称不存在！";
                }
            } catch (Exception e) {
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

    @RequestMapping({"/select/{pkId}"})
    @ResponseBody
    public MessageResult selectByPkId(@PathVariable("pkId") String pkId) {
        boolean status = true;
        String message = "查询成功";
        IqMetadata iqMetadata = null;
        if (StringUtils.isBlank(pkId)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                iqMetadata = iqMetadataService.select(pkId);
            } catch (Exception e) {
                status = false;
                message = "系统异常：" + e.getMessage();
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.error(message);
        }
        return new MessageResult(status, message, iqMetadata);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MessageResult delete(@RequestBody IqMetadata[] iqMetadatas) {
        boolean status = true;
        String message = "删除成功";
        if (iqMetadatas.length == 0) {
            status = false;
            message = "请求参数为空";
        }
        try {
            for (IqMetadata iqMetadata : iqMetadatas) {
                if (iqMetadataService.hasUsed(iqMetadata)) {
                    status = false;
                    message = "名称为" + iqMetadataService.select(iqMetadata.getPkId()).getName() + "元数据已经被引用！";
                    break;
                }
            }
            if (status && !iqMetadataService.delete(iqMetadatas)) {
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
            logger.error(message);
        }
        return new MessageResult(status, message);
    }

    @RequestMapping({"/update"})
    @ResponseBody
    public MessageResult update(@RequestBody IqMetadataPropsView iqMetadataPropsView) {
        boolean status = true;
        String message = "更新成功";
        if (iqMetadataPropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                if (iqMetadataService.hasUsed(iqMetadataPropsView.getIqMetadata())) {
                    status = false;
                    message = "该元数据已经被引用！";
                }
                if (status && !iqMetadataService.update(iqMetadataPropsView)) {
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

    /**
     * 获取字段信息
     *
     * @param dsId
     * @param tbName
     * @return
     */
    @RequestMapping({"/getColumnInfo/{dsId}"})
    @ResponseBody
    public MessageResult getColumnInfo(@PathVariable("dsId") String dsId, String tbName) {
        boolean status = true;
        String message = "获取字段信息成功！";
        List<MetadataCol> metadataCols = null;
        if (StringUtils.isBlank(dsId) || StringUtils.isBlank(tbName)) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                metadataCols = iqProviderService.getColumnInfo(dsId, tbName);
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
            Map<String, String> result = iqMetadataService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody IqMetadata[] iqMetadatas) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = iqMetadataService.createExcel(iqMetadatas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
