package com.hex.bigdata.udsp.im.provider;

import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.provider.model.Model;

import java.util.List;

/**
 * Created by JunjieM on 2017-9-5.
 */
public interface SourceProvider extends Provider {

    /**
     * 获取字段信息
     *
     * @param model
     * @return
     */
    List<MetadataCol> columnInfo(Model model);

}
