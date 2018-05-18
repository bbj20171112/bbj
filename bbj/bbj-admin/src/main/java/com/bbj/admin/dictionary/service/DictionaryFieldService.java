package com.bbj.admin.dictionary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.admin.dictionary.dao.DictionaryFieldDao;
import com.bbj.admin.dictionary.dao.DictionaryReferenceDao;
import com.bbj.admin.dictionary.dao.DictionaryTableDao;
import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.admin.dictionary.domain.DictionaryReference;
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;

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
		DictionaryTable table = dictionaryTableDao.queryById(field.getAttr(DictionaryField.tableId));
		String tableName = table.getAttr(DictionaryTable.table_name);
		
		DictionaryReference dictionaryReference = dictionaryReferenceDao.queryById(field.getAttr(DictionaryField.fieldType));
		if(dictionaryReference == null){
			field.setAttr(DictionaryField.fieldType, field.getAttr(DictionaryField.fieldType));
		} else {
			field.setAttr(DictionaryField.fieldType, dictionaryReference.getAttr(DictionaryReference.reference_value));
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
		DictionaryTable table = dictionaryTableDao.queryById(field.getAttr(DictionaryField.tableId));
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

	/**
	 * 更新序号
	 * @param id
	 * @param tableId
	 * @param type
	 * @return
	 */
	@Transactional
	public int updateOrdernumber(String id,String type) {
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		DictionaryField current = dictionaryFieldDao.queryById(id);
		String currentOrder = current.getAttr(DictionaryField.fieldOrderNumber);
		if("previous".equals(type)){
	        List<WhereFilter> list = new ArrayList<WhereFilter>();
	        list.add(new WhereFilter(DictionaryField.tableId, "=",current.getAttr(DictionaryField.tableId)));
	        if(currentOrder != null && !"".equals(currentOrder)){
	        	list.add(new WhereFilter(DictionaryField.fieldOrderNumber, "<", currentOrder));
	        	list.add(new WhereFilter(DictionaryField.fieldOrderNumber, "is not null ",null));
	        	sqlFilter.addWhereFilter(list );
	        	DictionaryField max = dictionaryFieldDao.queryMaxSmallerThan(sqlFilter);
	        	String maxOrder = max.getAttr(DictionaryField.fieldOrderNumber);
	        	max.setAttr(DictionaryField.fieldOrderNumber, currentOrder);
	        	dictionaryFieldDao.update(max);
	        	current.setAttr(DictionaryField.fieldOrderNumber, maxOrder);
	        	dictionaryFieldDao.update(current);
	        } else {
				sqlFilter.addWhereFilter(list );
	        	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
	        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
	        	dictionaryFieldDao.update(current);
	        }
		} else if("next".equals(type)){
			List<WhereFilter> list = new ArrayList<WhereFilter>();
			if(currentOrder != null && !"".equals(currentOrder)){
		        list.add(new WhereFilter(DictionaryField.fieldOrderNumber, ">", currentOrder));
		        list.add(new WhereFilter(DictionaryField.fieldOrderNumber, "is not null",null));
		        list.add(new WhereFilter(DictionaryField.tableId, "=", current.getAttr(DictionaryField.tableId)));
		        sqlFilter.addWhereFilter(list );
		        DictionaryField min = dictionaryFieldDao.queryMinBiggerThan(sqlFilter);
		        if(min != null){
		        	String minOrder = min.getAttr(DictionaryField.fieldOrderNumber);
			        min.setAttr(DictionaryField.fieldOrderNumber, currentOrder);
			        dictionaryFieldDao.update(min);
			        current.setAttr(DictionaryField.fieldOrderNumber, minOrder);
			        dictionaryFieldDao.update(current);
		        } else {
		        	list.clear();
		        	sqlFilter.clear();
			        list.add(new WhereFilter(DictionaryField.tableId, "=", current.getAttr(DictionaryField.tableId)));
			        sqlFilter.addWhereFilter(list );
			    	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
		        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
		        	dictionaryFieldDao.update(current);
		        }
		    } else {
		    	list.add(new WhereFilter(DictionaryField.tableId, "=", current.getAttr(DictionaryField.tableId)));
		    	sqlFilter.addWhereFilter(list );
		    	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
	        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
	        	dictionaryFieldDao.update(current);
		    }
		} else if("first".equals(type)){
			List<WhereFilter> list = new ArrayList<WhereFilter>();
			list.add(new WhereFilter(DictionaryField.tableId, "=",current.getAttr(DictionaryField.tableId)));
			sqlFilter.addWhereFilter(list );
        	int min = dictionaryFieldDao.getMinOrderNumber(sqlFilter);
        	current.setAttr(DictionaryField.fieldOrderNumber, min - 1 + "");
        	dictionaryFieldDao.update(current);
		} else if("last".equals(type)){
			List<WhereFilter> list = new ArrayList<WhereFilter>();
			list.add(new WhereFilter(DictionaryField.tableId, "=",current.getAttr(DictionaryField.tableId)));
			sqlFilter.addWhereFilter(list );
        	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
        	dictionaryFieldDao.update(current);
		} else {
			return 0;
		}
		return 0;
	}
	
}
