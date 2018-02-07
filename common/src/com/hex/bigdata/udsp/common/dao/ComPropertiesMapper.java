package com.hex.bigdata.udsp.common.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.goframe.util.Util;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComPropertiesMapper extends SyncMapper<ComProperties> {

    @Override
    protected boolean insertExe(ComProperties comProperties) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.insert", comProperties) == 1;
    }

    @Override
    protected boolean updateExe(ComProperties comProperties) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.updateByPrimaryKey", comProperties) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.deleteByPrimaryKey", id) == 1;
    }

    @Override
    protected ComProperties selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.deleteByFkId", id) >= 0;
    }

    @Override
    protected List<ComProperties> selectListExe(String fkId) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.selectByFkId", fkId);
    }

    public List<ComProperties> selectAll() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.common.dao.ComPropertiesMapper.selectAll");
    }

    public List<ComProperties> selectByFkId(String fkId) {
        return this.selectList(fkId);
    }

    public boolean deleteByFkId(String fkId) {
        return this.deleteList(fkId);
    }

    public boolean insertModelComProperties(String pkId, List<ComProperties> comPropertiesList) {

        String comPropertiesId;
        for(ComProperties comProperties : comPropertiesList){
            comProperties.setFkId(pkId);
            comPropertiesId = Util.uuid();
            comProperties.setPkId(comPropertiesId);
            if(!insert(comPropertiesId,comProperties)){
                return false;
            }
        }
        return true;
    }
}