package com.dollhouse.core.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

public class ApplicationContextUtil implements ApplicationContextAware {
	private String databaseType;
	
	private ApplicationContext applicationContext;
	
	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
	
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public Object getSessionAttribute(String attribueName) {
		return getRequest().getSession().getAttribute(attribueName);
	}
	
	public void removeSessionAttribute(String attribueName) {
		getRequest().getSession().removeAttribute(attribueName);
	}
	
	public void setSessionAttribute(String attributeName, Object attributeValue) {
		getRequest().getSession().setAttribute(attributeName, attributeValue);
	}
	
	public String getMessage(String code) {
		return getMessage(code, null, null);
	}
	
	public String getMessage(String code, Locale locale) {
		return getMessage(code, null, locale);
	}
	
	public String getMessage(String code, Object[] args) {
		return getMessage(code, args, null);
	}
	
	public String getMessage(String code, Object[] args, Locale locale) {
		RequestContext requestContext = new RequestContext(getRequest());
		if(locale == null) locale =  requestContext.getLocale();
		
		return requestContext.getMessageSource().getMessage(code, args, locale);
	}
	
	public String getRequestIp() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");  
		}  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	    	ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
	    }
	    System.out.println("获取IP地址:"+ip);
	    String subIp = ip;
		if (ip != null && ip.indexOf(",") > 0) {
			String[] ips = ip.split("\\,");
			for (String tmpIp : ips) {
				if (tmpIp != null && !"unknown".equals(tmpIp.trim()) && !"127.0.0.1".equals(tmpIp.trim()) && !"0:0:0:0:0:0:0:1".equals(tmpIp.trim())) {
					subIp = tmpIp.trim();
					break;
				}
			}
		}
		System.out.println("获取真实IP地址:"+subIp);
		return "0:0:0:0:0:0:0:1".equals(subIp) ? "127.0.0.1" : subIp;
	}
	
	public void changeLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
	}
}