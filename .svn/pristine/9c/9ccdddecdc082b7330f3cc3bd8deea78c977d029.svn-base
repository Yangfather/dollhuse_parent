package com.dollhouse.baf.xml;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tables")
public class Tables implements Serializable {
	private static final long serialVersionUID = 3438297076418079997L;

	private String location;
	
	private String packageName;
	
	private List<Table> tableList;

	@XmlAttribute(name="location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@XmlAttribute(name="package")
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

    @XmlElement(name="table")
	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}
}