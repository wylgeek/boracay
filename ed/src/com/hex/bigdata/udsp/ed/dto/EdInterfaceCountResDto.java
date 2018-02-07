package com.hex.bigdata.udsp.ed.dto;

import com.hex.bigdata.udsp.ed.model.EdApplication;
import com.hex.bigdata.udsp.rc.model.RcService;

/**
 * Created by wb_dsjpt_05 on 2018/1/29.
 */
public class EdInterfaceCountResDto extends RcService {

    private int reqTotalCount;    //请求次数

    private int reqSuccessCount;    //请求成功次数

    private int reqFailedCount; //请求失败次数

    private int remoteCount; //远程访问次数

    private int cacheCount;  //缓存次数

    public int getReqTotalCount() {
        return reqTotalCount;
    }

    public void setReqTotalCount(int reqTotalCount) {
        this.reqTotalCount = reqTotalCount;
    }

    public int getReqSuccessCount() {
        return reqSuccessCount;
    }

    public void setReqSuccessCount(int reqSuccessCount) {
        this.reqSuccessCount = reqSuccessCount;
    }

    public int getReqFailedCount() {
        return reqFailedCount;
    }

    public void setReqFailedCount(int reqFailedCount) {
        this.reqFailedCount = reqFailedCount;
    }

    public int getRemoteCount() {
        return remoteCount;
    }

    public void setRemoteCount(int remoteCount) {
        this.remoteCount = remoteCount;
    }

    public int getCacheCount() {
        return cacheCount;
    }

    public void setCacheCount(int cacheCount) {
        this.cacheCount = cacheCount;
    }
}
