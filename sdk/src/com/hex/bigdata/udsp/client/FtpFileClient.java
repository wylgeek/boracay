package com.hex.bigdata.udsp.client;

import com.hex.bigdata.udsp.config.SdkFtpClientConfig;
import com.hex.bigdata.udsp.util.SdkFtpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:15:08
 */
public class FtpFileClient {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(FtpFileClient.class);

    private SdkFtpUtil sdkFtpUtil;


    public SdkFtpUtil getSdkFtpUtil() {
        return sdkFtpUtil;
    }

    public void setSdkFtpUtil(SdkFtpUtil sdkFtpUtil) {
        this.sdkFtpUtil = sdkFtpUtil;
    }

    private FtpFileClient() {
    }

    private FtpFileClient(String hostName, int port, String ftpUserName, String ftpPassword) {
        try {
            this.sdkFtpUtil = new SdkFtpUtil(hostName, port, ftpUserName, ftpPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FtpFileClient(SdkFtpUtil sdkFtpUtil){
        this.sdkFtpUtil = sdkFtpUtil;
    }

    /**
     * 创建自定义的FtpFileClient
     *
     * @param hostName
     * @param port
     * @param ftpUserName
     * @param ftpPassword
     * @return
     */
    public static FtpFileClient createFtpClient(String hostName, int port, String ftpUserName, String ftpPassword) {
        return new FtpFileClient(hostName, port, ftpUserName, ftpPassword);
    }

    /**
     * 创建默认的FtpFileClient
     *
     * @return
     */
    public static FtpFileClient createFtpClient() {
        return new FtpFileClient(new SdkFtpUtil());
    }




    /**
     * 检查文件是否达到
     * 本方法通过检查标志文件是否生成来判断文件是否传输完成、判断文件是否达到
     *
     * @param filePath
     * @return
     */
    public boolean checkFileArrived(String filePath) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("传入参数为空！");
        }
        int index = filePath.lastIndexOf(".");
        filePath = filePath.substring(0, index);
        filePath = filePath + SdkFtpClientConfig.getLogFilePostfix();
        File file = new File(filePath);
        boolean flg = this.checkFileExist(filePath);
        return flg;
    }

    /**
     * 下载文件到指定目录，并重新命名文件
     * @param filePath 下载文件ftp服务器全路径
     * @param targetFileName 目标文件名
     * @param targetDirectory 下载目标文件夹
     * @throws Exception
     */
    public void downloadFile(String filePath, String targetFileName, String targetDirectory) throws Exception{
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("参数filePath不能为空");
        }
        if (StringUtils.isBlank(targetDirectory)) {
            throw new IllegalArgumentException("参数targetDirectory不能为空");
        }
        try {
            File downloadFile = new File(filePath);
            String downloadFileName = downloadFile.getName();
            String remotePath = downloadFile.getParent();
            remotePath = remotePath.replaceAll("\\\\", "\\/");
            this.sdkFtpUtil.connectFTPServer();
            this.sdkFtpUtil.downloadFile(remotePath, downloadFileName, targetDirectory,targetFileName);
        } finally {
            this.sdkFtpUtil.closeFTPClient();
        }
    }

    /**
     * 下载文件到目标文件夹
     *
     * @param filePath
     * @param targetDirectory
     */
    public void downloadFile(String filePath, String targetDirectory) throws Exception {
        this.downloadFile(filePath,null,targetDirectory);
    }


    private boolean checkFileExist(String filePath) throws Exception {
        SdkFtpUtil sdkFtp = this.sdkFtpUtil;
        try {
            sdkFtp.connectFTPServer();
            File file = new File(filePath);
            String fileName = file.getName();
            String parentPath = file.getParent();
            parentPath = parentPath.replaceAll("\\\\", "\\/");
            List<String> nameList = sdkFtp.getListFileName(parentPath);
            if (nameList == null || nameList.size() == 0) {
                return false;
            }
            for (String item : nameList) {
                if (filePath.equals(item)) {
                    return true;
                }
            }
            return false;
        } finally {
            sdkFtp.closeFTPClient();
        }
    }

    public static void main(String[] args) throws Exception {
        FtpFileClient ftpFileClient = new FtpFileClient("10.1.97.1", 21, "UDSP", "UDSP");
        //boolean flg = ftpFileClient.checkFileArrived("/home/ftp/UDSP/20170512/d708507e14f547b6b7d38098428a932c_20170512145953465.dat");
        //System.out.println(flg);
        ftpFileClient.downloadFile("/home/ftp/UDSP/20170512/d708507e14f547b6b7d38098428a932c_20170512145953465.dat", "C:\\Users\\PC\\Desktop");


    }


}
