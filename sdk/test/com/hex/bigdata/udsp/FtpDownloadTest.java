package com.hex.bigdata.udsp;

import com.hex.bigdata.udsp.client.FtpFileClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * FTP下载测试
 */
public class FtpDownloadTest {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(FtpDownloadTest.class);

    @Test
    public void checkAndDownload() {
        FtpFileClient client = FtpFileClient.createFtpClient();

        String filePath = "/home/ftp/UDSP/20170519/98c06595fe634faa0f248867c031ca_20170519143454991.dat";
        boolean isArrived = true;
        try {
            //检查文件是否到达
            isArrived = client.checkFileArrived(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果文件到达，则下载文件
        if (isArrived) {
            try {
                //下载文件到指定目录
                client.downloadFile(filePath, "C:\\Users\\PC\\Desktop");
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("文件下载成功");
        } else {
            logger.info("文件未达到或文件不存在");
        }
    }

    @Test
    public void checkAndDownload2() {
        //创建默认的FTP客户端
        //FtpFileClient client = FtpFileClient.createFtpClient();
        //创建带参数的FTP客户端
        FtpFileClient client = FtpFileClient.createFtpClient("10.1.97.1", 21, "UDSP", "UDSP");

        String filePath = "/home/ftp/UDSP/20170519/98c06595fe63431faa0f248867c031ca_20170519143454991.dat";
        boolean isArrived = true;
        try {
            //检查文件是否到达
            isArrived = client.checkFileArrived(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果文件到达，则下载文件
        if (isArrived) {
            try {
                //重命名文件并下载到指定文件夹下
                client.downloadFile(filePath, "123.txt", "C:\\Users\\PC\\Desktop");
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("文件下载成功");
        } else {
            logger.info("文件未达到或文件不存在");
        }
    }

}
