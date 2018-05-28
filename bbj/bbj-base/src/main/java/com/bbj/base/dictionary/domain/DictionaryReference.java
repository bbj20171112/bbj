package com.bbj.base.dictionary.domain;

import com.bbj.base.domain.BBJEntity;

/**
 * 参照值数据字典实体
 * @author bage
 *
 */
public class DictionaryReference extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String id = "id"; 
	public static final String table_id = "table_id"; 
	public static final String reference_value = "reference_value";
	public static final String reference_name = "reference_name"; 
	public static final String reference_remark = "reference_remark"; 
	
	@Override
	public String initTable() {
		return "admin_dictionary_reference";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				id, 
				table_id,
				reference_value, 
				reference_name,
				reference_remark,
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}

}
