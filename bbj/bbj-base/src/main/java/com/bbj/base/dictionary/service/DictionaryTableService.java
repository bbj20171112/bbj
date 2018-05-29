package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryTableDao;
import com.bbj.base.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.BBJServiceParam;

@Service
public class DictionaryTableService {
	
	@Autowired
	private DictionaryTableDao dictionaryTableDao;


	/**
	 * 查询<br><br>
	 * public DictionaryTable query(String id)
	 * @param serviceParam
	 * @return
	 */
	public DictionaryTable query(BBJServiceParam serviceParam){
		return dictionaryTableDao.query(serviceParam);
	}
	
	
	/**
	 * 分页查询<br><br>
	 * public List<DictionaryTable> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public List<DictionaryTable> queryByPage(BBJServiceParam serviceParam){
		return dictionaryTableDao.queryByPage(serviceParam);
	}
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public int getTotalRow(BBJServiceParam serviceParam){
		return dictionaryTableDao.getTotalRow(serviceParam);
	}
	
}
