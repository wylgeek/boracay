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
public class DB2Datasource extends JdbcDatasource {

    public DB2Datasource(List<Property> properties) {
        super(properties);
    }

    public DB2Datasource(Map<String, Property> propertieMap) {
        super(propertieMap);
    }

    public DB2Datasource(Datasource datasource) {
        super(datasource);
    }

    public DB2Datasource(ComDatasource comDatasource, List<ComProperties> comPropertieList) {
        super(comDatasource, comPropertieList);
    }

    public String getDriverClass() {
        String value = getProperty("driver.class").getValue();
        if (StringUtils.isBlank(value))
            value = "com.ibm.db2.jcc.DB2Driver";
         return value;
    }

}
