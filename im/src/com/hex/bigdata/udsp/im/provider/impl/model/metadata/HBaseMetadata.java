package com.hex.bigdata.udsp.im.provider.impl.model.metadata;

import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HBaseMetadata extends Metadata {
    public HBaseMetadata() {
    }

    public HBaseMetadata(List<Property> properties) {
        super(properties);
    }

    public HBaseMetadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public HBaseMetadata(Metadata metadata) {
        super(metadata);
    }

    public int getRegionNum() {
        String value = getProperty("hbase.region.num").getValue();
        if (StringUtils.isBlank(value) || !StringUtils.isNumeric(value))
            throw new IllegalArgumentException("hbase.region.num不能为空且必须是整数");
        return Integer.valueOf(value);
    }

    public String getCompression() {
        String value = getProperty("hbase.compression").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.compression不能为空");
            value = "snappy";
        return value;
    }

    public String getMethod() {
        String value = getProperty("hbase.method").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.method不能为空");
            value = "table_att";
        return value;
    }

    public String getSplitPolicy() {
        String value = getProperty("hbase.split.policy").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.split.policy不能为空");
            value = "org.apache.hadoop.hbase.regionserver.ConstantSizeRegionSplitPolicy";
        return value;
    }

    public String getFamily() {
        String value = getProperty("hbase.family").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.family不能为空");
            value = "f";
        return value;
    }

    public String getQualifier() {
        String value = getProperty("hbase.qualifier").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.qualifier不能为空");
            value = "q";
        return value;
    }

    public String getFqDataType() {
        String value = getProperty("hbase.fq.data.type").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.fq.data.type不能为空");
            value = "dsv";
        return value;
    }

    public String getFqDsvSeprator() {
        String value = getProperty("hbase.fq.dsv.seprator").getValue();
        if (StringUtils.isBlank(value))
            //throw new IllegalArgumentException("hbase.fq.dsv.seprator不能为空");
            value = "\007";
        return value;
    }

}
