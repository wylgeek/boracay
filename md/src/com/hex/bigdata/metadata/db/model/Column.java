package com.hex.bigdata.metadata.db.model;

/**
 * Created by junjiem on 2016-6-20.
 */
public class Column {
    private int seq; // 字段位置
    private String name; // 字段名称
    private String type; // 字段类型
    private String length; // 字段长度
    private String comment; // 字段注释
    private boolean nullable; // 是否允许为空（true:允许为空，false:不允许为空）
    private int primaryKeyN; // 主键字段位置（不是主键字段，值为0）
    private int partitionFieldN; // 分区字段位置（不是分区字段，值为0）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getPrimaryKeyN() {
        return primaryKeyN;
    }

    public void setPrimaryKeyN(int primaryKeyN) {
        this.primaryKeyN = primaryKeyN;
    }

    public int getPartitionFieldN() {
        return partitionFieldN;
    }

    public void setPartitionFieldN(int partitionFieldN) {
        this.partitionFieldN = partitionFieldN;
    }
}
