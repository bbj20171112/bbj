package com.bbj.admin;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.domain.SqlFilter;

@Service
public class TypeService {

	
	@Autowired
	private TypeDao typeDao;
	
	
	/**
	 * 增
	 * @param type
	 * @return
	 */
	public int insert(Type type){
		if(type == null){
			return 0;
		}
		return typeDao.insert(type);
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
		return typeDao.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param type
	 * @return
	 */
	public int update(Type type){
		return typeDao.update(type);
	}
	
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Type queryById(String id){
		return typeDao.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	public List<Type> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return typeDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	
	/**
	 * 获取总数
	 * @param sqlFilter
	 * @return
	 */
	public int getTotalRow(SqlFilter sqlFilter){
		return typeDao.getTotalRow(sqlFilter);
	}


}