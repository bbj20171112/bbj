package com.bbj.admin;

import com.bbj.base.domain.BBJEntity;

public class TestDomain extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "admin_test";
	
	public static final String id = "id"; // ID 
	public static final String name = "name"; // 名称 
	public static final String sex = "sex"; // 性别 
	public static final String icon = "icon"; // icon 
	public static final String ok = "ok"; // ok 
	public static final String userName = "user_name"; // user_name 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			id, 
			name, 
			sex, 
			icon, 
			ok, 
			userName, 
		};
		return attrs;
	}


}