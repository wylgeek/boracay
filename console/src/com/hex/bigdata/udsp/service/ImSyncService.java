package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.im.constant.ModelType;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.service.ImModelService;
import com.hex.bigdata.udsp.im.service.RealtimeJobService;
import com.hex.bigdata.udsp.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by PC on 2017/10/10.
 */
@Service
public class ImSyncService {
    private static Logger logger = LoggerFactory.getLogger(IqSyncService.class);
    @Autowired
    private BatchJobService batchJobService;
    @Autowired
    private RealtimeJobService realtimeJobService;
    @Autowired
    private ImModelService imModelService;

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
            Model model = imModelService.getModel(appId, data);
            if (ModelType.REALTIME == model.getType()) {
                realtimeJobService.start(model);
            } else {
                batchJobService.start(model);
            }
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
}
