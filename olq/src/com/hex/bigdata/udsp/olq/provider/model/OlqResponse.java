package com.hex.bigdata.udsp.olq.provider.model;


import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;

import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-15.
 */
public class OlqResponse {
    /**
     * 请求信息
     */
    private OlqRequest request;
    /**
     * 元数据
     */
    private ResultSetMetaData metadata;
    /**
     * 记录集合
     */
    private List<Map<String, String>> records;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 持续时间
     */
    private long consumeTime;

    /**
     * 状态
     */
    private Status status;

    /**
     * 状态码
     */
    private StatusCode statusCode;

    /**
     * 信息
     */
    private String message;

    /**
     * 异步时存储文件路径
     */
    private String filePath;

    /**
     * 分页参数
     */
    private Page page;

    /**
     * 返回字段信息
     */
    private LinkedHashMap<String,String> columns;

    public ResultSetMetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(ResultSetMetaData metadata) {
        this.metadata = metadata;
    }

    public List<Map<String, String>> getRecords() {
        return records;
    }

    public void setRecords(List<Map<String, String>> records) {
        this.records = records;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public OlqRequest getRequest() {
        return request;
    }

    public void setRequest(OlqRequest request) {
        this.request = request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public LinkedHashMap<String, String> getColumns() {
        return columns;
    }

    public void setColumns(LinkedHashMap<String, String> columns) {
        this.columns = columns;
    }
}
