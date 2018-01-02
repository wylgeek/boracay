package com.hex.bigdata.udsp.thread;

import com.hex.bigdata.udsp.common.constant.ErrorCode;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.constant.ConsumerConstant;
import com.hex.bigdata.udsp.dto.ConsumeRequest;
import com.hex.bigdata.udsp.model.ExternalRequest;
import com.hex.bigdata.udsp.model.Response;
import com.hex.bigdata.udsp.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2017/5/2.
 */
public class SocketRunnable implements Runnable {

    private Logger log = LoggerFactory.getLogger(SocketRunnable.class);

    private Socket server;

    private ConsumerService consumerService;

    public SocketRunnable(Socket server, ConsumerService consumerService) {
        this.server = server;
        this.consumerService = consumerService;
    }

    public SocketRunnable() {
    }

    @Override
    public void run() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(server.getInputStream()));

            String getMassage = "";
            StringBuffer massage = new StringBuffer();
            while ((getMassage = br.readLine()) != null) {
                massage.append(getMassage);
            }
            log.info("socket接收到内容为：" + massage.toString());
            String reponseString = consume(massage.toString(), server.getInetAddress().getHostAddress());

            bw = new BufferedWriter(new OutputStreamWriter(server.getOutputStream(), "UTF-8"));
            bw.write(reponseString);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String consume(String massage, String requestIp) {
        ExternalRequest externalRequest = null;
        long bef = System.currentTimeMillis();
        try {
            Map<String, Class> classMap = new HashMap<String, Class>();
            classMap.put(ConsumerConstant.CONSUME_RTS_DATASTREAM, Map.class);
            externalRequest = JSONUtil.parseJSON2Obj(massage, ExternalRequest.class, classMap);
        } catch (Exception e) {
            //处理异常，返回respone
            Response response = new Response();
            this.consumerService.setErrorResponse(response, new ConsumeRequest(), bef,
                    ErrorCode.ERROR_000005.getValue(), e.getMessage(), null);
            return JSONUtil.parseObj2JSON(response);
        }
        externalRequest.setRequestIp(requestIp);
        Response response = consumerService.externalConsume(externalRequest);
        return JSONUtil.parseObj2JSON(response);
    }

}
