package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface TargetProvider extends Provider {

    /**
     * 获取字段信息
     *
     * @param metadata
     * @return
     */
    List<MetadataCol> columnInfo(Metadata metadata);

    /**
     * 创建Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    boolean createSchema(Metadata metadata) throws Exception;

    /**
     * 删除Schema
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    boolean dropSchema(Metadata metadata) throws Exception;

    /**
     * 检查Schema是否存在
     *
     * @param metadata
     * @return
     * @throws Exception
     */
    boolean checkSchemaExists(Metadata metadata) throws Exception;
}
