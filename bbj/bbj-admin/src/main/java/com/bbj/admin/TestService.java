package com.bbj.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;

@Service
public class TestService {

	
	@Autowired
	private TestDao testDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(Test test)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return testDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return testDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(Test test)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return testDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public Test query(String id)
	 * @return
	 */
	public Test query(BBJDaoParam daoParam){
		return testDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<Test> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<Test> queryByPage(BBJDaoParam daoParam){
		return testDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return testDao.getTotalRow(daoParam);
	}

}