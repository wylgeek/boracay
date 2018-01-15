package com.hex.bigdata.udsp.service;

import com.alibaba.fastjson.JSON;
import com.hex.bigdata.metadata.db.util.JsonUtil;
import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.ed.connect.service.ConnectService;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.service.EdAppResponseParamService;
import com.hex.bigdata.udsp.ed.service.EdApplicationService;
import com.hex.bigdata.udsp.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/10/10.
 */
@Service
public class EdSyncService {
    private static Logger logger = LoggerFactory.getLogger(EdSyncService.class);

    @Autowired
    private EdApplicationService edApplicationService;

    @Autowired
    private EdAppResponseParamService edAppResponseParamService;

    @Autowired
    private ConnectService connectService;

    /**
     * 启动
     *
     * @param appId
     * @param data
     * @return
     */
    public Response start(String appId, Map<String, String> data, String udspUser) {
        Response response = new Response();
        try {
            EdApplication edApplication = edApplicationService.selectByPrimaryKey(appId);
            String responseJson = connectService.getData(data, edApplication, udspUser);
            responseJson = formatResponseContent(responseJson, appId);

            Object responseObj = formatResponseData(responseJson, appId);
            response.setResponseData(responseObj);

            response.setResponseContent(responseJson);
            response.setStatus(Status.SUCCESS.getValue());
            response.setStatusCode(StatusCode.SUCCESS.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

    /**
     * 筛选输出参数
     * 输出应用配置的输出参数
     *
     * @param jsonStr
     * @param appId
     * @return
     */
    public String formatResponseContent(String jsonStr, String appId) {
        Map map = JsonUtil.parseJSON2Map(jsonStr);
        Map responseMap = new HashMap();
        responseMap.put("consumeId", map.get("consumeId"));
        responseMap.put("consumeTime", map.get("consumeTime"));
        String status = (String) map.get("status");
        responseMap.put("status", status);
        //判断接口是否异常，如果异常则不返回数据。
        if (Status.DEFEAT.getValue().equals(status)) {
            responseMap.put("message", map.get("message"));
            responseMap.put("statusCode", ErrorCode.ERROR_500001.getValue());
            String returnJson = JsonUtil.parseMap2JSON(responseMap);
            return returnJson;
        }
        //处理要返回的数据
        Object dataObj = map.get("retData");
        String dataStr = JsonUtil.parseObj2JSON(dataObj);
        Map dataMap = JsonUtil.parseJSON2Map(dataStr);
        Map dataMapTemp = new HashMap();
        List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(appId);
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            String name = edAppResponseParam.getName();
            Object value = dataMap.get(name);
            dataMapTemp.put(name, value);
        }
        responseMap.put("retData", dataMapTemp);
        String returnJson = JsonUtil.parseMap2JSON(responseMap);
        return returnJson;
    }

    public Object formatResponseData(String jsonStr, String appId) {
        Map map = JsonUtil.parseJSON2Map(jsonStr);
        Map responseMap = new HashMap();
        responseMap.put("consumeId", map.get("consumeId"));
        responseMap.put("consumeTime", map.get("consumeTime"));
        String status = (String) map.get("status");
        responseMap.put("status", status);
        //判断接口是否异常，如果异常则不返回数据。
        if (Status.DEFEAT.getValue().equals(status)) {
            responseMap.put("message", map.get("message"));
            responseMap.put("statusCode", ErrorCode.ERROR_500001.getValue());
            String returnJson = JsonUtil.parseMap2JSON(responseMap);
            return returnJson;
        }
        //处理要返回的数据
        Object dataObj = map.get("retData");
        String dataStr = JsonUtil.parseObj2JSON(dataObj);
        Map dataMap = JsonUtil.parseJSON2Map(dataStr);
        Map dataMapTemp = new HashMap();
        List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(appId);
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            String name = edAppResponseParam.getName();
            Object value = dataMap.get(name);
            dataMapTemp.put(name, value);
        }
        responseMap.put("retData", dataMapTemp);
        Object returnObj = JSON.toJSON(responseMap);
        return returnObj;
    }
}