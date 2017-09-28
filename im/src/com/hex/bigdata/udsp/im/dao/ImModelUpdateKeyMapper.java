package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelUpdateKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImModelUpdateKeyMapper extends SyncMapper<ImModelUpdateKey> {

    @Override
    protected boolean insertExe(ImModelUpdateKey imModelUpdateKey) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImModelUpdateKeyMapper.insert",imModelUpdateKey) == 1;
    }

    @Override
    protected boolean updateExe(ImModelUpdateKey imModelUpdateKey) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelUpdateKey selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String mid) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.im.dao.ImModelUpdateKeyMapper.deleteByMid",mid) >= 0 ;
    }

    @Override
    protected List<ImModelUpdateKey> selectListExe(String id) {
        return null;
    }
}