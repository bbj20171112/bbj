package com.bbj.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.SqlFilter;

@Service
public class TestDomainService {

	
	@Autowired
	private TestDomainDao testDomainDao;
	
	
	/**
	 * 增
	 * @param testDomain
	 * @return
	 */
	public int insert(TestDomain testDomain){
		if(testDomain == null){
			return 0;
		}
		return testDomainDao.insert(testDomain);
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
		return testDomainDao.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param testDomain
	 * @return
	 */
	public int update(TestDomain testDomain){
		return testDomainDao.update(testDomain);
	}
	
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public TestDomain queryById(String id){
		return testDomainDao.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	public List<TestDomain> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return testDomainDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	
	/**
	 * 获取总数
	 * @param sqlFilter
	 * @return
	 */
	public int getTotalRow(SqlFilter sqlFilter){
		return testDomainDao.getTotalRow(sqlFilter);
	}


}
