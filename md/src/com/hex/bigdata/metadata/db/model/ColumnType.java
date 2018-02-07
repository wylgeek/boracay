package com.hex.bigdata.metadata.db.model;

/**
 * Created by junjiem on 2016-6-27.
 */
public class ColumnType {
    private String name;
    private int precision;
    private String createParams;
    private int minScale;
    private int maxScale;

    public ColumnType() {
        super();
    }

    public ColumnType(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String getCreateParams() {
        return createParams;
    }

    public void setCreateParams(String createParams) {
        this.createParams = createParams;
    }

    public int getMinScale() {
        return minScale;
    }

    public void setMinScale(int minScale) {
        this.minScale = minScale;
    }

    public int getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(int maxScale) {
        this.maxScale = maxScale;
    }
}
