package com.hex.bigdata.udsp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ErrorType;
import java.util.ArrayList;
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
     * @param serviceName
     * @param appId
     * @param data
     * @return
     */
    public Response start(String serviceName, String appId, Map<String, String> data, String udspUser) {
        Response response = new Response();
        try {
            EdApplication edApplication = edApplicationService.selectByPrimaryKey(appId);
            String responseJson = connectService.getData(serviceName, data, edApplication,udspUser);
            if(!StringUtils.isBlank(responseJson)){
                Map map = JsonUtil.parseJSON2Map(responseJson);
                if(StatusCode.DEFEAT.getValue().equals(map.get("statusCode").toString())) {
                    response.setMessage(map.get("message").toString());
                    response.setStatus(Status.DEFEAT.getValue());
                    response.setErrorCode(map.get("errorCode").toString());
                    response.setStatusCode(StatusCode.DEFEAT.getValue());
                } else {
                    Object returnObj = formatResponse(map, appId);
                    response.setResponseData(returnObj);
                    response.setStatus(Status.SUCCESS.getValue());
                    response.setStatusCode(StatusCode.SUCCESS.getValue());
                }
            } else {
                response.setMessage("请检查输入参数！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_500001.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

    /**
     * 筛选输出参数
     * 输出应用配置的输出参数
     * @param map
     * @param appId
     * @return
     */
    public Object formatResponse(Map map, String appId) {
        //获取返回参数列表
        List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(appId);
        Object dataObj = map.get("retData");
        //判断返回数据是不是数组
        if(dataObj instanceof ArrayList){
            List dataList = (ArrayList) dataObj;
            List list = new ArrayList();
            for(int i=0;i<dataList.size();i++){
                Object dataObj2 = dataList.get(i);
                //判断是否还是数组，（默认数组中的对象是返回数据）
                if(!(dataObj2 instanceof ArrayList)){
                    Object obj = getData2Return(dataObj2,edAppResponseParams);
                    list.add(obj);
                }
            }
            return JSON.toJSON(list);
        } else {
            //组装返回值
            return getData2Return(dataObj,edAppResponseParams);
        }
    }

    /**
     * 筛选出返回数据列表中对应的数据
     * @param dataObj
     * @param edAppResponseParams
     * @return
     */
    public Object getData2Return(Object dataObj, List<EdAppResponseParam> edAppResponseParams){
        String dataStr = JSONObject.toJSONString(dataObj);
        Map dataMap = JSONObject.toJavaObject(JSONObject.parseObject(dataStr),Map.class);
        Map dataMapTemp = new HashMap();
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            String name = edAppResponseParam.getName();
            Object value = dataMap.get(name);
            dataMapTemp.put(name,value);
        }
        Object returnObj = JSON.toJSON(dataMapTemp);
        return returnObj;
    }
}