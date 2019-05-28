package com.dollhouse.core.dao.impl;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dollhouse.core.annotation.Column;
import com.dollhouse.core.annotation.Table;
import com.dollhouse.core.dao.BaseDao;
import com.dollhouse.core.dao.dml.DmlItem;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.QueryItem;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.mapper.BaseMapper;
import com.dollhouse.core.utils.ApplicationContextUtil;
import com.dollhouse.core.utils.ReflectUtils;
import com.dollhouse.core.utils.StringUtils;



public abstract class AbstractBaseDao implements BaseDao {
	@Autowired
	protected ApplicationContextUtil applicationContextUtil;

	protected abstract String getDbKeywordEscape();
	
	protected abstract String getSequenceName(String table);
	
	protected abstract String getPageHeader(boolean isPaging);
	
	protected abstract <T> BaseMapper getBaseMapper(Class<T> clazz);
	
	protected abstract String getPageFooter(Integer pageNo, Integer pageSize, boolean isPaging);
	
	protected abstract void addPrimaryKey(StringBuffer strColumns, StringBuffer strColumnValues);
	
	@Override
	public Date getCurrentDate() {
		return getBaseMapper(BaseEntity.class).getCurrentDate();
	}
	
	@Override
	public <T extends BaseEntity> Long insert(T insertEntity) throws DaoException {
		if(insertEntity == null) throw new DaoException("insertEntity could not be empty");
		
		StringBuffer strColumns = new StringBuffer();
		StringBuffer strColumnValues = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		addPrimaryKey(strColumns, strColumnValues);
		for(String fieldName : insertEntity.getExpression().keySet()) {
			paramMap.put(fieldName, null);
			strColumns.append(",").append(getColumnName(insertEntity.getClass(), fieldName));
			strColumnValues.append(",").append(insertEntity.getExpression().get(fieldName));
		}
		for(Field field : ReflectUtils.getFields(insertEntity, new String[]{ID, SERIAL_VERSION_UID, EXPRESSION})) {
			if(paramMap.containsKey(field.getName())) continue;
			
			Object fieldValue = ReflectUtils.getFieldValue(insertEntity, field);
			if(fieldValue != null) {
				paramMap.put(field.getName(), fieldValue);
				strColumns.append(",").append(getColumnName(field));
				strColumnValues.append(",#{dmlItem.paramMap.").append(field.getName()).append("}");
			}
		}
		if(strColumns.length() <= 0) throw new DaoException("Can't not insert a record without insert columns");
		
		DmlItem dmlItem = new DmlItem();
		dmlItem.setParamMap(paramMap);
		dmlItem.setColumns(strColumns.substring(1));
		dmlItem.setColumnValues(strColumnValues.substring(1));
		dmlItem.setTable(getTableName(insertEntity.getClass()));
		dmlItem.setSequenceName(getSequenceName(dmlItem.getTable()));
		
		try {
			getBaseMapper(insertEntity.getClass()).insert(dmlItem);
		} catch(Exception e) {
			throw new DaoException("Insert record to table " + dmlItem.getTable() + " fail", e);
		}
		
		insertEntity.setId(dmlItem.getId());
		return dmlItem.getId() == null ? 0 : dmlItem.getId();
	}
	
	@Override
	public <T extends BaseEntity> Integer update(T updateEntity, List<Where> whereList, boolean updateEmpty) throws DaoException {
		if(updateEntity == null) throw new DaoException("updateEntity could not be null");
		if(whereList == null || whereList.size() <= 0) throw new DaoException("whereList could not be null");
		
		StringBuffer strColumnValues = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		for(String fieldName : updateEntity.getExpression().keySet()) {
			paramMap.put(fieldName, null);
			strColumnValues.append(",").append(getColumnName(updateEntity.getClass(), fieldName)).append(" = ").append(updateEntity.getExpression().get(fieldName));
		}
		for(Field field : ReflectUtils.getFields(updateEntity, new String[]{ID, SERIAL_VERSION_UID, EXPRESSION})) {
			if(paramMap.containsKey(field.getName())) continue;
			
			Object fieldValue = ReflectUtils.getFieldValue(updateEntity, field.getName());
			if(!updateEmpty && fieldValue == null) continue;
			
			paramMap.put(field.getName(), fieldValue);
			strColumnValues.append(",").append(getColumnName(field)).append(" = #{dmlItem.paramMap.").append(field.getName()).append("}");
		}
		if(strColumnValues.length() <= 0) throw new DaoException("Can't not update records without set statement");
		
		DmlItem dmlItem = new DmlItem();
		dmlItem.setParamMap(paramMap);
		dmlItem.setColumnValues(strColumnValues.substring(1));
		dmlItem.setTable(getTableName(updateEntity.getClass()));
		dmlItem.setWhereCondition(getWhereCondition(updateEntity.getClass(), whereList, paramMap, true));
		
		int result = 0;
		try {
			result = getBaseMapper(updateEntity.getClass()).update(dmlItem);
		} catch(Exception e) {
			throw new DaoException("update table " + dmlItem.getTable() + "'s record fail", e);
		}
		
		return result;
	}

	public <T extends BaseEntity> Integer delete(Class<T> clazz, List<Where> whereList) throws DaoException {
		if(clazz == null) throw new DaoException("clazz could not be null");
		//if(whereList == null || whereList.size() <= 0) throw new DaoException("whereList could not be null");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DmlItem dmlItem = new DmlItem();
		dmlItem.setParamMap(paramMap);
		dmlItem.setTable(getTableName(clazz));
		dmlItem.setWhereCondition(getWhereCondition(clazz, whereList, paramMap, true));
		
		int result = 0;
		try {
			result = getBaseMapper(clazz).delete(dmlItem);
		} catch(Exception e) {
			throw new DaoException("Delete record from table " + dmlItem.getTable() + "' fail", e);
		}
		
		return result;
	}

	private <T extends BaseEntity> QueryItem getOneQueryItem(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys) throws DaoException{
		if(clazz == null) throw new DaoException("clazz could not be null");
		//if(whereList == null || whereList.size() <= 0) throw new DaoException("whereList could not be null");
		
		QueryItem queryItem = new QueryItem();
		queryItem.setTable(getTableName(clazz));
		queryItem.setGroupBy(getGroupBy(clazz, groupBys));
		queryItem.setQueryColumns(getQueryColumns(clazz, queryColumns));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		queryItem.setParamMap(paramMap);
		queryItem.setWhereCondition(getWhereCondition(clazz, whereList, paramMap, false));
		
		return queryItem;
	}
	
	@Override
	public <T extends BaseEntity> T getOne(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys) throws DaoException {
		return getBaseMapper(clazz).getOne(getOneQueryItem(clazz, whereList, queryColumns, groupBys), clazz);
	}
	
	@Override
	public <T extends BaseEntity> Map<String, Object> getOneWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys) throws DaoException {
		return getBaseMapper(clazz).getOneWithMap(getOneQueryItem(clazz, whereList, queryColumns, groupBys));
	}
	
	private <T extends BaseEntity> QueryItem getListQueryItem(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException{
		if(clazz == null) throw new DaoException("clazz could not be null");
		
		QueryItem queryItem = new QueryItem();
		queryItem.setTable(getTableName(clazz));
		queryItem.setPageFooter(getPageHeader(false));
		queryItem.setOrderBy(getOrderBy(clazz, orderBys));
		queryItem.setGroupBy(getGroupBy(clazz, groupBys));
		queryItem.setPageFooter(getPageFooter(pageNo, pageSize, false));
		queryItem.setQueryColumns(getQueryColumns(clazz, queryColumns));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		queryItem.setParamMap(paramMap);
		queryItem.setWhereCondition(getWhereCondition(clazz, whereList, paramMap, false));
		
		return queryItem;
	}

	@Override
	public <T extends BaseEntity> List<T> getList(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException {
		return getBaseMapper(clazz).getList(getListQueryItem(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize), clazz);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> List<Map> getListWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException {
		return getBaseMapper(clazz).getList(getListQueryItem(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize), Map.class);
	}
	
	private <T> Page<T> getPage(Class<?> clazz, Class<T> resultClazz, QueryItem queryItem, Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? Page.PAGESIZE : pageSize;
		
		List<T> items = null;
		int totalSize = 0;
		if(StringUtils.isBlank(queryItem.getGroupBy())){
			totalSize = getBaseMapper(clazz).getSimpleListCount(queryItem);
		} else {
			totalSize = getBaseMapper(clazz).getListCount(queryItem);
		}
		if(totalSize > 0) items = getBaseMapper(clazz).getList(queryItem, resultClazz);
		
		Page<T> page = new Page<T>();
		page.setItems(items);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalSize(totalSize);
		
		return page;
	}
	
	private <T extends BaseEntity> QueryItem getPageQueryItem(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException {
		if(clazz == null) throw new DaoException("clazz could not be null");
		
		QueryItem queryItem = new QueryItem();
		queryItem.setTable(getTableName(clazz));
		queryItem.setOrderBy(getOrderBy(clazz, orderBys));
		queryItem.setGroupBy(getGroupBy(clazz, groupBys));
		queryItem.setPageHeader(getPageHeader(true));
		queryItem.setPageFooter(getPageFooter(pageNo, pageSize, true));
		queryItem.setQueryColumns(getQueryColumns(clazz, queryColumns));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		queryItem.setParamMap(paramMap);
		queryItem.setWhereCondition(getWhereCondition(clazz, whereList, paramMap, false));
		
		return queryItem;
	}
	
	@Override
	public <T extends BaseEntity> Page<T> getPage(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException {
		return getPage(clazz, clazz, getPageQueryItem(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize), pageNo, pageSize);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> Page<Map> getPageWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException {
		return getPage(clazz, Map.class, getPageQueryItem(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize), pageNo, pageSize);
	}
	
	private <T extends BaseEntity> String getWhereCondition(Class<T> clazz, List<Where> whereList, Map<String, Object> paramMap, boolean isDml) {
		if(whereList == null || whereList.size() <= 0 || (whereList.size() == 1 && "#getAll#".equals(whereList.get(0).getName()))) return "1 = 1";
		
		String firstCondition = null;
		StringBuffer strWhereCondition = new StringBuffer();
		for(Where where : whereList) {
			if(where.getWhereList() != null && where.getWhereList().size() > 0) {
				int whereIndex = 0;
				StringBuffer strInWhereCondition = new StringBuffer();
				for(Where inWhere : where.getWhereList()) {
					if(firstCondition == null) firstCondition = where.isOr() ? " or " : " and ";
					
					strInWhereCondition.append(getSingelWhere(clazz, inWhere, whereIndex, paramMap, isDml));
					whereIndex++;
				}
				strWhereCondition.append(where.isOr() ? " or (" : " and (").append(strInWhereCondition).append(")");
			} else {
				if(firstCondition == null) firstCondition = where.isOr() ? " or " : " and ";
				
				strWhereCondition.append(where.isOr() ? " or " : " and ").append(getSingelWhere(clazz, where, 0, paramMap, isDml));
			}
		}
		return strWhereCondition.toString().replaceFirst(firstCondition, "");
	}
	
	private <T extends BaseEntity>String getSingelWhere(Class<T> clazz, Where where, int whereIndex, Map<String, Object> paramMap, boolean isDml) {
		StringBuffer strWhere = new StringBuffer();
		if(whereIndex > 0) strWhere.append(where.isOr() ? " or " : " and ");
		
		String columnName = getColumnName(clazz, where.getName());
		String prefix = isDml ? "#{dmlItem.paramMap." : "#{queryItem.paramMap.";
		String paramName = whereIndex == 0 ? where.getName() : (where.getName()+ "_" + whereIndex);
		
		if(Where.IN.equals(where.getCondition()) || Where.NOT_IN.equals(where.getCondition())) {
			StringBuffer in = new StringBuffer();
			if (where.getValues() != null && where.getValues().length >= 1) {
				int idx = 0;
				for (Object value : where.getValues()) {
					paramMap.put(paramName + "_" + idx, value);
					in.append(",").append(prefix).append(paramName).append("_").append(idx++).append("}");
				}
			} else {
				in.append(",''");
			}
			strWhere.append(columnName).append(where.getCondition()).append("(").append(in.substring(1)).append(")");
		} else if(Where.IS_NULL.equals(where.getCondition()) || Where.IS_NOT_NULL.equals(where.getCondition())) {
			strWhere.append(columnName).append(where.getCondition());
		} else if(Where.BETWEEN.equals(where.getCondition())) {
			Object[] values = where.getValues();
			if(values[0] != null) {
				paramMap.put(paramName, values[0]);
				strWhere.append(columnName).append(values[2]).append(prefix).append(paramName).append("}");
			}
			if(values[1] != null) {
				paramMap.put(paramName + "End", values[1]);
				strWhere.append(values[0] == null ? "" : " and ").append(columnName).append(values[3]).append(prefix).append(paramName).append("End}");
			}
		} else {
			paramMap.put(paramName, where.getValue());
			strWhere.append(columnName).append(where.getCondition()).append(prefix).append(paramName).append("}");
		}
		
		return strWhere.toString();
	}
	
	protected <T extends BaseEntity> String getTableName(Class<T> clazz) {
		return clazz.getAnnotation(Table.class).name();
	}
	
	protected String getColumnName(Field field) {
		return  field == null ? null : getDbKeywordEscape() + field.getAnnotation(Column.class).name() + getDbKeywordEscape();
	}
	
	private <T extends BaseEntity> String getColumnName(Class<T> clazz, String fieldName) {
		return getColumnName(ReflectUtils.getField(clazz, fieldName));
	}
	
	private <T extends BaseEntity> String getQueryColumns(Class<T> clazz, String[] queryColumns) {
		StringBuffer strQueryColumns = new StringBuffer();
		if(queryColumns == null || queryColumns.length <= 0) {
			for(Field field : ReflectUtils.getFields(clazz, new String[]{SERIAL_VERSION_UID, EXPRESSION})) {
				strQueryColumns.append(",").append(getColumnName(field));
			}
		} else {
			for(String queryColumn : queryColumns) {
				if(queryColumn.contains(" "))
					strQueryColumns.append(",").append(queryColumn);
				else
					strQueryColumns.append(",").append(getColumnName(clazz, queryColumn));
			}
		}
		return strQueryColumns.substring(1);
	}
	
	private <T extends BaseEntity>String getGroupBy(Class<T> clazz, String[] groupBys) {
		if(groupBys == null || groupBys.length <= 0) return "";
		
		StringBuffer strGroupBys = new StringBuffer();
		for(String groupBy : groupBys) {
			if(groupBy.contains(" "))
				strGroupBys.append(",").append(groupBy);
			else
				strGroupBys.append(",").append(getColumnName(clazz, groupBy));
		}
		
		return "group by " + strGroupBys.substring(1);
	}
	
	private <T extends BaseEntity> String getOrderBy(Class<T> clazz, OrderBy[] orderBys) {
		if(orderBys == null || orderBys.length <= 0) return "";
		
		StringBuffer strOrderBys = new StringBuffer();
		for(OrderBy orderBy : orderBys) {
			if(orderBy.getIdx() != null) {
				strOrderBys.append(",").append(orderBy.getIdx());
			} else if(orderBy.getColumn().contains(" "))
				strOrderBys.append(",").append(orderBy.getColumn());
			else {
				strOrderBys.append(",").append(getColumnName(clazz, orderBy.getColumn()));
			}
			
			strOrderBys.append(orderBy.isAsc() ? "" : " desc");
		}
		
		return "order by " + strOrderBys.substring(1);
	}
	

	@Override
	public <T extends BaseEntity> Integer getListCount(Class<T> clazz, List<Where> whereList, String[] groupBys) throws DaoException {
		return this.getBaseMapper(clazz).getListCount(this.getOneQueryItem(clazz, whereList, new String[] { " -1" }, groupBys));
	}
}