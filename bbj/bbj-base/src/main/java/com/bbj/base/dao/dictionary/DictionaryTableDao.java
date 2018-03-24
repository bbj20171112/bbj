package com.bbj.base.dao.dictionary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.dictionary.DictionaryTable;

/**
 * 表数据字典的DAO实现
 * 
 * @author bage
 *
 */
@Component("dictionaryTableDao")
public class DictionaryTableDao extends BBJDaoImp<DictionaryTable> {

	private static final String key_sql = "key_sql";
	private static final String key_parameter = "key_parameter";

	
	/**
	 * 创建一个表
	 * 
	 * @param table
	 * @return
	 */
	public int createTable(DictionaryTable table) {
		Map<String, Object> map = getCreateTablePrepareSqlMap(table);
		String sql = (String) map.get(key_sql);
		Object[] args = (Object[]) map.get(key_parameter);
		return update( sql,args);
	}

	public String getCreateTablePrepareSql(DictionaryTable table){
		Map<String, Object> map = getCreateTablePrepareSqlMap(table);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getCreateTablePrepareSqlMap(DictionaryTable table){
		String tableName = table.getAttr(DictionaryTable.table_name);
		StringBuilder sb = new StringBuilder();
		sb.append(" create table ");
		sb.append(" " + tableName + " ");
		sb.append(" ( ");
		sb.append("  create_time datetime, ");
		sb.append("  update_time datetime, ");
		sb.append("  create_staff_id varchar(64), ");
		sb.append("  update_staff_id varchar(64), ");
		sb.append("  delete_state varchar(2) ");
		sb.append(" ) ");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

	
	/**
	 * 更新一个表
	 * 
	 * @param table
	 * @return
	 */
	public int alterTable(DictionaryTable table) {
		Map<String, Object> map = getAlterTablePrepareSqlMap(table);
		String sql = (String) map.get(key_sql);
		Object[] args = (Object[]) map.get(key_parameter);
		return update( sql,args);
	}

	public String getAlterTablePrepareSql(DictionaryTable table){
		Map<String, Object> map = getAlterTablePrepareSqlMap(table);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getAlterTablePrepareSqlMap(DictionaryTable table){
		DictionaryTable oldTable = this.queryById(table.getAttr(table.getId()));
		String oldTableName = oldTable.getAttr(DictionaryTable.table_name);
		String newTableName = table.getAttr(DictionaryTable.table_name);
		StringBuilder sb = new StringBuilder();
		sb.append(" alter table ");
		sb.append(" " + oldTableName + " ");
		sb.append(" RENAME TO ");
		sb.append(" " + newTableName + " ");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

	/**
	 * 删除一个表
	 * 
	 * @param table
	 * @return
	 */
	public int dropTable(DictionaryTable table) {
		Map<String, Object> map = getDropTablePrepareSqlMap(table);
		String sql = (String) map.get(key_sql);
		Object[] args = (Object[]) map.get(key_parameter);
		return update( sql,args);
	}

	public String getDropTablePrepareSql(DictionaryTable table){
		Map<String, Object> map = getDropTablePrepareSqlMap(table);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getDropTablePrepareSqlMap(DictionaryTable table){
		String tableName = table.getAttr(DictionaryTable.table_name);
		StringBuilder sb = new StringBuilder();
		sb.append(" drop table ");
		sb.append(" " + tableName + " ");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

	
}
