package com.bbj.base.service.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if(null == table.getAttr(table.getId())){
			table.setAttr(table.getId(),"" + System.currentTimeMillis());
		}
		if(null == table.getAttr(DictionaryTable.delete_state)){
			table.setAttr(DictionaryTable.delete_state,DictionaryTable.delete_state_not);
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
		if(null == table.getAttr(table.getId())){
			table.setAttr(table.getId(),"" + System.currentTimeMillis());
		}
		if(null == table.getAttr(DictionaryTable.delete_state)){
			table.setAttr(DictionaryTable.delete_state,DictionaryTable.delete_state_not);
		}
		rows += dictionaryTableDao.deleteById(id);; // 删除数据字典表
		rows += dictionaryTableDao.dropTable(table); // 创建一个表

		return rows;
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
	
	public int getTotalRow(SqlFilter<DictionaryTable> sqlFilter){
		return dictionaryTableDao.getTotalRow(sqlFilter);
	}
	
}
