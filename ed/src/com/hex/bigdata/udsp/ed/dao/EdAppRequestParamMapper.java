package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import java.util.List;

public interface EdAppRequestParamMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(EdAppRequestParam record);

    EdAppRequestParam selectByPrimaryKey(String pkId);

    List<EdAppRequestParam> selectAll();

    int updateByPrimaryKey(EdAppRequestParam record);
}