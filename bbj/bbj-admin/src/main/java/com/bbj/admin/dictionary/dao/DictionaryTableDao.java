package com.bbj.admin.dictionary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.utils.StringUtils;
import com.bbj.base.utils.TimeUtils;

/**
 * 表数据字典的DAO实现
 * 
 * @author bage
 *
 */
@Repository
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
		return getJdbcTemplate().update( sql,args);
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

	@Override
	public int update(BBJDaoParam daoParam) {
		DictionaryTable paramEntity = daoParam.get(BBJDaoParam.keyEntity,DictionaryTable.class);
		
		List<String> listAttrKeys = new DictionaryTable().getAttrKeys();
		// 构造参数列表
		StringBuilder sbParamAttrPair = new StringBuilder();
		List<String> listParamAttr = new ArrayList<String>();
		for(int i = 0;i < listAttrKeys.size();i ++){
			String key = listAttrKeys.get(i);
			if(StringUtils.isNotEmpty(key) && !paramEntity.getId().equals(key)){
				if(BBJEntity.update_time.equals(key) && "".equals(paramEntity.getAttr(key))){
					paramEntity.setAttr(key, TimeUtils.getCurrentTime());
				}
				if(StringUtils.isNotEmpty(paramEntity.getAttr(key))){
					if(sbParamAttrPair.length() == 0){
						sbParamAttrPair.append(key + "=?");
					}else{
						sbParamAttrPair.append("," + key + "=?");
					}
					listParamAttr.add(paramEntity.getAttr(key));
				}
			}
		}
		listParamAttr.add(paramEntity.getAttr("table_name_new"));
		String insertSql = " update " + paramEntity.getTableName() 
						+ " set "
						+ sbParamAttrPair.toString()
						+ " where " + paramEntity.getId() + "=?";
		return getJdbcTemplate().update(insertSql, listParamAttr.toArray(new Object[0]));
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
		return getJdbcTemplate().update( sql,args);
	}

	public String getAlterTablePrepareSql(DictionaryTable table){
		Map<String, Object> map = getAlterTablePrepareSqlMap(table);
		return (String) map.get(key_sql);
	}
	
	public Map<String,Object> getAlterTablePrepareSqlMap(DictionaryTable table){
		String oldTableName = table.getAttr(DictionaryTable.table_name);
		String newTableName = table.getAttr("table_name_new");
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
		return getJdbcTemplate().update( sql,args);
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
