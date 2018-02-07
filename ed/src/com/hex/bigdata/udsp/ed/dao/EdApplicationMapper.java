package com.hex.bigdata.udsp.ed.dao;

import com.hex.bigdata.udsp.ed.dto.EdApplicationDto;
import com.hex.bigdata.udsp.ed.dto.EdApplicationExlDto;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.model.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/4
 * Time:19:41
 */
@Repository
public class EdApplicationMapper extends BaseMapper {
    public int deleteByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.delete("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.deleteByPrimaryKey",
                pkId);
    }

    public int addEdApplication(EdApplication edApplication) {
        return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.addEdApplication",
                edApplication);
    }

    public int updateEdApplication(EdApplication edApplication) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.updateEdApplication",
                edApplication);
    }

    public int deleteEdApplication(EdApplication edApplication) {
        return this.sqlSessionTemplate.update("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.deleteEdApplication",
                edApplication);
    }

    public EdApplication selectByPrimaryKey(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.selectByPrimaryKey",
                pkId);
    }

    public List<EdApplication> getEdApplicationAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.getEdApplicationAll");
    }

    public List<EdApplicationDto> getEdApplicationInfoPage(EdApplicationDto edApplicationDto, Page page) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.getEdApplicationInfoPage",
                edApplicationDto, page.toPageBounds());
    }

    public EdApplication getEdApplicationByName(String name) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.getEdApplicationByName",
                name);
    }

    public EdApplicationExlDto getEdApplicationExl(String pkId) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.getEdApplicationExl",
                pkId);
    }

    public List selectEnableAll() {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.getEdApplicationAll");
    }

    public List<EdApplication> selectByInterfaceId(String interfaceId) {
        return this.sqlSessionTemplate.selectList("com.hex.bigdata.udsp.ed.dao.EdApplicationMapper.selectByInterfaceId",
                interfaceId);
    }
}
