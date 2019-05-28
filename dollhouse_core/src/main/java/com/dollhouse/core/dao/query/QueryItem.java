package com.dollhouse.core.dao.query;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QueryItem implements Serializable {
	private static final long serialVersionUID = 4340881612323817124L;
	
	private String table;
	
	private String queryColumns;
	
	private String whereCondition;
	
	private String groupBy;
	
	private String orderBy;
	
	private String pageHeader;
	
	private String pageFooter;
	
	private Map<String, Object> paramMap = new HashMap<String, Object>();

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getQueryColumns() {
		return queryColumns;
	}

	public void setQueryColumns(String queryColumns) {
		this.queryColumns = queryColumns;
	}

	public String getWhereCondition() {
		return whereCondition;
	}

	public void setWhereCondition(String whereCondition) {
		this.whereCondition = whereCondition;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}

	public String getPageFooter() {
		return pageFooter;
	}

	public void setPageFooter(String pageFooter) {
		this.pageFooter = pageFooter;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}