package com.hex.bigdata.udsp.im.provider.impl.util.model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-26.
 */
public class Page {
    private List<Map<String, String>> records; // 每页记录集合
    private int pageSize; // 默认的每页显示条数
    private int pageIndex; // 当前页
    private int totalCount; // 总记录数
    private int totalPage; // 总页数

    public Page() {
    }

    public Page(int pageIndex, int pageSize, int totalCount, List<Map<String, String>> records) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalCount = totalCount;
        this.records = records;
        this.totalPage = (totalCount % pageSize == 0 ? totalCount / pageSize
                : (totalCount / pageSize + 1));
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
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
