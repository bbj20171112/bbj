package com.bbj.base.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bbj.base.dao.BBJDao;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;

@Component
public class BBJServiceImp<T extends BBJDao<V>, V extends BBJEntity>{
	
	protected T currentBBJDao;
	
	public BBJServiceImp(){
		
		// 获取当前的泛型类型
		Type typeTemp = getClass().getGenericSuperclass();
		if(typeTemp instanceof ParameterizedType){
			
	        ParameterizedType type = (ParameterizedType) typeTemp;      
	        @SuppressWarnings("unchecked")
			Class<T> currentBBJDaoClass = (Class<T>) type.getActualTypeArguments()[0];
	        // 实例化一个对象
	        try {
				currentBBJDao = currentBBJDaoClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int insert(V bbjEntity){
		return currentBBJDao.insert(bbjEntity);
	}

	public int deleteById(String id){
		return currentBBJDao.deleteById(id);
	}

	public int update(V bbjEntity){
		return currentBBJDao.update(bbjEntity);
	}

	public V queryById(String id){
		return currentBBJDao.queryById(id);
	}

	public List<V> queryByPage(int tagPage, int pageSize,SqlFilter<V> sqlFilter){
		return currentBBJDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter<V> sqlFilter){
		return currentBBJDao.getTotalRow(sqlFilter);
	}
	
	
}
