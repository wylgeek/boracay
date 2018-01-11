package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.InterfaceReqCountDto;
import com.hex.bigdata.udsp.ed.service.EdInterfaceCountService;
import com.hex.goframe.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ed/interfaceReqCount")
public class InterfaceReqCountController extends BaseController {

    @Autowired
    private EdInterfaceCountService edInterfaceCountService;

    @RequestMapping("/getServiceCountGroupByDay")
    @ResponseBody
    public List<InterfaceReqCountDto> getServiceCountGroupByDay() {
        try{
            List<InterfaceReqCountDto> serviceCountDtos = edInterfaceCountService.getServiceCountGroupByDay();
            return serviceCountDtos;
        } catch (Exception e) {
            logger.info("服务请求异常{}",this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }
}
