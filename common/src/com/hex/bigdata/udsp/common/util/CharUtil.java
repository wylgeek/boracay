package com.hex.bigdata.udsp.common.util;

/**
 * Created by PC on 2017/10/20.
 */
public class CharUtil {

    /**
     * ASCII转译字符和码值的字符串转CHAR字符
     * <p>
     * 1、支持转译字符的字符串，如：\a、\b、\f、\t、\n、\r、\v、\`、\\、\"、\?
     * <p>
     * 2、支持ASCII码值（八进制）的字符串，如：\007、\036
     * <p>
     * 3、支持ASCII码值（十进制）的字符串，如：\7、\30
     * <p>
     * 4、支持ASCII码值（十六进制）的字符串，如：\0x07、\0x1E、\0X07、\0X1E、\u0007、\u001E
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static char ascii2Char(String str) throws Exception {
        int len = str.length();
        if (len == 1) {
            return str.charAt(0);
        } else if (len == 2) {
            if ("\\a".equals(str)) {
                return (char) 7;
            } else if ("\\b".equals(str)) {
                return (char) 8;
            } else if ("\\f".equals(str)) {
                return (char) 12;
            } else if ("\\n".equals(str)) {
                return (char) 10;
            } else if ("\\r".equals(str)) {
                return (char) 13;
            } else if ("\\t".equals(str)) {
                return (char) 9;
            } else if ("\\v".equals(str)) {
                return (char) 11;
            } else if ("\\\\".equals(str)) {
                return (char) 92;
            } else if ("\\`".equals(str)) {
                return (char) 39;
            } else if ("\\\"".equals(str)) {
                return (char) 34;
            } else if ("\\?".equals(str)) {
                return (char) 63;
            } else if ("\\0".equals(str)) {
                return (char) 0;
            }
        }
        try {
            if (len == 4 && str.startsWith("\\0")) {
                return (char) (int) Integer.valueOf(str.substring(2), 8); // 八进制转十进制
            } else if ((len == 5 && (str.startsWith("\\0x") || str.startsWith("\\0X")))
                    || (len == 6 && str.startsWith("\\u0"))) {
                return (char) (int) Integer.valueOf(str.substring(3), 16); // 十六进制转十进制
            } else if (len >= 2 && len <= 4 && str.startsWith("\\") && !str.substring(1).startsWith("0")) {
                return (char) (int) Integer.valueOf(str.substring(1), 10); // 十进制转十进制
            }
        } catch (Exception e) {
            ;
        }
        throw new Exception("不支持转换的字符串内容:" + str);
    }

}
