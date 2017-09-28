package com.hex.bigdata.udsp.common.constant;

import org.apache.zookeeper.Op;

/**
 * 操作符
 */
public enum Operator {
    EQ("等于", "=="),
    GT("大于", ">"),
    LT("小于", "<"),
    GE("大于等于", ">="),
    LE("小于等于", "<="),
    NE("不等于", "!="),
    LK("模糊匹配", "like"),
    IN("in查询","in"),
    RLIKE("右模糊匹配","right like");

    private String value;
    private String name;

    private Operator(String name, String value) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Operator getOperatorByValue(String value) {
        Operator operator = null;
        switch (value){
            case "==":
                operator = EQ;
                break;
            case  ">" :
                operator = GT;
                break;
            case "<" :
                operator = LT;
                break;
            case ">=" :
                operator = GE;
                break;
            case "<=" :
                operator = LE;
                break;
            case "!=" :
                operator = NE;
                break;
            case "like" :
                operator = LK;
                break;
            case "in" :
                operator = IN;
                break;
            case "right like" :
                operator = RLIKE;
                break;
            default:
                operator = null;
        }
        return operator;
    }
}
