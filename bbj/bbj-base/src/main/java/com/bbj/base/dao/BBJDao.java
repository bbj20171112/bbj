package com.bbj.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;

public interface BBJDao<T extends BBJEntity> {

	public JdbcTemplate getJdbcTemplate();
	
	/**
	 * 查询<br><br>
	 * public Map<String, Object> queryForMap(String prepareSql,Object... args);
	 * @param daoParam
	 * @return
	 */
	public Map<String, Object> queryForMap(BBJDaoParam daoParam);
	
	
	/**
	 * 查询<br><br>
	 * public List<T> queryForList(String prepareSql,Object[] args, Class<T> elementType)
	 * @param daoParam
	 * @return
	 */
	public List<T> queryForList(BBJDaoParam daoParam);
	
	/**
	 * 查询<br><br>
	 * public T queryForObject(String prepareSql, Object[] args, Class<T> requiredType);
	 * @param daoParam
	 * @return
	 */
	public T queryForObject(BBJDaoParam daoParam);
	
	/**
	 * 更新方法,一般的dao调用此方法进行更新数据库<br><br>
	 * public int update(String prepareSql,Object ...args);
	 * @param daoParam
	 * @return
	 */
	//public int update(BBJDaoParam daoParam);
	
	/**
	 * 批量更新方法,一般的dao调用此方法进行批量更新数据库<br><br>
	 * public int[] batchUpdate(String prepareSql,List<Object[]> batchArgs);
	 * @param daoParam
	 * @return
	 */
	public int[] batchUpdate(BBJDaoParam daoParam);
	
	/**
	 * 新增方法，根据对象增加一条记录<br><br>
	 * public int insert(T entity);
	 * @param daoParam
	 * @return
	 */
	public int insert(BBJDaoParam daoParam);

	/**
	 * 删除方法，根据主键进行删除记录<br><br>
	 * public int deleteById(String id);
	 * @param daoParam
	 * @return
	 */
	public int deleteById(BBJDaoParam daoParam);

	/**
	 * 更新方法，根据传入对象参数的主键进行更新修改该条记录<br><br>
	 * public int update(T entity);
	 * @param daoParam
	 * @return
	 */
	public int update(BBJDaoParam daoParam);

	/**
	 * 查找方法，根据主键值查找记录并映射成一个对象返回<br><br>
	 * public T queryById(String id);
	 * @param daoParam
	 * @return
	 */
	public T queryById(BBJDaoParam daoParam);
	

	/**
	 * 查询方法，分页查询<br><br>
	 * public List<T> queryByPage(int tagPage, int pageSize, SqlFilter sqlFilter);
	 * @param daoParam
	 * @return
	 */
	public List<T> queryByPage(BBJDaoParam daoParam);


	/**
	 * 统计条记录数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter);
	 * @param daoParam
	 * @return
	 */
	public int getTotalRow(BBJDaoParam daoParam);

}