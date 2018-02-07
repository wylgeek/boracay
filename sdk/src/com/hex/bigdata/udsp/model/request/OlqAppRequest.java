package com.hex.bigdata.udsp.model.request;

import com.hex.bigdata.udsp.model.Page;

import java.util.Map;

/**
 * 联机查询应用
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/5/17
 * TIME:11:12
 */
@Deprecated
public class OlqAppRequest extends UdspRequest {
    /**
     * 分页参数
     */
    private Page page;

    /**
     * 查询参数Map
     */
    private Map<String, String> data;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
