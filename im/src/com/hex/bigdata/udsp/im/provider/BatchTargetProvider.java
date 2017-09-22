package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface BatchTargetProvider extends TargetProvider, BatchProvider {

    /**
     * 批量构建
     * @param model
     * @throws Exception
     */
    void inputSQL(String key, Model model) throws Exception;

    /**
     * 创建目标引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    boolean createTargetEngineSchema(Model model) throws Exception;

    /**
     * 删除目标引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    boolean dropTargetEngineSchema(Model model) throws Exception;
}
