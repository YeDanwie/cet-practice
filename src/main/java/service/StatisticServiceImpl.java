package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Account;
import bean.Statistic;
import dao.StatisticMapper;

@Service
public class StatisticServiceImpl implements StatisticService{
	@Autowired
	private StatisticMapper statisticMapper;
	
	@Override
	public Statistic quereyByUser(Account user) {
		return statisticMapper.queryByUser(user);
	}

	@Override
	public void updateForUserWrong(Account user) {
		statisticMapper.updateForUserWrong(user);
	}

	@Override
	public void updateForUserCorrect(Account user) {
		statisticMapper.updateForUserCorrect(user);
	}

}
