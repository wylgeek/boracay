package com.hex.bigdata.udsp.olq.provider.model;

import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Page;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by PC on 2017/5/22.
 */
public class OlqResponseFetch {
    /**
     * 请求信息
     */
    private OlqRequest request;

    /**
     *  结果集
     */
    private ResultSet resultSet;

    /**
     *  链接
     */
    private Connection connection;

    /**
     * 声明
     */
    private Statement statement;

    /**
     *  持续时间
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
     * 分页参数
     */
    private Page page;

    public OlqRequest getRequest() {
        return request;
    }

    public void setRequest(OlqRequest request) {
        this.request = request;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public long getConsumeTime() {
        return consumeTime;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
