package com.dollhouse.core.utils.sms;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.dollhouse.core.utils.DyHttpClient;

/**
 * 亿美短信发送接口
 */
public class YiMeiSms {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	//亿美错误代码
	public static Map<Integer, String> ERROR_CODE = new HashMap<Integer, String>();
	static {
		ERROR_CODE.put(-2, "客户端异常");
		ERROR_CODE.put(-9000, "数据格式错误,数据超出数据库允许范围");
		ERROR_CODE.put(-9001, "序列号格式错误");
		ERROR_CODE.put(-9002, "密码格式错误");
		ERROR_CODE.put(-9003, "客户端Key格式错误");
		ERROR_CODE.put(-9016, "发送短信包大小超出范围");
		ERROR_CODE.put(-9017, "发送短信内容格式错误");
		ERROR_CODE.put(-9018, "发送短信扩展号格式错误");
		ERROR_CODE.put(-9019, "发送短信优先级格式错误");
		ERROR_CODE.put(-9020, "发送短信手机号格式错误");
		ERROR_CODE.put(-9021, "发送短信定时时间格式错误");
		ERROR_CODE.put(-9022, "发送短信唯一序列值错误");
		ERROR_CODE.put(-9025, "客户端请求sdk5超时（需确认）");
		ERROR_CODE.put(0, "成功");
		ERROR_CODE.put(-1, "系统异常");
		ERROR_CODE.put(-101, "命令不被支持");
		ERROR_CODE.put(-102, "RegistryTransInfo删除信息失败（转接）");
		ERROR_CODE.put(-103, "RegistryInfo更新信息失败（序列号相关注册）");
		ERROR_CODE.put(-104, "请求超过限制");
		ERROR_CODE.put(-117, "发送短信失败");
		ERROR_CODE.put(-118, "接收MO失败");
		ERROR_CODE.put(-119, "接收Report失败");
		ERROR_CODE.put(-126, "路由信息失败");
		ERROR_CODE.put(-1100, "序列号错误,序列号不存在内存中,或尝试攻击的用户");
		ERROR_CODE.put(-1103, "序列号Key错误");
		ERROR_CODE.put(-1102, "序列号密码错误");
		ERROR_CODE.put(-1104, "路由失败，请联系系统管理员");
		ERROR_CODE.put(-1105, "注册号状态异常, 未用 1");
		ERROR_CODE.put(-1107, "注册号状态异常, 停用 3");
		ERROR_CODE.put(-1108, "注册号状态异常, 停止 5");
		ERROR_CODE.put(-1901, "数据库插入操作失败");
		ERROR_CODE.put(-1902, "数据库更新操作失败");
		ERROR_CODE.put(-1903, "数据库删除操作失败");
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
		paramMap.put("cdkey", account);
		paramMap.put("password", password);
		paramMap.put("phone", phone);
		paramMap.put("message", content + sign);
		paramMap.put("seqid", "" + System.currentTimeMillis());
		paramMap.put("addserial", "");
		
		YiMeiResponse response = null;
		try {
			response = DyHttpClient.doPost(url, paramMap, YiMeiResponse.class);
			response = new YiMeiResponse();
			response.setError(0);
		} catch(Exception e) {
			logger.error("YiMei sms send fail:", e);
			return e.getMessage();
		}
		
		if(response == null || response.getError() != 0) {
			String errorMsg = response == null ? null : ERROR_CODE.get(response.getError());
			logger.error("YiMei sms sned fail:" + errorMsg == null ? "" : errorMsg);
			return errorMsg;
		}
			
		return null;
	}
}

@XmlRootElement(name="response")
class YiMeiResponse {
	/**
	 * 错误代码
	 */
	private Integer error;
	
	/**
	 * 错误信息描述
	 */
	private String message;
	
	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}