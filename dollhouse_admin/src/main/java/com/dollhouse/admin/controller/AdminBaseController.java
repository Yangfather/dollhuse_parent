package com.dollhouse.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dollhouse.admin.entity.Menu;
import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.domain.BaseDomain;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.EmailUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.entity.comm.AdminInfo;
import com.dollhouse.entity.comm.AdminMenu;
import com.dollhouse.entity.comm.AdminMenuLevel;
import com.dollhouse.entity.comm.domain.AdminMenuDomain;
import com.dollhouse.entity.comm.domain.AdminMenuLevelDomain;
import com.dollhouse.service.comm.CommonService;


public class AdminBaseController extends BaseController {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	protected BaseService baseService;
	
	@Autowired
	protected CommonService commonService;
	
	@Autowired
	protected EmailUtil emailUtil;
	
	public Map<String, Object> getHeaderInfo() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("menuList", getMenuList(null));
		resultMap.put("noticeMsgNum", getNoticeMsgNum());
		
		String sessionUser = getSessionUserName();
		resultMap.put("sessionUser", StringUtils.isNotBlank(sessionUser) ? sessionUser.substring(0, 1) + "***" : "");
		
		return resultMap;
	}
	
	public Integer getNoticeMsgNum() {
		return 0;
	}
	
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
	
	public List<Menu> getMenuList(Long categoryId) {
		AdminInfo admin = getSessionUser();
		if(admin == null || admin.getId()== null) return null;
		
		AdminMenuLevel adminMenuLevel = null;
		try {
			adminMenuLevel = baseService.getOne(
					AdminMenuLevel.class,
					Collections.singletonList(Where.eq(BaseDomain.ID, categoryId != null ? categoryId : admin.getAdminGrade())),
					Parameter.queryColumn(new String[]{AdminMenuLevelDomain.ID, AdminMenuLevelDomain.ADMIN_LEVEL}));
		} catch (DaoException e) {
			logger.error("Get admin purview fail", e);
		}
		if(adminMenuLevel == null) return null;
		
		List<AdminMenu> menuList = null;
		try {
			List<Where> whereList = new ArrayList<Where>();
			whereList.add(Where.eq(AdminMenuDomain.STATUS, 1));
			//if(categoryId == null) {
			//	whereList.add(Where.in(SysMenuDomain.MENU_ID, adminCategory.getPurview().split(",")));
			//}
//		menuList返回head.jsp
			menuList = baseService.getList(
					AdminMenu.class,
					whereList,
//				SORT_INDEX升序排列，MENU_ID升序排列，执行顺序	MENU_ID>SORT_INDEX
					Parameter.queryColumn(new String[]{AdminMenuDomain.ID,AdminMenuDomain.PID,AdminMenuDomain.NAME,AdminMenuDomain.PID,AdminMenuDomain.URL}),
					Parameter.orderBy(OrderBy.asc(AdminMenuDomain.ID))
			);
		} catch (DaoException e) {
			logger.error("Get menus fail", e);
		}
		if(menuList == null || menuList.size() <= 0) return null;
		
		String purview=StringUtils.isNotBlank(adminMenuLevel.getAdminLevel())?(","+adminMenuLevel.getAdminLevel()+","):"";
		List<Menu> topMenuList=new ArrayList<>();
			for (AdminMenu menu : menuList) {
				if(menu==null || menu.getId()==null ||menu.getPid()==null) continue;
				if(categoryId==null && !purview.contains(","+menu.getId()+",")) continue;
				if(menu.getPid()==0){
					Menu topMenu=new Menu();
					topMenu.setId(menu.getId());
					topMenu.setName(menu.getName());
					topMenu.setUrl(menu.getUrl());
					topMenu.setChecked(purview.contains(","+menu.getId()+","));
					topMenuList.add(topMenu);
			}
		}
		for (Menu topMenu : topMenuList) {
			List<Menu> subMenuList=new ArrayList<>();
			for (AdminMenu menu : menuList) {
				if(menu==null || menu.getId()==null ||menu.getPid()==null) continue;
				if(admin.getId()==null && !purview.contains(","+menu.getId()+",")) continue;
				if(menu.getPid()!=topMenu.getId().intValue()) continue;
				Menu subMenu=new Menu();
				subMenu.setId(menu.getId());
				subMenu.setName(menu.getName());
				subMenu.setUrl(menu.getUrl());
				subMenu.setChecked(purview.contains(","+menu.getId()+","));
				subMenuList.add(subMenu);
				topMenu.setSubMenu(subMenuList);
			}
			
		}
		if(topMenuList.size() > 0) {
			topMenuList.get(0).setActive(true);
		}
		return topMenuList;
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