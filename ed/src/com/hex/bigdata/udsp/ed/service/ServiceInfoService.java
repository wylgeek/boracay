package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
public class ServiceInfoService {
    @Autowired
    private ServiceInfoMapper serviceInfoMapper;

    public ServiceInfo getServiceInfoByPkId(String pkId) {
        return serviceInfoMapper.getServiceInfoByPkId(pkId);
    }

    public List<ServiceInfo> getServiceInfoList(ServiceInfoDto serviceInfoDto, Page page) {
        return serviceInfoMapper.getServiceInfoList(serviceInfoDto, page);
    }

    public ServiceInfo getServiceInfoByServiceName(String serviceName) {
        return serviceInfoMapper.getServiceInfoByServiceName(serviceName);
    }

    public MessageResult addServiceInfo(ServiceInfo serviceInfo) {
        //检查是否已经存在
        ServiceInfo serviceInfo1 = getServiceInfoByServiceName(serviceInfo.getServiceName());
        if (serviceInfo1 != null) {
            return new MessageResult(false, "服务编码已存在，请重新输入！");
        }

        //添加数据
        serviceInfo.setServiceName(serviceInfo.getServiceName().trim());
        if (!StringUtils.isBlank(serviceInfo.getReqUrl())) {
            serviceInfo.setReqUrl(serviceInfo.getReqUrl().trim());
        }
        serviceInfo.setPkId(Util.uuid());
        serviceInfo.setCrtUser(WebUtil.getCurrentUserId());
        serviceInfo.setCrtTime(new Date());
        int result = serviceInfoMapper.addServiceInfo(serviceInfo);

        //校验返回结果
        if (result != 1) {
            return new MessageResult(false, "添加数据失败，请重试！");
        }
        return new MessageResult(true, "添加数据成功！");
    }

    public MessageResult updateServiceInfoByPkId(ServiceInfo serviceInfo) {
        if (!StringUtils.isBlank(serviceInfo.getServiceName())) {
            serviceInfo.setServiceName(serviceInfo.getServiceName().trim());
        }
        if (!StringUtils.isBlank(serviceInfo.getReqUrl())) {
            serviceInfo.setReqUrl(serviceInfo.getReqUrl().trim());
        }

        serviceInfo.setUpdateUser(WebUtil.getCurrentUserId());
        serviceInfo.setUpdateTime(new Date());
        int result = serviceInfoMapper.updateServiceInfoByPkId(serviceInfo);
        if (result != 1) {
            return new MessageResult(false, "更新数据失败！");
        }
        return new MessageResult(true, "更新数据成功！");
    }

    @Transient
    public MessageResult deleteServiceInfo(ServiceInfo[] serviceInfos) {
        int count = 0;
        for (ServiceInfo serviceInfo : serviceInfos) {
            int result = serviceInfoMapper.deleteServiceInfo(serviceInfo.getPkId());
            if (result == 1) {
                count++;
            }
        }
        if (count != serviceInfos.length) {
            return new MessageResult(false, "删除失败，请重试！");
        }
        return new MessageResult(true, "删除成功！");
    }
}
