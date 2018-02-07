package com.hex.bigdata.udsp.mm.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.mm.dao.ContractorMapper;
import com.hex.bigdata.udsp.mm.dao.ModelInfoMapper;
import com.hex.bigdata.udsp.mm.dto.MmCollocateParamView;
import com.hex.bigdata.udsp.mm.dto.ModelInfoView;
import com.hex.bigdata.udsp.mm.model.MmApplication;
import com.hex.bigdata.udsp.mm.model.ModelInfo;
import com.hex.bigdata.udsp.mm.model.ModelParam;
import com.hex.goframe.model.MessageResult;
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
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/6
 * TIME:9:17
 */
@Service
public class ModelInfoService extends BaseService {
    /**
     * 模型管理-模型基础信息管理Dao层服务
     */
    @Autowired
    private ModelInfoMapper modelInfoMapper;

    @Autowired
    private ModelParamService modelParamService;

    @Autowired
    private MmApplicationService mmApplicationService;
    @Autowired
    private ContractorMapper contractorMapper;

    private static  List<ComExcelParam> comExcelParams = new ArrayList<>();
    static {
        //固定栏内容
        comExcelParams.add(new ComExcelParam(1,1,"name"));
        comExcelParams.add(new ComExcelParam(1,3,"contractor"));
        comExcelParams.add(new ComExcelParam(1,5,"note"));
        comExcelParams.add(new ComExcelParam(2,1,"modelType"));
        comExcelParams.add(new ComExcelParam(2,3,"describe"));
    }


    @Transactional
    public String insert(ModelInfo modelInfo) {
        String pkId = Util.uuid();
        modelInfo.setPkId(pkId);
        if (modelInfoMapper.insert(pkId, modelInfo)) {
            return pkId;
        }
        return "";
    }

    /**
     * 插入模型配置信息，同时插入配置字段和返回字段信息
     *
     * @param mmCollocateParamView
     * @return
     */
    @Transactional
    public String insert(MmCollocateParamView mmCollocateParamView) {
        String pkId = this.insert(mmCollocateParamView.getModelInfo());
        if (StringUtils.isNotBlank(pkId)) {
            modelParamService.insertQueryColList(pkId, mmCollocateParamView.getModelExcuteParam());
            modelParamService.insertReturnColList(pkId, mmCollocateParamView.getModelReturnParam());
            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param modelInfo
     * @return
     */
    @Transactional
    public boolean update(ModelInfo modelInfo) {
        return modelInfoMapper.update(modelInfo.getPkId(), modelInfo);
    }

    /**
     * 根据主键更新模型配置信息，同时更新对应的配置字段和返回字段信息
     *
     * @param mmCollocateParamView
     * @return
     */
    @Transactional
    public boolean update(MmCollocateParamView mmCollocateParamView) {
        ModelInfo modelInfo = mmCollocateParamView.getModelInfo();

        String mmId = modelInfo.getPkId();
        if (!this.update(modelInfo)) {
            return false;
        }
        if (!modelParamService.deleteByMmId(mmId)) {
            return false;
        }
        modelParamService.insertQueryColList(mmId, mmCollocateParamView.getModelExcuteParam());
        modelParamService.insertReturnColList(mmId, mmCollocateParamView.getModelReturnParam());
        return true;
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        return modelInfoMapper.delete(pkId);
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public ModelInfo select(String pkId) {
        return modelInfoMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMatedataView 查询参数
     * @param page            分页参数
     * @return
     */
    public List<ModelInfoView> select(ModelInfoView rtsMatedataView, Page page) {
        return modelInfoMapper.selectPage(rtsMatedataView, page);
    }

    /**
     * 检查名称是否唯一
     *
     * @param name 服务名称
     * @return 存在返回true，不存在返回false
     */
    public boolean checekUniqueName(String name) {
        ModelInfo rtsMatedata = this.modelInfoMapper.selectByName(name);
        return rtsMatedata != null;
    }

    /**
     * 批量删除
     *
     * @param modelInfos
     * @return
     */
    @Transactional
    public MessageResult delete(ModelInfo[] modelInfos) {
        boolean flag = true;
        StringBuffer message = new StringBuffer("");
        for (ModelInfo modelInfo : modelInfos) {
            String pkId = modelInfo.getPkId();
            //检查依赖
            List<MmApplication> mmApplications = mmApplicationService.selectByModelId(pkId);
            //存在下游依赖
            if (mmApplications != null && mmApplications.size() > 0) {
                flag = false;
                message.append(modelInfo.getName()).append("、");
                continue;
            }
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            message.deleteCharAt(message.length() - 1).append("，").append("模型已被应用，不允许对模型信息进行删除，删除失败！");
        }
        return new MessageResult(flag, message.toString(), null);
    }

    /**
     * 查询所有模型信息
     *
     * @return
     */
    public List<ModelInfo> selectAll() {
        return this.modelInfoMapper.selectAll();
    }

    /**
     * 通过模型厂商id查询关联的模型信息
     *
     * @param contractorId
     * @return
     */
    public List<ModelInfo> selectByContractorId(String contractorId) {
        return this.modelInfoMapper.selectByContractorId(contractorId);
    }

    /**
     * 检查是否存在下游依赖
     * 存在则返回false、不存在则返回true
     */
    public boolean checkDepend(String modelId){
        //检查是否关联下游应用
        List<MmApplication> mmApplications = mmApplicationService.selectByModelId(modelId);
        //存在依赖
        if (mmApplications != null && mmApplications.size() > 0) {
            return false;
        }
        return true;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.mm.model.ModelInfo");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("查询字段","com.hex.bigdata.udsp.mm.model.ModelParam",11,0,1, ComExcelEnums.ModelParam.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("返回字段","com.hex.bigdata.udsp.mm.model.ModelParam",10,0,2, ComExcelEnums.ModelParam.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for(int i = 0 ,activeIndex =  hfb.getNumberOfSheets();i < activeIndex;i++){
                sheet = hfb.getSheetAt(i);
                Map<String,List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<ModelInfo> modelInfos = (List<ModelInfo>)uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.ModelInfo");
                ModelInfo modelInfo = modelInfos.get(0);
                if(modelInfoMapper.selectByName(modelInfo.getName()) != null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个名称重复！");
                    break;
                }
                if(contractorMapper.selectByName(modelInfo.getContractor()) == null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + (i+1) + "个模型配置对应厂商名称不存在！");
                    break;
                }
                //设置厂商id
                modelInfo.setContractor(contractorMapper.selectByName(modelInfo.getContractor()).getPkId());
                String pkId = insert(modelInfo);

                List<ModelParam> modelQueryParam = (List<ModelParam>)uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.ModelParam");
                List<ModelParam> modelReturnParam = (List<ModelParam>)uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.ModelParam1");

                boolean insertQuery = modelParamService.insertQueryColList(pkId, modelQueryParam);
                boolean insertReturn = modelParamService.insertReturnColList(pkId, modelReturnParam);

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

    public String createExcel(ModelInfo[] modelInfos) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_modelInfo.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator+ "download_modelInfo_excel_"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xls";
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
        comExcelParams.add(new ComExcelParam(1,1,"name"));
        comExcelParams.add(new ComExcelParam(1,3,"contractor"));
        comExcelParams.add(new ComExcelParam(1,5,"note"));
        comExcelParams.add(new ComExcelParam(2,1,"modelType"));
        comExcelParams.add(new ComExcelParam(2,3,"describe"));
        for(ModelInfo modelInfo : modelInfos){
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
            ModelInfo model = modelInfoMapper.select(modelInfo.getPkId());
            //设置厂商名字
            model.setContractor(contractorMapper.select(model.getContractor()).getName());
            for(ComExcelParam comExcelParam : comExcelParams){
                try {
                    Field field = model.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet,comExcelParam.getRowNum(),comExcelParam.getCellNum(),field.get(model)==null?"":field.get(model).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<ModelParam> modelQueryCols = modelParamService.select(modelInfo.getPkId(),"1");
            if(modelQueryCols.size() > 0){
                for(ModelParam modelQueryCol : modelQueryCols){
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(modelQueryCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(modelQueryCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(modelQueryCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(modelQueryCol.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(modelQueryCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(modelQueryCol.getNote());
                    i++;
                }
            }
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(17), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(18), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<ModelParam> modelReturnCols = modelParamService.select(modelInfo.getPkId(), "2");
            if(modelQueryCols.size() > 0){
                for(ModelParam modelReturnCol : modelReturnCols){
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(modelReturnCol.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(modelReturnCol.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(modelReturnCol.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(modelReturnCol.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(modelReturnCol.getLength());
                    cell = row.createCell(5);
                    cell.setCellValue(modelReturnCol.getNote());
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
