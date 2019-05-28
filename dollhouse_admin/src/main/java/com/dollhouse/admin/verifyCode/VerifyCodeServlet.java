package com.dollhouse.admin.verifyCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCodeServlet {
//验证码字符串
	private String codeV;

	public String getCodeV() {
		return codeV;
	}
//生成验证码
	public BufferedImage create(){
		//大小
		int  width=120;
		int height=30;
		//声明一个RGB格式图片
		BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
//		获取画笔
		Graphics g=img.getGraphics();
		//背景色
		g.setColor(Color.LIGHT_GRAY);
		//画
		g.fillRect(0, 0, width, height);
		//字体
		g.setFont(new Font("黑体", Font.BOLD, 18));
		//为codeV传值
		String d="";
		//写一个字符到img
		Random r=new Random();
		for (int i=0;i<4;i++){
			//生成随机字母
			String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			char codeEnglish=chars.charAt((int)(Math.random()*34));
			d+=codeEnglish;
			codeV=d;
			//画笔随即色
			g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			//写出字符
			g.drawString(""+codeEnglish, i*30, 10+r.nextInt(256));
		}
		//干扰线
		for(int i=0;i<4;i++){
			g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
			//划线
			g.drawLine(r.nextInt(120), r.nextInt(30), r.nextInt(120), r.nextInt(30));
		}
		g.dispose();
		return img;
		
	}
	
}
