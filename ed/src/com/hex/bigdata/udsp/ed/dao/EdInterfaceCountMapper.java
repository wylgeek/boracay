package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountReqDto;
import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountResDto;
import com.hex.bigdata.udsp.ed.model.EdInterfaceCount;
import com.hex.goframe.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/4
 * Time:19:41
 */
@Repository
public class EdInterfaceCountMapper extends BaseMapper {
    public int insert(EdInterfaceCount edInterfaceCount) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.EdInterfaceCountMapper.insert",
                edInterfaceCount);
    }

    public List<EdInterfaceCountResDto> selectCountInfo(EdInterfaceCountReqDto edInterfaceCountReqDto){
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceCountMapper.selectCountInfo",
                edInterfaceCountReqDto);
    }

}
