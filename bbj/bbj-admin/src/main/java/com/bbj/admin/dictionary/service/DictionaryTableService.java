package com.bbj.admin.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.admin.dictionary.dao.DictionaryTableDao;
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.SqlFilter;

@Service
public class DictionaryTableService {
	
	@Autowired
	private DictionaryTableDao dictionaryTableDao;

	@Transactional
	public int insert(DictionaryTable table){
		int rows = 0;
		if(table == null){
			return rows;
		}
		
		rows += dictionaryTableDao.createTable(table); // 创建一个表
		rows += dictionaryTableDao.insert(table); // 插入到数据字典表
		
		return rows;
	}

	public String getCreateTablePrepareSql(DictionaryTable table){
		if(table == null){
			return "";
		}
		return dictionaryTableDao.getCreateTablePrepareSql(table);
	}
	
	@Transactional
	public int deleteById(String id){
		int rows = 0;
		if(id  == null){
			return rows;
		}
		DictionaryTable table = this.queryById(id);
		if(table  == null){
			return rows;
		}
		
		rows += dictionaryTableDao.dropTable(table); 
		rows += dictionaryTableDao.deleteById(id);
		
		return rows;
	}

	@Transactional
	public int update(DictionaryTable table){
		int rows = 0;
		if(table  == null){
			return rows;
		}
		 
		rows += dictionaryTableDao.alterTable(table);
		rows += dictionaryTableDao.update(table);
		
		return rows;
	}

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
