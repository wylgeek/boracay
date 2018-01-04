package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdApplicationParam;
import java.util.List;

public interface EdApplicationParamMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(EdApplicationParam record);

    EdApplicationParam selectByPrimaryKey(String pkId);

    List<EdApplicationParam> selectAll();

    int updateByPrimaryKey(EdApplicationParam record);
}