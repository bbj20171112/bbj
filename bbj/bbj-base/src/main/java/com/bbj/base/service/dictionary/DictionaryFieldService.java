package com.bbj.base.service.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.base.dao.dictionary.DictionaryFieldDao;
import com.bbj.base.dao.dictionary.DictionaryReferenceDao;
import com.bbj.base.dao.dictionary.DictionaryTableDao;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.dictionary.DictionaryField;
import com.bbj.base.domain.dictionary.DictionaryReference;
import com.bbj.base.domain.dictionary.DictionaryTable;

@Service(value = "dictionaryFieldService")
public class DictionaryFieldService
{
	@Autowired
	private DictionaryReferenceDao dictionaryReferenceDao;
	@Autowired
	private DictionaryFieldDao dictionaryFieldDao;
	@Autowired
	private DictionaryTableDao dictionaryTableDao;
	
	@Transactional
	public int insert(DictionaryField field){
		int rows = 0;
		if(field == null){
			return rows;
		}
		DictionaryTable table = dictionaryTableDao.queryById(field.getAttr(DictionaryField.table_id));
		String tableName = table.getAttr(DictionaryTable.table_name);
		
		DictionaryReference dictionaryReference = dictionaryReferenceDao.queryById(field.getAttr(DictionaryField.field_type));
		if(dictionaryReference == null){
			field.setAttr(DictionaryField.field_type, field.getAttr(DictionaryField.field_type));
		} else {
			field.setAttr(DictionaryField.field_type, dictionaryReference.getAttr(DictionaryReference.reference_value));
		}
		rows += dictionaryFieldDao.insert(field); // 插入到数据字典表
		rows += dictionaryFieldDao.createField(field,tableName); // 创建一个字段

		return rows;
	}
	
	@Transactional
	public int deleteById(String id){
		int rows = 0;
		if(id == null){
			return rows;
		}
		DictionaryField field = dictionaryFieldDao.queryById(id);
		DictionaryTable table = dictionaryTableDao.queryById(field.getAttr(DictionaryField.table_id));
		String tableName = table.getAttr(DictionaryTable.table_name);

		rows += dictionaryFieldDao.deleteById(id); // 从数据字典表删除
		rows += dictionaryFieldDao.dropField(field,tableName); // 创建一个字段

		return rows;
	}

	public int update(DictionaryField bbjEntity){
		return dictionaryFieldDao.update(bbjEntity);
	}

	public DictionaryField queryById(String id){
		return dictionaryFieldDao.queryById(id);
	}

	public List<DictionaryField> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return dictionaryFieldDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	public int getTotalRow(SqlFilter sqlFilter){
		return dictionaryFieldDao.getTotalRow(sqlFilter);
	}
	
}
