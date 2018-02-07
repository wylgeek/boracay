package com.hex.bigdata.udsp.ed.connect.service;

import com.hex.bigdata.udsp.ed.connect.sql.HiveClientConfig;
import com.hex.bigdata.udsp.ed.connect.sql.HiveDataSourceFactory;
import com.hex.bigdata.udsp.ed.connect.sql.hive.HiveClient2;
import com.hex.bigdata.udsp.ed.connect.sql.hive.HiveSql;
import com.hex.bigdata.udsp.ed.connect.sql.model.HBaseColumn;
import com.hex.bigdata.udsp.ed.connect.sql.model.TableColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author k2236
 * 
 */
@Service("HBaseService")
public class HiveService {

	private static Logger logger = LoggerFactory.getLogger(HiveService.class);

	private static final String family = "d";
	private static final String qualifier1 = "d";
	private static final String qualifier2 = "c";
	private static final String key = "key";
	private static final String data = "data";
	private static final String type = "string";

	static{
		HiveClientConfig.loadConf("hive.jdbc.config.properties");
	}

	/**
	 * 创建HBase的Hive关联表SQL语句
	 * @param ifNotExists
	 * @param tableName
	 * @param tableComment
	 * @param hbaseTableName
	 * @return
	 */
	public String createHBaseCorrelationTableSql(boolean ifNotExists,
			String tableName, String tableComment, String hbaseTableName) {
		// Hive关联表的字段
		List<TableColumn> hiveColumns = new ArrayList<TableColumn>();
		hiveColumns.add(new TableColumn(key, type));
		hiveColumns.add(new TableColumn("data", type));
		hiveColumns.add(new TableColumn("param", type));
		// HBase的列族
		List<HBaseColumn> hbaseColumns = new ArrayList<HBaseColumn>();
		hbaseColumns.add(new HBaseColumn(family, qualifier1));
		hbaseColumns.add(new HBaseColumn(family, qualifier2));
		// 生成建表语句
		return HiveSql.createHbaseCorrelationTable(true, ifNotExists,
				tableName, hiveColumns, tableComment, hbaseColumns,
				hbaseTableName);
	}

	/**
	 * 创建HBase的Hive关联表
	 * 仅供ed模块使用，因为产生的的sql语句有两个列，hive有三个字段
	 * @param tableName
	 * @param tableComment
	 * @param hbaseTableName
	 * @return
	 */
	public boolean createHBaseCorrelationTable(String tableName,
			String tableComment, String hbaseTableName) {
		Connection conn = null;
		Statement stmt = null;
		try {
			HiveClient2 hiveClient = new HiveClient2();
			String sql = createHBaseCorrelationTableSql(true, tableName,
					tableComment, hbaseTableName);
			logger.info("CREATE SQL:" + sql);
			conn = HiveDataSourceFactory.getConnection();
			stmt = conn.createStatement();
			hiveClient.executeUpdate(sql, stmt);
			logger.info("Hive的HBase关联表" + tableName + "创建成功！");
		} catch (Exception e) {
			logger.debug("Hive的HBase关联表创建失败 ", e);
			logger.info("Hive的HBase关联表" + tableName + "创建失败！");
			return false;
		} finally {
			HiveDataSourceFactory.close(null, stmt, conn);
		}
		return true;
	}
}