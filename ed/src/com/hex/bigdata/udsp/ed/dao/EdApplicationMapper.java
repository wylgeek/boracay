package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdApplication;
import java.util.List;

public interface EdApplicationMapper {
    int deleteByPrimaryKey(String pkId);

    int insert(EdApplication record);

    EdApplication selectByPrimaryKey(String pkId);

    List<EdApplication> selectAll();

    int updateByPrimaryKey(EdApplication record);
}