package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryFieldDao;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.domain.SqlFilter;

@Service
public class DictionaryFieldService
{
	@Autowired
	private DictionaryFieldDao dictionaryFieldDao;
	

	public DictionaryField queryById(String id){
		return dictionaryFieldDao.queryById(id);
	}

	public List<DictionaryField> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return dictionaryFieldDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter sqlFilter){
		return dictionaryFieldDao.getTotalRow(sqlFilter);
	}
	
}
