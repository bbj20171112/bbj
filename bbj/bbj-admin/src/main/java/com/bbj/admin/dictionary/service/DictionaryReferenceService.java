package com.bbj.admin.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.admin.dictionary.dao.DictionaryReferenceDao;
import com.bbj.admin.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.SqlFilter;

@Service(value = "dictionaryReferenceService")
public class DictionaryReferenceService {
	
	@Autowired
	private DictionaryReferenceDao dictionaryReferenceDao;

	@Transactional
	public int insert(DictionaryReference reference){
		return dictionaryReferenceDao.insert(reference);
	}
	
	public int deleteById(String id){
		return dictionaryReferenceDao.deleteById(id);
	}

	public int update(DictionaryReference bbjEntity){
		return dictionaryReferenceDao.update(bbjEntity);
	}

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
