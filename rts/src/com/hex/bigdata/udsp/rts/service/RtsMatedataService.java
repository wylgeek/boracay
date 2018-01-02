package com.hex.bigdata.udsp.rts.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.dao.ComDatasourceMapper;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper;
import com.hex.bigdata.udsp.rts.dto.RtsMatedataColsView;
import com.hex.bigdata.udsp.rts.dto.RtsMatedataView;
import com.hex.bigdata.udsp.rts.model.RtsMatedata;
import com.hex.bigdata.udsp.rts.model.RtsMatedataCol;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * 实时流-元数据服务
 * Created by tomnic on 2017/2/28.
 */
@Service
public class RtsMatedataService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsMatedataService.class);

    /**
     * 实时流-元数据Dao层服务
     */
    @Autowired
    private RtsMatedataMapper rtsMatedataMapper;

    /**
     * 实时流-元数据列服务
     */
    @Autowired
    private RtsMatedataColService rtsMatedataColService;

    @Autowired
    private ComDatasourceMapper comDatasourceMapper;

    private static List<ComExcelParam> comExcelParams = new ArrayList<>();

    static{
        //固定栏内容
        comExcelParams.add(new ComExcelParam(1,1,"name"));
        comExcelParams.add(new ComExcelParam(1,3,"dsId"));
        comExcelParams.add(new ComExcelParam(2,1,"topic"));
        comExcelParams.add(new ComExcelParam(2,3,"describe"));
        comExcelParams.add(new ComExcelParam(3,1,"note"));
    }

    @Transactional
    public String insert(RtsMatedata rtsMatedata) {
        String pkId = Util.uuid();
        rtsMatedata.setPkId(pkId);
        if (rtsMatedataMapper.insert(pkId, rtsMatedata)) {
            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param rtsMatedata
     * @return
     */
    @Transactional
    public boolean update(RtsMatedata rtsMatedata) {
        return rtsMatedataMapper.update(rtsMatedata.getPkId(), rtsMatedata);
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        return rtsMatedataMapper.delete(pkId);
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public RtsMatedata select(String pkId) {
        return rtsMatedataMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMatedataView   查询参数
     * @param page              分页参数
     * @return
     */
    public List<RtsMatedataView> select(RtsMatedataView rtsMatedataView, Page page) {
        return rtsMatedataMapper.selectPage(rtsMatedataView, page);
    }

    /**
     * 根据查询条件查询结果list，不分页
     * @param rtsMatedataView  查询参数
     * @return
     */
    public List<RtsMatedataView> select(RtsMatedataView rtsMatedataView){
        return rtsMatedataMapper.select(rtsMatedataView);
    }

    /**
     * 新增实时流数据源实体
     *
     * @param rtsMatedataColsView 实时流数据源实体
     * @return
     */
    @Transactional
    public String insert(RtsMatedataColsView rtsMatedataColsView) {
        RtsMatedata rtsMatedata = rtsMatedataColsView.getRtsMatedata();
        String pkId = this.insert(rtsMatedata);
        if (StringUtils.isNotBlank(pkId)) {
            List<RtsMatedataCol> rtsMatedataCols = rtsMatedataColsView.getRtsMatedataColList();
            for (RtsMatedataCol rtsMatedataCol : rtsMatedataCols) {
                rtsMatedataCol.setMdId(pkId);
                rtsMatedataColService.insert(rtsMatedataCol);
            }
            return pkId;
        }
        return "";
    }


    /**
     * 检查名称是否唯一
     *
     * @param name 数据源名称
     * @return 存在返回true，不存在返回fals
     */
    public boolean checekUniqueName(String name) {
        RtsMatedata rtsMatedata = this.rtsMatedataMapper.selectByName(name);
        return rtsMatedata != null;
    }

    /**
     * 更新数据源基础信息以及配置参数信息
     *
     * @param rtsMatedataColsView
     * @return
     */
    @Transactional
    public boolean update(RtsMatedataColsView rtsMatedataColsView) {
        RtsMatedata rtsMatedata = rtsMatedataColsView.getRtsMatedata();
        String pkId = rtsMatedata.getPkId();
        //更新基础信息
        boolean updateFlg = this.rtsMatedataMapper.update(pkId, rtsMatedata);
        //删除旧的的配置参数信息
        boolean delFlg = rtsMatedataColService.deleteByMdId(pkId);
        if (delFlg) {
            //插入新的配置参数信息
            List<RtsMatedataCol> rtsMatedataCols = rtsMatedataColsView.getRtsMatedataColList();
            for (RtsMatedataCol rtsMatedataCol : rtsMatedataCols) {
                rtsMatedataCol.setMdId(pkId);
                rtsMatedataColService.insert(rtsMatedataCol);
            }
        }
        return true;
    }

    /**
     * 批量删除
     *
     * @param rtsMatedatas
     * @return
     */
    @Transactional
    public boolean delete(RtsMatedata[] rtsMatedatas) {
        boolean flag = true;
        for (RtsMatedata rtsMatedata : rtsMatedatas) {
            String pkId = rtsMatedata.getPkId();
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean hasUsed(String pkId) {
        return rtsMatedataMapper.selectAppIdsByMdid(pkId).size() > 0;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.rts.model.RtsMatedata");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("数据列配置","com.hex.bigdata.udsp.rts.model.RtsMatedataCol",10,0,1, ComExcelEnums.RtsMatedata.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for(int i = 0 ,activeIndex =  hfb.getNumberOfSheets();i < activeIndex;i++){
                sheet = hfb.getSheetAt(i);

                Map<String,List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<RtsMatedata> rtsMatedatas = (List<RtsMatedata>)uploadExcelModel.get("com.hex.bigdata.udsp.rts.model.RtsMatedata");
                RtsMatedata rtsMatedata = rtsMatedatas.get(0);
                if(rtsMatedataMapper.selectByName(rtsMatedata.getName()) != null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个名称重复！");
                    break;
                }
                if(comDatasourceMapper.selectByModelAndName("RTS",rtsMatedata.getDsId()) == null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个对应数据源不存在！");
                    break;
                }
                //设置数据源id
                rtsMatedata.setDsId(comDatasourceMapper.selectByModelAndName("RTS",rtsMatedata.getDsId()).getPkId());
                String pkId = insert(rtsMatedata);
                List<RtsMatedataCol> rtsMatedataCols = (List<RtsMatedataCol>)uploadExcelModel.get("com.hex.bigdata.udsp.rts.model.RtsMatedataCol");
                boolean insert = rtsMatedataColService.insertList(pkId, rtsMatedataCols);
                if(insert){
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

    public String createExcel(RtsMatedata[] rtsMatedatas) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_rtsMateData.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator+"download_rtsMateData_excel_"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xls";
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

        for(RtsMatedata rtsMatedata : rtsMatedatas){
            sheet = workbook.createSheet();
            //将前面样式内容复制到下载表中
            int i = 0;
            for( ; i < 10 ; i++){
                try {
                    ExcelCopyUtils.copyRow(sheet.createRow(i), sourceSheet.getRow(i), sheet.createDrawingPatriarch(), workbook);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //设置内容
            RtsMatedata rtsmate = rtsMatedataMapper.select(rtsMatedata.getPkId());
            //设置数据源名字
            rtsmate.setDsId(comDatasourceMapper.select(rtsmate.getDsId()).getName());
            for(ComExcelParam comExcelParam : comExcelParams){
                try {
                    Field field = rtsmate.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet,comExcelParam.getRowNum(),comExcelParam.getCellNum(),field.get(rtsmate) == null? "" : field.get(rtsmate).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<RtsMatedataCol> rtsMatedataCols = rtsMatedataColService.selectByMdId(rtsMatedata.getPkId());
            if(rtsMatedataCols.size() > 0){
                for(RtsMatedataCol rtsMatedataCol : rtsMatedataCols){
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(rtsMatedataCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(rtsMatedataCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(rtsMatedataCol.getDescribe());
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
