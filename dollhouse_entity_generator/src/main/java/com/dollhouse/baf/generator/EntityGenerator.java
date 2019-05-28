package com.dollhouse.baf.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.UnsupportedDataTypeException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dollhouse.baf.entity.Column;
import com.dollhouse.baf.entity.Entity;
import com.dollhouse.baf.entity.EntityProperty;
import com.dollhouse.baf.mapper.GeneratorMapper;
import com.dollhouse.baf.xml.Tables;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@SuppressWarnings("resource")
public class EntityGenerator {
	public static GeneratorMapper generatorMapper;
	static {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-core.xml");
		generatorMapper = applicationContext.getBean(GeneratorMapper.class);
	}
	
	public static void main(String[] args) throws Exception {
		Tables tables = getTables();
		Configuration configuration = getConfiguration();
		for(com.dollhouse.baf.xml.Table table : tables.getTableList()) {
	        Entity entity = createDataModel(table.getName(), table.getEntityName(), table.getColumnList());
	        entity.setMapper(table.getMapper());
	        entity.setPackageName(tables.getPackageName());
	        
	        File outDirFile = new File(tables.getLocation());
	        if(!outDirFile.exists()) outDirFile.mkdir();
	        
	        System.out.println("Generate entity for table " + entity.getTableName());
	        File javaFile = toJavaFilename(outDirFile, entity.getPackageName(), entity.getClassName());
	        if(javaFile != null){
	            Writer javaWriter = new FileWriter(javaFile);
	            
	            Template template = configuration.getTemplate("entity.ftl");
	            template.process(Collections.singletonMap("entity", entity), javaWriter);
	            javaWriter.flush();
	            javaWriter.close();
	            
	            System.out.println("\t" + javaFile.getCanonicalPath());
	        }
	        
	        File domainFile = toJavaFilename(outDirFile, entity.getPackageName() + ".domain", entity.getClassName() + "Domain");
	        if(javaFile != null){
	            Writer javaWriter = new FileWriter(domainFile);
	            
	            Template template = configuration.getTemplate("entityDomain.ftl");
	            template.process(Collections.singletonMap("entity", entity), javaWriter);
	            javaWriter.flush();
	            javaWriter.close();
	            
	            System.out.println("\t" + domainFile.getCanonicalPath());
	        }
		}
	}
	
	private static Configuration getConfiguration() throws IOException {
		Configuration configuration = new Configuration();
    	configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File("./src/main/resources"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        return configuration;
	}
	
	private static Tables getTables() throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(Tables.class);
		Unmarshaller unMarshaller = ctx.createUnmarshaller();
		return (Tables)unMarshaller.unmarshal(new File("src/main/resources/tables.xml"));
	}
	
	private static Entity createDataModel(String tableName, String entityName, List<com.dollhouse.baf.xml.Column> columns) throws Exception {
    	columns = columns == null ? new ArrayList<com.dollhouse.baf.xml.Column>() : columns;
    	
		List<Column> columnList = getTableColumns(tableName);
		
		List<String> importList = new ArrayList<String>();
		
		List<EntityProperty> propertyList = new ArrayList<EntityProperty>();
		for(Column column : columnList) {
			if("id,create_by,create_date,update_by,update_date,version".contains(column.getColumnName())) continue;
			
			EntityProperty entityProperty = new EntityProperty();
			entityProperty.setColumnName(column.getColumnName());
			entityProperty.setPropertyName(jdbc2Java(column.getColumnName(), false));
			
			Class<?> javaType = null;
			if("auto_increment".equals(column.getExtra()))
				javaType = Long.class;
			else if(!column.getDataType().contains("char") && column.getPrecision() > 9 && column.getNumericScale()<=0)
				javaType = Long.class;
			else
				javaType = getJavaType(column.getDataType().toUpperCase(), column.getPrecision(), column.getScale());
			String propertyType = javaType.getCanonicalName();
			for(com.dollhouse.baf.xml.Column xmlColumn : columns) {
				if(xmlColumn.getName().equals(column.getColumnName()))
					propertyType = xmlColumn.getJavaType();
			}
			
			entityProperty.setPropertyType(propertyType.substring(propertyType.lastIndexOf(".") + 1));
			if(!propertyType.contains("java.lang.") && !importList.contains(propertyType)) importList.add(propertyType);
			
			propertyList.add(entityProperty);
		}
		
		importList.add("com.dollhouse.core.annotation.Table");
		importList.add("com.dollhouse.core.annotation.Column");
		importList.add("com.dollhouse.core.entity.BaseEntity");
    	
        Entity entity = new Entity();
        entity.setClassName(entityName);
        entity.setTableName(tableName);
        entity.setImportList(importList);
        entity.setProperties(propertyList);
        entity.setSuperclass("BaseEntity");
        entity.setExtraProperties(new ArrayList<EntityProperty>());
        
        return entity;
    }
	
	private static File toJavaFilename(File outFile, String packageName, String className) {
        File packagePath = new File(outFile, packageName.replace('.', '/'));
        if(!packagePath.exists()) packagePath.mkdirs();
        
        return new File(packagePath, className + ".java");
    }
	
	private static List<Column> getTableColumns(String tableName) throws IOException {
		List<Column> resultList = generatorMapper.getTableColumns(null, tableName);
		if(resultList == null || resultList.size() <= 0) return resultList;
		
		List<Column> tempList = new ArrayList<Column>();
		for(Column column : resultList) {
			String columnType = column.getColumnType();
			int idx = columnType.indexOf("(");
			if(idx <= 0) {
				column.setPrecision(0);
				column.setScale(0);
			} else {
				if(columnType.startsWith("enum")) {
					column.setPrecision(1);
					column.setScale(0);
				} else {
					String[] precisionScale = columnType.substring(idx + 1, columnType.indexOf(")")).split(",");
					column.setPrecision(Integer.parseInt(precisionScale[0]));
					column.setScale(precisionScale.length == 2 ? Integer.parseInt(precisionScale[1]) : 0);
				}
			}
			tempList.add(column);
		}
		return tempList;
	}
	
	private static String jdbc2Java(String jdbcName, boolean firstUppper) {
		if(jdbcName == null || "".equals(jdbcName.trim())) return "";
		
		StringBuffer sb = new StringBuffer();
		char[] chars = jdbcName.toLowerCase().toCharArray();
		for(int i=0;i<chars.length;i++) {
			char c = chars[i];
			if(c == '_') {
				int j = i + 1;
				if(j < chars.length) {
					sb.append(new String(new char[]{chars[j]}).toUpperCase());
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return firstUppper ? sb.substring(0, 1).toUpperCase() + sb.substring(1) : sb.toString();
	}
	
	private static Class<?> getJavaType(String jdbcType, int precision, int scale) throws Exception {
		Map<String, Class<?>> jdbc2JavaType = new HashMap<String, Class<?>>();
		jdbc2JavaType.put("TINYINT", Integer.class);
		jdbc2JavaType.put("SMALLINT", Integer.class);
		jdbc2JavaType.put("MEDIUMINT", Integer.class);
		jdbc2JavaType.put("BIGINT", Long.class);
		jdbc2JavaType.put("FLOAT", Double.class);
		jdbc2JavaType.put("DOUBLE", Double.class);
		jdbc2JavaType.put("CHAR", String.class);
		jdbc2JavaType.put("VARCHAR", String.class);
		jdbc2JavaType.put("TINYBLOB", String.class);
		jdbc2JavaType.put("TINYTEXT", String.class);
		jdbc2JavaType.put("BLOB", String.class);
		jdbc2JavaType.put("TEXT", String.class);
		jdbc2JavaType.put("MEDIUMBLOB", String.class);
		jdbc2JavaType.put("MEDIUMTEXT", String.class);
		jdbc2JavaType.put("LOGNGBLOB", String.class);
		jdbc2JavaType.put("LONGTEXT", String.class);
		jdbc2JavaType.put("DATE", Date.class);
		jdbc2JavaType.put("YEAR", Integer.class);
		jdbc2JavaType.put("DATETIME", Date.class);
		jdbc2JavaType.put("TIMESTAMP", Date.class);
		jdbc2JavaType.put("ENUM", Integer.class);
		
		Map<String, Class<?>> jdbc2JavaType1 = new HashMap<String, Class<?>>();
		jdbc2JavaType1.put("TIME", Date.class);
		jdbc2JavaType1.put("INT", Integer.class);
		jdbc2JavaType1.put("INTEGER", Integer.class);
		jdbc2JavaType1.put("DECIMAL", BigDecimal.class);
		
		Class<?> javaType = jdbc2JavaType.get(jdbcType);
		if(javaType == null){
			javaType = jdbc2JavaType1.get(jdbcType);
			if(javaType == null) throw new UnsupportedDataTypeException("Can not find a java type that match current jdbc type(" + jdbcType + ")");
			
			Object javaObj = javaType.getClass();
			if(javaObj instanceof Date) {
				return String.class;
			} else if(javaObj instanceof Integer) {
				if(precision > 9)
					return Long.class;
			} else if(javaObj instanceof BigDecimal && scale == 0) {
				if(precision <= 9)
					return Integer.class;
				else
					return Long.class;
			}
		}
		
		return javaType;
	}
}