package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.im.dao.ImMetadataColMapper;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hj on 2017-9-5.
 */
@Service
public class ImMetadataColService extends BaseService {
    @Autowired
    private ImMetadataColMapper imMetadataColMapper;

    @Transactional
    public String insert(ImMetadataCol imMetadataCol) {
        String pkId = Util.uuid();
        imMetadataCol.setPkId(pkId);
        if (imMetadataColMapper.insert(imMetadataCol.getPkId(), imMetadataCol)) {
            return pkId;
        }
        return "";
    }

    public List<ImMetadataCol> select(String mdId) {
        ImMetadataCol imMetadataCol = new ImMetadataCol();
        imMetadataCol.setMdId(mdId);
        return imMetadataColMapper.select(imMetadataCol);
    }

    @Transactional
    public boolean deleteByMdId(String mdId) {
        return imMetadataColMapper.deleteList(mdId);
    }

    public List<ImMetadataCol> selectModelUpdateKeys(String modelId) {
        return imMetadataColMapper.selectModelUpdateKeys(modelId);
    }
}

