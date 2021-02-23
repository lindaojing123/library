package com.util;

import org.apache.struts2.ServletActionContext;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

public class CaptchaAction {

	public String execute() throws Exception{
		//1.生成验证码随机数
		String securityCode = SecurityCode.getSecurityCode();
		//2.存入session作用域
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("security_code_key",securityCode );
		//3.使用随机数绘制验证码图片
		BufferedImage image = SecurityImage.createImage(securityCode);
		//4.使用IO流输出到客户端浏览器
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = response.getOutputStream();
		/**
		 * 参数一： 验证码图片对象
		 * 参数二： 图片的格式
		 * 参数三： 输出流对象
		 */
		ImageIO.write(image, "png", out);
		// 返回null， 代表不做跳转
		return null;
	}
}
