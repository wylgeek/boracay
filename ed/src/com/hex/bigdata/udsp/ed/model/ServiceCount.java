package com.hex.bigdata.udsp.ed.model;

import java.util.Date;

/**
 * Created by jc.zhao
 * Date:2018/1/3
 * Time:11:32
 */
public class ServiceCount {
    private String pkId;
    private String serviceInfoId;
    private String serviceName;
    private String serviceCnName;
    private String serviceType;
    private String serviceCompany;
    private String requestUser;
    private Date requestTime;

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getServiceInfoId() {
        return serviceInfoId;
    }

    public void setServiceInfoId(String serviceInfoId) {
        this.serviceInfoId = serviceInfoId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCnName() {
        return serviceCnName;
    }

    public void setServiceCnName(String serviceCnName) {
        this.serviceCnName = serviceCnName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceCompany() {
        return serviceCompany;
    }

    public void setServiceCompany(String serviceCompany) {
        this.serviceCompany = serviceCompany;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}
