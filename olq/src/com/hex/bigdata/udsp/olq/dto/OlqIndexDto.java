package com.hex.bigdata.udsp.olq.dto;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/10/12
 * TIME:9:49
 */
public class OlqIndexDto {

    public OlqIndexDto() {
    }

    public OlqIndexDto(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * 当前行位置
     */
    private int rowIndex;


    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

}
