package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.ServiceCountDto;
import com.hex.bigdata.udsp.ed.dto.ServiceInfoDto;
import com.hex.bigdata.udsp.ed.model.ServiceCount;
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
public class ServiceCountMapper extends BaseMapper {

    public int addServiceCount(ServiceCount serviceCount) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.ServiceCountMapper.addServiceCount",
                serviceCount);
    }

    public List<ServiceCount> getServiceCountList(ServiceCountDto serviceCountDto) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.ServiceCountMapper.getServiceCountList",
                serviceCountDto);
    }

    public List<ServiceCountDto> getServiceCountGroupByDay() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.ServiceCountMapper.getServiceCountGroupByDay");
    }

}
