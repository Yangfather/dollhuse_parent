package com.dollhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dollhouse.entity.comm.DollShopCar;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.entity.comm.domain.DollShopCarDomain;

public interface GoodsInfoMapper {
	public List<Map<String, Object>>  getGoodsById(@Param("doll_name_id")Long dollNameId);
	public List<DollNameDomain> getGoodsListByType(@Param("doll_type")Long dollType);
	public List<Map<String, Object>> starGoods();
	public List<Map<String,Object>> areaSaleMap();
	public Map<String,Object> moneyDay(@Param("startTime") String startTime,@Param("endTIme")String endTime);

}
