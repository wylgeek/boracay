package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.EdInterfaceCountMapper;
import com.hex.bigdata.udsp.ed.model.EdInterfaceCount;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EdInterfaceCountService extends BaseService {

    @Autowired
    private EdInterfaceCountMapper edInterfaceCountMapper;

    public Boolean insert(EdInterfaceCount edInterfaceCount) {
        String pkId = Util.uuid();
        edInterfaceCount.setPkId(pkId);
        edInterfaceCount.setReqTime(new Date());
        int result = edInterfaceCountMapper.insert(edInterfaceCount);
        if(result == 1) {
            return true;
        }
        return false;
    }

}
