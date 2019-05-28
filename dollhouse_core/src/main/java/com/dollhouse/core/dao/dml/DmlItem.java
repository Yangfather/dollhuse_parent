package com.dollhouse.core.dao.dml;

import java.io.Serializable;
import java.util.Map;

public class DmlItem implements Serializable {
	private static final long serialVersionUID = -655062984911991829L;

	private Long id;
	
	private String table;
	
	private String columns;
	
	private String sequenceName;
	
	private String columnValues;
	
	private String whereCondition;
	
	private Map<String, Object> paramMap;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	public String getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(String columnValues) {
		this.columnValues = columnValues;
	}

	public String getWhereCondition() {
		return whereCondition;
	}

	public void setWhereCondition(String whereCondition) {
		this.whereCondition = whereCondition;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}