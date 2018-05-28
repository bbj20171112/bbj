package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryReferenceDao;
import com.bbj.base.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.SqlFilter;

@Service
public class DictionaryReferenceService {
	
	@Autowired
	private DictionaryReferenceDao dictionaryReferenceDao;

	public DictionaryReference queryById(String id){
		return dictionaryReferenceDao.queryById(id);
	}

	public List<DictionaryReference> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return dictionaryReferenceDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter sqlFilter){
		return dictionaryReferenceDao.getTotalRow(sqlFilter);
	}
	
}
