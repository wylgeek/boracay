package com.hex.bigdata.udsp.ed.connect.util;

import com.hex.bigdata.udsp.ed.connect.bean.Meta;
import com.hex.bigdata.udsp.ed.connect.bean.RowKeyBean;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * Created by k1335 on 2016/4/18.
 */
public class TableUtil {

    private static final Logger logger = Logger.getLogger(TableUtil.class.getName());

    //shell目录
    public static final String ROOTDIR = "/goframe/udsp/shell/";
    //模板目录
    public static final String TEMPLATEDIR = "/goframe/udsp/shell/template/";
    //占位符
    public static final String PLACEHOLDER = "${tableName}";

    /**
     * 根据存储名称、表前缀生成表名
     *
     * @param storeName
     * @param tablePrefix
     * @return
     */
    public static String getTableName(String storeName, String tablePrefix) {
        StringBuffer stringBuffer = new StringBuffer(tablePrefix);
        stringBuffer.append("_").append(storeName.toLowerCase());
        String tableName = stringBuffer.toString();
        return tableName;
    }

    /**
     * 根据uuid生成主键
     *
     * @return
     */
    public static String getRowKeyWithUUID() {
        String uuid = UUID.randomUUID().toString();
        String[] keys = uuid.split("-");
        StringBuffer tempRowkey = new StringBuffer();
        for (String key : keys) {
            tempRowkey.append(key);
        }
        return tempRowkey.toString();
    }

    /**
     * 通过应用ID，时间戳生成rowkey
     *
     * @return
     */
    public static String getRowKeyWithAppId(String appId) {
        String uuid = UUID.randomUUID().toString();
        String[] keys = uuid.split("-");
        StringBuffer tempRowkey = new StringBuffer();
        for (String key : keys) {
            tempRowkey.append(key);
        }
        return tempRowkey.toString();
    }

    /**
     * 通过元数据，时间戳生成rowkey
     *
     * @return
     */
    public static String getRowKey(Meta meta) {
        //为使Region分布均匀
        Integer hashCode = meta.getFilename().hashCode();
        StringBuffer tempRowkey = new StringBuffer(hashCode.toString());
        String uuid = UUID.randomUUID().toString();
        String metaMd5 = MD5Hash.getMD5AsHex(Bytes.toBytes(meta.toString() + uuid));
        tempRowkey.append(metaMd5);
        return tempRowkey.toString();
    }

    public static String getRowKey() {
        return RowKeyBean.objectIdHexString();
    }

}
