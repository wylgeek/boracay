package com.hex.bigdata.udsp.im;

import com.hex.bigdata.udsp.common.util.CharUtil;

/**
 * Created by PC on 2017/10/20.
 */
public class StringToCharTest {

    public static void main(String[] args) throws Exception {
        String a16 = "\0x1E";
        String a10 = "15";
        String a8 = "\007";
        String a8_2 = "\u0036";


        String a = "\u0036";
        String b = "\\0X1E";
        String c = "\\t";

        int len1 = a.length();
        char[] chars1 = a.toCharArray();

        int len2 = b.length();
        char chars2 = CharUtil.ascii2Char(b);

        int len3 = c.length();
        char chars3 = CharUtil.ascii2Char(c);
    }

}
