package com.hex.bigdata.udsp.mc.dao;

import com.hex.bigdata.udsp.common.dao.base.AsyncInsertMapper;
import com.hex.bigdata.udsp.mc.dto.McConsumeLogView;
import com.hex.bigdata.udsp.mc.model.McConsumeLog;
import com.hex.goframe.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 */
@Repository
public class McConsumeLogMapper extends AsyncInsertMapper<McConsumeLog> {

    /**
     * 后台起线程异步插入
     */
    @Override
    protected boolean insertExe(McConsumeLog mcConsumeLog) {
        //输入内容过长处理
        String requestContent = mcConsumeLog.getRequestContent();
        if(StringUtils.isNotBlank(requestContent) && requestContent.length() > 4000) {
            mcConsumeLog.setRequestContent(requestContent.substring(0,4000));
        }
        try {
            return this.sqlSessionTemplate.insert("com.hex.bigdata.udsp.mc.dao.McConsumeLogMapper.insert", mcConsumeLog) == 1;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }

    @Override
    protected McConsumeLog selectExe(String id) {
        return this.sqlSessionTemplate.selectOne("com.hex.bigdata.udsp.mc.dao.McConsumeLogMapper.selectByPrimaryKey", id);
    }

    public List<McConsumeLog> select(McConsumeLogView mcConsumeLogView, Page page) {
        return this.sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mc.dao.McConsumeLogMapper.select", mcConsumeLogView,
                page.toPageBounds());
    }

    public List<McConsumeLog> select(McConsumeLogView mcConsumeLogView) {
        return this.sqlSessionTemplate.selectList(
                "com.hex.bigdata.udsp.mc.dao.McConsumeLogMapper.select", mcConsumeLogView);
    }
}