package com.hex.bigdata.udsp.im.provider.impl.model.modeling;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.provider.model.Model;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-9-5.
 */
public class SolrModel extends Model {

    public SolrModel(Model model) {
        super(model);
    }

    public SolrModel(List<Property> properties, Datasource srcDatasource) {
        super(properties, srcDatasource);
    }

    public String getCollectionName() {
        String value = getProperty("solr.collection.name").getValue();
        if (StringUtils.isBlank(value))
            throw new IllegalArgumentException("solr.collection.name不能为空");
        return value;
    }

    public boolean getViolenceQuery() {
        String value = getProperty("violence.query").getValue();
        if (StringUtils.isBlank(value))
            value = "true";
        return Boolean.valueOf(value);
    }
}
