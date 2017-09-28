package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import com.hex.bigdata.udsp.im.service.ImMetadataColService;
import com.hex.goframe.model.PageListResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-4.
 */
@RequestMapping("/im/md/col")
@Controller
public class ImMetadataColController {
    private static Logger logger = LogManager.getLogger(ImMetadataColController.class);
    @Autowired
    private ImMetadataColService imMetadataColService;

    @RequestMapping({"/select/{mdId}"})
    @ResponseBody
    public PageListResult selectByMdId(@PathVariable("mdId") String mdId) {
        logger.debug("select mdId=" + mdId);
        List<ImMetadataCol> list = null;
        try {
            list = imMetadataColService.select(mdId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @ResponseBody
    @RequestMapping("selectModelUpdateKeys/{modelId}")
    public PageListResult selectModelUpdateKeys(@PathVariable String modelId){
        List<ImMetadataCol> list = imMetadataColService.selectModelUpdateKeys(modelId);
        return new PageListResult(list);
    }
}
