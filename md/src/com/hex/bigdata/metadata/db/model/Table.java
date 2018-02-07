package com.hex.bigdata.metadata.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junjiem on 2016-6-20.
 */
public class Table {
    private String name; // 表名称
    private String comment; // 表注释
    private List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void append(Column col) {
        if (this.columns == null) {
            this.columns = new ArrayList<Column>();
        }
        this.columns.add(col);
    }
}
