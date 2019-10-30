package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import bean.ValidateCode;

public class Verify {
	 private static final char[] chars = {
	            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	public static ValidateCode createValidateCode() {
		int width = 70, height = 25;
		BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
		//获取画笔
		Graphics g = image.getGraphics();
		//设定背景色 
		g.setColor(new Color(200, 200, 200));
		g.fillRect(0, 0, width, height);
		
		//取随机产生的验证码(4个字符) 
		Random rnd = new Random();
		StringBuffer code = new StringBuffer();
		for(int i=0;i<4;i++) {
			int randNum = rnd.nextInt(chars.length);
			code.append(chars[randNum]);
		}
		
		//将验证码显示到图象中 
		g.setColor(Color.black);
		g.setFont(new Font("", Font.PLAIN, 25));
		g.drawString(code.toString(), 10, 17);
		// 随机产生100个干扰点，使图象中的验证码不易被其他程序探测到 
		for (int i = 0; i < 100; i++){
			int x = rnd.nextInt(width);
			int y = rnd.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		
		ValidateCode validateCode = new ValidateCode();
		validateCode.setCode(code.toString());
		validateCode.setImage(image);
		
		return validateCode;
	}
}
