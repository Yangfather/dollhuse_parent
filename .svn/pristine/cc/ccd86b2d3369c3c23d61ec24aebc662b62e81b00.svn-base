package com.dollhouse.core.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.Option;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.entity.domain.BaseDomain;

/**
 * 数据转换工具 
 */
public class DataConvertUtil {
	private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	private DecimalFormat decimalFormat = new DecimalFormat("######0.00");
	
	private Object data;
	
	private boolean toString;
	
	private boolean mapUnderscoreToCamelCase;
	
	private List<String> ipFields = new ArrayList<String>();
	
	private List<String> imageFields = new ArrayList<String>();
	
	private Map<String, String> alais = new HashMap<String, String>();
	
	private Map<String, String> appends = new HashMap<String, String>();
	
	private Map<String, String> dateFields = new LinkedHashMap<String, String>();
	
	private Map<String, List<Option>> statusFields = new LinkedHashMap<String, List<Option>>();
	
	private Map<String, DecimalFormat> numberFields = new LinkedHashMap<String, DecimalFormat>();
	
	/**
	 * @param data 要做转换的数据
	 */
	public DataConvertUtil(Object data) {
		this.data = data;
		this.toString = true;
	}
	
	/**
	 * @param data 要做转换的数据
	 * @param toString 是否把value转为String, 默认为true
	 */
	public DataConvertUtil(Object data, Boolean toString) {
		this.data = data;
		this.toString = (toString == null ? true : toString);
	}
	
	/**
	 * @param data 要做转换的数据
	 * @param toString 是否把value转为String, 默认为true
	 * @param mapUnderscoreToCamelCase 是否把field的下划线转为驼峰法
	 */
	public DataConvertUtil(Object data, Boolean toString, Boolean mapUnderscoreToCamelCase) {
		this.data = data;
		this.toString = (toString == null ? true : false);
		this.mapUnderscoreToCamelCase = (mapUnderscoreToCamelCase == null ? false : mapUnderscoreToCamelCase);
	}
	
	/**
	 * 设置要做转换的IP字段
	 * @param imageField 字段名，多个用,隔开
	 * @return
	 */
	public DataConvertUtil setIp(String ipField) {
		if(StringUtils.isEmpty(ipField)) return this;
		
		for(String temp : ipField.split(","))
			this.ipFields.add(temp);
		return this;
	}
	
	/**
	 * 设置要做转换的图片字段
	 * @param imageField 字段名，多个用,隔开
	 * @return
	 */
	public DataConvertUtil setImage(String imageField) {
		if(StringUtils.isEmpty(imageField)) return this;
		
		for(String temp : imageField.split(","))
			this.imageFields.add(temp);
		return this;
	}
	
	/**
	 * 设置要转换的日期字段(默认转为yyyy-MM-dd HH:mm:ss)
	 * @param dateField 字段名，多个用,隔开
	 * @return
	 */
	public DataConvertUtil setDate(String dateField) {
		return setDate(dateField, DATETIME);
	}
	
	/**
	 * 设置要转换的日期字段
	 * @param dateField 字段名，多个用,隔开
	 * @param dateFormat 日期转换格式，默认转为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public DataConvertUtil setDate(String dateField, String dateFormat) {
		if(StringUtils.isEmpty(dateField)) return this;
		
		for(String temp : dateField.split(","))
			this.dateFields.put(temp, dateFormat == null ? DATETIME : dateFormat);
		return this;
	}
	
	/**
	 * 设置要转换的number字段(默认保留两位小数)
	 * @param numberField 字段名，多个用,隔开
	 * @return
	 */
	public DataConvertUtil setNumber(String numberField) {
		return setNumber(numberField, decimalFormat);
	}
	
	/**
	 * 设置要转换的number字段
	 * @param numberField 字段名，多个用,隔开
	 * @param decimalFormat 转换格式，默认保留两位小数
	 * @return
	 */
	public DataConvertUtil setNumber(String numberField, DecimalFormat decimalFormat) {
		if(StringUtils.isEmpty(numberField)) return this;
		
		for(String temp : numberField.split(","))
			this.numberFields.put(temp, decimalFormat == null ? this.decimalFormat : decimalFormat);
		return this;
	}
	
	/**
	 * 设置要转换的状态字段
	 * @param statusField 字段名，多个用,隔开
	 * @param optionList 状态选项列表
	 * @return
	 */
	public DataConvertUtil setStatus(String statusField, List<Option> optionList) {
		if(StringUtils.isEmpty(statusField)) return this;
		
		for(String temp : statusField.split(","))
			this.statusFields.put(temp, optionList);
		return this;
	}
	
	/**
	 * 设置别名
	 * @param original 原字段名
	 * @param alais 别名
	 * @return
	 */
	public DataConvertUtil setAlais(String original, String alais) {
		if(StringUtils.isEmpty(original) && StringUtils.isEmpty(alais)) return this;
		
		this.alais.put(original, alais);
		return this;
	}
	
	/**
	 * 在字段后面增加字符
	 * @param statusField
	 * @param appendStr
	 * @return
	 */
	public DataConvertUtil setAppends(String statusField, String appendStr) {
		if (StringUtils.isEmpty(statusField)) {
			return this;
		}
		this.appends.put(statusField, appendStr);
		return this;
	}

	/**
	 * 数据转换
	 * @return
	 */
	public Object convert() {
		if(data == null) return null;
		
		if(data instanceof Map) return convert(Map.class);
		else if(data instanceof BaseEntity) return convert(Map.class);
		else if(data instanceof Page) return convertPageData(Map.class);
		else if(data instanceof DyResponse) return convertResponseData(Map.class);
		else if(data instanceof List) {
			List<?> dataList = (List<?>)data;
			if(dataList.size() <= 0) return data;
			return convertListData(dataList.get(0).getClass());
		}
		
		return data;
	}
	
	/**
	 * 数据转换，返回指定格式的数据(data只能是Map或BaseEntity)
	 * @param returnType Map或者BaseEntity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T convert(Class<T> returnType) {
		if(data == null) return null;
		validateType(data, returnType);
		
		if(data instanceof Map) {
			return convertMap(returnType);
		} else if(data instanceof BaseEntity) {
			return convertBaseEntity(returnType);
		}
		
		return (T)data;
	}
	
	/**
	 * 转换DyResponse.data的数据 
	 * @param returnType
	 * @return
	 */
	private <T> DyResponse convertResponseData(Class<?> returnType) {
		if(!(data instanceof DyResponse)) throw new IllegalArgumentException("Unsupport data type, the data must be DyResponse");
		
		DyResponse response = (DyResponse)data;
		if(response.getData() == null) return response;
		
		Object responseData = response.getData();
		if(!(responseData instanceof Page || responseData instanceof List || responseData instanceof Map))
			throw new IllegalArgumentException("DyResponse's data must be Page or List or Map");
		
		this.data = responseData;
		if(responseData instanceof Map) response.setData(convert(returnType));
		else if(responseData instanceof List) response.setData(convertListData(returnType));
		else if(responseData instanceof Page) response.setData(convertPageData(returnType));
		
		return response;
	}
	
	/**
	 * 转换Page.items的数据
	 * @param returnType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> Page<T> convertPageData(Class<T> returnType) {
		if(!(data instanceof Page)) throw new IllegalArgumentException("Unsupport data type, the data must be Page");
		
		Page<T> page = (Page<T>)data;
		List<T> items = page.getItems();
		if(items == null || items.size() <= 0) return page;
		
		this.data = items;
 		page.setItems(convertListData(returnType));
 		
		return page;
	}
	
	/**
	 * 转换List的数据
	 * @param returnType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> convertListData(Class<T> returnType) {
		if(!(data instanceof List)) throw new IllegalArgumentException("Unsupport data type, the data must be List");
		
		List items = (List)data;
		List<T> newItems = new ArrayList<T>();
 		for(Object obj : items) {
 			this.data = obj;
			newItems.add(convert(returnType));
		}
 		
		return newItems;
	}
	
	/**
	 * Map数据转换
	 * @param <T>
	 * @param returnType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T convertMap(Class<T> returnType) {
		Object result = newInstance(returnType);
		Map<String, Object> map = (Map<String, Object>) data;
		for(String field : map.keySet()) {
			Object value = map.get(field);
			if(alais.containsKey(field))
				result = setFieldValue(result, alais.get(field), value);
			result = setFieldValue(result, field, value);
		}
		
		return result == null ? null : (T)result;
	}
	
	/**
	 * BaseEntity数据转换
	 * @param <T>
	 * @param returnType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T convertBaseEntity(Class<T> returnType) {
		Object result = newInstance(returnType);
		for(Field field : data.getClass().getDeclaredFields()) {
			Object value = ReflectUtils.getFieldValue(data, field.getName());
			if(alais != null && alais.containsKey(field.getName()))
				result = setFieldValue(result, alais.get(field.getName()), value);
			result = setFieldValue(result, field.getName(), value);
		}
		setFieldValue(result, BaseDomain.ID, ReflectUtils.getFieldValue(data, BaseDomain.ID));
		
		return result == null ? null : (T)result;
	}
	
	private Object newInstance(Class<?> type) {
		if("com.dy.core.entity.BaseEntity".equals(type.getSuperclass() == null ? "" : type.getSuperclass().getName())) {
			try {
				return type.newInstance();
			} catch(Exception e) {
			}
		}
		
		return new HashMap<String, Object>();
	}
	
	@SuppressWarnings("unchecked")
	private <T> T setFieldValue(T result, String fieldName, Object fieldValue) {
		if("serialVersionUID".equals(fieldName)) return result;
		if(result == null || fieldValue == null || StringUtils.isEmpty(fieldValue.toString())) return result;
		
		fieldValue = convertFieldValue(result, fieldName, fieldValue);
		if(mapUnderscoreToCamelCase) fieldName = columnToProperty(fieldName);
		if(result instanceof Map) {
			((Map)result).put(fieldName, fieldValue);
		} else if(result instanceof BaseEntity) {
			if(fieldName.contains("_"))
				fieldName = columnToProperty(fieldName);
			ReflectUtils.setFieldValue(result, fieldName, fieldValue);
		}
		
		return result;
	}
	
	private <T> Object convertFieldValue(T result, String fieldName, Object fieldValue) {
		if (ipFields.contains(fieldName)) {//IP
			return IpUtil.ipLongToStr(toLong(fieldValue));
		} else if (imageFields.contains(fieldName)) {//Image
			String strValue = fieldValue.toString();
			Integer sepatorIdx = strValue.indexOf("|");
			return PropertiesUtil.getImageHost() + (sepatorIdx <= 0 ? strValue : strValue.substring(0, sepatorIdx));
		} else if (dateFields.containsKey(fieldName)) {//Date
			fieldValue = convertDate(dateFields.get(fieldName), fieldValue);
		} else if (statusFields.containsKey(fieldName)) {//Status
			List<Option> optionList = statusFields.get(fieldName);
			for (Option option : optionList) {
				if (option.getValue().toString().equals(fieldValue.toString())) {
					fieldValue = option.getText();
					break;
				}
			}
		} else if (numberFields.containsKey(fieldName)) {//Number
			fieldValue = numberFields.get(fieldName).format(fieldValue);
		} else if (appends.containsKey(fieldName)) {
			fieldValue = fieldValue.toString() + appends.get(fieldName);
		} else if (toString && result instanceof Map) {
			if (fieldValue instanceof Float || fieldValue instanceof Double || fieldValue instanceof BigDecimal) {
				fieldValue = decimalFormat.format(fieldValue);
			} else {
				fieldValue = fieldValue.toString();
			}
		}

		return fieldValue;
	}
	
	private Long toLong(Object value) {
		if(value instanceof Double)
			return ((Double)value).longValue();
		return Long.parseLong(value.toString());
	}
	
	private String convertDate(String dateFormat, Object value) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		if(value instanceof Date) return format.format(value);
		if(toLong(value)<=0) return "";
		return format.format(new Date(toLong(value) * 1000));
	}
	
	/**
	 * 数据库字段名转entity属性
	 * @param columnName
	 * @return
	 */
	private String columnToProperty(String columnName) {
		if(StringUtils.isEmpty(columnName)) return "";

		char[] chars = columnName.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<chars.length;i++) {
			char c = chars[i];
			if(c == '_') {
				int j = i + 1;
				if(j < chars.length) {
					sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 类型校验
	 * @param data
	 * @param returnType
	 */
	private <T> void validateType(Object data, Class<T> returnType) {
		if(!(data instanceof Map || data instanceof BaseEntity)) 
			throw new IllegalArgumentException("Unsupport data type, the data must be Map or BaseEntity");
		
		if(!("java.util.Map".equals(returnType.getName())
				|| "com.dy.core.entity.BaseEntity".equals(returnType.getSuperclass() == null ? "" : returnType.getSuperclass().getName()))) {
			Object returnData = null;
			try {
				returnData = returnType.newInstance();
				if(returnData instanceof Map || returnData instanceof BaseEntity) return;
			} catch (Exception e1) {}
			
			throw new IllegalArgumentException("Unsupport return type, the retun type must be Map or BaseEntity");
		}
	}
}