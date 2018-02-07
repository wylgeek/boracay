package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McServiceChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/8/28
 * TIME:11:08
 */
@Service
public class McServiceChartsService extends BaseService {

    @Autowired
    private McServiceChartsMapper mcServiceChartsMapper;

    public List<McUserChartsView> selectServiceStatis(McServiceChartsView serviceChartsView, Page page) {
        return this.mcServiceChartsMapper.selectServiceStatis(serviceChartsView, page);
    }

    public List<McChartsView> serviceChar1Second(String timeStart, String timeEnd, String serverName) {
        return mcServiceChartsMapper.serviceChart1Second(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> serviceChart1Minute(String timeStart, String timeEnd, String serverName) {
        return mcServiceChartsMapper.serviceChart1Minute(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> serviceChart1Hour(String timeStart, String timeEnd, String serverName) {
        return mcServiceChartsMapper.serviceChart1Hour(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> serviceChart1Day(String timeStart, String timeEnd, String serverName) {
        return mcServiceChartsMapper.serviceChart1Day(new McChartsView(timeStart, timeEnd, serverName));
    }

    /**
     * 指定服务+用户，秒级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> serviceChart1Second(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.serviceChart1Second(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务+用户，分钟级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> serviceChart1Minute(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.serviceChart1Minute(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务+用户，小时级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> serviceChart1Hour(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.serviceChart1Hour(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务+用户，天级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> serviceChart1Day(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.serviceChart1Day(new McChartsView(timeStart, timeEnd, serverName, userName));
    }


    /**
     * 对名为serverName的服务，按照用户名进行分组统计
     *
     * @param timeStart
     * @param timeEnd
     * @param serviceName
     * @return
     */
    public List<McChartsView> statsSingleServiceGroupByUserName(String timeStart, String timeEnd, String serviceName) {
        return mcServiceChartsMapper.statsServiceGroupByUserName(new McChartsView(timeStart, timeEnd, serviceName));
    }

    /**
     * 指定服务,用户维护统计，秒级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> userTimeSecond(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.userTimeSecond(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务,用户维护统计，分钟级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> userTimeMinute(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.userTimeMinute(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务,用户维护统计，小时级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> userTimeHour(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.userTimeHour(new McChartsView(timeStart, timeEnd, serverName, userName));
    }

    /**
     * 指定服务,用户维护统计，天级别统计
     * @param timeStart
     * @param timeEnd
     * @param serverName
     * @param userName
     * @return
     */
    public List<McChartsView> userTimeDay(String timeStart, String timeEnd, String serverName,String userName) {
        return mcServiceChartsMapper.userTimeDay(new McChartsView(timeStart, timeEnd, serverName,userName));
    }
}
