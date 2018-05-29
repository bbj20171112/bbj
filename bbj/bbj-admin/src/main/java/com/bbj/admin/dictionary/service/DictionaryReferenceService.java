package com.bbj.admin.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.admin.dictionary.dao.DictionaryReferenceDao;
import com.bbj.admin.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.BBJServiceParam;

@Service
public class DictionaryReferenceService {
	
	@Autowired
	private DictionaryReferenceDao dictionaryReferenceDao;

	/**
	 * 新增<br><br>
	 * public int insert(DictionaryReference reference)
	 * @param serviceParam
	 * @return
	 */
	public int insert(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.insert(serviceParam);
	}
	
	/**
	 * 删除<br><br>
	 * public int delete(String id)
	 * @param serviceParam
	 * @return
	 */
	public int delete(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.delete(serviceParam);
	}	

	/**
	 * 更新<br><br>
	 * public int update(DictionaryReference bbjEntity)
	 * @param serviceParam
	 * @return
	 */
	public int update(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.update(serviceParam);
	}

	/**
	 * 查询<br><br>
	 * public DictionaryReference query(String id)
	 * @param serviceParam
	 * @return
	 */
	public DictionaryReference query(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.query(serviceParam);
	}
	
	/**
	 * 分页查询<br><br>
	 * public List<DictionaryReference> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public List<DictionaryReference> queryByPage(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.queryByPage(serviceParam);
	}
	
	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public int getTotalRow(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.getTotalRow(serviceParam);
	}
	
}
