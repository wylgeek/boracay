package com.hex.bigdata.udsp.mc.util;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.mc.model.McCurrent;
import com.hex.bigdata.udsp.mc.service.McCurrentCountService;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.InnerRequest;
import com.hex.bigdata.udsp.model.Request;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

public class McCommonUtil {

    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 获取并发信息
     *
     * @param request
     * @param maxCurrentNum
     * @return
     */
    public static McCurrent getMcCurrent(Request request, int maxCurrentNum) {
        synchronized (McCurrentCountService.class) {
            McCurrent mcCurrent = new McCurrent();
            String consumeId = UdspCommonUtil.getConsumeId(JSONUtil.parseObj2JSON(request));
            mcCurrent.setStartTime(format.format(new Date()));
            mcCurrent.setServiceName(request.getServiceName());
            mcCurrent.setUserName(request.getUdspUser());
            mcCurrent.setAppType(request.getAppType());
            mcCurrent.setAppName(request.getAppName());
            mcCurrent.setRequestType(request.getRequestType());
            mcCurrent.setPid(consumeId);
            mcCurrent.setAppId(request.getAppId());
            mcCurrent.setSyncType(request.getType().toUpperCase());
            mcCurrent.setPkId(consumeId);
            mcCurrent.setSyncType(request.getType().toUpperCase());
            mcCurrent.setMaxCurrentNum(maxCurrentNum);
            String requestContent = "";
            if (ConsumerConstant.CONSUMER_REQUEST_TYPE_INNER.equalsIgnoreCase(request.getRequestType())) { // 内部请求
                InnerRequest innerRequest = new InnerRequest();
                ObjectUtil.copyObject(request, innerRequest);
                requestContent = JSONUtil.parseObj2JSON(innerRequest);
            } else if (ConsumerConstant.CONSUMER_REQUEST_TYPE_OUTER.equalsIgnoreCase(request.getRequestType())) { // 外部请求
                ExternalRequest externalRequest = new ExternalRequest();
                ObjectUtil.copyObject(request, externalRequest);
                requestContent = JSONUtil.parseObj2JSON(externalRequest);
            } else {
                requestContent = JSONUtil.parseObj2JSON(request);
            }
            mcCurrent.setRequestContent(requestContent);
            return mcCurrent;
        }
    }
}
