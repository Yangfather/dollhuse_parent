package com.dollhouse.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

public class NumberUtils {
	
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	public static final BigDecimal ONE_PERCENT = new BigDecimal(0.01);
	/**
	 * 格式化成保留两位小数,三位一逗号
	 */
	public static final DecimalFormat FORMATER = new DecimalFormat("#,##0.00");
	/**
	 * 格式化成保留两位小数,无逗号
	 */
	public static DecimalFormat FORMAT = new DecimalFormat("0.00"); 
	
	/**
	 * 格式化两位小数加三位一逗号
	 * @param num
	 * @return
	 */
	public static String format(BigDecimal num) {
		return FORMATER.format(num);
	}

	/**
	 * 按指定format进行格式化
	 * @param num
	 * @param format
	 * @return
	 */
	public static String format(BigDecimal num, DecimalFormat format) {
		return format.format(num);
	}
	
	/**
	 * 如果是空返回0
	 * @param num
	 * @return
	 */
	public static BigDecimal exNull(BigDecimal num) {
		return num != null ? num : BigDecimal.ZERO;
	}
	
	/**
	 * 加法
	 * @param nums
	 * @return
	 */
	public static BigDecimal add(BigDecimal... nums) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (BigDecimal num : nums) {
			if (num != null) {
				totalAmount = totalAmount.add(num);
			}
		}
		return totalAmount;
	}
	
	/**
	 * 减法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal sub(BigDecimal num1, BigDecimal num2) {
		return (num1 != null ? num1 : BigDecimal.ZERO).subtract(num2 != null ? num2 : BigDecimal.ZERO);
	}
	
	/**
	 * 乘法
	 * @param nums
	 * @return
	 */
	public static BigDecimal mul(BigDecimal... nums) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		int count = 0;
		for (BigDecimal num : nums) {
			if (num == null || BigDecimal.ZERO.compareTo(num) == 0) {
				return BigDecimal.ZERO;
			}
			if (count == 0) {
				totalAmount = num;
			} else {
				totalAmount = totalAmount.multiply(num);
			}
			count++;
		}
		return totalAmount;
	}
	
	/**
	 * 除法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal div(BigDecimal num1, BigDecimal num2) {
		if (num1 == null || num2 == null) {
			return BigDecimal.ZERO;
		}
		return num1.divide(num2, 6, BigDecimal.ROUND_HALF_DOWN);
	}
	
	/**
	 * 除法
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal div(BigDecimal num1, BigDecimal num2, int scale) {
		if (num1 == null || num2 == null || num1.compareTo(BigDecimal.ZERO) == 0 || num2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		return num1.divide(num2, scale, BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 处理小数点位数，默认保留两位小数
	 * @param num
	 * @return
	 */
	public static BigDecimal round(BigDecimal num) {
		return round(num, 2);
	}
	
	/**
	 * 四舍五入
	 * @param value
	 *  @param scale 小数点后num位
	 * @return
	 */
	public static BigDecimal round(BigDecimal num, int scale) {
		return num == null ? BigDecimal.ZERO : num.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	
	/**
	 * 处理小数点位数，默认为小数点后两位
	 * @param num
	 * @return
	 */
	public static Double round(Double num) {
		return round(num, 2);
	}
	
	/**
	 * 处理小数点位数
	 * @param num
	 * @param scale 小数点后num位
	 * @return
	 */
	public static Double round(Double num, int scale) {
		return num == null ? 0d : round(new BigDecimal(num), scale).doubleValue();
	}

	/**
	 * 向上取整
	 * @param value
	 * @return
	 */
	public static BigDecimal ceilNumber(BigDecimal num) {
		return num == null ? BigDecimal.ZERO : num.setScale(0, BigDecimal.ROUND_CEILING);
	}
	
	/**
	 * 判断是否大于0
	 * @param value
	 * @return
	 */
	public static boolean greaterThanZero(BigDecimal num) {
		return num == null ? false : num.compareTo(BigDecimal.ZERO) > 0;
	}
	
	/**
	 * 判断是否大于等于0
	 * @param value
	 * @return
	 */
	public static boolean greaterEqualZero(BigDecimal num) {
		return num == null ? false : num.compareTo(BigDecimal.ZERO) > 0;
	}
	
	/**
	 * 判断是否大于
	 * @param value
	 * @return
	 */
	public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) > 0;
	}

	
	/**
	 * 判断是否大于等于
	 * @param value
	 * @return
	 */
	public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) >= 0;
	}

	
	/**
	 * 判断是否小于
	 * @param value
	 * @return
	 */
	public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
		if (num1 == null) {
			return false;
		}
		return num1.compareTo(num2 != null ? num2 : BigDecimal.ZERO) < 0;
	}
	/**
	 * 将double类型的数据格式化成字符串表示,保留两位小数
	 * @param d
	 * @return
	 */
	public static String format(Double d) {
		if(d==null) return null;
		return NumberUtils.format(new BigDecimal(d),NumberUtils.FORMAT);
	}
	
	/**
	 * 将object类型对象转换为double类型
	 * @param obj 要转换成double类型的对象
	 * @param decimal 保留的小数位数
	 * @return
	 */
	public static double toDouble(Object obj, int decimal) {
		if(decimal<0) {
			throw new IllegalArgumentException("illegal decimal value ["+decimal+"]");
		}
		String s = "0";
		if(obj!=null) {
			s = obj.toString().trim();
		}
		if(s.length()==0) {
			s = "0";
		}
		BigDecimal bg = new BigDecimal(s);
		return bg.setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 单位换算
	 * @param orig 原值
	 * @param unit 单位值
	 * @param numberFormat 格式,为空返回原值
	 * @return 换算结果
	 */
	public static String unitConversion(BigDecimal orig,BigDecimal unit,DecimalFormat numberFormat){
		BigDecimal result = NumberUtils.div(orig, unit) ;
		if(numberFormat == null){
			return  NumberUtils.format(result);
		}
		String rt = NumberUtils.format(result,numberFormat) ;
		return rt ;
	}
	/**
	 * 将object类型对象转换为double类型,保留两位小数
	 * @param obj 要转换成double类型的对象
	 * @return
	 */
	public static double toDouble(Object obj) {
		return toDouble(obj, 2);
	}
	
	/**
	 * 将金额转换成用逗号隔开,如果超过千万，则显示为万
	 * @param args
	 */
	public static Map<String,Object> convertMap(Map<String,Object> map,String keys){
		if(StringUtils.isEmpty(keys)) return map;
		for(String key : keys.split(",")){
			if(map.get(key) == null) continue;
			BigDecimal value = new BigDecimal(map.get(key).toString());
			boolean flag=false;
			if(NumberUtils.lessThan(value,null)){
				value=value.abs();
				flag=true;
			}
			if(value.compareTo(new BigDecimal(10000)) >= 0){			
				if(flag){
					map.put(key, "-"+NumberUtils.unitConversion(value, new BigDecimal(10000),null)+"万元");
				}
				else{
					map.put(key, NumberUtils.unitConversion(value, new BigDecimal(10000),null)+"万元");
				}
			}else{
				if(flag){
					map.put(key, "-"+NumberUtils.format(value)+"元");
				}
				else{
					map.put(key, NumberUtils.format(value)+"元");
				}
			}
		}
		return map;
	} 
	
	public static void main(String[] args) {
//		System.out.println(NumberUtils.mul(BigDecimal.ONE, new BigDecimal(1000),new BigDecimal(898)));
//		System.out.println(NumberUtils.format(100d));
		//1604.274500
		System.out.println(NumberUtils.greaterThan(BigDecimal.ONE, BigDecimal.ZERO));
	}
}
