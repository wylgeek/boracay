package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.bigdata.udsp.ed.service.EdInterfaceParamService;
import com.hex.goframe.model.PageListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/5
 * Time:17:00
 */
@Controller
@RequestMapping("/interfaceParam")
public class EdInterfaceParamController {

    @Autowired
    private EdInterfaceParamService edInterfaceParamService;

    @RequestMapping("/selectListRequest/{pkId}")
    @ResponseBody
    public PageListResult selectListRequest(@PathVariable("pkId") String pkId) {
        EdInterfaceParam edInterfaceParam = new EdInterfaceParam();
        edInterfaceParam.setType("1");
        edInterfaceParam.setInterfaceId(pkId);
        List list = edInterfaceParamService.selectList(edInterfaceParam);
        return new PageListResult(list);
    }

    @RequestMapping("/selectListResponse/{pkId}")
    @ResponseBody
    public PageListResult selectListResponse(@PathVariable("pkId") String pkId) {
        EdInterfaceParam edInterfaceParam = new EdInterfaceParam();
        edInterfaceParam.setType("2");
        edInterfaceParam.setInterfaceId(pkId);
        List list = edInterfaceParamService.selectList(edInterfaceParam);
        return new PageListResult(list);
    }
}
