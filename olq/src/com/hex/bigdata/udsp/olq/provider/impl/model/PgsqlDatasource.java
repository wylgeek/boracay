package com.hex.bigdata.udsp.olq.provider.impl.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by PC on 2017/3/10.
 */
public class PgsqlDatasource extends JdbcDatasource {

    public PgsqlDatasource(List<Property> properties) {
        super(properties);
    }

    public PgsqlDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public PgsqlDatasource(Datasource datasource) {
        super(datasource);
    }

    public PgsqlDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "org.postgresql.Driver";
         return value;
    }

}
