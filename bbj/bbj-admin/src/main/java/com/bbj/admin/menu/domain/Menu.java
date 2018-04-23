package com.bbj.admin.menu.domain;

import com.bbj.base.domain.BBJEntity;

public class Menu extends BBJEntity {

	private static final long serialVersionUID = 1L;

	public static final String id = "id"; 
	public static final String menu_name = "menu_name"; 
	public static final String up_menu_id = "up_menu_id";
	public static final String program_id = "program_id";
	public static final String menu_icon = "menu_icon";
	public static final String menu_link = "menu_link"; 
	public static final String menu_remark = "menu_remark"; 
	
	@Override
	public String initTable() {
		return "admin_menu";
	}

	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
				id, 
				menu_name,
				up_menu_id,
				program_id,
				menu_icon,
				menu_link,
				menu_remark
		};
		return attrs;
	}

}
