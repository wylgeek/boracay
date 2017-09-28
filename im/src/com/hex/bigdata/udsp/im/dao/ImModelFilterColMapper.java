package com.hex.bigdata.udsp.im.dao;

import com.hex.bigdata.udsp.common.dao.base.SyncMapper;
import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import com.hex.goframe.util.Util;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ImModelFilterColMapper  extends SyncMapper<ImModelFilterCol> {

    @Override
    protected boolean insertExe(ImModelFilterCol imModelFilterCol) {
        String isNeed = imModelFilterCol.getIsNeed();
        //转译并给予默认值
        if(StringUtils.isBlank(isNeed) || isNeed.equals("否")){
            imModelFilterCol.setIsNeed("1");
        }else if(isNeed.equals("是")){
            imModelFilterCol.setIsNeed("0");
        }
        //给予默认值
        if(StringUtils.isBlank(imModelFilterCol.getLabel())) imModelFilterCol.setLabel(imModelFilterCol.getName());
        return sqlSessionTemplate.insert("com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper.insert",imModelFilterCol) == 1;
    }

    @Override
    protected boolean updateExe(ImModelFilterCol imModelFilterCol) {
        return false;
    }

    @Override
    protected boolean deleteExe(String id) {
        return false;
    }

    @Override
    protected ImModelFilterCol selectExe(String id) {
        return null;
    }

    @Override
    protected boolean deleteListExe(String mid) {
        return sqlSessionTemplate.delete("com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper.deleteByMid",mid) >= 0;
    }

    @Override
    protected List<ImModelFilterCol> selectListExe(String mid) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper.selectByMid",mid);
    }

    public boolean insertFilterCols(String pkId, List<ImModelFilterCol> imModelFilterCols) {
        String imModelFilterPkid = "";
        for(ImModelFilterCol imModelFilterCol:imModelFilterCols){
            imModelFilterCol.setModelId(pkId);

            imModelFilterPkid = Util.uuid();
            imModelFilterCol.setPkId(imModelFilterPkid);
            // 当有一个插入失败则算做失败
            if(!insert(imModelFilterPkid,imModelFilterCol)){
                return  false;
            }
        }
        return true;
    }
}