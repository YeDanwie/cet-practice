package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bean.Account;
import bean.Topic;
import bean.Word;
import service.DiscussService;
import service.PracticeService;
import service.StatisticService;

@Controller
public class IndexController {
	@Autowired
	private StatisticService statisticService;
	
	@Autowired
	private PracticeService practiceService;
	
	@Autowired
	private DiscussService discussService;
	
	
	@RequestMapping("/toLogin")
	 public String toLogin(){
	    return "login";
	 }
	
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }
    
    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
    	Account user = (Account)session.getAttribute("user");
    	model.addAttribute("username",user.getUsername());
    	return "index";
    }
    
    @RequestMapping("/memorize")
    public String memorize(Model model, HttpSession session) {
    	Account user = (Account)session.getAttribute("user");
    	model.addAttribute("username",user.getUsername());
    	
    	List<Word> list = practiceService.queryAllWords();
    	model.addAttribute("words", list);
    	return "memorize";
    }
    
    @RequestMapping("/practice")
    public String practice(Model model, HttpSession session) {
    	Account user = (Account)session.getAttribute("user");
    	model.addAttribute("username",user.getUsername());
    	
    	
    	return "practice";
    }
    
    @RequestMapping("/resource")
    public String resource(Model model, HttpSession session) {
    	Account user = (Account)session.getAttribute("user");
    	model.addAttribute("username",user.getUsername());
    	
    	List<Topic> topics = discussService.queryAllTopic();
    	model.addAttribute("topics", topics);
    	return "resource";
    }
    
    @RequestMapping("/info")
    public String info(Model model, HttpSession session) {
    	Account user = (Account)session.getAttribute("user");
    	model.addAttribute("username",user.getUsername());
    	model.addAttribute("statistic", statisticService.quereyByUser(user));
    	return "info";
    }
}
