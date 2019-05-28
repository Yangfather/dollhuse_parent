package com.dollhouse.user.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.JsonUtils;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.core.utils.ToMD5;
import com.dollhouse.entity.comm.DollMenu;
import com.dollhouse.entity.comm.DollName;
import com.dollhouse.entity.comm.DollStar;
import com.dollhouse.entity.comm.UserInfo;
import com.dollhouse.entity.comm.domain.DollMenuDomain;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.entity.comm.domain.DollStarDomain;
import com.dollhouse.entity.comm.domain.UserInfoDomain;
import com.dollhouse.service.statistical.GoodsInfoService;
import com.dollhouse.user.entity.AllMenu;
import com.dollhouse.user.entity.Menu;


@Controller
public class UserBaseController extends AdminBaseController{
	@Autowired
	protected BaseService baseService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	
	@RequestMapping(value={"/common/login"}, method=RequestMethod.GET)
	public ModelAndView toLogin(){
		return createSuccessModelAndView("admin/login", null);
	}
	@RequestMapping(value={"/common/register"}, method=RequestMethod.GET)
	public ModelAndView toRegister(){
		System.out.println("register");
		return createSuccessModelAndView("admin/register", null);
	}
//	自动补全
	@ResponseBody
	@RequestMapping(value={"/common/autocomplete"}, produces={"text/html;charset=UTF-8;","application/json;"})
	public String toAutocomplete(HttpServletRequest request,HttpServletResponse response){
		List<Object>  data=new ArrayList<>();
		try {
			List<DollName> autocomplete = baseService.getAll(DollName.class, Parameter.queryColumn(new String[]{DollNameDomain.DOLL_GOODS_NAME}));
			
			for (DollName dollName : autocomplete) {
				
				String str=dollName.getDollGoodsName();
				data.add(str);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		String string= JsonUtils.object2JsonString(data);
		return string;
		
	}
	
	
	@RequestMapping(value={"/common/showMenu"}, method=RequestMethod.GET)
	@ResponseBody
	public DyResponse showMenu() throws DaoException{
		AllMenu allMenu=new AllMenu();
//		头部导航栏
		String location="1";
		String location1="1-1";
		allMenu.setTopMenu(getMenuList(location,location1));
//		左侧导航栏
		String location2="2";
		String location3="2-1";
		allMenu.setLeftMenu(getMenuList(location2,location3));
//		轮播图
		List<Where> whereList=new ArrayList<>();
		whereList.add(Where.eq(DollMenuDomain.PID, "0"));
		whereList.add(Where.eq(DollMenuDomain.MENU_STATUS, "1"));
		whereList.add(Where.eq(DollMenuDomain.MENU_LOCATION, "2-2"));
		List<DollMenu> lunboList = baseService.getList(DollMenu.class,
				whereList,
				Parameter.queryColumn(new String[]{DollMenuDomain.MENU_IMG_URL,
						DollMenuDomain.MENU_TYPE,DollMenuDomain.ID,DollMenuDomain.NAME,DollMenuDomain.LINK_ID}));
		List<Menu> lunboMenuList=new ArrayList<>();
		for (DollMenu dollMenu : lunboList) {
			Menu menu=new Menu();
			menu.setMenuImgUrl(dollMenu.getMenuImgUrl());
			menu.setMenuType(dollMenu.getMenuType());
			menu.setId(dollMenu.getId());
			menu.setName(dollMenu.getName());
			menu.setLinkId(dollMenu.getLinkId());
			lunboMenuList.add(menu);
		}
		allMenu.setLunBo(lunboMenuList);
List<Where> whereList3=new ArrayList<>();
whereList.add(Where.gt(DollStarDomain.ID, 0L));
		//		明星单品
		List<DollStar> starList = baseService.getList(DollStar.class,
				whereList3,
				Parameter.limit(10),
				Parameter.queryColumn(new String[]{DollStarDomain.DOLL_NAME,DollStarDomain.DOLL_ORGANCODE,DollStarDomain.DOLL_IMGURL,DollStarDomain.DOLL_LOWERPRICE}),
				Parameter.orderBy(OrderBy.desc(DollStarDomain.ADD_TIME))
				);
		List<Menu> starGoods=new ArrayList<>();
		for (DollStar dollStar : starList) {
			Menu menu=new Menu();
			menu.setName(dollStar.getDollName());
			menu.setMenuImgUrl(dollStar.getDollImgurl());
			menu.setMenuPrice(dollStar.getDollLowerprice());
			menu.setLinkId(Long.valueOf(dollStar.getDollOrgancode()));
			starGoods.add(menu);
		}
		allMenu.setStarMenu(starGoods);
		setSessionAttribute("allMenu", allMenu);
		return createSuccessJsonResponse(null);
	}
	@RequestMapping(value={"/common/indexPage"},method=RequestMethod.GET)
	public DyResponse toIndex(){
		System.out.println("toindex");
		return createSuccessJsonResponse(null);
	}
//	后台登录，前台创建session
	@RequestMapping(value={"/dollhouse/admin/session"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse adminSession(String adminName){
		setSessionAttribute(Constant.SESSION_Admin, adminName);
		DyResponse response=createSuccessJsonResponse(null);
		return response;
		
	}
//	用户登录
	@RequestMapping(value={"/common/loginer"},method=RequestMethod.POST)
	@ResponseBody
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
		UserInfo admin=null;
		List<Where> whereList=new ArrayList<>();
		ToMD5 md5=new  ToMD5();
		String md5Password=md5.toMD5(adminPassword);
		whereList.add(Where.eq(UserInfoDomain.USER_NAME, adminName));
		whereList.add(Where.eq(UserInfoDomain.USER_PASSWORD, md5Password));
		try {
			admin=baseService.getOne(UserInfo.class, 
					whereList, 
					Parameter.queryColumn(new String[]{
							UserInfoDomain.ID,
							UserInfoDomain.USER_NAME,
							UserInfoDomain.USER_PASSWORD
					}));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(admin==null){
			return createErrorJsonResponse(getMessage("login.errorUserOrPassword"));
		}
		setSessionAttribute(Constant.SESSION_USER, admin);
		setSessionAttribute(Constant.SESSION_NAME, admin.getUserName());
		System.out.println( admin.getUserName());
		DyResponse response=createSuccessJsonResponse(null);
		this.setAdminLogAtrribute(response, new String[]{"name","touxiang"}, new Object[]{admin.getUserName(),admin.getUserPassword()});
		return response;
		
	}
	@RequestMapping("/common/returnIndex")
	public ModelAndView login2Index(){
		return createSuccessModelAndView("index", null);
		
	}
//	session销毁
	@RequestMapping("/common/logout")
	public ModelAndView logout(HttpServletRequest request){
		 removeSessionAttribute(Constant.SESSION_USER);
		 removeSessionAttribute(Constant.SESSION_NAME);
		return createSuccessModelAndView("/index", null);
	}
//	用户注册
	@RequestMapping(value={"/common/user/register"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse register(String username,String password ,String email,String phone) throws DaoException{
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(username);
//		md5加密
		ToMD5 md5=new  ToMD5();
		String md5Password=md5.toMD5(password);
		userInfo.setUserPassword(md5Password);
		userInfo.setUserEmail(email);
		userInfo.setUserPhone(phone);
		String data = null;
		List<UserInfo> result1=new ArrayList<>();
		List<UserInfo> result2=new ArrayList<>();
		List<Where> whereList1=new ArrayList<>();
		List<Where> whereList2=new ArrayList<>();
		whereList1.add(Where.eq(UserInfoDomain.USER_EMAIL, email));
		whereList2.add(Where.eq(UserInfoDomain.USER_PHONE, phone));
		result1= baseService.getList(UserInfo.class, whereList1, Parameter.queryColumn(new String[]{UserInfoDomain.USER_EMAIL}));
		result2 = baseService.getList(UserInfo.class, whereList2, Parameter.queryColumn(new String[]{UserInfoDomain.USER_PHONE}));
		if (result1.size()>0) {
			data="该邮箱已注册，请登录或更换邮箱注册";
			return createErrorJsonResponse(getMessage(JsonUtils.object2JsonString(data)));
		}
		if(result2.size()>0){
			
			data="该手机号已注册，请登录或更换手机号注册";
			return createErrorJsonResponse(getMessage(JsonUtils.object2JsonString(data)));
		}
		if(result1.size()==0&&result2.size()==0){
			long result = baseService.insert(userInfo);
			if(result<=0){
				data="注册失败，请重新注册";
				return createErrorJsonResponse(data);
			}
		}
		DyResponse response=createSuccessJsonResponse(null);
		return response;
	}
//	导航栏
	public List<Menu> getMenuList(String location,String location1){
		List<DollMenu> menuList=null;
		List<Where> whereList=new ArrayList<Where>();
		whereList.add(Where.eq(DollMenuDomain.MENU_STATUS, "1"));
		try {
			menuList=baseService.getList(DollMenu.class, whereList,
					Parameter.queryColumn(new String[]{DollMenuDomain.PID,DollMenuDomain.NAME,DollMenuDomain.MENU_TYPE,
							DollMenuDomain.MENU_PRICE,DollMenuDomain.MENU_LOCATION,DollMenuDomain.MENU_IMG_URL,DollMenuDomain.ID,DollMenuDomain.LINK_ID}));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		if(menuList==null||menuList.size()<=0) return null;
		List<Menu> topMenuList=new ArrayList<>();
//		顶部菜单
		for (DollMenu menu : menuList) {
			if(menu==null ||menu.getId()==null ||menu.getPid()==null) continue;
			Menu topMenu=new Menu();
			if(menu.getPid()==0 && menu.getMenuLocation().equals(location)){
				topMenu.setId(menu.getId());
				topMenu.setName(menu.getName());
				topMenu.setMenuType(menu.getMenuType());
				topMenu.setLinkId(menu.getLinkId());
				topMenuList.add(topMenu);
			}
		}
		for (Menu topMenu : topMenuList) {
			List<Menu> subMenuList=new ArrayList<>();
			for(DollMenu menu:menuList){
				if(menu==null ||menu.getId()==null ||menu.getPid()==null) continue;
				if(menu.getPid()!=topMenu.getId().intValue()) continue;
				Menu subMenu=new Menu();
				subMenu.setId(menu.getId());
				subMenu.setPid(menu.getPid());
				subMenu.setName(menu.getName());
				subMenu.setMenuImgUrl(menu.getMenuImgUrl());
				subMenu.setMenuPrice(menu.getMenuPrice());
				subMenu.setMenuLabel(menu.getMenuLabel());
				subMenu.setLinkId(menu.getLinkId());
				subMenu.setMenuType(menu.getMenuType());
				
				List<Menu> buttonList=new ArrayList<>();
				for (DollMenu temp : menuList) {
					if(temp==null || temp.getId()==null || temp.getPid()==null) continue;
					if(temp.getPid()==subMenu.getId().intValue()) continue;
					Menu button=new Menu();
					button.setId(menu.getId());
					button.setPid(menu.getPid());
					button.setName(menu.getName());
					button.setMenuImgUrl(menu.getMenuImgUrl());
					button.setMenuPrice(menu.getMenuPrice());
					button.setMenuLabel(menu.getMenuLabel());
					button.setMenuType(menu.getMenuType());
					button.setLinkId(menu.getLinkId());
					buttonList.add(button);
				}
				subMenu.setSubMenu(buttonList);
				subMenuList.add(subMenu);
			}
			topMenu.setSubMenu(subMenuList);
		}
		return topMenuList;
		
	}
}
