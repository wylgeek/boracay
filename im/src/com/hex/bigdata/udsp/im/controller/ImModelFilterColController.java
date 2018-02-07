package com.hex.bigdata.udsp.im.controller;

import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import com.hex.bigdata.udsp.im.service.ImModelFilterService;
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
@RequestMapping("im/imMFilter")
public class ImModelFilterColController {

    @Autowired
    private ImModelFilterService imModelFilterService;

    @ResponseBody
    @RequestMapping("getImMFilterByMid/{mid}")
    public MessageResult<ImModelFilterCol> getImModelFilterByMid(@PathVariable String mid){
        List<ImModelFilterCol> imModelFilterCols = imModelFilterService.getImModelFiltersByMid(mid);

        return new PageListResult(imModelFilterCols);
    }

}
