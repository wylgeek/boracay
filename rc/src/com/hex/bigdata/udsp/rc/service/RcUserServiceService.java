package com.hex.bigdata.udsp.rc.service;

import com.hex.bigdata.udsp.common.constant.ComExcelEnums;
import com.hex.bigdata.udsp.common.model.ComUploadExcelContent;
import com.hex.bigdata.udsp.common.util.ExcelCopyUtils;
import com.hex.bigdata.udsp.common.util.ExcelUploadhelper;
import com.hex.bigdata.udsp.rc.dao.RcUserServiceForUserIdAndServiceIdMapper;
import com.hex.bigdata.udsp.rc.dao.RcUserServiceMapper;
import com.hex.bigdata.udsp.rc.dto.IpSectionHelper;
import com.hex.bigdata.udsp.rc.dto.RcUserServiceView;
import com.hex.bigdata.udsp.rc.model.RcService;
import com.hex.bigdata.udsp.rc.model.RcUserService;
import com.hex.goframe.model.GFUser;
import com.hex.goframe.model.Page;
import com.hex.goframe.service.BaseService;
import com.hex.goframe.util.DateUtil;
import com.hex.goframe.util.FileUtil;
import com.hex.goframe.util.Util;
import org.apache.commons.lang3.StringUtils;
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
public class RcUserServiceService extends BaseService {
    /**
     * 实时流-元数据Dao层服务
     */
    @Autowired
    private RcUserServiceMapper rcUserServiceMapper;

    @Autowired
    private RcServiceService rcServiceService;

    @Autowired
    private RcUserServiceForUserIdAndServiceIdMapper rcUserServiceForUserIdAndServiceIdMapper;


    @Transactional
    public String insert(RcUserService rcUserService) {
        String pkId = Util.uuid();
        rcUserService.setPkId(pkId);

        if (checkBeforeInsetOrUpdate(rcUserService) && rcUserServiceMapper.insert(pkId, rcUserService)) {
            /*
            同时按照不同ID保存到内存中
             */
            String id = rcUserService.getUserId() + "|" + rcUserService.getServiceId();
            rcUserServiceForUserIdAndServiceIdMapper.insert(id, rcUserService);

            return pkId;
        }
        return "";
    }

    /**
     * 根据主键更新对象实体
     *
     * @param rcUserService
     * @return
     */
    @Transactional
    public boolean update(RcUserService rcUserService) {
        if (checkBeforeInsetOrUpdate(rcUserService) && rcUserServiceMapper.update(rcUserService.getPkId(), rcUserService)) {
            /*
            同时按照不同ID在内存中更新
             */
            String id = rcUserService.getUserId() + "|" + rcUserService.getServiceId();
            rcUserServiceForUserIdAndServiceIdMapper.update(id, rcUserService);
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
        RcUserService rcUserService = select(pkId);
        if (rcUserServiceMapper.delete(pkId)) {
            /*
            同时按照不同ID在内存中删除
             */
            String id = rcUserService.getUserId() + "|" + rcUserService.getServiceId();
            rcUserServiceForUserIdAndServiceIdMapper.delete(id);

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
    public RcUserService select(String pkId) {
        return rcUserServiceMapper.select(pkId);
    }

    /**
     * 分页多条件查询
     *
     * @param rtsMatedataView 查询参数
     * @param page            分页参数
     * @return
     */
    public List<RcUserServiceView> select(RcUserServiceView rtsMatedataView, Page page) {
        return rcUserServiceMapper.selectPage(rtsMatedataView, page);
    }

    /**
     * 批量删除
     *
     * @param rcServices
     * @return
     */
    @Transactional
    public boolean delete(RcUserService[] rcServices) {
        boolean flag = true;
        for (RcUserService rcService : rcServices) {
            String pkId = rcService.getPkId();
            boolean delFlg = delete(pkId);
            if (!delFlg) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 批量新增
     *
     * @param view
     * @return
     */
    public boolean insertBatch(RcUserServiceView view) {
        String userIds = view.getUserIds();
        String serviceIds = view.getServiceIds();
        String[] serviceIdArray = serviceIds.split(",");
        String[] userIdArray = userIds.split(",");

        //插入前检查
        if(!checkBeforeBatchInset(view)){
            return false;
        }
        //批量循环插入
        for (String serviceId : serviceIdArray) {
            for (String userId : userIdArray) {
                RcUserService temp = new RcUserService();
                temp.setServiceId(serviceId);
                temp.setIpSection(view.getIpSection());
                temp.setMaxSyncNum(view.getMaxSyncNum());
                temp.setMaxAsyncNum(view.getMaxSyncNum());
                temp.setUserId(userId);
                this.insert(temp);
            }
        }
        return true;
    }


    /**
     * 批量检查用户和服务关系是否存在
     *
     * @param userIds
     * @param serviceIds
     * @return
     */
    public boolean checkExistsBatch(String userIds, String serviceIds) {
        String[] userIdArray = userIds.split(",");
        String[] serviceArray = serviceIds.split(",");
        for (String serviceId : serviceArray) {
            for (String userId : userIdArray) {
                if (checkExists(userId, serviceId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查用户Id和服务Id关联关系是否存在
     *
     * @param userId
     * @param serviceId
     * @return
     */
    public boolean checkExists(String userId, String serviceId) {
        List<RcUserService> rcUserServices = this.rcUserServiceMapper.selectRelation(userId, serviceId);
        return rcUserServices.size() > 0;
    }

    /**
     * 查询详细的用户服务关系信息
     *
     * @param id
     * @return
     */
    public RcUserServiceView selectFullResultMap(String id) {
        return this.rcUserServiceMapper.selectFullResultMap(id);
    }

    /**
     * 根据用户id和服务id获取对应的关系信息
     *
     * @param userId
     * @param serviceId
     * @return
     */
    public RcUserService selectRelationByIds(String userId, String serviceId) {
        List<RcUserService> rcUserServices = this.rcUserServiceMapper.selectRelation(userId, serviceId);
        if (rcUserServices != null && rcUserServices.size() == 1) {
            return rcUserServices.get(0);
        }
        return null;
    }

    public RcUserService selectRelation(String userId, String serviceName) {
        String serviceId = "";
        RcService rcService = rcServiceService.selectByName(serviceName);
        if (rcService != null) {
            serviceId = rcService.getPkId();
        }

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(serviceId)) {
            return null;
        }
        List<RcUserService> rcUserServices = this.rcUserServiceMapper.selectRelation(userId, serviceId);
        if (rcUserServices != null && rcUserServices.size() == 1) {
            return rcUserServices.get(0);
        }
        return null;
    }

    /**
     * 通过条件查询用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @param page
     * @return
     */

    public List<GFUser> selectNotRelationUsers(RcUserServiceView rcUserServiceView, Page page) {
        List<GFUser> selectedUsers = this.rcUserServiceMapper.selectNotRelationUsers(rcUserServiceView, page);
        return selectedUsers;
    }

    /**
     * 通过条件查询用户信息
     * 服务Id、用户姓名、分页参数
     *
     * @param rcUserServiceView
     * @return
     */
    public List<GFUser> selectRelationUsers(RcUserServiceView rcUserServiceView) {
        List<GFUser> selectedUsers = this.rcUserServiceMapper.selectRelationUsers(rcUserServiceView);
        return selectedUsers;
    }


    /**
     * 通过userId和serviceId获取服务信息
     * <p/>
     * （内存中有则从内存获取）
     *
     * @return
     */
    public RcUserService selectByUserIdAndServiceId(String userId, String serviceId) {
        String id = userId + "|" + serviceId;
        return rcUserServiceForUserIdAndServiceIdMapper.select(id);
    }

    /**
     * 根据服务注册外键批量删除数据
     *
     * @param serviceId
     * @return
     */
    public boolean deleteServiceId(String serviceId) {
        return this.rcUserServiceMapper.deleteServiceId(serviceId);
    }

    /**
     * 根据服务注册外键查找对应授权服务
     *
     * @param serviceId
     * @return
     */
    public List selectRelationByServiceId(String serviceId) {
        return rcUserServiceMapper.selectRelationByServiceId(serviceId);
    }

    /**
     * 检查ip段表达式的合法性
     * 支持以下几种模式：
     * 1、正常的ip，如10.1.97.1
     * 2、以星号*代替0-255之间的任意数字，如10.1.97.*
     * 3、如10.1.97.[10-30]、10.1.97.[1-5,6-20]
     *
     * @param ipSections
     * @return
     */
    public boolean checkModels(String ipSections) {
        //逗号分隔
        String [] ipSectionArray=ipSections.split(",");
        boolean flg=true;
        for (String item:ipSectionArray){
            if (!this.checkModel(item)){
                flg=false;
                break;
            }
        }
        return  flg;
    }

    private boolean checkModel(String ipSection){
        String[] patternArray = ipSection.split("\\.");
        if (patternArray.length != 4) {
            return false;
        }
        boolean flg = true;
        for (String item : patternArray) {
            if (item.startsWith("[") && item.endsWith("]")) {
                if (this.isRightModelItem(item)) {
                    continue;
                }
                flg = false;
                break;
            } else if (item.equals("*")) {
                continue;
            } else if (this.isIpInt(item)) {
                continue;
            } else {
                flg = false;
                break;
            }
        }
        return flg;
    }


    /**
     * 检查区间表达式格式如[10-30]、[1-5,6-20]、[0,1-10]是否合法
     *
     * @return
     */
    private boolean isRightModelItem(String item) {
        item = item.substring(1, item.length() - 1);
        if (item.length() == 0) {
            return false;
        }
        String[] itemArray = item.split(",");
        if (itemArray.length == 0) {
            return false;
        }
        for (String subStr : itemArray) {
            String[] subArray = subStr.split("-");
            if (subArray.length > 2 || subArray.length < 1) {
                return false;
            } else if (subArray.length == 1&& this.isIpInt(subArray[0])){
                continue;
            }else if (subArray.length == 2 ){
                for (String ipIntStr : subArray) {
                    if (!this.isIpInt(ipIntStr)) {
                        return false;
                    }
                }
                //如果[7-5]，则不合法
                if (Integer.valueOf(subArray[0])>Integer.valueOf(subArray[1])){
                    return  false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数字是否属于IP整数
     *
     * @param number
     * @return
     */
    private boolean isIpInt(String number) {
        boolean isIpInt = true;
        Integer ipNumber = null;
        try {
            ipNumber = Integer.valueOf(number);
        } catch (Exception e) {
            isIpInt = false;
        }
        if (isIpInt && ipNumber != null && ipNumber >= 0 && ipNumber <= 255) {
            return isIpInt;
        }
        return false;
    }

    public static void main(String[] args) {

        RcUserServiceService rcService = new RcUserServiceService();
        System.out.println("==============================");
        System.out.println("checkModel start");
//        System.out.println(rcService.checkModel("*.*.*.*"));
//        System.out.println(rcService.checkModel("10.1.97.255"));
//        System.out.println(rcService.checkModel("10.1.97.255.0"));
//        System.out.println(rcService.checkModel("10.1.97.2589"));
//        System.out.println(rcService.checkModel("10.1.97.[1-8]"));
//        System.out.println(rcService.checkModel("10.1.97.[1-8],[9-12]"));
//        System.out.println(rcService.checkModel("*.*.*.[*,1-5]"));
        System.out.println(rcService.checkModel("*.*.*.[0,1-5]"));
        System.out.println(rcService.checkModel("*.*.*.[5]"));
        System.out.println("checkModel end");
        System.out.println("=================================");
        System.out.println(rcService.checkIpSuitForSection("10.1.1.1", "*.*.*.*"));
        System.out.println(rcService.checkIpSuitForSection("10.1.1.1", "*.*.*.[0,1-5]"));
        System.out.println(rcService.checkIpSuitForSection("10.1.97.1", "10.1.97.1"));
        System.out.println(rcService.checkIpSuitForSection("10.1.1.6", "*.*.*.[1-5,7-9]"));
        System.out.println(rcService.checkIpSuitForSection("10.1.1.6", "*.*.*.[5]"));
        System.out.println(rcService.checkIpSuitForSection("10.1.1.5", "*.*.*.[5]"));
    }

    /**
     * 检查ip地址与多个ip区间表达式是否匹配
     * @param ip
     * @param ipSections
     * @return
     */
    public boolean checkIpSuitForSections(String ip, String ipSections) {
        if (StringUtils.isBlank(ip)||StringUtils.isBlank(ipSections)){
            return false;
        }
        String[] ipSectionArray=ipSections.split(",");
        for (String item: ipSectionArray ){
            if (checkIpSuitForSection(ip,item)){
                return true;
            }
        }
        return false;
    }



    /**
     * 检查ip地址与单个ip区间表达式是否匹配
     * @param ip
     * @param ipSection
     * @return
     */
    private boolean checkIpSuitForSection(String ip, String ipSection) {
        String[] ipItems = ip.split("\\.");
        String[] ipSectionArray = ipSection.split("\\.");
        boolean flg = true;
        for (int i = 0; i < 4; i++) {
            if (ipSectionArray[i].startsWith("[") && ipSectionArray[i].endsWith("]")) {
                if (!this.isItemSuitForModel(ipItems[i], ipSectionArray[i])) {
                    flg = false;
                    break;
                }
            } else if (ipSectionArray[i].equals("*")) {
                continue;
            } else if (this.isIpInt(ipSectionArray[i])) {
                if (ipItems[i].equals(ipSectionArray[i])) {
                    continue;
                }
                flg = false;
                break;
            } else {
                flg = false;
                break;
            }
        }
        return flg;
    }

    /**
     * 检查区间表达式与ip对应的数字是否匹配
     * @param ipPart
     * @param sectionPart
     * @return
     */
    private boolean isItemSuitForModel(String ipPart, String sectionPart) {
        List<IpSectionHelper> sectionHelpers = this.getIpSectionHelpers(sectionPart);
        boolean suitFlg = false;
        int ipPartNum = Integer.valueOf(ipPart);
        for (IpSectionHelper sectionHelper : sectionHelpers) {
            if (IpSectionHelper.OPERATE_EQUALS.equals(sectionHelper.getOperate())) {
                if (sectionHelper.getEqualNum() == ipPartNum) {
                    suitFlg = true;
                    break;
                }
            } else {
                if (ipPartNum >= sectionHelper.getLowerNum() && ipPartNum <= sectionHelper.getHigerNum()) {
                    suitFlg = true;
                    break;
                }
            }

        }
        return suitFlg;
    }

    /**
     * 获取区间表达式对象
     * @param sectionPart
     * @return
     */
    private List<IpSectionHelper> getIpSectionHelpers(String sectionPart) {
        sectionPart = sectionPart.substring(1, sectionPart.length() - 1);
        if (sectionPart.length() == 0) {
            return null;
        }
        String[] itemArray = sectionPart.split(",");
        if (itemArray.length == 0) {
            return null;
        }
        List<IpSectionHelper> helpers = new ArrayList<>();
        for (String subStr : itemArray) {
            String[] subArray = subStr.split("-");
            if (subArray.length > 2 || subArray.length < 1) {
                return null;
            } else if (subArray.length == 1 && this.isIpInt(subArray[0])) {
                IpSectionHelper ipSectionHelper = new IpSectionHelper();
                ipSectionHelper.setOperate(IpSectionHelper.OPERATE_EQUALS);
                ipSectionHelper.setEqualNum(Integer.valueOf(subArray[0]));
                helpers.add(ipSectionHelper);
            } else if (subArray.length == 2 && this.isIpInt(subArray[0]) && this.isIpInt(subArray[1])) {
                IpSectionHelper ipSectionHelper = new IpSectionHelper();
                ipSectionHelper.setOperate(IpSectionHelper.OPERATE_SCOPE);
                ipSectionHelper.setLowerNum(Integer.valueOf(subArray[0]));
                ipSectionHelper.setHigerNum(Integer.valueOf(subArray[1]));
                helpers.add(ipSectionHelper);
            } else {
                return null;
            }
        }
        return helpers;
    }


    private boolean checkBeforeInsetOrUpdate(RcUserService rcService) {
        return rcService.getMaxAsyncNum() >= 0 && rcService.getMaxSyncNum() >= 0;
    }

    private boolean checkBeforeBatchInset(RcUserServiceView view) {
        return view.getMaxAsyncNum() >= 0 && view.getMaxSyncNum() >= 0;
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
            dataSourceContent.setClassName("com.hex.bigdata.udsp.rc.model.RcUserService");

            dataSourceContent.setType("unFixed");
            //添加表格解析内容
            dataSourceContent.setExcelProperties(ComExcelEnums.RcUserService.getAllNums());

            in = new FileInputStream(uploadFile);
            HSSFWorkbook hfb = new HSSFWorkbook(in);
            HSSFSheet sheet;
            sheet = hfb.getSheetAt(0);

            Map<String,List> uploadExcelModel = ExcelUploadhelper.getUploadExcelModel(sheet, dataSourceContent);
            List<RcUserService> rcServices = (List<RcUserService>)uploadExcelModel.get("com.hex.bigdata.udsp.rc.model.RcUserService");
            String inseResult;
            int i = 0;
            for (RcUserService rcService : rcServices){
                i++;
                if(rcServiceService.selectByName(rcService.getServiceId()) == null){
                    resultMap.put("status","false");
                    resultMap.put("message","第"+i+"个对应服务不存在！");
                    break;
                }
                rcService.setServiceId(rcServiceService.selectByName(rcService.getServiceId()).getPkId());
                inseResult = insert(rcService);
                if(inseResult != null){
                    resultMap.put("status","true");
                }else{
                    resultMap.put("status","false");
                    resultMap.put("message","上传失败！");
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

    public String createExcel(RcUserService[] rcServices) {
        HSSFWorkbook workbook = null;
        HSSFWorkbook sourceWork;
        HSSFSheet sourceSheet = null;
        HSSFSheet sheet = null;
        HSSFRow row;
        HSSFCell cell;
        String seprator = FileUtil.getFileSeparator();
        String dirPath = FileUtil.getWebPath("/");
        //模板文件位置
        String templateFile = ExcelCopyUtils.templatePath + seprator + "downLoadTemplate_rcAuther.xls";
        // 判断是否存在，不存在则创建
        dirPath += seprator + "TEMP_DOWNLOAD";
        File file = new File(dirPath);
        // 判断文件是否存在
        if (!file.exists()) {
            FileUtil.mkdir(dirPath);
        }
        dirPath += seprator+ "download_rcAuther_excel_"+ DateUtil.format(new Date(), "yyyyMMddHHmmss")+".xls";
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
        for(RcUserService rcService : rcServices){
            //设置内容
            RcUserService rcService1 = rcUserServiceMapper.select(rcService.getPkId());
            //将pkid替换成name
            rcService1.setServiceId(rcServiceService.select(rcService1.getServiceId()).getName());
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(i);
            cell = row.createCell(1);
            cell.setCellValue(rcService1.getServiceId());
            cell = row.createCell(2);
            cell.setCellValue(rcService1.getUserId());
            cell = row.createCell(3);
            cell.setCellValue(rcService1.getIpSection());
            cell = row.createCell(4);
            cell.setCellValue(rcService1.getMaxSyncNum());
            cell = row.createCell(5);
            cell.setCellValue(rcService1.getMaxAsyncNum());
            cell = row.createCell(6);
            cell.setCellValue(rcService1.getMaxSyncWaitNum());
            cell = row.createCell(7);
            cell.setCellValue(rcService1.getMaxAsyncWaitNum());
            cell = row.createCell(8);
            cell.setCellValue(rcService1.getMaxSyncWaitTimeout());
            cell = row.createCell(9);
            cell.setCellValue(rcService1.getMaxAsyncWaitTimeout());
            cell = row.createCell(10);
            cell.setCellValue(rcService1.getMaxSyncExecuteTimeout());
            cell = row.createCell(11);
            cell.setCellValue(rcService1.getMaxAsyncExecuteTimeout());
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

    /**
     * 根据服务名称获取对应的用户
     * @param rcUserServiceView
     * @return
     */
    public List<GFUser> selectUsersByServiceName(RcUserServiceView rcUserServiceView) {
        String serviceName = rcUserServiceView.getServiceName();
        if (StringUtils.isBlank(serviceName)){
            return null;
        }
        RcService rcService = rcServiceService.selectByName(serviceName);
        if (rcService == null ){
            return null;
        }
        rcUserServiceView.setServiceIds(rcService.getPkId());
        return this.selectRelationUsers(rcUserServiceView);
    }

    /**
     * 根据用户登录名获取对应的服务
     * @param rcUserServiceView
     * @return
     */
    public List<RcUserServiceView> selectServicesByUserId(RcUserServiceView rcUserServiceView) {
        String userName = rcUserServiceView.getUserId();
        if (StringUtils.isBlank(userName)){
            return null;
        }
        return this.rcUserServiceMapper.selectServicesByUserId(rcUserServiceView.getUserId());
    }
}
