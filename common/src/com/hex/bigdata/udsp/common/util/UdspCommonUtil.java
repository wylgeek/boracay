package com.hex.bigdata.udsp.common.util;

import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/11
 * TIME:9:38
 */
public class UdspCommonUtil {

    private static Logger logger = LoggerFactory.getLogger(UdspCommonUtil.class);

    private static Random random = new Random();

    public static String getLocalIpFromInetAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    /**
     * 获取本机MAC
     *
     * @return
     * @throws SocketException
     */
    public static String getLocalMac() {
        try {
            Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
            while (ni.hasMoreElements()) {
                NetworkInterface netl = ni.nextElement();
                byte[] bytes = netl.getHardwareAddress();
                if (netl != null && netl.isUp() && bytes != null && bytes.length == 6) {
                    StringBuffer sb = new StringBuffer();
                    for (byte b : bytes) {
                        sb.append(Integer.toHexString(b & 240 >> 4));
                        sb.append(Integer.toHexString(b & 15));
                        sb.append("-");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    return sb.toString().toUpperCase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取本机mac地址出错！！！！");
        }
        return null;
    }

    /**
     * 根据X-Forwarded-For获取客户端地址
     *
     * @return
     */
    public static String getRealRequestIp(HttpServletRequest request) {
        String clientIp = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
            if ("127.0.0.1".equals(clientIp) || "0:0:0:0:0:0:0:1".equals(clientIp)) {
                //根据网卡获取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clientIp = inet.getHostAddress();
            }
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照","分割
        if (StringUtils.isBlank(clientIp) && clientIp.length() > 15) {
            if (clientIp.indexOf(",") > 0) {
                clientIp = clientIp.substring(0, clientIp.indexOf(","));
            }
        }
        logger.info("clientIp:" + clientIp);
        return clientIp;
    }

    public static String getConsumeId(String key) {
        synchronized (UdspCommonUtil.class) {
            if (key == null) key = "";
            return Util.MD5(key + "|" + Util.uuid());
        }
    }

}
