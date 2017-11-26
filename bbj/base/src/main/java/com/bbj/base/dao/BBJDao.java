package com.bbj.base.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.bbj.base.domain.BBJEntity;

public abstract class BBJDao{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	protected BBJEntity curruntBBJEntity;
	
	public BBJDao() {
		curruntBBJEntity = initEntity();
	}

	public abstract BBJEntity initEntity();

	/**
	 * 
	 * @param primaryValue
	 * @return
	 */
	public BBJEntity queryForBBJEntity(String primaryValue){
		String sql = "select "+curruntBBJEntity.getAttrKeysStr()
		+" from " + curruntBBJEntity.getTableName() 
		+ " where " + curruntBBJEntity.getPrimaryAttr() + " = ?";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, primaryValue);
		
		if(rs.next()){
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
			return bbjEntity;
		}
		return null;
	}
	/**
	 * 
	 * @param cls
	 * @return
	 */
	public <T> T queryForObject(Class<T> cls){
		return this.jdbcTemplate.queryForObject("select count(*) from "+curruntBBJEntity.getTableName(), cls);
	}
	
	
}
