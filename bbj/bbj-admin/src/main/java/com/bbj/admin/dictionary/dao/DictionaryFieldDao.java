package com.bbj.admin.dictionary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;

/**
 * 字段数据字典的DAO实现
 * @author bage
 *
 */
@Repository
public class DictionaryFieldDao extends BBJDaoImp<DictionaryField>{
	
	private static final String key_sql = "key_sql";
	private static final String key_parameter = "key_parameter";

	
	
	
	@Override
	public List<DictionaryField> queryByPage(int tagPage, int pageSize, SqlFilter sqlFilter) {

		DictionaryField temp = new DictionaryField();
		int totalRow = getTotalRow(sqlFilter);
		int totalPage = totalRow / pageSize;
		if(totalPage *  pageSize < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageSize * (tagPage - 1 );
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select "+temp.getAttrKeysStr("a") + ",b.table_name" );
		sb.append("  from " + temp.getTableName() + " a left join " + DictionaryTable.tableName + " b" );
		sb.append(" 	on a.table_id = b.id ");
		sb.append("  where a." + BBJEntity.delete_state + " <> ? " );
				
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(BBJEntity.delete_state_yes);
		if(sqlFilter != null && !sqlFilter.getListParam().isEmpty()){
			sb.append(" and " + sqlFilter.getSqlString());
			listParam.addAll(sqlFilter.getListParam());
		}
		sb.append(" order by " + temp.getId());
		sb.append(" limit ?,? ");
		listParam.add(startId);
		listParam.add(pageSize);
		SqlRowSet rs = getJdbcTemplate().queryForRowSet(sb.toString(),listParam.toArray(new Object[0]));
		List<DictionaryField> list = new ArrayList<DictionaryField>();
		while(rs.next()){
			temp = new DictionaryField();
			List<String> keys = temp.getAttrKeys();
			for (int i = 0; i < keys.size(); i++) {
				temp.setAttr(keys.get(i), rs.getString(keys.get(i)));
			}
			temp.setAttr("table_name", rs.getString("table_name"));
			list.add(temp);
		}
		return list;
		// return super.queryByPage(tagPage, pageSize, sqlFilter);
	}

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
		sb.append(" " + tableName + " ");
		sb.append(" add ");
		sb.append(" " + field.getAttr(DictionaryField.field_name) + " ");
		sb.append(" " + field.getAttr(DictionaryField.field_type) + " ");
		
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
		sb.append(" " + tableName + " ");
		sb.append(" drop ");
		sb.append(" column ");
		sb.append(" " + field.getAttr(DictionaryField.field_name) + " ");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(key_sql, sb.toString());
		map.put(key_parameter, new Object[]{});
		return map;
	}

}
