package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface BatchSourceProvider extends SourceProvider, BatchProvider {

    /**
     * 创建源引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    boolean createSourceEngineSchema(Model model) throws Exception;

    /**
     * 删除源引擎Schema
     *
     * @param model
     * @return
     * @throws Exception
     */
    boolean dropSourceEngineSchema(Model model) throws Exception;

}
