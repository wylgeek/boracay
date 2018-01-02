package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.bigdata.udsp.common.util.FTPHelper;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponse;
import com.hex.bigdata.udsp.olq.provider.model.OlqResponseFetch;
import com.hex.bigdata.udsp.olq.service.OlqProviderService;
import com.hex.bigdata.udsp.olq.utils.OlqCommUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 同步联机查询的服务
 */
@Service
public class OlqSyncService {
    private static Logger logger = LoggerFactory.getLogger(OlqSyncService.class);

    static {
        FTPClientConfig.loadConf("goframe/udsp/udsp.config.properties");
    }

    @Autowired
    private OlqProviderService olqProviderService;

    /**
     * 同步运行
     *
     * @param consumeId
     * @param dsId
     * @param sql
     * @param page
     * @return
     */
    public Response syncStart(String consumeId, String dsId, String sql, Page page) {
        Response response = checkResponseParam(sql);
        if (response != null) return response;

        response = new Response();
        try {
            OlqResponse olqResponse = olqProviderService.select(consumeId, dsId, sql, page);
            response.setMessage(olqResponse.getMessage());
            response.setConsumeTime(olqResponse.getConsumeTime());
            response.setStatus(olqResponse.getStatus().getValue());
            response.setStatusCode(olqResponse.getStatusCode().getValue());
            response.setRecords(olqResponse.getRecords());
            response.setReturnColumns(olqResponse.getColumns());
            response.setPage(olqResponse.getPage());
        } catch (Exception e) {
            logger.error(ExceptionUtil.getMessage(e));
            e.printStackTrace();
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
        }
        return response;
    }

    private Response checkResponseParam(String sql) {
        Response response = null;
        if (StringUtils.isBlank(sql)) {
            response = new Response();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage(ErrorCode.ERROR_000009.getName());
        }
        return response;
    }

    private OlqResponse checkOlqResponseParam(String sql) {
        OlqResponse response = null;
        if (StringUtils.isBlank(sql)) {
            response = new OlqResponse();
            response.setStatus(Status.DEFEAT);
            response.setStatusCode(StatusCode.DEFEAT);
            response.setMessage(ErrorCode.ERROR_000009.getName());
        }
        return response;
    }

    /**
     * 异步运行
     *
     * @param dsId
     * @param sql
     * @return
     */
    public OlqResponse asyncStart(String consumeId, String dsId, String sql, Page page, String fileName, String userName) {
        OlqResponse response = checkOlqResponseParam(sql);
        if (response != null) return response;

        Status status = Status.SUCCESS;
        StatusCode statusCode = StatusCode.SUCCESS;
        String message = "成功";
        String filePath = "";
        OlqResponseFetch responseFetch = olqProviderService.selectFetch(consumeId, dsId, sql, page);
        Connection conn = responseFetch.getConnection();
        Statement stmt = responseFetch.getStatement();
        ResultSet rs = responseFetch.getResultSet();
        try {
            if (Status.SUCCESS == responseFetch.getStatus()) {
                // 写数据文件和标记文件到本地，并上传至FTP服务器
                CreateFileUtil.createDelimiterFile(rs, true, fileName);
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
                    //filePath = "ftp://" + FTPClientConfig.getHostname() + ":" + FTPClientConfig.getPort() + ftpFilePath;
                    filePath = ftpDataFilePath;
                    message = localDataFilePath;
                } catch (Exception e) {
                    status = Status.DEFEAT;
                    statusCode = StatusCode.DEFEAT;
                    message = "FTP上传失败！" + e.getMessage();
                    e.printStackTrace();
                } finally {
                    try {
                        ftpHelper.closeFTPClient();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {
                status = Status.DEFEAT;
                statusCode = StatusCode.DEFEAT;
                message = response.getMessage();
            }
        } catch (Exception e) {
            status = Status.DEFEAT;
            statusCode = StatusCode.DEFEAT;
            message = e.getMessage();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            OlqCommUtil.removeStatement(consumeId);
        }

        response = new OlqResponse();
        response.setFilePath(filePath);
        response.setMessage(message);
        response.setStatus(status);
        response.setStatusCode(statusCode);

        return response;
    }

}
