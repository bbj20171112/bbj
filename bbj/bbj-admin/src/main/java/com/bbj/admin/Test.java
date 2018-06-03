package com.bbj.admin;
import com.bbj.base.domain.BBJEntity;

public class Test extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "admin_test";
	
	public static final String id = "id"; // ID 
	public static final String icon = "icon"; // icon 
	public static final String sex = "sex"; // 性别 
	public static final String birthday = "birthday"; // birthday
	public static final String name = "name"; // 名称 
	public static final String testType = "test_type"; // 测试类型 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			id, 
			icon, 
			sex, 
			birthday, 
			name, 
			testType, 
		};
		return attrs;
	}


}