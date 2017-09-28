package com.hex.bigdata.udsp.common.constant;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/26
 * TIME:10:13
 */
public enum ErrorCode {
    ERROR_000001("权限不足","000001"),
    ERROR_000002("用户名密码错误", "000002"),
    ERROR_000003("并发数量达到上限","000003"),
    ERROR_000004("没有注册服务或者服务未启用","000004"),
    ERROR_000005("参数解析失败","000005"),
    ERROR_000006("请求IP不在允许的IP段内","000006"),
    ERROR_000007("程序内部异常","000007"),
    ERROR_000008("没有授权服务","000008"),
    ERROR_000009("必输参数为空","000009"),
    ERROR_000010("调用类型或者ENTITY设置错误","000010"),
    ERROR_000011("当前消费id不存在","000011"),
    ERROR_000012("查询消费状态过于频繁","000012"),
    ERROR_000013("调用参数异常","000013"),
    ERROR_000014("等待超时","000014"),
    ERROR_000015("执行超时","000015"),
    ERROR_000016("等待队列已满","000016"),
    ERROR_000099("其他错误","000099"),
    //注：1开头为交互查询相关错误码
    //注：2开头为模型相关错误码
    ERROR_200001("模型接口无响应", "200001"),
    ERROR_200002("模型名称不存在，请检查", "200002"),
    ERROR_200003("模型参数错误", "200003"),
    ERROR_200004("模型调用其他异常", "200004");

    //注：3开头为联机查询相关错误码

    //注：4开头为实时流相关错误码

    private String name;
    private String value;

    private ErrorCode(String name, String value) {
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
}
