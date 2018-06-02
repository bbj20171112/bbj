package com.bbj.organization.domain;
import com.bbj.base.domain.BBJEntity;

public class Position extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "org_position";
	
	public static final String code = "code"; // 编号 
	public static final String name = "name"; // 名称 
	public static final String depCode = "dep_code"; // 部门编号 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			code, 
			name, 
			depCode, 
		};
		return attrs;
	}


}