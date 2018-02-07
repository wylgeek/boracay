package com.hex.bigdata.udsp.service;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.constant.Status;
import com.hex.bigdata.udsp.common.constant.StatusCode;
import com.hex.bigdata.udsp.common.provider.model.Result;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.iq.provider.model.IqResponse;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerRequestView;
import com.hex.bigdata.udsp.rts.dto.RtsProducerRequestView;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.bigdata.udsp.rts.model.RtsProducer;
import com.hex.bigdata.udsp.rts.provider.model.Column;
import com.hex.bigdata.udsp.rts.provider.model.ConsumerResponse;
import com.hex.bigdata.udsp.rts.provider.model.ProducerResponse;
import com.hex.bigdata.udsp.rts.service.RtsMatedataColService;
import com.hex.bigdata.udsp.rts.service.RtsProducerService;
import com.hex.bigdata.udsp.rts.service.RtsProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 同步实时流的服务（包括生产者和消费者）
 */
@Service
public class RtsSyncService {
    @Autowired
    private RtsMatedataColService rtsMatedataColService;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private RtsProviderService rtsProviderService;

    /**
     * 同步运行生产者
     *
     * @param appId
     * @param datas
     * @return
     */
    public Response startProducer(String appId, List<Map<String, String>> datas) {
        Response response = checkParam(appId, datas);
        if (response != null) return response;

        response = new Response();
        try {
            RtsProducer rtsProducer = rtsProducerService.select(appId);
            String mdId = rtsProducer.getMdId();
            List<RtsMatedataCol> rtsMatedataCols = rtsMatedataColService.selectByMdId(mdId);
            List<List<Column>> producerData = new ArrayList<>();
            for (Map<String, String> data : datas) {
                List<Column> columnList = new ArrayList<>();
                for (int i = 0; i < rtsMatedataCols.size(); i++) {
                    String name = rtsMatedataCols.get(i).getName();
                    Column column = new Column();
                    column.setSeq((short) (i + 1));
                    column.setName(name);
                    column.setValue(data.get(name));
                    columnList.add(column);
                }
                producerData.add(columnList);
            }

            RtsProducerRequestView rtsProducerRequestView = new RtsProducerRequestView();
            rtsProducerRequestView.setProducerId(appId);
            rtsProducerRequestView.setProducerData(producerData);

            ProducerResponse producerResponse = rtsProviderService.producer(rtsProducerRequestView);

            response.setConsumeTime(producerResponse.getConsumeTime());
            response.setMessage(producerResponse.getMessage());
            response.setStatus(producerResponse.getStatus().getValue());
            response.setStatusCode(producerResponse.getStatusCode().getValue());
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ErrorCode.ERROR_000007.getName() + "：" + e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000007.getValue());
        }

        return response;
    }


    /**
     * 检查输入的参数
     *
     * @param appId
     * @param datas
     * @return
     */
    private Response checkParam(String appId, List<Map<String, String>> datas) {
        Response response = null;
        boolean isError = false;
        StringBuffer colsName = new StringBuffer();
        for (RtsMatedataCol rtsMatedataCol : rtsMatedataColService.selectByProducerPkid(appId)) {
            colsName.append(rtsMatedataCol.getName() + ",");
            if (StringUtils.isBlank(datas.get(0).get(rtsMatedataCol.getName()))) {
                isError = true;
            }
        }
        if (isError) {
            response = new Response();
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
            response.setErrorCode(ErrorCode.ERROR_000009.getValue());
            response.setMessage("请检查以下参数的值:" + colsName.substring(0, colsName.length() - 1));
        }
        return response;
    }

    /**
     * 同步运行消费者
     *
     * @param appId
     * @param timeout
     * @return
     */
    public Response startConsumer(String appId, long timeout) {

        Response response = new Response();
        try {
            RtsConsumerRequestView rtsConsumerRequestView = new RtsConsumerRequestView();
            rtsConsumerRequestView.setConsumerId(appId);
            rtsConsumerRequestView.setTimeout(timeout == 0 ? 500 : (int) timeout);

            ConsumerResponse consumerResponse = rtsProviderService.consumer(rtsConsumerRequestView);

            response.setConsumeTime(consumerResponse.getConsumeTime());
            response.setMessage(consumerResponse.getMessage());
            response.setStatus(consumerResponse.getStatus().getValue());
            response.setStatusCode(consumerResponse.getStatusCode().getValue());
            //返回字段名称及类型
            response.setReturnColumns(consumerResponse.getColumns());
            List<Map<String, String>> records = new ArrayList<>();
            Map<String, String> map = null;
            for (Result result : consumerResponse.getRecords()) {
                map = new HashMap<>();
                for (Map.Entry<String, Object> entry : result.entrySet()) {
                    map.put(entry.getKey(), result.getString(entry.getKey()));
                }
                records.add(map);
            }
            response.setRecords(records);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(e.getMessage());
            response.setStatus(Status.DEFEAT.getValue());
            response.setStatusCode(StatusCode.DEFEAT.getValue());
        }
        return response;
    }

}
