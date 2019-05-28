package com.dollhouse.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.entity.comm.AdminInfo;


public class AdminBaseController extends BaseController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected BaseService baseService;

	
	public Long getSessionUserId() {
		AdminInfo admin = getSessionUser();
		return admin == null ? null : admin.getId();
	}
	
	public String getSessionUserName() {
		AdminInfo admin = getSessionUser();
		return admin == null ? null : admin.getAdminName();
	}
	
	public AdminInfo getSessionUser() {
		Object obj = getSessionAttribute(Constant.SESSION_USER);
		if(obj == null || !(obj instanceof AdminInfo)) return null;
		
		return (AdminInfo)obj;
	}
	
	public void setAdminLogAtrribute(DyResponse response,String[] keys, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(keys != null && values != null){
			for (int i = 0; i < keys.length; i++) {
				map.put(keys[i], values[i]);
			}
		}
		this.getRequest().setAttribute("admin_log_map", map);
		this.getRequest().setAttribute("dyResponse", response);
	}
}