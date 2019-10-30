package service;

import javax.servlet.http.HttpSession;

import bean.Account;

public interface AccountService {
	public String login(Account user, HttpSession session, String code);
	public String register(Account user);
	public String changePassword(Account user);
}
