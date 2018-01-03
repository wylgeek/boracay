package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.ServiceCountDto;
import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.bigdata.udsp.ed.service.ServiceCountService;
import com.hex.bigdata.udsp.ed.service.ServiceInfoService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:49
 */
@Controller
@RequestMapping("/serviceCount")
public class ServiceCountController {
    private static Logger logger = LoggerFactory.getLogger(ServiceCountController.class);

    @Autowired
    private ServiceCountService serviceCountService;

    @RequestMapping("/addServiceCount")
    @ResponseBody
    public MessageResult addServiceCount(String time) {
        try{
            ServiceInfo serviceInfo = new ServiceInfo();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            serviceInfo.setCrtTime(format.parse(time));
            MessageResult messageResult = serviceCountService.addServiceCount(serviceInfo);
            return messageResult;
        } catch (Exception e ) {
            logger.info("服务请求异常{}",this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getServiceCountGroupByDay")
    @ResponseBody
    public List<ServiceCountDto> getServiceCountGroupByDay() {
        try{
            List<ServiceCountDto> serviceCountDtos = serviceCountService.getServiceCountGroupByDay();
            return serviceCountDtos;
        } catch (Exception e) {
            logger.info("服务请求异常{}",this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }
}
