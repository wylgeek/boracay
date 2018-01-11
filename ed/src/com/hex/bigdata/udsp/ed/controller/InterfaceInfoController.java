package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoParamDto;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.ed.service.InterfaceInfoService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:49
 */
@Controller
@RequestMapping("/interfaceInfo")
public class InterfaceInfoController {
    private static Logger logger = LoggerFactory.getLogger(InterfaceInfoController.class);

    @Autowired
    private InterfaceInfoService interfaceInfoService;

    @RequestMapping("/getInterfaceInfoByPkId")
    @ResponseBody
    public MessageResult getInterfaceInfoByPkId(String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByPkId(pkId);
            return new MessageResult(true, interfaceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/getInterfaceInfoList")
    @ResponseBody
    public PageListResult getInterfaceInfoList(InterfaceInfoDto interfaceInfoDto, Page page) {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList(interfaceInfoDto, page);
            return new PageListResult(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addInterfaceInfo")
    @ResponseBody
    public MessageResult addInterfaceInfo(@RequestBody InterfaceInfoParamDto interfaceInfoParamDto) {
        if (interfaceInfoParamDto == null || StringUtils.isBlank(interfaceInfoParamDto.getInterfaceInfo().getInterfaceCode())) {
            return new MessageResult(false, "参数为空");
        }
        try {
            return interfaceInfoService.addInterfaceInfo(interfaceInfoParamDto);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/getInterfaceInfoByInterfaceCode")
    @ResponseBody
    public MessageResult getInterfaceInfoByInterfaceCode(String interfaceCode) {
        if (StringUtils.isBlank(interfaceCode)) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByInterfaceCode(interfaceCode);

            if (interfaceInfo == null) {
                return new MessageResult(false, "请求的数据不存在！");
            }
            return new MessageResult(true, interfaceInfo);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/updateInterfaceInfoByPkId")
    @ResponseBody
    public MessageResult updateInterfaceInfoByPkId(@RequestBody InterfaceInfoParamDto interfaceInfoParamDto) {
        if (interfaceInfoParamDto == null) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            return interfaceInfoService.updateInterfaceInfoByPkId(interfaceInfoParamDto);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试!");
        }
    }

    @RequestMapping("/deleteInterfaceInfo")
    @ResponseBody
    public MessageResult deleteInterfaceInfo(@RequestBody InterfaceInfo[] interfaceInfos) {
        if (interfaceInfos.length <= 0) {
            return new MessageResult(false, "参数为空！");
        }
        try {
            return interfaceInfoService.deleteInterfaceInfo(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return new MessageResult(false, "请求失败，请重试！");
        }
    }

    @RequestMapping("/selectInterfaceInfoList")
    @ResponseBody
    public PageListResult selectInterfaceInfoList() {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList();
            return new PageListResult(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试使用接口
     *
     * @param name
     * @param sex
     * @return
     */
    @RequestMapping("/selectInterfaceInfoTest")
    @ResponseBody
    public PageListResult selectInterfaceInfoTest(@RequestBody String name, String sex) {
        try {
            List<InterfaceInfo> interfaceInfos = interfaceInfoService.getInterfaceInfoList();
            return new PageListResult(interfaceInfos);
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试使用接口
     *
     * @param pkId
     * @return
     */
    @RequestMapping("/getInterfaceInfoTest")
    @ResponseBody
    public InterfaceInfo getInterfaceInfoTest(@RequestBody InterfaceInfo interfaceInfo) {
        String pkId = interfaceInfo.getPkId();
        if (StringUtils.isBlank(pkId)) {
            return null;
        }
        try {
            InterfaceInfo interfaceInfo2 = interfaceInfoService.getInterfaceInfoByPkId(pkId);
            return interfaceInfo2;
        } catch (Exception e) {
            logger.info("服务请求异常{}", this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }
}
