package com.hex.bigdata.udsp.ed.connect.service;


/**
 * Created by k1335 on 2016/4/18.
 */
public interface DataStoreService {
    boolean createTable(String storeName);

    String putData(String tableName, final String rowId, final byte[] data, final byte[] param);

    byte[] getDataCrtTime(String tableName, String rowKey);

    byte[] getDataInfo(String tableName, String rowKey);

    String getDataList(String storeName, String rowKey, long validTime) throws Exception;

}
