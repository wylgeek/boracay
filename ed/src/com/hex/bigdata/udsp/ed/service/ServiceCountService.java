package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.ServiceCountMapper;
import com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.ServiceCountDto;
import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceCount;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:19
 */
@Service
public class ServiceCountService {
    @Autowired
    private ServiceCountMapper serviceCountMapper;

    public MessageResult addServiceCount(ServiceInfo serviceInfo) {
        //测试阶段使用
        serviceInfo.setServiceName("tongdunjiekou");
        serviceInfo.setPkId("");
        serviceInfo.setServiceName(serviceInfo.getServiceName().trim());
        serviceInfo.setReqUrl(serviceInfo.getReqUrl().trim());

        ServiceCount serviceCount = new ServiceCount();

        serviceCount.setPkId(Util.uuid());
        serviceCount.setServiceInfoId(serviceInfo.getPkId());
        serviceCount.setServiceName(serviceInfo.getServiceName());
        serviceCount.setServiceCnName(serviceInfo.getServiceCnName());
        serviceCount.setServiceType(serviceInfo.getServiceType());
        serviceCount.setServiceCompany(serviceInfo.getServiceCompany());
        serviceCount.setRequestUser(WebUtil.getCurrentUserId());
        serviceCount.setRequestTime(serviceInfo.getCrtTime());
        int result = serviceCountMapper.addServiceCount(serviceCount);
        if(result != 1){
            return new MessageResult(false,"添加数据失败！");
        }
        return new MessageResult(true,"添加数据成功！");

        //项目发布时使用代码
        /*if(serviceInfo == null || StringUtils.isBlank(serviceInfo.getPkId()) ||
                StringUtils.isBlank(serviceInfo.getServiceName())) {
            return new MessageResult(false,"参数为空！");
        }

        ServiceCount serviceCount = new ServiceCount();
        if(!StringUtils.isBlank(serviceInfo.getServiceName())){
            serviceCount.setServiceName(serviceInfo.getServiceName());
        }
        serviceCount.setPkId(Util.uuid());
        serviceCount.setServiceInfoId(serviceInfo.getPkId());
        serviceCount.setServiceName(serviceInfo.getServiceName());
        serviceCount.setServiceCnName(serviceInfo.getServiceCnName());
        serviceCount.setServiceType(serviceInfo.getServiceType());
        serviceCount.setServiceCompany(serviceInfo.getServiceCompany());
        serviceCount.setRequestUser(WebUtil.getCurrentUserId());
        serviceCount.setRequestTime(new Date());
        int result = serviceCountMapper.addServiceCount(serviceCount);
        if(result != 1){
            return new MessageResult(false,"添加数据失败！");
        }
        return new MessageResult(true,"添加数据成功！");*/
    }

    public List<ServiceCount> getServiceCountList(ServiceCountDto serviceCountDto) {
        if(!(serviceCountDto == null || StringUtils.isBlank(serviceCountDto.getServiceName()))){
            serviceCountDto.setServiceName(serviceCountDto.getServiceName().trim());
        }
        return serviceCountMapper.getServiceCountList(serviceCountDto);
    }

    public List<ServiceCountDto> getServiceCountGroupByDay() {
        return serviceCountMapper.getServiceCountGroupByDay();
    }
}

