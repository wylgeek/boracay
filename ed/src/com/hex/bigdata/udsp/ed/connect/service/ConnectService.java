package com.hex.bigdata.udsp.ed.connect.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.bigdata.udsp.ed.connect.util.RestTemplateUtil;
import com.hex.bigdata.udsp.ed.constant.ConnectCheck;
import com.hex.bigdata.udsp.ed.constant.ConnectIsNeed;
import com.hex.bigdata.udsp.ed.constant.InterfaceType;
import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.model.EdInterfaceCount;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.ed.service.EdAppRequestParamService;
import com.hex.bigdata.udsp.ed.service.EdInterfaceCountService;
import com.hex.bigdata.udsp.ed.service.InterfaceInfoService;
import com.hex.goframe.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jc.zhao
 * Date:2018/1/9
 * Time:13:38
 */
@Service
public class ConnectService {
    @Autowired
    private DataStoreService dataStoreService;

    @Autowired
    private InterfaceInfoService interfaceInfoService;

    @Autowired
    private EdAppRequestParamService edAppRequestParamService;

    @Autowired
    private EdInterfaceCountService edInterfaceCountService;

    /**
     * 取数据统一入口
     * @param serviceName
     * @param reqTemp
     * @param edApplication
     * @param udspUser
     * @return
     * @throws Exception
     */
    public String getData(String serviceName, Map reqTemp, EdApplication edApplication, String udspUser) throws Exception {
        String appId = edApplication.getPkId();
        //检查必须参数是否齐全
        if (ConnectCheck.CHECK.getValue().equals(edApplication.getIsCheck())) {
            List<EdAppRequestParam> reqList = edAppRequestParamService.getEdAppRequestParamByAppId(edApplication.getPkId());
            for (EdAppRequestParam edAppRequestParam : reqList) {
                if (ConnectIsNeed.NEED.getValue().equals(edAppRequestParam.getIsNeed())) {
                    Object value = reqTemp.get(edAppRequestParam.getName());
                    if (value == null) {
                        return "";
                    }
                }
            }
        }

        Map reqParam = new HashMap();
        reqParam.put("data",reqTemp);
        reqParam.put("serviceName",serviceName);
        //将输入参数转成json字符串
        String req = JSON.toJSON(reqParam).toString();

        //获取接口信息
        InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByPkId(edApplication.getInterfaceId());

        //判断访问方式，获取返回参数
        //记录到流水表中
        String returnJson = "";
        if (InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
            //从缓存获取数据
            returnJson = getDateFromCache(req, interfaceInfo, appId, udspUser);
        } else {
            //实时访问数据
            returnJson = getDataFromRemote(req, interfaceInfo, appId, udspUser);
        }
        return returnJson;
    }

    /**
     * 从connector层获取数据
     * @param reqParam
     * @param interfaceInfo
     * @param appId
     * @param udspUser
     * @return
     * @throws Exception
     */
    public String getDataFromRemote(String reqParam, InterfaceInfo interfaceInfo, String appId, String udspUser) throws Exception {
        //远程访问需要记录到表，方便统计
        this.insertCount(reqParam, appId, udspUser);
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        String returnJson = restTemplateUtil.post(reqParam, interfaceInfo.getReqUrl());
        return returnJson;
    }

    /**
     * 从cache获取数据
     * 当数据超过保质期后，调用【getDataFromSourceAndSave】方法，获取数据并保存到Hbase
     * @param reqParam
     * @param interfaceInfo
     * @param appId
     * @param udspUser
     * @return
     * @throws Exception
     */
    public String getDateFromCache(String reqParam, InterfaceInfo interfaceInfo, String appId, String udspUser) throws Exception {
        String interfaceCode = interfaceInfo.getInterfaceCode();

        //根据接口编码和请求参数的json字符串生成rowId
        String rowIdLeft = MD5Util.MD5_16(interfaceCode + reqParam);
        long currentTime = new Date().getTime();
        String rowIdRight = StringUtils.leftPad(Long.toString(currentTime),13,'0');
        String rowId = rowIdLeft + rowIdRight;

        //表名
        String tableName = interfaceCode;

        //保质期。单位：毫秒
        long validTime = interfaceInfo.getValidTime() * 1000 * 60;

        String returnStr = dataStoreService.getDataList(tableName,rowId,validTime);
        if(StringUtils.isBlank(returnStr)) {
            return getDataFromSourceAndSave(reqParam, interfaceInfo, tableName, rowId, appId, udspUser);
        } else {
            return returnStr;
        }
    }

    /**
     * 从connector获取数据，同时保存到Hbase
     * @param reqParam
     * @param interfaceInfo
     * @param tableName
     * @param rowId
     * @param appId
     * @param udspUser
     * @return
     * @throws Exception
     */
    public String getDataFromSourceAndSave(String reqParam, InterfaceInfo interfaceInfo, String tableName, String rowId,
                                           String appId, String udspUser) throws Exception {
        String data = this.getDataFromRemote(reqParam, interfaceInfo, appId, udspUser);

        //存到缓存,仅有查到数据，同时查询结果正常时存入缓存中
        byte[] dataAsByte;
        if(StringUtils.isBlank(data)){
            return data;
        } else {
            // 处理远程查询结果
            // 如果查询出现错误，则不需要存入缓存中
            Map map = JSONObject.parseObject(data,Map.class);
            if(StatusCode.DEFEAT.getValue().equals(map.get("statusCode").toString())) {
                return data;
            }
            dataAsByte = data.getBytes("UTF-8");
        }
        byte[] paramAsByte = reqParam.getBytes("UTF-8");
        dataStoreService.putData(tableName, rowId, dataAsByte, paramAsByte);
        return data;
    }

    /**
     * 创建表（每一个接口创建一个表）
     * 在接口信息创建的时候调用
     * @param storeName
     * @return
     */
    public Boolean createTable(String storeName) {
        return dataStoreService.createTable(storeName);
    }

    /**
     * 远程访问日志记录
     * @param reqParam
     * @param appId
     * @param udspUser
     */
    public void insertCount(String reqParam, String appId, String udspUser) {
        EdInterfaceCount edInterfaceCount = new EdInterfaceCount();
        edInterfaceCount.setAppId(appId);
        edInterfaceCount.setReqUser(udspUser);
        edInterfaceCount.setReqParam(reqParam);
        edInterfaceCountService.insert(edInterfaceCount);
    }

}
