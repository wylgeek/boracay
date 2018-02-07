package com.hex.bigdata.udsp.olq.provider.impl;

import com.hex.bigdata.udsp.common.provider.model.Page;
import com.hex.bigdata.udsp.olq.provider.model.OlqQuerySql;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by junjiem on 2017-2-15.
 */
@Component("com.hex.bigdata.udsp.olq.provider.impl.InceptorProvider")
public class InceptorProvider extends JdbcProvider {
    private static Logger logger = LogManager.getLogger(InceptorProvider.class);

    protected OlqQuerySql getPageSql(String sql, Page page) {
        OlqQuerySql olqQuerySql = new OlqQuerySql(sql);
        if (page == null || !sql.toUpperCase().trim().contains("SELECT")) {
            return olqQuerySql;
        }
        //分页sql组装
        int pageSize = page.getPageSize();
        int pageIndex = page.getPageIndex();
        pageIndex = (pageIndex == 0 ? 1 : pageIndex);
        int startRow = (pageIndex - 1) * pageSize;
        StringBuffer pageSqlBuffer = new StringBuffer("SELECT * FROM (").append(sql)
                .append(" ) UDSP_VIEW LIMIT ").append(pageSize)
                .append(", ").append(startRow);
        olqQuerySql.setPageSql(pageSqlBuffer.toString());
        //总记录数查询SQL组装
        StringBuffer totalSqlBuffer = new StringBuffer("SELECT COUNT(1) FROM (");
        totalSqlBuffer.append(sql);
        totalSqlBuffer.append(") UDSP_VIEW");
        olqQuerySql.setTotalSql(totalSqlBuffer.toString());
        //page设置
        olqQuerySql.setPage(page);
        logger.debug("配置的源SQL:\n" + olqQuerySql.getOriginalSql());
        logger.debug("分页查询SQL:\n" + olqQuerySql.getPageSql());
        logger.debug("查询总数SQL:\n" + olqQuerySql.getTotalSql());
        return olqQuerySql;
    }
}
