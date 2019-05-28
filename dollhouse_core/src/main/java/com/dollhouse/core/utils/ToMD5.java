package com.dollhouse.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ToMD5 {
	public String toMD5(String str){
		StringBuffer md5Code = new StringBuffer();
		MessageDigest md5;
		String md5Password=new String();
		try {
			md5 = MessageDigest.getInstance("MD5");
			if(str.length()>0 && str!=null){
				byte[] digest=md5.digest(str.getBytes());
				System.out.println(digest);
				for (byte b : digest) {
					String hexString=Integer.toHexString(b & 0xff);
					if (hexString.length()<2) {
						hexString="0"+hexString;
					} 
					md5Code=md5Code.append(b);
				    md5Password=String.valueOf(md5Code);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//计算MD5
		return md5Password;
	
		
	}

}
