package com.hex.bigdata.udsp.mc.dao;

import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.goframe.dao.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PC on 2017/3/23.
 */
@Repository
public class McChartsMapper extends BaseMapper {

    public List<McChartsView> chart1Second(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart1Second", mcChartsView);
    }

    public List<McChartsView> chart1Minute(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart1Minute", mcChartsView);
    }

    public List<McChartsView> chart1Hour(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart1Hour", mcChartsView);
    }

    public List<McChartsView> chart1Day(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart1Day", mcChartsView);
    }

    public List<McChartsView> chart2Second(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart2Second", mcChartsView);
    }

    public List<McChartsView> chart2Minute(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart2Minute", mcChartsView);
    }

    public List<McChartsView> chart2Hour(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart2Hour", mcChartsView);
    }

    public List<McChartsView> chart2Day(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart2Day", mcChartsView);
    }

    public List<McChartsView> chart3(McChartsView mcChartsView) {
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart3", mcChartsView);
    }

    public List<McChartsView> chart4(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart4", mcChartsView);
    }

    public List<McChartsView> chart5(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart5", mcChartsView);
    }

    public List<McChartsView> chart6(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart6", mcChartsView);
    }

    public List<McChartsView> chart7(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart7", mcChartsView);
    }

    public List<McChartsView> chart8(String timeStart, String timeEnd) {
        McChartsView mcChartsView = new McChartsView(timeStart, timeEnd);
        return sqlSessionTemplate.selectList("com.hex.bigdata.udsp.mc.dao.McChartsMapper.chart8", mcChartsView);
    }
}
