package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
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
public class InterfaceInfoMapper extends BaseMapper {

    public InterfaceInfo getInterfaceInfoByPkId(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.getInterfaceInfoByPkId",
                pkId);
    }

    public List<InterfaceInfo> getInterfaceInfoList(InterfaceInfoDto interfaceInfoDto, Page page) {
        List list = this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.getInterfaceInfoList",
                interfaceInfoDto, page.toPageBounds());
        return list;
    }

    public InterfaceInfo getInterfaceInfoByInterfaceCode(String interfaceCode) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.getInterfaceInfoByInterfaceCode",
                interfaceCode);
    }

    public int addInterfaceInfo(InterfaceInfo interfaceInfo) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.addInterfaceInfo",
                interfaceInfo);
    }

    public int updateInterfaceInfoByPkId(InterfaceInfo interfaceInfo) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.updateInterfaceInfoByPkId",
                interfaceInfo);
    }

    public int deleteInterfaceInfo(String pkId) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.deleteInterfaceInfo",
                pkId);
    }

    public List<InterfaceInfo> getInterfaceInfoList() {
        List list = this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper.getInterfaceInfoAll");
        return list;
    }
}
