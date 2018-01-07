package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.InterfaceReqCountMapper;
import com.hex.bigdata.udsp.ed.dto.InterfaceReqCountDto;
import com.hex.bigdata.udsp.ed.model.InterfaceReqCount;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterfaceReqCountService extends BaseService{

    @Autowired
    private InterfaceReqCountMapper interfaceReqCountMapper;

    public MessageResult addServiceCount(InterfaceInfo interfaceInfo) {
        if(interfaceInfo == null || StringUtils.isBlank(interfaceInfo.getPkId()) ||
                StringUtils.isBlank(interfaceInfo.getInterfaceCode())) {
            return new MessageResult(false,"参数为空！");
        }

        InterfaceReqCount interfaceReqCount = new InterfaceReqCount();
//        if(!StringUtils.isBlank(interfaceInfo.getServiceName())){
//            serviceCount.setServiceName(interfaceInfo.getServiceName());
//        }
//        interfaceReqCount.setPkId(Util.uuid());
//        serviceCount.setServiceInfoId(interfaceInfo.getPkId());
//        serviceCount.setServiceName(interfaceInfo.getServiceName());
//        serviceCount.setServiceCnName(interfaceInfo.getServiceCnName());
//        serviceCount.setServiceType(interfaceInfo.getServiceType());
//        serviceCount.setServiceCompany(interfaceInfo.getServiceCompany());
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
