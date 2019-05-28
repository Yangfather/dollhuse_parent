package com.dollhouse.core.utils;

public class DaXiaoXieCase {
	String sb=new String();
	public  String  toUpperCase(String str){
		if(str.length()>0 &&str!=null){
			for(int i = 0; i < str.length(); i++){
				 if (Character.isLowerCase(str.charAt(i))) {//判断是不是小写  
	                   
	                   sb=sb+(str.charAt(i)+"").toUpperCase();//将字符串转换为大写  
	                }else{
	                	sb=sb+(str.charAt(i)+"").toUpperCase();
	                }
			}
		}
		return sb;
	}
	public String toLowerCase(String str){
		if(str.length()>0 &&str!=null){
			for(int i = 0; i < str.length(); i++){
				 if (Character.isUpperCase(str.charAt(i))) {//判断是不是小写  
	                   
					 sb=sb+(str.charAt(i)+"").toLowerCase();//将字符串转换为小写
	                }else{
	                	sb=sb+(str.charAt(i)+"").toLowerCase();
	                }
			}
		}
		return sb;
	}

}
