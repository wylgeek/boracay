package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.im.dao.ImMetadataColMapper;
import com.hex.bigdata.udsp.im.dao.ImMetadataMapper;
import com.hex.bigdata.udsp.im.dao.ImModelMappingMapper;
import com.hex.bigdata.udsp.im.model.ImMetadataCol;
import com.hex.bigdata.udsp.im.model.ImModelMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jintian on 2017/9/11.
 */
@Service
public class ImModelMappingService {

    @Autowired
    private ImModelMappingMapper imModelMappingMapper;

    @Autowired
    private ImMetadataColMapper imMetadataColMapper;

    public List<ImModelMapping> getImModelMappingsByMid(String mid) {

        return imModelMappingMapper.selectByModelId(mid);
    }

    public List<ImMetadataCol> getImMMappingTargetCols(String mid) {
        return imMetadataColMapper.getImMMappingTargetCols(mid);
    }
}
