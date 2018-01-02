package com.hex.bigdata.udsp.mc.controller;

import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.mc.dto.McChartsView;
import com.hex.bigdata.udsp.mc.dto.McServiceChartsView;
import com.hex.bigdata.udsp.mc.dto.McUserChartsView;
import com.hex.bigdata.udsp.mc.service.McChartsService;
import com.hex.goframe.controller.BaseController;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.model.PageListResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by PC on 2017/3/24.
 */
@RequestMapping("/mc/stats/charts")
@Controller
public class McChartsController extends BaseController {

    private static Logger logger = LogManager.getLogger(McChartsController.class);
    private static final FastDateFormat ymdhmsFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static final FastDateFormat ymdhmFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    @Autowired
    private McChartsService mcChartsService;

    @RequestMapping({"/chart1Minute"})
    @ResponseBody
    public PageListResult chart1Minute(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart1Minute(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Hour"})
    @ResponseBody
    public PageListResult chart1Hour(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart1Hour(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart1Day"})
    @ResponseBody
    public PageListResult chart1Day(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart1Day(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart2Minute"})
    @ResponseBody
    public PageListResult chart2Minute(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart2Minute(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart2Hour"})
    @ResponseBody
    public PageListResult chart2Hour(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart2Hour(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart2Day"})
    @ResponseBody
    public PageListResult chart2Day(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart2Day(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart3"})
    @ResponseBody
    public PageListResult chart3(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart3(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart4"})
    @ResponseBody
    public PageListResult chart4(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart4(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart5"})
    @ResponseBody
    public PageListResult chart5(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart5(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart6"})
    @ResponseBody
    public PageListResult chart6(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart6(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart7"})
    @ResponseBody
    public PageListResult chart7(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart7(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/chart8"})
    @ResponseBody
    public PageListResult chart8(String datetime, String interval) {
        logger.debug("select datetime=" + datetime + " interval=" + interval);
        List<McChartsView> list = null;
        try {
            Date dtEnd = getDate(datetime);
            String dtEndStr = ymdhmsFormat.format(dtEnd);
            String dtStartStr = ymdhmsFormat.format(new Date(dtEnd.getTime() - Long.valueOf(interval) * 60 * 1000));
            list = mcChartsService.chart8(dtStartStr, dtEndStr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("系统异常：" + e);
        }
        return new PageListResult(list);
    }

    @RequestMapping({"/nowtime"})
    @ResponseBody
    public MessageResult nowtime() {
        logger.debug("get now date time");
        boolean status = true;
        String message = "获取当前时间成功";
        String nowtime = "";
        try {
            nowtime = ymdhmsFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
            message = "系统异常：" + e;
        }
        return new MessageResult(status, message, nowtime);
    }

    private Date getDate(String datetime) throws ParseException {
        Date dtEnd = null;
        if (StringUtils.isNotBlank(datetime)) {
            dtEnd = ymdhmFormat.parse(datetime);
        } else {
            dtEnd = new Date();
        }
        return dtEnd;
    }
}
