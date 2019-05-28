package com.dollhouse.baf.entity;

import java.util.List;

public class Entity {
	private String packageName;
	
	private String className;
	
	private String superclass;
	
	private String tableName;
	
	private String mapper;
	
	List<String> importList;
	
	List<EntityProperty> properties;
	
	List<EntityProperty> extraProperties;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getMapper() {
		return mapper;
	}

	public void setMapper(String mapper) {
		this.mapper = mapper;
	}

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

	public List<EntityProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<EntityProperty> properties) {
		this.properties = properties;
	}

	public List<EntityProperty> getExtraProperties() {
		return extraProperties;
	}

	public void setExtraProperties(List<EntityProperty> extraProperties) {
		this.extraProperties = extraProperties;
	}
}