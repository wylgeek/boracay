package com.hex.bigdata.udsp.ed.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.ed.connect.service.ConnectService;
import com.hex.bigdata.udsp.ed.connect.service.HiveService;
import com.hex.bigdata.udsp.ed.constant.InterfaceType;
import com.hex.bigdata.udsp.ed.constant.RcServiceStatus;
import com.hex.bigdata.udsp.ed.dao.InterfaceInfoMapper;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoDto;
import com.hex.bigdata.udsp.ed.dto.InterfaceInfoParamDto;
import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.ed.model.EdInterfaceParam;
import com.hex.bigdata.udsp.ed.model.InterfaceInfo;
import com.hex.bigdata.udsp.rc.dao.RcServiceMapper;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
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
import java.util.*;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:16:19
 */
@Service
public class InterfaceInfoService {
    @Autowired
    private InterfaceInfoMapper interfaceInfoMapper;

    @Autowired
    private EdInterfaceParamService edInterfaceParamService;

    @Autowired
    private EdApplicationService edApplicationService;

    @Autowired
    private RcServiceMapper rcServiceMapper;

    @Autowired
    private ConnectService connectService;

    @Autowired
    private HiveService hiveService;

    private static List<ComExcelParam> comExcelParams = new ArrayList<>();

    static {
        //固定栏内容
        comExcelParams.add(new ComExcelParam(1, 1, "interfaceCode"));
        comExcelParams.add(new ComExcelParam(1, 3, "interfaceName"));
        comExcelParams.add(new ComExcelParam(2, 1, "validTime"));
        comExcelParams.add(new ComExcelParam(2, 3, "reqUrl"));
        comExcelParams.add(new ComExcelParam(3, 1, "interfaceType"));
        comExcelParams.add(new ComExcelParam(3, 3, "interfaceCompany"));
        comExcelParams.add(new ComExcelParam(4, 1, "interfaceDesc"));
    }

    /**
     * 通过ID获取接口信息
     * @param pkId
     * @return
     */
    public InterfaceInfo getInterfaceInfoByPkId(String pkId) {
        return interfaceInfoMapper.getInterfaceInfoByPkId(pkId);
    }

    /**
     * 获取接口信息列表
     * @param interfaceInfoDto
     * @param page
     * @return
     */
    public List<InterfaceInfo> getInterfaceInfoList(InterfaceInfoDto interfaceInfoDto, Page page) {
        return interfaceInfoMapper.getInterfaceInfoList(interfaceInfoDto, page);
    }

    /**
     * 通过接口编码，获取接口信息
     * @param interfaceCode
     * @return
     */
    public InterfaceInfo getInterfaceInfoByInterfaceCode(String interfaceCode) {
        return interfaceInfoMapper.getInterfaceInfoByInterfaceCode(interfaceCode);
    }

    /**
     * 添加接口信息
     * @param interfaceInfo
     * @return
     * @throws Exception
     */
    public MessageResult addInterfaceInfo(InterfaceInfo interfaceInfo) throws Exception {
        //检查是否已经存在
        InterfaceInfo interfaceInfo1 = getInterfaceInfoByInterfaceCode(interfaceInfo.getInterfaceCode());
        if (interfaceInfo1 != null) {
            return new MessageResult(false, "服务编码已存在，请重新输入！");
        }

        //判断是否是缓存数据，并创建缓存需用的表
        if (InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
            createCacheTable(interfaceInfo);
        }

        //添加数据
        String pkId = Util.uuid();
        interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        interfaceInfo.setPkId(pkId);
        interfaceInfo.setCrtUser(WebUtil.getCurrentUserId());
        interfaceInfo.setCrtTime(new Date());
        int result = interfaceInfoMapper.addInterfaceInfo(interfaceInfo);

        //校验返回结果
        if (result != 1) {
            throw new Exception();
        }
        MessageResult messageResult = new MessageResult();
        messageResult.setStatus(true);
        messageResult.setMessage(pkId);
        return messageResult;
    }

    /**
     * 总入口
     * 修改接口信息
     * @param interfaceInfoParamDto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult updateInterfaceInfoByPkId(InterfaceInfoParamDto interfaceInfoParamDto) throws Exception {
        String interfaceId = interfaceInfoParamDto.getInterfaceInfo().getPkId();
        String str = this.checkRcService(interfaceId);
        if (!"".equals(str)) {
            return new MessageResult(false, str + "服务正在使用，请停止服务后修改！");
        }
        //删除原来的输入、输出参数信息
        edInterfaceParamService.deleteByInterfaceId(interfaceId);
        //修改接口信息
        MessageResult messageResult1 = updateInterfaceInfoByPkId(interfaceInfoParamDto.getInterfaceInfo());
        //添加输入参数
        MessageResult messageResult2 = edInterfaceParamService.insertRequestColList(interfaceId, interfaceInfoParamDto.getEdInterfaceParamsRequest());
        //添加输出参数
        MessageResult messageResult3 = edInterfaceParamService.insertResponseColList(interfaceId, interfaceInfoParamDto.getEdInterfaceParamsResponse());

        if (messageResult1.isStatus() && messageResult2.isStatus() && messageResult3.isStatus()) {
            return new MessageResult(true, "修改成功");
        } else {
            throw new Exception();
        }
    }

    /**
     * 修改接口信息
     * @param interfaceInfo
     * @return
     * @throws Exception
     */
    public MessageResult updateInterfaceInfoByPkId(InterfaceInfo interfaceInfo) throws Exception {
        //检查是否已经存在
        InterfaceInfo interfaceInfo1 = getInterfaceInfoByInterfaceCode(interfaceInfo.getInterfaceCode());
        if(interfaceInfo1 != null) {
            if(!interfaceInfo.getPkId().equals(interfaceInfo1.getPkId())) {
                return new MessageResult(false, "服务编码已存在，请重新输入！");
            } else if(!InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo1.getInterfaceType())
                    && InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
                //服务编码已存在,并从实时数据变成缓存数据
                createCacheTable(interfaceInfo);
            }
        } else if(InterfaceType.CACHE_ACCESS.getValue().equals(interfaceInfo.getInterfaceType())) {
            //服务编码不存在，同时要缓存数据
            createCacheTable(interfaceInfo);
        }

        interfaceInfo.setInterfaceCode(interfaceInfo.getInterfaceCode().trim());
        interfaceInfo.setReqUrl(interfaceInfo.getReqUrl().trim());
        interfaceInfo.setUpdateUser(WebUtil.getCurrentUserId());
        interfaceInfo.setUpdateTime(new Date());
        int result = interfaceInfoMapper.updateInterfaceInfoByPkId(interfaceInfo);
        if (result != 1) {
            throw new Exception();
        }
        return new MessageResult(true, "更新数据成功！");
    }

    /**
     * 总入口
     * 删除接口
     * @param interfaceInfos
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult deleteInterfaceInfo(InterfaceInfo[] interfaceInfos) throws Exception {
        for (InterfaceInfo interfaceInfo : interfaceInfos) {
            String interfaceId = interfaceInfo.getPkId();
            if (checkAppIsExist(interfaceId)) {
                return new MessageResult(false, "请先删除接口编码为：" + interfaceInfo.getInterfaceCode() + "的应用配置");
            }
        }

        for (InterfaceInfo interfaceInfo : interfaceInfos) {
            //删除接口
            int result1 = interfaceInfoMapper.deleteInterfaceInfo(interfaceInfo.getPkId());
            //删除输入、输出参数
            int result2 = edInterfaceParamService.deleteByInterfaceId(interfaceInfo.getPkId());
            if (result1 == 0 && result2 <= 0) {
                throw new Exception();
            }
        }
        return new MessageResult(true, "删除成功！");
    }

    /**
     * 总入口
     * 保存接口配置
     * @param interfaceInfoParamDto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult addInterfaceInfo(InterfaceInfoParamDto interfaceInfoParamDto) throws Exception {
        //添加接口信息
        MessageResult messageResult1 = this.addInterfaceInfo(interfaceInfoParamDto.getInterfaceInfo());
        if (messageResult1.isStatus()) {
            //添加输入参数
            MessageResult messageResult2 = edInterfaceParamService.insertRequestColList(messageResult1.getMessage(),
                    interfaceInfoParamDto.getEdInterfaceParamsRequest());
            //添加输出参数
            MessageResult messageResult3 = edInterfaceParamService.insertResponseColList(messageResult1.getMessage(),
                    interfaceInfoParamDto.getEdInterfaceParamsResponse());
            if (messageResult2.isStatus() && messageResult3.isStatus()) {
                return new MessageResult(true, "保存成功");
            }
            throw new Exception();
        }
        return new MessageResult(false, "保存失败");
    }

    public List<InterfaceInfo> getInterfaceInfoList() {
        return interfaceInfoMapper.getInterfaceInfoList();
    }

    /**
     * 校验服务是否正在运行
     * @param interfaceId
     * @return
     */
    public String checkRcService(String interfaceId) {
        //校验服务是否正在运行
        String str = "";
        List<EdApplication> edApplications = edApplicationService.selectByInterfaceId(interfaceId);
        for (EdApplication edApplication : edApplications) {
            String appId = edApplication.getPkId();
            RcService rcService = rcServiceMapper.selectByAppTypeAndAppId("ED", appId);
            if (rcService == null) {
                continue;
            }
            if (RcServiceStatus.START.getValue().equals(rcService.getStatus())) {
                String name = edApplication.getName();
                String desc = edApplication.getDescribe();
                str = str + "[" + name + ":" + desc + "];";
            }
        }
        return str;
    }

    /**
     * 校验接口应用是否存在
     * @param interfaceId
     * @return
     */
    public Boolean checkAppIsExist(String interfaceId) {
        List<EdApplication> edApplications = edApplicationService.selectByInterfaceId(interfaceId);
        if (edApplications.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建hbase表，hive关联表
     * @param interfaceInfo
     * @throws Exception
     */
    private void createCacheTable(InterfaceInfo interfaceInfo) throws Exception{
        //在hbase中创建对应的表，正常创建则返回true，不成功false
        Boolean result1 = connectService.createTable(interfaceInfo.getInterfaceCode());
        //创建hbase的关联表hive
        String tableName = "udsp_" + interfaceInfo.getInterfaceCode();
        tableName = tableName.toLowerCase();
        String tableComment = interfaceInfo.getInterfaceName() + "关联表";
        Boolean result2 = hiveService.createHBaseCorrelationTable(tableName, tableComment, tableName);
        if (!result1 || !result2) {
            throw new Exception();
        }
    }

    /**
     * 数据源excel文件导入
     * @param uploadFilePath
     * @return
     */
    public Map<String, String> uploadExcel(String uploadFilePath) {
        Map resultMap = new HashMap<String, String>(2);
        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.ed.model.InterfaceInfo");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("查询字段", "com.hex.bigdata.udsp.ed.model.EdInterfaceParam", 11, 0, 1, ComExcelEnums.EdInterfaceParam.getAllNums()));
            comExcelPropertiesList.add(new ComExcelProperties("返回字段", "com.hex.bigdata.udsp.ed.model.EdInterfaceParam", 10, 0, 2, ComExcelEnums.EdInterfaceParam.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<InterfaceInfo> interfaceInfos = (List<InterfaceInfo>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.InterfaceInfo");
                InterfaceInfo interfaceInfo = interfaceInfos.get(0);
                if (interfaceInfoMapper.getInterfaceInfoByInterfaceCode(interfaceInfo.getInterfaceCode()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称重复！");
                    break;
                }
                MessageResult messageResult = addInterfaceInfo(interfaceInfo);
                String pkId = null;
                if (messageResult.isStatus()) {
                    pkId = messageResult.getMessage();
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个保存失败！");
                    break;
                }

                List<EdInterfaceParam> edInterfaceParamsReq = (List<EdInterfaceParam>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.EdInterfaceParam");
                List<EdInterfaceParam> edInterfaceParamsRes = (List<EdInterfaceParam>) uploadExcelModel.get("com.hex.bigdata.udsp.ed.model.EdInterfaceParam1");

                MessageResult messageResult1 = edInterfaceParamService.insertRequestColList(pkId, edInterfaceParamsReq);
                MessageResult messageResult2 = edInterfaceParamService.insertResponseColList(pkId, edInterfaceParamsRes);

                if (messageResult1.isStatus() && messageResult2.isStatus()) {
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

    /**
     * 下载excel表格
     *
     * @param interfaceInfos
     * @return
     */
    public String createExcel(InterfaceInfo[] interfaceInfos) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_edInterfaceInfo.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator + "downLoad_edInterfaceInfo_excel" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        comExcelParams.add(new ComExcelParam(1, 1, "interfaceCode"));
        comExcelParams.add(new ComExcelParam(1, 3, "interfaceName"));
        comExcelParams.add(new ComExcelParam(2, 1, "validTime"));
        comExcelParams.add(new ComExcelParam(2, 3, "reqUrl"));
        comExcelParams.add(new ComExcelParam(3, 1, "interfaceType"));
        comExcelParams.add(new ComExcelParam(3, 3, "interfaceCompany"));
        comExcelParams.add(new ComExcelParam(4, 1, "interfaceDesc"));
        for (InterfaceInfo interfaceInfo : interfaceInfos) {
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

            //设置内容
            InterfaceInfo interfaceInfo1 = interfaceInfoMapper.getInterfaceInfoByPkId(interfaceInfo.getPkId());
            for (ComExcelParam comExcelParam : comExcelParams) {
                try {
                    Field field = interfaceInfo1.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(interfaceInfo1) == null ? "" : field.get(interfaceInfo1).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<EdInterfaceParam> edInterfaceParams = edInterfaceParamService.selectByInterfaceIdAndType(interfaceInfo1.getPkId(), "1");
            if (edInterfaceParams.size() > 0) {
                for (EdInterfaceParam edInterfaceParam : edInterfaceParams) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(edInterfaceParam.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(edInterfaceParam.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(edInterfaceParam.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(edInterfaceParam.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(edInterfaceParam.getNote());
                    i++;
                }
            }
            try {
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(17), sheet.createDrawingPatriarch(), workbook);
                ExcelCopyUtils.copyRow(sheet.createRow(i++), sourceSheet.getRow(18), sheet.createDrawingPatriarch(), workbook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<EdInterfaceParam> edInterfaceParams1 = edInterfaceParamService.selectByInterfaceIdAndType(interfaceInfo1.getPkId(), "2");
            if (edInterfaceParams1.size() > 0) {
                for (EdInterfaceParam edInterfaceParam : edInterfaceParams1) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(edInterfaceParam.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(edInterfaceParam.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(edInterfaceParam.getDescribe());
                    cell = row.createCell(3);
                    cell.setCellValue(edInterfaceParam.getColType());
                    cell = row.createCell(4);
                    cell.setCellValue(edInterfaceParam.getNote());
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
