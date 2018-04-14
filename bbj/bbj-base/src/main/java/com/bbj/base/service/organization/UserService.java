package com.bbj.base.service.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.base.dao.organization.UserDao;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.organization.User;

@Service(value = "userService")
public class UserService
{
	@Autowired
	private UserDao userDao;
	
	public int insert(User user){
		if(user == null){
			return 0;
		}
		return userDao.insert(user);
	}
	
	@Transactional
	public int deleteById(String id){
		if(id == null){
			return 0;
		}
		return userDao.deleteById(id);
	}

	public int update(User user){
		return userDao.update(user);
	}

	public User queryById(String id){
		return userDao.queryById(id);
	}

	public List<User> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return userDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter sqlFilter){
		return userDao.getTotalRow(sqlFilter);
	}
	
}

