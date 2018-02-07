package com.hex.bigdata.udsp.mm.dto;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/12
 * TIME:9:49
 */
public class MmIndexDto {

    public MmIndexDto() {
    }

    public MmIndexDto(int rowIndex, int returnTitleIndex) {
        this.rowIndex = rowIndex;
        this.returnTitleIndex = returnTitleIndex;
    }

    /**
     * 当前行位置
     */
    private int rowIndex;

    /**
     * 返回字段标题位置
     */
    private int returnTitleIndex;


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

}
