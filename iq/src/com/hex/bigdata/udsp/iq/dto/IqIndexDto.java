package com.hex.bigdata.udsp.iq.dto;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/12
 * TIME:9:49
 */
public class IqIndexDto {

    public IqIndexDto() {
    }

    public IqIndexDto(int rowIndex, int returnTitleIndex, int orderTitleIndex) {
        this.rowIndex = rowIndex;
        this.returnTitleIndex = returnTitleIndex;
        this.orderTitleIndex = orderTitleIndex;
    }

    /**
     * 当前行位置
     */
    private int rowIndex;

    /**
     * 返回字段标题位置
     */
    private int returnTitleIndex;

    /**
     * 排序字段标题位置
     */
    private int orderTitleIndex;



    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getReturnTitleIndex() {
        return returnTitleIndex;
    }

    public void setReturnTitleIndex(int returnTitleIndex) {
        this.returnTitleIndex = returnTitleIndex;
    }

    public int getOrderTitleIndex() {
        return orderTitleIndex;
    }

    public void setOrderTitleIndex(int orderTitleIndex) {
        this.orderTitleIndex = orderTitleIndex;
    }
}
