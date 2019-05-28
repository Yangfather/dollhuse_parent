package com.dollhouse.core.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dollhouse.core.dao.dml.DmlItem;
import com.dollhouse.core.dao.query.QueryItem;
import com.dollhouse.core.entity.BaseEntity;


public interface BaseMapper {
	/**
	 * Get current date time from database
	 */
	public Date getCurrentDate();
	
	/**
	 * Add a record to specified table
	 * @param dmlItem
	 * @return
	 */
	public Integer insert(@Param("dmlItem")DmlItem dmlItem);
	
	/**
	 * Add records to specified table
	 * @param dmlItem
	 * @return
	 */
	@Insert("insert into ${dmlItem.table}(${dmlItem.columns}) ${dmlItem.columnValues}")
	public Integer insertList(@Param("dmlItem")DmlItem dmlItem);
    
	/**
	 * Update table values
	 * @param dmlItem
	 * @return
	 */
	@Update("update ${dmlItem.table} set ${dmlItem.columnValues} where ${dmlItem.whereCondition}")
    public Integer update(@Param("dmlItem")DmlItem dmlItem);
    
    /**
     * Delete records from specified table
     * @param dmlItem
     * @return
     */
	@Delete("delete from ${dmlItem.table} where ${dmlItem.whereCondition}")
    public Integer delete(@Param("dmlItem")DmlItem dmlItem);
    
    /**
     * Count records that specified query condition
     * @param queryItem
     * @return
     */
    @Select("select count(1) from (select ${queryItem.queryColumns} from ${queryItem.table} where ${queryItem.whereCondition} ${queryItem.groupBy}) t")
    public Integer getListCount(@Param("queryItem")QueryItem queryItem);
    

    /**
     * Count records that specified query condition
     * @param queryItem
     * @return
     */
    @Select("select count(1) from ${queryItem.table} where ${queryItem.whereCondition}")
    public Integer getSimpleListCount(@Param("queryItem")QueryItem queryItem);
    
    /**
     * Get one record that specified query condition
     * @param queryItem
     * @param clazz
     * @return
     */
    @Select("select ${queryItem.queryColumns} from ${queryItem.table} where ${queryItem.whereCondition} ${queryItem.groupBy} ${queryItem.orderBy}")
    public <T extends BaseEntity> T getOne(@Param("queryItem")QueryItem queryItem, @Param("getClazz")Class<T> clazz);
    
    /**
     * Get one record that specified query condition
     * @param queryItem
     * @param clazz
     * @return
     */
    @Select("select ${queryItem.queryColumns} from ${queryItem.table} where ${queryItem.whereCondition} ${queryItem.groupBy} ${queryItem.orderBy}")
    public Map<String, Object> getOneWithMap(@Param("queryItem")QueryItem queryItem);
    
    /**
     * Get records that specified query condition
     * @param queryItem
     * @param clazz
     * @return
     */
    @Select("${queryItem.pageHeader} select ${queryItem.queryColumns} from ${queryItem.table} where ${queryItem.whereCondition} ${queryItem.groupBy} ${queryItem.orderBy} ${queryItem.pageFooter}")
    public <T> List<T> getList(@Param("queryItem")QueryItem queryItem, @Param("getClazz")Class<T> clazz);
}