package com.bbj.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
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
		String prepareSql = daoParam.get(BBJDaoParam.keyPrepareSql,String.class);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		return jdbcTemplate.queryForMap(prepareSql, args);
	}



	public List<T> queryForList(BBJDaoParam daoParam) {
		String prepareSql = daoParam.get(BBJDaoParam.keyPrepareSql,String.class);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		Class<T> elementType = (Class<T>) daoParam.get(BBJDaoParam.keyArgs);
		return jdbcTemplate.queryForList(prepareSql, args,elementType);
	}



	public T queryForObject(BBJDaoParam daoParam) {
		String prepareSql = daoParam.get(BBJDaoParam.keyPrepareSql,String.class);
		Object[] args = daoParam.get(BBJDaoParam.keyArgs,Object[].class);
		Class<T> requiredType = (Class<T>) daoParam.get(BBJDaoParam.keyArgs);
		return jdbcTemplate.queryForObject(prepareSql, args, requiredType);
	}



	public int[] batchUpdate(BBJDaoParam daoParam) {
		String prepareSql = daoParam.get(BBJDaoParam.keyPrepareSql,String.class);
		List<Object[]> batchArgs = (List<Object[]>) daoParam.get(BBJDaoParam.keyBatchArgs);
		return jdbcTemplate.batchUpdate(prepareSql, batchArgs);
	}



	public int deleteById(BBJDaoParam daoParam) {
		String id = daoParam.get(BBJDaoParam.keyId,String.class);
		String updateSql = " update " + currentBBJEntity.getTableName() 
		+ " set " + BBJEntity.delete_state + " = " + BBJEntity.delete_state_yes 
		+ " where " + currentBBJEntity.getId() + " = ?";
		return jdbcTemplate.update(updateSql, new Object[]{id});
	}



	public int update(BBJDaoParam daoParam) {
		
		T bbjEntity = daoParam.get(BBJDaoParam.keyEntity,currentBBJEntityClass);
		
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
				if(StringUtils.isNotEmpty(bbjEntity.getAttr(key))){
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



	public T queryById(BBJDaoParam daoParam) {
		String id = daoParam.get(BBJDaoParam.keyId,String.class);
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
		int totalRow = getTotalRow(new BBJDaoParam().setAttr(BBJDaoParam.keySqlFilter, sqlFilter)));
		int totalPage = totalRow / pageSize;
		if(totalPage *  pageSize < totalRow){ // 不能够整除，总页数应该 + 1
			totalPage = totalPage + 1;
		}
		if(tagPage <= 0){
			tagPage = totalPage;
		}		
		int startId = pageSize * (tagPage - 1 );
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select "+currentBBJEntity.getAttrKeysStr() );
		sb.append( " from " + currentBBJEntity.getTableName()  );
		sb.append( " where " + BBJEntity.delete_state + " <> ? " );
				
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
