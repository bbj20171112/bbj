package com.bbj.base.dictionary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.bbj.base.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.utils.StringUtils;

/**
 * 参照值数据字典的DAO实现
 * 
 * @author bage
 *
 */
@Repository
public class DictionaryReferenceDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // jdbcTemplate
	
	/**
	 * 分页查询<br><br>
	 * public List<DictionaryField> queryByPage(String tableName, String referenceFieldName, List<String> referenceFieldValues,int tagPage, int pageSize, SqlFilter sqlFilter)
	 */
	public List<DictionaryReference> queryByPage(BBJDaoParam daoParam) {
		String tableName = daoParam.getString(DictionaryReference.tableName);
		String referenceFieldName = daoParam.getString(DictionaryReference.referenceFieldName);
		Object referenceFieldValueObj = daoParam.get(DictionaryReference.referenceFieldValue);
		SqlFilter sqlFilter = daoParam.get(BBJDaoParam.keySqlFilter,SqlFilter.class);
		int pageSize = daoParam.getInt(BBJDaoParam.keyPageSize);
		int tagPage = daoParam.getInt(BBJDaoParam.keyTagPage);
		
		if(referenceFieldValueObj != null){
			
			int totalRow = getTotalRow(daoParam);
			int totalPage = totalRow / pageSize;
			if(totalPage *  pageSize < totalRow){ // 不能够整除，总页数应该 + 1
				totalPage = totalPage + 1;
			}
			if(tagPage <= 0){
				tagPage = totalPage;
			}		
			int startId = pageSize * (tagPage - 1 );
			
			@SuppressWarnings("unchecked")
			List<String> referenceFieldValues = (List<String>) referenceFieldValueObj;
			StringBuilder sb = new StringBuilder();
			sb .append(" select "+referenceFieldName + "," +StringUtils.getListString(referenceFieldValues,",") + "" );
			sb.append("  from " + tableName + "" );
			sb.append("  where " + BBJEntity.delete_state + " <> ? " );
					
			List<Object> listParam = new ArrayList<Object>();
			listParam.add(BBJEntity.delete_state_yes);
			if(sqlFilter != null && !sqlFilter.getListParam().isEmpty()){
				sb.append(" and " + sqlFilter.getSqlString());
				listParam.addAll(sqlFilter.getListParam());
			}
			sb.append(" limit ?,? ");
			listParam.add(startId);
			listParam.add(pageSize);
			SqlRowSet rs = jdbcTemplate.queryForRowSet(sb.toString(),listParam.toArray(new Object[0]));
			List<DictionaryReference> list = new ArrayList<DictionaryReference>();
			DictionaryReference temp = null;
			while(rs.next()){
				temp = new DictionaryReference();
				List<Map<String,String>> referenceFieldValuesInDb = new ArrayList<Map<String,String>>();
				for (int i = 0; i < referenceFieldValues.size(); i++) {
					Map<String,String> map = new HashMap<String,String>();
					map.put(referenceFieldValues.get(i), rs.getString(referenceFieldValues.get(i)));
					referenceFieldValuesInDb.add(map);
				}
				temp.setAttr(DictionaryReference.tableName, tableName);
				temp.setAttr(DictionaryReference.referenceFieldName, rs.getString(referenceFieldName));
				temp.setAttr(DictionaryReference.referenceFieldValue, referenceFieldValuesInDb);
				list.add(temp);
			}
			return list;
		}
		return new ArrayList<DictionaryReference>();
	}
	

	public int getTotalRow(BBJDaoParam daoParam) {
		
		String tableName = daoParam.getString(DictionaryReference.tableName);
		SqlFilter sqlFilter = daoParam.get(BBJDaoParam.keySqlFilter,SqlFilter.class);
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from ");
		sb.append( tableName );
		sb.append( " where " + BBJEntity.delete_state + " <> ? " );
		
		List<Object> list = new ArrayList<Object>();
		list.add(BBJEntity.delete_state_yes);
		if(sqlFilter != null && !sqlFilter.getListParam().isEmpty()){
			sb.append(" and " + sqlFilter.getSqlString());
			list.addAll(sqlFilter.getListParam());
		}
		int row = jdbcTemplate.queryForObject(sb.toString(),list.toArray(new Object[0]),Integer.class);
		return row <=0 ? 1 : row;
	}

}
