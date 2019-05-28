package com.dollhouse.baf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.dollhouse.baf.entity.Column;
import com.dollhouse.baf.entity.Table;



@Component
public interface GeneratorMapper {
	public List<Table> getTables(@Param("tableSchema")String tableSchema, @Param("tableName")String tableName);
	public List<Column> getTableColumns(@Param("tableSchema")String tableSchema, @Param("tableName")String tableName);
}