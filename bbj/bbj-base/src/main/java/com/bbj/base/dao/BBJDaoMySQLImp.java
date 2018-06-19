package com.bbj.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.bbj.base.dictionary.dao.DictionaryFieldDao;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.utils.StringUtils;
import com.bbj.base.utils.TimeUtils;

/**
 * 目前仅提供了基本的增删改查<br>
 * 如果需要额外的查询操作，可以通过 jdbcTemplate 进行调用执行相应的查询操作逻辑
 * 
 * @author bage
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BBJDaoMySQLImp<T extends BBJEntity> implements BBJDao<T>{
	
	@Autowired
	private JdbcTemplate jdbcTemplate; // jdbcTemplate
	private Class<T> currentBBJEntityClass; // 当前的实体类
	private BBJEntity currentBBJEntity = null; // 当前的实体对象
	
	@Autowired
	private DictionaryFieldDao dictionaryFieldDao;

	
	public BBJDaoMySQLImp(){
		
		// 获取当前的泛型类型
        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();      
        currentBBJEntityClass = (Class<T>) type.getActualTypeArguments()[0];
        
        // 实例化一个对象
		currentBBJEntity = newInstanceBBJEntity();
		
	}
	
	/**
	 * 示例化一个对象
	 * @return
	 */
	private BBJEntity newInstanceBBJEntity() {
		try {
			return currentBBJEntityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentBBJEntity;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	public Map<String, Object> queryForMap(BBJDaoParam daoParam) {
		String prepareSql = daoParam.getString(BBJDaoParam.keyPrepareSql);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		return jdbcTemplate.queryForMap(prepareSql, args);
	}



	public List<T> queryForList(BBJDaoParam daoParam) {
		String prepareSql = daoParam.getString(BBJDaoParam.keyPrepareSql);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		Class<T> elementType = (Class<T>) daoParam.get(BBJDaoParam.keyArgs);
		return jdbcTemplate.queryForList(prepareSql, args,elementType);
	}



	public T queryForObject(BBJDaoParam daoParam) {
		String prepareSql = daoParam.getString(BBJDaoParam.keyPrepareSql);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		Class<T> requiredType = (Class<T>) daoParam.get(BBJDaoParam.keyArgs);
		return jdbcTemplate.queryForObject(prepareSql, args, requiredType);
	}



	public int[] batchUpdate(BBJDaoParam daoParam) {
		String prepareSql = daoParam.getString(BBJDaoParam.keyPrepareSql);
		List<Object[]> batchArgs = (List<Object[]>) daoParam.get(BBJDaoParam.keyBatchArgs);
		return jdbcTemplate.batchUpdate(prepareSql, batchArgs);
	}



	public int insert(BBJDaoParam daoParam) {
		T bbjEntity = daoParam.get(BBJDaoParam.keyEntity,currentBBJEntityClass);
		List<String> listAttrKeys = bbjEntity.getAttrKeys();
		// 构造参数列表
		StringBuilder sbAttrKeys = new StringBuilder();
		StringBuilder sbParamAttr = new StringBuilder();
		List<String> listParamAttr = new ArrayList<String>();
		for(int i = 0;i < listAttrKeys.size();i ++){
			String key = listAttrKeys.get(i);
			if(StringUtils.isNotEmpty(key)){
				// 如果没有ID，设置当前时间
				if(bbjEntity.getAttr(bbjEntity.getId()) == "" || bbjEntity.getAttr(bbjEntity.getId()) == null){
					bbjEntity.setAttr(bbjEntity.getId(), "" + System.currentTimeMillis());
				}
				// 如果删除状态没有值，默认有效状态
				if(BBJEntity.delete_state.equals(key) && (null == bbjEntity.getAttr(key) || "".equals(bbjEntity.getAttr(key)))){
					bbjEntity.setAttr(key, BBJEntity.delete_state_not);
				}
				// 创建时间
				if(BBJEntity.create_time.equals(key) && "".equals(bbjEntity.getAttr(key))){
					bbjEntity.setAttr(key, TimeUtils.getCurrentTime());
				}
				if(StringUtils.isNotEmpty(bbjEntity.getAttr(key))){
					if(sbAttrKeys.length() == 0){
						sbAttrKeys.append(key);
						sbParamAttr.append("?");
					}else{
						sbAttrKeys.append(","+key);
						sbParamAttr.append(",?");
					}
					listParamAttr.add(bbjEntity.getAttr(key));
				}
			}
		}
		String insertSql = " insert into " + bbjEntity.getTableName() 
						+ "(" + sbAttrKeys.toString() + ")"
						+ "values("	+ sbParamAttr.toString() + ")" ;
		return jdbcTemplate.update(insertSql, listParamAttr.toArray(new Object[0]));
	}

	public int delete(BBJDaoParam daoParam) {
		String id = daoParam.getString(BBJDaoParam.keyId);
		String updateSql = " update " + currentBBJEntity.getTableName() 
		+ " set " + BBJEntity.delete_state + " = " + BBJEntity.delete_state_yes 
		+ " where " + currentBBJEntity.getId() + " = ?";
		return jdbcTemplate.update(updateSql, new Object[]{id});
	}



	public int update(BBJDaoParam daoParam) {
		
		T bbjEntity = daoParam.get(BBJDaoParam.keyEntity,currentBBJEntityClass);
		
		BBJDaoParam fieldParamDictionary = new BBJDaoParam()
				.addAttr(BBJDaoParam.keyTagPage, 1)
				.addAttr(BBJDaoParam.keyPageSize, 1000)
				.addAttr(BBJDaoParam.keySqlFilter, null);
		
		List<DictionaryField> dictionaryFields = dictionaryFieldDao.queryByPage(fieldParamDictionary);
		
				
		List<String> listAttrKeys = bbjEntity.getAttrKeys();
		// 构造参数列表
		StringBuilder sbParamAttrPair = new StringBuilder();
		List<String> listParamAttr = new ArrayList<String>();
		for(int i = 0;i < listAttrKeys.size();i ++){
			String key = listAttrKeys.get(i);
			if(StringUtils.isNotEmpty(key) && !currentBBJEntity.getId().equals(key)){
				if(BBJEntity.update_time.equals(key) && "".equals(bbjEntity.getAttr(key))){
					bbjEntity.setAttr(key, TimeUtils.getCurrentTime());
				}
				boolean isVarchar = false;
				for (int j = 0; j < dictionaryFields.size(); j++) {
					DictionaryField item = dictionaryFields.get(j);
					// 如果是字符类型
					if(item.getAttr(DictionaryField.fieldName).equals(key) && (item.getAttr(DictionaryField.fieldType).contains("varchar") || item.getAttr(DictionaryField.fieldType).contains("VARCHAR"))){
						isVarchar = true;
						break;
					}
				}
				if(isVarchar || StringUtils.isNotEmpty(bbjEntity.getAttr(key))){ //字符串或者不为空
					if(sbParamAttrPair.length() == 0){
						sbParamAttrPair.append(key + "=?");
					}else{
						sbParamAttrPair.append("," + key + "=?");
					}
					listParamAttr.add(bbjEntity.getAttr(key));
				} 
			}
		}
		listParamAttr.add(bbjEntity.getAttr(currentBBJEntity.getId()));
		String insertSql = " update " + bbjEntity.getTableName() 
						+ " set "
						+ sbParamAttrPair.toString()
						+ " where " + currentBBJEntity.getId() + "=?";
		return jdbcTemplate.update(insertSql, listParamAttr.toArray(new Object[0]));
	}



	public T query(BBJDaoParam daoParam) {
		String id = daoParam.getString(BBJDaoParam.keyId);
		String sql = "  select "+currentBBJEntity.getAttrKeysStr()
					+ " from " + currentBBJEntity.getTableName() 
					+ " where " + currentBBJEntity.getId() + " = ? " 
					+ " and " + BBJEntity.delete_state + " <> ? ";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id,BBJEntity.delete_state_yes);
		if(rs.next()){
		BBJEntity bbjEntity = newInstanceBBJEntity();
		List<String> keys = currentBBJEntity.getAttrKeys();
		for (int i = 0; i < keys.size(); i++) {
			bbjEntity.setAttr(keys.get(i), rs.getString(keys.get(i)));
		}
		return (T) bbjEntity;
		} 
		return null ;
	}



	public List<T> queryByPage(BBJDaoParam daoParam) {
		int tagPage = daoParam.get(BBJDaoParam.keyTagPage,Integer.class);
		int pageSize = daoParam.get(BBJDaoParam.keyPageSize,Integer.class);
		SqlFilter sqlFilter = daoParam.get(BBJDaoParam.keySqlFilter,SqlFilter.class);
		int totalRow = getTotalRow(daoParam);
		int totalPage = totalRow / pageSize;
		if(totalPage *  pageSize < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageSize * (tagPage - 1 );
		
		
		// 获取表的数字字典
		SqlFilter fieldSqlFilter = new BBJSqlFilter(DictionaryField.class);
		fieldSqlFilter.addWhereFilter(Arrays.asList(new WhereFilter(DictionaryField.tableName, WhereFilter.EQUALS, currentBBJEntity.getTableName() )));
		
		BBJDaoParam fieldParamDictionary = new BBJDaoParam()
				.addAttr(BBJDaoParam.keyTagPage, 1)
				.addAttr(BBJDaoParam.keyPageSize, 1000)
				.addAttr(BBJDaoParam.keySqlFilter, fieldSqlFilter);
		
		List<DictionaryField> dictionaryFields = dictionaryFieldDao.queryByPage(fieldParamDictionary);
		
		StringBuilder sbForeignSelect = new StringBuilder();
		StringBuilder sbForeignJoin = new StringBuilder();

		String selfTableAlias = "self";

		// 解析外键
		for (int i = 0; i < dictionaryFields.size(); i++) {
			DictionaryField dictionaryField = dictionaryFields.get(i);
			String referenceTableName = dictionaryField.getAttr(DictionaryField.fieldReferenceTableName);
			if(StringUtils.isNotEmpty(referenceTableName)){ // 外键
				
				String foreignField = dictionaryField.getAttr(DictionaryField.fieldReferenceTableFieldValue);
				String fieldName = dictionaryField.getAttr(DictionaryField.fieldName);

				String alias = "foreigner_" + fieldName ;
				String key = alias + "_" + foreignField; // 外键命名规则
				sbForeignSelect.append(", " + alias + "." + foreignField + " " + key);
				
				sbForeignJoin.append(" left join " + referenceTableName + " " + alias + " on " + selfTableAlias + "." + dictionaryField.getAttr(DictionaryField.fieldName) + "=" +alias + "." + dictionaryField.getAttr(DictionaryField.fieldReferenceTableFieldName) );
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select "+currentBBJEntity.getAttrKeysStr(selfTableAlias) + " " + sbForeignSelect.toString());
		sb.append( " from " + currentBBJEntity.getTableName() + " " + selfTableAlias + " " + sbForeignJoin.toString() );
		sb.append( " where " + selfTableAlias + "." + BBJEntity.delete_state + " <> ? " );
				
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(BBJEntity.delete_state_yes);
		if(sqlFilter != null && !sqlFilter.getListParam().isEmpty()){
			sb.append(" and " + sqlFilter.getSqlString());
			listParam.addAll(sqlFilter.getListParam());
		}
		sb.append(" order by " + currentBBJEntity.getId());
		sb.append(" limit ?,? ");
		listParam.add(startId);
		listParam.add(pageSize);
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sb.toString(),listParam.toArray(new Object[0]));
		List<BBJEntity> list = new ArrayList<BBJEntity>();
		while(rs.next()){
			BBJEntity bbjEntity = newInstanceBBJEntity();
			List<String> keys = currentBBJEntity.getAttrKeys();
			for (int i = 0; i < keys.size(); i++) {
				bbjEntity.setAttr(keys.get(i), rs.getString(keys.get(i)));
			}
			// 添加外键
			// 解析外键
			for (int i = 0; i < dictionaryFields.size(); i++) {
				DictionaryField dictionaryField = dictionaryFields.get(i);
				String referenceTableName = dictionaryField.getAttr(DictionaryField.fieldReferenceTableName);
				if(StringUtils.isNotEmpty(referenceTableName)){ // 外键
					
					String foreignField = dictionaryField.getAttr(DictionaryField.fieldReferenceTableFieldValue);
					String fieldName = dictionaryField.getAttr(DictionaryField.fieldName);

					String key = "foreigner_" + fieldName + "_" + foreignField;
					bbjEntity.setAttr(key, rs.getString(key));

				}
			}
			
			list.add(bbjEntity);
		}
		return (List<T>) list;
	}


	public int getTotalRow(BBJDaoParam daoParam) {
		SqlFilter sqlFilter = daoParam.get(BBJDaoParam.keySqlFilter,SqlFilter.class);
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from ");
		sb.append( currentBBJEntity.getTableName() );
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
