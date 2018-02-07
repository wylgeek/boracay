package com.hex.bigdata.metadata.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junjiem on 2016-6-20.
 */
public class Database {
    private String name; // 数据库名称
    private String comment; // 数据库注释
    private List<Table> tables;

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

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public void append(Table tb) {
        if (this.tables == null) {
            this.tables = new ArrayList<Table>();
        }
        this.tables.add(tb);
    }
}
