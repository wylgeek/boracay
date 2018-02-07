package com.hex.bigdata.udsp.rts.provider;

import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.rts.provider.model.*;

/**
 * Created by junjiem on 2017-2-20.
 */
public interface Provider {

    // 推
    ProducerResponse push(ProducerRequest producerRequest);

    // 拉
    ConsumerResponse pull(ConsumerRequest consumerRequest);

    boolean testDatasource(Datasource datasource);
}
