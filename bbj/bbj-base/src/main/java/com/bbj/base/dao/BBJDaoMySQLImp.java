package com.bbj.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.utils.StringUtils;

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
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#insert(T)
	 */
	public int insert(T bbjEntity){
		if(inputIsValid(bbjEntity)){
			return 0;
		}
		List<String> listAttrKeys = bbjEntity.getAttrKeys();
		// 构造参数列表
		StringBuilder sbAttrKeys = new StringBuilder();
		StringBuilder sbParamAttr = new StringBuilder();
		List<String> listParamAttr = new ArrayList<String>();
		for(int i = 0;i < listAttrKeys.size();i ++){
			String key = listAttrKeys.get(i);
			if(StringUtils.isNotEmpty(key)){
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
		String insertSql = " insert into " + bbjEntity.getTableName() 
						+ "(" + sbAttrKeys.toString() + ")"
						+ "values("	+ sbParamAttr.toString() + ")" ;
		return jdbcTemplate.update(insertSql, listParamAttr.toArray(new Object[0]));
	}

	/**
	 * 目前仅仅是判断是否为空
	 * @param bbjEntity
	 * @return
	 */
	private boolean inputIsValid(T bbjEntity) {
		if(bbjEntity == null){
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#deleteById(java.lang.String)
	 */
	public int deleteById(String id){
		String updateSql = " update " + currentBBJEntity.getTableName() 
						+ " set " + BBJEntity.delete_state + " = " + BBJEntity.delete_state_yes 
						+ " where " + BBJEntity.id + " = ?";
		return jdbcTemplate.update(updateSql, new Object[]{id});
	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#update(T)
	 */
	public int update(T bbjEntity){
		List<String> listAttrKeys = bbjEntity.getAttrKeys();
		// 构造参数列表
		StringBuilder sbParamAttrPair = new StringBuilder();
		List<String> listParamAttr = new ArrayList<String>();
		for(int i = 0;i < listAttrKeys.size();i ++){
			String key = listAttrKeys.get(i);
			if(StringUtils.isNotEmpty(key) && !BBJEntity.id.equals(key)){
				if(sbParamAttrPair.length() == 0){
					sbParamAttrPair.append(key + "=?");
				}else{
					sbParamAttrPair.append("," + key + "=?");
				}
				listParamAttr.add(bbjEntity.getAttr(key));
			}
		}
		listParamAttr.add(bbjEntity.getAttr(BBJEntity.id));
		String insertSql = " update " + bbjEntity.getTableName() 
						+ " set "
						+ sbParamAttrPair.toString()
						+ " where " + BBJEntity.id + "=?";
		return jdbcTemplate.update(insertSql, listParamAttr.toArray(new Object[0]));
	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#queryById(java.lang.String)
	 */
	public T queryById(String id){
		String sql = "  select "+currentBBJEntity.getAttrKeysStr()
					+ " from " + currentBBJEntity.getTableName() 
				+ " where " + BBJEntity.id + " = ? " 
				+ " and " + BBJEntity.delete_state + " <> ? ";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id,BBJEntity.delete_state_yes);
		BBJEntity bbjEntity = newInstanceBBJEntity();
		if(rs.next()){
			List<String> keys = currentBBJEntity.getAttrKeys();
			for (int i = 0; i < keys.size(); i++) {
				bbjEntity.setAttr(keys.get(i), rs.getString(keys.get(i)));
			}
		}
		return (T) bbjEntity;

	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#queryByPage(int, in3t)
	 */
	public List<T> queryByPage(int tagPage,int pageSize){
		return this.queryByPage(tagPage, pageSize, null);
	}

	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#queryByPage(int, int, com.bbj.base.domain.SqlFilter)
	 */
	public List<T> queryByPage(int tagPage,int pageSize,SqlFilter<T> sqlFilter){
		
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
		sb.append(" select "+currentBBJEntity.getAttrKeysStr() );
		sb.append( " from " + currentBBJEntity.getTableName()  );
		sb.append( " where " + BBJEntity.delete_state + " <> ? " );
				
		List<Object> listParam = new ArrayList<Object>();
		listParam.add(BBJEntity.delete_state_yes);
		if(sqlFilter != null){
			sb.append(" and " + sqlFilter.getSqlString());
			listParam.addAll(sqlFilter.getListParam());
		}
		sb.append(" order by " + BBJEntity.id);
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
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#getTotalRow()
	 */
	public int getTotalRow(){
		return getTotalRow(null);
	}
	
	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#getTotalRow(com.bbj.base.domain.SqlFilter)
	 */
	public int getTotalRow(SqlFilter<T> sqlFilter){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from ");
		sb.append( currentBBJEntity.getTableName() );
		sb.append( " where " + BBJEntity.delete_state + " <> ? " );
		
		List<Object> list = new ArrayList<Object>();
		list.add(BBJEntity.delete_state_yes);
		if(sqlFilter != null){
			sb.append(" and " + sqlFilter.getSqlString());
			list.addAll(sqlFilter.getListParam());
		}
		int row = jdbcTemplate.queryForObject(sb.toString(),list.toArray(new Object[0]),Integer.class);
		return row <=0 ? 1 : row;
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

	/* (non-Javadoc)
	 * @see com.bbj.base.dao.BBJDao#update(java.lang.String,)
	 */
	public int update(String prepareSql, Object... args) {
		return jdbcTemplate.update(prepareSql, args);
	}

	public int[] batchUpdate(String prepareSql, List<Object[]> batchArgs) {
		return jdbcTemplate.batchUpdate(prepareSql,batchArgs);
	}

	public T queryForObject(String prepareSql,Object[] args, Class<T> requiredType) {
		return jdbcTemplate.queryForObject(prepareSql,args, requiredType);
	}

	
	public List<T> queryForList(String prepareSql,Object[] args, Class<T> elementType) {
		return jdbcTemplate.queryForList(prepareSql, args, elementType);
	}
	
	public Map<String, Object> queryForMap(String prepareSql,Object... args){
		return jdbcTemplate.queryForMap(prepareSql, args);
	}
	
}
