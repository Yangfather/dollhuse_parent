package com.dollhouse.admin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.admin.entity.Menu;
import com.dollhouse.admin.entity.Sales;
import com.dollhouse.admin.verifyCode.VerifyCodeServlet;
import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.OptionUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.core.utils.ToMD5;
import com.dollhouse.entity.comm.AdminInfo;
import com.dollhouse.entity.comm.domain.AdminInfoDomain;
import com.dollhouse.service.comm.CommonService;
import com.dollhouse.service.statistical.UserInfoService;

@Controller
public class AdminController extends AdminBaseController{
	@Autowired
	private BaseService baseService;
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	protected OptionUtil optionUtil;
	
	@RequestMapping(value={"/","/system/index","/common/login"},method=RequestMethod.GET)
	public ModelAndView toIndex(){
		if(getSessionAttribute(Constant.SESSION_USER)==null){
			return createSuccessModelAndView("index", null);
		}
		List<Map<String, Object>> salesList = userInfoService.saleStatistics();
		Sales sales=new Sales();
//		已处理
		 Object handleNum = salesList.get(0).get("totalNum");
		sales.setHandleNum(handleNum);
		BigDecimal handleMoney=(BigDecimal) salesList.get(0).get("totalMoney");
		sales.setHandleMoney(handleMoney);
		Long handleOrder=(Long) salesList.get(0).get("totalOrder");
		sales.setHandleOrder(handleOrder);
//		未处理
		Object unHandleNum= salesList.get(1).get("totalNum");
		sales.setUnHandleNum(unHandleNum);
		BigDecimal unHandleMoney=(BigDecimal) salesList.get(1).get("totalMoney");
		sales.setUnHandleMoney(unHandleMoney);
		Long unHandleOrder=(Long) salesList.get(1).get("totalOrder");
		sales.setUnHandleOrder(unHandleOrder);
		setSessionAttribute(Constant.SESSION_MENU, getHeaderInfo());
		return createSuccessModelAndView("system/homePage", sales);
		
	}
	
	@ResponseBody
	@RequestMapping(value={"/public/login"},method=RequestMethod.POST)
	public DyResponse login(String adminName,String adminPassword ,String code){
		String errorMsg=validateNull(
				new String[]{getMessage("1b1.adminName"),getMessage("1b1.adminPassword"),"验证码"},
				new String[]{adminName,adminPassword,code});
		if(StringUtils.isNotBlank(errorMsg)){
			return createErrorJsonResponse(errorMsg);
		}
//		验证码校验
		String error=validateSessionVerifyCode(code);
		if(StringUtils.isNotBlank(error)){
			return createErrorJsonResponse(error);
		}
		this.removeSessionAttribute(Constant.SESSION_VERIFY_CODE);
		AdminInfo admin=null;
		List<Where> whereList=new ArrayList<>();
		ToMD5 md5=new  ToMD5();
		String md5Password=md5.toMD5(adminPassword);
		whereList.add(Where.eq(AdminInfoDomain.ADMIN_NAME, adminName));
		whereList.add(Where.eq(AdminInfoDomain.ADMIN_PASSWORD, md5Password));
		System.out.println(adminName+";"+md5Password);
		try {
			admin=baseService.getOne(AdminInfo.class, 
					whereList, 
					Parameter.queryColumn(new String[]{
							AdminInfoDomain.ID,
							AdminInfoDomain.ADMIN_NAME,
							AdminInfoDomain.ADMIN_GRADE,
							AdminInfoDomain.STATUS
					}));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(admin==null){
			return createErrorJsonResponse(getMessage("login.errorUserOrPassword"));
		}
		if (admin.getStatus()!= 1) {
			return createErrorJsonResponse(getMessage("login.userLocked"));
		}
		setSessionAttribute(Constant.SESSION_USER, admin);
		setSessionAttribute(Constant.SESSION_NAME, admin.getAdminName());
		System.out.println(admin.getAdminName());
		DyResponse response=createSuccessJsonResponse(null);
		this.setAdminLogAtrribute(response, new String[]{"name","touxiang"}, new Object[]{admin.getAdminName(),admin.getAdminIcon()});
		return response;
		
	}
	@RequestMapping(value={"/admin/sessionOut"})
	public ModelAndView sessionOut(){
		removeSessionAttribute(Constant.SESSION_USER);
		return createSuccessModelAndView("system/homePage", null);
		
	}
	
	
	
	@RequestMapping(value={"/public/admin/getCode"},method=RequestMethod.GET)
	public void getCode(HttpServletRequest request,HttpServletResponse response){
		   VerifyCodeServlet i = new VerifyCodeServlet();  
	        //发送图片   
	        BufferedImage bi=i.create();
	        
	    	String code = i.getCodeV().toString(); 
	        
	        request.getSession().setAttribute("code", code);
	       
			try {
				ImageIO.write(bi, "JPEG", response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
//	跳转头部导航栏
	@RequestMapping(value={"/system/menu/topMenu"},method=RequestMethod.GET)
	public ModelAndView headMenu(){
		return createSuccessModelAndView("menu-management/head-menu", null);
		
	}
	//提供跳转增加商品页面
	@RequestMapping(value={"/system/goods/addGoods"},method=RequestMethod.GET)
	public ModelAndView returnPage(){
			return createSuccessModelAndView("goods-manager/add-goods", optionUtil.getGoodsType());
	}
//	跳转商品修改界面
	@RequestMapping(value={"/system/goods/change"},method=RequestMethod.GET)
	public ModelAndView change(){
		System.out.println("请求商品修改");
		return createSuccessModelAndView("goods-manager/change-goods", null);
	}
//	跳转商品删除界面
	@RequestMapping(value={"/system/goods/delete"},method=RequestMethod.GET)
	public ModelAndView delete(){
		return createSuccessModelAndView("goods-manager/delete-goods", null);
	}
//	跳转商品查找界面
	@RequestMapping(value={"/system/goods/search"},method=RequestMethod.GET)
	public ModelAndView search(){
		return createSuccessModelAndView("goods-manager/search-goods", null);
	}	
//	跳转客服聊天界面
	@RequestMapping(value={"/system/order/chat"},method=RequestMethod.GET)
	public ModelAndView chat(){
		return createSuccessModelAndView("order-manager/chat-page", null);
	}

//	跳转管理员页面
	@RequestMapping(value={"/system/admin/info"},method=RequestMethod.GET)
	public ModelAndView toManagerInfo(){
		return createSuccessModelAndView("system-management/manager-info", null);
		
	}
//	跳转网站设置
	@RequestMapping(value={"/system/admin/create"},method=RequestMethod.GET)
	public ModelAndView toSheZhi(){
		return createSuccessModelAndView("system-management/webContent/content-list", null);
		
	}
	
//	跳转售后订单页面
	@RequestMapping(value={"/system/order/bought"},method=RequestMethod.GET)
	public ModelAndView bought(){
		return createSuccessModelAndView("order-manager/bought-order", null);
		
	}
	
//	跳转类别销量页面
	@RequestMapping(value={"/system/sales/type"},method=RequestMethod.GET)
	public ModelAndView typeSales(){
		return createSuccessModelAndView("sales-management/type-sales", null);
	}
	
//	跳转消金统计页面
	@RequestMapping(value={"/system/sales/money"},method=RequestMethod.GET)
	public ModelAndView moneySales(){
		return createSuccessModelAndView("sales-management/money-sales", null);
	}
	
//	跳转地域销量页面
	@RequestMapping(value={"/system/sales/area"},method=RequestMethod.GET)
	public ModelAndView areaSales(){
		return createSuccessModelAndView("sales-management/area-sales", null);
	}
}
