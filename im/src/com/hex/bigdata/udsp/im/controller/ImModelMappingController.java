package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.im.model.ImModelMapping;
import com.hex.bigdata.udsp.im.service.ImModelMappingService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.PageListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-4.
 */
@Controller
@RequestMapping("im/imMMapping")
public class ImModelMappingController {

    @Autowired
    private ImModelMappingService imModelMappingService;

    @ResponseBody
    @RequestMapping("getImMMappingByMid/{mid}")
    public MessageResult getImModelMappingsByModelId(@PathVariable String mid){

        List<ImModelMapping> imModelMappings =  imModelMappingService.getImModelMappingsByMid(mid);

        return new PageListResult(imModelMappings);
    }

}
