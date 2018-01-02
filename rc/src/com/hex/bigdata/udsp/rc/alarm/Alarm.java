package com.hex.bigdata.udsp.rc.alarm;

import com.hex.bigdata.udsp.rc.alarm.model.Config;
import org.apache.commons.mail.EmailException;

/**
 * 警报接口类
 */
public interface Alarm {

    void send(Config config, String message) throws Exception; // 发送警报
}
