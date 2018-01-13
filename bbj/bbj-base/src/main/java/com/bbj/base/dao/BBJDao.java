package com.bbj.base.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.utils.StringUtils;

@SuppressWarnings("unchecked")
public class BBJDao<T extends BBJEntity>{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	private Class<T> currentEntityClass;
	private BBJEntity curruntBBJEntity = null;
	
	public BBJDao(){
        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();      
        currentEntityClass = (Class<T>) type.getActualTypeArguments()[0];
        
		try {
			curruntBBJEntity = currentEntityClass.newInstance();
			System.out.println(curruntBBJEntity);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加一条记录
	 * @param bbjEntity
	 * @return the number of rows affected
	 */
	public int insert(T bbjEntity){
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
	 * 根据主键进行删除
	 * @param primaryValue
	 * @return the number of rows affected
	 */
	public int deleteById(String primaryValue){
		String updateSql = " update " + curruntBBJEntity.getTableName() 
						+ " set " + BBJEntity.delete_state + " = " + BBJEntity.delete_state_yes 
						+ " where " + BBJEntity.id + " = ?";
		return jdbcTemplate.update(updateSql, new Object[]{primaryValue});
	}
	/**
	 * 修改一条记录
	 * @param bbjEntity
	 * @return the number of rows affected
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
	
	/**
	 * 根据主键值查找一个对象
	 * @param primaryValue
	 * @return
	 */
	public T queryById(String primaryValue){
		String sql = "  select "+curruntBBJEntity.getAttrKeysStr()
					+ " from " + curruntBBJEntity.getTableName() 
				+ " where " + BBJEntity.id + " = ? " 
				+ " and " + BBJEntity.delete_state + " <> ? ";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, primaryValue,BBJEntity.delete_state_yes);
		BBJEntity bbjEntity = null;
		if(rs.next()){
			bbjEntity = new BBJEntity() {
				private static final long serialVersionUID = 1L;
				@Override
				public String initTable() {
					return curruntBBJEntity.getTableName();
				}
				@Override
				public String[] initAttr() {
					return curruntBBJEntity.getAttrKeys().toArray(new String[0]);
				}
			};
			List<String> keys = curruntBBJEntity.getAttrKeys();
			for (int i = 0; i < keys.size(); i++) {
				bbjEntity.setAttr(keys.get(i), rs.getString(keys.get(i)));
			}
		}
		System.out.println("--------222----");
		T temp =  (T) bbjEntity;
		System.out.println("-------23333-----" + temp);
		return temp;

	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public List<T> queryByPage(int tagPage,int pageSize){
		return this.queryByPage(tagPage, pageSize, null);
	}

	/**
	 * 分页查询
	 * @return
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
		sb.append(" select "+curruntBBJEntity.getAttrKeysStr() );
		sb.append( " from " + curruntBBJEntity.getTableName()  );
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
		System.out.println(sb.toString());
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sb.toString(),listParam.toArray(new Object[0]));
		List<BBJEntity> list = new ArrayList<BBJEntity>();
		while(rs.next()){
			BBJEntity bbjEntity = new BBJEntity() {
				private static final long serialVersionUID = 1L;
				@Override
				public String initTable() {
					return curruntBBJEntity.getTableName();
				}
				@Override
				public String[] initAttr() {
					return curruntBBJEntity.getAttrKeys().toArray(new String[0]);
				}
			};
			List<String> keys = curruntBBJEntity.getAttrKeys();
			for (int i = 0; i < keys.size(); i++) {
				bbjEntity.setAttr(keys.get(i), rs.getString(keys.get(i)));
			}
			list.add(bbjEntity);
		}
		return (List<T>) list;
	}
	
	/**
	 * 查询有多少条记录
	 * @param sqlFilter 
	 * @return
	 */
	public int getTotalRow(){
		return getTotalRow(null);
	}
	/**
	 * 查询有多少条记录
	 * @param sqlFilter 
	 * @return
	 */
	public int getTotalRow(SqlFilter<T> sqlFilter){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from ");
		sb.append( curruntBBJEntity.getTableName() );
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
}
