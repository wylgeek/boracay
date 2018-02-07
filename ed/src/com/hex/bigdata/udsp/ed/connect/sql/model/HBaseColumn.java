package com.hex.bigdata.udsp.ed.connect.sql.model;

public class HBaseColumn {

	private String family; // 列族
	private String qualifier; // 列

	public HBaseColumn(String family, String qualifier) {
		super();
		this.family = family;
		this.qualifier = qualifier;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
}
