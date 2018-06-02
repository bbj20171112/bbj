package com.bbj.organization.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.organization.dao.DepartmentDao;
import com.bbj.organization.domain.Department;

@Service
public class DepartmentService {

	
	@Autowired
	private DepartmentDao departmentDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(Department department)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return departmentDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return departmentDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(Department department)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return departmentDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public Department query(String id)
	 * @return
	 */
	public Department query(BBJDaoParam daoParam){
		return departmentDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<Department> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<Department> queryByPage(BBJDaoParam daoParam){
		return departmentDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return departmentDao.getTotalRow(daoParam);
	}

}