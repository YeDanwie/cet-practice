package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Account;
import dao.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public String login(Account user, HttpSession session, String code) {
		if(!code.equalsIgnoreCase(session.getAttribute("code").toString())) {
			return "{\"isSuccess\":false}";
		}
		
		List<Account> list = accountMapper.login(user);
		if(list.size() > 0) {
			session.setAttribute("user", user);
			return "{\"isSuccess\":true}";
		} else {
			return "{\"isSuccess\":false}";
		}
	}

	@Override
	public String register(Account user) {
		List<Account> list = accountMapper.selectUserByUsername(user.getUsername());
		if(list.size()==0) {
			accountMapper.registerUser(user);
			return "{\"isSuccess\":true}";
		} else {
			return "{\"isSuccess\":false}";
		}
	}

	@Override
	public String changePassword(Account user) {
		accountMapper.updatePasswordByUser(user);
		return "{\"isSuccess\":true}";
	}
}
