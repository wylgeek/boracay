package com.hex.bigdata.udsp.rts.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComExcelParam;
import com.hex.bigdata.udsp.common.model.ComExcelProperties;
import com.hex.bigdata.udsp.common.model.ComProperties;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.service.ComPropertiesService;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.common.util.ExceptionUtil;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.dto.ServiceBaseInfo;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.service.RcServiceService;
import com.hex.bigdata.udsp.rts.dao.RtsConsumerMapper;
import com.hex.bigdata.udsp.rts.dao.RtsMatedataMapper;
import com.hex.bigdata.udsp.rts.dto.RTSIndexDto;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerProsView;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerView;
import com.hex.bigdata.udsp.rts.model.RtsConsumer;
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
 * Created by tomnic on 2017/3/1.
 */
@Service
public class RtsConsumerService extends BaseService {
    /**
     * 日志记录
     */
    private static Logger logger = LogManager.getLogger(RtsConsumerService.class);


    /**
     * 公共配置参数服务
     */
    @Autowired
    private ComPropertiesService comPropertiesService;

    /**
     * 实时流数据源DAO层服务
     */
    @Autowired
    private RtsConsumerMapper rtsConsumerMapper;

    /**
     * 实时流-元数据列服务
     */
    @Autowired
    private RtsMatedataColService rtsMatedataColService;


    @Autowired
    private RtsMatedataMapper rtsMatedataMapper;

    @Autowired
    private RcServiceService rcServiceService;

    private static List<ComExcelParam> comExcelParams;

    static {
        comExcelParams = new ArrayList<>();
        comExcelParams.add(new ComExcelParam(1, 1, "name"));
        comExcelParams.add(new ComExcelParam(1, 3, "mdId"));
        comExcelParams.add(new ComExcelParam(2, 1, "groupId"));
        comExcelParams.add(new ComExcelParam(2, 3, "describe"));
        comExcelParams.add(new ComExcelParam(3, 1, "note"));
    }


    @Transactional
    public String insert(RtsConsumer rtsConsumer) {
        String pkId = Util.uuid();
        rtsConsumer.setPkId(pkId);
        if (rtsConsumerMapper.insert(pkId, rtsConsumer)) {
            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param rtsConsumer
     * @return
     */
    @Transactional
    public boolean update(RtsConsumer rtsConsumer) {
        return rtsConsumerMapper.update(rtsConsumer.getPkId(), rtsConsumer);
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        return rtsConsumerMapper.delete(pkId);
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public RtsConsumer select(String pkId) {
        return rtsConsumerMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param rtsConsumerView 查询参数
     * @param page            分页参数
     * @return
     */

    public List<RtsConsumerView> select(RtsConsumerView rtsConsumerView, Page page) {
        return rtsConsumerMapper.selectPage(rtsConsumerView, page);
    }

    /**
     * 根据查询条件查询结果list，不分页
     *
     * @param rtsConsumerView 查询参数
     * @return
     */
    public List<RtsConsumer> select(RtsConsumerView rtsConsumerView) {
        return rtsConsumerMapper.select(rtsConsumerView);
    }

    /**
     * 新增实时流数据源实体
     *
     * @param rtsConsumerProsView 实时流-生产者及配置参数
     * @return
     */
    @Transactional
    public String insert(RtsConsumerProsView rtsConsumerProsView) {
        RtsConsumer rtsDatasource = rtsConsumerProsView.getRtsConsumer();
        String pkId = this.insert(rtsDatasource);
        if (StringUtils.isNotBlank(pkId)) {
            List<ComProperties> comPropertiesList = rtsConsumerProsView.getComPropertiesList();
            comPropertiesService.insertList(pkId, comPropertiesList);
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
        RtsConsumer rtsConsumer = this.rtsConsumerMapper.selectByName(name);
        return rtsConsumer != null;
    }

    /**
     * 更新数据源基础信息以及配置参数信息
     *
     * @param rtsConsumerProsView
     * @return
     */
    @Transactional
    public boolean update(RtsConsumerProsView rtsConsumerProsView) {
        RtsConsumer rtsConsumer = rtsConsumerProsView.getRtsConsumer();
        String pkId = rtsConsumer.getPkId();
        //更新基础信息
        boolean updateFlg = this.rtsConsumerMapper.update(pkId, rtsConsumer);
        //删除旧的的配置参数信息
        boolean delFlg = comPropertiesService.deleteByFkId(pkId);
        if (delFlg) {
            //插入新的配置参数信息
            List<ComProperties> comPropertiesList = rtsConsumerProsView.getComPropertiesList();
            comPropertiesService.insertList(pkId, comPropertiesList);
        }
        return true;
    }

    /**
     * 批量删除
     *
     * @param rtsConsumers
     * @return
     */
    @Transactional
    public boolean delete(RtsConsumer[] rtsConsumers) {
        boolean flag = true;
        for (RtsConsumer rtsDatasource : rtsConsumers) {
            String pkId = rtsDatasource.getPkId();
            boolean delFlg = delete(pkId);
            //删除旧的的配置参数信息
            //boolean comProDelflg = comPropertiesService.deleteByFkId(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public List<RtsMatedataCol> selectConsumerColumns(String consumerId) {
        RtsConsumer rtsProducer = this.select(consumerId);
        List<RtsMatedataCol> colList = null;
        if (rtsProducer != null) {
            colList = this.rtsMatedataColService.selectByMdId(rtsProducer.getMdId());
        }
        return colList;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.rts.model.RtsConsumer");

            dataSourceContent.setComExcelParams(comExcelParams);
            List<ComExcelProperties> comExcelPropertiesList = new ArrayList<>();
            //添加对应的配置栏内容
            comExcelPropertiesList.add(new ComExcelProperties("数据源配置", "com.hex.bigdata.udsp.common.model.ComProperties", 10, 0, 1, ComExcelEnums.Comproperties.getAllNums()));

            dataSourceContent.setComExcelPropertiesList(comExcelPropertiesList);
            dataSourceContent.setType("fixed");

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            for (int i = 0, activeIndex = hfb.getNumberOfSheets(); i < activeIndex; i++) {
                sheet = hfb.getSheetAt(i);
                Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
                List<RtsConsumer> rtsConsumers = (List<RtsConsumer>) uploadExcelModel.get("com.hex.bigdata.udsp.rts.model.RtsConsumer");
                RtsConsumer rtsConsume = rtsConsumers.get(0);
                if (rtsConsumerMapper.selectByName(rtsConsume.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个名称重复！");
                    break;
                }
                if (rtsMatedataMapper.selectByName(rtsConsume.getMdId()) == null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + (i + 1) + "个对应元数据不存在！");
                    break;
                }
                //更新元数据
                rtsConsume.setMdId(rtsMatedataMapper.selectByName(rtsConsume.getMdId()).getPkId());
                String pkId = insert(rtsConsume);
                List<ComProperties> comPropertiesList = (List<ComProperties>) uploadExcelModel.get("com.hex.bigdata.udsp.common.model.ComProperties");
                boolean insert = comPropertiesService.insertList(pkId, comPropertiesList);
                if (insert) {
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

    public String createExcel(RtsConsumer[] rtsConsumers) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_rtsComsumer.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_rtsComsumer_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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

        for (RtsConsumer rtsConsumer : rtsConsumers) {
            this.setWorkbookSheet(workbook, sourceSheet, comExcelParams, rtsConsumer);
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
            sourceSheet = sourceWork.getSheetAt(5);
            //创建表格
        } catch (IOException e) {
            e.printStackTrace();
        }
        RcService rcService = null;
        if (StringUtils.isNotBlank(rcUserService.getServiceId())) {
            rcService = rcServiceService.select(rcUserService.getServiceId());
        }

        RtsConsumer rtsConsumer = null;
        if (null != rcService) {
            rtsConsumer = this.select(rcService.getAppId());
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

        ServiceBaseInfo serviceBaseInfo = new ServiceBaseInfo(rcUserService, maxSize, "");

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

        for (ComExcelParam comExcelParam : comExcelParams) {
            try {
                Field field = serviceBaseInfo.getClass().getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(serviceBaseInfo) == null ? "" : field.get(serviceBaseInfo).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        RTSIndexDto rtsIndexDto = new RTSIndexDto(i);
        this.setWorkbookSheetPart(sheet, rtsConsumer, sourceSheet, workbook, rtsIndexDto);
    }

    /**
     * 设置信息到workbook
     *
     * @param workbook
     * @param sourceSheet
     * @param comExcelParams
     * @param rtsConsumer
     */
    public void setWorkbookSheet(HSSFWorkbook workbook, HSSFSheet sourceSheet, List<ComExcelParam> comExcelParams, RtsConsumer rtsConsumer) {

        HSSFSheet sheet;
        sheet = workbook.createSheet();

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
        RtsConsumer rtsConsumer1 = rtsConsumerMapper.select(rtsConsumer.getPkId());
        //设置元数据名字
        rtsConsumer1.setMdId(rtsMatedataMapper.select(rtsConsumer1.getMdId()).getName());
        for (ComExcelParam comExcelParam : comExcelParams) {
            try {
                Field field = rtsConsumer1.getClass().getDeclaredField(comExcelParam.getName());
                field.setAccessible(true);
                ExcelCopyUtils.setCellValue(sheet, comExcelParam.getRowNum(), comExcelParam.getCellNum(), field.get(rtsConsumer1) == null ? "" : field.get(rtsConsumer1).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.setWorkbookSheetPart(sheet, rtsConsumer, sourceSheet, workbook, new RTSIndexDto(i));
    }

    public void setWorkbookSheetPart(HSSFSheet sheet, RtsConsumer rtsConsumer, HSSFSheet sourceSheet, HSSFWorkbook workbook, RTSIndexDto rtsIndexDto) {
        HSSFRow row;
        HSSFCell cell;
        int rowIndex = rtsIndexDto.getRowIndex();
        List<RtsMatedataCol> rtsMatedataCols = rtsMatedataColService.selectByMdId(rtsConsumer.getMdId());
        if (rtsMatedataCols.size() > 0) {
            int k = 1;
            for (RtsMatedataCol rtsMatedataCol : rtsMatedataCols) {
                row = sheet.createRow(rowIndex);
                cell = row.createCell(0);
                cell.setCellValue(k);
                cell = row.createCell(1);
                cell.setCellValue(rtsMatedataCol.getName());
                cell = row.createCell(2);
                cell.setCellValue(rtsMatedataCol.getType());
                cell = row.createCell(3);
                cell.setCellValue(rtsMatedataCol.getDescribe());
                rowIndex++;
                k++;
            }
        }
    }
}
