package com.bbj.admin.menu.domain;
import com.bbj.base.domain.BBJEntity;

/**
 * 菜单对象
 * @author bage
 *
 */
public class Menu extends BBJEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final String tableName = "admin_menu";
	
	public static final String id = "id"; // ID 
	public static final String menuNumber = "menu_number"; // 菜单编号 
	public static final String menuName = "menu_name"; // 菜单名称 
	public static final String upMenuId = "up_menu_id"; // 上级菜单ID 
	public static final String programId = "program_id"; // 程序ID 
	public static final String menuIcon = "menu_icon"; // 菜单图标 
	public static final String menuLink = "menu_link"; // 菜单链接 
	public static final String menuOrderNumber = "menu_order_number"; // 菜单序号
	public static final String menuRemark = "menu_remark"; // 菜单备注 
	
	
	@Override
	public String initTable() {
		return tableName;
	}
	
	
	@Override
	public String[] initAttr() {
		String attrs[] = new String[]{
			id, 
			menuNumber,
			menuName, 
			upMenuId, 
			programId, 
			menuIcon, 
			menuLink, 
			menuOrderNumber,
			menuRemark, 
		};
		return attrs;
	}


}