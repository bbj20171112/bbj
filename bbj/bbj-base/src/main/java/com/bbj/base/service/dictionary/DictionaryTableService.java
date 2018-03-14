package com.bbj.base.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.base.dao.dictionary.DictionaryTableDao;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.dictionary.DictionaryTable;

@Service(value = "dictionaryTableService")
public class DictionaryTableService {
	
	@Autowired
	private DictionaryTableDao dictionaryTableDao;

	@Transactional
	public int insert(DictionaryTable table){
		int rows = 0;
		if(table == null){
			return rows;
		}
		
		rows += dictionaryTableDao.insert(table); // 插入到数据字典表
		rows += dictionaryTableDao.createTable(table); // 创建一个表

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
		DictionaryTable table = queryById(id);
		if(table  == null){
			return rows;
		}
		rows += dictionaryTableDao.deleteById(id);; 
		rows += dictionaryTableDao.dropTable(table); 

		return rows;
	}

	public int update(DictionaryTable bbjEntity){
		return dictionaryTableDao.update(bbjEntity);
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
