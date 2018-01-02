package com.hex.bigdata.udsp.olq.service;

import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.dto.ServiceBaseInfo;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/12
 * TIME:19:22
 */
@Service
public class OlqService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OlqApplicationService.class);

    @Autowired
    private RcServiceService rcServiceService;

    @Autowired
    private ComPropertiesService comPropertiesService;

    /**
     * 设置信息到workbook
     *
     * @param workbook
     */
    public void setWorkbooksheet(HSSFWorkbook workbook, RcUserServiceView rcUserService) {
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        String seprator = FileUtil.getFileSeparator();
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_allServiceInfo.xls";

        // 获取模板文件第一个Sheet对象
        POIFSFileSystem sourceFile = null;
        try {
            sourceFile = new POIFSFileSystem(new FileInputStream(templateFile));
            sourceWork = new HSSFWorkbook(sourceFile);
            //联机查询模板时第二个sheet
            sourceSheet = sourceWork.getSheetAt(1);
            //创建表格
        } catch (IOException e) {
            e.printStackTrace();
        }

        RcService rcService = null;
        if (StringUtils.isNotBlank(rcUserService.getServiceId())) {
            rcService = rcServiceService.select(rcUserService.getServiceId());
        }

        List<ComExcelParam> comExcelParams = new ArrayList<ComExcelParam>();
        comExcelParams.add(new ComExcelParam(2, 1, "serviceName"));
        comExcelParams.add(new ComExcelParam(2, 3, "serviceDescribe"));
        comExcelParams.add(new ComExcelParam(2, 5, "maxNum"));
        comExcelParams.add(new ComExcelParam(3, 1, "maxSyncNum"));
        comExcelParams.add(new ComExcelParam(3, 3, "maxAsyncNum"));
        comExcelParams.add(new ComExcelParam(3, 5, "maxSyncWaitNum"));
        comExcelParams.add(new ComExcelParam(3, 7, "maxAsyncWaitNum"));
        comExcelParams.add(new ComExcelParam(4, 1, "userId"));
        comExcelParams.add(new ComExcelParam(4, 5, "userName"));
        comExcelParams.add(new ComExcelParam(5, 1, "udspRequestUrl"));

        long maxSize = 65535;
        if (null != rcService) {
            List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(rcService.getAppId());
            for (ComProperties item : comPropertiesList) {
                if ("max.data.size".equals(item.getName())) {
                    if (StringUtils.isNotBlank(item.getValue())) {
                        maxSize = Long.valueOf(item.getValue());
                    }
                }
            }
        }

        ServiceBaseInfo serviceBaseInfo = new ServiceBaseInfo(rcUserService, maxSize, "");
        HSSFSheet sheet;
        sheet = workbook.createSheet();
        //将前面样式内容复制到下载表中
        int i = 0;
        for (; i < 11; i++) {
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i), sourceSheet.getRow(i), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (ComExcelParam comExcelParam : comExcelParams) {
            try {
                Field field = serviceBaseInfo.getClass().getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(serviceBaseInfo) == null ? "" : field.get(serviceBaseInfo).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
