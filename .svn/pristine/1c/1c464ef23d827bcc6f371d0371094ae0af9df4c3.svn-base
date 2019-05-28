package com.dollhouse.core.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollhouse.core.dao.BaseDao;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.ApplicationContextUtil;



@Service("baseService")
public class BaseServiceImpl implements BaseService{
	@Autowired
	private ApplicationContextUtil applicationContextUtil;
	
	public BaseDao getBaseDao() {
		return (BaseDao)applicationContextUtil.getBean(applicationContextUtil.getDatabaseType() + "BaseDao");
	}
	
	public Date getCurrentDate() {
		return getBaseDao().getCurrentDate();
	}

	@Override
	public <T extends BaseEntity> long insert(T insertEntity) throws DaoException {
		return getBaseDao().insert(insertEntity);
	}
	
	@Override
	public <T extends BaseEntity> long insert(List<T> insertEntityList) throws DaoException {
		return getBaseDao().insert(insertEntityList);
	}
	
	@Override
	public <T extends BaseEntity> int updateById(T updateEntity, Long id, boolean ... updateEmpty) throws DaoException {
		boolean hasExtendParamter = updateEmpty != null && updateEmpty.length > 0;
		return getBaseDao().update(updateEntity, Collections.singletonList(Where.eq(BaseDao.ID, id)), hasExtendParamter ? updateEmpty[0] : false);
	}

	@Override
	public <T extends BaseEntity> int update(T updateEntity, List<Where> whereList, boolean ... updateEmpty) throws DaoException {
		boolean hasExtendParamter = updateEmpty != null && updateEmpty.length > 0;
		return getBaseDao().update(updateEntity, whereList, hasExtendParamter ? updateEmpty[0] : false);
	}
	
	@Override
	public <T extends BaseEntity> int deleteById(Class<T> clazz, Long id) throws DaoException {
		return getBaseDao().delete(clazz, Collections.singletonList(Where.eq(BaseDao.ID, id)));
	}

	@Override
	public <T extends BaseEntity> int delete(Class<T> clazz, List<Where> whereList) throws DaoException {
		return getBaseDao().delete(clazz, whereList);
	}

	@Override
	public <T extends BaseEntity> T getById(Class<T> clazz, Long id, String[] queryColumns) throws DaoException {
		return getBaseDao().getOne(clazz, Collections.singletonList(Where.eq(BaseDao.ID, id)), queryColumns, null);
	}
	
	@Override
	public <T extends BaseEntity> Map<String, Object> getByIdWithMap(Class<T> clazz, Long id, String[] queryColumns) throws DaoException {
		return getBaseDao().getOneWithMap(clazz, Collections.singletonList(Where.eq(BaseDao.ID, id)), queryColumns, null);
	}

	@Override
	public <T extends BaseEntity> T getOne(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException {
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getOne(clazz, whereList, queryColumns, groupBys);
	}
	
	@Override
	public <T extends BaseEntity> Map<String, Object> getOneWithMap(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException {
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		
		return getBaseDao().getOneWithMap(clazz, whereList, queryColumns, groupBys);
	}

	@Override
	public <T extends BaseEntity> List<T> getAll(Class<T> clazz, Parameter ... parameters) throws DaoException {
		OrderBy[] orderBys = null;
		String[] queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getList(clazz, Collections.singletonList(Where.eq("#getAll#", null)), queryColumns, null, orderBys, null, null);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> List<Map> getAllWithMap(Class<T> clazz, Parameter ... parameters) throws DaoException {
		OrderBy[] orderBys = null;
		String[] queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getListWithMap(clazz, Collections.singletonList(Where.eq("#getAll#", null)), queryColumns, null, orderBys, null, null);
	}

	@Override
	public <T extends BaseEntity> List<T> getList(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException {
		Integer limit = null;
		OrderBy[] orderBys = null;
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.LIMIT.equals(parameter.getType())) limit = (Integer)parameter.getValue();
				else if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getList(clazz, whereList, queryColumns, groupBys, orderBys, null, limit);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> List<Map> getListWithMap(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException {
		Integer limit = null;
		OrderBy[] orderBys = null;
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.LIMIT.equals(parameter.getType())) limit = (Integer)parameter.getValue();
				else if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getListWithMap(clazz, whereList, queryColumns, groupBys, orderBys, null, limit);
	}

	@Override
	public <T extends BaseEntity> Page<T> getPage(Class<T> clazz, List<Where> whereList, Integer pageNo, Parameter... parameters) throws DaoException {
		if(pageNo == null) throw new IllegalArgumentException("pageNo can not be empty");
		
		Integer pageSize = null;
		OrderBy[] orderBys = null;
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.PAGESIZE.equals(parameter.getType())) pageSize = (Integer)parameter.getValue();
				else if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		//System.out.println("12344321"+getBaseDao().getPage(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize));
		return getBaseDao().getPage(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> Page<Map> getPageWithMap(Class<T> clazz, List<Where> whereList, Integer pageNo, Parameter... parameters) throws DaoException {
		if(pageNo == null) throw new IllegalArgumentException("pageNo can not be empty");
		
		Integer pageSize = null;
		OrderBy[] orderBys = null;
		String[] groupBys = null, queryColumns = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.PAGESIZE.equals(parameter.getType())) pageSize = (Integer)parameter.getValue();
				else if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
				else if(Parameter.ORDER_BY.equals(parameter.getType())) orderBys = (OrderBy[])parameter.getValue();
				else if(Parameter.QUERY_COLUMN.equals(parameter.getType())) queryColumns = (String[])parameter.getValue();
			}
		}
		
		return getBaseDao().getPageWithMap(clazz, whereList, queryColumns, groupBys, orderBys, pageNo, pageSize);
	}

	@Override
	public <T extends BaseEntity> Integer getListCount(Class<T> clazz, List<Where> whereList, Parameter... parameters) throws DaoException {
		String[] groupBys = null;
		if(parameters != null && parameters.length > 0) {
			for(Parameter parameter : parameters) {
				if(Parameter.GROUP_BY.equals(parameter.getType())) groupBys = (String[])parameter.getValue();
			}
		}
		return getBaseDao().getListCount(clazz, whereList, groupBys);
	}
}