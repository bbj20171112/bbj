package com.bbj.organization.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.organization.dao.CompanyDao;
import com.bbj.organization.domain.Company;

@Service
public class CompanyService {

	
	@Autowired
	private CompanyDao companyDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(Company company)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return companyDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return companyDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(Company company)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return companyDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public Company query(String id)
	 * @return
	 */
	public Company query(BBJDaoParam daoParam){
		return companyDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<Company> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<Company> queryByPage(BBJDaoParam daoParam){
		return companyDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return companyDao.getTotalRow(daoParam);
	}

}