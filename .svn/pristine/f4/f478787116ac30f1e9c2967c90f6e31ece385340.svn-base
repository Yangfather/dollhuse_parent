package com.dollhouse.core.utils.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dollhouse.core.utils.DyHttpClient;

/**
 * 建周短信发送接口
 */
public class JianZhouSms {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	//建周错误代码
	public static Map<Integer, String> ERROR_CODE = new HashMap<Integer, String>();
	static {
		ERROR_CODE.put(0, "成功");
		ERROR_CODE.put(-1, "余额不足");
		ERROR_CODE.put(-2, "帐号或密码错误");
		ERROR_CODE.put(-3, "连接服务商失败");
		ERROR_CODE.put(-4, "超时");
		ERROR_CODE.put(-5, "其他错误，一般为网络问题，IP受限等");
		ERROR_CODE.put(-6, "短信内容为空");
		ERROR_CODE.put(-7, "目标号码为空");
		ERROR_CODE.put(-8, "用户通道设置不对，需要设置三个通道");
		ERROR_CODE.put(-9, "捕获未知异常");
		ERROR_CODE.put(-10, "超过最大定时时间限制");
		ERROR_CODE.put(-11, "目标号码在黑名单里");
		ERROR_CODE.put(-12, "消息内容包含禁用词语");
		ERROR_CODE.put(-13, "没有权限使用该网关");
		ERROR_CODE.put(-14, "找不到对应的Channel ID");
		ERROR_CODE.put(-17, "没有提交权限，客户端帐号无法使用接口提交");
		ERROR_CODE.put(-18, "提交参数名称不正确或确实参数");
		ERROR_CODE.put(-19, "必须为POST提交");
		ERROR_CODE.put(-20, "超速提交(一般为每秒一次提交)");
	}
	
	/**
	 * 发送短信
	 * @param url
	 * @param account
	 * @param password
	 * @param phone 要发送的电话号码，多个用，隔开
	 * @param content 发送内容
	 * @return
	 */
	public String send(String url, String account, String password, String sign, String phone, String content) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("account", account);
		paramMap.put("password", password);
		paramMap.put("destmobile", phone);
		paramMap.put("msgText", content + sign);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHhmmss");
		paramMap.put("sendDateTime", "");
		
		String result = null;
		try {
			result = DyHttpClient.doPost(url, paramMap, String.class);
		} catch(Exception e) {
			logger.error("JianZhou sms send fail:", e);
			return e.getMessage();
		}
		
//		if(result == null || result < 0) {
//			String errorMsg = result == null ? null : ERROR_CODE.get(result);
//			logger.error("JianZhou sms sned fail:" + errorMsg == null ? "" : errorMsg);
//			return errorMsg;
//		}
			
		return result;
	}
}