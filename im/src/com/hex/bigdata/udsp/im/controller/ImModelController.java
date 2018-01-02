package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.bigdata.udsp.im.dto.ImModelDto;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
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
 * Created by JunjieM on 2017-9-4.
 */
@Controller
@RequestMapping("im/model")
public class ImModelController {

    private Logger log = LoggerFactory.getLogger(ImModelController.class);
    @Autowired
    private ImModelService imModelService;

    @ResponseBody
    @RequestMapping("insert")
    public MessageResult inset(@RequestBody ImModelDto imModelViews) {
        log.debug("插入交互建模数据为：" + JSONUtil.parseObj2JSON(imModelViews));
        boolean result = true;
        String message = "保存成功！";
        try {
            result = imModelService.insert(imModelViews) != null;
            if (!result) {
                message = "保存失败！";
            }
        } catch (Exception e) {
            result = false;
            message = "保存失败，失败信息：" + e.getMessage();
            log.error("交互建模数据插入失败，失败数据为：" + JSONUtil.parseObj2JSON(imModelViews) + ";错误信息：" + e.getMessage());
        }
        return new MessageResult(result, message);
    }

    @ResponseBody
    @RequestMapping("update/{pkId}")
    public MessageResult update(@RequestBody ImModelDto imModelViews, @PathVariable String pkId) {
        log.debug("更新交互建模数据为：" + JSONUtil.parseObj2JSON(imModelViews));
        boolean result = true;
        String message = "更新成功";
        try {
            //设置pkId
            imModelViews.getImModel().setPkId(pkId);
            result = imModelService.update(imModelViews);
            if (!result) {
                message = "更新失败！";
            }
        } catch (Exception e) {
            result = false;
            message = "更新失败，失败信息：" + e.getMessage();
            log.error("交互建模数据更新失败，失败数据为：" + JSONUtil.parseObj2JSON(imModelViews) + ";错误信息：" + e.getMessage());
        }
        return new MessageResult(result, message);
    }

    /**
     * 根据查询内容获取交互建模列表
     *
     * @param imModelView
     * @return
     */
    @ResponseBody
    @RequestMapping("page")
    public PageListResult page(ImModelView imModelView, Page page) {
        log.debug("查询交互建模列表的查询为：" + JSONUtil.parseObj2JSON(imModelView) + ";页面信息为：" + JSONUtil.parseObj2JSON(page));
        List<ImModelView> imModels = imModelService.selectPage(imModelView, page);

        return new PageListResult(imModels, page);
    }

    @ResponseBody
    @RequestMapping("delete")
    public MessageResult delete(@RequestBody ImModel[] ImModels) {
        boolean result = true;
        String message = "删除成功";
        try {
            result = imModelService.delete(ImModels);
            if (!result) {
                message = "删除失败！";
            }
        } catch (Exception e) {
            result = false;
            message = "删除失败，错误信息：" + e.getMessage();
        }
        return new MessageResult(result, message);
    }

    /**
     * 通过主键获取交互建模基础信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("selectByPkId/{pkId}")
    public ImModel selectByPkId(@PathVariable String pkId) {

        return imModelService.selectByPkId(pkId);
    }

    /**
     * 通过名称检查模型名称是否已存在
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("checkExist/{modelName}")
    public MessageResult checkExist(@PathVariable String modelName) {
        boolean status = true;
        boolean result = false;
        String message = "模型名称不存在";
        try {
            if (imModelService.selectByName(modelName) != null) {
                result = true;
                message = "模型名称存在";
            }
        } catch (Exception e) {
            status = false;
            message = "检查模型名称是否存在失败";
            log.error("根据模型名称查询模型是否存在失败！模型名称为：" + modelName + ";错误信息：" + e.getMessage());
        }
        return new MessageResult(status, message, result);
    }

    /**
     * 获取源的字段列表
     *
     * @return
     */
    @RequestMapping("getColumnsInfo/{srcDataSourceId}")
    @ResponseBody
    public MessageResult getSrcMateData(@PathVariable String srcDataSourceId, @RequestBody Property[] properties) {
        boolean status = true;
        String message = "获取字段信息成功";
        List<MetadataCol> metadataCols = null;
        try {
            metadataCols = imModelService.getSrcMateData(properties, srcDataSourceId);
            if (metadataCols == null || metadataCols.size() == 0) {
                status = false;
                message = "获取字段信息失败";
            }
        } catch (Exception e) {
            message = e.getMessage();
            status = false;
        }
        return new MessageResult(status, message, metadataCols);
    }

    /**
     * 交互建模-模型excel上传
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
                Map<String, String> result = imModelService.uploadExcel(uploadFilePath);
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
    public String createExcel(@RequestBody ImModel[] imModels) {
        // 写入Excel文件
        String filePath = "";
        try {
            filePath = imModelService.createExcel(imModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }


    @ResponseBody
    @RequestMapping(value = "updateModelDelStatus/{pkId}/{status}")
    public MessageResult updateModelDelStatus(@PathVariable String pkId, @PathVariable String status) {
        boolean result = false;
        String message = "";
        try {
            ImModel imModel = imModelService.selectByPkId(pkId);
            //如果是删除的话要判断是否能够删除，原则上构建了的不能够直接删除，或者删除连带构建一起删除
            if ("1".equals(status) && "2".equals(imModel.getStatus())) {
                result = false;
                message = "该模型已建模不可删除！";
            } else {
                result = imModelService.updateModelDelStatus(pkId, status);
                if (result == false) {
                    message = "失败";
                } else {
                    message = "成功";
                }
            }
        } catch (Exception e) {
            result = false;
            message = e.getMessage();
        }
        return new MessageResult(result, message);
    }

    @ResponseBody
    @RequestMapping(value = "updateModelBuildStatus/{pkId}/{status}")
    public MessageResult updateModelBuildStatus(@PathVariable String pkId, @PathVariable String status) {
        boolean result = false;
        String message = "";
        try {
            //先获取该对一个pkId的交互建模模型
            ImModel imModel = imModelService.selectByPkId(pkId);
            //如果相同则说明已经已经构建或则已经删除构建
            if (imModel.getStatus() == status) {
                result = false;
                message = "请执行正确的操作！";
            }
            result = imModelService.updateStatus(imModel, status);
            if (result) {
                message = "成功";
            } else {
                message = "失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            message = e.getMessage();
        }
        return new MessageResult(result, message);
    }
}
