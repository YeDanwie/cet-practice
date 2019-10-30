package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bean.Account;
import bean.Quiz;
import service.PracticeService;
import service.StatisticService;


@RestController
public class PracticeController {
	@Autowired
	private PracticeService practiceService;
	
	@Autowired
	private StatisticService statisticService;
	
	@RequestMapping("/getOneQuiz")
	@ResponseBody
	public Quiz getOneQuiz() {
		return practiceService.getOneQuiz();
	}
	
	@RequestMapping("/ansCorrect")
	public void ansCorrect(HttpSession session) {
		Account user = (Account) session.getAttribute("user");
		statisticService.updateForUserCorrect(user);
	}
	
	@RequestMapping("/ansWrong")
	public void ansWrong(HttpSession session) {
		Account user = (Account) session.getAttribute("user");
		statisticService.updateForUserWrong(user);
	}
}
