package com.dollhouse.admin.interceptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.admin.entity.Menu;
import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.interceptor.LoginHandlerInterceptor;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.PropertiesUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.entity.comm.SysSystemConfig;
import com.dollhouse.entity.comm.domain.SysSystemConfigDomain;


public class AdminLoginHandlerInterceptor extends LoginHandlerInterceptor {
	@Autowired
	private BaseService baseService;
	
	@Override
	@SuppressWarnings("unchecked")
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(request.getServletPath().matches(".*/((assets)|(js)|(images)|(common)|(public)).*") || request.getServletPath().contains("/api")){
			super.postHandle(request, response, handler, modelAndView);
			return;
		}
		Object hd = request.getSession().getAttribute(Constant.SESSION_MENU);
		if(modelAndView != null && hd != null) {
			Map<String,String> subsetMap = this.checkSubset();
			modelAndView.addObject("hd", hd);
			
			Map<String, Boolean> authMap = new HashMap<String, Boolean>();
			Map<String, Object> headerInfo = (Map<String, Object>)hd;
			Object obj = headerInfo.get("menuList");
			if(obj != null) {
				for(Menu menu : (List<Menu>)obj) {
					menu.setActive(false);
					if(request.getServletPath().endsWith(menu.getUrl()) || (subsetMap.containsKey(menu.getUrl()) && subsetMap.get(menu.getUrl()).contains(request.getServletPath()))) {
						addAuthButton(authMap, menu);
						menu.setActive(true);
						break;
					}
					boolean isEnd = false;
					if(menu.getSubMenu() != null && menu.getSubMenu().size() > 0) {
						for(Menu subMenu : menu.getSubMenu()) {
							if(StringUtils.isBlank(subMenu.getUrl())) {
								continue;
							}
							if(request.getServletPath().endsWith(subMenu.getUrl())) {
								addAuthButton(authMap, subMenu);
								menu.setActive(true);
								isEnd = true;
								break;
							}
						}
					}
					if(isEnd) {
						break;
					}
				}
			}
			modelAndView.addObject("auth", authMap);
			
			List<SysSystemConfig> configList = baseService.getList(
					SysSystemConfig.class,
					Collections.singletonList(Where.in(SysSystemConfigDomain.NID, new String[]{"site_logo", "site_name", "site_keywords", "site_discription", "site_copyright"})),
					Parameter.queryColumn(new String[] {SysSystemConfigDomain.NID,SysSystemConfigDomain.VALUE})
				);
				if(configList != null && configList.size() > 0) {
					for(SysSystemConfig config : configList) {
						if("site_logo".equals(config.getNid())) {
							modelAndView.addObject(config.getNid(), PropertiesUtil.getImageHost() + config.getValue());
						} else {
							modelAndView.addObject(config.getNid(), config.getValue());
						}
					}
				}
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	private void addAuthButton(Map<String, Boolean> authMap, Menu menu) {
		if(menu == null || menu.getSubMenu() == null) {
			return;
		}
		
		for(Menu btn : menu.getSubMenu()) {
			if(!btn.getUrl().startsWith("btn:") || !btn.isChecked() ) {
				continue;
			}
			
			authMap.put(btn.getUrl().replace("btn:", ""), true);
		}
	}
	
	private Map<String, String> checkSubset() {
		Map<String, String> subsetMap = new HashMap<String, String>();
		subsetMap.put("platform/manager/list", "/platform/manager/detail,");
		subsetMap.put("project/loan/list", "/project/loan/detail,");
		subsetMap.put("loan/loan/list", "/loan/loan/detail,");
		return subsetMap;
	}
}
