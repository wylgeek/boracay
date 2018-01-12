package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.ed.connect.service.ConnectService;
import com.hex.bigdata.udsp.ed.constant.RcServiceStatus;
import com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoParamDto;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EdInterfaceParamService edInterfaceParamService;

    @Autowired
    private EdApplicationService edApplicationService;

    @Autowired
    private RcServiceService rcServiceService;

    @Autowired
    private ConnectService connectService;


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
        String pkId = Util.uuid();
        interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        interfaceInfo.setPkId(pkId);
        interfaceInfo.setCrtUser(WebUtil.getCurrentUserId());
        interfaceInfo.setCrtTime(new Date());
        int result = interfaceInfoMapper.addInterfaceInfo(interfaceInfo);

        //校验返回结果
        if (result != 1) {
            return new MessageResult(false, "添加数据失败，请重试！");
        }
        MessageResult messageResult = new MessageResult();
        messageResult.setStatus(true);
        messageResult.setMessage(pkId);
        return messageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageResult updateInterfaceInfoByPkId(InterfaceInfoParamDto interfaceInfoParamDto) throws Exception {
        //判断服务编码是否修改
        InterfaceInfo interfaceInfo = this.getInterfaceInfoByPkId(interfaceInfoParamDto.getInterfaceInfo().getPkId());
        if(!interfaceInfo.getInterfaceCode().equals(interfaceInfoParamDto.getInterfaceInfo().getInterfaceCode())){
            //在hbase中创建对应的表，如果表已经存在则返回false，正常创建则返回true，不成功抛出异常
            connectService.createTable(interfaceInfoParamDto.getInterfaceInfo().getInterfaceCode());
        }
        String interfaceId = interfaceInfoParamDto.getInterfaceInfo().getPkId();
        String str = this.checkRcService(interfaceId);
        if (!"".equals(str)) {
            return new MessageResult(false, str + "服务正在使用，请停止服务后修改！");
        }

        edInterfaceParamService.deleteByInterfaceId(interfaceId);
        MessageResult messageResult1 = updateInterfaceInfoByPkId(interfaceInfoParamDto.getInterfaceInfo());
        MessageResult messageResult2 = edInterfaceParamService.insertRequestColList(interfaceId, interfaceInfoParamDto.getEdInterfaceParamsRequest());
        MessageResult messageResult3 = edInterfaceParamService.insertResponseColList(interfaceId, interfaceInfoParamDto.getEdInterfaceParamsResponse());

        if (messageResult1.isStatus() && messageResult2.isStatus() && messageResult3.isStatus()) {
            return new MessageResult(true, "修改成功");
        } else {
            throw new Exception();
        }
    }

    public MessageResult updateInterfaceInfoByPkId(InterfaceInfo interfaceInfo) {
        //检查是否已经存在
        InterfaceInfo interfaceInfo1 = getInterfaceInfoByInterfaceCode(interfaceInfo.getInterfaceCode());
        if (interfaceInfo1 != null && !interfaceInfo.getPkId().equals(interfaceInfo1.getPkId())) {
            return new MessageResult(false, "服务编码已存在，请重新输入！");
        }

        interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        interfaceInfo.setUpdateUser(WebUtil.getCurrentUserId());
        interfaceInfo.setUpdateTime(new Date());
        int result = interfaceInfoMapper.updateInterfaceInfoByPkId(interfaceInfo);
        if (result != 1) {
            return new MessageResult(false, "更新数据失败！");
        }
        return new MessageResult(true, "更新数据成功！");
    }

    @Transactional(rollbackFor = Exception.class)
    public MessageResult deleteInterfaceInfo(InterfaceInfo[] interfaceInfos) throws Exception {
        String str = "";
        for (InterfaceInfo interfaceInfo : interfaceInfos) {
            String interfaceId = interfaceInfo.getPkId();
            str = str + this.checkRcService(interfaceId);
        }
        if(!"".equals(str)){
            return new MessageResult(false,str+"服务正在使用，请停止服务后修改！");
        }

        for (InterfaceInfo interfaceInfo : interfaceInfos) {
            int result1 = interfaceInfoMapper.deleteInterfaceInfo(interfaceInfo.getPkId());
            int result2 = edInterfaceParamService.deleteByInterfaceId(interfaceInfo.getPkId());
            if (result1 == 0 && result2 <= 0) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "删除成功！");
    }

    /**
     * 保存接口配置
     *
     * @param interfaceInfoParamDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult addInterfaceInfo(InterfaceInfoParamDto interfaceInfoParamDto) throws Exception {
        //在hbase中创建对应的表，如果表已经存在则返回false，正常创建则返回true，不成功抛出异常
        connectService.createTable(interfaceInfoParamDto.getInterfaceInfo().getInterfaceCode());
        //数据库中添加信息
        MessageResult messageResult1 = this.addInterfaceInfo(interfaceInfoParamDto.getInterfaceInfo());
        if (messageResult1.isStatus()) {
            MessageResult messageResult2 = edInterfaceParamService.insertRequestColList(messageResult1.getMessage(),
                    interfaceInfoParamDto.getEdInterfaceParamsRequest());
            MessageResult messageResult3 = edInterfaceParamService.insertResponseColList(messageResult1.getMessage(),
                    interfaceInfoParamDto.getEdInterfaceParamsResponse());
            if (messageResult2.isStatus() && messageResult3.isStatus()) {
                return new MessageResult(true, "保存成功");
            }
            throw new Exception();
        }
        return new MessageResult(false, "保存失败");
    }

    public List<InterfaceInfo> getInterfaceInfoList() {
        return interfaceInfoMapper.getInterfaceInfoList();
    }

    /**
     * 校验服务是否正在运行
     * @param interfaceId
     * @return
     */
    public String checkRcService(String interfaceId) {
        //校验服务是否正在运行
        String str = "";
        List<EdApplication> edApplications = edApplicationService.selectByInterfaceId(interfaceId);
        for (EdApplication edApplication : edApplications) {
            String appId = edApplication.getPkId();
            RcService rcService = rcServiceService.selectByAppTypeAndAppId("ED", appId);
            if (RcServiceStatus.START.getValue().equals(rcService.getStatus())) {
                String name = edApplication.getName();
                String desc = edApplication.getDescribe();
                str = str + "["+name+":" + desc+"];";
            }
        }
        return str;
    }
}
