package com.bbj.organization.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.organization.dao.UserDao;
import com.bbj.organization.domain.User;

@Service
public class UserService {

	
	@Autowired
	private UserDao userDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(User user)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return userDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return userDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(User user)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return userDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public User query(String id)
	 * @return
	 */
	public User query(BBJDaoParam daoParam){
		return userDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<User> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<User> queryByPage(BBJDaoParam daoParam){
		return userDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return userDao.getTotalRow(daoParam);
	}

}