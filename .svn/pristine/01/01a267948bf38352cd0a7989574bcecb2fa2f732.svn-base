package com.dollhouse.user.controller.system;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dollhouse.core.constant.Constant;
import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.core.exception.DaoException;
import com.dollhouse.core.service.BaseService;
import com.dollhouse.core.utils.EmailUtil;
import com.dollhouse.core.utils.RequestUtil;
import com.dollhouse.core.utils.ToMD5;
import com.dollhouse.entity.comm.UserInfo;
import com.dollhouse.entity.comm.domain.UserInfoDomain;
import com.dollhouse.user.controller.AdminBaseController;
import com.dollhouse.user.controller.VerifyCodeController;
@Controller
public class UserInfoController extends AdminBaseController{
	@Autowired
	private BaseService baseService;
	@Autowired
	private VerifyCodeController verifyCodeController;
	@RequestMapping(value={"/member/member/memberInfo"},method=RequestMethod.GET)
	public ModelAndView userInfo(HttpServletRequest request){
		UserInfo userInfo =new UserInfo();
		Long id = RequestUtil.getLong(request, "id", null);
		try {
			userInfo= baseService.getById(UserInfo.class, id, new String[]{UserInfoDomain.ID,UserInfoDomain.USER_NAME,UserInfoDomain.USER_PASSWORD,UserInfoDomain.USER_PHONE,UserInfoDomain.USER_IDENTIFICATION,UserInfoDomain.USER_EMAIL});
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessModelAndView("admin/userInfo", userInfo);
		
	}
	@RequestMapping(value={"/member/userName/change"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse nameChange(Long id,String name){
		System.out.println("id"+id+"name"+name);
		UserInfo userInfo=new UserInfo();
		userInfo.setUserName(name);
		try {
			baseService.updateById(userInfo, id, false);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessJsonResponse(getMessage("用户名修改成功！"));
	}
	@RequestMapping(value={"/member/userPassword/change"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse passwordChang(Long id,String password,String code){
		UserInfo userInfo=new UserInfo();
		ToMD5 md5=new ToMD5();
		String md5Password= md5.toMD5(password);
		userInfo.setUserPassword(md5Password);
		String verifycode=(String) getSessionAttribute("code");
		String result = checkVerifyCode(verifycode, code);
		if(result.equals("验证码不能为空!")){
			return createErrorJsonResponse(getMessage("验证码不能为空!"));
		}else if(result.equals("success")) {
			try {
				baseService.updateById(userInfo, id, false);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return createErrorJsonResponse(getMessage("验证码输入不正确！"));
		}
		return createSuccessJsonResponse(getMessage("密码修改成功！"));
	}
	@RequestMapping(value={""},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse emailChange(Long id,String email){
		UserInfo userInfo=new UserInfo();
		userInfo.setUserEmail(email);
		try {
			baseService.updateById(userInfo, id, false);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessJsonResponse(getMessage("邮箱修改成功！"));
		
	}
	@RequestMapping(value={},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse phoneChange(Long id,String phone){
		UserInfo userInfo=new UserInfo();
		userInfo.setUserPhone(phone);
		try {
			baseService.updateById(userInfo, id, false);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createSuccessJsonResponse(getMessage("手机号修改成功！"));
		
	}
	@RequestMapping(value={"/member/member/code"},method=RequestMethod.POST)
	@ResponseBody
	public DyResponse emailCode(String sendAddress){
		String code=generateVerifyCode(4);
		setSessionAttribute("code", code);
		System.out.println(code);
		try {
			EmailUtil.email(sendAddress,code);
			return createErrorJsonResponse(getMessage("邮箱发送失败，请确认邮箱地址"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createSuccessJsonResponse(null);
	}
	public static String generateVerifyCode(int verifySize) {
		 final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		return generateVerifyCode(verifySize, VERIFY_CODES);
	}

	/**
	 * 使用指定源生成验证码
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @param sources
	 *            验证码字符源
	 * @return
	 */
	public static String generateVerifyCode(int verifySize, String sources) {
		final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
		if (sources == null || sources.length() == 0) {
			sources = VERIFY_CODES;
		}
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}
	public static String checkVerifyCode(String verifycode,String code) {
		// 验证码校验
		if ("".equals(verifycode))
			return "验证码不能为空!";
		String sessionVerifyCode =  code;
		if (!verifycode.equalsIgnoreCase(sessionVerifyCode)) {
			return "fail";
		}
		return "success";
	}

}
