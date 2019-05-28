package com.dollhouse.core.dao.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.dollhouse.core.dao.dml.DmlItem;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.mapper.BaseMapper;
import com.dollhouse.core.utils.ReflectUtils;


@Component("mysqlBaseDao")
public class MysqlBaseDaoImpl extends AbstractBaseDao {
	@Override
	public String getDbKeywordEscape() {
		return "`";
	}
	
	public String getSequenceName(String table) {
		return null;
	}
	
	@Override
	public String getPageHeader(boolean isPaging) {
		return "";
	}
	
	@Override
	public <T> BaseMapper getBaseMapper(Class<T> clazz) {
		return (BaseMapper)applicationContextUtil.getBean("mysqlBaseMapper");
	}
	
	@Override
	public String getPageFooter(Integer pageNo, Integer pageSize, boolean isPaging) {
		if(!isPaging) {
			return pageSize == null ? "" : "limit 0, " + pageSize;
		} else {
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? Page.PAGESIZE : pageSize;
			
			return "limit " + (pageNo - 1)*pageSize + ", " + pageSize;
		}
	}

	@Override
	public void addPrimaryKey(StringBuffer strColumns, StringBuffer strColumnValues) {}
	
	@Override
	public <T extends BaseEntity> Integer insert(List<T> insertEntityList) throws DaoException {
		if(insertEntityList == null || insertEntityList.size() <= 0) throw new DaoException("insertEntityList could not be empty");
		
		StringBuffer strColumns = new StringBuffer();
		StringBuffer strColumnValues = new StringBuffer();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		int index = 0;
		T firstEntity = insertEntityList.get(0);
		for (T insertEntity : insertEntityList) {
			StringBuffer strColumnValue = new StringBuffer();
			for (Field field : ReflectUtils.getFields(firstEntity, new String[] { ID, SERIAL_VERSION_UID, EXPRESSION })) {
				Object fieldValue = ReflectUtils.getFieldValue(insertEntity, field);
				if (fieldValue != null) {
					paramMap.put(field.getName() + index, fieldValue);
					if (index == 0)
						strColumns.append(",").append(getColumnName(field));
					strColumnValue.append(",#{dmlItem.paramMap.").append(field.getName()).append(index).append("}");
				}
			}
			index++;
			strColumnValues.append(",(").append(strColumnValue.substring(1)).append(")");
		}
		if(strColumns.length() <= 0) throw new DaoException("Can't not insert a record without insert columns");
		
		DmlItem dmlItem = new DmlItem();
		dmlItem.setParamMap(paramMap);
		dmlItem.setColumns(strColumns.substring(1));
		dmlItem.setColumnValues("values" + strColumnValues.substring(1));
		dmlItem.setTable(getTableName(firstEntity.getClass()));
		dmlItem.setSequenceName(getSequenceName(dmlItem.getTable()));
		
		int result = 0;
		try {
			result = getBaseMapper(firstEntity.getClass()).insertList(dmlItem);
		} catch(Exception e) {
			throw new DaoException("Insert record to table " + dmlItem.getTable() + " fail", e);
		}
		if(result <= 0) throw new DaoException("Insert record to table " + dmlItem.getTable() + " fail");
		
		return 1;
	}
}