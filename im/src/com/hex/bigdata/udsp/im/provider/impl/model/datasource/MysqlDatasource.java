package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class MysqlDatasource extends JdbcDatasource {

    public MysqlDatasource(Datasource datasource) {
        super(datasource);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "com.mysql.jdbc.Driver";
        return value;
    }

}
