package com.hex.bigdata.udsp.common.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.dao.ComDatasourceMapper;
import com.hex.bigdata.udsp.common.dto.ComDatasourcePropsView;
import com.hex.bigdata.udsp.common.dto.ComDatasourceView;
import com.hex.bigdata.udsp.common.model.*;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.FTPClientConfig;
import com.hex.goframe.model.GFDict;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.excel.ExcelHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by junjiem on 2017-2-23.
 */
@Service
public class ComDatasourceService extends BaseService {
    @Autowired
    private ComDatasourceMapper comDatasourceMapper;
    @Autowired
    private ComPropertiesService comPropertiesService;

    //导入导出模板内容固定位置
    private static List<ComExcelParam> comExcelParams;

    static {
        comExcelParams = new ArrayList<>();
        comExcelParams.add(new ComExcelParam(2, 1, "name"));
        comExcelParams.add(new ComExcelParam(2, 3, "model"));
        comExcelParams.add(new ComExcelParam(3, 1, "describe"));
        comExcelParams.add(new ComExcelParam(3, 3, "type"));
        comExcelParams.add(new ComExcelParam(4, 1, "implClass"));
        comExcelParams.add(new ComExcelParam(4, 3, "note"));
    }

    @Transactional
    public String insert(ComDatasource comDatasource) {
        String pkId = Util.uuid();
        comDatasource.setPkId(pkId);
        if (comDatasourceMapper.insert(comDatasource.getPkId(), comDatasource)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public String insert(ComDatasourcePropsView comDatasourcePropsView) {
        ComDatasource comDatasource = comDatasourcePropsView.getComDatasource();
        String pkId = this.insert(comDatasource);
        if (StringUtils.isBlank(pkId)) {
            return "";
        }
        comPropertiesService.insertList(pkId, comDatasourcePropsView.getComPropertiesList());
        return pkId;
    }

    @Transactional
    public boolean update(ComDatasource comDatasource) {
        return comDatasourceMapper.update(comDatasource.getPkId(), comDatasource);
    }

    @Transactional
    public boolean update(ComDatasourcePropsView comDatasourcePropsView) {
        ComDatasource comDatasource = comDatasourcePropsView.getComDatasource();
        String pkId = comDatasource.getPkId();
        if (!comDatasourceMapper.update(pkId, comDatasource)) {
            return false;
        }
        if (!comPropertiesService.deleteByFkId(pkId)) {
            return false;
        }
        comPropertiesService.insertList(pkId, comDatasourcePropsView.getComPropertiesList());
        return true;
    }

    @Transactional
    public boolean delete(String pkId) {
        return comDatasourceMapper.delete(pkId);
    }

    @Transactional
    public boolean delete(ComDatasource[] comDatasources) {
        boolean status = true;
        for (ComDatasource comDatasource : comDatasources) {
            String pkId = comDatasource.getPkId();
            if (!this.delete(pkId)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public ComDatasource select(String pkId) {
        return comDatasourceMapper.select(pkId);
    }

    public List<ComDatasource> select(ComDatasourceView comDatasourceView, Page page) {
        return comDatasourceMapper.select(comDatasourceView, page);
    }

    public List<ComDatasource> select(ComDatasourceView comDatasourceView) {
        return comDatasourceMapper.select(comDatasourceView);
    }

    public boolean checkModelAndName(String model, String name) {
        return comDatasourceMapper.selectByModelAndName(model, name) != null;
    }

    public ComDatasource selectByModelAndName(String model, String name) {
        return comDatasourceMapper.selectByModelAndName(model, name);
    }

    public List<ComDatasource> selectAll() {
        return comDatasourceMapper.selectAll();
    }

    /**
     * 数据源excel文件导入
     *
     * @param uploadFilePath
     * @return
     */
    public Map<String, String> uploadExcel(String uploadFilePath) {
        Map resultMap = new HashMap<String, String>(2);
        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.common.model.ComDatasource");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            comExcelPropertiesList.add(new ComExcelProperties("配置信息", "com.hex.bigdata.udsp.common.model.ComProperties", 10, 0, 1, ComExcelEnums.DataSourceProperties.getAllNums()));
            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<ComDatasource> datasources = (List<ComDatasource>) uploadExcelModel.get("com.hex.bigdata.udsp.common.model.ComDatasource");
                ComDatasource comDatasource = datasources.get(0);
                if (selectByModelAndName(comDatasource.getModel(), comDatasource.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称已存在！");
                    break;
                }
                String pkId = insert(comDatasource);
                List<ComProperties> propertiesList = (List<ComProperties>) uploadExcelModel.get("com.hex.bigdata.udsp.common.model.ComProperties");
                boolean result = propertiesList.size() == 0 || comPropertiesService.insertList(pkId, propertiesList);
                if (result == true) {
                    resultMap.put("status", "true");
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个保存失败！");
                    break;
                }
            }
        } catch (Exception e) {
            resultMap.put("status", "false");
            resultMap.put("message", "程序内部异常：" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    public String createExcel(ComDatasource[] comDatasources) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        // 模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_dataSource.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_dataSource_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
        // 获取模板文件第一个Sheet对象
        POIFSFileSystem sourceFile = null;

        try {
            sourceFile = new POIFSFileSystem(new FileInputStream(
                    templateFile));
            sourceWork = new HSSFWorkbook(sourceFile);
            sourceSheet = sourceWork.getSheetAt(0);
            //创建表格
            workbook = new HSSFWorkbook();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet;

        for (ComDatasource comDatasource : comDatasources) {
            sheet = workbook.createSheet();
            //将前面样式内容复制到下载表中
            int i = 0;
            for (; i < 10; i++) {
                try {
                    ExcelCopyUtils.copyRow(sheet.createRow(i), sourceSheet.getRow(i), sheet.createDrawingPatriarch(), workbook);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置内容
            ComDatasource comdata = comDatasourceMapper.select(comDatasource.getPkId());
            for (ComExcelParam comExcelParam : comExcelParams) {
                try {
                    Field field = comdata.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(comdata) == null ? "" : field.get(comdata).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<ComProperties> comPropertiesList = comPropertiesService.selectByFkId(comDatasource.getPkId());
            int k = 0;
            if (comPropertiesList.size() > 0) {
                for (ComProperties comProperties : comPropertiesList) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(k + 1);
                    cell = row.createCell(1);
                    cell.setCellValue(comProperties.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(comProperties.getValue());
                    cell = row.createCell(3);
                    cell.setCellValue(comProperties.getDescribe());
                    i++;
                    k++;
                }
            }
        }
        if (workbook != null) {
            try {
                FileOutputStream stream = new FileOutputStream(dirPath);
                workbook.write(new FileOutputStream(dirPath));
                stream.close();
                return dirPath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<GFDict> selectParameterBySourceId(String sourceId) {

        return comDatasourceMapper.selectParameterBySourceId(sourceId);
    }

    public List<ComDatasource> selectImSourceDs() {
        return comDatasourceMapper.selectImSourceDs();
    }

    public boolean checkSourceType(String sourceId) {
        return comDatasourceMapper.checkSourceType(sourceId);
    }

    public List<ComDatasource> selectEDs() {
        return comDatasourceMapper.selectEDs();
    }
}

