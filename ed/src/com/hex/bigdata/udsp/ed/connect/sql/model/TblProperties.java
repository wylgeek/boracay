package com.hex.bigdata.udsp.ed.connect.sql.model;

/**
 * Impala表属性
 * 
 * @author junjiem
 * 
 */
public class TblProperties {
	private String key;
	private String value;

	public TblProperties() {
		super();
	}

	public TblProperties(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
