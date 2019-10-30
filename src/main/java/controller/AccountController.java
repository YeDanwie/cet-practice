package controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Account;
import bean.ValidateCode;
import service.AccountService;
import util.Verify;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(String username, String password, HttpSession session, String inputCode) {
		Account user = new Account();
		user.setUsername(username);
		user.setPassword(password);
		
		return accountService.login(user, session, inputCode);
	}
	
	@RequestMapping("/getCode")
	 public void getCode(HttpSession session, HttpServletResponse response) throws Exception{	
		ValidateCode validateCode = Verify.createValidateCode();
		session.setAttribute("code", validateCode.getCode());
	    
		BufferedImage image = (BufferedImage) validateCode.getImage();
       response.setContentType("image/png");
       OutputStream os = response.getOutputStream();
       ImageIO.write(image, "png", os);
	 }
	
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public String register(String username, String password) {
		Account user = new Account();
		user.setUsername(username);
		user.setPassword(password);
		
		return accountService.register(user);
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public String login(HttpSession session, String password) {
		Account user = (Account) session.getAttribute("user");
		user.setPassword(password);
		
		return accountService.changePassword(user);
	}
}
