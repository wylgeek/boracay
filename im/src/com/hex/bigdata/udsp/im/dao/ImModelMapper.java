package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.dto.ImModelView;
import com.hex.bigdata.udsp.im.model.ImModel;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ImModelMapper extends SyncMapper<ImModel> {

    @Override
    protected boolean insertExe(ImModel imModel) {
        return sqlSessionTemplate.insert(
                "com.hex.bigdata.udsp.im.dao.ImModelMapper.insert", imModel) == 1;
    }

    @Override
    protected boolean updateExe(ImModel imModel) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.im.dao.ImModelMapper.updateByPrimaryKey",imModel) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.im.dao.ImModelMapper.updateToDeleted",id) == 1;
    }

    @Override
    protected ImModel selectExe(String pkId) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImModelMapper.selectByPrimaryKey",pkId);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<ImModel> selectListExe(String id) {
        return null;
    }

    public List<ImModelView> selectPage(ImModelView imModelView, Page page) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImModelMapper.selectPage",imModelView,page.toPageBounds());
    }

    public ImModel selectByName(String modelName) {
        return  sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImModelMapper.selectByName",modelName);
    }

    public String getModelUpdateModeByName(String updateMode) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImModelMapper.getModelUpdateModeByName",updateMode);
    }

    public String getModelBuildModeByName(String buildMode) {
        return sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.im.dao.ImModelMapper.getModelBuildModeByName",buildMode);
    }

    public boolean updateModelDelStatus(String pkId,String status){
        Map paramter = new HashMap<>();
        paramter.put("pkId",pkId);
        paramter.put("status",status);
        return sqlSessionTemplate.update("com.hex.bigdata.udsp.im.dao.ImModelMapper.updateModelDelStatus",paramter) == 1;
    }

    /**
     * 查询模型配置信息
     * @return
     */
    public List<ImModel> selectAll() {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImModelMapper.selectAll");
    }


}