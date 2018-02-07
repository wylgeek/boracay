package com.hex.bigdata.udsp.im.provider.impl.util.model;

public class RowFormat {
	public static final String IMPALA_FIELDS_TERMINATED_DEFAULT = "\\t";
	public static final String IMPALA_FIELDS_TERMINATED_OTHER = "\\007";
	public static final String IMPALA_LINES_TERMINATED_DEFAULT = "\\n";

	private String fieldsTerminated; // 字段结束符
	private String fieldsEscaped; // 字段逃逸符
	private String linesTerminated; // 行结束符

	public RowFormat(String fieldsTerminated) {
		super();
		this.fieldsTerminated = fieldsTerminated;
	}

	public RowFormat(String fieldsTerminated, String linesTerminated) {
		super();
		this.fieldsTerminated = fieldsTerminated;
		this.linesTerminated = linesTerminated;
	}

	public RowFormat(String fieldsTerminated, String fieldsEscaped,
			String linesTerminated) {
		super();
		this.fieldsTerminated = fieldsTerminated;
		this.fieldsEscaped = fieldsEscaped;
		this.linesTerminated = linesTerminated;
	}

	public String getFieldsTerminated() {
		return fieldsTerminated;
	}

	public void setFieldsTerminated(String fieldsTerminated) {
		this.fieldsTerminated = fieldsTerminated;
	}

	public String getFieldsEscaped() {
		return fieldsEscaped;
	}

	public void setFieldsEscaped(String fieldsEscaped) {
		this.fieldsEscaped = fieldsEscaped;
	}

	public String getLinesTerminated() {
		return linesTerminated;
	}

	public void setLinesTerminated(String linesTerminated) {
		this.linesTerminated = linesTerminated;
	}

}
