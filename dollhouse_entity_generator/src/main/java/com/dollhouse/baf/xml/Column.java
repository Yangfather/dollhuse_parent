package com.dollhouse.baf.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class Column implements Serializable {
	private static final long serialVersionUID = -6981768880435744939L;

	private String name;
	
	private String javaType;

	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="javaType")
	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
}