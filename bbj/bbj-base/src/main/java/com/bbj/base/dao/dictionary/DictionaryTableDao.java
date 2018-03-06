package com.bbj.base.dao.dictionary;

import org.springframework.stereotype.Component;

import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.dictionary.DictionaryTable;

/**
 * 表数据字典的DAO实现
 * @author bage
 *
 */
@Component("dictionaryTableDao")
public class DictionaryTableDao extends BBJDaoImp<DictionaryTable>{

	
	/**
	 * 创建一个表
	 * @param table
	 * @return
	 */
	public int createTable(DictionaryTable table){
		Object[] args = new Object[]{};
		String sql = getCreateTableSql(table);
		return update(sql, args);
	}
	
	public String getCreateTableSql(DictionaryTable table){
		return " create table ? ()  ";
	}
	/**
	 * 更新一个表
	 * @param table
	 * @return
	 */
	public int alterTable(DictionaryTable table){
		return 0;
	}
	
	/**
	 * 删除一个表
	 * @param table
	 * @return
	 */
	public int deleteTable(String id){
		return 0;
	}
	
	
}
