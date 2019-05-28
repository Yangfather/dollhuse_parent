package com.dollhouse.image.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dollhouse.core.entity.DyResponse;
import com.dollhouse.image.util.ImageUtil;

import gui.ava.html.image.generator.HtmlImageGenerator;

@Controller
public class Hmtl2ImageController {
	private static Logger logger = Logger.getLogger(Hmtl2ImageController.class);
	
	@ResponseBody
	@RequestMapping("{module}/{function}/html2Image")
	public DyResponse html2Image(HttpServletRequest request, @PathVariable("module") String module, @PathVariable("function") String function, String htmlstr) {
		if(StringUtils.isEmpty(module) || StringUtils.isEmpty(function) || StringUtils.isEmpty(htmlstr)){
			return this.createErrorJsonResonse("参数为空");
		}
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		String fileName = ImageUtil.generateFileName(UUID.randomUUID().toString() + ".png");
		String imagePathDir = ImageUtil.getImagePathDir(module, function);
		String imageRealPathDir = ImageUtil.getImageRealPathDir(request, imagePathDir);
		File file = new File(imageRealPathDir);
		if(!file.exists()){
			file.mkdirs();
		}
		imageGenerator.loadHtml(htmlstr);
		imageGenerator.getBufferedImage();
		imageGenerator.saveAsImage(imageRealPathDir + fileName);

		ImageInfo imageInfo = null;
		try {
			imageInfo = new ImageInfo();
			imageInfo.setId(imagePathDir + fileName);
			imageInfo.setName(fileName);
			imageInfo.setFileurl(imageInfo.getId());
		} catch (Exception e) {
			logger.error("文件保存失败", e);
		}
		return createSuccessJsonResonse(imageInfo);
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