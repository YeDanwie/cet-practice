package dao;

import org.apache.ibatis.annotations.Mapper;

import bean.Account;
import bean.Statistic;

@Mapper
public interface StatisticMapper {
	public Statistic queryByUser(Account user);
	public void updateForUserWrong(Account use);
	public void updateForUserCorrect(Account use);
}
