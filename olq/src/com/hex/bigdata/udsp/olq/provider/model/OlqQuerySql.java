package com.hex.bigdata.udsp.olq.provider.model;

import com.hex.bigdata.udsp.common.provider.model.Page;

/**
 * 联机查询SQL查询对象
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/14
 * TIME:14:17
 */
public class OlqQuerySql {

    private OlqQuerySql() {
    }


    /**
     * 联机查询适用
     *
     * @param originalSql
     */
    public OlqQuerySql(String originalSql) {
        if (originalSql.lastIndexOf(";") == originalSql.length()) {
            originalSql = originalSql.substring(0, originalSql.length() - 1);
        }
        this.originalSql = originalSql;
    }

    /**
     * 联机查询应用适用
     *
     * @param originalSql
     * @param page
     */
    public OlqQuerySql(String originalSql, Page page) {
        if (originalSql.lastIndexOf(";") == originalSql.length()) {
            originalSql = originalSql.substring(0, originalSql.length() - 1);
        }
        this.page = page;
        this.originalSql = originalSql;
    }

    /**
     * 原生SQL
     */
    private String originalSql;

    /**
     * 分页信息
     */
    private Page page;

    /**
     * 分页查询SQL
     */
    private String pageSql;

    /**
     * 查询总记录数SQL
     */
    private String totalSql;

    public String getOriginalSql() {
        return originalSql;
    }

    public void setOriginalSql(String originalSql) {
        this.originalSql = originalSql;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getPageSql() {
        return pageSql;
    }

    public void setPageSql(String pageSql) {
        this.pageSql = pageSql;
    }

    public String getTotalSql() {
        return totalSql;
    }

    public void setTotalSql(String totalSql) {
        this.totalSql = totalSql;
    }

    @Override
    public String toString() {
        return "OLQQuerySql{" +
                "originalSql='" + originalSql + '\'' +
                ", page=" + page +
                ", pageSql='" + pageSql + '\'' +
                ", totalSql='" + totalSql + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String originalSql = "select \"ID\", \"BNYE\", \"BWYE\"   from \"EASYCORE\".\"A\"";
        if (originalSql.lastIndexOf(";") == originalSql.length()) {
            originalSql = originalSql.substring(0, originalSql.length() - 1);
        }
        System.out.println(originalSql);
    }


}
