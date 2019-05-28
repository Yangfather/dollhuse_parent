package com.dollhouse.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;



public interface BaseDao {
	public static final String ID = "id";
	public static final String EXPRESSION = "expression";
	public static final String SERIAL_VERSION_UID = "serialVersionUID";
	
	public Date getCurrentDate();
	
	public <T extends BaseEntity>Long insert(T insertEntity) throws DaoException;
	
	public <T extends BaseEntity>Integer insert(List<T> insertEntityList) throws DaoException;
	
	public <T extends BaseEntity> Integer update(T updateEntity, List<Where> whereList, boolean updateEmpty) throws DaoException;
	
	public <T extends BaseEntity> Integer delete(Class<T> clazz, List<Where> whereList) throws DaoException;
	
	public <T extends BaseEntity> T getOne(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys) throws DaoException;
	
	public <T extends BaseEntity> Map<String, Object> getOneWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys) throws DaoException;
	
	public <T extends BaseEntity> List<T> getList(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException;
	
	public <T extends BaseEntity> List<Map> getListWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException;
	
	public <T extends BaseEntity> Page<T> getPage(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException;
	
	public <T extends BaseEntity> Page<Map> getPageWithMap(Class<T> clazz, List<Where> whereList, String[] queryColumns, String[] groupBys, OrderBy[] orderBys, Integer pageNo, Integer pageSize) throws DaoException;
	
	public <T extends BaseEntity> Integer getListCount(Class<T> clazz, List<Where> whereList, String[] groupBys) throws DaoException;



}