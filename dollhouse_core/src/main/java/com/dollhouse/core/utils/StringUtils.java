package com.dollhouse.core.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//正则表达式验证URL，phone，telphone,email，money_pattern，IP，money_pattern_allow_negative
public class StringUtils extends org.apache.commons.lang.StringUtils {
	private static final Pattern URL = Pattern.compile(
		"^((https|http|ftp|rtsp|mms)?://)" 
	     + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" 
	     + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" 
	     + "|" 
	     + "([0-9a-z_!~*'()-]+\\.)*" 
	     + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." 
	     + "[a-z]{2,6})" 
	     + "(:[0-9]{1,4})?" 
	     + "((/?)|" 
	     + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$", Pattern.CASE_INSENSITIVE
	);
	private static final Pattern PHONE = Pattern.compile("^1[3|4|5|7|8]([0-9])\\d{8}$");
	private static final Pattern TELPHONE = Pattern.compile("^(0[0-9]{2,4}-?[0-9]{7,8})|(1[3|4|5|7|8][0-9]{9})$");
	private static final Pattern EMAIL = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");	private static final Pattern MONEY = Pattern.compile("^[0-9]+$|^[0-9]+\\.[0-9]{1,6}$");
	private static final Pattern MONEY_PATTERN = Pattern.compile("^[0-9]+$|^[0-9]+\\.[0-9]{1,6}$");
	private static final Pattern IP = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
	private static final Pattern MONEY_PATTERN_ALLOW_NEGATIVE = Pattern.compile("^[+-]?[0-9]+[0-9]*(\\.[0-9]+)?$");
	
	public static boolean isEqual(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null) return true;
		if(obj1 == null || obj2 == null) return false;
		
		return obj1.equals(obj2);
	}
	
	public static boolean isNotEqual(Object obj1, Object obj2) {
		return !isEqual(obj1, obj2);
	}
	
	/**
	 * 六位数字验证码
	 * @return
	 */
	public static String getSixCode() {
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += new Random().nextInt(10);
		}
		return result;
	}
	
	/**
	 * 数字字母验证码
	 */
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
    
    public static boolean checkUrl(String url) {
		return URL.matcher(url).matches();
	}
    
	public static Boolean checkEmail(String email) {
        return EMAIL.matcher(email).matches();
	}
	
	public static Boolean checkPhone(String phone) {
        return PHONE.matcher(phone).matches();
	}
	
	public static Boolean checkTelPhone(String tel){
        return TELPHONE.matcher(tel).matches();
	}
	
	public static Boolean checkIp(String ip){
		return IP.matcher(ip).matches();
	}
	
	public static boolean isMoney(String str) {
		return MONEY_PATTERN.matcher(str).matches();
	}
	
	public static boolean isMoneyAllowNegative(String str) {
		return MONEY_PATTERN_ALLOW_NEGATIVE.matcher(str).matches();
	}
	/**
	 * 
	 * type+(yyMMddHHmmss)+ID(截取后4位，不够补0)+三位随机
	 * @param id 生成平台传memberID 其余用platform
	 * @param type 平台:P 借款:L 投资:T 还款:R
	 * @return 备案号
	 */
	public static String generateRecordNo(Long id, String type) {
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
		Random random = new Random();
		String date = dateFormat.format(new Date());
		String randomStr = "";
		for (int i = 0; i < 7; i++) {
			randomStr += String.valueOf(random.nextInt(10));
		}
		String idStr = id.toString();
		int idLength = idStr.length();
		if (idLength >= 4) {
			idStr = idStr.substring(idLength - 4, idLength);
		} else {
			idStr = StringUtils.leftPad(idStr, 4, "0");
		}
		return type + date + idStr + randomStr;
	}
	
	public static String coverToUtf8(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	
	/**
	 * 生成平台接入ID
	 * @param id 平台ID
	 * @return
	 */
	public static Long generateOrganCode(Long id) {
		String organCode = 68 + StringUtils.leftPad(id.toString(), 8, "0") ;
		return Long.valueOf(organCode) ;
	}
	
	public static void main(String[] args) {
		String ascii="\u56fd\u7f8e\u91d1\u878d\u7684\u5b89\u5168\u6027\u53ef\u4ee5\uff0c\u6bd5\u7adf\u6709\u56fd\u7f8e\u96c6\u56e2\u4e09\u5341\u5e74\u7684\u54c1\u724c\u4fe1\u8a89\u5462\uff01";  
		String aa = unicodeToString(ascii);
		System.out.println(aa);  
	}
	
   /**
     * unicode转中文
     * @param str
     * @return
     * @author yutao
     * @date 2017年1月24日上午10:33:25
     */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			try {
				ch = (char) Integer.parseInt(matcher.group(2), 16);
				str = str.replace(matcher.group(1), ch + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;

	}
}