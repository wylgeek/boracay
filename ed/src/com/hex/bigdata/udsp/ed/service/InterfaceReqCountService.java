package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.InterfaceReqCountMapper;
import com.hex.bigdata.udsp.ed.dto.InterfaceReqCountDto;
import com.hex.bigdata.udsp.ed.model.InterfaceReqCount;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InterfaceReqCountService extends BaseService{

    @Autowired
    private InterfaceReqCountMapper interfaceReqCountMapper;

    public MessageResult addServiceCount(ServiceInfo serviceInfo) {
        if(serviceInfo == null || StringUtils.isBlank(serviceInfo.getPkId()) ||
                StringUtils.isBlank(serviceInfo.getServiceName())) {
            return new MessageResult(false,"参数为空！");
        }

        InterfaceReqCount interfaceReqCount = new InterfaceReqCount();
//        if(!StringUtils.isBlank(serviceInfo.getServiceName())){
//            serviceCount.setServiceName(serviceInfo.getServiceName());
//        }
//        interfaceReqCount.setPkId(Util.uuid());
//        serviceCount.setServiceInfoId(serviceInfo.getPkId());
//        serviceCount.setServiceName(serviceInfo.getServiceName());
//        serviceCount.setServiceCnName(serviceInfo.getServiceCnName());
//        serviceCount.setServiceType(serviceInfo.getServiceType());
//        serviceCount.setServiceCompany(serviceInfo.getServiceCompany());
//        serviceCount.setRequestUser(WebUtil.getCurrentUserId());
//        serviceCount.setRequestTime(new Date());
        int result = interfaceReqCountMapper.addServiceCount(interfaceReqCount);
        if(result != 1){
            return new MessageResult(false,"添加数据失败！");
        }
        return new MessageResult(true,"添加数据成功！");
    }

    public List<InterfaceReqCount> getServiceCountList(InterfaceReqCountDto interfaceReqCountDto) {
        if(!(interfaceReqCountDto == null || StringUtils.isBlank(interfaceReqCountDto.getInterfaceCode()))){
            interfaceReqCountDto.setInterfaceCode(interfaceReqCountDto.getInterfaceCode());
        }
        return interfaceReqCountMapper.getServiceCountList(interfaceReqCountDto);
    }

    public List<InterfaceReqCountDto> getServiceCountGroupByDay() {
        return interfaceReqCountMapper.getServiceCountGroupByDay();
    }
}
