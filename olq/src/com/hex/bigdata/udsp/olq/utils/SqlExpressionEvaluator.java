package com.hex.bigdata.udsp.olq.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL字符串表达式工具类
 * <p>
 * 说明:
 * ${} 表示必填内容，参数名称需要满足字母、数字、下划线，且以字母开头；
 * #[] 表示选填内容，#[]内部有且仅有一个${}。
 * <p>
 * 示例：
 * SELECT ABC,CBA,${field1} #[,${field2}]
 * FROM TABLE_NAME
 * WHERE 1=1 #[and ABC>${abc}]
 * AND (1=1 #[or CBA='${cba}] #[or BAC='${bac}'])
 */
public class SqlExpressionEvaluator {

    private static Logger logger = LogManager.getLogger(SqlExpressionEvaluator.class);

    // #[]
    private static final String REGEX = "(#\\[(((?!#\\[).)*)\\])";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    // ${}
    private static final String REGEX2 = "(\\$\\{([\\w ]*)\\})";
    private static final Pattern PATTERN2 = Pattern.compile(REGEX2);

    // 满足字母、数字、下划线，且以字母开头
    private static final String REGEX3 = "^[A-Za-z][A-Za-z0-9_]{0,63}$";
    private static final Pattern PATTERN3 = Pattern.compile(REGEX3);

    /**
     * 解析字符串并批量替换表达式
     *
     * @param str
     * @param params
     * @return
     */
    public static String parseSql(String str, Map<String, String> params) {
        Preconditions.checkArgument(StringUtils.isNotBlank(str));
        // 先处理#[]
        Matcher m = PATTERN.matcher(str);
        while (m.find()) {
            String $exp = m.group(1); // sfdf\nsdg#[ABC > ${abc}]sdf\ngsdg is group(1) ===> #[ABC > ${abc}]
            String exp = m.group(2).trim(); // sfdf\nsdg#[ABC > ${abc}]sdf\ngsdg is group(2) ===> ABC > ${abc}
            logger.debug("#group(1):" + $exp);
            logger.debug("#group(2):" + exp);
            String value = evaluate(exp, params); // 正确的结果字符串
            str = StringUtils.replace(str, $exp, value); // 替换表达式为正确的结果字符串
        }
        // 再处理${}
        str = evaluates(str, params);
        return str;
    }

    /**
     * 解析结果字符串
     *
     * @param str
     * @param params
     * @return
     */
    private static String evaluate(String str, Map<String, String> params) {
        boolean flg = true;
        Matcher m = PATTERN2.matcher(str);
        while (m.find()) {
            String $exp = m.group(1); // ABC > ${abc}  is group(1) ===> ${abc}
            String exp = m.group(2).trim(); // ABC > ${abc} is group(2) ===> abc
            logger.debug("$group(1):" + $exp);
            logger.debug("$group(2):" + exp);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                logger.debug("value:" + value);
                if (exp.equals(key)) {
                    if (StringUtils.isNotBlank(value)) {
                        str = StringUtils.replace(str, $exp, value); // 替换表达式为正确的结果字符串
                        flg = false;
                    } else {
                        break;
                    }
                }
            }
        }
        if (flg) {
            str = " ";
        }
        logger.debug("str:" + str);
        return str;
    }

    /**
     * 解析结果字符串
     *
     * @param str
     * @param params
     * @return
     */
    private static String evaluates(String str, Map<String, String> params) {
        Matcher m = PATTERN2.matcher(str);
        while (m.find()) {
            String $exp = m.group(1); // ABC > ${abc}  is group(1) ===> ${abc}
            String exp = m.group(2).trim(); // ABC > ${abc} is group(2) ===> abc
            logger.debug("$group(1):" + $exp);
            logger.debug("$group(2):" + exp);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                logger.debug("value:" + value);
                if (exp.equals(key)) {
                    if (StringUtils.isBlank(value)) {
                        value = "";
                    }
                    str = StringUtils.replace(str, $exp, value); // 替换表达式为正确的结果字符串
                    logger.debug("str:" + str);
                }
            }
        }
        return str;
    }

    /**
     * 解析参数名称集合
     *
     * @param str
     * @return
     */
    @Deprecated
    public static List<String> parseParams(String str) {
        Preconditions.checkArgument(StringUtils.isNotBlank(str));
        List<String> params = new ArrayList<String>();
        Matcher m = PATTERN2.matcher(str);
        while (m.find()) {
            String $exp = m.group(1);
            String exp = m.group(2).trim();
            logger.debug("$group(1):" + $exp);
            logger.debug("$group(2):" + exp);
            if (StringUtils.isBlank(exp)) {
                throw new IllegalArgumentException("参数名称为空！");
            } else if (!match(exp)) {
                throw new IllegalArgumentException("参数名称必须满足字母、数字、下划线，且以字母开头！");
            }
            params.add(exp);
        }
        // 去重
        params = new ArrayList(new HashSet(params));
        return params;
    }

    /**
     * 解析参数名称集合
     *
     * @param str
     * @return
     */
    public static List<String> parseParams2(String str) {
        Preconditions.checkArgument(StringUtils.isNotBlank(str));
        List<String> params = new ArrayList<>();
        // 先处理#[]
        Matcher m = PATTERN.matcher(str);
        while (m.find()) {
            String $exp = m.group(1);
            String exp = m.group(2).trim();
            logger.debug("#group(1):" + $exp);
            logger.debug("#group(2):" + exp);
            String name = evaluateParam(exp); // 解析参数名称
            logger.debug("name:" + name);
            if (StringUtils.isNotBlank(name)) {
                params.add(name);
            }
            str = StringUtils.replace(str, $exp, ""); // 替换表达式为""
            logger.debug("str:" + str);
        }
        // 再处理${}
        params.addAll(evaluateParams(str));
        // 去重
        params = new ArrayList(new HashSet(params));
        return params;
    }

    /**
     * 解析参数名称
     *
     * @param str
     * @return
     */
    private static String evaluateParam(String str) {
        String name = "";
        Matcher m = PATTERN2.matcher(str);
        int count = 0;
        while (m.find()) {
            String $exp = m.group(1);
            String exp = m.group(2).trim();
            logger.debug("$group(1):" + $exp);
            logger.debug("$group(2):" + exp);
            if (count == 1) {
                throw new IllegalArgumentException("#[]内不能有多个参数！");
            } else if (StringUtils.isBlank(exp)) {
                throw new IllegalArgumentException("参数名称为空！");
            } else if (!match(exp)) {
                throw new IllegalArgumentException("参数名称必须满足字母、数字、下划线，且以字母开头！");
            }
            name = exp;
            count++;
        }
        return name;
    }

    /**
     * 解析参数名称集合
     *
     * @param str
     * @return
     */
    private static List<String> evaluateParams(String str) {
        List<String> params = new ArrayList<>();
        Matcher m = PATTERN2.matcher(str);
        while (m.find()) {
            String $exp = m.group(1);
            String exp = m.group(2).trim();
            logger.debug("$group(1):" + $exp);
            logger.debug("$group(2):" + exp);
            if (StringUtils.isBlank(exp)) {
                throw new IllegalArgumentException("参数名称为空！");
            } else if (!match(exp)) {
                throw new IllegalArgumentException("参数名称必须满足字母、数字、下划线，且以字母开头！");
            }
            params.add(exp);
        }
        return params;
    }

    /**
     * 解析参数名称Map
     *
     * @param str
     * @return
     */
    public static Map<String, Boolean> parseParamMap(String str) {
        Preconditions.checkArgument(StringUtils.isNotBlank(str));
        Map<String, Boolean> params = new HashMap<>();
        // 先处理#[]
        Matcher m = PATTERN.matcher(str);
        while (m.find()) {
            String $exp = m.group(1);
            String exp = m.group(2).trim();
            logger.debug("#group(1):" + $exp);
            logger.debug("#group(2):" + exp);
            String name = evaluateParam(exp); // 解析参数名称
            logger.debug("name:" + name);
            if (StringUtils.isNotBlank(name)) {
                params.put(name, false); // 选输的参数
            }
            str = StringUtils.replace(str, $exp, ""); // 替换表达式为""
            logger.debug("str:" + str);
        }
        // 再处理${}
        List<String> list = evaluateParams(str);
        for (String name : list) {
            params.put(name, true); // 必输的参数
        }
        return params;
    }

    /**
     * 验证参数名称
     *
     * @param str
     * @return
     */
    private static boolean match(String str) {
        Matcher matcher = PATTERN3.matcher(str);
        return matcher.matches();
    }


    public static void main(String[] args) {
        String sql = "SELECT ${return_field} from table_name \n"
                + "where #[ABC > ${abc}] ${abc}\n"
                + "AND (#[CBA = '${cba}'] OR #[TEST <> ${ test } ${aaaa}]) \n"
                + " LIMIT ${limit}";

        List<String> params = parseParams2(sql);
        logger.info("--------------------------------------------");
        for (String name : params) {
            logger.info(name);
        }

        Map<String, String> map = new HashMap<>();
        map.put("abc", "1");
        map.put("limit", "2");
        map.put("cba", "3");
        map.put("test", "4");
        map.put("return_field", "5");
        String str = parseSql(sql, map);
        logger.info("--------------------------------------------");
        logger.info(str);

        // 解析参数调用（不推荐）parseParams()
        // 解析参数调用（推荐） parseParamMap()
        // 解析SQL调用（推荐） parseSql()
    }

}
