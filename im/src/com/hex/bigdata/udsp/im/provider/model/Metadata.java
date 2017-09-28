package com.hex.bigdata.udsp.im.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Base;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.constant.MetadataStatus;
import com.hex.bigdata.udsp.im.constant.MetadataType;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class Metadata extends Base {
    private String name;

    private String describe;

    private String note;

    private String tbName; // 库表

    private MetadataStatus status; // 状态

    private MetadataType type; // 类型

    private Datasource datasource; // 数据源

    private List<MetadataCol> metadataCols; // 字段集合

    public Metadata() {
    }

    public Metadata(List<Property> properties) {
        super(properties);
    }

    public Metadata(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public Metadata(Metadata metadata) {
        super(metadata.getPropertyMap());
        this.name = metadata.getName();
        this.describe = metadata.getDescribe();
        this.note = metadata.getNote();
        this.tbName = metadata.tbName;
        this.status = metadata.getStatus();
        this.type = metadata.getType();
        this.datasource = metadata.getDatasource();
        this.metadataCols = metadata.getMetadataCols();
    }

    public MetadataStatus getStatus() {
        if (status == null)
            throw new IllegalArgumentException("status不能为空");
        return status;
    }

    public void setStatus(MetadataStatus status) {
        this.status = status;
    }

    public MetadataType getType() {
        if (type == null)
            throw new IllegalArgumentException("type不能为空");
        return type;
    }

    public void setType(MetadataType type) {
        this.type = type;
    }

    public String getTbName() {
        if (StringUtils.isBlank(tbName))
            throw new IllegalArgumentException("tbName不能为空");
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getName() {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("name不能为空");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Datasource getDatasource() {
        if (datasource == null)
            throw new IllegalArgumentException("datasource不能为空");
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public List<MetadataCol> getMetadataCols() {
        if (metadataCols == null || metadataCols.size() == 0)
            throw new IllegalArgumentException("metadataCols不能为空");
        return metadataCols;
    }

    public void setMetadataCols(List<MetadataCol> metadataCols) {
        this.metadataCols = metadataCols;
    }
}
