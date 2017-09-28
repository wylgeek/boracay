package com.hex.bigdata.udsp.olq.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.constant.DatasourceType;
import com.hex.bigdata.udsp.common.dto.ComDatasourceView;
import com.hex.bigdata.udsp.common.model.ComDatasource;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.provider.model.Datasource;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.olq.constant.OLQConstant;
import com.hex.bigdata.udsp.olq.dao.OLQApplicationMapper;
import com.hex.bigdata.udsp.olq.dto.OLQApplicationDto;
import com.hex.bigdata.udsp.olq.dto.OLQApplicationView;
import com.hex.bigdata.udsp.olq.model.OLQApplication;
import com.hex.bigdata.udsp.olq.model.OLQApplicationParam;
import com.hex.bigdata.udsp.olq.model.OLQQuerySql;
import com.hex.bigdata.udsp.olq.provider.Provider;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 联机查询应用管理服务
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/6/26
 * TIME:10:48
 */
@Service
public class OLQApplicationService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(OLQApplicationService.class);

    /**
     * 联机查询应用管理DAO层服务
     */
    @Autowired
    private OLQApplicationMapper olqApplicationMapper;

    /**
     * 数据源服务
     */
    @Autowired
    private ComDatasourceService comDatasourceService;

    /**
     * 占位符参数服务
     */
    @Autowired
    private OLQApplicationParamService olqApplicationParamService;

    @Autowired
    private OlqProviderService olqProviderService;

    /**
     * 分页查询
     *
     * @param olqApplicationView
     * @param page
     * @return
     */
    public List<OLQApplicationView> select(OLQApplicationView olqApplicationView, Page page) {
        return this.olqApplicationMapper.selectPage(olqApplicationView, page);
    }

    /**
     * 应用信息插入到数据库
     *
     * @param oLQApplicationDto
     * @return
     */
    @Transactional
    public String insert(OLQApplicationDto oLQApplicationDto) {
        String pkId = Util.uuid();
        OLQApplication olqApplication = oLQApplicationDto.getOlqApplication();
        olqApplication.setPkId(pkId);
        this.olqApplicationMapper.insert(pkId, olqApplication);
        List<OLQApplicationParam> params = oLQApplicationDto.getParams();
        if (params != null && params.size() != 0) {
            olqApplicationParamService.insertList(params, pkId);
        }
        return pkId;
    }

    /**
     * @param oLQApplicationDto
     * @return
     */
    @Transactional
    public boolean update(OLQApplicationDto oLQApplicationDto) {
        //应用信息更新到数据库
        OLQApplication olqApplication = oLQApplicationDto.getOlqApplication();
        boolean updateFlg = this.olqApplicationMapper.update(olqApplication.getPkId(), olqApplication);
        if (!updateFlg) {
            logger.info("更新联机查询应用信息失败！,参数信息如下：【" + oLQApplicationDto.toString() + "】");
            return updateFlg;
        }
        String pkId = olqApplication.getPkId();
        //应用参数配置信息更新到数据库
        boolean delFlg = this.olqApplicationParamService.deleteByAppId(pkId);
        if (!delFlg) {
            logger.info("删除更新联机查询应用参数信息失败！");
            return delFlg;
        }
        List<OLQApplicationParam> params = oLQApplicationDto.getParams();
        boolean insertFlg = true;
        if (params != null && params.size() != 0) {
            insertFlg = this.olqApplicationParamService.insertList(params, pkId);
        }
        return insertFlg;
    }

    /**
     * 检查应用信息唯一性
     *
     * @param name
     * @return
     */
    public boolean checekUniqueName(String name) {
        OLQApplication olqApplication = this.olqApplicationMapper.selectByName(name);
        return olqApplication != null;
    }

    /**
     * 通过名称查找
     *
     * @param name
     * @return
     */
    public OLQApplication selectByName(String name) {
        return this.olqApplicationMapper.selectByName(name);
    }

    /**
     * 通过名称查找完整的信息
     *
     * @param name
     * @return
     */
    public OLQApplicationDto selectFullInfoByName(String name) {
        OLQApplicationDto olqApplicationDto = new OLQApplicationDto();

        OLQApplication olqApplication = this.olqApplicationMapper.selectByName(name);
        if (olqApplication == null) {
            return null;
        }
        olqApplicationDto.setOlqApplication(olqApplication);
        List<OLQApplicationParam> olqApplicationParams = this.olqApplicationParamService.selectByAppId(olqApplication.getPkId());
        olqApplicationDto.setParams(olqApplicationParams);
        return olqApplicationDto;
    }

    /**
     * 通过主键查询
     *
     * @param pkId
     * @return
     */
    public OLQApplication select(String pkId) {
        return this.olqApplicationMapper.select(pkId);
    }

    /**
     * 通过主键查询
     *
     * @param pkId
     * @return
     */
    public OLQApplicationDto selectFullAppInfo(String pkId) {
        if (StringUtils.isBlank(pkId)) {
            return null;
        }
        OLQApplicationDto applicationDto = new OLQApplicationDto();
        OLQApplication olqApplication = this.select(pkId);
        if (olqApplication != null) {
            applicationDto.setOlqApplication(olqApplication);
            List<OLQApplicationParam> olqApplicationParams = this.olqApplicationParamService.selectByAppId(pkId);
            applicationDto.setParams(olqApplicationParams);
        }
        return applicationDto;
    }

    /**
     * 批量删除
     *
     * @param olqApplications
     * @return
     */
    @Transactional
    public boolean delete(OLQApplication[] olqApplications) {
        for (OLQApplication olqApplication : olqApplications) {
            this.olqApplicationMapper.delete(olqApplication.getPkId());
        }
        return true;
    }

    /**
     * 查询所有存在的应用
     *
     * @return
     */
    public List<OLQApplication> selectAll() {
        return this.olqApplicationMapper.selectAll();
    }

    /**
     * 上传配置信息
     *
     * @param uploadFilePath
     * @return
     */
    public MessageResult uploadExcel(String uploadFilePath) {

        File uploadFile = new File(uploadFilePath);
        FileInputStream in = null;
        try {
            ComUploadExcelContent dataSourceContent = new ComUploadExcelContent();
            dataSourceContent.setClassName("com.hex.bigdata.udsp.olq.model.OLQApplication");
            List<ComExcelParam> comExcelParams = new ArrayList<>();
            comExcelParams.add(new ComExcelParam(2, 1, "name"));
            comExcelParams.add(new ComExcelParam(2, 3, "olqDsName"));
            comExcelParams.add(new ComExcelParam(2, 5, "note"));
            comExcelParams.add(new ComExcelParam(3, 1, "describe"));
            comExcelParams.add(new ComExcelParam(3, 3, "olqSql"));

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();

            comExcelPropertiesList.add(new ComExcelProperties("参数字段", "com.hex.bigdata.udsp.olq.model.OLQApplicationParam", 10, 0, 1, ComExcelEnums.OLQApplicationParamCoumn.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;

            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);

                List<OLQApplication> olqApplications = (List<OLQApplication>) uploadExcelModel.get("com.hex.bigdata.udsp.olq.model.OLQApplication");
                OLQApplication olqApplication = olqApplications.get(0);
                //数据合法性检查
                if (StringUtils.isNotBlank(olqApplication.getName()) && this.selectByName(olqApplication.getName()) != null) {
                    return new MessageResult(false, "第" + (i + 1) + "个名称已存在！");
                }

                List<OLQApplicationParam> olqApplicationParams = (List<OLQApplicationParam>) uploadExcelModel.get("com.hex.bigdata.udsp.olq.model.OLQApplicationParam");
                OLQApplicationDto olqApplicationDto = new OLQApplicationDto();
                olqApplicationDto.setOlqApplication(olqApplication);
                olqApplicationDto.setParams(olqApplicationParams);

                MessageResult messageResult = this.uploadCheck(olqApplicationDto);
                if (!messageResult.isStatus()) {
                    return messageResult;
                }
                olqApplicationDto = (OLQApplicationDto) messageResult.getData();
                String olqPkId = this.insert(olqApplicationDto);
                if (StringUtils.isBlank(olqPkId)) {
                    return new MessageResult(false, "第" + (i + 1) + "个sheet保存失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResult(false, "程序内部异常" + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new MessageResult(true, "上传成功！");
    }


    /**
     * 上传文件内容合法性检查
     *
     * @param olqApplicationDto
     * @return
     */
    public MessageResult uploadCheck(OLQApplicationDto olqApplicationDto) {
        //检查联机查询应用信息合法性校验
        OLQApplication olqApplication = olqApplicationDto.getOlqApplication();
        String olqDsName = olqApplication.getOlqDsName();
        if (StringUtils.isBlank(olqDsName)){
            return new MessageResult(false, "数据源名称不能为空，请检查！");
        }
        //数据源名称
        ComDatasource comDatasource = this.comDatasourceService.selectByModelAndName(DatasourceType.OLQ.getValue(),olqDsName);
        if (comDatasource == null) {
            return new MessageResult(false, "数据源名称对应的数据源不存在，请检查！");
        }
        olqApplication.setOlqDsId(comDatasource.getPkId());
        //联机查询应用名称
        String olqName = olqApplication.getName();
        if (StringUtils.isBlank(olqName)) {
            return new MessageResult(false, "联机查询应用名称不能为空！");
        }
        if (this.selectByName(olqApplication.getName()) != null) {
            return new MessageResult(false, "联机查询应用名称对应的应用已经存在，请检查！");
        }
        //联机查询应用说明
        String olqDesc = olqApplication.getDescribe();
        if (StringUtils.isBlank(olqDesc)) {
            return new MessageResult(false, "联机查询应用说明不能为空！");
        }
        //联机查询应用SQL
        String olqSql = olqApplication.getOlqSql();
        if (StringUtils.isBlank(olqSql)) {
            return new MessageResult(false, "联机查询应用SQL不能为空！");
        }
        //参数意义校验
        List<String> paramNames = new ArrayList<String>();
        try {
            paramNames = getParamNamesFromSql(olqSql);
        } catch (IllegalArgumentException e) {
            return new MessageResult(false, "应用查询SQL语句中的参数名称为空！");
        }
        List<OLQApplicationParam> paramObjects = olqApplicationDto.getParams();
        if ((paramNames == null || paramNames.size() == 0) && (paramObjects == null || paramObjects.size() == 0)) {
            //二者都为空
            return new MessageResult(true, "检查完成，数据正确！", olqApplicationDto);
        } else if ((paramNames != null && paramNames.size() > 0) && (paramObjects != null || paramObjects.size() > 0)) {
            //二者都不为空
            //检查参数个数是否一致
            if (paramNames.size() != paramObjects.size()) {
                return new MessageResult(false, "联机查询应用SQL占位符参数个数和参数列表中参数个数不一致，请检查");
            }
            //检查参数名称是否完全一致
            boolean containFlg = true;
            for (OLQApplicationParam param : paramObjects) {
                if (!paramNames.contains(param.getParamName())) {
                    containFlg = false;
                    break;
                }
            }
            if (!containFlg) {
                return new MessageResult(false, "联机查询应用SQL占位符参数名称和参数列表中参数名称不一致，请检查");
            }
            StringBuffer checkResult = new StringBuffer("");
            //检查参数列合法性
            for (int i = 0; i < paramObjects.size(); i++) {
                OLQApplicationParam param = paramObjects.get(i);
                int seq = param.getSeq();
                String paramDesc = param.getParamDesc();
                if (StringUtils.isBlank(paramDesc)) {
                    checkResult.append("序号为" + seq + "的参数描述不能为空；");
                }
                String isNeed = param.getIsNeed();
                if (StringUtils.isBlank(isNeed)) {
                    checkResult.append("序号为" + seq + "的参数必填的值不能为空；");
                } else if (OLQConstant.OLQ_IS_NEED_CN_YES.equals(isNeed)) {
                    param.setIsNeed(OLQConstant.OLQ_IS_NEED_CODE_YES);
                } else if (OLQConstant.OLQ_IS_NEED_CN_NO.equals(isNeed)) {
                    param.setIsNeed(OLQConstant.OLQ_IS_NEED_CODE_NO);
                } else {
                    checkResult.append("序号为" + seq + "的参数是必填的值非法，请输入是或否；");
                }
            }
            if (StringUtils.isNotBlank(checkResult)) {
                return new MessageResult(false, checkResult.toString());
            }
        } else {
            return new MessageResult(false, "联机查询应用SQL占位符参数个数和参数列表中参数个数不一致，请检查");
        }
        return new MessageResult(true, "检查完成，数据正确！", olqApplicationDto);
    }


    /**
     * 从sql语句中获取参数名称
     *
     * @param olqSql
     * @return
     */
    public List<String> getParamNamesFromSql(String olqSql) {
        String regEx = "\\$\\{\\w{1,}}";
        Pattern pattern = Pattern.compile(regEx);
        List<String> paramNames = new ArrayList<String>();
        Matcher macther = pattern.matcher(olqSql);
        while (macther.find()) {
            String param = macther.group(0);
            if (StringUtils.isBlank(param)) {
                throw new IllegalArgumentException("参数名称为空");
            }
            paramNames.add(param.substring(2, param.length() - 1));
        }
        return paramNames;
    }

    public static void main(String[] args) {

        Map<String, String> paramVals = new HashMap<>();
        paramVals.put("WBJYM", "303");

        Set<String> inputParamsNames = paramVals.keySet();
        String originalSql = "SELECT * FROM OMDATA.S01_SJYMB JYM WHERE JYM.WBJYM =${WBJYM}  and JYM =${WBJYM}  and JYM =${WBJYM}";

        for (String param : inputParamsNames) {
            originalSql = originalSql.replaceAll("\\$\\{" + param + "}", paramVals.get(param));
        }

        System.out.println(originalSql);

    }


    /**
     * 获取分页查询SQL
     *
     * @param olqApplicationDto
     * @param paramVals
     * @param page
     * @return
     */
    public MessageResult getExecuteSQL(OLQApplicationDto olqApplicationDto, Map<String, String> paramVals, com.hex.bigdata.udsp.common.provider.model.Page page) {
        MessageResult messageResult = this.getExecuteSQL(olqApplicationDto, paramVals);
        if (!messageResult.isStatus()) {
            return messageResult;
        }
        OLQApplication olqApplication = olqApplicationDto.getOlqApplication();
        //数据源名称
        ComDatasource comDatasource = this.comDatasourceService.select(olqApplication.getOlqDsId());

        String querySql = (String) messageResult.getData();
        Provider provider = olqProviderService.getProviderImpl(comDatasource);
        OLQQuerySql olqQuerySql = provider.getPageSql(querySql, page);
        messageResult.setData(olqQuerySql);

        return messageResult;
    }

    /**
     * 根据应用配置信息、参数值获取sql语句
     *
     * @param olqApplicationDto
     * @param paramVals
     * @return
     */
    public MessageResult getExecuteSQL(OLQApplicationDto olqApplicationDto, Map<String, String> paramVals) {

        //原生SQL语句
        String originalSql = olqApplicationDto.getOlqApplication().getOlqSql();

        //获取参数列表
        List<OLQApplicationParam> params = olqApplicationDto.getParams();
        Set<String> inputParamsNames = paramVals.keySet();
        if (params == null || params.size() == 0) {
            return new MessageResult(true, "", originalSql);
        } else if ((params != null && params.size() != 0) && (inputParamsNames != null || inputParamsNames.size() != 0)) {
            //比较个数
            if (params.size() != inputParamsNames.size()) {
                return new MessageResult(false, "传入参数值的个数与参数个数不匹配");
            }
            //比较参数名
            //检查参数名称是否完全一致
            boolean containFlg = true;
            for (OLQApplicationParam param : params) {
                if (!inputParamsNames.contains(param.getParamName())) {
                    containFlg = false;
                    break;
                }
            }
            if (!containFlg) {
                return new MessageResult(false, "传入参数值的个数与参数个数不匹配");
            }
            //组装SQL
            for (String param : inputParamsNames) {
                originalSql = originalSql.replaceAll("\\$\\{" + param + "}", paramVals.get(param));
            }
            return new MessageResult(true, "", originalSql);

        } else {
            return new MessageResult(false, "传入参数值的个数与参数个数不匹配");
        }

    }

    /**
     * @param olqApplications
     * @return
     */
    public String downloadExcel(OLQApplication[] olqApplications) {
        //查询出olqApplication所有信息
        List<OLQApplicationDto> olqApplicationDtos = new ArrayList<>();

        for (OLQApplication olqApplication : olqApplications) {
            olqApplicationDtos.add(this.selectFullAppInfo(olqApplication.getPkId()));
        }

        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        // 模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_olqApplication.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_olqApplication_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        comExcelParams.add(new ComExcelParam(2, 1, "name"));
        comExcelParams.add(new ComExcelParam(2, 3, "olqDsName"));
        comExcelParams.add(new ComExcelParam(2, 5, "note"));
        comExcelParams.add(new ComExcelParam(3, 1, "describe"));
        comExcelParams.add(new ComExcelParam(3, 3, "olqSql"));
        for (OLQApplicationDto olqApplicationDto : olqApplicationDtos) {
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
            OLQApplication olqApp = olqApplicationDto.getOlqApplication();
            olqApp.setOlqDsName(comDatasourceService.select(olqApp.getOlqDsId()).getName());
            for (ComExcelParam comExcelParam : comExcelParams) {
                try {
                    Field field = olqApp.getClass().getDeclaredField(comExcelParam.getName());
                    field.setAccessible(true);
                    ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(olqApp) == null ? "" : field.get(olqApp).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            List<OLQApplicationParam> olqApplicationParams = olqApplicationDto.getParams();

            if (olqApplicationParams != null && olqApplicationParams.size() > 0) {
                for (OLQApplicationParam applicationParam : olqApplicationParams) {
                    row = sheet.createRow(i);
                    cell = row.createCell(0);
                    cell.setCellValue(applicationParam.getSeq());
                    cell = row.createCell(1);
                    cell.setCellValue(applicationParam.getParamName());
                    cell = row.createCell(2);
                    cell.setCellValue(applicationParam.getParamDesc());
                    cell = row.createCell(3);
                    cell.setCellValue(applicationParam.getDefaultValue());
                    cell = row.createCell(4);
                    if ("0".equals(applicationParam.getIsNeed())) {
                        cell.setCellValue("是");
                    } else {
                        cell.setCellValue("否");
                    }
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

    public List<ComDatasource> selectOlqDataSource() {
        ComDatasourceView datasourceView = new ComDatasourceView();
        datasourceView.setModel(DatasourceType.OLQ.getValue());
        List<ComDatasource> searchList = comDatasourceService.select(datasourceView);
        return searchList;
    }
}
