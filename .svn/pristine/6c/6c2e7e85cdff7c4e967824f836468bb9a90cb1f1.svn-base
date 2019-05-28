package com.dollhouse.core.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public static Properties properties = null;
	
	static {
		try {
			InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("system.properties");
			
			properties = new Properties();
			properties.load(inputStream);
		} catch(Exception e) {}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key) == null ? "" : properties.get(key).toString();
	}
	
	/**
	 * 获取data端URL(system.properties -> SERVER_URL)
	 * @return
	 */
	public static String getHost() {
		return properties.getProperty("SERVER_URL") == null ? "http://localhost:8080/diyou_server" : properties.get("SERVER_URL").toString();
	}
	
	/**
	 * 获取图片服务器URL(system.properties -> IMAGE_VIEW_SERVER_URL)
	 * @return
	 */
	public static String getImageHost() {
		return properties.getProperty("IMAGE_VIEW_SERVER_URL") == null ? "http://localhost:8080/diyou_image" : properties.get("IMAGE_VIEW_SERVER_URL").toString();
	}

	/**
	 * 获取图片上传服务器上传URL(system.properties -> IMAGE_SERVER_URL)
	 * @return
	 */
	public static String getImageUploadHost() {
		return properties.getProperty("IMAGE_SERVER_URL") == null ? "http://localhost:8080/dollhouse_image" : properties.get("IMAGE_SERVER_URL").toString();
	}
	
	/**
	 * 获取私有图片服务器URL(system.properties -> IMAGE_PRIVATE_VIEW_SERVER_URL)
	 * @return
	 */
	public static String getPrivateImageHost() {
		return properties.getProperty("IMAGE_PRIVATE_VIEW_SERVER_URL") == null ? "http://localhost:8080/diyou_image" : properties.get("IMAGE_PRIVATE_VIEW_SERVER_URL").toString();
	}
	
	/**
	 * 获取私有图片服务器上传URL(system.properties -> IMAGE_PRIVATE_SERVER_URL)
	 * @return
	 */
	public static String getPrivateImageUploadHost() {
		return properties.getProperty("IMAGE_PRIVATE_SERVER_URL") == null ? "http://localhost:8080/diyou_image" : properties.get("IMAGE_PRIVATE_SERVER_URL").toString();
	}
	
	/**
	 * 获取私有图片访问链接
	 * @return
	 */
	public static String getPrivateImagePath() {
		return PropertiesUtil.getImageUploadHost() + "/getImage?path=";
	}
	/**
	 * 修改后获取前台私有图片访问链接
	 * @return
	 */
	public static String getPrivateImageRealPath() {
		return properties.getProperty("IMAGE_PRIVATE_SHOW_URL") == null ? "http://localhost:8080/diyou_user/getImage?path=" : properties.get("IMAGE_PRIVATE_SHOW_URL").toString()+"/getImage?path=";
	}
	/**
	 * 修改后获取后台私有图片访问链接
	 * @return
	 */
	public static String getAdminPrivateImageRealPath() {
		return properties.getProperty("IMAGE_PRIVATE_SHOW_URL") == null ? "http://localhost:8080/diyou_admin/getImage?path=" : properties.get("IMAGE_PRIVATE_SHOW_URL").toString()+"/getImage?path=";
	}
	/**
	 * 获取AES加密密钥(system.properties -> AES_KEY)
	 * @return
	 */
	public static String getAesKey() {
		return properties.getProperty("AES_KEY") == null ? "#123we#$%^fdhg34" : properties.get("AES_KEY").toString();
	}
}