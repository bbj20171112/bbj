package com.bbj.organization.domain;
import com.bbj.base.domain.BBJEntity;

public class Department extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "org_department";
	
	public static final String code = "code"; // 编号 
	public static final String name = "name"; // 名称 
	public static final String comCode = "com_code"; // 公司编号 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			code, 
			name, 
			comCode, 
		};
		return attrs;
	}


}