package com.hex.bigdata.udsp.common.service;

import com.hex.bigdata.udsp.common.util.ShellUtil;
import com.hex.goframe.spi.httl.HttlClasspathLoader;
import com.hex.goframe.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

/**
 * Created by PC on 2017/5/31.
 */
@Service
public class FtpUserManagerService {

    private static Logger logger = LogManager.getLogger(FtpUserManagerService.class);

    @Value("${ftp.hostname}")
    private String ftpHostname;

    @Value("${host.username}")
    private String hostUsername;

    @Value("${host.password}")
    private String hostPassword;

    @Value("${ftp.rootpath}")
    private String ftpRootpath;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    private final String SHELL_DIR_PATH = "goframe/udsp/shell";

    private String shellDirPath;

    /**
     * 启动服务时初始化FTP服务器上UDSP用户和用户组
     */
    public void init() {
        try {
            shellDirPath = ResourceUtils.getFile("classpath:" + SHELL_DIR_PATH).getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("shellDirPath：" + shellDirPath);
        logger.info("初始化FTP服务器上UDSP用户和用户组【开始】");
        String shell = "sh " + shellDirPath + "/init_udsp_user_group.sh "
                + ftpHostname + " " + hostUsername + " " + hostPassword + " " + ftpRootpath + " " + ftpUsername + " " + ftpPassword
                + " >> /tmp/init_udsp_user_group.log";
        ShellUtil.exec(shell);
        logger.info("初始化FTP服务器上UDSP用户和用户组【结束】");
    }

    /**
     * 添加生产者FTP用户
     *
     * @param ftpUsername
     * @param ftpPassword
     */
    public void addProducerFtpUser(String ftpUsername, String ftpPassword) {
        logger.info("添加FTP服务器上" + ftpUsername + "用户【开始】");
        String shell = "sh " + shellDirPath + "/add_producer_user.sh "
                + ftpHostname + " " + hostUsername + " " + hostPassword + " " + ftpUsername + " " + ftpPassword
                + " >> /tmp/add_consumer_user.log";
        ShellUtil.exec(shell);
        logger.info("添加FTP服务器上" + ftpUsername + "用户【结束】");
    }

    /**
     * 删除生产者FTP用户
     *
     * @param ftpUsername
     */
    public void delProducerFtpUser(String ftpUsername) {
        logger.info("删除FTP服务器上" + ftpUsername + "用户【开始】");
        String shell = "sh " + shellDirPath + "/del_producer_user.sh "
                + ftpHostname + " " + hostUsername + " " + hostPassword + " " + ftpUsername
                + " >> /tmp/del_consumer_user.log";
        ShellUtil.exec(shell);
        logger.info("删除FTP服务器上" + ftpUsername + "用户【结束】");
    }

    /**
     * 添加消费者FTP用户
     *
     * @param ftpUsername
     * @param ftpPassword
     */
    public void addConsumerFtpUser(String ftpUsername, String ftpPassword) {
        if ("admin".equals(ftpUsername)) {
            ftpUsername = "udsp" + ftpUsername;
        }
        logger.info("添加FTP服务器上" + ftpUsername + "用户【开始】");
        String shell = "sh " + shellDirPath + "/add_consumer_user.sh "
                + ftpHostname + " " + hostUsername + " " + hostPassword + " " + ftpRootpath + " " + ftpUsername + " " + ftpPassword
                + " >> /tmp/add_consumer_user.log";
        ShellUtil.exec(shell);
        logger.info("添加FTP服务器上" + ftpUsername + "用户【结束】");
    }

    /**
     * 删除消费者FTP用户
     *
     * @param ftpUsername
     */
    public void delConsumerFtpUser(String ftpUsername) {
        if ("admin".equals(ftpUsername)) {
            ftpUsername = "udsp" + ftpUsername;
        }
        logger.info("删除FTP服务器上" + ftpUsername + "用户【开始】");
        String shell = "sh " + shellDirPath + "/del_consumer_user.sh "
                + ftpHostname + " " + hostUsername + " " + hostPassword + " " + ftpRootpath + " " + ftpUsername
                + " >> /tmp/del_consumer_user.log";
        ShellUtil.exec(shell);
        logger.info("删除FTP服务器上" + ftpUsername + "用户【结束】");
    }
}
