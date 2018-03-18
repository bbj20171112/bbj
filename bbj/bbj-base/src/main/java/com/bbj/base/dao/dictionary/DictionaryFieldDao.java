package com.bbj.base.dao.dictionary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.dictionary.DictionaryField;

/**
 * 字段数据字典的DAO实现
 * @author bage
 *
 */
@Component
public class DictionaryFieldDao extends BBJDaoImp<DictionaryField>{
	
	private static final String key_sql = "key_sql";
	private static final String key_parameter = "key_parameter";

	
	/**
	 * 添加字段: alter table [tableName] add [fieldName] [fieldType]
	 * 
	 * @param table
	 * @return
	 */
	public int createField(DictionaryField field,String tableName) {
		Map<String, Object> map = getCreateFieldPrepareSqlMap(field,tableName);
		String sql = (String) map.get(key_sql);
		Object[] args = (Object[]) map.get(key_parameter);
		return update( sql,args);
	}

	/**
	 * 添加字段: alter table [tableName] add [fieldName] [fieldType]
	 * @param field
	 * @param tableName
	 * @return
	 */
	public String getCreateFieldPrepareSql(DictionaryField field,String tableName){
		Map<String, Object> map = getCreateFieldPrepareSqlMap(field,tableName);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getCreateFieldPrepareSqlMap(DictionaryField field,String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append(" alter table ");
		sb.append(" " + tableName);
		sb.append(" add ");
		sb.append(" " + field.getAttr(DictionaryField.field_name) + " ");
		sb.append(" " + field.getAttr(DictionaryField.field_key_type) + " ");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

	
	/**
	 * 更新一个字段
	 * 
	 * @param table
	 * @return
	 */
	public int alterField(DictionaryField field,String tableName) {
		return 0;
	}

	/**
	 * 删除一个字段
	 * 
	 * @param table
	 * @return
	 */
	public int dropField(DictionaryField field,String tableName) {
		Map<String, Object> map = getDropFieldPrepareSqlMap(field,tableName);
		String sql = (String) map.get(key_sql);
		Object[] args = (Object[]) map.get(key_parameter);
		return update( sql,args);
	}

	public String getDropTablePrepareSql(DictionaryField field,String tableName){
		Map<String, Object> map = getDropFieldPrepareSqlMap(field,tableName);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getDropFieldPrepareSqlMap(DictionaryField field,String tableName){
		StringBuilder sb = new StringBuilder();
		sb.append(" alter table ");
		sb.append(" " + tableName);
		sb.append(" drop ");
		sb.append(" column ");
		sb.append(" " + field.getAttr(DictionaryField.field_name) + " ");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

}
