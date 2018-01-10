package com.hex.bigdata.udsp.ed.connect.service.impl;

import com.hex.bigdata.udsp.ed.connect.service.DataStoreService;
import com.hex.bigdata.udsp.ed.connect.service.ParamService;
import com.hex.bigdata.udsp.ed.connect.util.HBasePool;
import com.hex.bigdata.udsp.ed.connect.util.TableUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                return false;
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            //添加列族
            HColumnDescriptor dataColumn = new HColumnDescriptor(
                    paramService.getHbaseDataFamily());
            tableDescriptor.addFamily(dataColumn);

            admin.createTable(tableDescriptor);
        } catch (IOException e) {
            logger.info("创建表失败，表名：" + tableName);
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String putData(String tableName, final String rowId, final byte[] data, final byte[] crtTime) {
        return hbaseTemplate.execute(tableName, new TableCallback<String>() {
            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowId));
                if (data != null && crtTime != null) {
                    //数据存储
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseDataColumn(), data);
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseCrtTimeColumn(), crtTime);
                }
                table.put(put);
                return rowId;
            }
        });
    }

    @Override
    public byte[] getDataInfo(String tableName, String rowKey) {
        String familyName = new String(paramService.getHbaseDataFamily());
        String qualifier = new String(paramService.getHbaseDataColumn());
        return hbaseTemplate.get(tableName, rowKey, familyName, qualifier, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(Result result, int i) throws Exception {
                return result.value();
            }
        });
    }

    @Override
    public byte[] getDataCrtTime(String tableName, String rowKey) {
        String familyName = new String(paramService.getHbaseDataFamily());
        String qualifier = new String(paramService.getHbaseCrtTimeColumn());
        return hbaseTemplate.get(tableName, rowKey, familyName, qualifier, new RowMapper<byte[]>() {
            @Override
            public byte[] mapRow(Result result, int i) throws Exception {
                return result.value();
            }
        });
    }

    public boolean deleteRow(String tableName, String rowKey) {
        try {
            HTable table = new HTable(config, tableName);
            List<Delete> list = new ArrayList<Delete>();
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            list.add(delete);
            table.delete(delete);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteRows(String tableName, List<String> rowKeys) {
        List<Delete> list = new ArrayList<Delete>();
        for (String rowKey : rowKeys) {
            Delete delete = new Delete(Bytes.toBytes(rowKey));
            list.add(delete);
        }
        try {
            if (list != null && list.size() > 0) {
                HTable table = new HTable(config, tableName);
                table.delete(list);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    public boolean setup(String storeName) {
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        String realPath = this.getClass().getResource("/").getPath();

        String destDirPath = realPath + TableUtil.ROOTDIR + tableName;
        String srcDirPath = realPath + TableUtil.TEMPLATEDIR;

        File destDir = new File(destDirPath);
        File srcDir = new File(srcDirPath);

        //复制配置文件到目标文件夹
        try {
            FileUtils.copyDirectory(srcDir, destDir);
            //修改配置文件中的占位符
            modifyFileContent(destDir, tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //执行shell脚本
        String command = "sh " + destDirPath + "/setup.sh >> /tmp/udsp/log/setup_" + tableName + "_" + dateFormat.format(new Date()) + ".log";

        logger.info("start command:" + command);
        exeShell(command);
        logger.info("end command:" + command);

        return true;
    }


    public boolean cleanup(String storeName) {
        String tableName = TableUtil.getTableName(storeName, paramService.getUdspStorePrefix());
        String realPath = this.getClass().getResource("/").getPath();

        String destDirPath = realPath + TableUtil.ROOTDIR + tableName;
        String srcDirPath = realPath + TableUtil.TEMPLATEDIR;

        File destDir = new File(destDirPath);
        File srcDir = new File(srcDirPath);

        //复制配置文件到目标文件夹
        try {
            FileUtils.copyDirectory(srcDir, destDir);
            //修改配置文件中的占位符
            modifyFileContent(destDir, tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //执行shell脚本
        String command = "sh " + destDirPath + "/cleanup.sh >> /tmp/udsp/log/cleanup_" + tableName + "_" + dateFormat.format(new Date()) + ".log";

        logger.info("start command:" + command);
        exeShell(command);
        logger.info("end command:" + command);

        return true;
    }


    /*@Override
    public String updateData(String tableName, final String rowId, final byte[] data, final byte[] crtTime) {
        return hbaseTemplate.execute(tableName, new TableCallback<String>() {
            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowId));
                if (data != null && crtTime != null) {
                    //数据存储
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseDataColumn(), data);
                    put.addColumn(paramService.getHbaseDataFamily(), paramService.getHbaseCtrTimeColumn(), crtTime);
                }
                table.put(put);
                return rowId;
            }
        });
    }*/



    /*@Override
    public FileData getTotalData(String tableName, String rowKey) {
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<FileData>() {
            @Override
            public FileData mapRow(Result result, int i) throws Exception {
                Map<String, byte[]> map = new HashMap<String, byte[]>();
                if (result.isEmpty()) {
                    return null;
                }
                byte[] data = result.getColumnCells(paramService.getHbaseDataFamily(), paramService.getHbaseDataColumn()).get(0).getValue();
                for (Map.Entry<byte[], byte[]> entry : result.getFamilyMap(paramService.getHbaseMetaFamily()).entrySet()) {
                    map.put(Bytes.toString(entry.getKey()), entry.getValue());
                }
                Meta meta = Meta.fromMap(map);
                return new FileData(meta, data);
            }
        });
    }*/


    public boolean tableExist(String tableName) {
        byte[] tableNameAsBytes = Bytes.toBytes(tableName);
        HBaseAdmin admin = null;
        try {
            admin = new HBaseAdmin(config);
            if (admin.tableExists(tableNameAsBytes)) {
                logger.info("hbase中已存在该表名，表名为" + tableName);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*@Override
    public List<FileData> findFilesByPage(String tableName, String starRow, String stopRow, int pageIndex, int pageSize) {
        Scan scan = new Scan();
        if (StringUtils.isNotBlank(starRow)) {
            scan.setStartRow(Bytes.toBytes(starRow));
        }
        if (StringUtils.isNotBlank(stopRow)) {
            scan.setStopRow(Bytes.toBytes(stopRow));
        }
        int scanNum = pageSize * pageIndex; // 扫描的数据条数
        PageFilter pageFilter = new PageFilter(scanNum);
        scan.setFilter(pageFilter);

        final int befNum = pageSize * (pageIndex - 1); // 不需要显示的数据条数

        return hbaseTemplate.find(tableName, scan, new ResultsExtractor<List<FileData>>() {
            @Override
            public List<FileData> extractData(ResultScanner resultScanner) throws Exception {
                List<FileData> fileDatas = new ArrayList<FileData>();
                int count = 0;
                for (Result result : resultScanner) {
                    count++;
                    if (count > befNum) {
                        FileData fileData = new FileData();
                        Map<String, byte[]> map = new HashMap<String, byte[]>();
                        //byte[] data=result.getColumnCells(ParamUtils.dataFamily,ParamUtils.dataColumn).get(0).getValue();
                        for (Map.Entry<byte[], byte[]> entry : result.getFamilyMap(paramService.getHbaseMetaFamily()).entrySet()) {
                            map.put(Bytes.toString(entry.getKey()), entry.getValue());
                        }
                        Meta meta = Meta.fromMap(map);
                        fileData.setId(Bytes.toString(result.getRow()));
                        //fileData.setData(data);
                        fileData.setMeta(meta);
                        fileDatas.add(fileData);
                    }
                }
                return fileDatas;
            }
        });

    }*/

    /**
     * 修改文件中的字符
     *
     * @param file
     * @param tableName
     */
    private static void modifyFileContent(File file, String tableName) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File tempfile : files) {
                modifyFileContent(tempfile, tableName);
            }
        } else {
            String content = "";
            try {
                content = FileUtils.readFileToString(file);
                if (!StringUtils.isBlank(content)) {
                    content = content.replace(TableUtil.PLACEHOLDER, tableName);
                    FileUtils.writeStringToFile(file, content);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * java执行shell命令
     *
     * @param command
     */
    private static void exeShell(String command) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NoSql执行者方法
     *
     * @param command
     * @param <T>
     * @return
     * @throws IOException
     */
    private static <T> T noSqlExecutor(TableName tableName, NoSqlCommand command) throws IOException {
        Connection conn = null;
        Table table = null;
        try {
            conn = pool.getConnection();
            table = conn.getTable(tableName);
            return command.execute(conn, table);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (table != null) {
                table.close();
            }
            if (conn != null) {
                //conn.close();
                pool.release(conn);
            }
        }
    }

    /**
     * NoSql指令的接口
     */
    static interface NoSqlCommand {
        /**
         * 执行
         *
         * @param conn
         * @param table
         * @param <T>
         * @return
         * @throws IOException
         */
        <T> T execute(Connection conn, Table table) throws IOException;
    }
}
