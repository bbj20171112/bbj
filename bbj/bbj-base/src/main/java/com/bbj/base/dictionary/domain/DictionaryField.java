package com.bbj.base.dictionary.domain;

import com.bbj.base.domain.BBJEntity;

/**
 * 字段数据字典实体
 * @author bage
 *
 */
public class DictionaryField extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String id = "id"; 
	public static final String tableId = "table_id"; // 表ID
	public static final String fieldName = "field_name"; // 名称
	public static final String fieldNameComment = "field_name_comment"; // 说明 
	public static final String fieldType = "field_type";  // 类型
	public static final String fieldTypeComment = "field_type_comment"; // 类型说明
	public static final String fieldShowType = "field_show_type"; // 显示类型
	public static final String fieldShowTypeComment = "field_show_type_comment"; // 显示说明
	public static final String fieldOrderNumber = "field_order_number"; // 排序序号
	public static final String fieldConstraint = "field_constraint";  
	public static final String fieldConstraintComment = "field_constraint_comment"; 
	public static final String fieldReferenceTableId = "field_reference_table_id"; 
	public static final String fieldReferenceTableFieldName = "field_reference_table_field_name"; 
	public static final String fieldReferenceComment="field_reference_comment"; 
	
	@Override
	public String initTable() {
		return "admin_dictionary_field";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				id, 
				tableId, 
				fieldName,
				fieldNameComment,
				fieldType,
				fieldTypeComment,
				fieldShowType,
				fieldShowTypeComment,
				fieldOrderNumber,
				fieldConstraint,
				fieldConstraintComment,
				fieldReferenceTableId,
				fieldReferenceTableFieldName,
				fieldReferenceComment,
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}

}
