package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.goframe.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/4
 * Time:19:43
 */
@Repository
public class EdAppResponseParamMapper extends BaseMapper {
    public int deleteByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.deleteByPrimaryKey",
                pkId);
    }

    public int updateByPrimaryKey(EdAppResponseParam edAppResponseParam) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.updateByPrimaryKey",
                edAppResponseParam);
    }

    public EdAppResponseParam selectByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.selectByPrimaryKey",
                pkId);
    }

    public List<EdAppResponseParam> selectAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.selectAll");
    }

    public int addEdAppResponseParam(EdAppResponseParam edAppResponseParam) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.addEdAppResponseParam",
                edAppResponseParam);
    }


    public List<EdAppResponseParam> getEdAppResponseParamByAppId(String appId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.getEdAppResponseParamByAppId",
                appId);
    }

    public int deleteEdAppResponseParamByAppId(String appId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdAppResponseParamMapper.deleteEdAppResponseParamByAppId",
                appId);
    }
}
