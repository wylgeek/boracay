package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import java.util.List;

public interface EdAppResponseParamMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(EdAppResponseParam record);

    EdAppResponseParam selectByPrimaryKey(String pkId);

    List<EdAppResponseParam> selectAll();

    int updateByPrimaryKey(EdAppResponseParam record);
}