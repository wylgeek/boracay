package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ImMetadataColMapper extends SyncMapper<ImMetadataCol> {

    @Override
    protected boolean insertExe(ImMetadataCol imMetadataCol) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.insert", imMetadataCol) == 1;
    }

    @Override
    protected boolean updateExe(ImMetadataCol imMetadataCol) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImMetadataCol selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.selectByPrimaryKey",id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.deleteByMdId", id) >= 0;
    }

    @Override
    protected List<ImMetadataCol> selectListExe(String id) {
        return null;
    }

    public List<ImMetadataCol> select(ImMetadataCol imMetadataCol) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.select", imMetadataCol);
    }

    public List<ImMetadataCol> selectModelUpdateKeys(String modelId) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.selectModelUpdateKeys",modelId);
    }

    public ImMetadataCol selectByNameAndMdId(String mdId, String name) {
        Map<String,String> parameters = new HashMap<>(2);
        parameters.put("name",name);
        parameters.put("mdId",mdId);
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImMetadataColMapper.selectByNameAndMdId",parameters);
    }
}