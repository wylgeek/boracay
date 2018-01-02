package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.util.CharUtil;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.im.provider.BatchTargetProvider;
import com.hex.bigdata.udsp.im.provider.RealtimeTargetProvider;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseAdminPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.factory.HBaseConnectionPoolFactory;
import com.hex.bigdata.udsp.im.provider.impl.model.datasource.HBaseDatasource;
import com.hex.bigdata.udsp.im.provider.impl.model.metadata.HBaseMetadata;
import com.hex.bigdata.udsp.im.provider.impl.util.HBaseUtil;
import com.hex.bigdata.udsp.im.provider.impl.util.model.*;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class HBaseWrapper extends Wrapper implements BatchTargetProvider, RealtimeTargetProvider {

    private static Logger logger = LogManager.getLogger(HBaseWrapper.class);

    protected static final String HIVE_ENGINE_STORAGE_HANDLER_CLASS = "org.apache.hadoop.hive.hbase.HBaseStorageHandler";

    protected List<TableColumn> getTargetColumns(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<TableColumn> columns = new ArrayList<>();
        columns.add(new TableColumn("KEY", DataType.STRING.getValue()));
        columns.add(new TableColumn("VAL", DataType.STRING.getValue()));
        return columns;
    }

    protected List<TblProperty> getTblProperties(String tableName) {
        List<TblProperty> tblProperties = new ArrayList<>();
        tblProperties.add(new TblProperty("hbase.table.name", tableName));
        //tblProperties.add(new TblProperty("hbase.mapred.output.outputtable", tableName));
        return tblProperties;
    }

    protected List<SerDeProperty> getSerDeProperties(List<ModelMapping> modelMappings, HBaseMetadata hbaseMetadata) {
        List<SerDeProperty> serDeProperties = new ArrayList<>();
        String family = hbaseMetadata.getFamily();
        String qualifier = hbaseMetadata.getQualifier();
        String hbaseColumnsMapping = ":key," + family + ":" + qualifier;
        serDeProperties.add(new SerDeProperty("hbase.columns.mapping", hbaseColumnsMapping));
        return serDeProperties;
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        if (modelMappings == null || modelMappings.size() == 0)
            return null;
        List<String> selectColumns = new ArrayList<>();
        HBaseMetadata hBaseMetadata = new HBaseMetadata(metadata);
        String fqDataType = hBaseMetadata.getFqDataType();
        String fqDsvSeprator = hBaseMetadata.getFqDsvSeprator();
        // 按照目标元数据字段升序
        List<ModelMapping> newModelMappings = new ArrayList<>();
        newModelMappings.addAll(modelMappings);
        Collections.sort(newModelMappings, new Comparator<ModelMapping>() {
            @Override
            public int compare(ModelMapping o1, ModelMapping o2) {
                return o1.getMetadataCol().getSeq().compareTo(o2.getMetadataCol().getSeq());
            }
        });
        /*
        根据目标元数据字段信息获取val值
         */
        String val = "";
        List<MetadataCol> vals = new ArrayList<>();
        for (ModelMapping mapping : newModelMappings) {
            String sName = mapping.getName();
            MetadataCol mdCol = mapping.getMetadataCol();
            String tName = mdCol.getName();
            mdCol.setNote(tName);
            mdCol.setName(sName);
            if (mdCol.isStored()) vals.add(mdCol);
        }
        if ("json".equalsIgnoreCase(fqDataType)) {
            val = getJsonValueSql(vals);
        } else {
            val = getDsvValueSql(vals, fqDsvSeprator);
        }
        logger.debug("VALUE=>" + val);
        /*
         根据目标元数据字段信息获取key值
         */
        int count = 0;
        for (ModelMapping mapping : newModelMappings) {
            if (mapping.getMetadataCol().isPrimary()) count++;
        }
        String key = null;
        if (count == 0) { // 没有主键
            List<MetadataCol> dts = new ArrayList<>();
            List<MetadataCol> keys = new ArrayList<>();
            for (ModelMapping mapping : newModelMappings) {
                MetadataCol mdCol = mapping.getMetadataCol();
                if (mdCol.isIndexed()) {
                    mdCol.setName(mapping.getName());
                    if (DataType.TIMESTAMP == mdCol.getType()) {
                        dts.add(mdCol);
                    } else {
                        keys.add(mdCol);
                    }
                }
            }
            key = getKeySql(keys, dts, vals);
        } else if (count == 1) { // 一个主键
            for (ModelMapping mapping : newModelMappings) {
                String sName = mapping.getName();
                MetadataCol mdCol = mapping.getMetadataCol();
                boolean primary = mdCol.isPrimary();
                if (primary) {
                    key = "NVL(CAST(" + sName + " AS STRING),'') AS KEY";
                    break;
                }
            }
        } else { // 多个主键
            key = "SUBSTR(SYS_MD5(CONCAT_WS('|',";
            for (int i = 0, k = 0; i < newModelMappings.size(); i++) {
                String sName = newModelMappings.get(i).getName();
                MetadataCol mdCol = newModelMappings.get(i).getMetadataCol();
                boolean primary = mdCol.isPrimary();
                if (primary) {
                    key += (k == 0 ? "NVL(CAST(" + sName + " AS STRING),'')"
                            : ",NVL(CAST(" + sName + " AS STRING),'')");
                    k++;
                }
            }
            key += ")),9,16) AS KEY";
        }
        logger.debug("KEY=>" + key);

        selectColumns.add(key);
        selectColumns.add(val);

        return selectColumns;
    }

    private String getKeySql(List<MetadataCol> keys, List<MetadataCol> dts, List<MetadataCol> vals) {
        if ((keys == null || keys.size() == 0) && (dts == null || dts.size() == 0)) {
            throw new IllegalArgumentException("keys和dts不能同时为空");
        }
        String sql = "\nCONCAT_WS('|'";
        // 哈希头
        sql += getHashStrSql(keys);
        // 普通字段
        if (keys != null && keys.size() > 0) {
            for (int i = 0; i < keys.size(); i++) {
                MetadataCol mdCol = keys.get(i);
                String name = mdCol.getName();
                String length = mdCol.getLength();
                int len = getLen(length);
                String space = (len <= 0 ? "" : ",SPACE(" + len + "-LENGTH(CAST(" + name + " AS STRING)))");
                sql += "\n,CONCAT(CAST(" + name + " AS STRING)" + space + ")";
            }
        }
        // 日期字段
        if (dts != null && dts.size() > 0) {
            for (int i = 0; i < dts.size(); i++) {
                MetadataCol mdCol = dts.get(i);
                String name = mdCol.getName();
                String length = mdCol.getLength();
                int len = getLen(length);
                sql += (keys.size() == 0 ? "\n" : "\n,");
                if (len == 19) {
                    sql += "SUBSTR(REGEXP_REPLACE(CAST(" + name + " AS STRING),'/','-'),1,19)";
                } else if (len == 17) {
                    sql += "SUBSTR(REGEXP_REPLACE(REGEXP_REPLACE(CAST(" + name + " AS STRING),'/','-'),'-',''),1,17))";
                } else if (len == 10) {
                    sql += "SUBSTR(REGEXP_REPLACE(CAST(" + name + " AS STRING),'/','-'),1,10)";
                } else if (len == 8) {
                    sql += "SUBSTR(REGEXP_REPLACE(REGEXP_REPLACE(CAST(" + name + " AS STRING),'/','-'),'-',''),1,8))";
                } else {
                    sql += "REGEXP_REPLACE(CAST(" + name + " AS STRING),'/','-')";
                }
            }
        }
        // 哈希尾
        if (vals.size() > 0) {
            sql += getHashStrSql(vals);
        }
        sql += "\n) AS KEY";
        return sql;
    }

    private String getHashStrSql(List<MetadataCol> list) {
        String sql = "";
        if (list != null && list.size() > 0) {
            sql = "\n,SUBSTR(SYS_MD5(CONCAT_WS('|',";
            for (int i = 0; i < list.size(); i++) {
                MetadataCol mdCol = list.get(i);
                String name = mdCol.getName();
                sql += (i == 0 ? "NVL(CAST(" + name + " AS STRING),'')"
                        : ",NVL(CAST(" + name + " AS STRING),'')");
            }
            sql += ")),9,16)";
        }
        return sql;
    }

    protected int getLen(String length) {
        int len = 0;
        if (StringUtils.isNotBlank(length)) {
            try {
                len = Integer.valueOf(length);
            } catch (Exception e) {
                logger.debug(ExceptionUtil.getMessage(e));
            }
        }
        return len;
    }

    private String getJsonValueSql(List<MetadataCol> vals) {
        if (vals == null || vals.size() == 0) {
            throw new IllegalArgumentException("HBase至少要有一个或多个存储字段！");
        }
        String sql = "\nJSON_WS(";
        for (int i = 0; i < vals.size(); i++) {
            MetadataCol metadataCol = vals.get(i);
            String sName = metadataCol.getName();
            String tName = metadataCol.getNote();
            sql += (i == 0 ? "\n'" + tName + "',NVL(CAST(" + sName + " AS STRING),'')"
                    : "\n,'" + tName + "',NVL(CAST(" + sName + " AS STRING),'')");
        }
        sql += "\n) AS VAL";
        return sql;
    }

    private String getDsvValueSql(List<MetadataCol> vals, String seprator) {
        if (vals == null || vals.size() == 0) {
            throw new IllegalArgumentException("HBase至少要有一个或多个存储字段！");
        }
        String sql = "\nCONCAT_WS('" + seprator + "'";
        for (int i = 0; i < vals.size(); i++) {
            MetadataCol metadataCol = vals.get(i);
            String sName = metadataCol.getName();
            sql += "\n,NVL(CAST(" + sName + " AS STRING),'')";
        }
        sql += "\n) AS VAL";
        return sql;
    }

    @Override
    protected void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception {
        checkModelMappings(modelMappings);

        String tableName = metadata.getTbName();
        HBaseDatasource hBaseDatasource = new HBaseDatasource(metadata.getDatasource());
        HBaseMetadata hBaseMetadata = new HBaseMetadata(metadata);
        String fqDataType = hBaseMetadata.getFqDataType();
        String fqDsvSeprator = hBaseMetadata.getFqDsvSeprator();
        String family = hBaseMetadata.getFamily();
        String qualifier = hBaseMetadata.getQualifier();
        // 按照目标元数据字段升序
        Collections.sort(modelMappings, new Comparator<ModelMapping>() {
            @Override
            public int compare(ModelMapping o1, ModelMapping o2) {
                return o1.getMetadataCol().getSeq().compareTo(o2.getMetadataCol().getSeq());
            }
        });
        // 转为Map
        Map<String, String> valueMap = new HashMap<>();
        for (ValueColumn column : valueColumns) {
            valueMap.put(column.getColName(), column.getValue());
        }
        /*
        根据目标元数据字段信息获取val值
         */
        String value = "";
        List<MetadataCol> vals = new ArrayList<>();
        for (ModelMapping mapping : modelMappings) {
            MetadataCol mdCol = mapping.getMetadataCol();
            if (mdCol.isStored()) vals.add(mdCol);
        }
        if ("json".equalsIgnoreCase(fqDataType)) {
            value = getJsonValue(vals, valueMap);
        } else {
            value = getDsvValue(vals, fqDsvSeprator, valueMap);
        }
        logger.debug("VALUE=>" + value);
        /*
         根据目标元数据字段信息获取key值
         */
        int count = 0;
        for (ModelMapping mapping : modelMappings) {
            if (mapping.getMetadataCol().isPrimary()) count++;
        }
        String key = "";
        if (count == 0) { // 没有主键
            List<MetadataCol> dts = new ArrayList<>();
            List<MetadataCol> keys = new ArrayList<>();
            for (ModelMapping mapping : modelMappings) {
                MetadataCol mdCol = mapping.getMetadataCol();
                if (mdCol.isIndexed()) {
                    if (DataType.TIMESTAMP == mdCol.getType()) {
                        dts.add(mdCol);
                    } else {
                        keys.add(mdCol);
                    }
                }
            }
            key = getKey(keys, dts, vals, valueMap);
        } else if (count == 1) { // 一个主键
            for (ModelMapping mapping : modelMappings) {
                MetadataCol mdCol = mapping.getMetadataCol();
                if (mdCol.isPrimary()) {
                    key = valueMap.get(mdCol.getName());
                    break;
                }
            }
        } else { // 多个主键
            key = "";
            for (int i = 0; i < modelMappings.size(); i++) {
                MetadataCol mdCol = modelMappings.get(i).getMetadataCol();
                if (mdCol.isPrimary()) {
                    key += (i == 0 ? "" : "|");
                    key += valueMap.get(mdCol.getName());
                }
            }
            key = md5_16(key);
        }
        logger.debug("KEY=>" + key);
        HBaseUtil.put(hBaseDatasource, tableName, key, family, qualifier, value);
    }

    private void checkModelMappings(List<ModelMapping> modelMappings) {
        boolean flg = false;
        for (ModelMapping modelMapping : modelMappings) {
            if (modelMapping.getMetadataCol().isPrimary()) {
                flg = true;
                break;
            } else if (modelMapping.getMetadataCol().isIndexed()) {
                flg = true;
                break;
            }
        }
        if (!flg) {
            throw new IllegalArgumentException("【映射字段】的目标字段必须要有至少一个主键或者索引字段！");
        }
    }

    private String getKey(List<MetadataCol> keys, List<MetadataCol> dts, List<MetadataCol> vals, Map<String, String> valueMap) {
        if ((keys == null || keys.size() == 0) && (dts == null || dts.size() == 0)) {
            throw new IllegalArgumentException("keys和dts不能同时为空");
        }
        List<String> list = new ArrayList<>();
        String str = null;
        // 哈希头
        str = getHashStr(keys, valueMap);
        if (StringUtils.isNotBlank(str)) list.add(str);
        // 普通字段
        if (keys != null && keys.size() > 0) {
            str = "";
            for (int i = 0; i < keys.size(); i++) {
                MetadataCol mdCol = keys.get(i);
                String name = mdCol.getName();
                String value = valueMap.get(name);
                if (value == null) value = "";
                int len = getLen(mdCol.getLength());
                str += (i == 0 ? "" : "|");
                str += (len <= 0 ? value : realValue(value, len));
            }
        }
        list.add(str);
        // 日期字段
        if (dts != null && dts.size() > 0) {
            str = "";
            for (int i = 0; i < dts.size(); i++) {
                MetadataCol mdCol = dts.get(i);
                String name = mdCol.getName();
                String value = valueMap.get(name);
                if (value == null) value = "";
                int len = getLen(mdCol.getLength());
                str += (i == 0 ? "" : "|");
                if (len == 10 && value.length() > 10) {
                    str += value.substring(0, 10);
                } else if (len == 8 && value.length() > 8) {
                    str += replaceDateStr(value).substring(0, 8);
                } else {
                    str += value;
                }
            }
        }
        list.add(str);
        // 哈希尾
        str = getHashStr(vals, valueMap);
        if (StringUtils.isNotBlank(str)) list.add(str);
        return StringUtils.join(list, "|");
    }

    // 获取不同编码的字符串长度
    private int countCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes(code).length;
    }

    //得到需要的字符串
    private String realValue(String value, int length) {
        int len = 0;
        try {
            len = countCode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (len < length) {
            for (int i = 0; i < length - len; i++) {
                value = value + ' ';
            }
        }
        return value;
    }

    // 转为8位日期
    private String replaceDateStr(String dataStr) {
        dataStr = dataStr.replaceAll("-", "");
        dataStr = dataStr.replaceAll("/", "");
        return dataStr;
    }

    private String getHashStr(List<MetadataCol> list, Map<String, String> valueMap) {
        if (list != null && list.size() > 0) {
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                String key = list.get(i).getName();
                str += (i == 0 ? "" : "|");
                str += valueMap.get(key);
            }
            return md5_16(str);
        }
        return null;
    }

    //得到16位的MD5
    private String md5_16(String str) {
        return DigestUtils.md5Hex(str).substring(8, 24);
    }

    private String getJsonValue(List<MetadataCol> vals, Map<String, String> valueMap) {
        if (vals == null || vals.size() == 0) {
            throw new IllegalArgumentException("HBase至少要有一个或多个存储字段！");
        }
        Map<String, String> map = new HashMap<>();
        for (MetadataCol col : vals) {
            String key = col.getName();
            map.put(key, valueMap.get(key));
        }
        return JSONUtil.parseMap2JSON(map);
    }

    private String getDsvValue(List<MetadataCol> vals, String seprator, Map<String, String> valueMap) throws Exception {
        if (vals == null || vals.size() == 0) {
            throw new IllegalArgumentException("HBase至少要有一个或多个存储字段！");
        }
        List<String> list = new ArrayList<>();
        for (MetadataCol col : vals) {
            list.add(valueMap.get(col.getName()));
        }
        return StringUtils.join(list, CharUtil.ascii2Char(seprator));
    }

    @Override
    protected void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        insertInto(metadata, modelMappings, valueColumns);
    }

    @Override
    protected void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        insertInto(metadata, modelMappings, valueColumns);
    }
}
