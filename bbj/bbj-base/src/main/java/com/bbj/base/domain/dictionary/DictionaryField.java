package com.bbj.base.domain.dictionary;

import com.bbj.base.domain.BBJEntity;

/**
 * 字段数据字典实体
 * @author bage
 *
 */
public class DictionaryField extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String id = "id"; 
	public static final String table_id = "table_id";
	public static final String field_name = "field_name"; 
	public static final String field_name_comment = "field_name_comment"; 
	public static final String field_key_type = "field_key_type"; 
	public static final String field_key_type_comment = "field_key_type_comment"; 
	public static final String field_constraint = "field_constraint"; 
	public static final String field_constraint_comment = "field_constraint_comment"; 
	public static final String field_reference = "field_reference"; 
	public static final String field_reference_comment="field_reference_comment"; 
	
	@Override
	public String initTable() {
		return "dictionary_field";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				id, 
				table_id, 
				field_name,
				field_name_comment,
				field_key_type,
				field_key_type_comment,
				field_constraint,
				field_constraint_comment,
				field_reference,
				field_reference_comment,
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}

}
