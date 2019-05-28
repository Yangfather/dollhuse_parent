package com.dollhouse.core.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dollhouse.core.constant.Constant;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if ("/".equals(path))
			return true;

		if (path.matches(".*/((assets)|(js)|(images)|(common)|(public)).*"))
			return true;

		for (String newPath : list) {
			if (path.matches(newPath)) {
				return true;
			}
		}
		
		if (request.getSession().getAttribute(Constant.SESSION_USER) == null) {
			System.out.println("---------------");
			response.sendRedirect(request.getContextPath() + Constant.LOGIN_URL);
			return false;
		}

		return true;
	}
	
	/**
	 * 免拦截的url
	 */
	public static List<String> list = new ArrayList<String>();
	static {
		//备案平台管理
		list.add("/platform/list");
		list.add("/platform/toList");
		list.add("/platform/listpage");
		list.add("/platform/detail");
		list.add("/platform/detailpage");
		list.add("/system/platform/checkNum");
		list.add("/system/platform/info");
		list.add("/system/platform/toIndex");
		list.add("/platform/listDetail");
		list.add("/platform/statistics/risk");
		
		//用户中心
		list.add("/member/member/memberInfo");
		
		list.add("/api/project") ;
		list.add("/api/repay") ;
		list.add("/phone/location/get") ;
	}	
}