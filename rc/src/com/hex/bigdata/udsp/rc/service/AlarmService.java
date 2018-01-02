package com.hex.bigdata.udsp.rc.service;

import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import com.hex.bigdata.udsp.iq.provider.Provider;
import com.hex.bigdata.udsp.iq.service.IqProviderService;
import com.hex.bigdata.udsp.rc.alarm.Alarm;
import com.hex.bigdata.udsp.rc.alarm.model.Config;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.goframe.dao.GFDictMapper;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.service.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JunjieM on 2017-11-22.
 */
@Service
public class AlarmService extends BaseService {
    private static Logger logger = LogManager.getLogger(AlarmService.class);

    @Autowired
    private GFDictMapper gfDictMapper;
    @Autowired
    private ComPropertiesService comPropertiesService;

    /**
     * 发送警报信息
     *
     * @param rcUserService
     * @param message
     * @throws Exception
     */
    public void send(RcUserService rcUserService, String message) throws Exception {
        Alarm alarm = getAlarmImpl(rcUserService.getAlarmType());
        if (alarm == null) return;
        List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(rcUserService.getPkId());
        Config config = new Config(comPropertiesList);
        alarm.send(config, message);
    }

    /**
     * 得到警报接口的实例
     *
     * @param alarmType
     * @return
     */
    private Alarm getAlarmImpl(String alarmType) {
        if ("NONE".equals(alarmType)) return null;
        GFDict gfDict = gfDictMapper.selectByPrimaryKey("RC_IMPL_CLASS", alarmType);
        String implClass = gfDict.getDictName();
        return (Alarm) ObjectUtil.newInstance(implClass);
    }
}
