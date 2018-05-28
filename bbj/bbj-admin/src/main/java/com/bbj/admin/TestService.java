package com.bbj.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.SqlFilter;

@Service
public class TestService {

	
	@Autowired
	private TestDao testDao;
	
	
	/**
	 * 增
	 * @param test
	 * @return
	 */
	public int insert(Test test){
		if(test == null){
			return 0;
		}
		return testDao.insert(test);
	}
	
	
	/**
	 * 删
	 * @param id
	 * @return
	 */
	public int deleteById(String id){
		if(id == null){
			return 0;
		}
		return testDao.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param test
	 * @return
	 */
	public int update(Test test){
		return testDao.update(test);
	}
	
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Test queryById(String id){
		return testDao.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	public List<Test> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return testDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	
	/**
	 * 获取总数
	 * @param sqlFilter
	 * @return
	 */
	public int getTotalRow(SqlFilter sqlFilter){
		return testDao.getTotalRow(sqlFilter);
	}


}