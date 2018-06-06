package com.bbj.base.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.base.dictionary.dao.DictionaryReferenceDao;
import com.bbj.base.dictionary.domain.DictionaryReference;
import com.bbj.base.domain.BBJServiceParam;

@Service
public class DictionaryReferenceService {
	
	@Autowired
	private DictionaryReferenceDao dictionaryReferenceDao;


	/**
	 * 分页查询<br><br>
	 * public List<DictionaryReference> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public List<DictionaryReference> queryByPage(BBJServiceParam serviceParam){
		return dictionaryReferenceDao.queryByPage(serviceParam);
	}
	
}
