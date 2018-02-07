package com.hex.bigdata.udsp.olq.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.olq.dto.OlqApplicationView;
import com.hex.bigdata.udsp.olq.model.OlqApplication;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OlqApplicationMapper extends SyncMapper<OlqApplication> {

    @Override
    protected boolean insertExe(OlqApplication olqApplication) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.insert", olqApplication) == 1;
    }

    @Override
    protected boolean updateExe(OlqApplication olqApplication) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.updateByPrimaryKey",olqApplication) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.deleteByPrimaryKeyFake",id) == 1;
    }

    @Override
    protected OlqApplication selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.selectByPrimaryKey",id);
    }

    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<OlqApplication> selectListExe(String id) {
        return null;
    }

    /**
     * 分页模糊查询
     *
     * @param olqApplicationView
     * @param page
     * @return
     */
    public List<OlqApplicationView> selectPage(OlqApplicationView olqApplicationView, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.selectPage", olqApplicationView, page.toPageBounds());
    }

    /**
     * 根据应用名称进行精确查找
     * @param name
     * @return
     */
    public OlqApplication selectByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.selectByName",name);
    }

    /**
     * 查找所有存在的联机查询应用
     * @return
     */
    public List<OlqApplication> selectAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.olq.dao.OlqApplicationMapper.selectAll");
    }
}