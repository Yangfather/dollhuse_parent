package com.dollhouse.user.controller.system;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.dao.query.OrderBy;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.entity.Option;
import com.dollhouse.core.entity.Page;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.core.utils.OptionUtil;
import com.dollhouse.core.utils.RequestUtil;
import com.dollhouse.core.utils.StringUtils;
import com.dollhouse.entity.comm.DollName;
import com.dollhouse.entity.comm.DollOrder;
import com.dollhouse.entity.comm.DollShopCar;
import com.dollhouse.entity.comm.UserInfo;
import com.dollhouse.entity.comm.domain.DollNameDomain;
import com.dollhouse.service.statistical.GoodsInfoService;
import com.dollhouse.user.entity.GoodsDetial;

@Controller
public class GoodsInfoController extends BaseController {
	@Autowired
	protected BaseService baseService;
	@Autowired
	protected GoodsInfoService goodsInfoService;
	@Autowired
	protected OptionUtil optionUtil;
//	商品查询
	@RequestMapping(value={"/user/goods/toGoodsDetial"},method=RequestMethod.POST)
	public ModelAndView  toGoodsDetial(HttpServletRequest request){
		String search=RequestUtil.getString(request, "searchGoods", null);
		
		return createSuccessModelAndView("goods/goods-detial", null);
	}
//	商品详情
	@RequestMapping(value={"/user/common/goodsInfo"},method=RequestMethod.GET)
	public ModelAndView getGoodsInfo(HttpServletRequest request,Model model){
		String goodsName=request.getParameter("name");
		Long id=new Long(goodsName);
		List<Map<String, Object>> goods = goodsInfoService.getGoodsById(id);
		GoodsDetial detial=new GoodsDetial();
//		商品名称
		detial.setName(String.valueOf((goods.get(0)).get("doll_goods_name")));
//		商品ID
		detial.setId(Long.valueOf(String.valueOf(( goods.get(0)).get("id"))));
//		商品编号
		detial.setOrganCode(String.valueOf(( goods.get(0)).get("doll_organcode")));
//		图片部分
		String filePath = String.valueOf(( goods.get(0)).get("doll_img_url"));
		String[] paths=filePath.split(",");
		List<GoodsDetial> goodsImgUrl=new ArrayList<>();
		for (String aa : paths) {
			GoodsDetial subdetial=new GoodsDetial();
			subdetial.setURL(aa);
			goodsImgUrl.add(subdetial);
		}
		detial.setImgUrl(goodsImgUrl);
//		颜色部分
		String color = String.valueOf(( goods.get(0)).get("doll_color"));
//		中英文","replace
		String[] colors=color.replaceAll("，", ",").split(",");
		List<GoodsDetial> goodsColor=new ArrayList<>();
		for (String aa : colors) {
			GoodsDetial subdetial=new GoodsDetial();
			subdetial.setColor(aa);
			goodsColor.add(subdetial);
		}
		detial.setColors(goodsColor);
//		型号部分
		List<GoodsDetial> types=new ArrayList<>();
		for (Map<String,Object> good : goods) {
			GoodsDetial subdetial=new GoodsDetial();
			String type = String.valueOf( good.get("doll_type_name"));
			Long typeId=Long.valueOf(String.valueOf(good.get("type_id")));
			BigDecimal price =(BigDecimal) good.get("doll_type_price");
			subdetial.setId(typeId);
			subdetial.setPrice(price);
			subdetial.setType(type);
			types.add(subdetial);
		}
		detial.setTypes(types);
//		价格部分
		List<GoodsDetial> prices=new ArrayList<>();
		for (Map<String,Object> good : goods) {
			GoodsDetial subdetial=new GoodsDetial();
			BigDecimal price =(BigDecimal) good.get("doll_type_price");
			Long typeId=Long.valueOf(String.valueOf(good.get("type_id")));
			String type = String.valueOf( good.get("doll_type_name"));
			subdetial.setId(typeId);
			subdetial.setPrice(price);
			subdetial.setType(type);
			prices.add(subdetial);
		}
		detial.setPrices(prices);
		model.addAttribute("detial", detial);
		return createSuccessModelAndView("goods/goods-detial", null);
	}
	@RequestMapping(value={"/user/goods/chat"},method=RequestMethod.GET)
	public ModelAndView toChat(){
		return createSuccessModelAndView("goods/goods-chat", null);
	}
	
//	加入购物车
	@RequestMapping(value={"/user/goods/addShopCar"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse addShopCar(BigDecimal price,BigDecimal  total,Long num,String img,String name,String organcode,String type,String color) throws IOException{
		UserInfo userInfoDomain = (UserInfo) getSessionAttribute(Constant.SESSION_USER);
		DollShopCar shopCar=new DollShopCar();
		shopCar.setUserId(userInfoDomain.getId());
//		单价
		shopCar.setDollPrice(price);
//		合计
		shopCar.setDollTotal(total);
//		数量
		shopCar.setDollNum(num);
//		图片
		shopCar.setDollImg(img);
//		商品名称
		shopCar.setDollName(name);
//		商品编号
		shopCar.setDollOrgancode(organcode);
//		商品类型
		shopCar.setDollType(type);
//		商品颜色
		shopCar.setDollColor(color);
		try {
			long result = baseService.insert(shopCar);
			if (result>0) {
				return createSuccessJsonResponse(null);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return createErrorJsonResponse("加入购物车成功");
	}
//	删除购物车商品
	@RequestMapping(value={"/user/shopCar/delete"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse delete(Long id){
		try {
			int result = baseService.deleteById(DollShopCar.class, id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessJsonResponse(null);
		
	}
	@RequestMapping(value={"/user/goods/toPayPage"},method=RequestMethod.GET)
	public  ModelAndView toPayPage(HttpServletRequest request,Model model) throws ServletException, IOException{
		UserInfo userInfoDomain = (UserInfo) getSessionAttribute(Constant.SESSION_USER);
		DollShopCar shopCar=new DollShopCar();
		shopCar.setUserId(userInfoDomain.getId());
		shopCar.setDollName(RequestUtil.getString(request, "name", null));
		shopCar.setDollOrgancode(RequestUtil.getString(request, "organcode", null));
		shopCar.setDollType(RequestUtil.getString(request, "type", null));
		shopCar.setDollColor(RequestUtil.getString(request, "color", null));
		String price = RequestUtil.getString(request, "price", null);
//		单价
		BigDecimal bd1=new BigDecimal(price);
		shopCar.setDollPrice(bd1);
//		合计
		String total = RequestUtil.getString(request, "total", null);
		BigDecimal bd2=new BigDecimal(total);
		shopCar.setDollTotal(bd2);
		shopCar.setDollNum(Long.valueOf(RequestUtil.getString(request, "num", null)));
		shopCar.setDollImg(RequestUtil.getString(request, "img", null));
		request.setAttribute("shopCar", shopCar);
		return createSuccessModelAndView("goods/goods-pay", null);
	}
	@RequestMapping(value={"user/buy/pay"},method=RequestMethod.POST)
	public ModelAndView Order(HttpServletRequest request){
		DollOrder dollOrder=new DollOrder();
		UserInfo userInfoDomain = (UserInfo) getSessionAttribute(Constant.SESSION_USER);
		String province= RequestUtil.getString(request, "receiveAddressProvince", null);
		dollOrder.setReceiveAddressProvince(province);
		String city= RequestUtil.getString(request, "receiveAddressCity", null);
		dollOrder.setReceiveAddressCity(city);
		String area= RequestUtil.getString(request, "receiveAddressArea", null);
		dollOrder.setReceiveAddressArea(area);
		String addressDetial = RequestUtil.getString(request, "receiveAddressDetial", null);
		dollOrder.setReceiveAddressDetial(addressDetial);
		String receiveName = RequestUtil.getString(request, "receiveName", null);
		dollOrder.setReceiveName(receiveName);
		String receivePhone= RequestUtil.getString(request, "receivePhone", null);
		dollOrder.setReceivePhone(receivePhone);
		String imgUrl=RequestUtil.getString(request, "dollImg", null);
		dollOrder.setDollImg(imgUrl);
		String name=RequestUtil.getString(request, "dollName",  null);
		dollOrder.setDollName(name);
		String type=RequestUtil.getString(request, "dollType",  null);
		dollOrder.setDollType(type);
		String color=RequestUtil.getString(request, "dollColor",  null);
		dollOrder.setDollColor(color);
		String price=RequestUtil.getString(request, "dollPrice", null);
		BigDecimal pp=new BigDecimal(price);
		dollOrder.setDollPrice(pp);
		Long num=RequestUtil.getLong(request, "dollNum",  null);
		dollOrder.setDollNum(num);
		String total=RequestUtil.getString(request, "dollTotal",  null);
		BigDecimal tt=new BigDecimal(total);
		dollOrder.setDollTotal(tt);
		String message=RequestUtil.getString(request, "leaveMessage", null);
		dollOrder.setLeaveMessage(message);
		dollOrder.setLoginUserId(userInfoDomain.getId());
		dollOrder.setBuyTime(String.valueOf(DateUtil.getCurrentTime()));
		try {
			baseService.insert(dollOrder);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data="200";
		return createSuccessModelAndView("order/pay-successful", data);
		
	}
	@RequestMapping(value={"/user/goods/goods-list"},method=RequestMethod.GET)
	public ModelAndView goodsList(Integer page,String searchGoods,String menuType,String menuName,HttpServletRequest request){
//		搜索框请求
		DollName doll=new DollName();
		if(!StringUtils.isEmpty(searchGoods)){
			doll.setDollGoodsName(searchGoods);
		}
//导航栏请求	
		if(!StringUtils.isEmpty(menuType)){
			doll.setDollType(menuType);
			doll.setDollGoodsName(menuName);
			setSessionAttribute("doll", doll);
		}
		return createSuccessModelAndView("goods/goods-list",doll);
	}
	
	@RequestMapping(value={"/user/goods/goods-page"},method=RequestMethod.GET)
	public ModelAndView goodsPage(Integer page,String searchGoods,HttpServletRequest request){
		System.out.println(searchGoods);
		Page<DollName>  goodsList=null;
		List<Where> whereList=new ArrayList<>();
		DollName dollName=(DollName) request.getSession().getAttribute("doll");
		DollName dollNameDomain=new DollName();
		if (dollName.getDollType()!=null) {
			dollNameDomain.setDollGoodsName(searchGoods);
			whereList.add(Where.eq(DollNameDomain.DOLL_TYPE, dollName.getDollType()));
		}else{
			for (Option list : optionUtil.getGoodsType()) {
				System.out.println(String.valueOf(list.getValue()));
				if ((list.getText()).equals(searchGoods)) {
					System.out.println(String.valueOf(list.getValue()));
					dollNameDomain.setDollType(String.valueOf(list.getValue()));
					whereList.add(Where.eq(DollNameDomain.DOLL_TYPE, dollNameDomain.getDollType()));
				}
			}
		}
	
		
//导航栏请求	
		try {
			goodsList=baseService.getPage(DollName.class,
					whereList,
					page==null?1:page+1 ,
							Parameter.queryColumn(new String[]{DollNameDomain.ID,
									DollNameDomain.DOLL_GOODS_NAME,DollNameDomain.DOLL_ORGANCODE,DollNameDomain.DOLL_TYPE,DollNameDomain.DOLL_IMG_URL}),
							Parameter.orderBy(OrderBy.asc(DollNameDomain.LOWER_PRICE))
			);
			for (DollName doll : goodsList.getItems()) {
				String[] str=doll.getDollImgUrl().split(",");
				doll.setDollImgUrl(str[0]);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessModelAndView("goods/goods-page",goodsList);
	}
	
}
