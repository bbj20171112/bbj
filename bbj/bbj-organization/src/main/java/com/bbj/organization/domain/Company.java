package com.bbj.organization.domain;
import com.bbj.base.domain.BBJEntity;

public class Company extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "org_company";
	
	public static final String code = "code"; // 公司编号 
	public static final String name = "name"; // 公司名称 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			code, 
			name, 
		};
		return attrs;
	}


}