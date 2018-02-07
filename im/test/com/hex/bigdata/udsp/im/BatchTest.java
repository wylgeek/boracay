package com.hex.bigdata.udsp.im;

import com.hex.bigdata.udsp.common.constant.DataType;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.im.constant.*;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.bigdata.udsp.im.service.BatchJobService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchTest {
    public static void main(String[] args){

        //数据源所需字段
        String name = "tzb_solr_hbase";
        String type = "SOLR_HBASE";
        String describe = "台州银行大数据开发环境SOLR+HBASE使用处理方式";
        String note = "求解";
        String implClass = "TextClass";

        //数据源参数配置
        Property property = new Property();
        property.setName("hbase.zk.port");
        property.setValue("25008");
        property.setDescribe("HBase的Zookeeper的接口，如2181");

        Property property1 = new Property();
        property1.setName("hbase.zk.quorum");
        property1.setValue("10.1.97.1");
        property1.setDescribe("HBase的Zookeeper的集群IP，如10.1.97.1,10.1.97.2");

        Map<String,Property> propertys = new HashMap<String,Property>();
        propertys.put("1",property);
        propertys.put("2",property1);

        //数据源
        Datasource datasource = new Datasource(propertys);
        datasource.setImplClass(implClass);
        datasource.setDescribe(describe);
        datasource.setName(name);
        datasource.setNote(note);
        datasource.setType(type);

        MetadataCol metadataCol = new MetadataCol();
        metadataCol.setName("hex_hbase_test01");
        metadataCol.setSeq((short) 32);
        metadataCol.setType(DataType.STRING);

        List<MetadataCol> list = new ArrayList<MetadataCol>();
        list.add(metadataCol);

        //目标元数据源定义
        Metadata metadata = new Metadata();
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setDatasource(datasource);
        metadata.setTbName(name);
        metadata.setStatus(MetadataStatus.NO_CREATED);
        metadata.setDescribe(describe);
        metadata.setName(name);
        metadata.setNote(note);
        metadata.setMetadataCols(list);

        //模型参数组装
        Model model = new Model();
        model.setId("1111111111101");
        model.setName("BJ_ZZKXSMX_TEST");
        model.setUpdateMode(UpdateMode.INSERT_INTO);
        model.setBuildMode(BuildMode.INSERT_OVERWRITE);
        model.setSourceDatasource(datasource);
        model.setStatus(ModelStatus.NO_CREATED);
        model.setType(ModelType.REALTIME);
        model.setDescribe("测试Test。。。");
        model.setEngineDatasource(datasource);

        BatchJobService batchJobService = new BatchJobService();
        try {
            batchJobService.start(model);
        }catch (Exception e) {
            throw new RuntimeException("存入内存出现错误！！");
        }

    }
}
