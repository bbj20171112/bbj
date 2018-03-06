package com.bbj.base.domain.dictionary;

import com.bbj.base.domain.BBJEntity;

/**
 * 表数据字典实体
 * @author bage
 *
 */
public class DictionaryTable extends BBJEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public String initTable() {
		return "dictionary_table";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				"id", 
				"table_name",
				"table_comment",
				"table_description",
				"sequence_id",
				
				"table_remark",
		};
		return attrs;
	}

}
