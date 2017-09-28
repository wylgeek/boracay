package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.provider.model.Result;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.bigdata.udsp.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 同步交互查询的服务
 */
@Service
public class IqSyncService {

    private static Logger logger = LoggerFactory.getLogger(IqSyncService.class);

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");
    }

    @Autowired
    private IqProviderService iqProviderService;

    public Response syncStart(String appId, Map<String, String> paraMap, Page page) {
        Response response = new Response();
        try {
            IqResponse iqResponse = run(appId, paraMap, page);
            response.setPage(iqResponse.getPage());
            response.setMessage(iqResponse.getMessage());
            response.setConsumeTime(iqResponse.getConsumeTime());
            response.setStatus(iqResponse.getStatus().getValue());
            response.setStatusCode(iqResponse.getStatusCode().getValue());
            List<Map<String, String>> records = new ArrayList<>();
            Map<String, String> map = null;

            List<Result> results = iqResponse.getRecords();
            if (null != results && results.size() > 0) {
                for (Result result : iqResponse.getRecords()) {
                    map = new HashMap<>();
                    for (Map.Entry<String, Object> entry : result.entrySet()) {
                        map.put(entry.getKey(), result.getString(entry.getKey()));
                    }
                    records.add(map);
                }
                response.setRecords(records);
                //返回字段名称及类型
            }
            response.setReturnColumns(iqResponse.getColumns());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

    /**
     * 运行
     *
     * @param appId
     * @param paraMap
     * @param page
     * @return
     */
    private IqResponse run(String appId, Map<String, String> paraMap, Page page) {
        if (page != null && page.getPageIndex() > 0) {
            return iqProviderService.select(appId, paraMap, page.getPageIndex(), page.getPageSize());
        } else {
            return iqProviderService.select(appId, paraMap);
        }
    }

    /**
     * 异步运行
     *
     * @param appId
     * @param paraMap
     * @param page
     * @return
     */
    public IqResponse asyncStart(String appId, Map<String, String> paraMap, Page page, String fileName, String userName) {
        Status status = Status.SUCCESS;
        StatusCode statusCode = StatusCode.SUCCESS;
        String message = "成功";
        String filePath = "";
        IqResponse response = run(appId, paraMap, page);
        if (Status.SUCCESS == response.getStatus()) {
            List<Result> list = response.getRecords();
            List<Map<String, String>> records = new ArrayList<>();
            Map<String, String> map = null;
            for (Result result : list) {
                map = new HashMap<>();
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                    map.put(entry.getKey(), result.getString(entry.getKey()));
                }
                records.add(map);
            }
            // 写数据文件和标记文件到本地，并上传至FTP服务器
            CreateFileUtil.createDelimiterFile(records, true, fileName);
            String dataFileName = CreateFileUtil.getDataFileName(fileName);
            String flgFileName = CreateFileUtil.getFlgFileName(fileName);
            String localDataFilePath = CreateFileUtil.getLocalDataFilePath(fileName);
            String localFlgFilePath = CreateFileUtil.getLocalFlgFilePath(fileName);
            String ftpFileDir = CreateFileUtil.getFtpFileDir(userName);
            String ftpDataFilePath = ftpFileDir + "/" + dataFileName;
            FTPHelper ftpHelper = new FTPHelper();
            try {
                ftpHelper.connectFTPServer();
                ftpHelper.uploadFile(localDataFilePath, dataFileName, ftpFileDir);
                ftpHelper.uploadFile(localFlgFilePath, flgFileName, ftpFileDir);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    ftpHelper.closeFTPClient();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //filePath = "ftp://" + FTPClientConfig.getHostname() + ":" + FTPClientConfig.getPort() + ftpFilePath;
            filePath = ftpDataFilePath;
            message = localDataFilePath;
        } else {
            status = Status.DEFEAT;
            statusCode = StatusCode.DEFEAT;
            message = "失败";
        }
        response.setFilePath(filePath);
        response.setMessage(message);
        response.setStatus(status);
        response.setStatusCode(statusCode);
        return response;
    }

}
