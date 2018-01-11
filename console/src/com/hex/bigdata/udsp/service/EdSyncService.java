package com.hex.bigdata.udsp.service;

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
    public Response start(String appId, Map<String, String> data) {
        Response response = new Response();
        try {
            EdApplication edApplication = edApplicationService.selectByPrimaryKey(appId);
            String responseJson = connectService.getData(data, edApplication);
            responseJson = formatResponse(responseJson, appId);

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
     * @param jsonStr
     * @param appId
     * @return
     */
    public String formatResponse(String jsonStr, String appId) {
        Map map = JsonUtil.parseJSON2Map(jsonStr);
        Map responseMap = new HashMap();
        List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(appId);
        for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
            String name = edAppResponseParam.getName();
            Object value = map.get(name);
            responseMap.put(name,value);
        }
        String returnJson = JsonUtil.parseMap2JSON(responseMap);
        return returnJson;
    }
}