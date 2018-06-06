package com.bbj.base.dictionary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bbj.base.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.BBJServiceParam;

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
	
	public List<DictionaryReference> queryByPage(BBJServiceParam serviceParam) {
		// jdbcTemplate
		return null;
	}
	
	
}
