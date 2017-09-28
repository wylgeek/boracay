package com.hex.bigdata.udsp.im.provider.impl.model.datasource;

import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.provider.model.Property;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class OracleDatasource extends JdbcDatasource {

    public OracleDatasource(List<Property> properties) {
        super(properties);
    }

    public OracleDatasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public OracleDatasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "oracle.jdbc.OracleDriver";
        return value;
    }
}
