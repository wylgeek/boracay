package com.hex.bigdata.udsp.im.provider.model;

import com.hex.bigdata.udsp.common.constant.DataType;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class ModelMapping implements Serializable {
    private Short seq;

    private String name;

    private String note;

    private DataType type;

    private String length;

    private String describe;

    private boolean indexed;

    private boolean primary;

    private boolean stored;

    private MetadataCol metadataCol; // 元数据字段

    public Short getSeq() {
        if (seq == null)
            throw new IllegalArgumentException("seq不能为空");
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public MetadataCol getMetadataCol() {
        if (metadataCol == null)
            throw new IllegalArgumentException("metadataCol不能为空");
        return metadataCol;
    }

    public void setMetadataCol(MetadataCol metadataCol) {
        this.metadataCol = metadataCol;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isIndexed() {
        return indexed;
    }

    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }
}
