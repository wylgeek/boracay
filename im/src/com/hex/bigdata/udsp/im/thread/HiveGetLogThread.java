package com.hex.bigdata.udsp.im.thread;

import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.hive.jdbc.HiveStatement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hive Jdbc 获取执行日志的线程类
 */
public class HiveGetLogThread extends Thread {
    private static Logger logger = LogManager.getLogger(HiveGetLogThread.class);

    private BatchJobService batchService = (BatchJobService) WebApplicationContextUtil.getBean("batchJobService");

    private long queryLogIntervalMs = 500L;
    private int fetchSize = 100;
    private String id;

    private int jobCount = 0;
    private int mapCount = 0;
    private int reduceCount = 0;
    private int percent = 0;
    private int oldPercent = -1;

    public HiveGetLogThread(String id) {
        this.id = id;
    }

    public HiveGetLogThread(String id, long queryLogIntervalMs) {
        this.id = id;
        this.queryLogIntervalMs = queryLogIntervalMs;
    }

    public HiveGetLogThread(String id, long queryLogIntervalMs, int fetchSize) {
        this.id = id;
        this.queryLogIntervalMs = queryLogIntervalMs;
        this.fetchSize = fetchSize;
    }

    public void run() {
        HiveStatement hiveStatement = batchService.getHiveStatement(id);
        if (hiveStatement == null) {
            return;
        }
        try {
            while (!hiveStatement.isClosed() && hiveStatement.hasMoreLogs()) {
                try {
                    for (String log : hiveStatement.getQueryLog(true, fetchSize)) {
                        parseLog(log);
                    }
                    Thread.currentThread().sleep(queryLogIntervalMs);
                } catch (SQLException e) { // 防止while里面报错，导致一直退不出循环
                    logger.warn(ExceptionUtil.getMessage(e));
                    return;
                } catch (InterruptedException e) {
                    logger.warn(ExceptionUtil.getMessage(e));
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析日志信息，从而计算总体的百分比（近似值）
     *
     * @param log
     */
    private void parseLog(String log) {
        logger.warn(log);
        String reg0 = "Total jobs = (\\d+)";
        List<String> list0 = regxString(log, reg0);
        if (list0 != null && list0.size() == 1) {
            jobCount = Integer.valueOf(list0.get(0));
        }
        for (int i = 0; i < jobCount; i++) {
            String reg1 = "Hadoop job information for Stage-" + i + ": number of mappers: (\\d+); number of reducers: (\\d+)";
            List<String> list1 = regxString(log, reg1);
            if (list1 != null && list1.size() == 2) {
                mapCount = Integer.valueOf(list1.get(0));
                reduceCount = Integer.valueOf(list1.get(1));
            }
            String reg2 = "Stage-" + i + " map = (\\d+)%,  reduce = (\\d+)%";
            List<String> list2 = regxString(log, reg2);
            if (list2 != null && list2.size() == 2) {
                percent = Integer.valueOf(list2.get(0)) + Integer.valueOf(list2.get(1)) / (i + 1);
                if (reduceCount > 0) percent = percent / 2;
            }
        }
        logger.debug("百分比：" + percent + "%");
        if (oldPercent != percent) {
            logger.info("百分比：" + percent + "%");
            // 百分比值写入内存中
            if (batchService != null && StringUtils.isNoneBlank(id)) {
                batchService.building(id, percent);
            }
            oldPercent = percent;
        }
    }

    /**
     * 获取查询的字符串，将匹配的字符串取出
     */
    private List<String> regxString(String str, String regx) {
        List<String> list = null;
        //1.将正在表达式封装成对象Patten 类来实现
        Pattern pattern = Pattern.compile(regx);
        //2.将字符串和正则表达式相关联
        Matcher matcher = pattern.matcher(str);
        //3.String 对象中的matches方法就是通过这个Matcher和pattern来实现的。
        //logger.debug(matcher.matches());
        //4.查找符合规则的子串
        while (matcher.find()) {
            list = new ArrayList<>();
            //获取的字符串
            logger.debug("获取的字符串：" + matcher.group());
            //获取的字符串的首位置和末位置
            logger.debug("获取的字符串的首位置【" + matcher.start() + "】和末位置【" + matcher.end() + "】");
            //提取获取字符串中需要的内容
            for (int i = 1; i <= matcher.groupCount(); i++) {
                logger.debug("提取获取的字符串中需要的第【" + i + "】个内容：" + matcher.group(i));
                list.add(matcher.group(i));
            }
        }
        return list;
    }
}
