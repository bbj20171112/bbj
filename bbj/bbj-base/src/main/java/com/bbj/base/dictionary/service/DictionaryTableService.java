package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryTableDao;
import com.bbj.base.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.SqlFilter;

@Service
public class DictionaryTableService {
	
	@Autowired
	private DictionaryTableDao dictionaryTableDao;

	public DictionaryTable queryById(String id){
		return dictionaryTableDao.queryById(id);
	}

	public List<DictionaryTable> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return dictionaryTableDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter sqlFilter){
		return dictionaryTableDao.getTotalRow(sqlFilter);
	}
	
}
