package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceInfo;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:14:36
 */
@Repository
public class ServiceInfoMapper extends BaseMapper {

    public ServiceInfo getServiceInfoByPkId(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.getServiceInfoByPkId",
                pkId);
    }

    public List<ServiceInfo> getServiceInfoList(ServiceInfoDto serviceInfoDto, Page page) {
        List list = this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.getServiceInfoList",
                serviceInfoDto, page.toPageBounds());
        page.totalCount(list);
        return list;
    }

    public ServiceInfo selectByServiceName(String serviceName) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.selectByServiceName",
                serviceName);
    }

    public int addServiceInfo(ServiceInfo serviceInfo) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.addServiceInfo",
                serviceInfo);
    }

    public int updateServiceInfoByPkId(ServiceInfo serviceInfo) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.updateServiceInfoByPkId",
                serviceInfo);
    }

    public int deleteServiceInfo(String pkId) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.ServiceInfoMapper.deleteServiceInfo",
                pkId);
    }
}
