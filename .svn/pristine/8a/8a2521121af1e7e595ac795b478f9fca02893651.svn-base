package com.dollhouse.image.util;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;



public class ImageUtil {

	private static Logger logger = Logger.getLogger(ImageUtil.class);
	
	private static String UPLOAN_PATH = "/images"; //公共图片

	private static String PRIVATE_UPLOAN_PATH = "/images/private"; //受保存图片
	
	//private static String PRIVATE_IMG_URL = "http://pic.jianguan.diyou.cc";//测试站
	
	private static String PRIVATE_IMG_URL = "http://pic.tjific.com";//正式站
	//private static String PRIVATE_IMG_URL = "http://localhost:8080/diyou_image";
	
	/**
	 * 相对路径
	 * @param module
	 * @param function
	 * @return
	 */
	public static String getImagePathDir(String module, String function) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		return "/" + module + "/" + function + "/" + dateformat.format(new Date()) + "/";
	}

	/**
	 * 绝对路径
	 * @param imagePathDir
	 * @return
	 */
	public static String getImageRealPathDir(HttpServletRequest request, String imagePathDir) {
		return getImageRealPathDir(request, imagePathDir, false);
	}
	
	/**
	 * 绝对路径
	 * @param imagePathDir
	 * @return
	 */
	private static String getImageRealPathDir(HttpServletRequest request, String imagePathDir, boolean isPrivate) {
		String realPathDir = new File(request.getSession().getServletContext().getRealPath("")).getParentFile().getParent();
		return realPathDir + (isPrivate ? PRIVATE_UPLOAN_PATH : UPLOAN_PATH) + imagePathDir;
	}
	
	
	/**
	 * 绝对路径
	 * @param imagePathDir
	 * @return
	 */
	public static String getPrivateImageRealPathDir(String imagePath) {
		return PRIVATE_IMG_URL + imagePath;
	}
	
	/**
	 * 生成文件名
	 * @param fileName
	 * @return
	 */
	public static String generateFileName(String fileName) {
		return UUID.randomUUID().toString() + getExtensionName(fileName);
	}
	
	/**
	 * 获取文件扩展名 比如：.jpg
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length() - 1))) {
				return fileName.substring(dot);
			}
		}
		return fileName != null ? fileName : "";
	} 
	
	/**
	 * 根据文件名获取MD5串
	 * @param text
	 * @return
	 */
	public static String md5(String text) {
		if (text == null || text.length() < 1) {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}

		byte[] byteArray = text.getBytes();
		//byte[] byteArray = UUID.randomUUID().toString().getBytes();
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	public static List<String> getImageSrc(String htmlStr) {
		Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Matcher m = p.matcher(htmlStr);
		List<String> srcs = new ArrayList<String>();
		while (m.find()) {
			srcs.add(m.group(1));
		}
		return srcs;
	}
	
	
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString();
		System.out.println(uuid);
		System.out.println(md5(uuid));
	}
}
