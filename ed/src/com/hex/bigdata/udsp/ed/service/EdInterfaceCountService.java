package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.EdInterfaceCountMapper;
import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountReqDto;
import com.hex.bigdata.udsp.ed.dto.EdInterfaceCountResDto;
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
import java.util.List;

@Service
public class EdInterfaceCountService extends BaseService {

    @Autowired
    private EdInterfaceCountMapper edInterfaceCountMapper;

    /**
     * 插入远程访问日志
     * @param edInterfaceCount
     * @return
     */
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

    /**
     * 获取接口访问统计信息
     * @param edInterfaceCountReqDto
     * @return
     */
    public List<EdInterfaceCountResDto> selectCountInfo(EdInterfaceCountReqDto edInterfaceCountReqDto){
        List<EdInterfaceCountResDto> list = edInterfaceCountMapper.selectCountInfo(edInterfaceCountReqDto);
        for(EdInterfaceCountResDto edInterfaceCountResDto:list){
            int totalCount = edInterfaceCountResDto.getReqFailedCount() + edInterfaceCountResDto.getReqSuccessCount();
            int cacheCount = totalCount - edInterfaceCountResDto.getRemoteCount();
            edInterfaceCountResDto.setReqTotalCount(totalCount);
            edInterfaceCountResDto.setCacheCount(cacheCount);
        }
        return list;
    }

}
