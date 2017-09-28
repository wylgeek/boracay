package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.Model;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface RealtimeTargetProvider extends TargetProvider, RealtimeProvider {

    /**
     * 实时构建
     *
     * @param model
     * @throws Exception
     */
    void buildRealtime(Model model) throws Exception;

}
