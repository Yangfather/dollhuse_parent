package com.dollhouse.service.statistical;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollhouse.entity.comm.DollName;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.entity.comm.domain.DollShopCarDomain;
import com.dollhouse.mapper.GoodsInfoMapper;
@Service("GoodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService{
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	@Override
	public List<Map<String, Object>> getGoodsById(Long dollNameId) {
		return this.goodsInfoMapper.getGoodsById(dollNameId);
	}

	@Override
	public List<DollNameDomain> getGoodsListByType(Long dollType) {
		return this.goodsInfoMapper.getGoodsListByType(dollType);
	}

	@Override
	public List<Map<String, Object>> starGoods() {
		
		return this.goodsInfoMapper.starGoods();
	}

	@Override
	public List<Map<String, Object>> areaSaleMap() {
		// TODO Auto-generated method stub
		return this.goodsInfoMapper.areaSaleMap();
	}

}
