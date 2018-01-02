package com.hex.bigdata.udsp.rc.dao;


import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.rc.dto.RcServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RcServiceMapper extends SyncMapper<RcService> {

    @Override
    protected boolean insertExe(RcService rcService) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.insert", rcService) == 1;
    }

    @Override
    protected boolean updateExe(RcService rcService) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.updateByPrimaryKey", rcService) == 1;
    }

    @Override
    protected boolean deleteExe(String id) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.deleteByPrimaryKeyFake", id) == 1;
    }

    @Override
    protected RcService selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByPrimaryKey", id);
    }


    @Override
    protected boolean deleteListExe(String id) {
        return false;
    }

    @Override
    protected List<RcService> selectListExe(String fkId) {
        return null;
    }

    /**
     * @param rcServiceView
     * @param page
     * @return
     */
    public List<RcServiceView> selectPage(RcServiceView rcServiceView, Page page) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectPage", rcServiceView,
                page.toPageBounds());
    }

    /**
     * 根据名称查找
     *
     * @param name
     * @return
     */
    public RcService selectByName(String name) {
        return sqlSessionTemplate.selectOne(
                "com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByName", name);
    }

    /**
     * 根据服务类型查询服务
     *
     * @param serviceType 服务类型
     * @return
     */
    public List selectByType(String serviceType) {
        return sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByType", serviceType);
    }

    /**
     * 根据服务类型查询服务名称
     *
     * @param serviceType 服务类型
     * @return
     */
    public RcService selectAuthInfo(String serviceType) {
        return sqlSessionTemplate.selectOne(
                "com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectAuthInfo", serviceType);
    }

    /**
     * 根据应用名称和应用类型查找对应的服务注册信息
     *
     * @param type
     * @param appId
     * @return
     */
    public RcService selectByAppTypeAndAppId(String type, String appId) {
        RcService rcService = new RcService();
        rcService.setType(type);
        rcService.setAppId(appId);
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectByAppTypeAndAppId", rcService);
    }

    /**
     * 根据应用名称和应用类型查找对应的启用的服务注册信息
     *
     * @param type
     * @param appId
     * @return
     */
    public RcService selectStartByAppTypeAndAppId(String type, String appId) {
        RcService rcService = new RcService();
        rcService.setType(type);
        rcService.setAppId(appId);
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.selectStartByAppTypeAndAppId", rcService);
    }

    /**
     * 修改服务状态
     *
     * @param item
     * @return
     */
    @Transactional
    public boolean statusChange(RcService item) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.rc.dao.RcServiceMapper.updateStatus", item) == 1;
    }
}