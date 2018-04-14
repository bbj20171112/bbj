package com.bbj.base.domain.organization;

import com.bbj.base.domain.BBJEntity;

public class User extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String id = "id"; 
	public static final String name = "name";
	public static final String sex = "sex"; 
	public static final String love = "love"; 
	public static final String icon = "icon"; 
	public static final String birthday = "birthday";
	public static final String introduction = "introduction"; 
	
	@Override
	public String initTable() {
		return "dictionary_field";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				id, 
				name, 
				sex,
				love,
				icon,
				birthday,
				introduction,
				BBJEntity.create_time,
				BBJEntity.update_time,
				BBJEntity.create_staff_id,
				BBJEntity.update_staff_id,
				BBJEntity.delete_state
		};
		return attrs;
	}
}
