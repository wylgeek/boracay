package com.hex.bigdata.udsp.im;

import com.hex.bigdata.udsp.im.thread.HiveGetLogThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by JunjieM on 2017-9-21.
 */
public class HiveExecuter {

    public static void main(String[] args) {
        String jdbcurl = "jdbc:hive2://192.168.1.61:10000/default";
        String username = "hive";
        String password = "111111";
        String preSql = "INSERT OVERWRITE TABLE solr_handler_test3 \n" +
                "SELECT id, card_no, channel_type, amt, dt FROM solr_handler_test1 limit 1000";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.apache.hive.jdbc.HiveDriver");
            conn = DriverManager.getConnection(jdbcurl, username, password);
            stmt = conn.createStatement();
            HiveGetLogThread thread = new HiveGetLogThread(stmt, 100L, 10);
            thread.start();
            stmt.executeUpdate(preSql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
