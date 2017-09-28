package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.im.provider.*;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.util.WebApplicationContextUtil;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-6.
 */
@Service
public class ImProviderService {
    private static Logger logger = LogManager.getLogger(ImProviderService.class);
    @Autowired
    private GFDictMapper gfDictMapper;

    /**
     * 获取字段信息
     *
     * @param metadata
     * @return
     */
    public List<MetadataCol> getCloumnInfo(Metadata metadata) {
        return getTargetProvider(metadata.getDatasource()).columnInfo(metadata);
    }

    /**
     * 检查Schema是否存在
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public boolean checkSchemaExists(Metadata metadata) throws Exception {
        return getTargetProvider(metadata.getDatasource()).checkSchemaExists(metadata);
    }

    /**
     * 获取字段信息
     *
     * @param model
     * @return
     */
    public List<MetadataCol> getCloumnInfo(Model model) {
        return getSourceProvider(model.getSourceDatasource()).columnInfo(model);
    }

    /**
     * 创建引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    public boolean createEngineSchema(Model model) throws Exception {
        logger.debug("创建引擎Schema...");
        if (!getBatchSourceProvider(model.getSourceDatasource()).createSourceEngineSchema(model)) return false;
        return getBatchTargetProvider(model.getTargetMetadata().getDatasource()).createTargetEngineSchema(model);
    }

    /**
     * 删除引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    public boolean dropEngineSchema(Model model) throws Exception {
        logger.debug("删除引擎Schema...");
        if (!getBatchSourceProvider(model.getSourceDatasource()).dropSourceEngineSchema(model)) return false;
        return getBatchTargetProvider(model.getTargetMetadata().getDatasource()).dropTargetEngineSchema(model);
    }

    /**
     * 创建Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public boolean createSchema(Metadata metadata) throws Exception {
        logger.debug("创建Schema...");
        return getTargetProvider(metadata.getDatasource()).createSchema(metadata);
    }

    /**
     * 删除Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    public boolean dropSchema(Metadata metadata) throws Exception {
        logger.debug("删除Schema...");
        return getTargetProvider(metadata.getDatasource()).dropSchema(metadata);
    }

    /**
     * 构建实时任务
     *
     * @param model
     */
    public void buildRealtime(Model model) throws Exception {
        logger.debug("构建实时任务【开始】");
        getRealtimeTargetProvider(model.getTargetMetadata().getDatasource()).buildRealtime(model);
        logger.debug("构建实时任务【结束】");
    }

    /**
     * 构建批量任务
     *
     * @param model
     */
    public void buildBatch(String key, Model model) throws Exception {
        logger.debug("构建批量任务【开始】");
        getBatchTargetProvider(model.getTargetMetadata().getDatasource()).buildBatch(key, model);
        logger.debug("构建批量任务【结束】");
    }

    /**
     * 测试数据源
     *
     * @param datasource
     * @return
     */
    public boolean testDatasource(Datasource datasource) {
        logger.debug("测试数据源...");
        return getProvider(datasource).testDatasource(datasource);
    }

    private BatchSourceProvider getBatchSourceProvider(Datasource datasource) {
        return (BatchSourceProvider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private BatchTargetProvider getBatchTargetProvider(Datasource datasource) {
        return (BatchTargetProvider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private RealtimeTargetProvider getRealtimeTargetProvider(Datasource datasource) {
        return (RealtimeTargetProvider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private SourceProvider getSourceProvider(Datasource datasource) {
        return (SourceProvider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private TargetProvider getTargetProvider(Datasource datasource) {
        return (TargetProvider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private Provider getProvider(Datasource datasource) {
        return (Provider) WebApplicationContextUtil.getBean(getImplClass(datasource));
    }

    private String getImplClass(Datasource datasource) {
        String implClass = datasource.getImplClass();
        if (StringUtils.isBlank(implClass)) {
            GFDict gfDict = gfDictMapper.selectByPrimaryKey("IM_IMPL_CLASS", datasource.getType());
            implClass = gfDict.getDictName();
        }
        return implClass;
    }
}
