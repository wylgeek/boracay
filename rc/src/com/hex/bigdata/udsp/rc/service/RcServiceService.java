package com.hex.bigdata.udsp.rc.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.constant.CommonConstant;
import com.hex.bigdata.udsp.common.constant.DatasourceType;
import com.hex.bigdata.udsp.common.dto.ComDatasourceView;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.service.ComDatasourceService;
import com.hex.bigdata.udsp.common.util.CreateFileUtil;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.iq.dto.IqApplicationView;
import com.hex.bigdata.udsp.iq.service.IqApplicationService;
import com.hex.bigdata.udsp.mm.dao.MmApplicationMapper;
import com.hex.bigdata.udsp.mm.service.MmApplicationService;
import com.hex.bigdata.udsp.olq.service.OLQApplicationService;
import com.hex.bigdata.udsp.rc.dao.RcServiceForAppTypeAndAppIdMapper;
import com.hex.bigdata.udsp.rc.dao.RcServiceForServiceNameMapper;
import com.hex.bigdata.udsp.rc.dao.RcServiceMapper;
import com.hex.bigdata.udsp.rc.dto.RcServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.bigdata.udsp.rc.util.RcConstant;
import com.hex.bigdata.udsp.rts.dao.RtsConsumerMapper;
import com.hex.bigdata.udsp.rts.dao.RtsProducerMapper;
import com.hex.bigdata.udsp.rts.dto.RtsConsumerView;
import com.hex.bigdata.udsp.rts.dto.RtsProducerView;
import com.hex.bigdata.udsp.rts.service.RtsConsumerService;
import com.hex.bigdata.udsp.rts.service.RtsProducerService;
import com.hex.goframe.model.MessageResult;
import com.hex.goframe.model.Page;
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
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:15:11
 */
@Service
public class RcServiceService {
    @Autowired
    private RcServiceMapper rcServiceMapper;
    @Autowired
    private RtsProducerService rtsProducerService;
    @Autowired
    private RtsConsumerService rtsConsumerService;
    @Autowired
    private ComDatasourceService comDatasourceService;
    @Autowired
    private IqApplicationService iqApplicationService;
    @Autowired
    private RcServiceForServiceNameMapper rcServiceForServiceNameMapper;
    @Autowired
    private RcServiceForAppTypeAndAppIdMapper rcServiceForAppTypeAndAppIdMapper;

    @Autowired
    private MmApplicationService mmApplicationService;
    @Autowired
    private MmApplicationMapper mmApplicationMapper;
    @Autowired
    private RtsProducerMapper rtsProducerMapper;
    @Autowired
    private RtsConsumerMapper rtsConsumerMapper;
    @Autowired
    private OLQApplicationService olqApplicationService;


    /**
     * 服务授权
     */
    @Autowired
    private RcUserServiceService rcUserServiceService;

    @Transactional
    public String insert(RcService rcService) {
        String pkId = Util.uuid();
        rcService.setPkId(pkId);
        if (rcServiceMapper.insert(pkId, rcService)) {
            /*
            同时按照不同ID保存到内存中
             */
            String id1 = rcService.getName();
            rcServiceForServiceNameMapper.insert(id1, rcService);
            String id2 = rcService.getType() + "|" + rcService.getAppId();
            rcServiceForAppTypeAndAppIdMapper.insert(id2, rcService);

            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param rcService
     * @return
     */
    @Transactional
    public boolean update(RcService rcService) {
        if (rcServiceMapper.update(rcService.getPkId(), rcService)) {
            /*
            同时按照不同ID在内存中更新
             */
            String id1 = rcService.getName();
            rcServiceForServiceNameMapper.update(id1, rcService);
            String id2 = rcService.getType() + "|" + rcService.getAppId();
            rcServiceForAppTypeAndAppIdMapper.update(id2, rcService);

            return true;
        }
        return false;
    }

    /**
     * 根据主键进行删除
     *
     * @param pkId
     * @return
     */
    @Transactional
    public boolean delete(String pkId) {
        RcService rcService = select(pkId);
        if (rcServiceMapper.delete(pkId)) {
            /*
            同时按照不同ID在内存中删除
             */
            String id1 = rcService.getName();
            rcServiceForServiceNameMapper.delete(id1);
            String id2 = rcService.getType() + "|" + rcService.getAppId();
            rcServiceForAppTypeAndAppIdMapper.delete(id2);

            return true;
        }
        return false;
    }

    /**
     * 根据主键进行查询
     *
     * @param pkId
     * @return
     */
    public RcService select(String pkId) {
        return rcServiceMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMatedataView 查询参数
     * @param page            分页参数
     * @return
     */
    public List<RcServiceView> select(RcServiceView rtsMatedataView, Page page) {
        return rcServiceMapper.selectPage(rtsMatedataView, page);
    }

    /**
     * 检查名称是否唯一
     *
     * @param name 服务名称
     * @return 存在返回true，不存在返回false
     */
    public boolean checekUniqueName(String name) {
        RcService rtsMatedata = this.rcServiceMapper.selectByName(name);
        return rtsMatedata != null;
    }

    /**
     * 通过名获取服务信息
     *
     * @param name
     * @return
     */
    public RcService selectByName(String name) {
        return this.rcServiceMapper.selectByName(name);
    }

    /**
     * 通过服务名获取服务信息（查询出启用状态的服务）
     * <p/>
     * （内存中有则从内存获取）
     *
     * @param name
     * @return
     */
    public RcService selectByServiceName(String name) {
        return this.rcServiceForServiceNameMapper.select(name);
    }

    /**
     * 通过应用类型和应用ID获取服务信息
     * <p/>
     * （内存中有则从内存获取）
     *
     * @return
     */
    public RcService selectByAppTypeAndAppId(String appType, String appId) {
        String id = appType + "|" + appId;
        return this.rcServiceForAppTypeAndAppIdMapper.select(id);
    }

    /**
     * 批量删除
     *
     * @param rcServices
     * @return
     */
    @Transactional
    public boolean delete(RcService[] rcServices) {
        boolean flag = true;
        for (RcService rcService : rcServices) {
            String pkId = rcService.getPkId();
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
            //批量删除服务注册数据时，同时删除服务授权数据(逻辑删除)
            rcUserServiceService.deleteServiceId(pkId);
        }
        return flag;
    }

    /**
     * 根据类型查询应用
     *
     * @param type
     * @return
     */

    public List selectApps(String type) {
        List searchList = new ArrayList();
        if (RcConstant.UDSP_SERVICE_TYPE_IQ.equals(type)) {
            searchList = this.iqApplicationService.select(new IqApplicationView());
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equals(type)) {
            ComDatasourceView datasourceView = new ComDatasourceView();
            datasourceView.setModel(DatasourceType.OLQ.getValue());
            searchList = comDatasourceService.select(datasourceView);
        } else if (RcConstant.UDSP_SERVICE_TYPE_MM.equals(type)) {
            searchList = mmApplicationService.selectAll();
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_PRODUCER.equals(type)) {
            searchList = this.rtsProducerService.select(new RtsProducerView());
        } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_CONSUMER.equals(type)) {
            searchList = this.rtsConsumerService.select(new RtsConsumerView());
        } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ_APP.equals(type)) {
            searchList = this.olqApplicationService.selectAll();
        } else {
            searchList = null;
        }
        return searchList;
    }

    /**
     * 根据服务类型查询服务
     *
     * @param serviceType 服务类型
     * @return
     */
    public List selectByType(String serviceType) {
        return this.rcServiceMapper.selectByType(serviceType);
    }

    /**
     * 根据服务类型查询服务名称
     *
     * @return
     */
    public RcService selectAuthInfo(String pkId) {
        return rcServiceMapper.selectAuthInfo(pkId);
    }

    /**
     * 根据服务名称ID查询是否有服务授权信息
     *
     * @return
     */
    public MessageResult selectRcUserService(RcService[] rcServices) {
        StringBuffer message = new StringBuffer("");
        boolean checkFlg = true;
        for (RcService rcService : rcServices) {
            List<RcUserService> rcUserServices = rcUserServiceService.selectRelationByServiceId(rcService.getPkId());
            if (rcUserServices != null && rcUserServices.size() > 0) {
                checkFlg = false;
                message.append(rcService.getName()).append(",");
            }
        }
        if (!checkFlg) {
            message.deleteCharAt(message.length() - 1).append("等服务有对应的服务授权信息，不予删除！");
        }
        return new MessageResult(checkFlg, message.toString());
    }

    /**
     * 根据应用名称和应用类型查找服务注册信息
     *
     * @param type
     * @param appId
     * @return
     */
    public boolean checkAppIdAndType(String type, String appId) {
        RcService rcService = this.rcServiceMapper.selectRcServiceByAppIdAndType(type, appId);
        return rcService != null;
    }

    public Map<String, String> checkApplicationsUsed(String model, Map<String, String>[] applications) {
        Map<String, String> returnMap = null;
        for (Map<String, String> application : applications) {
            if (checkAppIdAndType(model, application.get("pkId"))) {
                returnMap = new HashMap<>(2);
                returnMap.put("status", "true");
                if ("OLQ".equals(model)) {
                    returnMap.put("message", "名称为：" + application.get("name") + "数据源已被应用！");
                } else {
                    returnMap.put("message", "名称为：" + application.get("name") + "应用已被注册！");
                }
                break;
            }
        }
        return returnMap;
    }

    /**
     * 改变服务状态
     *
     * @param rcServices
     * @param status
     * @return
     */
    @Transactional
    public boolean statusChange(RcService[] rcServices, String status) {
        boolean flag = true;
        for (RcService item : rcServices) {
            item = this.select(item.getPkId());
            item.setStatus(status);
            boolean delFlg = this.rcServiceMapper.update(item.getPkId(),item);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        return flag;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.rc.model.RcService");

            dataSourceContent.setType("unFixed");
            //添加表格解析内容
            dataSourceContent.setExcelProperties(ComExcelEnums.RcService.getAllNums());

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            sheet = hfb.getSheetAt(0);

            Map<String, List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
            List<RcService> rcServices = (List<RcService>) uploadExcelModel.get("com.hex.bigdata.udsp.rc.model.RcService");
            String inseResult;
            String type;
            String serviceName;
            int i = 1;
            for (RcService rcService : rcServices) {
                if (rcServiceMapper.selectByName(rcService.getName()) != null) {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + i + "个名称重复！");
                    break;
                }
                //跟新应用id
                type = rcService.getType();
                serviceName = rcService.getAppId();
                if (RcConstant.UDSP_SERVICE_TYPE_IQ.equals(type)) {
                    if (this.iqApplicationService.selectByName(serviceName) == null) {
                        resultMap.put("status", "false");
                        resultMap.put("message", "第" + i + "个应用名称不存在！");
                        break;
                    }
                    rcService.setAppId(this.iqApplicationService.selectByName(serviceName).getPkId());
                } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equals(type)) {
                    if (comDatasourceService.selectByModelAndName("OLQ", serviceName) == null) {
                        resultMap.put("status", "false");
                        resultMap.put("message", "第" + i + "个应用名称不存在！");
                        break;
                    }
                    rcService.setAppId(comDatasourceService.selectByModelAndName("OLQ", serviceName).getPkId());
                } else if (RcConstant.UDSP_SERVICE_TYPE_MM.equals(type)) {
                    if (mmApplicationMapper.selectByName(serviceName) == null) {
                        resultMap.put("status", "false");
                        resultMap.put("message", "第" + i + "个应用名称不存在！");
                        break;
                    }
                    rcService.setAppId(mmApplicationMapper.selectByName(serviceName).getPkId());
                } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_PRODUCER.equals(type)) {
                    if (rtsProducerMapper.selectByName(serviceName) == null) {
                        resultMap.put("status", "false");
                        resultMap.put("message", "第" + i + "个应用名称不存在！");
                        break;
                    }
                    rcService.setAppId(rtsProducerMapper.selectByName(serviceName).getPkId());
                } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_CONSUMER.equals(type)) {
                    if (rtsConsumerMapper.selectByName(serviceName) == null) {
                        resultMap.put("status", "false");
                        resultMap.put("message", "第" + i + "个应用名称不存在！");
                        break;
                    }
                    rcService.setAppId(rtsConsumerMapper.selectByName(serviceName).getPkId());
                }
                //如果服务状态为空则
                if (StringUtils.isBlank(rcService.getStatus())) {
                    rcService.setStatus(CommonConstant.SERVICE_STATUS_ENABLED);
                } else if (CommonConstant.SERVICE_STATUS_ENABLED_TEXT.equals(rcService.getStatus())) {
                    rcService.setStatus(CommonConstant.SERVICE_STATUS_ENABLED);
                } else if (CommonConstant.SERVICE_STATUS_DISABLED_TEXT.equals(rcService.getStatus())) {
                    rcService.setStatus(CommonConstant.SERVICE_STATUS_DISABLED);
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + i + "个服务状态填写不正确！");
                    break;
                }

                inseResult = insert(rcService);
                if (inseResult != null) {
                    resultMap.put("status", "true");
                } else {
                    resultMap.put("status", "false");
                    resultMap.put("message", "第" + i + "个保存失败！");
                    break;
                }
                i++;
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

    public String createExcel(RcService[] rcServices) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFSheet sheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_rcService.xls";
        // 下载地址
        String dirPath = CreateFileUtil.getLocalDirPath();
        dirPath += seprator + "download_rcService_excel_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xls";
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
        String type;
        String serviceName;
        for (RcService rcService : rcServices) {
            //设置内容
            RcService rcServiceTemp = rcServiceMapper.select(rcService.getPkId());

            type = rcServiceTemp.getType();
            serviceName = rcServiceTemp.getAppId();
            if (RcConstant.UDSP_SERVICE_TYPE_IQ.equals(type)) {
                rcService.setAppId(this.iqApplicationService.select(serviceName).getName());
            } else if (RcConstant.UDSP_SERVICE_TYPE_OLQ.equals(type)) {
                rcService.setAppId(comDatasourceService.select(serviceName).getName());
            } else if (RcConstant.UDSP_SERVICE_TYPE_MM.equals(type)) {
                rcService.setAppId(mmApplicationMapper.select(serviceName).getName());
            } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_PRODUCER.equals(type)) {
                rcService.setAppId(rtsProducerMapper.select(serviceName).getName());
            } else if (RcConstant.UDSP_SERVICE_TYPE_RTS_CONSUMER.equals(type)) {
                rcService.setAppId(rtsConsumerMapper.select(serviceName).getName());
            }

            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(rcServiceTemp.getName());
            cell = row.createCell(2);
            cell.setCellValue(rcServiceTemp.getType());
            cell = row.createCell(3);
            cell.setCellValue(rcService.getAppId());
            cell = row.createCell(4);
            cell.setCellValue(rcServiceTemp.getDescribe());
            cell = row.createCell(5);
            if (CommonConstant.SERVICE_STATUS_ENABLED.equals(rcServiceTemp.getStatus())) {
                cell.setCellValue(CommonConstant.SERVICE_STATUS_ENABLED_TEXT);
            } else {
                cell.setCellValue(CommonConstant.SERVICE_STATUS_DISABLED_TEXT);
            }
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
