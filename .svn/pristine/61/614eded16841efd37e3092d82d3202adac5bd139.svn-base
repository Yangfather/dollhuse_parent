package com.dollhouse.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.service.statistical.GoodsInfoService;
@Controller
public class FenXiController extends AdminBaseController{
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@RequestMapping(value={"/admin/salesManagement/areaSales"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse areaSaleMap(){
		List<Map<String, Object>> areaSale=goodsInfoService.areaSaleMap();
		return createSuccessJsonResponse(areaSale);
		
	}

}
