package com.hex.bigdata.udsp.im.provider.impl.util.model;

import com.hex.bigdata.udsp.common.constant.DataType;

/**
 * Created by JunjieM on 2017-9-25.
 */
public class ValueColumn {
    private String colName; // 字段名
    private DataType dataType; // 类型
    private String length; //长度
    private String value; // 值

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
