package com.bbj.base.dictionary.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.bbj.base.dao.BBJDaoImp;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;

/**
 * 字段数据字典的DAO实现
 * @author bage
 *
 */
@Repository
public class DictionaryFieldDao extends BBJDaoImp<DictionaryField>{
	
	
	/**
	 * 分页查询<br><br>
	 * public List<DictionaryField> queryByPage(int tagPage, int pageSize, SqlFilter sqlFilter)
	 */
	@Override
	public List<DictionaryField> queryByPage(BBJDaoParam daoParam) {
		SqlFilter sqlFilter = daoParam.get(BBJDaoParam.keySqlFilter,SqlFilter.class);
		int pageSize = daoParam.getInt(BBJDaoParam.keyPageSize);
		int tagPage = daoParam.getInt(BBJDaoParam.keyTagPage);
		
		DictionaryField temp = new DictionaryField();
		int totalRow = getTotalRow(daoParam);
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
		sb.append(" order by if(isnull("+DictionaryField.fieldOrderNumber+"),1,0),"+DictionaryField.fieldOrderNumber+" ");
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
	}

}
