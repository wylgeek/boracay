package com.hex.bigdata.udsp.controller;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.dto.ComDatasourcePropsView;
import com.hex.bigdata.udsp.common.util.*;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.InnerRequest;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.ConsumerService;
import com.hex.bigdata.udsp.service.DatasourceTestService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.GFLoginUser;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http Controller
 */
@RequestMapping("/http")
@Controller
public class HttpController extends BaseController {
    private static Logger logger = LogManager.getLogger(HttpController.class);

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");
    }

    @Value("${download.timeout.ms:300000}")
    private Long downloadTimeoutMs;

    @Value("${download.sleep.time.ms:1000}")
    private Long downloadSleepTimeMs;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private DatasourceTestService dataSourseTestService;

    // --------------------------------------------外部请求----------------------------------------------------

    /**
     * 欢迎信息
     */
    @RequestMapping(value = {"/welcome"}, method = {RequestMethod.GET})
    @ResponseBody
    public String welcome() {
        return "Welcome to use UDSP consuming http service!";
    }

    /**
     * 外部请求
     * <p/>
     * 这里用json字符串作为请求参数是为了自己转换成Request，出错时把信息返回给请求方。
     */
    @RequestMapping(value = {"/consume"}, method = {RequestMethod.POST})
    @ResponseBody
    public Response consume(@RequestBody String json, HttpServletRequest request) {
        ExternalRequest externalRequest = null;
        long bef = System.currentTimeMillis();
        try {
            externalRequest = jsonToExternalRequest(json);
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

    // --------------------------------------------内部请求----------------------------------------------------

    /**
     * 内部请求1
     * <p/>
     * (由于nui的datagrid请求方式特殊性)
     */
    @RequestMapping({"/datagrid/consume"})
    @ResponseBody
    public PageListResult datagridConsume(InnerRequest innerRequest, Page page, HttpServletRequest request) {
        boolean status = true;
        String message = "执行成功";
        com.hex.bigdata.udsp.common.provider.model.Page p = new com.hex.bigdata.udsp.common.provider.model.Page();
        p.setPageIndex(page.getPageIndex());
        p.setPageSize(page.getPageSize());
        innerRequest.setPage(p);
        //获取并设置客户端请求的IP
        innerRequest.setRequestIp(UdspCommonUtil.getRealRequestIp(request));
        PageListResult pageListResult = null;
        try {
            Response response = getInnerConsume(innerRequest);
            if ("SUCCESS".equals(response.getStatus())) {
                if (response.getPage() != null) {
                    pageListResult = new PageListResult(response.getRecords(), page);
                    pageListResult.setTotal(response.getPage().getTotalCount());
                } else {
                    pageListResult = new PageListResult(response.getRecords());
                }
                pageListResult.setStatus(status);
                pageListResult.setMessage(message);
            } else {
                status = false;
                message = response.getMessage();
                pageListResult = new PageListResult(null);
                pageListResult.setStatus(status);
                pageListResult.setMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = e.getMessage();
            pageListResult = new PageListResult(null);
            pageListResult.setStatus(status);
            pageListResult.setMessage(message);
        }
        return pageListResult;
    }

    /**
     * 内部请求2
     */
    @RequestMapping({"/inner/consume"})
    @ResponseBody
    public Response innerConsume(@RequestBody String json, HttpServletRequest request) {
        InnerRequest innerRequest = jsonToInnerRequest(json);
        //获取并设置客户端请求的IP
        innerRequest.setRequestIp(UdspCommonUtil.getRealRequestIp(request));
        return getInnerConsume(innerRequest);
    }

    /**
     * 内部请求3
     * <p/>
     * 异步请求并下载文件
     */
    @RequestMapping({"/inner/async/consume"})
    @ResponseBody
    public MessageResult innerAsyncConsume(@RequestBody String json, HttpServletRequest request) {
        InnerRequest innerRequest = jsonToInnerRequest(json);
        boolean status = true;
        String message = "下载成功";
        //获取并设置客户端请求的IP
        innerRequest.setRequestIp(UdspCommonUtil.getRealRequestIp(request));
        if (!ConsumerConstant.CONSUMER_TYPE_ASYNC.equalsIgnoreCase(innerRequest.getType())
                || !ConsumerConstant.CONSUMER_ENTITY_START.equalsIgnoreCase(innerRequest.getEntity())) {
            return new MessageResult(false, "不为异步的start请求");
        }
        Response response = getInnerConsume(innerRequest);
        if (response == null || !Status.SUCCESS.getValue().equalsIgnoreCase(response.getStatus())) {
            return new MessageResult(false, "异步的start请求失败");
        }
        String responseContent = response.getResponseContent();
        innerRequest.setType("async");
        innerRequest.setEntity("status");
        innerRequest.setConsumeId(response.getConsumeId());
        int count = 0;
        int num = (int) (downloadTimeoutMs / downloadSleepTimeMs);
        while (true) {
            if (count >= num) {
                return new MessageResult(false, "异步下载文件超时");
            }
            response = getInnerConsume(innerRequest);
            if (response == null) {
                return new MessageResult(false, "异步的status响应为空");
            }
            if (StatusCode.SUCCESS.getValue().equals(response.getStatusCode())) {
                return new MessageResult(true, responseContent);
            } else if (StatusCode.RUNING.getValue().equals(response.getStatusCode())) {
                try {
                    Thread.sleep(downloadSleepTimeMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return new MessageResult(false, response.getMessage());
            }
            count++;
        }
    }

    /**
     * 下载文件
     */
    @RequestMapping({"/inner/download"})
    @ResponseBody
    public void innerDownload(String filePath, HttpServletResponse rsp) {
        OutputStream os = null;
        InputStream in = null;
        try {
            os = rsp.getOutputStream();
            rsp.reset();
            String fileName = FileUtil.getFileName(filePath);
            rsp.setHeader("Content-Disposition", "attachment;filename="
                    + fileName);
            rsp.setContentType("application/octet-stream;charset=utf-8");
            FTPHelper ftpHelper = new FTPHelper();
            ftpHelper.connectFTPServer();
            in = ftpHelper.downloadFile(filePath);
            IOUtils.copy(in, os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * 测试数据源
     */
    @RequestMapping("/ds/test")
    @ResponseBody
    public MessageResult dsTest(@RequestBody ComDatasourcePropsView comDatasourcePropsView) {
        boolean status = true;
        String message = "测试数据源成功！";
        if (comDatasourcePropsView == null) {
            status = false;
            message = "请求参数为空";
        } else {
            try {
                status = dataSourseTestService.testDatasource(comDatasourcePropsView);
                if (status) {
                    message = "测试数据源成功！";
                } else {
                    message = "测试数据源失败！";
                }
            } catch (Exception e) {
                e.printStackTrace();
                status = false;
                message = "测试数据源失败!";
            }
        }
        if (status) {
            logger.debug(message);
        } else {
            logger.warn(message);
        }
        return new MessageResult(status, message);
    }

    private Response getInnerConsume(InnerRequest innerRequest) {
        List<String> roles = getUserRoles();
        boolean isAdmin = roles.contains("ADMIN");
        GFLoginUser loginUser = getLoginUser();
        innerRequest.setUdspUser(loginUser.getUserId());
        return consumerService.innerConsume(innerRequest, isAdmin);
    }

    private InnerRequest jsonToInnerRequest(String json) {
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
        return JSONUtil.parseJSON2Obj(json, InnerRequest.class, classMap);
    }

    private ExternalRequest jsonToExternalRequest(String json) {
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
        return JSONUtil.parseJSON2Obj(json, ExternalRequest.class, classMap);
    }
}
