package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:19
 */
@Service
public class InterfaceInfoService {
    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;

    public InterfaceInfo getInterfaceInfoByPkId(String pkId) {
        return interfaceInfoMapper.getInterfaceInfoByPkId(pkId);
    }

    public List<InterfaceInfo> getInterfaceInfoList(InterfaceInfoDto interfaceInfoDto, Page page) {
        return interfaceInfoMapper.getInterfaceInfoList(interfaceInfoDto, page);
    }

    public InterfaceInfo getInterfaceInfoByInterfaceCode(String interfaceCode) {
        return interfaceInfoMapper.getInterfaceInfoByInterfaceCode(interfaceCode);
    }

    public MessageResult addInterfaceInfo(InterfaceInfo interfaceInfo) {
        //检查是否已经存在
        InterfaceInfo interfaceInfo1 = getInterfaceInfoByInterfaceCode(interfaceInfo.getInterfaceCode());
        if (interfaceInfo1 != null) {
            return new MessageResult(false, "服务编码已存在，请重新输入！");
        }

        //添加数据
        interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        if (!StringUtils.isBlank(interfaceInfo.getReqUrl())) {
            interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        }
        interfaceInfo.setPkId(Util.uuid());
        interfaceInfo.setCrtUser(WebUtil.getCurrentUserId());
        interfaceInfo.setCrtTime(new Date());
        int result = interfaceInfoMapper.addInterfaceInfo(interfaceInfo);

        //校验返回结果
        if (result != 1) {
            return new MessageResult(false, "添加数据失败，请重试！");
        }
        return new MessageResult(true, "添加数据成功！");
    }

    public MessageResult updateInterfaceInfoByPkId(InterfaceInfo interfaceInfo) {
        if (!StringUtils.isBlank(interfaceInfo.getInterfaceCode())) {
            interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        }
        if (!StringUtils.isBlank(interfaceInfo.getReqUrl())) {
            interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        }

        interfaceInfo.setUpdateUser(WebUtil.getCurrentUserId());
        interfaceInfo.setUpdateTime(new Date());
        int result = interfaceInfoMapper.updateInterfaceInfoByPkId(interfaceInfo);
        if (result != 1) {
            return new MessageResult(false, "更新数据失败！");
        }
        return new MessageResult(true, "更新数据成功！");
    }

    @Transient
    public MessageResult deleteInterfaceInfo(InterfaceInfo[] interfaceInfos) {
        int count = 0;
        for (InterfaceInfo interfaceInfo : interfaceInfos) {
            int result = interfaceInfoMapper.deleteInterfaceInfo(interfaceInfo.getPkId());
            if (result == 1) {
                count++;
            }
        }
        if (count != interfaceInfos.length) {
            return new MessageResult(false, "删除失败，请重试！");
        }
        return new MessageResult(true, "删除成功！");
    }
}
