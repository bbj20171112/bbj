package com.bbj.base.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dao.dictionary.DictionaryTableDao;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.dictionary.DictionaryTable;

@Service(value = "dictionaryTableService")
public class DictionaryTableService
{
	@Autowired
	private DictionaryTableDao dictionaryTableDao;
	
	public int insert(DictionaryTable bbjEntity){
		return dictionaryTableDao.insert(bbjEntity);
	}

	public int deleteById(String id){
		return dictionaryTableDao.deleteById(id);
	}

	public int update(DictionaryTable bbjEntity){
		return dictionaryTableDao.update(bbjEntity);
	}

	public DictionaryTable queryById(String id){
		return dictionaryTableDao.queryById(id);
	}

	public List<DictionaryTable> queryByPage(int tagPage, int pageSize,SqlFilter<DictionaryTable> sqlFilter){
		return dictionaryTableDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
}
