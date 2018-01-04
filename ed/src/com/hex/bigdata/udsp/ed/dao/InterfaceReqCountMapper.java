package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.InterfaceReqCountDto;
import com.hex.bigdata.udsp.ed.model.InterfaceReqCount;
import com.hex.goframe.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InterfaceReqCountMapper extends BaseMapper {
    public int addServiceCount(InterfaceReqCount interfaceReqCount) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.InterfaceReqCountMapper.insert",
                interfaceReqCount);
    }

    public List<InterfaceReqCount> getServiceCountList(InterfaceReqCountDto interfaceReqCountDto) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.InterfaceReqCountMapper.getServiceCountList",
                interfaceReqCountDto);
    }

    public List<InterfaceReqCountDto> getServiceCountGroupByDay() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.InterfaceReqCountMapper.getServiceCountGroupByDay");
    }
}