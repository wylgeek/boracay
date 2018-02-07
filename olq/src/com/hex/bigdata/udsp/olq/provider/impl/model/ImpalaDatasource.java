package com.hex.bigdata.udsp.olq.provider.impl.model;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by junjiem on 2017-2-20.
 */
public class ImpalaDatasource extends JdbcDatasource {

    public ImpalaDatasource(List<Property> properties) {
        super(properties);
    }

    public ImpalaDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public ImpalaDatasource(Datasource datasource) {
        super(datasource);
    }

    public ImpalaDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "org.apache.hive.jdbc.HiveDriver";
        return value;
    }

}
