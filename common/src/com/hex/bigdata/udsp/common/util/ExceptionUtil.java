package com.hex.bigdata.udsp.common.util;

/**
 * Created by PC on 2017/12/15.
 */
public class ExceptionUtil {

    public static String getMessage(Throwable e) {
        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage());
        StackTraceElement[] traces = e.getStackTrace();
        for (StackTraceElement trace : traces) {
            sb.append("\tat " + trace + "\r\n");
        }
        return sb.toString();
    }

}
