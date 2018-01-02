package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelMapping;
import com.hex.goframe.util.Util;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImModelMappingMapper  extends SyncMapper<ImModelMapping> {

    @Override
    protected boolean insertExe(ImModelMapping imModelMapping) {
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImModelMappingMapper.insert",imModelMapping) == 1;
    }

    @Override
    protected boolean updateExe(ImModelMapping imModelMapping) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelMapping selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String mid) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.im.dao.ImModelMappingMapper.deleteByMid",mid) >= 0 ;
    }

    @Override
    protected List<ImModelMapping> selectListExe(String mid) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImModelMappingMapper.selectByMid",mid);
    }

    public boolean insertModelMappings(String pkId, List<ImModelMapping> imModelMappings) {

        String imModelMappingId;
        for(ImModelMapping imModelMapping : imModelMappings){
            imModelMappingId = Util.uuid();
            imModelMapping.setModelId(pkId);
            imModelMapping.setPkId(imModelMappingId);
            //是否索引
            if("true".equals(imModelMapping.getIndexed()) || "是".equals(imModelMapping.getIndexed()) || "0".equals(imModelMapping.getIndexed())){
                imModelMapping.setIndexed("0");
            }else{
                imModelMapping.setIndexed("1");
            }

            //是否主键
            if("true".equals(imModelMapping.getPrimary()) || "是".equals(imModelMapping.getPrimary()) || "0".equals(imModelMapping.getPrimary())){
                imModelMapping.setPrimary("0");
            }else{
                imModelMapping.setPrimary("1");
            }

            //是否存储
            if("true".equals(imModelMapping.getStored()) || "是".equals(imModelMapping.getStored()) || "0".equals(imModelMapping.getStored())){
                imModelMapping.setStored("0");
            }else{
                imModelMapping.setStored("1");
            }

            //一个失败则视为失败
            if(!insert(imModelMappingId,imModelMapping)){
                return false;
            }
        }
        return true;
    }

    public List<ImModelMapping> selectByModelId(String mId) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImModelMappingMapper.selectByModelId",mId);
    }
}