package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryFieldDao;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.domain.BBJServiceParam;

@Service
public class DictionaryFieldService
{
	@Autowired
	private DictionaryFieldDao dictionaryFieldDao;
	

	/**
	 * 查找<br><br>
	 * public DictionaryField query(String id)
	 * @param serviceParam
	 * @return
	 */
	public DictionaryField query(BBJServiceParam serviceParam){
		return dictionaryFieldDao.query(serviceParam);
	}

	/**
	 * 分页<br><br>
	 * public List<DictionaryField> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public List<DictionaryField> queryByPage(BBJServiceParam serviceParam){
		return dictionaryFieldDao.queryByPage(serviceParam);
	}
	

	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public int getTotalRow(BBJServiceParam serviceParam){
		return dictionaryFieldDao.getTotalRow(serviceParam);
	}	
	
}
