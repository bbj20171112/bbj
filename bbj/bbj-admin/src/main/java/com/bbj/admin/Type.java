package com.bbj.admin;

import com.bbj.base.domain.BBJEntity;

public class Type extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "admin_type";
	
	public static final String id = "id"; // ID 
	public static final String typeValue = "type_value"; // 类型value 
	public static final String typeRemark = "type_remark"; // 类型remark 
	public static final String typeKey = "type_key"; // 类型key 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			id, 
			typeValue, 
			typeRemark, 
			typeKey, 
		};
		return attrs;
	}


}
