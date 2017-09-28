package com.hex.bigdata.metadata.db.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by hex on 16/6/22.
 */

public class ParameterUtil {
    public ParameterUtil() {
    }

    public static void notEmpty(String arg, String parameterName) {
        if (StringUtils.isEmpty(arg)) {
            throw new IllegalArgumentException("参数:" + parameterName + " 不能为空!");
        }
    }

    public static void notNull(Object arg, String message) {
        if (null == arg) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String arg, String parameterName, String message) {
        if (StringUtils.isEmpty(arg)) {
            throw new IllegalArgumentException(message == null ? "参数:" + parameterName + " 不能为空!" : message);
        }
    }

    public static void notInteger(String arg, String parameterName) {
        if (!arg.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("参数:" + parameterName + " 不是数字!");
        }
    }

    public static void isEmpty(String arg, String parameterName) {
        if (StringUtils.isNotEmpty(arg)) {
            throw new IllegalArgumentException("参数:" + parameterName + " 必须为空!");
        }
    }
}