package com.bbj.base.domain.dictionary;

import com.bbj.base.domain.BBJEntity;

/**
 * 字段数据字典实体
 * @author bage
 *
 */
public class DictionaryField extends BBJEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public String initTable() {
		return "dictionary_field";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				"id", 
				"table_id", 
				"field_name",
				"field_name_comment",
				"field_type",
				"field_constraint",
				"field_constraint_comment",
				"field_reference",
				"field_reference_comment",
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}

}
