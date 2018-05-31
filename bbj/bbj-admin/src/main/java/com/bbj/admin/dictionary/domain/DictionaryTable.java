package com.bbj.admin.dictionary.domain;

import com.bbj.base.domain.BBJEntity;

/**
 * 表数据字典实体
 * @author bage
 *
 */
public class DictionaryTable extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String tableName = "admin_dictionary_table";
	
	public static final String table_name = "table_name";
	public static final String table_comment = "table_comment";
	public static final String table_description = "table_description";
	public static final String sequence_id = "sequence_id";
	public static final String table_remark = "table_remark";
	
	@Override
	public String initTable() {
		return tableName;
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				table_name,
				table_comment,
				table_description,
				sequence_id,
				
				table_remark
		};
		return attrs;
	}

}
