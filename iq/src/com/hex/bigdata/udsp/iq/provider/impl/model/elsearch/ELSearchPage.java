package com.hex.bigdata.udsp.iq.provider.impl.model.elsearch;

import java.util.List;
import java.util.Map;

/**
 * Solr查询的分页类
 */
public class ELSearchPage {
    private List<Map<String, Object>> records; // 每页记录集合
    private int pageIndex; // 当前页
    private int pageSize; // 默认的每页显示条数
    private int totalCount; // 总记录数
    private int totalPage; // 总页数

    public ELSearchPage() {
        super();
    }

    public ELSearchPage(List<Map<String, Object>> records, int pageIndex, int pageSize, int totalCount) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.records = records;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1);
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, Object>> records) {
        this.records = records;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}