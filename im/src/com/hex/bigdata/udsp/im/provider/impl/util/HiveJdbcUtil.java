package com.hex.bigdata.udsp.im.provider.impl.util;

import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HiveDatasource;
import com.hex.bigdata.udsp.im.service.BatchJobService;
import com.hex.bigdata.udsp.im.thread.HiveGetLogThread;
import com.hex.goframe.util.WebApplicationContextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-22.
 */
public class HiveJdbcUtil {
    private static Logger logger = LogManager.getLogger(HiveJdbcUtil.class);
    private static Map<String, Statement> statementPool = new HashMap<>();

    public static synchronized Statement getStatement(String key) {
        return statementPool.remove(key);
    }

    public static synchronized void putStatement(String key, Statement stmt){
        statementPool.put(key, stmt);
    }

    public static boolean executeUpdate(String key, HiveDatasource datasource, String updateSql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        int rs = -1;
        try {
            conn = JdbcUtil.getConnection(datasource);
            stmt = conn.createStatement();

            putStatement(key, stmt);

            // 注入BatchService对象
            BatchJobService batchService = (BatchJobService)WebApplicationContextUtil.getBean("batchService");

            HiveGetLogThread thread = new HiveGetLogThread(batchService, key, stmt, 500L, 100);
            thread.start();

            rs = stmt.executeUpdate(updateSql);
        } finally {
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(stmt);
            com.hex.bigdata.metadata.db.util.JdbcUtil.close(conn);
        }
        return rs == 0 ? true : false;
    }
}
