package com.hex.bigdata.metadata.db;

import com.hex.bigdata.metadata.db.model.Column;
import com.hex.bigdata.metadata.db.model.ColumnType;
import com.hex.bigdata.metadata.db.model.Database;
import com.hex.bigdata.metadata.db.model.Table;
import com.hex.bigdata.metadata.db.util.JsonUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-7.
 */
public class JdbcTestUtil {
    public static void print(Client client) throws SQLException {
        System.out.println("=================================START=========================================");

        String currentDbName = client.getCurrentDbName();
        System.out.println("--------------------------------CurrentDbName----------------------------------");
        System.out.println(currentDbName);

        List<Database> dbs = client.getDatabases();
        System.out.println("--------------------------------DatabaseList----------------------------------");
        System.out.println(JsonUtil.parseList2JSON(dbs));

        List<Table> tbs = client.getTables("dcp");
        System.out.println("--------------------------------TableList----------------------------------");
        System.out.println(JsonUtil.parseList2JSON(tbs));

        List<Column> cols = client.getColumns("dcp", "t_gf_dict");
        System.out.println("--------------------------------ColumnList----------------------------------");
        System.out.println(JsonUtil.parseList2JSON(cols));

        Database db = client.getDatabase("dcp");
        System.out.println("--------------------------------Database----------------------------------");
        System.out.println(JsonUtil.parseObj2JSON(db));

        Table tb = client.getTable("dcp", "t_gf_dict");
        System.out.println("--------------------------------Table----------------------------------");
        System.out.println(JsonUtil.parseObj2JSON(tb));

        List<ColumnType> cts = client.getColumnTypes();
        System.out.println("--------------------------------ColumnTypes----------------------------------");
        System.out.println(JsonUtil.parseList2JSON(cts));

        System.out.println("==================================END=======================================");
    }
}
