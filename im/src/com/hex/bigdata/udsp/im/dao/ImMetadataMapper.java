package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImMetadata;
import com.hex.bigdata.udsp.im.dto.ImMetadataView;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImMetadataMapper extends SyncMapper<ImMetadata> {
    @Override
    protected boolean insertExe(ImMetadata imMetadata) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.insert", imMetadata) == 1;
    }

    public List<ImMetadataView> select(ImMetadataView imMetadataView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.im.dao.ImMetadataMapper.select", imMetadataView,
                page.toPageBounds());
    }

    public ImMetadata selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.selectByName", name);
    }

    @Override
    protected boolean updateExe(ImMetadata imMetadata) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.updateByPrimaryKey", imMetadata) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected ImMetadata selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImMetadata> selectListExe(String id) {
        return null;
    }

    public List<ImMetadata> selectAll() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.selectAll");
    }

    public List<ImMetadata> selectTargetMateData(String tgctId) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImMetadataMapper.selectTargetMateData",tgctId);
    }
}