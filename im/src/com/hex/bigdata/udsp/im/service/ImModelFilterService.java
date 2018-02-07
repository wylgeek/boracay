package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.im.dao.ImModelFilterColMapper;
import com.hex.bigdata.udsp.im.model.ImModelFilterCol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jintian on 2017/9/11.
 */
@Service
public class ImModelFilterService {

    @Autowired
    private ImModelFilterColMapper imModelFilterColMapper;

    public List<ImModelFilterCol> getImModelFiltersByMid(String mid){
        return  imModelFilterColMapper.selectList(mid);
    }
}
