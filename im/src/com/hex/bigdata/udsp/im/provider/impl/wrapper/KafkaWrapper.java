package com.hex.bigdata.udsp.im.provider.impl.wrapper;

import com.hex.bigdata.udsp.im.provider.RealtimeSourceProvider;
import com.hex.bigdata.udsp.im.provider.impl.util.model.ValueColumn;
import com.hex.bigdata.udsp.im.provider.impl.util.model.WhereProperty;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.provider.model.ModelMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by JunjieM on 2017-9-7.
 */
public abstract class KafkaWrapper extends Wrapper implements RealtimeSourceProvider {
    private static Logger logger = LogManager.getLogger(KafkaWrapper.class);

    @Override
    protected void insertInto(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns) throws Exception {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateInsert(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void matchingUpdate(Metadata metadata, List<ModelMapping> modelMappings, List<ValueColumn> valueColumns, List<WhereProperty> whereProperties) throws Exception {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<String> getSelectColumns(List<ModelMapping> modelMappings, Metadata metadata) {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void emptyDatas(Metadata metadata) throws IOException, Exception {
        try {
            throw new Exception("不支持该方法");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
