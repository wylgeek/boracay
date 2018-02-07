package com.hex.bigdata.udsp.olq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.olq.model.OlqApplicationParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OlqApplicationParamMapper extends SyncMapper<OlqApplicationParam> {
    @Override
    protected boolean insertExe(OlqApplicationParam olqApplicationParam) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.olq.dao.OlqApplicationParamMapper.insert", olqApplicationParam) == 1;
    }

    @Override
    protected boolean updateExe(OlqApplicationParam olqApplicationParam) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected OlqApplicationParam selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String id) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.olq.dao.OlqApplicationParamMapper.deleteListByAppId", id) >= 0;
    }

    @Override
    protected List<OlqApplicationParam> selectListExe(String id) {
        return this.selectByAppId(id);
    }


    protected List<OlqApplicationParam> selectByAppId(String olqAppId) {
        OlqApplicationParam applicationParam = new OlqApplicationParam();
        applicationParam.setOlqAppId(olqAppId);
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OlqApplicationParamMapper.selectByCondition", applicationParam);

    }
}