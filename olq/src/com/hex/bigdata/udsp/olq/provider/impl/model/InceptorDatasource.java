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
public class InceptorDatasource extends JdbcDatasource {

    public InceptorDatasource(List<Property> properties) {
        super(properties);
    }

    public InceptorDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public InceptorDatasource(Datasource datasource) {
        super(datasource);
    }

    public InceptorDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "org.apache.hive.jdbc.HiveDriver";
         return value;
    }

}
