package com.bbj.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;

public interface BBJDao<T extends BBJEntity> {

	public JdbcTemplate getJdbcTemplate();
	
	public Map<String, Object> queryForMap(String prepareSql,Object... args);
	
	public List<T> queryForList(String prepareSql,Object[] args, Class<T> elementType);
	
	/**
	 * 查询
	 * @param prepareSql
	 * @param requiredType
	 * @return
	 */
	public T queryForObject(String prepareSql, Object[] args, Class<T> requiredType);
	
	/**
	 * 更新方法,一般的dao调用此方法进行更新数据库
	 * @param prepareSql
	 * @param args
	 * @return
	 */
	public int update(String prepareSql,Object ...args);
	
	/**
	 * 批量更新方法,一般的dao调用此方法进行批量更新数据库
	 * @param prepareSql
	 * @param batchArgs
	 * @return
	 */
	public int[] batchUpdate(String prepareSql,List<Object[]> batchArgs);
	
	/**
	 * 新增方法，根据对象增加一条记录
	 * @param bbjEntity
	 * @return the number of rows affected
	 */
	int insert(T bbjEntity);

	/**
	 * 删除方法，根据主键进行删除记录
	 * @param id
	 * @return the number of rows affected
	 */
	int deleteById(String id);

	/**
	 * 更新方法，根据传入对象参数的主键进行更新修改该条记录
	 * @param bbjEntity
	 * @return the number of rows affected
	 */
	int update(T bbjEntity);

	/**
	 * 查找方法，根据主键值查找记录并映射成一个对象返回
	 * @param id
	 * @return
	 */
	T queryById(String id);

	/**
	 * 查询方法，分页查询
	 * @param tagPage
	 * @param pageSize
	 * @return
	 */
	List<T> queryByPage(int tagPage, int pageSize);

	/**
	 * 查询方法，分页查询
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	List<T> queryByPage(int tagPage, int pageSize, SqlFilter sqlFilter);

	/**
	 * 统计条记录数
	 * @return
	 */
	int getTotalRow();

	/**
	 * 统计条记录数
	 * @param sqlFilter 
	 * @return
	 */
	int getTotalRow(SqlFilter sqlFilter);

}