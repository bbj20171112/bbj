package com.bbj.salary.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.salary.dao.SalaryDao;
import com.bbj.salary.domain.Salary;

@Service
public class SalaryService {

	
	@Autowired
	private SalaryDao salaryDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(Salary salary)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return salaryDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return salaryDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(Salary salary)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return salaryDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public Salary query(String id)
	 * @return
	 */
	public Salary query(BBJDaoParam daoParam){
		return salaryDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<Salary> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<Salary> queryByPage(BBJDaoParam daoParam){
		return salaryDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return salaryDao.getTotalRow(daoParam);
	}

}