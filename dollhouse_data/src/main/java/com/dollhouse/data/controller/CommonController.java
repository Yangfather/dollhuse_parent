package com.dollhouse.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dollhouse.core.controller.BaseController;
import com.dollhouse.core.entity.DyResponse;


@Controller
public class CommonController extends BaseController {
	@ResponseBody
	@RequestMapping(value="/common/test", method=RequestMethod.GET)
	public DyResponse testData() {
		return createSuccessJsonResponse("成功");
	}
}