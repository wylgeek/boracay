package com.hex.bigdata.udsp.mm.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.service.FtpUserManagerService;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.mm.dao.ContractorMapper;
import com.hex.bigdata.udsp.mm.dto.ContractorView;
import com.hex.bigdata.udsp.mm.model.Contractor;
import com.hex.bigdata.udsp.mm.model.ModelInfo;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/4/12
 * TIME:19:11
 */
@Service
public class ContractorService extends BaseService {

    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(ContractorService.class);

    /**
     * 模型管理-模型基础信息管理Dao层服务
     */
    @Autowired
    private ContractorMapper contractorMapper;

    /**
     * 模型服务
     */
    @Autowired
    private ModelInfoService modelInfoService;

    @Autowired
    private FtpUserManagerService ftpUserManagerService;

    @Transactional
    public String insert(Contractor contractor) {
        String pkId = Util.uuid();
        String ftpUsername = contractor.getName();
        String ftpPassword = contractor.getFtpPassword();
        contractor.setPkId(pkId);
        //contractor.setFtpPassword(MD5Util.MD5_16(ftpPassword));
        if (contractorMapper.insert(pkId, contractor)) {
            ftpUserManagerService.addProducerFtpUser(ftpUsername, ftpPassword);
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
    public boolean update(Contractor modelInfo) {
        boolean result = false;
        String ftpUserName = modelInfo.getName();
        String ftpPassword = modelInfo.getFtpPassword();
        //ftp登陆密码在进行存储前MD5
        //modelInfo.setFtpPassword(MD5Util.MD5_16(ftpPassword));
        if(contractorMapper.update(modelInfo.getPkId(), modelInfo)){
            ftpUserManagerService.delProducerFtpUser(ftpUserName);
            ftpUserManagerService.addProducerFtpUser(ftpUserName, ftpPassword);
            result = true;
        }

        return result;
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        Contractor contractor = contractorMapper.select(pkId);
        ftpUserManagerService.delProducerFtpUser(contractor.getName());
        return contractorMapper.delete(pkId);
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public Contractor select(String pkId) {
        return contractorMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param contractorView 查询参数
     * @param page           分页参数
     * @return
     */
    public List<ContractorView> select(ContractorView contractorView, Page page) {
        return contractorMapper.selectPage(contractorView, page);
    }

    /**
     * 检查名称是否唯一
     *
     * @param name 厂商名称
     * @return 存在返回true，不存在返回false
     */
    public boolean checekUniqueName(String name) {
        Contractor contractor = this.contractorMapper.selectByName(name);
        return contractor != null;
    }

    /**
     * 批量删除
     *
     * @param contractors
     * @return
     */
    @Transactional
    public MessageResult delete(Contractor[] contractors) {
        boolean flag = true;
        StringBuffer message = new StringBuffer("");
        for (Contractor contractor : contractors) {
            String pkId = contractor.getPkId();
            //检查是否存在下游依赖
            List<ModelInfo> modelInfos = modelInfoService.selectByContractorId(pkId);
            //存在下游依赖
            if (modelInfos != null && modelInfos.size() > 0) {
                flag = false;
                message.append(contractor.getCnName()) .append("、");
                continue;
            }
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        if (!flag) {
            message.deleteCharAt(message.length()-1).append("，").append("存在依赖关系，删除失败！");
        }
        return new MessageResult(flag, message.toString(),null);
    }

    /**
     * 查询所有有效的厂商
     *
     * @return
     */
    public List<Contractor> selectAll() {
        return contractorMapper.selectAll();
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.mm.model.Contractor");

            dataSourceContent.setType("unFixed");
            //添加表格解析内容
            dataSourceContent.setExcelProperties(ComExcelEnums.MmContractor.getAllNums());

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            sheet = hfb.getSheetAt(0);

            Map<String,List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
            List<Contractor> contractorList = (List<Contractor>)uploadExcelModel.get("com.hex.bigdata.udsp.mm.model.Contractor");
            String inseResult;
            int i = 1;
            for (Contractor contractor : contractorList){

                if(contractorMapper.selectByName(contractor.getName()) != null){
                    resultMap.put("status","false");
                    resultMap.put("message","第" + i +"个名称已存在！");
                    break;
                }
                inseResult = insert(contractor);
                if(inseResult != null){
                    resultMap.put("status","true");
                }else{
                    resultMap.put("status","false");
                    resultMap.put("message","第" + i +"保存失败！");
                    break;
                }
                i ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status","false");
            resultMap.put("message","程序内部异常："+ e.getMessage());
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

    public String createExcel(Contractor[] contractors) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFSheet sheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_mmcontractor.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator+ "download_mmcontractor_excel_"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xls";
        // 获取模板文件第一个Sheet对象
        POIFSFileSystem sourceFile = null;

        try {
            sourceFile = new POIFSFileSystem(new FileInputStream(
                    templateFile));

            sourceWork = new HSSFWorkbook(sourceFile);
            sourceSheet = sourceWork.getSheetAt(0);
            //创建表格
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet();
            ExcelCopyUtils.copyRow(sheet.createRow(0), sourceSheet.getRow(0), sheet.createDrawingPatriarch(), workbook);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = 1;
        for(Contractor contractor : contractors){
            //设置内容
            Contractor mmcontractor = contractorMapper.select(contractor.getPkId());
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(mmcontractor.getCnName());
            cell = row.createCell(2);
            cell.setCellValue(mmcontractor.getName());
            cell = row.createCell(3);
            cell.setCellValue(mmcontractor.getHttpUrl());
            cell = row.createCell(4);
            cell.setCellValue(mmcontractor.getPrincipal());
            cell = row.createCell(5);
            cell.setCellValue(mmcontractor.getMobile());
            cell = row.createCell(6);
            cell.setCellValue(mmcontractor.getFtpPassword());
            cell = row.createCell(7);
            cell.setCellValue(mmcontractor.getNote());
            i++;
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
