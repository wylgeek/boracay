package com.hex.bigdata.udsp.ed.connect.service;

import com.alibaba.fastjson.JSON;
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
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
     *
     * @param reqParam
     * @param edApplication
     * @return
     */
    public String getData(Map reqParam, EdApplication edApplication) throws UnsupportedEncodingException {
        String appId = edApplication.getPkId();
        //检查必须参数是否齐全
        if (ConnectCheck.CHECK.getValue().equals(edApplication.getIsCheck())) {
            List<EdAppRequestParam> reqList = edAppRequestParamService.getEdAppRequestParamByAppId(edApplication.getPkId());
            for (EdAppRequestParam edAppRequestParam : reqList) {
                if (ConnectIsNeed.NEED.getValue().equals(edAppRequestParam.getIsNeed())) {
                    Object value = reqParam.get(edAppRequestParam.getName());
                    if (value == null) {
                        return "";
                    }
                }
            }
        }

        //将输入参数转成json字符串
        String req = JSON.toJSON(reqParam).toString();

        //获取接口信息
        InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfoByPkId(edApplication.getInterfaceId());

        //判断访问方式，获取返回参数
        //记录到流水表中
        String returnJson = "";
        if (InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
            //从缓存获取数据
            returnJson = getDateFromCache(req, interfaceInfo,appId);
        } else if (InterfaceType.DIRECT_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
            //实时访问数据
            returnJson = getDataFromRemote(req, interfaceInfo,appId);
        }
        return returnJson;
    }

    /**
     * 从connector层获取数据
     *
     * @param reqParam
     * @return
     */
    public String getDataFromRemote(String reqParam, InterfaceInfo interfaceInfo,String appId) {
        //远程访问需要记录到表，方便统计
        this.insertCount(reqParam,appId);
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();
        String returnJson = restTemplateUtil.post(reqParam, interfaceInfo.getReqUrl());
        if (StringUtils.isBlank(returnJson)) {
            return "查询结果为空！";
        }
        return returnJson;
    }

    /**
     * 从cache获取数据（先确认是否创建表）
     * 当数据超过保质期后，调用【getDataFromSourceAndSave】方法，获取数据并保存到Hbase
     *
     * @param reqParam
     * @return
     */
    public String getDateFromCache(String reqParam, InterfaceInfo interfaceInfo,String appId) throws UnsupportedEncodingException {
        String interfaceCode = interfaceInfo.getInterfaceCode();

        //根据接口编码和请求参数的json字符串生成rowId
        String rowId = MD5Util.MD5_32(interfaceCode + reqParam);

        //表名
        String tableName = interfaceCode;

        //获取存入时间
        byte[] crtTimeAsByte = dataStoreService.getDataCrtTime(tableName, rowId);

        //检查是否有缓存数据
        if (crtTimeAsByte == null || crtTimeAsByte.length == 0) {
            return getDataFromSourceAndSave(reqParam, interfaceInfo, tableName, rowId,appId);
        }

        //校验缓存是否过期
        long crtTime = Bytes.toLong(crtTimeAsByte);
        long currentTime = new Date().getTime();
        long validTime = interfaceInfo.getValidTime();
        validTime = validTime * 60 * 1000;
        if (crtTime + validTime > currentTime) {
            //3、从缓存取数据
            String responseJson = new String(dataStoreService.getDataInfo(tableName, rowId), "UTF-8");
            return responseJson;
        } else {
            return getDataFromSourceAndSave(reqParam, interfaceInfo, tableName, rowId,appId);
        }
    }

    /**
     * 从connector获取数据，同时保存到Hbase
     *
     * @param tableName
     * @param rowId
     * @return
     * @throws Exception
     */
    public String getDataFromSourceAndSave(String reqParam, InterfaceInfo interfaceInfo, String tableName, String rowId,
                                           String appId)
            throws UnsupportedEncodingException {
        String data = this.getDataFromRemote(reqParam, interfaceInfo,appId);
        //存到缓存
        byte[] dataAsByte = data.getBytes("UTF-8");
        long currentTime = new Date().getTime();
        byte[] crtTimeAsByte = Bytes.toBytes(currentTime);
        dataStoreService.putData(tableName, rowId, dataAsByte, crtTimeAsByte);
        return data;
    }

    /**
     * 创建表（每一个接口创建一个表）
     *
     * @param storeName
     * @return
     */
    public Boolean createTable(String storeName) {
        return dataStoreService.createTable(storeName);
    }

    public void insertCount(String reqParam,String appId) {
        EdInterfaceCount edInterfaceCount = new EdInterfaceCount();
        edInterfaceCount.setAppId(appId);
        edInterfaceCount.setReqParam(reqParam);
        edInterfaceCountService.insert(edInterfaceCount);
    }

}
