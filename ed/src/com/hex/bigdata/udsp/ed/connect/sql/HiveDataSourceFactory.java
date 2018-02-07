package com.hex.bigdata.udsp.ed.connect.sql;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveDataSourceFactory {
	private static Logger log = Logger.getLogger(HiveDataSourceFactory.class);
	private static BasicDataSource hiveDs = null;

	static {
		try {
			getDataSource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建数据源
	 * 
	 * @return
	 */
	public static BasicDataSource getDataSource() throws Exception {
		if (hiveDs == null) {
			log.info("Hive数据库连接信息：[driverClass=" + HiveClientConfig.getDriverClass() + ", jdbcUrl=" + HiveClientConfig.getJdbcUrl()
					+ ", username=" + HiveClientConfig.getUsername() + ", password=" + HiveClientConfig.getPassword() + "]");
			hiveDs = new BasicDataSource();
			hiveDs.setDriverClassName(HiveClientConfig.getDriverClass());
			hiveDs.setUrl(HiveClientConfig.getJdbcUrl());
			hiveDs.setUsername(HiveClientConfig.getUsername());
			hiveDs.setPassword(HiveClientConfig.getPassword());
			hiveDs.setInitialSize(HiveClientConfig.getInitialSize());// 数据库初始化时，创建的连接个数
			hiveDs.setMinIdle(HiveClientConfig.getMinIdle());// 最小空闲连接数
			hiveDs.setMaxIdle(HiveClientConfig.getMaxIdle());// 数据库最大连接数
			hiveDs.setMaxActive(HiveClientConfig.getMaxActive());// 设置最大并发数
			hiveDs.setMaxWait(HiveClientConfig.getMaxWait());// 最长等待时间，单位毫秒
			hiveDs.setValidationQuery(HiveClientConfig.getValidationQuery()); // 验证链接的SQL语句，必须能返回一行及以上数据
			hiveDs.setValidationQueryTimeout(HiveClientConfig.getValidationQueryTimeout()); // 自动验证连接的时间
			hiveDs.setTimeBetweenEvictionRunsMillis(HiveClientConfig.getTimeBetweenEvictionRunsMillis()); // N毫秒检测一次是否有死掉的线程
			//hiveDs.setMinEvictableIdleTimeMillis(HiveClientConfig.getMinEvictableIdleTimeMillis());// 空闲连接N毫秒中后释放
			hiveDs.setTestWhileIdle(HiveClientConfig.isTestWhileIdle());
			hiveDs.setTestOnBorrow(HiveClientConfig.isTestOnBorrow());
			hiveDs.setTestOnReturn(HiveClientConfig.isTestOnReturn());
		}
		return hiveDs;
	}

	/**
	 * 释放数据源
	 */
	public static void closeDataSource() throws Exception {
		if (hiveDs != null) {
			hiveDs.close();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			if (hiveDs != null) {
				con = hiveDs.getConnection();
			} else {
				con = getDataSource().getConnection();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return con;
	}

	/**
	 * 关闭连接
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				log.error("关闭ResultSet异常！" + e.getMessage(), e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				log.error("关闭Statement异常！" + e.getMessage(), e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				log.error("关闭Connection异常！" + e.getMessage(), e);
			}
		}
	}
}
