package com.hex.bigdata.udsp.im;


import com.hex.bigdata.udsp.common.util.JSONUtil;

import java.io.Serializable;

/**
 * 交易明细
 */
public class S01_FZRMX implements Serializable {
    private String TRAN_ID;//交易ID	"RTS_DQTQZQ"
    private String KMH;//科目号
    private String KHDH;//客户代号
    private String ZHKH;//账号卡号
    private String XH;//序号
    private String HSZH;//核算账号
    private String JGM;//机构码
    private String JYM;//交易码
    private String JYJE;//交易金额
    private String HBH;//货币号
    private String PZZL;//凭证种类
    private String PZHM;//凭证号码
    private String JDBZ;//借贷标志
    private String JYRQ;//交易日期	yyyymmdd
    private String JYSJ;//交易时间	hh24:mi:ss
    private String TSSJ;//推送时间	yyyymmdd hh24:mi:ss 核心往大数据平台推送的时间

    public S01_FZRMX() {
        super();
    }

    public S01_FZRMX(String TRAN_ID, String KMH, String KHDH, String ZHKH, String XH, String HSZH, String JGM, String JYM, String JYJE, String HBH, String PZZL, String PZHM, String JDBZ, String JYRQ, String JYSJ, String TSSJ) {
        this.TRAN_ID = TRAN_ID;
        this.KMH = KMH;
        this.KHDH = KHDH;
        this.ZHKH = ZHKH;
        this.XH = XH;
        this.HSZH = HSZH;
        this.JGM = JGM;
        this.JYM = JYM;
        this.JYJE = JYJE;
        this.HBH = HBH;
        this.PZZL = PZZL;
        this.PZHM = PZHM;
        this.JDBZ = JDBZ;
        this.JYRQ = JYRQ;
        this.JYSJ = JYSJ;
        this.TSSJ = TSSJ;
    }

    public String toJsonStr() {
        return JSONUtil.parseObj2JSON(this);
    }

    @Override
    public String toString() {
        return "JSCD_FZRMX{" +
                "TRAN_ID='" + TRAN_ID + '\'' +
                ", KMH='" + KMH + '\'' +
                ", KHDH='" + KHDH + '\'' +
                ", ZHKH='" + ZHKH + '\'' +
                ", XH='" + XH + '\'' +
                ", HSZH='" + HSZH + '\'' +
                ", JGM='" + JGM + '\'' +
                ", JYM='" + JYM + '\'' +
                ", JYJE='" + JYJE + '\'' +
                ", HBH='" + HBH + '\'' +
                ", PZZL='" + PZZL + '\'' +
                ", PZHM='" + PZHM + '\'' +
                ", JDBZ='" + JDBZ + '\'' +
                ", JYRQ='" + JYRQ + '\'' +
                ", JYSJ='" + JYSJ + '\'' +
                ", TSSJ='" + TSSJ + '\'' +
                '}';
    }

    public String getTRAN_ID() {
        return TRAN_ID;
    }

    public void setTRAN_ID(String TRAN_ID) {
        this.TRAN_ID = TRAN_ID;
    }

    public String getKMH() {
        return KMH;
    }

    public void setKMH(String KMH) {
        this.KMH = KMH;
    }

    public String getKHDH() {
        return KHDH;
    }

    public void setKHDH(String KHDH) {
        this.KHDH = KHDH;
    }

    public String getZHKH() {
        return ZHKH;
    }

    public void setZHKH(String ZHKH) {
        this.ZHKH = ZHKH;
    }

    public String getXH() {
        return XH;
    }

    public void setXH(String XH) {
        this.XH = XH;
    }

    public String getHSZH() {
        return HSZH;
    }

    public void setHSZH(String HSZH) {
        this.HSZH = HSZH;
    }

    public String getJGM() {
        return JGM;
    }

    public void setJGM(String JGM) {
        this.JGM = JGM;
    }

    public String getJYM() {
        return JYM;
    }

    public void setJYM(String JYM) {
        this.JYM = JYM;
    }

    public String getJYJE() {
        return JYJE;
    }

    public void setJYJE(String JYJE) {
        this.JYJE = JYJE;
    }

    public String getHBH() {
        return HBH;
    }

    public void setHBH(String HBH) {
        this.HBH = HBH;
    }

    public String getPZZL() {
        return PZZL;
    }

    public void setPZZL(String PZZL) {
        this.PZZL = PZZL;
    }

    public String getPZHM() {
        return PZHM;
    }

    public void setPZHM(String PZHM) {
        this.PZHM = PZHM;
    }

    public String getJDBZ() {
        return JDBZ;
    }

    public void setJDBZ(String JDBZ) {
        this.JDBZ = JDBZ;
    }

    public String getJYRQ() {
        return JYRQ;
    }

    public void setJYRQ(String JYRQ) {
        this.JYRQ = JYRQ;
    }

    public String getJYSJ() {
        return JYSJ;
    }

    public void setJYSJ(String JYSJ) {
        this.JYSJ = JYSJ;
    }

    public String getTSSJ() {
        return TSSJ;
    }

    public void setTSSJ(String TSSJ) {
        this.TSSJ = TSSJ;
    }
}
