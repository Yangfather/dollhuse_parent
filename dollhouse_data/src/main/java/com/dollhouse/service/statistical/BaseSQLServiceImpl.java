package com.dollhouse.service.statistical;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollhouse.entity.comm.UserInfo;
import com.dollhouse.mapper.BaseSQLMapper;
@Service("BaseSQLService")
public class BaseSQLServiceImpl implements BaseSQLService {
	@Autowired
	private BaseSQLMapper baseSQLMapper;

	@Override
	public List<String> all() {
		
		return this.baseSQLMapper.all();
	}

	@Override
	public List<UserInfo> testEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserInfo> testPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> saleStatistics() {
		// TODO Auto-generated method stub
		return this.baseSQLMapper.saleStatistics();
	}

	

}
