package com.bbj.admin.menu.domain;
import com.bbj.base.domain.BBJEntity;

public class Menu extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "admin_menu";
	
	public static final String id = "id"; // ID 
	public static final String menuName = "menu_name"; // menu_name 
	public static final String upMenuId = "up_menu_id"; // up_menu_id 
	public static final String programId = "program_id"; // program_id 
	public static final String menuIcon = "menu_icon"; // menu_icon 
	public static final String menuLink = "menu_link"; // menu_link 
	public static final String menuRemark = "menu_remark"; // menu_remark 

	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			id, 
			menuName, 
			upMenuId, 
			programId, 
			menuIcon, 
			menuLink, 
			menuRemark, 
		};
		return attrs;
	}


}