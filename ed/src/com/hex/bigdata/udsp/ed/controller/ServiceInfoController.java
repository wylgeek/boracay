package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.bigdata.udsp.ed.service.ServiceInfoService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:49
 */
@Controller
@RequestMapping("/serviceInfo")
public class ServiceInfoController {
    private static Logger logger = LoggerFactory.getLogger(ServiceInfoController.class);

    @Autowired
    private ServiceInfoService serviceInfoService;

    @RequestMapping("/getServiceInfoByPkId")
    @ResponseBody
    public MessageResult getServiceInfoByPkId(String pkId) {
        try{
            if(StringUtils.isBlank(pkId)){
                return new MessageResult(false,"参数为空！");
            }
            ServiceInfo serviceInfo = serviceInfoService.getServiceInfoByPkId(pkId);
            return new MessageResult(true,serviceInfo);
        } catch (Exception e){
            logger.info("服务请求异常{}",this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false,"请求失败，请重试！");
        }
    }

    @RequestMapping("/getServiceInfoList")
    @ResponseBody
    public Map getServiceInfoList(ServiceInfoDto serviceInfoDto, Page page) {
        try {
            List<ServiceInfo> serviceInfos = serviceInfoService.getServiceInfoList(serviceInfoDto, page);
            HashMap map = new HashMap();
            map.put("data", serviceInfos);
            map.put("total", Integer.valueOf(page.getTotalCount()));
            return map;
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addServiceInfo")
    @ResponseBody
    public MessageResult addServiceInfo(@RequestBody ServiceInfo serviceInfo) {
        try {
            if (serviceInfo == null || StringUtils.isBlank(serviceInfo.getServiceName())) {
                return new MessageResult(false, "参数为空1");
            }

            return serviceInfoService.addServiceInfo(serviceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/getServiceInfoByServiceName")
    @ResponseBody
    public MessageResult getServiceInfoByServiceName(String serviceName) {
        try {
            if (StringUtils.isBlank(serviceName)) {
                return new MessageResult(false, "参数为空！");
            }
            ServiceInfo serviceInfo = serviceInfoService.getServiceInfoByServiceName(serviceName);

            if (serviceInfo == null) {
                return new MessageResult(false, "请求的数据不存在！");
            }
            return new MessageResult(true, serviceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updateServiceInfoByPkId")
    @ResponseBody
    public MessageResult updateServiceInfoByPkId(@RequestBody ServiceInfo serviceInfo) {
        try {
            if (serviceInfo == null || StringUtils.isBlank(serviceInfo.getPkId())) {
                return new MessageResult(false, "参数为空！");
            }
            return serviceInfoService.updateServiceInfoByPkId(serviceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试!");
        }
    }

    @RequestMapping("/deleteServiceInfo")
    @ResponseBody
    public MessageResult deleteServiceInfo(@RequestBody ServiceInfo[] serviceInfos) {
        try{
            if(serviceInfos.length <= 0){
                return new MessageResult(false,"参数为空！");
            }
            return serviceInfoService.deleteServiceInfo(serviceInfos);
        } catch (Exception e){
            logger.info("服务请求异常{}",this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false,"请求失败，请重试！");
        }
    }
}
