package com.bbj.base.dao.dictionary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.dictionary.DictionaryTable;
import com.bbj.base.utils.RegularUtils;

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
		String sql = getCreateTablePrepareSql(table);
		return update( sql);
	}

	public String getCreateTablePrepareSql(DictionaryTable table){
		String tableName = table.getAttr(DictionaryTable.table_name);
		if(!RegularUtils.isSqlValid(tableName)){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(" create table ");
		sb.append(tableName);
		sb.append(" ( ");
		sb.append("  create_time datetime, ");
		sb.append("  update_time datetime, ");
		sb.append("  create_staff_id varchar(64), ");
		sb.append("  update_staff_id varchar(64), ");
		sb.append("  delete_state varchar(2) ");
		sb.append(" ) ");
		return sb.toString();
	}
	
	public Map<String,Object> getCreateFieldPrepareSqlMap(DictionaryTable table){
		String tableName = table.getAttr(DictionaryTable.table_name);
		StringBuilder sb = new StringBuilder();
		sb.append(" create table ");
		sb.append(table.getAttr(DictionaryTable.table_name));
		sb.append(" ( ");
		sb.append("  create_time datetime, ");
		sb.append("  update_time datetime, ");
		sb.append("  create_staff_id varchar(64), ");
		sb.append("  update_staff_id varchar(64), ");
		sb.append("  delete_state varchar(2) ");
		sb.append(" ) ");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb);
		map.put(key_parameter, new Object[]{table.getAttr(DictionaryTable.table_name)});
		return map;
	}

	/**
	 * 更新一个表
	 * 
	 * @param table
	 * @return
	 */
	public int alterTable(DictionaryTable table) {
		return 0;
	}

	/**
	 * 删除一个表
	 * 
	 * @param table
	 * @return
	 */
	public int deleteTable(String id) {
		return 0;
	}
}
