package com.hex.bigdata.metadata.db.model;

/**
 * Created by junjiem on 2016-6-20.
 */
public class Config {
    private String dbType;
    private String jdbcUrl;
    private String username;
    private String password;
    private String dbName;

    public Config() {
        super();
    }

    public Config(String dbType, String jdbcUrl, String username, String password) {
        super();
        this.dbType = dbType;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
