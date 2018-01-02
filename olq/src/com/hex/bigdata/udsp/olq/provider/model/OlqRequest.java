package com.hex.bigdata.udsp.olq.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Page;

/**
 * Created by junjiem on 2017-2-17.
 */
public class OlqRequest {
    /**
     * 数据源信息
     */
    private Datasource datasource;

    private String sql;

    private Page page;

    public OlqRequest() {
    }

    public OlqRequest(Datasource datasource, String sql, Page page) {
        this.datasource = datasource;
        this.sql = sql;
        this.page = page;
    }

    public OlqRequest(Datasource datasource, String sql) {
        this.datasource = datasource;
        this.sql = sql;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

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
