package com.dollhouse.core.dao.query;

import java.io.Serializable;

import com.dollhouse.core.entity.domain.BaseDomain;

public class Parameter implements Serializable {
	private static final long serialVersionUID = -3392017094980696766L;
	
	public static final String LIMIT = "limit";
	public static final String PAGENO = "pageNo";
	public static final String PAGESIZE = "pageSize";
	public static final String ORDER_BY = "orderBY";
	public static final String GROUP_BY = "groupBy";
	public static final String QUERY_COLUMN = "queryColumn";

	private String type;
	
	private Object value;

	public String getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}
	
	public static Parameter limit(Integer limit) {
		Parameter parameter = new Parameter();
		parameter.value = limit;
		parameter.type = LIMIT;
		return parameter;
	}
	
	public static Parameter pageNo(Integer pageNo) {
		Parameter parameter = new Parameter();
		parameter.value = pageNo;
		parameter.type = PAGENO;
		return parameter;
	}
	
	public static Parameter pageSize(Integer pageSize) {
		Parameter parameter = new Parameter();
		parameter.value = pageSize;
		parameter.type = PAGESIZE;
		return parameter;
	}
	
	public static Parameter orderBy(OrderBy ... orderBys) {
		Parameter parameter = new Parameter();
		parameter.value = orderBys;
		parameter.type = ORDER_BY;
		return parameter;
	}
	
	public static Parameter groupBy(String[] groupBys) {
		Parameter parameter = new Parameter();
		parameter.value = groupBys;
		parameter.type = GROUP_BY;
		return parameter;
	}
	
	public static Parameter queryId() {
		Parameter parameter = new Parameter();
		parameter.value = new String[]{BaseDomain.ID};
		parameter.type = QUERY_COLUMN;
		
		return parameter;
	}
	
	public static Parameter querySingle(String columnName) {
		Parameter parameter = new Parameter();
		parameter.value = new String[]{columnName};
		parameter.type = QUERY_COLUMN;
		
		return parameter;
	}
	
	public static Parameter queryColumn(String[] queryColumns) {
		Parameter parameter = new Parameter();
		parameter.value = queryColumns;
		parameter.type = QUERY_COLUMN;
		return parameter;
	}
}