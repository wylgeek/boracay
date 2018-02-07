package com.hex.bigdata.udsp.ed.controller;

import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountReqDto;
import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountResDto;
import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.bigdata.udsp.ed.service.EdInterfaceCountService;
import com.hex.bigdata.udsp.ed.service.EdInterfaceParamService;
import com.hex.goframe.model.PageListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/interfaceCount")
public class EdInterfaceCountController {
    private static Logger logger = LoggerFactory.getLogger(EdInterfaceCountController.class);

    @Autowired
    private EdInterfaceCountService edInterfaceCountService;

    @RequestMapping("/selectCountInfo")
    @ResponseBody
    public PageListResult<EdInterfaceCountResDto> selectCountInfo(EdInterfaceCountReqDto edInterfaceCountReqDto){
        try{
            List list = edInterfaceCountService.selectCountInfo(edInterfaceCountReqDto);
            return new PageListResult<>(list);
        } catch (Exception e) {
            logger.info("获取ED统计信息失败"+this.getClass().getName());
            e.printStackTrace();
            return null;
        }
    }

}
