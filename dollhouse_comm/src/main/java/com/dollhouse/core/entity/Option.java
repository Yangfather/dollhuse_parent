package com.dollhouse.core.entity;

import java.io.Serializable;

public class Option implements Serializable {
	private static final long serialVersionUID = -7593243933345729218L;

	private String name;
	
	private String method;
	
	private Object[] params;
	
	private Object value;
	
	private String text;
	
	private String type;

	private Boolean checkstatu;
	
	private String in_type;
	
	private String verify;
	
	private String unit;
	
	private String childmark;

	public String getIn_type() {
		return in_type;
	}

	public void setIn_type(String in_type) {
		this.in_type = in_type;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean isCheckstatu() {
		return checkstatu;
	}

	public void setCheckstatu(Boolean checkstatu) {
		this.checkstatu = checkstatu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Option() {}
	
	public Option(Object value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getChildmark() {
		return childmark;
	}

	public void setChildmark(String childmark) {
		this.childmark = childmark;
	}
}