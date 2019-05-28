package com.dollhouse.user.controller.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.dao.query.Parameter;
import com.dollhouse.core.dao.query.Where;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.DateUtil;
import com.dollhouse.core.utils.RequestUtil;
import com.dollhouse.entity.comm.DollOrder;
import com.dollhouse.entity.comm.DollShopCar;
import com.dollhouse.entity.comm.UserInfo;
import com.dollhouse.entity.comm.domain.DollShopCarDomain;
import com.dollhouse.user.entity.Order;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class PlatformController extends BaseController {
	@Autowired
	protected BaseService baseService;
	
	
	@RequestMapping(value={"/doll/system/shopCar"},method=RequestMethod.GET)
	public ModelAndView shopCar(HttpServletRequest request){
		Long userId = RequestUtil.getLong(request, "userName", null);
		System.out.println(userId);
		List<Where> whereList=new ArrayList<>();
		whereList.add(Where.eq(DollShopCarDomain.USER_ID, userId));
		List<DollShopCar> DollShopCars=new ArrayList<>();
		try {
			DollShopCars = baseService.getList(DollShopCar.class, whereList,
					Parameter.queryColumn(new String[]{DollShopCarDomain.ID,DollShopCarDomain.DOLL_NAME,DollShopCarDomain.DOLL_IMG,DollShopCarDomain.DOLL_COLOR,DollShopCarDomain.DOLL_NUM,DollShopCarDomain.DOLL_ORGANCODE,DollShopCarDomain.DOLL_PRICE,DollShopCarDomain.DOLL_TOTAL,DollShopCarDomain.DOLL_TYPE}));
			
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(DollShopCars.size());
		return createSuccessModelAndView("shopCar/doll_car", DollShopCars);
	}
	@RequestMapping(value={"/common/shopCar/pay"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse pay(HttpServletRequest request){
		String  array=request.getParameter("array");
		JSONArray json=JSONArray.fromObject(array); 
		JSONObject jsonOne;
		List<DollOrder> orders=new ArrayList<>();
		List<Long> orderIds=new ArrayList<>();
		for(int i=0;i<json.size();i++){
			jsonOne = json.getJSONObject(i); 
			DollOrder dollOrder=new DollOrder();
			dollOrder.setDollName(jsonOne.getString("name"));
			dollOrder.setDollImg(jsonOne.getString("url"));
			dollOrder.setDollPrice(BigDecimal.valueOf(jsonOne.getDouble("price")));
			dollOrder.setDollColor(jsonOne.getString("color"));
			dollOrder.setDollNum(jsonOne.getLong("num"));
			dollOrder.setDollType(jsonOne.getString("type"));
			dollOrder.setDollTotal(BigDecimal.valueOf(jsonOne.getDouble("total")));
			orders.add(dollOrder);
			orderIds.add(jsonOne.getLong("id"));
			}
		setSessionAttribute(Constant.SHOP_ORDER, orders);
		setSessionAttribute(Constant.ORDER_ID, orderIds);
		return createSuccessJsonResponse(null);
	}
	
	@RequestMapping(value={"/common/pay/page"},method=RequestMethod.GET)
	public ModelAndView payPage(HttpServletRequest request){
		Long totalNum=RequestUtil.getLong(request, "totalNum", null);
		Double totalMoney=RequestUtil.getDouble(request, "totalMoney", null);
		Order order=new Order();
		order.setTotalNum(totalNum);
		order.setTotalMoney(totalMoney);
		return createSuccessModelAndView("order/goods-pay", order);
	}
	@RequestMapping(value={"/common/buy/pay"},method=RequestMethod.POST)
	public ModelAndView Order(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) getSessionAttribute(Constant.SESSION_USER);
		@SuppressWarnings("unchecked")
		List<DollOrder> orders=(List<DollOrder>) getSessionAttribute(Constant.SHOP_ORDER);
		String province= RequestUtil.getString(request, "receiveAddressProvince", null);
		String city= RequestUtil.getString(request, "receiveAddressCity", null);
		String area= RequestUtil.getString(request, "receiveAddressArea", null);
		String addressDetial = RequestUtil.getString(request, "receiveAddressDetial", null);
		String receiveName = RequestUtil.getString(request, "receiveName", null);
		String receivePhone= RequestUtil.getString(request, "receivePhone", null);
		String message=RequestUtil.getString(request, "leaveMessage", null);
		List<DollOrder> orderList=new ArrayList<>();
		for (DollOrder dollOrder : orders) {
			dollOrder.setLoginUserId(userInfo.getId());
			dollOrder.setReceiveAddressProvince(province);
			dollOrder.setReceiveAddressCity(city);
			dollOrder.setReceiveAddressArea(area);
			dollOrder.setReceiveAddressDetial(addressDetial);
			dollOrder.setReceiveName(receiveName);
			dollOrder.setReceivePhone(receivePhone);
			dollOrder.setLeaveMessage(message);
			dollOrder.setBuyTime(String.valueOf(DateUtil.getCurrentTime()));
			System.out.println(String.valueOf(DateUtil.getCurrentTime()));
			orderList.add(dollOrder);
		}
		try {
			long result = baseService.insert(orderList);
			if(result>0){
				@SuppressWarnings("unchecked")
				List<Long> orderIds=(List) getSessionAttribute(Constant.ORDER_ID);
				for (Long long1 : orderIds) {
					baseService.deleteById(DollShopCar.class, long1);
				}
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data="200";
		return createSuccessModelAndView("order/pay-successful", data);
		
	}

}
