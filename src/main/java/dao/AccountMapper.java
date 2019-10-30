package dao;

import java.util.List;
import bean.Account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
	public List<Account> login(Account user);
	public List<Account> selectUserByUsername(String username);
	public Integer registerUser(Account user);
	public void updatePasswordByUser(Account user);
}
