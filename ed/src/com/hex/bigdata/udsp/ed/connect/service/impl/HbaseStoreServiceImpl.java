package com.hex.bigdata.udsp.ed.connect.service.impl;

import com.hex.bigdata.udsp.ed.connect.service.DataStoreService;
import com.hex.bigdata.udsp.ed.connect.service.ParamService;
import com.hex.bigdata.udsp.ed.connect.util.HBasePool;
import com.hex.bigdata.udsp.ed.connect.util.TableUtil;
import org.apache.avro.hadoop.io.AvroKeyValue;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by k1335 on 2016/4/18.
 */
@Service
public class HbaseStoreServiceImpl implements DataStoreService {

    private static final Logger logger = Logger.getLogger(HbaseStoreServiceImpl.class.getName());
    private static HBasePool pool;

    static {
        //解决winutils.exe不存在的问题
        File workaround = new File(".");
        System.getProperties().put("hadoop.home.dir", workaround.getAbsolutePath());
        try {
            new File("./bin").mkdirs();
            new File("./bin/winutils.exe").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Resource(name = "hbaseConfiguration")
    private Configuration config;

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private ParamService paramService;

    /**
     * 预分区，分区规则
     * @return
     */
    private byte[][] getSplitKeys() {
        String[] keys = new String[] { "0147ae147ae147ae", "028f5c28f5c28f5c", "03d70a3d70a3d70a", "051eb851eb851eb8",
                "0666666666666666", "07ae147ae147ae14", "08f5c28f5c28f5c2", "0a3d70a3d70a3d70", "0b851eb851eb851e",
                "0ccccccccccccccc", "0e147ae147ae147a", "0f5c28f5c28f5c28", "10a3d70a3d70a3d6", "11eb851eb851eb84",
                "1333333333333332", "147ae147ae147ae0", "15c28f5c28f5c28e", "170a3d70a3d70a3c", "1851eb851eb851ea",
                "1999999999999998", "1ae147ae147ae146", "1c28f5c28f5c28f4", "1d70a3d70a3d70a2", "1eb851eb851eb850",
                "1ffffffffffffffe", "2147ae147ae147ac", "228f5c28f5c28f5a", "23d70a3d70a3d708", "251eb851eb851eb6",
                "2666666666666664", "27ae147ae147ae12", "28f5c28f5c28f5c0", "2a3d70a3d70a3d6e", "2b851eb851eb851c",
                "2cccccccccccccca", "2e147ae147ae1478", "2f5c28f5c28f5c26", "30a3d70a3d70a3d4", "31eb851eb851eb82",
                "3333333333333330", "347ae147ae147ade", "35c28f5c28f5c28c", "370a3d70a3d70a3a", "3851eb851eb851e8",
                "3999999999999996", "3ae147ae147ae144", "3c28f5c28f5c28f2", "3d70a3d70a3d70a0", "3eb851eb851eb84e",
                "3ffffffffffffffc", "4147ae147ae147aa", "428f5c28f5c28f58", "43d70a3d70a3d706", "451eb851eb851eb4",
                "4666666666666662", "47ae147ae147ae10", "48f5c28f5c28f5be", "4a3d70a3d70a3d6c", "4b851eb851eb851a",
                "4cccccccccccccc8", "4e147ae147ae1476", "4f5c28f5c28f5c24", "50a3d70a3d70a3d2", "51eb851eb851eb80",
                "533333333333332e", "547ae147ae147adc", "55c28f5c28f5c28a", "570a3d70a3d70a38", "5851eb851eb851e6",
                "5999999999999994", "5ae147ae147ae142", "5c28f5c28f5c28f0", "5d70a3d70a3d709e", "5eb851eb851eb84c",
                "5ffffffffffffffa", "6147ae147ae147a8", "628f5c28f5c28f56", "63d70a3d70a3d704", "651eb851eb851eb2",
                "6666666666666660", "67ae147ae147ae0e", "68f5c28f5c28f5bc", "6a3d70a3d70a3d6a", "6b851eb851eb8518",
                "6cccccccccccccc6", "6e147ae147ae1474", "6f5c28f5c28f5c22", "70a3d70a3d70a3d0", "71eb851eb851eb7e",
                "733333333333332c", "747ae147ae147ada", "75c28f5c28f5c288", "770a3d70a3d70a36", "7851eb851eb851e4",
                "7999999999999992", "7ae147ae147ae140", "7c28f5c28f5c28ee", "7d70a3d70a3d709c", "7eb851eb851eb84a",
                "7ffffffffffffff8", "8147ae147ae147a6", "828f5c28f5c28f54", "83d70a3d70a3d702", "851eb851eb851eb0",
                "866666666666665e", "87ae147ae147ae0c", "88f5c28f5c28f5ba", "8a3d70a3d70a3d68", "8b851eb851eb8516",
                "8cccccccccccccc4", "8e147ae147ae1472", "8f5c28f5c28f5c20", "90a3d70a3d70a3ce", "91eb851eb851eb7c",
                "933333333333332a", "947ae147ae147ad8", "95c28f5c28f5c286", "970a3d70a3d70a34", "9851eb851eb851e2",
                "9999999999999990", "9ae147ae147ae13e", "9c28f5c28f5c28ec", "9d70a3d70a3d709a", "9eb851eb851eb848",
                "9ffffffffffffff6", "a147ae147ae147a4", "a28f5c28f5c28f52", "a3d70a3d70a3d700", "a51eb851eb851eae",
                "a66666666666665c", "a7ae147ae147ae0a", "a8f5c28f5c28f5b8", "aa3d70a3d70a3d66", "ab851eb851eb8514",
                "acccccccccccccc2", "ae147ae147ae1470", "af5c28f5c28f5c1e", "b0a3d70a3d70a3cc", "b1eb851eb851eb7a",
                "b333333333333328", "b47ae147ae147ad6", "b5c28f5c28f5c284", "b70a3d70a3d70a32", "b851eb851eb851e0",
                "b99999999999998e", "bae147ae147ae13c", "bc28f5c28f5c28ea", "bd70a3d70a3d7098", "beb851eb851eb846",
                "bffffffffffffff4", "c147ae147ae147a2", "c28f5c28f5c28f50", "c3d70a3d70a3d6fe", "c51eb851eb851eac",
                "c66666666666665a", "c7ae147ae147ae08", "c8f5c28f5c28f5b6", "ca3d70a3d70a3d64", "cb851eb851eb8512",
                "ccccccccccccccc0", "ce147ae147ae146e", "cf5c28f5c28f5c1c", "d0a3d70a3d70a3ca", "d1eb851eb851eb78",
                "d333333333333326", "d47ae147ae147ad4", "d5c28f5c28f5c282", "d70a3d70a3d70a30", "d851eb851eb851de",
                "d99999999999998c", "dae147ae147ae13a", "dc28f5c28f5c28e8", "dd70a3d70a3d7096", "deb851eb851eb844",
                "dffffffffffffff2", "e147ae147ae147a0", "e28f5c28f5c28f4e", "e3d70a3d70a3d6fc", "e51eb851eb851eaa",
                "e666666666666658", "e7ae147ae147ae06", "e8f5c28f5c28f5b4", "ea3d70a3d70a3d62", "eb851eb851eb8510",
                "ecccccccccccccbe", "ee147ae147ae146c", "ef5c28f5c28f5c1a", "f0a3d70a3d70a3c8", "f1eb851eb851eb76",
                "f333333333333324", "f47ae147ae147ad2", "f5c28f5c28f5c280", "f70a3d70a3d70a2e", "f851eb851eb851dc",
                "f99999999999998a", "fae147ae147ae138", "fc28f5c28f5c28e6", "fd70a3d70a3d7094", "feb851eb851eb842" };
        byte[][] splitKeys = new byte[keys.length][];
        TreeSet<byte[]> rows = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);//升序排序
        for (int i = 0; i < keys.length; i++) {
            rows.add(Bytes.toBytes(keys[i]));
        }
        Iterator<byte[]> rowKeyIter = rows.iterator();
        int i=0;
        while (rowKeyIter.hasNext()) {
            byte[] tempRow = rowKeyIter.next();
            rowKeyIter.remove();
            splitKeys[i] = tempRow;
            i++;
        }
        return splitKeys;
    }

    /**
     * 创建hbase表
     * @param storeName
     * @return
     */
    @Override
    public boolean createTable(String storeName) {
        //原表名加前缀
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        byte[] tableNameAsBytes = Bytes.toBytes(tableName);
        try {
            //创建表
            HBaseAdmin admin = new HBaseAdmin(config);
            //验证表是否存在
            if (admin.tableExists(tableNameAsBytes)) {
                logger.info("hbase中已存在该表名，表名为" + tableName);
                return true;
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            //添加列族
            HColumnDescriptor dataColumn = new HColumnDescriptor(
                    paramService.getHbaseDataFamily());
            tableDescriptor.addFamily(dataColumn);

            //添加分区策略：按照文件大小分（当分区的文件超过默认大小，会一分为二）
            tableDescriptor.setRegionSplitPolicyClassName(ConstantSizeRegionSplitPolicy.class.getName());
            admin.createTable(tableDescriptor,getSplitKeys());
            return true;
        } catch (IOException e) {
            logger.info("创建表失败，表名：" + tableName);
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 添加数据
     * @param storeName
     * @param rowId
     * @param data
     * @param param
     * @return
     */
    @Override
    public String putData(String storeName, final String rowId, final byte[] data, final byte[] param) {
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        return hbaseTemplate.execute(tableName, new TableCallback<String>() {
            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowId));
                if (data != null && param != null) {
                    //数据存储
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseDataColumn(), data);
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseParamColumn(), param);
                }
                table.put(put);
                return rowId;
            }
        });
    }

    /**
     * 扫描数据
     * @param storeName
     * @param rowKey
     * @param validTime
     * @return
     * @throws Exception
     */
    @Override
    public String getDataList(String storeName, String rowKey, long validTime) throws Exception {
        final String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        String familyName = new String(paramService.getHbaseDataFamily());
        String qualifier = new String(paramService.getHbaseDataColumn());
        final byte[] fa = Bytes.toBytes(familyName);
        final byte[] qu = Bytes.toBytes(qualifier);
        Long currentTime = new Date().getTime();
        Scan scan = new Scan();
        //扫描某列
        scan.addColumn(fa, qu);
        //过滤出保质期内的数据
        scan.setTimeRange(currentTime - validTime, currentTime);
        //过滤出相同查询参数的数据
        String rowStr = rowKey.substring(0, 16);
        scan.setStartRow(Bytes.toBytes(rowStr));
        scan.setStopRow(Bytes.toBytes(rowStr + "9"));
        return hbaseTemplate.find(tableName, scan, new ResultsExtractor<String>() {
            @Override
            public String extractData(ResultScanner resultScanner) throws Exception {
                Long time = 0l;
                String str = null;
                for (Result result : resultScanner) {
                    for (KeyValue kv : result.raw()) {
                        Long temp = kv.getTimestamp();
                        String tempStr = new String(kv.getValue(), "UTF-8");
                        if (time.equals(0l)) {
                            time = temp;
                            str = tempStr;
                        } else if (temp - time > 0) {
                            time = temp;
                            str = tempStr;
                        }
                    }
                }
                return str;
            }
        });
    }

    /**
     * 因为修改了rowkey产生的策略，rowkey并不能直接确定，所以此方法过时
     * @param storeName
     * @param rowKey
     * @return
     */
    @Deprecated
    @Override
    public byte[] getDataInfo(String storeName, String rowKey) {
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        String familyName = new String(paramService.getHbaseDataFamily());
        String qualifier = new String(paramService.getHbaseDataColumn());
        return hbaseTemplate.get(tableName, rowKey, familyName, qualifier, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(Result result, int i) throws Exception {
                return result.value();
            }
        });
    }

    /**
     * 因为修改了rowkey产生的策略，rowkey并不能直接确定，所以此方法过时
     * @param storeName
     * @param rowKey
     * @return
     */
    @Deprecated
    @Override
    public byte[] getDataCrtTime(String storeName, String rowKey) {
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        String familyName = new String(paramService.getHbaseDataFamily());
        String qualifier = new String(paramService.getHbaseParamColumn());
        return hbaseTemplate.get(tableName, rowKey, familyName, qualifier, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(Result result, int i) throws Exception {
                return result.value();
            }
        });
    }

}
