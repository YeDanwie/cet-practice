package service;

import bean.Account;
import bean.Statistic;

public interface StatisticService {
	public Statistic quereyByUser(Account user);
	public void updateForUserWrong(Account user);
	public void updateForUserCorrect(Account user);
	
}
