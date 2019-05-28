package com.dollhouse.core.entity;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 2675311554406530455L;
	
	public static final int PAGESIZE = 10;
	
	private int pageNo = 1;
	
	private int pageSize = PAGESIZE;
	
	private int totalSize;
	
	private int totalPage;
	
	private List<T> items;
	
	private Object params;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
		this.setTotalPage(totalSize%pageSize==0 ? totalSize/pageSize : totalSize/pageSize + 1);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}
	
	
}