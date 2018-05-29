package com.bbj.admin.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.admin.dictionary.dao.DictionaryTableDao;
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.BBJServiceParam;

@Service
public class DictionaryTableService {
	
	@Autowired
	private DictionaryTableDao dictionaryTableDao;

	/**
	 * 新增<br><br>
	 * public int insert(DictionaryTable table)
	 * @param serviceParam
	 * @return
	 */
	@Transactional
	public int insert(BBJServiceParam serviceParam){
		int rows = 0;
		DictionaryTable table = serviceParam.get(BBJServiceParam.keyEntity,DictionaryTable.class);
		if(table  == null){
			return rows;
		}
		
		rows += dictionaryTableDao.createTable(table); // 创建一个表
		rows += dictionaryTableDao.insert(serviceParam); // 插入到数据字典表
		
		return rows;
	}
	
	
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @param serviceParam
	 * @return
	 */
	@Transactional
	public int delete(BBJServiceParam serviceParam){
		int rows = 0;
		DictionaryTable table = this.query(serviceParam);
		if(table  == null){
			return rows;
		}
		
		rows += dictionaryTableDao.dropTable(table); 
		rows += dictionaryTableDao.delete(serviceParam);
		
		return rows;
	}	

	/**
	 * 更新<br><br>
	 * public int update(DictionaryTable table)
	 * @param serviceParam
	 * @return
	 */
	@Transactional
	public int update(BBJServiceParam serviceParam){
		int rows = 0;
		DictionaryTable table = serviceParam.get(BBJServiceParam.keyEntity,DictionaryTable.class);
		if(table   == null){
			return rows;
		}
		 
		rows += dictionaryTableDao.alterTable(table);
		rows += dictionaryTableDao.update(serviceParam);
		
		return rows;
	}

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
