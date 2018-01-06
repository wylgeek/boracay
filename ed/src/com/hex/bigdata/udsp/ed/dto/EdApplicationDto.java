package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.EdApplication;

/**
 * Created by jc.zhao
 * Date:2018/1/6
 * Time:21:51
 */
public class EdApplicationDto extends EdApplication {
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

    private String interfaceCode;

    private String interfaceName;

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

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}
