package com.dollhouse.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.management.InvalidAttributeValueException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.editor.DyCustomDateEditor;
import com.dollhouse.core.entity.BaseEntity;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.domain.BaseDomain;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.ApplicationContextUtil;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.core.utils.DyHttpClient;
import com.dollhouse.core.utils.IpUtil;
import com.dollhouse.core.utils.OptionUtil;
import com.dollhouse.core.utils.StringUtils;



public class BaseController {
	private Logger logger = Logger.getLogger(BaseController.class);
	
	@Autowired
	public OptionUtil optionUtil;
	
	@Autowired
	protected BaseService baseService;
	
	@Autowired
	protected ApplicationContextUtil applicationContextUtil;
	
	public DyResponse createSuccessJsonResponse(Object data) {
		return this.createSuccessJsonResponse(data, "OK");
	}
	
	public DyResponse createSuccessJsonResponse(Object data, Object description) {
		DyResponse response = new DyResponse();
		response.setData(data);
		response.setStatus(DyResponse.OK);
		response.setDescription(description);
		
		return response;
	}
	
	public DyResponse createErrorJsonResponse(Object description) {
		DyResponse response = new DyResponse();
		response.setStatus(DyResponse.ERROR);
		response.setDescription(description);
		
		return response;
	}
	
	public ModelAndView createSuccessModelAndView(String viewName, Object data) {
		return createSuccessModelAndView(viewName, data, "Ok");
	}
	
	public ModelAndView createSuccessModelAndView(String viewName, Object data, Object description) {
		ModelAndView modelAndView = new ModelAndView(viewName);
		modelAndView.addObject("data", data);
		modelAndView.addObject("description", description);
		
		return modelAndView;
	}
	
	public ModelAndView createErrorModelAndView(String description) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("description", description);
		
		return modelAndView;
	}
	
	public HttpServletRequest getRequest() {
		return applicationContextUtil.getRequest();
	}
	
	public Object getSessionAttribute(String attribueName) {
		return applicationContextUtil.getSessionAttribute(attribueName);
	}
	
	public void removeSessionAttribute(String attribueName) {
		applicationContextUtil.removeSessionAttribute(attribueName);
	}
	
	public void setSessionAttribute(String attributeName, Object attributeValue) {
		if(Constant.SESSION_VERIFY_CODE.equals(attributeName)) {
			applicationContextUtil.setSessionAttribute(Constant.SESSION_VC_ERROR_TIME, 0);
		}
		
		applicationContextUtil.setSessionAttribute(attributeName, attributeValue);
	}
	
	public String validateSessionVerifyCode(String verifyCode) {
		if(getSessionAttribute(Constant.SESSION_VERIFY_CODE) == null) {
			return "验证码过期或无效，请重新获取";
		}
		
		if(StringUtils.isBlank(verifyCode)) {
			return "验证码不能为空";
		}
		if(!verifyCode.equalsIgnoreCase((String)getSessionAttribute(Constant.SESSION_VERIFY_CODE))) {
			Integer vcErrorTime = (Integer)getSessionAttribute(Constant.SESSION_VC_ERROR_TIME) + 1;
			if(vcErrorTime >= Constant.MAX_ERROR_TIMES) {
				removeSessionAttribute(Constant.SESSION_VERIFY_CODE);
				removeSessionAttribute(Constant.SESSION_VC_ERROR_TIME);
				
				return "验证码过期或无效，请重新获取";
			} else {
				setSessionAttribute(Constant.SESSION_VC_ERROR_TIME, vcErrorTime);
			}
			
			return "验证码错误";
		}
		
		return null;
	}
	
	public String getMessage(String code) {
		return applicationContextUtil.getMessage(code, null, null);
	}
	
	public String getMessage(String code, Locale locale) {
		return applicationContextUtil.getMessage(code, null, locale);
	}
	
	public String getMessage(String code, Object[] args) {
		return applicationContextUtil.getMessage(code, args, null);
	}
	
	public String getMessage(String code, Object[] args, Locale locale) {
		return applicationContextUtil.getMessage(code, args, locale);
	}
	
	public String getRequestIp() {
		return applicationContextUtil.getRequestIp();
	}
	
	public Long getLongRequestIp() {
		return IpUtil.ipStrToLong(getRequestIp());
	}
	
	public void changeLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		applicationContextUtil.changeLocale(request, response, locale);
	}
	
	public String validateNull(String[] texts, Object[] values) {
		if(values == null || texts == null || values.length != texts.length) return null;
		
		StringBuffer errorMsg = new StringBuffer();
		for(int i=0;i<values.length;i++) {
			if(texts[i] == null) continue;
			
			if(values[i] == null) errorMsg.append(",").append(texts[i]);
			if(values[i] instanceof String && StringUtils.isBlank((String)values[i])) errorMsg.append(",").append(texts[i]);
		}
		
		if(StringUtils.isNotBlank(errorMsg.toString()))
			return getMessage("validate.null", new Object[]{errorMsg.substring(1)});
		
		return null;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		String dateFormat = (String)getSessionAttribute(Constant.SESSION_DATE_FORMAT);
        binder.registerCustomEditor(Date.class, new DyCustomDateEditor(dateFormat));
    }
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param whereList 过滤条件
	 * @return 不存在：null, 存在：错误提示信息(xxx(xxx)已存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes" })
	public <T extends BaseEntity> String validateExist(Class<T> clazz, Object fieldValue, String viewName, List<Where> whereList) throws Exception {
		if(whereList == null || whereList.size() <= 0) {
			throw new InvalidAttributeValueException("whereList can not be null");
		}
		
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param pkColumn 主键字段(编辑的时候必填，新增放空)
	 * @param pkValue 主键值(编辑的时候必填，新增放空)
	 * @param fieldName 要校验的字段
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param wherList 过滤条件
	 * @return 不存在：null, 存在：错误提示信息(xxx(xxx)已存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateExist(Class<T> clazz, String pkColumn, Object pkValue, String fieldName, Object fieldValue, String viewName, List<Where> whereList) throws Exception {
		if(StringUtils.isEmpty(pkColumn)) pkColumn = BaseDomain.ID;
		
		List<Where> wheres = new ArrayList<Where>();
		if(whereList != null && whereList.size() >= 0) {
			wheres.addAll(whereList);
		}
		wheres.add(Where.eq(fieldName, fieldValue));
		if(pkValue != null) {
			wheres.add(Where.notEq(pkColumn, pkValue));
		}
		List result = baseService.getListWithMap(clazz, wheres, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @param whereList 过滤条件
	 * @return 存在：null, 不存在：错误提示信息(xxx(xxx)不存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateNotExist(Class<T> clazz, Object fieldValue, String viewName, String moudle, String function, List<Where> whereList) throws Exception {
		if(whereList == null || whereList.size() <= 0) {
			throw new InvalidAttributeValueException("whereList can not be null");
		}
		
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.notexist", new String[]{viewName, fieldValue.toString()});
	}
	
	/**
	 * 存在性校验
	 * @param clazz
	 * @param pkColumn 主键字段(编辑的时候必填，新增放空)
	 * @param pkValue 主键值(编辑的时候必填，新增放空)
	 * @param fieldName 要校验的字段
	 * @param fieldValue 要校验字段的值
	 * @param viewName 界面显示的名称
	 * @return 存在：null, 不存在：错误提示信息(xxx(xxx)不存在)
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	public <T extends BaseEntity> String validateNotExist(Class<T> clazz, String pkColumn, Object pkValue, String fieldName, Object fieldValue, String viewName) throws Exception {
		if(StringUtils.isEmpty(pkColumn)) pkColumn = "id";
		
		List<Where> whereList = new ArrayList<Where>();
		whereList.add(Where.eq(fieldName, fieldValue));
		if(pkValue != null) {
			whereList.add(Where.notEq(pkColumn, pkValue));
		}
		List result = baseService.getListWithMap(clazz, whereList, Parameter.queryId());
		if(result == null || result.size() <= 0) return null;
		
		return this.getMessage("validate.exist", new String[]{viewName, fieldValue.toString()});
	}
	
	
	public Map<String, String> fileUpload(MultipartHttpServletRequest request, String module, String function) {
		return this.fileUpload(request, module, function, false);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> fileUpload(MultipartHttpServletRequest request, String module, String function, boolean isPrivate) {
		if (request == null) {
			return new HashMap<String, String>();
		}

		Map<String, MultipartFile> fileMap = request.getFileMap();
		if (fileMap == null || fileMap.size() <= 0) {
			return new HashMap<String, String>();
		}

		Map<String, String> resultMap = new HashMap<>();
		for (String field : fileMap.keySet()) {
			MultipartFile file = fileMap.get(field);
			if (file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
				continue;
			}

			DyResponse response = doFileUpload((CommonsMultipartFile) file, module, function, isPrivate);
			if (response == null || response.getStatus() != DyResponse.OK || response.getData() == null || !(response.getData() instanceof Map)) {
				String errorMsg = "Upload file(" + file.getOriginalFilename() + ") fail:";
				resultMap.put("error", errorMsg + (response == null ? "" : (String) response.getDescription()));

				return resultMap;
			} else {
				Map<String, Object> map = (Map<String, Object>) response.getData();
				resultMap.put(field, (String) map.get("fileurl"));
			}
		}

		return resultMap;
	}
	
	/**
	 * LOgo公有
	 * @param request
	 * @param module
	 * @param function
	 * @param isPrivate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> fileUploadLogo(String field,MultipartFile file,String module, String function, boolean isPrivate) {

		Map<String, String> resultMap = new HashMap<>();
		if (file == null || file.getSize() <= 0 || StringUtils.isBlank(file.getOriginalFilename())) {
			return resultMap;
		}

		DyResponse response = doFileUpload((CommonsMultipartFile) file, module, function, isPrivate);
		if (response == null || response.getStatus() != DyResponse.OK || response.getData() == null
				|| !(response.getData() instanceof Map)) {
			String errorMsg = "Upload file(" + file.getOriginalFilename() + ") fail:";
			resultMap.put("error", errorMsg + (response == null ? "" : (String) response.getDescription()));

			return resultMap;
		} else {
			Map<String, Object> map = (Map<String, Object>) response.getData();
			resultMap.put(field, (String) map.get("fileurl"));
		}

		return resultMap;
	}
	
	private DyResponse doFileUpload(CommonsMultipartFile file, String module, String function, boolean isPrivate) {
		DyResponse response = new DyResponse();
		try {
			DiskFileItem fileItem = (DiskFileItem) file.getFileItem();
			
			response = DyHttpClient.doImageUpload(module, function, file.getOriginalFilename(), fileItem.getStoreLocation(), isPrivate);
		} catch (Exception e) {
			logger.error("File upload error:", e);
			response.setStatus(DyResponse.ERROR);
			response.setDescription(e.getMessage());
		}
		
		return response;
	}
	
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param value 值
	 */
	public void addWhereCondition(List<Where> whereList, String column, Object value) throws Exception {
		this.addWhereCondition(whereList, column, Where.EQ, value, true);
	}
	
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param condition 查询条件(=,>,<,>等)
	 * @param value 值
	 */
	public void addWhereCondition(List<Where> whereList, String column, String condtion, Object value) throws Exception {
		this.addWhereCondition(whereList, column, condtion, value, true);
	}
	
	/**
	 * 添加where条件到whereList
	 * @param whereList
	 * @param column 数据库字段名
	 * @param condition 查询条件(=,>,<,>等)
	 * @param value 值
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 */
	public void addWhereCondition(List<Where> whereList, String column, String condtion, Object value, boolean isDateNeedCovert) throws Exception {
		if(StringUtils.isEmpty(column) || value == null) return;
		if(value instanceof String && StringUtils.isEmpty(value.toString())) return;
		
		if(whereList == null) whereList = new ArrayList<Where>();
		if(isDateNeedCovert && value instanceof Date) value = DateUtil.convert((Date) value);
		whereList.add(new Where(column, value, condtion));
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, true);
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param formatType 格式化类型(date/datetime),默认为date
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, String formatType) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, true);
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, boolean isDateNeedCovert) throws Exception {
		this.addAndWhereCondition(whereList, column, startValue, endValue, isDateNeedCovert, "date");
	}
	
	/**
	 * 添加and条件，如：add_time > startValue and add_time < endValue
	 * @param whereList
	 * @param column 数据库字段名
	 * @param startValue
	 * @param endValue
	 * @param isDateNeedCovert 时间是否需要转为long，默认为true
	 * @param formatType 格式化类型(date/datetime),默认为date
	 */
	public void addAndWhereCondition(List<Where> whereList, String column, Object startValue, Object endValue, boolean isDateNeedCovert, String formatType) throws Exception {
		if(StringUtils.isEmpty(column)) return;
		
		if(whereList == null) whereList = new ArrayList<Where>();
		
		List<Where> ands = new ArrayList<Where>();
		if(startValue != null) {
			if(isDateNeedCovert && startValue instanceof Date) {
				if("date".equals(formatType)) {
					String startDate = DateUtil.dateFormat((Date) startValue) + " 00:00:00";
					startValue = DateUtil.convert(startDate);
				} else {
					startValue = DateUtil.convert((Date) endValue);
				}
			}
			ands.add(new Where(column, startValue, Where.GE));
		}
		if(endValue != null) {
			if(isDateNeedCovert && endValue instanceof Date) {
				if("date".equals(formatType)) {
					String endDate = DateUtil.dateFormat((Date) endValue) + " 23:59:59";
					endValue = DateUtil.convert(endDate);
				} else {
					endValue = DateUtil.convert((Date) endValue);
				}
			}
			ands.add(new Where(column, endValue, Where.LE));
		}
		if(ands.size() > 0) whereList.add(Where.list(ands));
	}
	
	public String getBasePath() {
		HttpServletRequest request = getRequest();
		
		StringBuffer basePath = new StringBuffer();
		basePath.append(request.getScheme()).append("://").append(request.getServerName());
		if(request.getServerPort() != 80) 
		basePath.append(":").append(request.getServerPort());
		basePath.append(request.getContextPath());
		
		return basePath.toString();
	}
}