package com.hex.bigdata.udsp.mm.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.mm.dao.MmApplicationMapper;
import com.hex.bigdata.udsp.mm.dao.ModelInfoMapper;
import com.hex.bigdata.udsp.mm.dto.MmApplicationParamView;
import com.hex.bigdata.udsp.mm.dto.MmApplicationView;
import com.hex.bigdata.udsp.mm.dto.MmFullAppInfoView;
import com.hex.bigdata.udsp.mm.dto.MmIndexDto;
import com.hex.bigdata.udsp.mm.model.MmAppExecuteParam;
import com.hex.bigdata.udsp.mm.model.MmAppReturnParam;
import com.hex.bigdata.udsp.mm.model.MmApplication;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.dto.ServiceBaseInfo;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.goframe.model.MessageResult;
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
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/6
 * TIME:9:15
 */
@Service
public class MmApplicationService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(MmApplicationService.class);

    /**
     * 模型管理-模型基础信息管理Dao层服务
     */
    @Autowired
    private MmApplicationMapper mmApplicationMapper;

    @Autowired
    private ExecuteParamService executeParamService;

    @Autowired
    private ReturnParamService returnParamService;

    @Autowired
    private RcServiceService rcServiceService;


    @Autowired
    private ModelInfoMapper modelInfoMapper;

    private static List<ComExcelParam> comExcelParams = new ArrayList<>();

    static {
        comExcelParams.add(new ComExcelParam(1, 1, "name"));
        comExcelParams.add(new ComExcelParam(1, 3, "modelId"));
        comExcelParams.add(new ComExcelParam(2, 1, "describe"));
        comExcelParams.add(new ComExcelParam(2, 3, "maxNum"));
        comExcelParams.add(new ComExcelParam(3, 3, "note"));
    }


    @Transactional
    public String insert(MmApplicationParamView mmApplicationParamView) {
        MmApplication mmApplication = mmApplicationParamView.getMmapplication();
        String pkId = this.insert(mmApplication);
        if (StringUtils.isNotBlank(pkId)) {
            executeParamService.insertList(pkId, mmApplicationParamView.getMmAppExecuteParam());
            returnParamService.insertList(pkId, mmApplicationParamView.getMmAppReturnParam());
            return pkId;
        }

        return "";
    }

    public String insert(MmApplication mmApplication) {
        String pkId = Util.uuid();
        mmApplication.setPkId(pkId);
        if (mmApplicationMapper.insert(pkId, mmApplication)) {
            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param mmApplication
     * @return
     */
    @Transactional
    public boolean update(MmApplication mmApplication) {
        return mmApplicationMapper.update(mmApplication.getPkId(), mmApplication);
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        return mmApplicationMapper.delete(pkId);
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public MmApplication select(String pkId) {
        return mmApplicationMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param mmApplicationView 查询参数
     * @param page              分页参数
     * @return
     */
    public List<MmApplicationView> select(MmApplicationView mmApplicationView, Page page) {
        return mmApplicationMapper.selectPage(mmApplicationView, page);
    }

    /**
     * 检查名称是否唯一
     *
     * @param name 服务名称
     * @return 存在返回true，不存在返回false
     */
    public boolean checekUniqueName(String name) {
        MmApplication mmApplication = this.mmApplicationMapper.selectByName(name);
        return mmApplication != null;
    }

    /**
     * 批量删除
     *
     * @param mmApplications
     * @return
     */
    @Transactional
    public MessageResult delete(MmApplication[] mmApplications) {
        boolean flag = true;
        StringBuffer message = new StringBuffer("");

        for (MmApplication mmApplication : mmApplications) {
            String pkId = mmApplication.getPkId();
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        //如果
        if (!flag) {
            message.deleteCharAt(message.length() - 1).append("，").append("注册中心存在模型应用对应的服务，不允许对模型应用进行删除，删除失败！");
        }
        return new MessageResult(flag, message.toString());
    }


    /**
     * 更新模型应用配置
     *
     * @param mmApplicationParamView
     * @return
     */
    @Transactional
    public boolean update(MmApplicationParamView mmApplicationParamView) {
        MmApplication mmapplication = mmApplicationParamView.getMmapplication();
        String pkId = mmapplication.getPkId();
        if (!this.update(mmapplication)) {
            return false;
        }
        if (!executeParamService.deleteByFkId(pkId)) {
            return false;
        }
        executeParamService.insertList(pkId, mmApplicationParamView.getMmAppExecuteParam());
        if (!returnParamService.deleteByFkId(pkId)) {
            return false;
        }
        returnParamService.insertList(pkId, mmApplicationParamView.getMmAppReturnParam());
        return true;
    }


    /**
     * 根据应用Ip查询应用信息
     *
     * @param appId 应用主键
     * @return
     */
    public MmFullAppInfoView selectFullAppInfo(String appId) {

        //根据应用Ip查询应用基本信息
        MmFullAppInfoView appInfo = this.mmApplicationMapper.selectFullAppInfo(appId);
        if (appInfo == null) {
            return null;
        }
        List<MmAppExecuteParam> executeParams = executeParamService.selectByFkId(appId);
        List<MmAppReturnParam> returnParams = returnParamService.selectByFkId(appId);
        appInfo.setExecuteParams(executeParams);
        appInfo.setReturnParams(returnParams);
        return appInfo;
    }

    public List<MmApplication> selectAll() {
        return this.mmApplicationMapper.selectAll();
    }

    public List<MmApplication> selectByModelId(String modelId) {
        return this.mmApplicationMapper.selectByModelId(modelId);
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.mm.model.MmApplication");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("查询字段", "com.hex.bigdata.udsp.mm.model.MmAppExecuteParam", 10, 0, 1, ComExcelEnums.MmAppliactionExecuteParam.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("返回字段", "com.hex.bigdata.udsp.mm.model.MmAppReturnParam", 10, 0, 2, ComExcelEnums.MmAppliactionReturnParam.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<MmApplication> mmApplications = (List<MmApplication>) uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.MmApplication");
                MmApplication mmApplication = mmApplications.get(0);
                if (mmApplicationMapper.selectByName(mmApplication.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称重复！");
                    break;
                }
                if (modelInfoMapper.selectByName(mmApplication.getModelId()) == null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个应用对应的模型配置不存在！");
                    break;
                }
                //设置模型id
                mmApplication.setModelId(modelInfoMapper.selectByName(mmApplication.getModelId()).getPkId());
                String pkId = insert(mmApplication);

                List<MmAppExecuteParam> mmAppExecuteParams = (List<MmAppExecuteParam>) uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.MmAppExecuteParam");
                List<MmAppReturnParam> mmAppReturnParams = (List<MmAppReturnParam>) uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.MmAppReturnParam");

                boolean insertQuery = executeParamService.insertList(pkId, mmAppExecuteParams);
                boolean insertReturn = returnParamService.insertList(pkId, mmAppReturnParams);

                if (insertQuery && insertReturn) {
                    resultMap.put("status", "true");
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个保存失败！");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status", "false");
            resultMap.put("message", "程序内部异常：" + e.getMessage());
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

    public String createExcel(MmApplication[] mmApplications) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_mmApplication.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator + "download_mmApplication_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        for (MmApplication mmApplication : mmApplications) {
            this.setWorkbookSheet(workbook, sourceSheet, comExcelParams, mmApplication);
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
            //模型调用为第四个sheet
            sourceSheet = sourceWork.getSheetAt(3);
            //创建表格
        } catch (IOException e) {
            e.printStackTrace();
        }
        RcService rcService = null;
        if (StringUtils.isNotBlank(rcUserService.getServiceId())) {
            rcService = rcServiceService.select(rcUserService.getServiceId());
        }
        MmApplication mmApplication = null;
        if (null != rcService) {
            mmApplication = this.select(rcService.getAppId());
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

        if (null != mmApplication) {
            maxSize = mmApplication.getMaxNum() == null ? 65535 : mmApplication.getMaxNum();
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
        MmIndexDto mmIndexDto = new MmIndexDto(i, 20);
        this.setWorkbookSheetPart(sheet, mmApplication, sourceSheet, workbook, mmIndexDto);

    }

    /**
     * 设置信息到workbook
     *
     * @param workbook
     * @param sourceSheet
     * @param comExcelParams
     * @param mmApplication
     */
    public void setWorkbookSheet(HSSFWorkbook workbook, HSSFSheet sourceSheet, List<ComExcelParam> comExcelParams, MmApplication mmApplication) {

        HSSFSheet sheet;
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
        MmApplication mmApp = mmApplicationMapper.select(mmApplication.getPkId());
        //设置模型名称
        mmApp.setModelId(modelInfoMapper.select(mmApp.getModelId()).getName());
        for (ComExcelParam comExcelParam : comExcelParams) {
            try {
                Field field = mmApp.getClass().getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(mmApp) == null ? "" : field.get(mmApp).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MmIndexDto mmIndexDto = new MmIndexDto(i, 17);
        this.setWorkbookSheetPart(sheet, mmApplication, sourceSheet, workbook, mmIndexDto);
    }

    public void setWorkbookSheetPart(HSSFSheet sheet, MmApplication mmApplication, HSSFSheet sourceSheet, HSSFWorkbook workbook, MmIndexDto mmIndexDto) {
        HSSFRow row;
        HSSFCell cell;
        int rowIndex = mmIndexDto.getRowIndex();
        List<MmAppExecuteParam> mmAppExecuteParams = executeParamService.selectByFkId(mmApplication.getPkId());
        if (mmAppExecuteParams.size() > 0) {
            for (MmAppExecuteParam mmAppExecuteParam : mmAppExecuteParams) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(mmAppExecuteParam.getSeq());
                cell = row.createCell(1);
                cell.setCellValue(mmAppExecuteParam.getName());
                cell = row.createCell(2);
                cell.setCellValue(mmAppExecuteParam.getDescribe());
                cell = row.createCell(3);
                cell.setCellValue(mmAppExecuteParam.getDefaultVal());
                cell = row.createCell(4);
                cell.setCellValue(mmAppExecuteParam.getIsNeed());
                rowIndex++;
            }
        }
        try {
            ExcelCopyUtils.copyRow(sheet.createRow(rowIndex++), sourceSheet.getRow(mmIndexDto.getReturnTitleIndex()), sheet.createDrawingPatriarch(), workbook);
            ExcelCopyUtils.copyRow(sheet.createRow(rowIndex++), sourceSheet.getRow(mmIndexDto.getReturnTitleIndex() + 1), sheet.createDrawingPatriarch(), workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<MmAppReturnParam> mmAppReturnParams = returnParamService.selectByFkId(mmApplication.getPkId());
        if (mmAppReturnParams.size() > 0) {
            for (MmAppReturnParam mmAppReturnParam : mmAppReturnParams) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(mmAppReturnParam.getSeq());
                cell = row.createCell(1);
                cell.setCellValue(mmAppReturnParam.getName());
                cell = row.createCell(2);
                cell.setCellValue(mmAppReturnParam.getDescribe());
                rowIndex++;
            }
        }

    }

}
