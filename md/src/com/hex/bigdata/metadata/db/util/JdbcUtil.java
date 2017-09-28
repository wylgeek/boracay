package com.hex.bigdata.metadata.db.util;

import org.apache.commons.lang.StringUtils;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {

    public static Connection newConnection(DBType dbType, String jdbcUrl,
                                           String username, String password) {
        String driverClass = getDriverClass(dbType);
        Connection conn = null;
        try {
            Class.forName(driverClass).newInstance();
            conn = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection newConnection(DBType dbType, String host, String port, String name,
                                           String username, String password) {
        String jdbcUrl = getJdbcUrl(dbType, host, port, name);
        if (DBType.IMPALA == dbType && StringUtils.isEmpty(password)) {
            jdbcUrl += ";auth=noSasl";
        }
        return newConnection(dbType, jdbcUrl, username, password);
    }

    public static Connection newConnection(DBType dbType, String jdbcUrl, Properties properties) {
        String driverClass = getDriverClass(dbType);
        Connection conn = null;
        try {
            Class.forName(driverClass).newInstance();
            conn = DriverManager.getConnection(jdbcUrl, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String getDriverClass(DBType dbType) {
        return JdbcTemplateUtil.getDriverClass(dbType.getValue());
    }

    public static String getJdbcUrl(DBType dbType, String host, String port, String name) {
        ParameterUtil.notEmpty(host, dbType + "数据库配置的主机地址");

        if (StringUtils.isEmpty(port) || !port.matches("^[0-9]+$")) {
            port = JdbcTemplateUtil.getJdbcDefaultPort(dbType.getValue());
        }

        String jdbcUrlTemplate = JdbcTemplateUtil.getJdbcUrlTemplate(dbType.getValue());

        if (DBType.ORACLE == dbType && StringUtils.isEmpty(name)) {
            name = "orcl";
        }

        return jdbcUrlTemplate.replace("${host}", host)//
                .replace("${port}", port)//
                .replace("${name}", name);
    }

    public static void close(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            if (obj instanceof Connection) {
                Connection conn = ((Connection) obj);
                if(!conn.isClosed())
                    conn.close();
            } else if (obj instanceof Statement) {
                ((Statement) obj).close();
            } else if (obj instanceof ResultSet) {
                ((ResultSet) obj).close();
            } else if (obj instanceof Closeable) {
                ((Closeable) obj).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> int convertRs2List(ResultSet rs, List<T> list, Function<ResultSet, T> handler) throws SQLException {
        try {
            int n = 0;
            while (rs.next()) {
                T ret = handler.call(rs);
                if (null == ret) {
                    continue;
                }
                list.add(ret);
            }
            return n;
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public static <T> List<T> convertRs2List(ResultSet rs, Function<ResultSet, T> handler) throws SQLException {
        List<T> list = new ArrayList<T>();
        try {
            while (rs.next()) {
                T ret = handler.call(rs);
                if (null == ret) {
                    continue;
                }
                list.add(ret);
            }
            return list;
        } finally {
            JdbcUtil.close(rs);
        }
    }

    public static <T> int execSqlAndConvertRs2List(Connection connection, String sql, List<T> list, Function<ResultSet, T> handler) throws SQLException {
        Statement stmt = connection.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return convertRs2List(rs, list, handler);
        } finally {
            JdbcUtil.close(stmt);
        }
    }

    public static <T> List<T> execSqlAndConvertRs2List(Connection connection, String sql, Function<ResultSet, T> handler) throws SQLException {
        Statement stmt = connection.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            return convertRs2List(rs, handler);
        } finally {
            JdbcUtil.close(stmt);
        }
    }

    public static void each(ResultSet rs, Action<ResultSet> handler) throws SQLException {
        try {
            while (rs.next()) {
                handler.call(rs);
            }
        } finally {
            JdbcUtil.close(rs);
        }
    }
}
