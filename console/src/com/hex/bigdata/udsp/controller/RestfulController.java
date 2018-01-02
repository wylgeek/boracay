package com.hex.bigdata.udsp.controller;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.UdspCommonUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.ConsumerService;
import com.hex.goframe.controller.BaseController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Restful Controller
 */
@RequestMapping("/restful")
@RestController
public class RestfulController extends BaseController {
    private static Logger logger = LogManager.getLogger(RestfulController.class);

    @Autowired
    private ConsumerService consumerService;

    /**
     * 欢迎信息
     *
     * @return
     */
    @RequestMapping(value = {"/welcome"}, method = {RequestMethod.GET})
    @ResponseBody
    public String welcome() {
        return "Welcome to use UDSP consuming restful service!";
    }

    /**
     * 外部请求
     * <p>
     * 这里用json字符串作为请求参数是为了自己转换成Request，出错时把信息返回给请求方。
     */
    @RequestMapping(value = {"/consume"}, method = {RequestMethod.POST})
    @ResponseBody
    public Response consume(@RequestBody String json, HttpServletRequest request) {
        ExternalRequest externalRequest = null;
        long bef = System.currentTimeMillis();
        try {
            externalRequest = jsonToRequest(json);
        } catch (Exception e) {
            //处理异常，返回respone
            Response response = new Response();
            this.consumerService.setErrorResponse(response, new ConsumeRequest(), bef,
                    ErrorCode.ERROR_000005.getValue(), e.getMessage(), null);
            return response;
        }
        //获取并设置客户端请求的IP
        externalRequest.setRequestIp(UdspCommonUtil.getRealRequestIp(request));
        return consumerService.externalConsume(externalRequest);
    }

    private ExternalRequest jsonToRequest(String json){
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
        return JSONUtil.parseJSON2Obj(json, ExternalRequest.class, classMap);
    }
}
