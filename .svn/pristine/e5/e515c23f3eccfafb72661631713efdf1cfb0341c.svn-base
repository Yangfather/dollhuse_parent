package com.dollhouse.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.james.mime4j.util.CharsetUtil;
import org.apache.log4j.Logger;

import com.dollhouse.core.entity.DyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DyHttpClient {
	protected static Logger logger = Logger.getLogger(DyHttpClient.class);
	
	private static final String IMAGE_TYPE = ".*\\.((jpg)|(jpeg)|(png)|(gif)|(bmp)|(png)|(dxf)|(tiff)|(pcx)|(apk))";
	
	public static String doPost(String path, Map<String, String> paramMap) throws Exception {
		return doPost(path, paramMap, "UTF-8");
	}
	
	public static String doPost(String path, Map<String, String> paramMap, String charset) throws Exception {
		charset = StringUtils.isBlank(charset) ? "UTF-8" : charset;
		
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		if(paramMap != null) {
			for(String key : paramMap.keySet()) {
				paramsList.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
		}
		
		HttpPost httpPost = null;
		if(path.indexOf("http://") >= 0 || path.indexOf("https://") >= 0) 
			httpPost = new HttpPost(path);
		else
			httpPost = new HttpPost(PropertiesUtil.getHost() + "/" + path);
		HttpEntity entity = new UrlEncodedFormEntity(paramsList, charset);
		httpPost.setEntity(entity);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpResponse httpResponse = httpclient.execute(httpPost);
		entity = httpResponse.getEntity();
		
		String result = "";
		if(entity != null) result = EntityUtils.toString(entity, charset);
		
		httpclient.close();
		
		return result.replace("\r\n", "");
	}
	
	/**
	 * URL调用data端相应的服务
	 * @param path
	 * @param paramMap
	 * @param resultType
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPost(String path, Map<String, String> paramMap, final Class<T> resultType) throws Exception {
		return doPost(path, paramMap, resultType, "UTF-8");
	}
	
	/**
	 * URL调用data端相应的服务
	 * @param path
	 * @param paramMap
	 * @param resultType
	 * @param charset 默认UTF-8
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPost(String path, Map<String, String> paramMap, final Class<T> resultType, String charset) throws Exception {
		charset = StringUtils.isBlank(charset) ? "UTF-8" : charset;
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		for (String key : paramMap.keySet()) {
			paramsList.add(new BasicNameValuePair(key, paramMap.get(key)));
		}
		HttpPost httpPost = null;
		if (path.indexOf("http://") >= 0 || path.indexOf("https://") >= 0)
			httpPost = new HttpPost(path);
		else
			httpPost = new HttpPost(PropertiesUtil.getHost() + "/" + path);
		HttpEntity entity = new UrlEncodedFormEntity(paramsList, charset);
		httpPost.setEntity(entity);
		if (resultType.toString().equals("class java.lang.String")) {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			HttpClient httpclient = HttpClients.createDefault();
			T responseBody = (T) httpclient.execute(httpPost, responseHandler);
			return responseBody;
		} else {
			ResponseHandler<T> responseHandler = getResponseHandler(resultType, charset);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			T result = httpclient.execute(httpPost, responseHandler);

			httpclient.close();
			return result;
		}
	}
	
	/**
	 * URL调用data端相应的服务
	 * @param path
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static DyResponse doPost(String path, String param) throws Exception {
		ResponseHandler<DyResponse> responseHandler = getResponseHandler(DyResponse.class, "UTF-8");
		
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new BasicNameValuePair("__key__", param));
		
		HttpPost httpPost = null;
		if(path.indexOf("http://") >= 0 || path.indexOf("https://") >= 0) 
			httpPost = new HttpPost(path);
		else
			httpPost = new HttpPost(PropertiesUtil.getHost() + "/" + path);
		HttpEntity entity = new UrlEncodedFormEntity(paramsList, "UTF-8");
		httpPost.setEntity(entity);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		DyResponse response = httpclient.execute(httpPost, responseHandler);
		httpclient.close();
		
		return response;
	}
	
	/**
	 * 文件上传（单个）
	 * @param module 模块名
	 * @param function 功能点
	 * @param fileName 上传文件名
	 * @param file 上传文件
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static DyResponse doImageUpload(String module, String function, String fileName, File file, boolean isPrivate) throws Exception {
		if(file == null || fileName == null) return null;
		if(!fileName.toLowerCase().matches(IMAGE_TYPE)) {
			DyResponse response = new DyResponse();
			response.setStatus(DyResponse.ERROR);
			response.setDescription("文件名(" + fileName + ")无效");
			return response;
		}
		
		ResponseHandler<DyResponse> responseHandler = getResponseHandler(DyResponse.class, "UTF-8");
		
		String specFileName = null;
		if(fileName.contains(",")) specFileName = fileName;
		
        MultipartEntity multipartEntity = new MultipartEntity();  
        FileBody fileBody = new FileBody(file);
        multipartEntity.addPart("file_0", fileBody);
        multipartEntity.addPart("fileName", new StringBody(fileName.replace(",", ""), CharsetUtil.getCharset("UTF-8")));  
		
        String postUrl = (isPrivate ? PropertiesUtil.getPrivateImageUploadHost() : PropertiesUtil.getImageUploadHost())+ "/" + module + "/" + function + "/uploadImage";
		HttpPost httpPost = new HttpPost(postUrl);
		httpPost.setEntity(multipartEntity);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		DyResponse response = httpclient.execute(httpPost, responseHandler);
		httpclient.close();
		
		if(response == null || response.getStatus() != DyResponse.OK) return null;
		List<Map<String, Object>> result = (List<Map<String, Object>>) response.getData();
		
		if(StringUtils.isNotBlank(specFileName)) {
			Map<String, Object> map = result.get(0);
			map.put("name", specFileName);
			response.setData(map);
		} else {
			response.setData(result.get(0));
		}
		
		return response;
	}
	
	/**
	 * 文件上传(多个)
	 * @param module 模块名
	 * @param function 功能点
	 * @param fileMap 上传文件(fileName --> file)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static DyResponse doImageUpload(String module, String function, Map<String, File> fileMap) throws Exception {
		if(fileMap == null || fileMap.size() <= 0) return null;
		
		ResponseHandler<DyResponse> responseHandler = getResponseHandler(DyResponse.class, "UTF-8");
		
        int index = 0;
        StringBuffer fileNames = new StringBuffer();
        MultipartEntity multipartEntity = new MultipartEntity();
        Map<String, String> specFileName = new HashMap<String, String>();
        for(String fileName : fileMap.keySet()) {
        	if(fileName != null && !fileName.toLowerCase().matches(IMAGE_TYPE)) {
        		DyResponse response = new DyResponse();
    			response.setStatus(DyResponse.ERROR);
    			response.setDescription("文件名(" + fileName + ")无效");
    			return response;
        	}
        	if(fileName.contains(",")) {
        		specFileName.put(fileName.replace(",", ""), fileName);
        		fileNames.append(",").append(fileName.replace(",", ""));
        	} else {
        		fileNames.append(",").append(fileName);
        	}
        	
	        multipartEntity.addPart("file_" + index++, new FileBody(fileMap.get(fileName)));
        }
        multipartEntity.addPart("fileName", new StringBody(fileNames.substring(1), CharsetUtil.getCharset("UTF-8")));
		
		HttpPost httpPost = new HttpPost(PropertiesUtil.getImageUploadHost() + "/" + module + "/" + function + "/uploadImage");
		httpPost.setEntity(multipartEntity);
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
		httpPost.setConfig(requestConfig);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		DyResponse response = httpclient.execute(httpPost, responseHandler);
		httpclient.close();
		
		if(response != null && response.getStatus() == DyResponse.OK) {
			List<Map<String, Object>> fileList = (List<Map<String, Object>>) response.getData();
			for(Map<String, Object> map : fileList) {
				String key = map.get("name").toString();
				if(specFileName.containsKey(key)) {
					map.put("name", specFileName.get(key));
				}
			}
		}
		
		return response;
	}
	
	private static <T> ResponseHandler<T> getResponseHandler(final Class<T> clazz, final String charset) {
		ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
			@Override
			@SuppressWarnings("unchecked")
		    public T handleResponse(final HttpResponse response) throws IOException {
		        StatusLine statusLine = response.getStatusLine();
		        if(statusLine.getStatusCode() >= 300) 
		            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		        
		        HttpEntity httpEntity = response.getEntity();
		        if(httpEntity == null) 
		            throw new ClientProtocolException("Response contains no content");
		        
		        Reader reader = new InputStreamReader(httpEntity.getContent(), Charset.forName(charset));
		        if(clazz.getAnnotation(XmlRootElement.class) != null) {
		        	try {
						JAXBContext ctx = JAXBContext.newInstance(clazz);
						Unmarshaller unmarshaller = ctx.createUnmarshaller();
						return (T) unmarshaller.unmarshal(new StringReader(EntityUtils.toString(httpEntity, charset).trim()));
					} catch(JAXBException e) {
						logger.error("Convert xml response to java entity fail:", e);
						return null;
					}
		        }
		        
		        Gson gson = new GsonBuilder().create();
		        return gson.fromJson(reader, clazz);
		    }
		};
		
		return responseHandler;
	}
	
	
	/**
	 * 文件上传（单个）
	 * @param module 模块名
	 * @param function 功能点
	 * @param fileName 上传文件名
	 * @param file 上传文件
	 * @return
	 * @throws Exception
	 */
	public static DyResponse html2Image(String module, String function, String htmlStr) {
		DyResponse response = null;

		try {
			ResponseHandler<DyResponse> responseHandler = getResponseHandler(DyResponse.class, "UTF-8");

			String postUrl = PropertiesUtil.getImageUploadHost() + "/" + module + "/" + function + "/html2Image";
			HttpPost httpPost = new HttpPost(postUrl);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("htmlstr", htmlStr));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
			httpPost.setEntity(entity);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			response = httpclient.execute(httpPost, responseHandler);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
			httpPost.setConfig(requestConfig);
			httpclient.close();
			if (response == null || response.getStatus() != DyResponse.OK) {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return response;
	}
}