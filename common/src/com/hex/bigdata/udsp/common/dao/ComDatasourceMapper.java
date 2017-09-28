package com.hex.bigdata.udsp.common.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.common.dto.ComDatasourceView;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComDatasourceMapper extends SyncMapper<ComDatasource> {

    @Override
    protected boolean insertExe(ComDatasource comDatasource) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.insert", comDatasource) == 1;
    }

    @Override
    protected boolean updateExe(ComDatasource comDatasource) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.updateByPrimaryKey", comDatasource) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected ComDatasource selectExe(String id) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectByPrimaryKey", id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ComDatasource> selectListExe(String fkId) {
        return null;
    }

    public List<ComDatasource> selectAll() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectAll");
    }

    public List<ComDatasource> select(ComDatasourceView comDatasourceView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.select", comDatasourceView,
                page.toPageBounds());
    }

    public List<ComDatasource> select(ComDatasourceView comDatasourceView) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.select", comDatasourceView);
    }

    public ComDatasource selectByModelAndName(String model, String name) {
        ComDatasource comDatasource = new ComDatasource();
        comDatasource.setModel(model);
        comDatasource.setName(name);
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectByModelAndName", comDatasource);
    }

    public List<GFDict> selectParameterBySourceId(String sourceId) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectParameterBySourceId", sourceId);
    }

    public List<ComDatasource> selectImSourceDs() {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectImSourceDs");
    }

    public boolean checkSourceType(String sourceId) {

        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.checkSourceType",sourceId).size() > 0;
    }

    public List<ComDatasource> selectEDs() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.common.dao.ComDatasourceMapper.selectEDs");
    }
}