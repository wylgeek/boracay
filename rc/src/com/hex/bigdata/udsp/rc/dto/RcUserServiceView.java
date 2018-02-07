package com.hex.bigdata.udsp.rc.dto;

import com.hex.bigdata.udsp.rc.model.RcUserService;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/3/8
 * TIME:14:37
 */
public class RcUserServiceView extends RcUserService {
    /**
     * 创建时间查询时间范围-开始时间
     */
    private String crtTimeStart;
    /**
     * 创建时间查询时间范围-结束时间
     */
    private String crtTimeEnd;
    /**
     * 更新时间查询时间范围-开始时间
     */
    private String uptTimeStart;
    /**
     * 更新时间查询时间范围-结束时间
     */
    private String uptTimeEnd;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务说明
     */
    private String serviceDescribe;
    /**
     *服务类型
     */
    private String serviceType;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户名Id集合
     */
    private String userIds;

    /**
     * 服务集合
     */
    private String serviceIds;

    /**
     * 服务集合
     */
    private String[] serviceArray;


    public String getCrtTimeStart() {
        return crtTimeStart;
    }

    public void setCrtTimeStart(String crtTimeStart) {
        this.crtTimeStart = crtTimeStart;
    }

    public String getCrtTimeEnd() {
        return crtTimeEnd;
    }

    public void setCrtTimeEnd(String crtTimeEnd) {
        this.crtTimeEnd = crtTimeEnd;
    }

    public String getUptTimeStart() {
        return uptTimeStart;
    }

    public void setUptTimeStart(String uptTimeStart) {
        this.uptTimeStart = uptTimeStart;
    }

    public String getUptTimeEnd() {
        return uptTimeEnd;
    }

    public void setUptTimeEnd(String uptTimeEnd) {
        this.uptTimeEnd = uptTimeEnd;
    }
    
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String[] getServiceArray() {
        if (StringUtils.isBlank(serviceIds)){
            return null;
        }
        serviceArray=this.getServiceIds().split(",");
        return serviceArray;
    }

    public void setServiceArray(String[] serviceArray) {
        this.serviceArray = serviceArray;
    }

    public String getServiceDescribe() {
        return serviceDescribe;
    }

    public void setServiceDescribe(String serviceDescribe) {
        this.serviceDescribe = serviceDescribe;
    }
}
