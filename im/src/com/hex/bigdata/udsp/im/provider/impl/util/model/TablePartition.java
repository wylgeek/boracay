package com.hex.bigdata.udsp.im.provider.impl.util.model;

public class TablePartition {
	private String colName;
	private String colValue;
	private String dataType;

	public TablePartition() {
		super();
	}

	public TablePartition(String colName, String colValue, String dataType) {
		super();
		this.colName = colName;
		this.colValue = colValue;
		this.dataType = dataType;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColValue() {
		return colValue;
	}

	public void setColValue(String colValue) {
		this.colValue = colValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

}
