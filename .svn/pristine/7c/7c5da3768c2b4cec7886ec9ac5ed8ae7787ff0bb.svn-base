package com.dollhouse.core.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

import com.dollhouse.core.dao.dml.DmlItem;



@Component("mysqlBaseMapper")
public interface MysqlBaseMapper extends BaseMapper {
	/**
	 * Get current date time from database
	 */
	@Override
	@Select("select current_timestamp()")
	public Date getCurrentDate();
	
	/**
	 * Add a record to specified table
	 * @param dmlItem
	 * @return
	 */
	@Override
	@Insert("insert into ${dmlItem.table}(${dmlItem.columns}) values(${dmlItem.columnValues})")
	@SelectKey(before=false,keyProperty="dmlItem.id",resultType=Long.class,statement="select last_insert_id()")
	public Integer insert(@Param("dmlItem")DmlItem dmlItem);
}