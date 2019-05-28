package com.dollhouse.core.utils.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dollhouse.core.utils.DyHttpClient;
import com.dollhouse.core.utils.StringUtils;

/**
 * 亿美短信发送接口
 */
public class ChuangLanSms {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	//亿美错误代码
	public static Map<Integer, String> ERROR_CODE = new HashMap<Integer, String>();
	static {
		ERROR_CODE.put(0, "提交成功");
		ERROR_CODE.put(101, "无此用户");
		ERROR_CODE.put(102, "密码错");
		ERROR_CODE.put(103, "提交过快（提交速度超过流速限制）");
		ERROR_CODE.put(104, "系统忙（因平台侧原因，暂时无法处理提交的短信）");
		ERROR_CODE.put(105, "敏感短信（短信内容包含敏感词）");
		ERROR_CODE.put(106, "消息长度错（>536或<=0）");
		ERROR_CODE.put(107, "包含错误的手机号码");
		ERROR_CODE.put(108, "手机号码个数错（群发>50000或<=0;单发>200或<=0）");
		ERROR_CODE.put(109, "无发送额度（该用户可用短信数已使用完）");
		ERROR_CODE.put(110, "不在发送时间内");
		ERROR_CODE.put(111, "超出该账户当月发送额度限制");
		ERROR_CODE.put(112, "无此产品，用户没有订购该产品");
		ERROR_CODE.put(113, "extno格式错（非数字或者长度不对）");
		ERROR_CODE.put(115, "自动审核驳回");
		ERROR_CODE.put(116, "签名不合法，未带签名（用户必须带签名的前提下）");
		ERROR_CODE.put(117, "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
		ERROR_CODE.put(118, "用户没有相应的发送权限");
		ERROR_CODE.put(119, "用户已过期");
		ERROR_CODE.put(120, "短信内容不在白名单中");
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
		paramMap.put("pswd", password);
		paramMap.put("mobile", phone);
		paramMap.put("msg", sign + content);
		paramMap.put("needstatus", "true");
		paramMap.put("seqid", "" + System.currentTimeMillis());

		String response = null;
		try {
			response = DyHttpClient.doPost(url, paramMap, String.class);
		} catch (Exception e) {
			logger.error("ChuangLan sms send fail:", e);
			return e.getMessage();
		}

		if (!StringUtils.isBlank(response)) {
			response = response.replaceAll("(\r\n|\r|\n|\n\r)", ",");
			String[] results = response.split(",");
			if (results == null || results.length < 2) {
				return "ChuangLan sms return error:" + response;
			}
			if (!"0".equals(results[1])) {
				String errorMsg = response == null ? null : ERROR_CODE.get(results[1]);
				logger.error("ChuangLan sms sned fail:" + errorMsg == null ? "" : errorMsg);
				return errorMsg;
			}
			return null;
		} else {
			return "ChuangLan sms return null";
		}
	}
}