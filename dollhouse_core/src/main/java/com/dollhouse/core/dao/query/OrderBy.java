package com.dollhouse.core.dao.query;

import java.io.Serializable;

public class OrderBy implements Serializable {
	private static final long serialVersionUID = -6243652073112197459L;
	
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	private Integer idx;
	
	private String column;
	
	private boolean isAsc = true;
	
	public Integer getIdx() {
		return idx;
	}

	public String getColumn() {
		return column;
	}

	public boolean isAsc() {
		return isAsc;
	}
	
	public static OrderBy asc(int idx) {
		OrderBy orderBy = new OrderBy();
		orderBy.idx = idx;
		return orderBy;
	}
	
	public static OrderBy asc(String column) {
		OrderBy orderBy = new OrderBy();
		orderBy.column = column;
		return orderBy;
	}
	
	public static OrderBy desc(int idx) {
		OrderBy orderBy = new OrderBy();
		orderBy.idx = idx;
		orderBy.isAsc = false;
		return orderBy;
	}
	
	public static OrderBy desc(String column) {
		OrderBy orderBy = new OrderBy();
		orderBy.isAsc = false;
		orderBy.column = column;
		return orderBy;
	}
}