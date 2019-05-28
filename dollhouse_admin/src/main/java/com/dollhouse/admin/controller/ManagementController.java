package com.dollhouse.admin.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.admin.entity.GoodsDetial;
import com.dollhouse.admin.entity.Lunbo;
import com.dollhouse.admin.entity.Menu;
import com.dollhouse.admin.interceptor.aspect.OperationAnnotation;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.Option;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.DataConvertUtil;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.core.utils.OptionUtil;
import com.dollhouse.core.utils.RequestUtil;
import com.dollhouse.core.utils.SecurityUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.core.utils.ToMD5;
import com.dollhouse.entity.comm.AdminInfo;
import com.dollhouse.entity.comm.AdminMenuLevel;
import com.dollhouse.entity.comm.DollMenu;
import com.dollhouse.entity.comm.DollName;
import com.dollhouse.entity.comm.DollOrder;
import com.dollhouse.entity.comm.DollType;
import com.dollhouse.entity.comm.domain.AdminInfoDomain;
import com.dollhouse.entity.comm.domain.AdminMenuLevelDomain;
import com.dollhouse.entity.comm.domain.DollMenuDomain;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.entity.comm.domain.DollOrderDomain;
import com.dollhouse.entity.comm.domain.DollTypeDomain;
import com.dollhouse.entity.comm.domain.MbMemberDomain;
import com.dollhouse.service.comm.CommonService;

@Controller
public class ManagementController extends AdminBaseController {
	@Autowired
	private BaseService baseService;
	@Autowired
	private CommonService commonService;
	@Autowired
	protected OptionUtil optionUtil;
	@RequestMapping(value="/system/admin/{path}", method=RequestMethod.GET)
	public ModelAndView toAddAdmin(@PathVariable("path")String path, Long id) {
		AdminInfo admin = null;
		if(id != null) {
			try {
				admin = baseService.getById(
					AdminInfo.class,
					id,
					new String[]{AdminInfoDomain.ID,AdminInfoDomain.ADMIN_NAME,AdminInfoDomain.ADMIN_EMAIL, AdminInfoDomain.ADMIN_PHONE, AdminInfoDomain.ADMIN_GRADE}
				);
			} catch (DaoException e) {
				logger.error("Get admin category by id fail:", e);
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("admin", admin);
		resultMap.put("type", getAdminCategory());
		resultMap.put("status", optionUtil.getAdminStatus());
		resultMap.put("path", path);
		return createSuccessModelAndView("system-management/add", resultMap);
	}
	
	private List<Option> getAdminCategory() {
		return optionUtil.generateOption(AdminMenuLevel.class, Collections.singletonList(Where.eq(AdminMenuLevelDomain.STATUS, 1)));
	}
	
	@ResponseBody
	@RequestMapping(value="/system/admin/submit", method=RequestMethod.POST)
	@OperationAnnotation(moduleName = "管理员", operation = "#operation#管理员(#name#)成功")
	public DyResponse addAdmin(AdminInfo admin,HttpServletRequest request) {
		boolean isUpdate = admin.getId() != null;
		admin.setAdminPassword(RequestUtil.getString(request, "adminPassword", null));
		admin.setAdminAddTime(DateUtil.getCurrentTime());
		System.err.println(admin.getStatus());
		//Null validate
		String errorMsg = validateNull(new String[]{"名称", "邮箱", "电话", "状态", isUpdate ? null : "密码"},  new Object[]{admin.getAdminName(),admin.getStatus(), admin.getAdminEmail(), admin.getAdminPhone(), admin.getAdminPassword(), admin.getAdminGrade()});
		if(StringUtils.isNotBlank(errorMsg)) {
			return createErrorJsonResponse(errorMsg);
		}
		
		//Exist validate
		try {
			errorMsg = validateExist(AdminInfo.class, AdminInfoDomain.ID, admin.getId(), AdminInfoDomain.ADMIN_NAME, admin.getAdminName(), "名称", null);
		} catch (Exception e) {
			logger.error("Admin name unique validate fail:", e);
		}
		if(StringUtils.isNotBlank(errorMsg)) {
			return createErrorJsonResponse(errorMsg);
		}
		
		//Phone validate
		if(admin.getAdminPhone() != null && !StringUtils.checkPhone(admin.getAdminPhone().toString())) {
			return createErrorJsonResponse("手机号无效");
		}
		//Encrypt password
		if(StringUtils.isNotBlank(admin.getAdminPassword())) {
			ToMD5 md5=new  ToMD5();
			String md5Password=md5.toMD5(admin.getAdminPassword());
			admin.setAdminPassword(md5Password);
		} else {
			admin.setAdminPassword(null);
		}
		
		long result = 0;
		try {
			if(isUpdate) {
				admin.setId(getSessionUserId());
				result = baseService.updateById(admin, admin.getId());
			} else {
				System.out.println(admin.getAdminPassword());
				
				result=baseService.insert(admin);
			}
		} catch (DaoException e) {
			logger.error("Add/Edit admin fail", e);
		}
		DyResponse response = null;
		if(result <= 0) {
			response = createErrorJsonResponse(isUpdate ? "修改失败" : "添加失败");
		}else{
			response = createSuccessJsonResponse(null, isUpdate ? "修改成功" : "添加成功");
		}
		//添加后台日志
		this.setAdminLogAtrribute(response,new String[]{"operation","name"},new Object[]{isUpdate ? "修改" : "添加",admin.getAdminName()});
		return response;
	}
	
	@RequestMapping(value="/system/admin/editPassword", method=RequestMethod.GET)
	public ModelAndView editPassword(Long id) {
		AdminInfo admin = null;
		if(id != null) {
			try {
				admin = baseService.getById(AdminInfo.class,id,null);
			} catch (DaoException e) {
				logger.error("Get admin category by id fail:", e);
			}
		}
		
		return createSuccessModelAndView("system-management/editPassword", admin);
	}
	
	@ResponseBody
	@RequestMapping(value = "/system/admin/savePassword", method = RequestMethod.POST)
	@OperationAnnotation(moduleName = "管理员", operation = "修改管理员(#name#)密码成功")
	public DyResponse savePassword(Long id,String adminPassword, String confirmPassword) throws Exception {
		String errorMsg = validateNull(new String[]{"adminPassword","confirmPassword"}, new String[] {"密码","确认密码"});
		if(StringUtils.isNotBlank(errorMsg)) {
			return createErrorJsonResponse(errorMsg);
		}
		
		if(!adminPassword.equals(confirmPassword))return createErrorJsonResponse("两次密码不一致");
		//判断用户是否存在
		List<Where> whereList= new ArrayList<Where>();
		whereList.add(Where.eq(MbMemberDomain.ID, id));
		AdminInfo admin = baseService.getOne(AdminInfo.class, whereList);
		if(admin == null) return createErrorJsonResponse("用户不存在");
		ToMD5 md5=new  ToMD5();
		String md5Password=md5.toMD5(adminPassword);
		admin.setAdminPassword(md5Password);
		baseService.updateById(admin, admin.getId());
		DyResponse response = createSuccessJsonResponse(null);
		//添加后台日志
		this.setAdminLogAtrribute(response,new String[]{"name"},new Object[]{admin.getAdminName()});
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/system/admin/delete", method=RequestMethod.POST)
	@OperationAnnotation(moduleName = "管理员", operation = "删除管理员(#name#)成功")
	public DyResponse delAdmin(Long id) {
		AdminInfo admin = null;
		try {
			admin = baseService.getById(AdminInfo.class, id, null);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = baseService.deleteById(AdminInfo.class, id);
		} catch (DaoException e) {
			logger.error("Delete admin fail", e);
		}
		DyResponse response = null;
		if(result <= 0) {
			response = createErrorJsonResponse("删除失败");
		}else{
			response = createSuccessJsonResponse(null, "删除成功");
		}
		//添加后台日志
		this.setAdminLogAtrribute(response,new String[]{"name"},new Object[]{admin.getAdminName()});
		return response;
	}
	
	@RequestMapping(value={"/system/admin/page"},method=RequestMethod.GET)
	public ModelAndView managerInfo(Integer page){
		return createSuccessModelAndView("system-management/page",
				new DataConvertUtil(getAdmin(page == null ? 0 : page))
					.setStatus(AdminInfoDomain.ADMIN_GRADE, optionUtil.getAdminStatus())
					.convert()
			);
	}
	private Page<AdminInfo> getAdmin(Integer page) {
		Page<AdminInfo> adminPage = null;
		try {
			adminPage = baseService.getPage(
					AdminInfo.class,
					Collections.singletonList(Where.gt(AdminInfoDomain.ID, 0L)),
					page + 1,
					Parameter.pageSize(10),
					Parameter.queryColumn(new String[]{AdminInfoDomain.ID, AdminInfoDomain.ADMIN_NAME, AdminInfoDomain.ADMIN_PASSWORD,AdminInfoDomain.ADMIN_EMAIL,AdminInfoDomain.ADMIN_PHONE, AdminInfoDomain.ADMIN_GRADE}));
		} catch (DaoException e) {
			logger.error("Get admin info fail:", e);
		}
		
		return adminPage;
	}
/*//	添加用户
	@RequestMapping(value={"/system/admin/add"},method=RequestMethod.GET)
	public ModelAndView addAdmin(){
		return createSuccessModelAndView("system-management/add", null);
	}
//	修改
	@RequestMapping(value={"/system/admin/edit"},method=RequestMethod.GET)
	public ModelAndView adminChange(){
		return createSuccessModelAndView("system-management/add", null);
		
	}
//	修改密码
	@RequestMapping(value={"/system/admin/editPassword"},method=RequestMethod.GET)
	public ModelAndView adminPasswordChange(){
		return createSuccessModelAndView("system-management/editPassword", null);
		
	}*/
/**
 * 以下为菜单管理
 */
	@RequestMapping(value={"/system/menu/leftMenu"},method=RequestMethod.GET)
	public  ModelAndView  toleftMenu(){
		List<Where> whereList=new ArrayList<>();
		whereList.add(Where.eq(DollMenuDomain.MENU_LOCATION, "2"));
		List<Menu> leftMenu=new ArrayList<>();
		try {
			List<DollMenu> firMenu = baseService.getList(DollMenu.class, whereList, Parameter.queryColumn(new String[]{DollMenuDomain.ID,DollMenuDomain.NAME}));
			for (DollMenu dollMenu : firMenu) {
				Menu menu=new Menu();
				menu.setId(dollMenu.getId());
				menu.setName(dollMenu.getName());
				List<Where> whereList1=new ArrayList<>();
				whereList1.add(Where.eq(DollMenuDomain.PID, dollMenu.getId()));
				List<DollMenu> secMenus = baseService.getList(DollMenu.class, whereList1, Parameter.queryColumn(new String[]{DollMenuDomain.ID,DollMenuDomain.NAME,DollMenuDomain.MENU_IMG_URL}));
				List<Menu> secMenuList=new ArrayList<>();
				for (DollMenu dollMenu2 : secMenus) {
					Menu secMenu=new Menu();
					secMenu.setId(dollMenu2.getId());
					secMenu.setName(dollMenu2.getName());
					secMenu.setUrl(dollMenu2.getMenuImgUrl());
					secMenuList.add(secMenu);
				}
				menu.setSubMenu(secMenuList);
				leftMenu.add(menu);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessModelAndView("menu-management/left-menu", leftMenu);
		
	}
	@RequestMapping(value={"system/secMenu/delete"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse delSecMenu(Long id){
		try {
			baseService.deleteById(DollMenu.class, id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return createSuccessJsonResponse(null);
		
	}
//	跳转商品查找界面
	@RequestMapping(value={"/system/menu/search"},method=RequestMethod.GET)
	public ModelAndView search(){
		return createSuccessModelAndView("menu-management/search-goods", null);
	}	
//	 查询
	  @RequestMapping(value={"/admin/menuManager/goodsSearch"},method=RequestMethod.GET)
	    public ModelAndView searchGoods(Integer page,DollName dollName){
//			 调用方法page
			Page dollPage = page(page, dollName);
			return createSuccessModelAndView("menu-management/search-page",
					new DataConvertUtil(dollPage)
					.setStatus(DollNameDomain.DOLL_TYPE,optionUtil.getGoodsType()).convert());
	    }
	  @RequestMapping(value={"/admin/MenuManager/searchDetial"},method=RequestMethod.GET)
		 public ModelAndView searchDetial(Long id,Model model){
//			 调用方法detial
			GoodsDetial detial = detial(id);
			ModelAndView view=new ModelAndView();
			view.setViewName("menu-management/search-detial");
			view.addObject("data", detial);
			view.addObject("type",optionUtil.getMenuType() );
			return view;
		 }
//	  添加导航子菜单
	  @RequestMapping(value={"/admin/secMenu/add"},method=RequestMethod.POST)
	  @ResponseBody
	  public DyResponse secMenu(Long linkId,Long type,String url,BigDecimal lowerPrice,String name){
		DollMenu dollMenu=new DollMenu();
		dollMenu.setLinkId(linkId);
		dollMenu.setPid(type);
		dollMenu.setMenuImgUrl(url);
		dollMenu.setMenuPrice(lowerPrice);
		dollMenu.setName(name);
		dollMenu.setMenuLocation("2-2");
		try {
			baseService.insert(dollMenu);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		  
	  }
/**
 * 轮播图管理
 * @return
 * @throws DaoException 
 */
	  @RequestMapping(value={"/system/menu/lunBo"},method=RequestMethod.GET)
	  public ModelAndView lunbo() throws DaoException{
		 List<Where> whereList = new ArrayList<>();
		 whereList.add(Where.eq(DollMenuDomain.MENU_LOCATION, "2-2"));
		 whereList.add(Where.eq(DollMenuDomain.PID, "0"));
		List<DollMenu> lbList = baseService.getList(DollMenu.class, whereList, Parameter.queryColumn(new String[]{DollMenuDomain.ID,DollMenuDomain.MENU_IMG_URL,DollMenuDomain.NAME}));
		return createSuccessModelAndView("menu-management/lunbo-pic", lbList);
		  
	  }
	  @RequestMapping(value={"/admin/menuManager/lunboPage"},method=RequestMethod.GET)
	  public  ModelAndView toLunbo(HttpServletRequest request){
		 Long picId = RequestUtil.getLong(request, "picId", null);
		 Lunbo lunbo=new Lunbo();
		 lunbo.setPicId(picId);
		 setSessionAttribute("lunbo", lunbo);
		return createSuccessModelAndView("menu-management/lunbo/lunbo-search", null);
		  
	  }
//		 查询
		  @RequestMapping(value={"/admin/menuManager/lunboSearch"},method=RequestMethod.GET)
		    public ModelAndView lunboGoods(Integer page,DollName dollName){
//				 调用方法page
				Page dollPage = page(page, dollName);
				return createSuccessModelAndView("menu-management/lunbo/lunbo-page",
						new DataConvertUtil(dollPage)
						.setStatus(DollNameDomain.DOLL_TYPE,optionUtil.getGoodsType()).convert());
		    }
		  @RequestMapping(value={"/admin/MenuManager/lunboDetial"},method=RequestMethod.GET)
			 public ModelAndView lunboDetial(Long id,Model model){
			 
//				 调用方法detial
				GoodsDetial detial = detial(id);
				ModelAndView view=new ModelAndView();
				view.setViewName("menu-management/lunbo/lunbo-detial");
				view.addObject("data", detial);
				view.addObject("type",optionUtil.getMenuType() );
				return view;
			 }
		  @RequestMapping(value={"/admin/lunbo/update"},method=RequestMethod.POST)
		  @ResponseBody
		  public DyResponse llunboUpdate(Long linkId,String url,String name){
			  DollMenu dollMenu=new DollMenu();
			  dollMenu.setLinkId(linkId);
			  dollMenu.setMenuImgUrl(url);
			  dollMenu.setName(name);
			  dollMenu.setMenuLocation("2-2");
			  Lunbo lb = (Lunbo) getSessionAttribute("lunbo");
			  try {
				baseService.updateById(dollMenu, lb.getPicId(), false);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			  
		  }
		  @RequestMapping(value={"/system/order/untreated"},method=RequestMethod.GET)
		  public ModelAndView toOrderUntreated(){
			  return createSuccessModelAndView("order-manager/order-untreated", null);
		  }
//		  未处理订单
		  @RequestMapping(value={"/system/search/untreated"},method=RequestMethod.GET)
		  public  ModelAndView  orderUntreated(Integer page,String goodsName,String receiveName,String startTime,String endTime,HttpServletRequest request) throws Exception{
			  Page<DollOrder> dollOrderPage=null;
			  List<Where> whereList=new ArrayList<>();
			try {
				if(!StringUtils.isEmpty(goodsName)){
					 goodsName=StringUtils.coverToUtf8(goodsName);
					whereList.add(Where.like(DollOrderDomain.DOLL_NAME, goodsName));
				}
				if(!StringUtils.isEmpty(receiveName)){
					receiveName=StringUtils.coverToUtf8(receiveName);
					whereList.add(Where.like(DollOrderDomain.RECEIVE_NAME, receiveName));
				}
				whereList.add(Where.eq(DollOrderDomain.HANDLE_RESULT, "0"));
				addAndWhereCondition(whereList,DollOrderDomain.BUY_TIME, DateUtil.convert(startTime+" 00:00:00"), DateUtil.convert(endTime+" 23:59:59"));
				dollOrderPage=baseService.getPage(DollOrder.class,
						whereList,
						page==null?1:page+1 ,
						Parameter.queryColumn(new String[]{DollOrderDomain.ID,DollOrderDomain.DOLL_NAME,DollOrderDomain.DOLL_TYPE,
								DollOrderDomain.DOLL_COLOR,DollOrderDomain.DOLL_NUM,DollOrderDomain.DOLL_TOTAL,DollOrderDomain.DOLL_TOTAL,
								DollOrderDomain.LEAVE_MESSAGE,DollOrderDomain.RECEIVE_ADDRESS_PROVINCE,DollOrderDomain.RECEIVE_ADDRESS_CITY,
								DollOrderDomain.RECEIVE_ADDRESS_AREA,DollOrderDomain.RECEIVE_ADDRESS_DETIAL}));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("orderType", optionUtil.getOrderType());
			return createSuccessModelAndView("order-manager/order-untreated-page", dollOrderPage);
			  
		  }
		  @RequestMapping(value={"/admin/order/orderResult"},method=RequestMethod.GET)
		  public  ModelAndView orderResult(Long id){
			  DollOrder dollOrder=new DollOrder();
			  dollOrder.setHandleResult(1);
			  try {
				baseService.updateById(dollOrder, id, false);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return createSuccessModelAndView("order-manager/order-untreated", null);
			  
		  }
		  
//		  已处理订单
		  @RequestMapping(value={"/system/order/treated"},method=RequestMethod.GET)
		  public ModelAndView toOrderTreated(){
			  return createSuccessModelAndView("order-manager/order-treated", null);
		  }
		  @RequestMapping(value={"/system/search/treated"},method=RequestMethod.GET)
		  public  ModelAndView  orderTreated(Integer page,String goodsName,String receiveName,String startTime,String endTime,HttpServletRequest request) throws Exception{
			  Page<DollOrder> dollOrderPage=null;
			  List<Where> whereList=new ArrayList<>();
			try {
				if(!StringUtils.isEmpty(goodsName)){
					 goodsName=StringUtils.coverToUtf8(goodsName);
					whereList.add(Where.like(DollOrderDomain.DOLL_NAME, goodsName));
				}
				if(!StringUtils.isEmpty(receiveName)){
					receiveName=StringUtils.coverToUtf8(receiveName);
					whereList.add(Where.like(DollOrderDomain.RECEIVE_NAME, receiveName));
				}
				whereList.add(Where.eq(DollOrderDomain.HANDLE_RESULT, "0"));
				addAndWhereCondition(whereList,DollOrderDomain.BUY_TIME, DateUtil.convert(startTime+" 00:00:00"), DateUtil.convert(endTime+" 23:59:59"));
				dollOrderPage=baseService.getPage(DollOrder.class,
						whereList,
						page==null?1:page+1 ,
						Parameter.queryColumn(new String[]{DollOrderDomain.ID,DollOrderDomain.DOLL_NAME,DollOrderDomain.DOLL_TYPE,
						DollOrderDomain.DOLL_COLOR,DollOrderDomain.DOLL_NUM,DollOrderDomain.DOLL_TOTAL,DollOrderDomain.DOLL_TOTAL,
						DollOrderDomain.LEAVE_MESSAGE,DollOrderDomain.RECEIVE_ADDRESS_PROVINCE,DollOrderDomain.RECEIVE_ADDRESS_CITY,
						DollOrderDomain.RECEIVE_ADDRESS_AREA,DollOrderDomain.RECEIVE_ADDRESS_DETIAL}));
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return createSuccessModelAndView("order-manager/order-treated-page", dollOrderPage);
			  
		  }
		  
//	    获取分页列表的信息
	    public Page page(Integer page,DollName dollName){
	    	 Page<DollName> dollPage=null;
			 try {
				 List<Where> whereList=new ArrayList<>();
				 if(!StringUtils.isEmpty(dollName.getDollGoodsName())){
					 whereList.add(Where.like(DollNameDomain.DOLL_GOODS_NAME, dollName.getDollGoodsName()));
				 }
				 String organcode=null;
				 if(dollName.getDollOrgancode()!=null){
					 organcode=String.valueOf(dollName.getDollOrgancode());
					 whereList.add(Where.eq(DollNameDomain.DOLL_ORGANCODE, organcode));
				 }
				if(whereList.size()<=0){
					 whereList.add(Where.ge(DollNameDomain.DOLL_STATUS, 0));
				 }
				dollPage=baseService.getPage(DollName.class,
						whereList,
						page==null?1:page+1 ,
								Parameter.queryColumn(new String[]{DollNameDomain.ID,DollNameDomain.DOLL_GOODS_NAME,DollNameDomain.DOLL_ORGANCODE,DollNameDomain.DOLL_TYPE}),
								Parameter.orderBy(OrderBy.asc(DollNameDomain.ID))
				);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dollPage;
	    }
//	    获取详情
	    public GoodsDetial detial(Long id){
	    	 List<Where> whereList=new ArrayList<>();
			 whereList.add(Where.eq(DollNameDomain.ID, id));
			 DollName goods = new DollName();
			try {
				goods = baseService.getOne(DollName.class, 
						 whereList,
						 Parameter.queryColumn(new String[]{DollNameDomain.ID,DollNameDomain.DOLL_GOODS_NAME,DollNameDomain.DOLL_ORGANCODE,
								 DollNameDomain.DOLL_IMG_URL,DollNameDomain.DOLL_COLOR,DollNameDomain.DOLL_TYPE,DollNameDomain.DOLL_STATUS,
								 DollNameDomain.LOWER_PRICE,DollNameDomain.HEIGHER_PRICE}));
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 GoodsDetial detial=new GoodsDetial();
//				商品名称
				detial.setName(goods.getDollGoodsName());
//				商品ID
				detial.setId(goods.getId());
//				商品编号
				detial.setOrganCode( goods.getDollOrgancode());
//				颜色部分
				detial.setColor(goods.getDollColor());
//				最低价
				detial.setLowerPrice(goods.getLowerPrice());
//				最高价
				detial.setHeigherPrice(goods.getHeigherPrice());
//				商品状态
				detial.setStatus(goods.getDollStatus());
//				商品描述
				detial.setDespriton(goods.getDespriton());
//				图片部分
				String filePath =  goods.getDollImgUrl();
				String[] paths=filePath.split(",");
				List<GoodsDetial> goodsImgUrl=new ArrayList<>();
				for (String aa : paths) {
					GoodsDetial subdetial=new GoodsDetial();
					subdetial.setURL(aa);
					goodsImgUrl.add(subdetial);
				}
				detial.setImgUrl(goodsImgUrl);
//				型号部分
				List<GoodsDetial> types=new ArrayList<>();
				List<DollType> typeList = new ArrayList<>();
				try {
					List<Where> whereList1=new ArrayList<>();
					whereList1.add(Where.eq(DollTypeDomain.DOLL_NAME_ID, id));
					typeList = baseService.getList(DollType.class,
							whereList1,
							Parameter.queryColumn(new String[]{DollTypeDomain.TYPE_ID,DollTypeDomain.DOLL_NAME_ID,DollTypeDomain.DOLL_TYPE_NAME,
									DollTypeDomain.DOLL_TYPE_NUM,DollTypeDomain.DOLL_TYPE_PRICE}));
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (typeList.size()>0) {
					for (DollType good : typeList) {
						GoodsDetial subdetial=new GoodsDetial();
						subdetial.setId(good.getTypeId());
						subdetial.setPrice(good.getDollTypePrice());
						subdetial.setType(good.getDollTypeName());
						types.add(subdetial);
					}
					detial.setTypes(types);
				}
			return detial;
	    	
	    }
	
}
