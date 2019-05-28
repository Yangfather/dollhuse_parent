package com.dollhouse.admin.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.domain.BaseDomain;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.ApplicationContextUtil;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.core.utils.IpUtil;
import com.dollhouse.core.utils.StringUtils;


public class BaseSchedule {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	protected ApplicationContextUtil applicationContextUtil;
	
	@Autowired
	protected BaseService baseService;
	
	protected static String IP = "127.0.0.1";
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param value 值
	 */
	public void addWhereCondition(List<Where> whereList, String column, Object value) throws Exception {
		this.addWhereCondition(whereList, column, Where.EQ, value, true);
	}
	
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param condition 查询条件(=,>,<,>等)
	 * @param value 值
	 */
	public void addWhereCondition(List<Where> whereList, String column, String condtion, Object value) throws Exception {
		this.addWhereCondition(whereList, column, condtion, value, true);
	}
	
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param condition 查询条件(=,>,<,>等)
	 * @param value 值
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 */
	public void addWhereCondition(List<Where> whereList, String column, String condtion, Object value, boolean isDateNeedCovert) throws Exception {
		if(StringUtils.isEmpty(column) || value == null) return;
		if(value instanceof String && StringUtils.isEmpty(value.toString())) return;
		
		if(whereList == null) whereList = new ArrayList<Where>();
		if(isDateNeedCovert && value instanceof Date) value = DateUtil.convert((Date) value);
		whereList.add(new Where(column, value, condtion));
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, true);
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param formatType 格式化类型(date/datetime),默认为date
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, String formatType) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, true);
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, boolean isDateNeedCovert) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, isDateNeedCovert, "date");
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 * @param formatType 格式化类型(date/datetime),默认为date
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, boolean isDateNeedCovert, String formatType) throws Exception {
		if(StringUtils.isEmpty(column)) return;
		
		if(whereList == null) whereList = new ArrayList<Where>();
		
		List<Where> ands = new ArrayList<Where>();
		if(startValue != null) {
			if(isDateNeedCovert && startValue instanceof Date) {
				if("date".equals(formatType)) {
					String startDate = DateUtil.dateFormat((Date) startValue) + " 00:00:00";
					startValue = DateUtil.convert(startDate);
				} else {
					startValue = DateUtil.convert((Date) endValue);
				}
			}
			ands.add(new Where(column, startValue, Where.GE));
		}
		if(endValue != null) {
			if(isDateNeedCovert && endValue instanceof Date) {
				if("date".equals(formatType)) {
					String endDate = DateUtil.dateFormat((Date) endValue) + " 23:59:59";
					endValue = DateUtil.convert(endDate);
				} else {
					endValue = DateUtil.convert((Date) endValue);
				}
			}
			ands.add(new Where(column, endValue, Where.LE));
		}
		if(ands.size() > 0) whereList.add(Where.list(ands));
	}
	
	public String getRequestIp() {
		return applicationContextUtil.getRequestIp();
	}
	
	public Long getLongRequestIp() {
		return IpUtil.ipStrToLong(getRequestIp());
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param whereList 过滤条件
	 * @return 不存在：null, 存在：错误提示信息(xxx(xxx)已存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes" })
	public <T extends BaseEntity> String validateExist(Class<T> clazz, Object fieldValue, String viewName, List<Where> whereList) throws Exception {
		if(whereList == null || whereList.size() <= 0) {
			throw new InvalidAttributeValueException("whereList can not be null");
		}
		
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param pkColumn 主键字段(编辑的时候必填，新增放空)
	 * @param pkValue 主键值(编辑的时候必填，新增放空)
	 * @param fieldName 要校验的字段
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param wherList 过滤条件
	 * @return 不存在：null, 存在：错误提示信息(xxx(xxx)已存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateExist(Class<T> clazz, String pkColumn, Object pkValue, String fieldName, Object fieldValue, String viewName, List<Where> whereList) throws Exception {
		if(StringUtils.isEmpty(pkColumn)) pkColumn = BaseDomain.ID;
		
		List<Where> wheres = new ArrayList<Where>();
		if(whereList != null && whereList.size() >= 0) {
			wheres.addAll(whereList);
		}
		wheres.add(Where.eq(fieldName, fieldValue));
		if(pkValue != null) {
			wheres.add(Where.notEq(pkColumn, pkValue));
		}
		List result = baseService.getListWithMap(clazz, wheres, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param whereList 过滤条件
	 * @return 存在：null, 不存在：错误提示信息(xxx(xxx)不存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateNotExist(Class<T> clazz, Object fieldValue, String viewName, String moudle, String function, List<Where> whereList) throws Exception {
		if(whereList == null || whereList.size() <= 0) {
			throw new InvalidAttributeValueException("whereList can not be null");
		}
		
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.notexist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param pkColumn 主键字段(编辑的时候必填，新增放空)
	 * @param pkValue 主键值(编辑的时候必填，新增放空)
	 * @param fieldName 要校验的字段
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @return 存在：null, 不存在：错误提示信息(xxx(xxx)不存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateNotExist(Class<T> clazz, String pkColumn, Object pkValue, String fieldName, Object fieldValue, String viewName) throws Exception {
		if(StringUtils.isEmpty(pkColumn)) pkColumn = "id";
		
		List<Where> whereList = new ArrayList<Where>();
		if(pkValue != null) {
			whereList.add(Where.eq(fieldName, fieldValue));
			whereList.add(Where.notEq(pkColumn, pkValue));
		}
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	public String getMessage(String code, Object[] args) {
		return applicationContextUtil.getMessage(code, args, null);
	}
}
