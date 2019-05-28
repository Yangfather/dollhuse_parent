package com.dollhouse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dollhouse.entity.comm.UserInfo;

public interface UserInfoMapper {
	public List<String> all();
	public List<UserInfo> testEmail(@Param("email") String email);
	public List<UserInfo> testPhone(@Param("phone") String phone);
	public List<Map<String,Object>> saleStatistics();
}
