package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.model.Page;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:11:12
 */
public class SqlRequest extends UdspRequest {
    /**
     * 执行sql语句
     */
    private String sql;

    /**
     * 分页参数
     */
    private Page page;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
