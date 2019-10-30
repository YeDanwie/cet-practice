package controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Account;
import bean.Discuss;
import bean.Topic;
import service.DiscussService;

@Controller
public class DiscussController {
	@Autowired
	private DiscussService discussService;
	
	@RequestMapping("/createTopic")
	@ResponseBody
	public void createTopic(String theme, String content, HttpSession session) {
		Account user = (Account) session.getAttribute("user");
		String upper = user.getUsername();
		Date create_date = new java.sql.Date(System.currentTimeMillis());
		
		Topic topic = new Topic();
		topic.setUpper(upper);
		topic.setTheme(theme);
		topic.setContent(content);
		topic.setCreate_date(create_date);
		discussService.createTopic(topic);
	}
	
	@RequestMapping("/discuss")
	public String showDiscuss(Model model, String topic_id, HttpSession session) {
		Account user = (Account) session.getAttribute("user");
		model.addAttribute("username", user.getUsername());
		
		int id = Integer.parseInt(topic_id);
		
		Topic topic = discussService.queryTopicById(id);
		model.addAttribute("topic", topic);
		
		List<Discuss> discuss = discussService.queryDiscussByTopicId(id);
		model.addAttribute("discuss", discuss);
		return "discuss";
	}
	
	@RequestMapping("/submitDiscuss")
	@ResponseBody
	public void submitDiscuss(String topic_id, String content, HttpSession session) {
		int id = Integer.parseInt(topic_id);
		Discuss discuss = new Discuss();
		discuss.setTopic_id(id);
		Account user = (Account) session.getAttribute("user");
		discuss.setUsername(user.getUsername());
		discuss.setMessage(content);
		Date discuss_date = new java.sql.Date(System.currentTimeMillis());
		discuss.setDiscuss_date(discuss_date);
		discussService.insertNewDiscuss(discuss);
	}
}
