package com.hex.bigdata.udsp.ed.connect.controller;

import com.hex.bigdata.udsp.ed.connect.service.ConnectService;
import com.hex.bigdata.udsp.ed.controller.EdApplicationController;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jc.zhao
 * Date:2018/1/9
 * Time:10:07
 */
@Controller
@RequestMapping("/connect")
public class ConnectController {
    private static Logger logger = LoggerFactory.getLogger(EdApplicationController.class);

    @Autowired
    private ConnectService connectService;

    @RequestMapping("/getData")
    @ResponseBody
    public String getData(Map reqParam, EdApplication edApplication){
        try{
            String returnJson = connectService.getData(reqParam,edApplication);
            return returnJson;
        } catch (Exception e) {
            logger.info("接口调用异常");
            e.printStackTrace();
            return "接口调用异常";
        }
    }

    @RequestMapping("/getDataTest1")
    @ResponseBody
    public String getDataTest1(){
        Map reqParam = new HashMap();
        EdApplication edApplication = new EdApplication();
        reqParam.put()
        try{
            String returnJson = connectService.getData(reqParam,edApplication);
            return returnJson;
        } catch (Exception e) {
            logger.info("接口调用异常");
            e.printStackTrace();
            return "接口调用异常";
        }
    }

    @RequestMapping("/getDataTest2")
    @ResponseBody
    public String getDataTest2(){
        Map reqParam = new HashMap();
        EdApplication edApplication = new EdApplication();
        reqParam.put()
        try{
            String returnJson = connectService.getData(reqParam,edApplication);
            return returnJson;
        } catch (Exception e) {
            logger.info("接口调用异常");
            e.printStackTrace();
            return "接口调用异常";
        }
    }

}
