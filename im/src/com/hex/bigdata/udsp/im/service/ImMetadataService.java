package com.hex.bigdata.udsp.im.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.provider.model.Property;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.*;
import com.hex.bigdata.udsp.im.constant.MetadataStatus;
import com.hex.bigdata.udsp.im.constant.MetadataType;
import com.hex.bigdata.udsp.im.dao.ImMetadataMapper;
import com.hex.bigdata.udsp.im.model.*;
import com.hex.bigdata.udsp.im.dto.ImMetadataDto;
import com.hex.bigdata.udsp.im.dto.ImMetadataView;
import com.hex.bigdata.udsp.im.provider.model.Metadata;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.im.util.ImUtil;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
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
 * Created by hj on 2017-9-5.
 */
@Service
public class ImMetadataService extends BaseService {
    @Autowired
    private ImMetadataMapper imMetadataMapper;
    @Autowired
    private ImMetadataColService imMetadataColService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private ComPropertiesService comPropertiesService;
    @Autowired
    private ImProviderService imProviderService;

    private static List<ComExcelParam> comExcelParams;

    static {
        comExcelParams = new ArrayList<>();
        comExcelParams.add(new ComExcelParam(2, 1, "name"));
        comExcelParams.add(new ComExcelParam(2, 3, "describe"));
        comExcelParams.add(new ComExcelParam(3, 1, "dsId"));
        comExcelParams.add(new ComExcelParam(3, 3, "type"));
        comExcelParams.add(new ComExcelParam(4, 1, "tbName"));
        comExcelParams.add(new ComExcelParam(5, 1, "note"));
    }

    @Transactional
    public String insert(ImMetadata imMetadata) {
        String pkId = Util.uuid();
        imMetadata.setPkId(pkId);
        String type = imMetadata.getType();
        if ("1".equals(type)) {
            imMetadata.setStatus("2"); //外表状态为已建
        } else if ("0".equals(type)) {
            imMetadata.setStatus("1"); //1未建 2已建
        }
        if (imMetadataMapper.insert(imMetadata.getPkId(), imMetadata)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public String insert(ImMetadataDto imMetadataDto) {
        ImMetadata imMetadata = imMetadataDto.getImMetadata();
        String pkId = this.insert(imMetadata);
        if (StringUtils.isBlank(pkId)) {
            return "";
        }
        comPropertiesService.insertList(pkId, imMetadataDto.getComPropertiesList());
        List<ImMetadataCol> imMetadataCols = imMetadataDto.getImMetadataColList();
        for (ImMetadataCol imMetadataCol : imMetadataCols) {
            imMetadataCol.setMdId(pkId);
            imMetadataColService.insert(imMetadataCol);
        }
        return pkId;
    }

    @Transactional
    public boolean update(ImMetadataDto imMetadataDto) {
        ImMetadata imMetadata = imMetadataDto.getImMetadata();
        String pkId = imMetadata.getPkId();
        if (!imMetadataMapper.update(pkId, imMetadata)) {
            return false;
        }
        if (!comPropertiesService.deleteByFkId(pkId)) {
            return false;
        }
        comPropertiesService.insertList(pkId, imMetadataDto.getComPropertiesList());
        if (!imMetadataColService.deleteByMdId(pkId)) {
            return false;
        }
        List<ImMetadataCol> imMetadataCols = imMetadataDto.getImMetadataColList();
        for (ImMetadataCol imMetadataCol : imMetadataCols) {
            imMetadataCol.setMdId(pkId);
            imMetadataColService.insert(imMetadataCol);
        }
        return true;
    }

    public List<ImMetadataView> select(ImMetadataView imMetadataView, Page page) {
        return imMetadataMapper.select(imMetadataView, page);
    }

    public ImMetadata select(String pkId) {
        return imMetadataMapper.select(pkId);
    }

    @Transactional
    public boolean delete(String pkId) {
        return imMetadataMapper.delete(pkId);
    }

    @Transactional
    public boolean delete(ImMetadata[] imMetadatas) {
        boolean status = true;
        for (ImMetadata imMetadata : imMetadatas) {
            String pkId = imMetadata.getPkId();
            if (!this.delete(pkId)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public boolean checkName(String name) {
        return imMetadataMapper.selectByName(name) != null;
    }

    public List<MetadataCol> getCloumnInfo(String dsId, String tbName) {
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comProperties = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comProperties);
        Metadata metadata = new Metadata();
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setTbName(tbName);
        metadata.setDatasource(datasource);
        List<MetadataCol> list = imProviderService.getCloumnInfo(metadata);
        return list;
    }

    public boolean checkSchema(String dsId, String tbName) throws Exception {
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comProperties = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comProperties);
        Metadata metadata = new Metadata();
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setTbName(tbName);
        metadata.setDatasource(datasource);
        return imProviderService.checkSchema(metadata);
    }

    @Transactional
    public boolean createTable(String pkId) throws Exception {
        ImMetadata imMetadata = this.select(pkId);
        imMetadata.setStatus("2"); //状态为已建
        imProviderService.createSchema(getMetadata(pkId));
        return imMetadataMapper.update(pkId, imMetadata);
    }

    @Transactional
    public boolean dropTable(String pkId) throws Exception {
        ImMetadata imMetadata = this.select(pkId);
        imMetadata.setStatus("1"); //状态为未建
        imProviderService.dropSchema(getMetadata(pkId));
        return imMetadataMapper.update(pkId, imMetadata);
    }

    public Metadata getMetadata(String pkId) {
        ImMetadata imMetadata = this.select(pkId);
        String dsId = imMetadata.getDsId();
        ComDatasource comDatasource = comDatasourceService.select(dsId);
        List<ComProperties> comProperties = comPropertiesService.selectByFkId(dsId);
        Datasource datasource = new Datasource(comDatasource, comProperties);
        List<Property> prop = PropertyUtil.convertToPropertyList(comPropertiesService.selectByFkId(pkId));
        Metadata metadata = new Metadata(prop);
        metadata.setName(imMetadata.getName());
        metadata.setType(MetadataType.EXTERNAL);
        metadata.setTbName(imMetadata.getTbName());
        metadata.setMetadataCols(ImUtil.convertToMetadataColList(imMetadataColService.select(pkId)));
        metadata.setDescribe(imMetadata.getDescribe());
        metadata.setDatasource(datasource);
        metadata.setStatus(MetadataStatus.NO_CREATED);
        return metadata;
    }

    public List<ImMetadata> selectAll() {
        return imMetadataMapper.selectAll();
    }

    @Transactional
    public Map<String, String> uploadExcel(String uploadFilePath) {
        Map resultMap = new HashMap<String, String>(2);
        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        int i = 0;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.im.model.ImMetadata");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            comExcelPropertiesList.add(new ComExcelProperties("字段信息", "com.hex.bigdata.udsp.im.model.ImMetadataCol", 10, 0, 1, ComExcelEnums.ImMetadataCol.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("配置栏", "com.hex.bigdata.udsp.common.model.ComProperties", 10, 0, 2, ComExcelEnums.Comproperties.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;

            Page page = new Page();
            page.setPageIndex(0);
            page.setPageSize(1);
            for (int activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                if (sheet.getLastRowNum() <= 0) {
                    break;
                }
                ImMetadataDto imMetadataDto = new ImMetadataDto();
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<ImMetadata> imMetadatas = (List<ImMetadata>) uploadExcelModel.get("com.hex.bigdata.udsp.im.model.ImMetadata");
                ImMetadata imMetadata = imMetadatas.get(0);
                if (imMetadataMapper.selectByName(imMetadata.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称已存在！");
                    break;
                }
                //更改数据源
                ComDatasource ds = comDatasourceService.selectByModelAndName("IM", imMetadata.getDsId());
                if (ds == null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个数据源不存在！");
                    break;
                }
                imMetadata.setDsId(ds.getPkId());
                //更改类型 0 内表 1 外表
                imMetadata.setType("内表".equals(imMetadata.getType()) ? "0" : "1");

                //设置模型状态 1 未建 2 已建  内表未建 外表已建
                String type = imMetadata.getType();
                if ("0".equals(type)) {// 内表
                    imMetadata.setStatus("1");
                } else if ("1".equals(type)) { // 外表
                    imMetadata.setStatus("2");
                }
                //设置模型
                imMetadataDto.setImMetadata(imMetadata);
                //获取字段内容
                List<ImMetadataCol> imMetadataCols = (List<ImMetadataCol>) uploadExcelModel.get("com.hex.bigdata.udsp.im.model.ImMetadataCol");
                imMetadataDto.setImMetadataColList(imMetadataCols);
                //获取参数内容
                List<ComProperties> comPropertiesList = (List<ComProperties>) uploadExcelModel.get("com.hex.bigdata.udsp.common.model.ComProperties");
                imMetadataDto.setComPropertiesList(comPropertiesList);
                this.insert(imMetadataDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status", "false");
            resultMap.put("message", "第" + i + "个报错异常,错误信息：" + e.getMessage());
            throw new RuntimeException(e.getMessage());
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

    public String createExcel(ImMetadata[] imMetadatas) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        // 模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_imMetadata.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_imMetadata_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        for (ImMetadata imMetadata : imMetadatas) {
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
            imMetadata = imMetadataMapper.select(imMetadata.getPkId());
            //设置数据源
            if (StringUtils.isNotEmpty(imMetadata.getDsId())) {
                imMetadata.setDsId(comDatasourceService.select(imMetadata.getDsId()).getName());
            }
            //设置类型
            imMetadata.setType("0".equals(imMetadata.getType()) ? "内表" : "外表");

            for (ComExcelParam comExcelParam : comExcelParams) {
                try {
                    Field field = imMetadata.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(imMetadata) == null ? "" : field.get(imMetadata).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置字段
            List<ImMetadataCol> imMetadataCols = imMetadataColService.select(imMetadata.getPkId());
            if (imMetadataCols.size() > 0) {
                for (ImMetadataCol imMetadataCol : imMetadataCols) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(imMetadataCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(imMetadataCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(imMetadataCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(imMetadataCol.getType());
                    cell = row.createCell(4);
                    cell.setCellValue(imMetadataCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(imMetadataCol.getNote());
                    cell = row.createCell(6);
                    cell.setCellValue(imMetadataCol.getIndexed());
                    cell = row.createCell(7);
                    cell.setCellValue(imMetadataCol.getPrimary());
                    cell = row.createCell(8);
                    cell.setCellValue(imMetadataCol.getStored());
                    i++;
                }
            }

            //设置参数栏
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(13), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(14), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<ComProperties> comProperties = comPropertiesService.selectByFkId(imMetadata.getPkId());
            if (comProperties.size() > 0) {
                int k = 1;
                for (ComProperties comProperty : comProperties) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(k);
                    cell = row.createCell(1);
                    cell.setCellValue(comProperty.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(comProperty.getValue());
                    cell = row.createCell(3);
                    cell.setCellValue(comProperty.getDescribe());
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

    public List<ImMetadataView> selectTargetMateData(String type) {
        return imMetadataMapper.selectTargetMateData(type);
    }

    /**
     * 根据条件查询元数据信息
     * @param imMetadataView
     * @return
     */
    public List<ImMetadataView> selectMateDataByCondition(ImMetadataView imMetadataView) {
        return imMetadataMapper.select(imMetadataView);
    }
}

