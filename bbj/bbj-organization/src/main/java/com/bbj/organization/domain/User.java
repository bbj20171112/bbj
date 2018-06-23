package com.bbj.organization.domain;
import com.bbj.base.domain.BBJEntity;

public class User extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "org_user";
	
	public static final String phone = "phone"; // 用户手机号 
	public static final String name = "name"; // 名称 
	public static final String password = "password"; // 用户密码 
	public static final String image = "image"; // 用户头像 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			phone, 
			name, 
			password, 
			image, 
		};
		return attrs;
	}


}