package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class HiveModel extends JdbcModel {
    public HiveModel() {
    }

    public HiveModel(List<Property> properties) {
        super(properties);
    }

    public HiveModel(Map<String, Property> propertyMap) {
        super(propertyMap);
    }

    public HiveModel(Model model) {
        super(model);
    }

    public HiveModel(List<Property> properties, Datasource srcDatasource) {
        super(properties, srcDatasource);
    }
}
