package com.dollhouse.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;



public interface BaseService {
	public Date getCurrentDate();
	
	public <T extends BaseEntity> long insert(T insertEntity) throws DaoException;
	
	public <T extends BaseEntity> long insert(List<T> insertEntityList) throws DaoException;
	
	public <T extends BaseEntity> int updateById(T updateEntity, Long id, boolean ... updateEmpty) throws DaoException;
	
	public <T extends BaseEntity> int update(T updateEntity, List<Where> whereList, boolean ... updateEmpty) throws DaoException;
	
	public <T extends BaseEntity> int deleteById(Class<T> clazz, Long id) throws DaoException;
	
	public <T extends BaseEntity> int delete(Class<T> clazz, List<Where> whereList) throws DaoException;
	
	/**
	 * 根据ID查询
	 * @param clazz 要查询的表对应的实体
	 * @param id id值
	 * @param queryColumns 要查询的字段
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> T getById(Class<T> clazz, Long id, String[] queryColumns) throws DaoException;
	
	/**
	 * 根据ID查询
	 * @param clazz 要查询的表对应的实体
	 * @param id id值
	 * @param queryColumns 要查询的字段
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> Map<String, Object> getByIdWithMap(Class<T> clazz, Long id, String[] queryColumns) throws DaoException;
	
	/**
	 * 查询单条记录
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> T getOne(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException;
	
	/**
	 * 查询单条记录
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> Map<String, Object> getOneWithMap(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException;
	
	/**
	 * 查询所有
	 * @param clazz 要查询的表对应的实体，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> List<T> getAll(Class<T> clazz, Parameter ... parameters) throws DaoException;
	
	/**
	 * 查询所有
	 * @param clazz 要查询的表对应的实体，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> List<Map> getAllWithMap(Class<T> clazz, Parameter ... parameters) throws DaoException;
	
	/**
	 * 列表查询
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.limit(10) 查询记录数
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> List<T> getList(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException;
	
	/**
	 * 列表查询
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.limit(10) 查询记录数
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> List<Map> getListWithMap(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException;
	
	/**
	 * 分页查询
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param pageNo 第几页
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.pageSize(10) 每页记录数，默认10
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> Page<T> getPage(Class<T> clazz, List<Where> whereList, Integer pageNo, Parameter ... parameters) throws DaoException;
	
	/**
	 * 分页查询
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param pageNo 第几页
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.pageSize(10) 每页记录数，默认10
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * 	Parameter.orderBy(new OrderBy[]{OrderBy.asc("id")}) 排序
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseEntity> Page<Map> getPageWithMap(Class<T> clazz, List<Where> whereList, Integer pageNo, Parameter ... parameters) throws DaoException;
	
	/**
	 * 查询数量
	 * @param clazz 要查询的表对应的实体，必填
	 * @param whereList 查询条件，必填
	 * @param parameters 可选，支持以下参数:
	 * 	Parameter.groupBy(new String[]{"id"}) 分组
	 * 	Parameter.queryColumn(new String[]{"id"}) 查询字段
	 * @return
	 * @throws DaoException
	 */
	public <T extends BaseEntity> Integer getListCount(Class<T> clazz, List<Where> whereList, Parameter ... parameters) throws DaoException;

}
