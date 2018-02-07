package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.ed.constant.RcServiceStatus;
import com.hex.bigdata.udsp.ed.dao.EdApplicationMapper;
import com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.EdApplicationDto;
import com.hex.bigdata.udsp.ed.dto.EdApplicationExlDto;
import com.hex.bigdata.udsp.ed.dto.EdApplicationParamDto;
import com.hex.bigdata.udsp.ed.dto.EdIndexDto;
import com.hex.bigdata.udsp.ed.model.EdAppRequestParam;
import com.hex.bigdata.udsp.ed.model.EdAppResponseParam;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.rc.dao.RcServiceMapper;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/6
 * TIME:9:15
 */
@Service
public class EdApplicationService extends BaseService {

    @Autowired
    private EdApplicationMapper edApplicationMapper;

    @Autowired
    private EdAppRequestParamService edAppRequestParamService;

    @Autowired
    private EdAppResponseParamService edAppResponseParamService;

    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;

    @Autowired
    private RcServiceMapper rcServiceMapper;

    private static List<ComExcelParam> comExcelParams = new ArrayList<>();

    static {
        comExcelParams.add(new ComExcelParam(1, 1, "name"));
        comExcelParams.add(new ComExcelParam(1, 3, "interfaceId"));
        comExcelParams.add(new ComExcelParam(2, 1, "describe"));
        comExcelParams.add(new ComExcelParam(2, 3, "maxNum"));
        comExcelParams.add(new ComExcelParam(3, 1, "note"));
        comExcelParams.add(new ComExcelParam(3, 3, "isCheck"));
    }

    public int deleteByPrimaryKey(String pkId) {
        return edApplicationMapper.deleteByPrimaryKey(pkId);
    }

    /**
     * 添加接口应用信息
     * @param edApplication
     * @return
     * @throws Exception
     */
    public String insert(EdApplication edApplication) throws Exception {
        EdApplication edApplication1 = this.getEdApplicationByName(edApplication.getName());
        if (edApplication1 != null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pkId = Util.uuid();
        edApplication.setPkId(pkId);
        edApplication.setCrtUser(WebUtil.getCurrentUserId());
        edApplication.setCrtTime(format.format(new Date()));
        int result = edApplicationMapper.addEdApplication(edApplication);
        if (result == 1) {
            return pkId;
        }
        return "";
    }

    public int addEdApplication(EdApplication edApplication) {
        EdApplication edApplication1 = this.getEdApplicationByName(edApplication.getName());
        if (edApplication1 != null) {
            return -1;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
        edApplication.setPkId(Util.uuid());
        edApplication.setCrtUser(WebUtil.getCurrentUserId());
        edApplication.setCrtTime(format.format(new Date()));
        return edApplicationMapper.addEdApplication(edApplication);
    }

    /**
     * 修改接口应用信息
     * @param edApplication
     * @return
     */
    public int updateEdApplication(EdApplication edApplication) {
        EdApplication edApplication1 = this.getEdApplicationByName(edApplication.getName());
        if (edApplication1 != null && !edApplication.getPkId().equals(edApplication1.getPkId())) {
            return -1;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        edApplication.setUptUser(WebUtil.getCurrentUserId());
        edApplication.setUptTime(format.format(new Date()));
        return edApplicationMapper.updateEdApplication(edApplication);
    }

    public int deleteEdApplication(String pkId) {
        EdApplication edApplication = new EdApplication();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        edApplication.setUptUser(WebUtil.getCurrentUserId());
        edApplication.setUptTime(format.format(new Date()));
        return edApplicationMapper.deleteEdApplication(edApplication);
    }

    /**
     * 通过ID查询接口应用信息
     * @param pkId
     * @return
     */
    public EdApplication selectByPrimaryKey(String pkId) {
        return edApplicationMapper.selectByPrimaryKey(pkId);
    }

    public List<EdApplication> getEdApplicationAll() {
        return edApplicationMapper.getEdApplicationAll();
    }

    /**
     * 获取接口应用列表
     * @param edApplicationDto
     * @param page
     * @return
     */
    public List<EdApplicationDto> getEdApplicationInfoPage(EdApplicationDto edApplicationDto, Page page) {
        return edApplicationMapper.getEdApplicationInfoPage(edApplicationDto, page);
    }

    /**
     * 总入口
     * 添加接口应用
     * @param edApplicationParamDto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult addEdApplicationAndParam(EdApplicationParamDto edApplicationParamDto) throws Exception {
        //添加接口应用
        String result = insert(edApplicationParamDto.getEdApplication());
        if (result == "") {
            throw new Exception();
        }
        //添加输入参数
        edAppRequestParamService.addEdAppRequestParam(result, edApplicationParamDto.getEdAppRequestParam());
        //添加输出参数
        edAppResponseParamService.addEdAppResponseParam(result, edApplicationParamDto.getEdAppResponseParam());
        return new MessageResult(true, "添加成功");
    }

    /**
     * 总入口
     * 修改接口信息
     * @param edApplicationParamDto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult updateEdApplicationAndParam(EdApplicationParamDto edApplicationParamDto) throws Exception {
        String appId = edApplicationParamDto.getEdApplication().getPkId();
        //校验服务是否停止
        String result = this.checkRcService(appId);
        if (!"".equals(result)) {
            return new MessageResult(false, "此服务正在使用，请停止服务后修改！");
        }
        //修改接口应用信息
        int result1 = this.updateEdApplication(edApplicationParamDto.getEdApplication());
        //删除接口应用输入参数
        int result2 = edAppRequestParamService.deleteEdAppRequestParamByAppId(appId);
        //删除接口应用输出参数
        int result3 = edAppResponseParamService.deleteEdAppResponseParamByAppId(appId);
        if (result1 != 1 || result2 <= 0 || result3 <= 0) {
            throw new Exception();
        }
        //删除接口应用输入参数
        edAppRequestParamService.addEdAppRequestParam(appId, edApplicationParamDto.getEdAppRequestParam());
        //删除接口应用输出参数
        edAppResponseParamService.addEdAppResponseParam(appId, edApplicationParamDto.getEdAppResponseParam());
        return new MessageResult(true, "修改成功");
    }

    /**
     * 总入口
     * 删除接口应用
     * @param edApplications
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult deleteEdApplicationAndParam(EdApplication[] edApplications) throws Exception {
        String str = "";
        for (EdApplication edApplication : edApplications) {
            String pkId = edApplication.getPkId();
            //校验服务是否启动
            String result = this.checkRcService(pkId);
            if (!"".equals(result)) {
                String name = edApplication.getName();
                String desc = edApplication.getDescribe();
                str = str + "[" + name + ":" + desc + "];";
            }
        }
        if (!"".equals(str)) {
            return new MessageResult(false, str + "服务正在使用，请停止服务后修改！");
        }

        for (EdApplication edApplication : edApplications) {
            String pkId = edApplication.getPkId();
            //删除接口应用信息
            int result1 = this.deleteByPrimaryKey(pkId);
            //删除接口应用输入参数
            int result2 = edAppRequestParamService.deleteEdAppRequestParamByAppId(pkId);
            //删除接口应用输出参数
            int result3 = edAppResponseParamService.deleteEdAppResponseParamByAppId(pkId);
            if (result1 != 1 || result2 <= 0 || result3 <= 0) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "删除成功");
    }

    /**
     * 通过接口名查询接口应用信息
     * @param name
     * @return
     */
    public EdApplication getEdApplicationByName(String name) {
        return edApplicationMapper.getEdApplicationByName(name);
    }

    /**
     * 校验服务是否正在运行
     * @param appId
     * @return
     */
    public String checkRcService(String appId) {
        //校验服务是否正在运行
        String str = "";
        RcService rcService = rcServiceMapper.selectByAppTypeAndAppId("ED", appId);
        if (rcService == null) {
            return str;
        }
        if (RcServiceStatus.START.getValue().equals(rcService.getStatus())) {
            str = appId;
        }
        return str;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.ed.model.EdApplication");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("查询字段", "com.hex.bigdata.udsp.ed.model.EdAppRequestParam", 10, 0, 1, ComExcelEnums.EdAppRequestParam.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("返回字段", "com.hex.bigdata.udsp.ed.model.EdAppResponseParam", 10, 0, 2, ComExcelEnums.EdAppResponseParam.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<EdApplication> edApplications = (List<EdApplication>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.EdApplication");
                EdApplication edApplication = edApplications.get(0);
                InterfaceInfo interfaceInfo = interfaceInfoMapper.getInterfaceInfoByInterfaceCode(edApplication.getName());
                if (edApplicationMapper.getEdApplicationByName(edApplication.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称重复！");
                    break;
                }
                if (interfaceInfo == null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个应用对应的模型配置不存在！");
                    break;
                }
                //设置模型id
                edApplication.setInterfaceId(interfaceInfo.getPkId());
                String pkId = insert(edApplication);

                List<EdAppRequestParam> edAppExecuteParams = (List<EdAppRequestParam>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.EdAppRequestParam");
                List<EdAppResponseParam> edAppReturnParams = (List<EdAppResponseParam>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.EdAppResponseParam");

                MessageResult messageResult1 = edAppRequestParamService.addEdAppRequestParam(pkId, edAppExecuteParams);
                MessageResult messageResult2 = edAppResponseParamService.addEdAppResponseParam(pkId, edAppReturnParams);

                if (messageResult1.isStatus() && messageResult2.isStatus()) {
                    resultMap.put("status", "true");
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "参数保存失败！");
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

    public String createExcel(EdApplication[] edApplications) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_edApplication.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator + "download_edApplication_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        for (EdApplication edApplication : edApplications) {
            this.setWorkbookSheet(workbook, sourceSheet, comExcelParams, edApplication);
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

    /**
     * 设置信息到workbook
     *
     * @param workbook
     * @param sourceSheet
     * @param comExcelParams
     * @param edApplication
     */
    public void setWorkbookSheet(HSSFWorkbook workbook, HSSFSheet sourceSheet, List<ComExcelParam> comExcelParams, EdApplication edApplication) {

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
        EdApplicationExlDto edApp = edApplicationMapper.getEdApplicationExl(edApplication.getPkId());
        //设置模型名称
        edApp.setInterfaceId(edApp.getInterfaceId());
        for (ComExcelParam comExcelParam : comExcelParams) {
            try {
                Field field = edApp.getClass().getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(edApp) == null ? "" : field.get(edApp).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        EdIndexDto edIndexDto = new EdIndexDto(i, 17);
        this.setWorkbookSheetPart(sheet, edApplication, sourceSheet, workbook, edIndexDto);
    }

    public void setWorkbookSheetPart(HSSFSheet sheet, EdApplication edApplication, HSSFSheet sourceSheet, HSSFWorkbook workbook, EdIndexDto edIndexDto) {
        HSSFRow row;
        HSSFCell cell;
        int rowIndex = edIndexDto.getRowIndex();
        List<EdAppRequestParam> edAppRequestParams = edAppRequestParamService.getEdAppRequestParamByAppId(edApplication.getPkId());
        if (edAppRequestParams.size() > 0) {
            for (EdAppRequestParam edAppRequestParam : edAppRequestParams) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(edAppRequestParam.getSeq());
                cell = row.createCell(1);
                cell.setCellValue(edAppRequestParam.getName());
                cell = row.createCell(2);
                cell.setCellValue(edAppRequestParam.getDescribe());
                cell = row.createCell(3);
                cell.setCellValue(edAppRequestParam.getDefaultVal());
                cell = row.createCell(4);
                cell.setCellValue(edAppRequestParam.getIsNeed());
                rowIndex++;
            }
        }
        try {
            ExcelCopyUtils.copyRow(sheet.createRow(rowIndex++), sourceSheet.getRow(edIndexDto.getReturnTitleIndex()),
                    sheet.createDrawingPatriarch(), workbook);
            ExcelCopyUtils.copyRow(sheet.createRow(rowIndex++),
                    sourceSheet.getRow(edIndexDto.getReturnTitleIndex() + 1), sheet.createDrawingPatriarch(), workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<EdAppResponseParam> edAppResponseParams = edAppResponseParamService.getEdAppResponseParamByAppId(edApplication.getPkId());
        if (edAppResponseParams.size() > 0) {
            for (EdAppResponseParam edAppResponseParam : edAppResponseParams) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(edAppResponseParam.getSeq());
                cell = row.createCell(1);
                cell.setCellValue(edAppResponseParam.getName());
                cell = row.createCell(2);
                cell.setCellValue(edAppResponseParam.getDescribe());
                rowIndex++;
            }
        }

    }

    public List selectAll() {
        return this.edApplicationMapper.selectEnableAll();
    }

    public List<EdApplication> selectByInterfaceId(String interfaceId) {
        return this.edApplicationMapper.selectByInterfaceId(interfaceId);
    }
}
