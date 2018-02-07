package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/4
 * Time:20:40
 */
@Repository
public class EdInterfaceParamMapper extends BaseMapper {
    public int deleteByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.deleteByPrimaryKey",
                pkId);
    }

    public int insert(EdInterfaceParam record) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.insert",
                record);
    }

    public int updateByPrimaryKey(EdInterfaceParam record) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.updateByPrimaryKey",
                record);
    }

    public EdInterfaceParam selectByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectByPrimaryKey",
                pkId);
    }

    public List<EdInterfaceParam> selectAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectAll");
    }

    public List<EdInterfaceParam> selectList(EdInterfaceParam record,Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectList",
                record,page.toPageBounds());
    }

    public List<EdInterfaceParam> select(EdInterfaceParam record) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.select",
                record);
    }

    public List<EdInterfaceParam> selectByInterfaceId(String interfaceId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectByInterfaceId",
                interfaceId);
    }

    public int deleteByInterfaceId(String interfaceId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.deleteByInterfaceId",
                interfaceId);
    }

    public List<EdInterfaceParam> selectList(EdInterfaceParam record) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectList",
                record);
    }

    public List<EdInterfaceParam> selectByInterfaceIdAndType(EdInterfaceParam record) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdInterfaceParamMapper.selectByInterfaceIdAndType",
                record);
    }
}
