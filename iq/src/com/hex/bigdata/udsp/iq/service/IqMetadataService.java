package com.hex.bigdata.udsp.iq.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.dao.ComDatasourceMapper;
import com.hex.bigdata.udsp.common.model.*;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.im.provider.model.MetadataCol;
import com.hex.bigdata.udsp.iq.dao.IqMetadataMapper;
import com.hex.bigdata.udsp.iq.dto.IqMetadataPropsView;
import com.hex.bigdata.udsp.iq.dto.IqMetadataView;
import com.hex.bigdata.udsp.iq.model.IqMetadata;
import com.hex.bigdata.udsp.iq.model.IqMetadataCol;
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
 * Created by junjiem on 2017-2-23.
 */
@Service
public class IqMetadataService extends BaseService {
    @Autowired
    private IqMetadataMapper iqMetadataMapper;
    @Autowired
    private IqMetadataColService iqMetadataColService;
    @Autowired
    private ComDatasourceMapper comDatasourceMapper;

    @Transactional
    public String insert(IqMetadata iqMetadata) {
        String pkId = Util.uuid();
        iqMetadata.setPkId(pkId);
        if (iqMetadataMapper.insert(iqMetadata.getPkId(), iqMetadata)) {
            return pkId;
        }
        return "";
    }

    @Transactional
    public String insert(IqMetadataPropsView iqMetadataPropsView) {
        IqMetadata iqMetadata = iqMetadataPropsView.getIqMetadata();
        String pkId = this.insert(iqMetadata);
        if (StringUtils.isNotBlank(pkId)) {
            iqMetadataColService.insertQueryColList(pkId, iqMetadataPropsView.getIqMetadataQueryColList());
            iqMetadataColService.insertReturnColList(pkId, iqMetadataPropsView.getIqMetadataReturnColList());
            return pkId;
        }
        return "";
    }

    @Transactional
    public boolean update(IqMetadata iqMetadata) {
        return iqMetadataMapper.update(iqMetadata.getPkId(), iqMetadata);
    }

    @Transactional
    public boolean update(IqMetadataPropsView iqMetadataPropsView) {
        IqMetadata iqMetadata = iqMetadataPropsView.getIqMetadata();
        String pkId = iqMetadata.getPkId();
        if (!iqMetadataMapper.update(pkId, iqMetadata)) {
            return false;
        }
        if (!iqMetadataColService.deleteByMdId(pkId)) {
            return false;
        }
        iqMetadataColService.insertQueryColList(pkId, iqMetadataPropsView.getIqMetadataQueryColList());
        iqMetadataColService.insertReturnColList(pkId, iqMetadataPropsView.getIqMetadataReturnColList());
        return true;
    }

    @Transactional
    public boolean delete(String pkId) {
        return iqMetadataMapper.delete(pkId);
    }

    @Transactional
    public boolean delete(IqMetadata[] iqMetadatas) {
        boolean status = true;
        for (IqMetadata iqMetadata : iqMetadatas) {
            String pkId = iqMetadata.getPkId();
            if (!this.delete(pkId)) {
                status = false;
                break;
            }
        }
        return status;
    }

    public IqMetadata select(String pkId) {
        return iqMetadataMapper.select(pkId);
    }

    public List<IqMetadataView> select(IqMetadataView iqMetadataView, Page page) {
        return iqMetadataMapper.select(iqMetadataView, page);
    }

    public List<IqMetadataView> select(IqMetadataView iqMetadataView) {
        return iqMetadataMapper.select(iqMetadataView);
    }

    public boolean checkName(String name) {
        return iqMetadataMapper.selectByName(name) != null;
    }

    public boolean hasUsed(IqMetadata iqMetadata) {
        if(iqMetadataColService.selectAppPkIdsByMdid(iqMetadata.getPkId()).size() > 0){
            return true;
        }
        return false;
    }

    /**
     * 数据源excel文件导入
     * @param uploadFilePath
     * @return
     */
    public Map<String, String> uploadExcel(String uploadFilePath) {
        Map resultMap = new HashMap<String,String>(2);
        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.iq.model.IqMetadata");
            List<ComExcelParam> comExcelParams = new ArrayList<>();
            //固定栏内容
            comExcelParams.add(new ComExcelParam(2,1,"name"));
            comExcelParams.add(new ComExcelParam(2,3,"dsId"));
            comExcelParams.add(new ComExcelParam(2,5,"note"));
            comExcelParams.add(new ComExcelParam(3,1,"describe"));
            comExcelParams.add(new ComExcelParam(3,3,"tbName"));

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("查询字段","com.hex.bigdata.udsp.iq.model.IqMetadataCol",11,0,1, ComExcelEnums.IqMetadataCol.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("返回字段","com.hex.bigdata.udsp.iq.model.IqMetadataCol",10,0,2, ComExcelEnums.IqMetadataCol.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for(int i = 0 ,activeIndex =  hfb.getNumberOfSheets();i < activeIndex;i++){
                sheet = hfb.getSheetAt(i);
                Map<String,List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<IqMetadata> iqMetadatas = (List<IqMetadata>)uploadExcelModel.get("com.hex.bigdata.udsp.iq.model.IqMetadata");
                IqMetadata iqMetadata = iqMetadatas.get(0);
                if(iqMetadataMapper.selectByName(iqMetadata.getName()) != null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个名称已存在！");
                    break;
                }
                if(comDatasourceMapper.selectByModelAndName("IQ",iqMetadata.getDsId()) == null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个元数据对应数据源不存在！");
                    break;
                }
                //跟新其对应数据源
                iqMetadata.setDsId(comDatasourceMapper.selectByModelAndName("IQ",iqMetadata.getDsId()).getPkId());
                String pkId = insert(iqMetadata);
                List<IqMetadataCol> queryColumnList = (List<IqMetadataCol>)uploadExcelModel.get("com.hex.bigdata.udsp.iq.model.IqMetadataCol");
                List<IqMetadataCol> iqAppReturnColList = (List<IqMetadataCol>)uploadExcelModel.get("com.hex.bigdata.udsp.iq.model.IqMetadataCol1");
                boolean insertReturn = iqAppReturnColList.size() == 0 || iqMetadataColService.insertReturnColList(pkId, iqAppReturnColList);
                boolean insertQuery = queryColumnList.size() == 0 || iqMetadataColService.insertQueryColList(pkId, queryColumnList);
                if(insertQuery  && insertReturn){
                    resultMap.put("status","true");
                }else{
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个保存失败！");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","false");
            resultMap.put("message","程序内部异常：" + e.getMessage());
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    public String createExcel( IqMetadata[] iqMetadatas) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_iqMetadata.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator+"download_iqMetadata_excel_"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xls";
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
        List<ComExcelParam> comExcelParams = new ArrayList<>();
        comExcelParams.add(new ComExcelParam(2,1,"name"));
        comExcelParams.add(new ComExcelParam(2,3,"dsId"));
        comExcelParams.add(new ComExcelParam(2,5,"note"));
        comExcelParams.add(new ComExcelParam(3,1,"describe"));
        comExcelParams.add(new ComExcelParam(3,3,"tbName"));

        for(IqMetadata iqMetadata : iqMetadatas){
            sheet = workbook.createSheet();


            //将前面样式内容复制到下载表中
            int i = 0;
            for( ; i < 11 ; i++){
                try {
                    ExcelCopyUtils.copyRow(sheet.createRow(i), sourceSheet.getRow(i), sheet.createDrawingPatriarch(), workbook);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置内容
            IqMetadata iqmeta = iqMetadataMapper.select(iqMetadata.getPkId());
            //设置数据源名
            iqmeta.setDsId(comDatasourceMapper.select(iqmeta.getDsId()).getName());
            for(ComExcelParam comExcelParam : comExcelParams){
                try {
                    Field field = iqmeta.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet,comExcelParam.getRowNum(),comExcelParam.getCellNum(),field.get(iqmeta) == null? "":field.get(iqmeta).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<IqMetadataCol> iqMetadataQueryCols = iqMetadataColService.select(iqmeta.getPkId(), "1");
            if(iqMetadataQueryCols.size() > 0){
                for(IqMetadataCol iqMetadataCol : iqMetadataQueryCols){
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(iqMetadataCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(iqMetadataCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(iqMetadataCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(iqMetadataCol.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(iqMetadataCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(iqMetadataCol.getNote());
                    i++;
                }
            }

            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(19), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(20), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<IqMetadataCol> iqMetadataReturnCols = iqMetadataColService.select(iqmeta.getPkId(), "2");
            if(iqMetadataReturnCols.size() > 0){
                for(IqMetadataCol iqMetadataCol : iqMetadataReturnCols){
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(iqMetadataCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(iqMetadataCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(iqMetadataCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(iqMetadataCol.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(iqMetadataCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(iqMetadataCol.getNote());
                    i++;
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
}
