package com.hex.bigdata.udsp.controller;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.model.WSRequest;
import com.hex.bigdata.udsp.model.WSResponse;
import com.hex.bigdata.udsp.service.ConsumerService;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * WebService Controller
 */
@Component
@WebService(serviceName = "ws", endpointInterface = "com.hex.bigdata.udsp.controller.WebServiceInterface")
public class WebServiceController implements WebServiceInterface {
    private static Logger logger = LogManager.getLogger(WebServiceController.class);

    // 有问题无效
//    @Resource(name = "org.apache.cxf.jaxws.context.WebServiceContextImpl")
//    private WebServiceContext context;

    @Autowired
    private ConsumerService consumerService;

    /**
     * 欢迎信息
     */
    @Override
    public String welcome() {
        return "Welcome to use UDSP consuming webService service!";
    }

    /**
     * 外部请求
     * <p>
     * 这里用WSRequest对象是因为WebService客户端可以通过WSDL生成WSRequest等JavaBean。
     */
    @Override
    public WSResponse consume(@RequestBody WSRequest wsRequest) {
        ExternalRequest externalRequest = new ExternalRequest();
        ObjectUtil.copyObject(wsRequest, externalRequest);

        //获取并设置客户端请求的IP
        externalRequest.setRequestIp(getClientIp());
        Response response = consumerService.externalConsume(externalRequest);

        WSResponse wsResponse = new WSResponse();
        ObjectUtil.copyObject(response, wsResponse);

        return wsResponse;
    }

    /**
     * 外部请求
     * <p>
     * 这里用json字符串作为请求参数是为了自己转换成Request，出错时把信息返回给请求方。
     */
    public String consume2(@RequestBody String json) {
        ExternalRequest externalRequest = null;
        Response response = null;
        long bef = System.currentTimeMillis();
        try {
            externalRequest = jsonToRequest(json);
        } catch (Exception e) {
            //处理异常，返回respone
            response = new Response();
            this.consumerService.setErrorResponse(response, new ConsumeRequest(), bef,
                    ErrorCode.ERROR_000005.getValue(), e.getMessage(), null);
            return JSONUtil.parseObj2JSON(response);
        }
        //获取并设置客户端请求的IP
        externalRequest.setRequestIp(getClientIp());
        response = consumerService.externalConsume(externalRequest);
        return JSONUtil.parseObj2JSON(response);
    }

//    private String getClientIp() {
//        MessageContext mc = context.getMessageContext();
//        HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
//        String clientIp = request.getRemoteAddr();
//        logger.debug("client IP: " + clientIp);
//        return clientIp;
//    }

    private String getClientIp() {
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        String clientIp = request.getRemoteAddr();
        logger.debug("client IP: " + clientIp);
        return clientIp;
    }

    private ExternalRequest jsonToRequest(String json){
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
        return JSONUtil.parseJSON2Obj(json, ExternalRequest.class, classMap);
    }

}