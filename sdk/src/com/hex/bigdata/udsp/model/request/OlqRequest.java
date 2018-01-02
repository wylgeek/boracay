package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.model.Page;

/**
 * 联机查询
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:11:12
 */
@Deprecated
public class OlqRequest extends UdspRequest {
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
