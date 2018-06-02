package com.bbj.organization.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.organization.dao.PositionDao;
import com.bbj.organization.domain.Position;

@Service
public class PositionService {

	
	@Autowired
	private PositionDao positionDao;
	
	
	/**
	 * 新增<br><br>
	 * public int insert(Position position)
	 * @return
	 */
	public int insert(BBJDaoParam daoParam){
		return positionDao.insert(daoParam);
	}
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @return
	 */
	public int delete(BBJDaoParam daoParam){
		return positionDao.delete(daoParam);
	}
	
	
	/**
	 * 更改<br><br>
	 * public int update(Position position)
	 * @return
	 */
	public int update(BBJDaoParam daoParam){
		return positionDao.update(daoParam);
	}
	
	
	/**
	 * 查询<br><br>
	 * public Position query(String id)
	 * @return
	 */
	public Position query(BBJDaoParam daoParam){
		return positionDao.query(daoParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<Position> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @return
	 */
	public List<Position> queryByPage(BBJDaoParam daoParam){
		return positionDao.queryByPage(daoParam);
	}
	
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam){
		return positionDao.getTotalRow(daoParam);
	}

}