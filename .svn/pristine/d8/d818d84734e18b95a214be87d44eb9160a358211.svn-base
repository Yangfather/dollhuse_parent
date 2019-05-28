package com.dollhouse.image.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.image.util.ImageUtil;

@Controller
public class ImageController {
	private static Logger logger = Logger.getLogger(ImageController.class);
	
	private static final BufferedImage waterMarkImage;
	static {
		waterMarkImage = genWaterMarkImage(10, 10, "diyou");
	}
	
	private static final String IMAGE_TYPE = ".*\\.((jpg)|(jpeg)|(png)|(gif)|(bmp)|(png)|(dxf)|(tiff)|(pcx)|(apk))";
	
	@RequestMapping("/getImage")
	public void getImage(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getParameter("path");
		if (StringUtils.isBlank(path)) {
			return;
		}
		
		FileOutputStream fos = null;    
        BufferedInputStream bis = null;    
        HttpURLConnection httpUrl = null;    
        URL url = null;    
        int BUFFER_SIZE = 1024;    
        byte[] buf = new byte[BUFFER_SIZE];    
        int size = 0;    
        try {    
            url = new URL(ImageUtil.getPrivateImageRealPathDir(path));    
            httpUrl = (HttpURLConnection) url.openConnection();    
            httpUrl.connect();    
            bis = new BufferedInputStream(httpUrl.getInputStream());    
            OutputStream out = response.getOutputStream();    
            while ((size = bis.read(buf)) != -1) {     
            	out.write(buf, 0, size);    
            }    
            out.flush();    
        } catch (IOException e) {    
        } catch (ClassCastException e) {    
        } finally {    
            try {    
                fos.close();    
                bis.close();    
                httpUrl.disconnect();    
            } catch (IOException e) {    
            } catch (NullPointerException e) {    
            }    
        }  
	}

	@ResponseBody
	@RequestMapping("{module}/{function}/uploadImage")
	public DyResponse uploadImage(HttpServletRequest request, @PathVariable("module") String module, @PathVariable("function") String function, String fileName) {
		return this.doUpload(request, module, function, fileName);
	}

	/*@ResponseBody
	@RequestMapping("{module}/{function}/protected/uploadImage")
	public DyResponse uploadPrivateImage(HttpServletRequest request, @PathVariable("module")String module, @PathVariable("function")String function, String fileName) {
		return this.doUpload(request, module, function, fileName, true);
	}*/
	
	private DyResponse doUpload(HttpServletRequest request,String module, String function, String fileName){
		if(fileName == null) return createErrorJsonResonse("文件名不能为空");
		
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		String imagePathDir = ImageUtil.getImagePathDir(module, function);
		String imageRealPathDir = ImageUtil.getImageRealPathDir(request, imagePathDir);

		File imageSaveFile = new File(imageRealPathDir);
		if (!imageSaveFile.exists()) {
			imageSaveFile.mkdirs();
		}

		File file = null;
		ImageInfo imageInfo = null;
		String[] fileNames = fileName.split(",");
		CommonsMultipartFile multipartFile = null;
		List<ImageInfo> resultList = new ArrayList<ImageInfo>();
		for(int i=0;;i++) {
			try {
				multipartFile = (CommonsMultipartFile)multiRequest.getFile("file_" + i);
				if(multipartFile == null) break;
				
				if(!".apk".equals(fileName.substring(fileName.lastIndexOf(".")))){
					DiskFileItem fileItem = (DiskFileItem)multipartFile.getFileItem();
					if(!addWaterMark(fileItem.getStoreLocation(), fileName))
						return createErrorJsonResonse("图片(" + fileName + ")包含非法信息");
				}
				if(fileNames[i] != null && !fileNames[i].toLowerCase().matches(IMAGE_TYPE))
					return createErrorJsonResonse("文件名(" + fileNames[i] + ")无效");
				
				String newFileName = ImageUtil.generateFileName(fileNames[i]);
				file = new File(imageRealPathDir + newFileName);
			
				multipartFile.transferTo(file);
				
				imageInfo = new ImageInfo();
				imageInfo.setId(imagePathDir + newFileName);
				imageInfo.setName(fileNames[i]);
				imageInfo.setFileurl(imageInfo.getId());
				resultList.add(imageInfo);
			} catch (Exception e) {
				logger.error("文件保存失败", e);
			}
		}

		return createSuccessJsonResonse(resultList);
	}

	/**
	 * 读取管道中的流数据
	 * @param inStream
	 * @return
	 */
	public byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			logger.error("读取图片管道中的流数据失败", e);
			return null;
		}
	}
	
	public boolean addWaterMark(File file, String fileName) throws IOException {  
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        
        Image image = ImageIO.read(file);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        if(width <= 0 || height <= 0) return false;
        
        if("jpg".equals(ext) || "jpeg".equals(ext)) return true;
        
        BufferedImage bufferedImage = (BufferedImage)image;
        Graphics2D g = bufferedImage.createGraphics();
        
        int widthDiff = width - 10;
        int heightDiff = height - 10;
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.0f));
        g.drawImage(waterMarkImage, widthDiff, heightDiff, 10, 10, new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
        	
        });
        g.dispose();

        ImageIO.write(bufferedImage, ext, file);
        
        return true;
    }
	
	private static BufferedImage genWaterMarkImage(int width, int height, String text){
		BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		
		Color color = new Color(66,2,82);
		Font font = new Font("Times New Roman",Font.PLAIN,22);
		Graphics2D g = bufferedImage.createGraphics();
		g.setFont(font);
		g.setColor(color);
		g.setBackground(new Color(226,226,240));
		g.clearRect(0, 0, width, height);
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(text, context);
		
		if(StringUtils.isBlank(text)) {
			double x = (width - bounds.getWidth()) / 2;
			double y = (height - bounds.getHeight()) / 2;
			double ascent = bounds.getY();
			double baseY = y - ascent;
			g.drawString(text, (int)x, (int)baseY);
		}
		g.dispose();
		
		return bufferedImage;
	}

	public DyResponse createSuccessJsonResonse(Object data) {
		DyResponse response = new DyResponse();
		response.setStatus(DyResponse.OK);
		response.setDescription("OK");
		response.setData(data);

		return response;
	}

	public DyResponse createErrorJsonResonse(String errorMsg) {
		DyResponse response = new DyResponse();
		response.setStatus(DyResponse.ERROR);
		response.setDescription(errorMsg);

		return response;
	}
}

class ImageInfo {

	private String id;

	private String name;

	private String fileurl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
}