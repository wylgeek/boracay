package com.hex.bigdata.udsp.mc.service;

import com.hex.bigdata.udsp.mc.dao.McChartsMapper;
import com.hex.bigdata.udsp.mc.dao.McServiceChartsMapper;
import com.hex.bigdata.udsp.mc.dao.McUserChartsMapper;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McServiceChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PC on 2017/3/24.
 */
@Service
public class McChartsService extends BaseService {
    @Autowired
    private McChartsMapper mcChartsMapper;

    public List<McChartsView> chart1Second(String timeStart, String timeEnd) {
        return mcChartsMapper.chart1Second(new McChartsView(timeStart, timeEnd));
    }

    public List<McChartsView> chart1Minute(String timeStart, String timeEnd) {
        return mcChartsMapper.chart1Minute(new McChartsView(timeStart, timeEnd));
    }

    public List<McChartsView> chart1Hour(String timeStart, String timeEnd) {
        return mcChartsMapper.chart1Hour(new McChartsView(timeStart, timeEnd));
    }

    public List<McChartsView> chart1Day(String timeStart, String timeEnd) {
        return mcChartsMapper.chart1Day(new McChartsView(timeStart, timeEnd));
    }

    public List<McChartsView> chart2Second(String timeStart, String timeEnd) {
        return mcChartsMapper.chart2Second(timeStart, timeEnd);
    }

    public List<McChartsView> chart2Minute(String timeStart, String timeEnd) {
        return mcChartsMapper.chart2Minute(timeStart, timeEnd);
    }

    public List<McChartsView> chart2Hour(String timeStart, String timeEnd) {
        return mcChartsMapper.chart2Hour(timeStart, timeEnd);
    }

    public List<McChartsView> chart2Day(String timeStart, String timeEnd) {
        return mcChartsMapper.chart2Day(timeStart, timeEnd);
    }

    public List<McChartsView> chart3(String timeStart, String timeEnd) {
        return mcChartsMapper.chart3(new McChartsView(timeStart, timeEnd));
    }

    public List<McChartsView> chart4(String timeStart, String timeEnd) {
        return mcChartsMapper.chart4(timeStart, timeEnd);
    }

    public List<McChartsView> chart5(String timeStart, String timeEnd) {
        return mcChartsMapper.chart5(timeStart, timeEnd);
    }

    public List<McChartsView> chart6(String timeStart, String timeEnd) {
        return mcChartsMapper.chart6(timeStart, timeEnd);
    }

    public List<McChartsView> chart7(String timeStart, String timeEnd) {
        return mcChartsMapper.chart7(timeStart, timeEnd);
    }

    public List<McChartsView> chart8(String timeStart, String timeEnd) {
        return mcChartsMapper.chart8(timeStart, timeEnd);
    }

    public List<McChartsView> chart1Second(String timeStart, String timeEnd, String serverName) {
        return mcChartsMapper.chart1Second(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> chart1Minute(String timeStart, String timeEnd, String serverName) {
        return mcChartsMapper.chart1Minute(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> chart1Hour(String timeStart, String timeEnd, String serverName) {
        return mcChartsMapper.chart1Hour(new McChartsView(timeStart, timeEnd, serverName));
    }

    public List<McChartsView> chart1Day(String timeStart, String timeEnd, String serverName) {
        return mcChartsMapper.chart1Day(new McChartsView(timeStart, timeEnd, serverName));
    }

}
