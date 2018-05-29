package com.bbj.admin.dictionary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbj.admin.dictionary.dao.DictionaryFieldDao;
import com.bbj.admin.dictionary.dao.DictionaryTableDao;
import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.base.domain.BBJDaoParam;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;


@Service
public class DictionaryFieldService
{
	@Autowired
	private DictionaryFieldDao dictionaryFieldDao;
	@Autowired
	private DictionaryTableDao dictionaryTableDao;
	
	/**
	 * 新增<br><br>
	 * public int insert(DictionaryField field)
	 * @param daoParam
	 * @return
	 */
	@Transactional
	public int insert(BBJServiceParam serviceParam){
		int rows = 0;
		DictionaryField field = serviceParam.get(BBJServiceParam.keyEntity,DictionaryField.class);
		if(field == null){
			return rows;
		}
		DictionaryTable table = dictionaryTableDao.query(new BBJDaoParam().addAttr(BBJDaoParam.keyId, field.getAttr(DictionaryField.tableId)));
		String tableName = table.getAttr(DictionaryTable.table_name);
		
		rows += dictionaryFieldDao.insert(serviceParam); // 插入到数据字典表
		rows += dictionaryFieldDao.createField(field,tableName); // 创建一个字段

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
		
		String id = serviceParam.get(BBJServiceParam.keyId,String.class);

		if(id == null){
			return rows;
		}
		DictionaryField field = dictionaryFieldDao.query(serviceParam);
		DictionaryTable table = dictionaryTableDao.query(new BBJServiceParam().addAttr(BBJServiceParam.keyId,field.getAttr(DictionaryField.tableId)));
		String tableName = table.getAttr(DictionaryTable.table_name);

		rows += dictionaryFieldDao.delete(serviceParam); // 从数据字典表删除
		rows += dictionaryFieldDao.dropField(field,tableName); // 创建一个字段

		return rows;
	}

	/**
	 * 更新<br><br>
	 * public int update(DictionaryField bbjEntity)
	 * @param serviceParam
	 * @return
	 */
	public int update(BBJServiceParam serviceParam){
		return dictionaryFieldDao.update(serviceParam);
	}
	
	
	/**
	 * 查找<br><br>
	 * public DictionaryField query(String id)
	 * @param serviceParam
	 * @return
	 */
	public DictionaryField query(BBJServiceParam serviceParam){
		return dictionaryFieldDao.query(serviceParam);
	}

	/**
	 * 分页<br><br>
	 * public List<DictionaryField> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public List<DictionaryField> queryByPage(BBJServiceParam serviceParam){
		return dictionaryFieldDao.queryByPage(serviceParam);
	}
	

	/**
	 * 获取总数<br><br>
	 * public int getTotalRow(SqlFilter sqlFilter)
	 * @param serviceParam
	 * @return
	 */
	public int getTotalRow(BBJServiceParam serviceParam){
		return dictionaryFieldDao.getTotalRow(serviceParam);
	}

	/**
	 * 更新序号<br><br>
	 * public int updateOrdernumber(String id,String type)
	 * @param serviceParam
	 * @return
	 */
	@Transactional
	public int updateOrdernumber(BBJServiceParam serviceParam) {
		String id = serviceParam.getString("id");
		String type = serviceParam.getString("type");
		
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		DictionaryField current = dictionaryFieldDao.query(new BBJDaoParam().addAttr(BBJDaoParam.keyId, id));
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
	        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, max));
	        	current.setAttr(DictionaryField.fieldOrderNumber, maxOrder);
	        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
	        } else {
				sqlFilter.addWhereFilter(list );
	        	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
	        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
	        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
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
			        dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, min));
			        current.setAttr(DictionaryField.fieldOrderNumber, minOrder);
			        dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
		        } else {
		        	list.clear();
		        	sqlFilter.clear();
			        list.add(new WhereFilter(DictionaryField.tableId, "=", current.getAttr(DictionaryField.tableId)));
			        sqlFilter.addWhereFilter(list );
			    	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
		        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
		        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
		        }
		    } else {
		    	list.add(new WhereFilter(DictionaryField.tableId, "=", current.getAttr(DictionaryField.tableId)));
		    	sqlFilter.addWhereFilter(list );
		    	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
	        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
	        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
		    }
		} else if("first".equals(type)){
			List<WhereFilter> list = new ArrayList<WhereFilter>();
			list.add(new WhereFilter(DictionaryField.tableId, "=",current.getAttr(DictionaryField.tableId)));
			sqlFilter.addWhereFilter(list );
        	int min = dictionaryFieldDao.getMinOrderNumber(sqlFilter);
        	current.setAttr(DictionaryField.fieldOrderNumber, min - 1 + "");
        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
		} else if("last".equals(type)){
			List<WhereFilter> list = new ArrayList<WhereFilter>();
			list.add(new WhereFilter(DictionaryField.tableId, "=",current.getAttr(DictionaryField.tableId)));
			sqlFilter.addWhereFilter(list );
        	int max = dictionaryFieldDao.getMaxOrderNumber(sqlFilter);
        	current.setAttr(DictionaryField.fieldOrderNumber, max + 1 + "");
        	dictionaryFieldDao.update(new BBJDaoParam().addAttr(BBJDaoParam.keyEntity, current));
		} else {
			return 0;
		}
		return 0;
	}
	
}
