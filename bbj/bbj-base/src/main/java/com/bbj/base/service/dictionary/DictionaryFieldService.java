package com.bbj.base.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dao.dictionary.DictionaryFieldDao;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.dictionary.DictionaryField;

@Service(value = "dictionaryFieldService")
public class DictionaryFieldService
{
	@Autowired
	private DictionaryFieldDao dictionaryTableDao;
	
	public int insert(DictionaryField bbjEntity){
		return dictionaryTableDao.insert(bbjEntity);
	}

	public int deleteById(String id){
		return dictionaryTableDao.deleteById(id);
	}

	public int update(DictionaryField bbjEntity){
		return dictionaryTableDao.update(bbjEntity);
	}

	public DictionaryField queryById(String id){
		return dictionaryTableDao.queryById(id);
	}

	public List<DictionaryField> queryByPage(int tagPage, int pageSize,SqlFilter<DictionaryField> sqlFilter){
		return dictionaryTableDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter<DictionaryField> sqlFilter){
		return dictionaryTableDao.getTotalRow(sqlFilter);
	}
	
}
