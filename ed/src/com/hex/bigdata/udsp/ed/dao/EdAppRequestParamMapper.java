package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.goframe.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/4
 * Time:19:42
 */
@Repository
public class EdAppRequestParamMapper extends BaseMapper {
    public int deleteByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.deleteByPrimaryKey",
                pkId);
    }

    public int updateByPrimaryKey(EdAppRequestParam edAppRequestParam) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.updateByPrimaryKey",
                edAppRequestParam);
    }

    public EdAppRequestParam selectByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.selectByPrimaryKey",
                pkId);
    }

    public List<EdAppRequestParam> selectAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.selectAll");
    }

    public int addEdAppRequestParam(EdAppRequestParam edAppRequestParam) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.addEdAppRequestParam",
                edAppRequestParam);
    }

    public List<EdAppRequestParam> getEdAppRequestParamByAppId(String appId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.getEdAppRequestParamByAppId",
                appId);
    }

    public int deleteEdAppRequestParamByAppId(String appId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdAppRequestParamMapper.deleteEdAppRequestParamByAppId",
                appId);
    }
}
