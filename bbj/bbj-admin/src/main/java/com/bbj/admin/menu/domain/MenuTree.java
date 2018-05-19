package com.bbj.admin.menu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bbj.admin.menu.domain.Menu;

/**
 * 整个菜单对象
 * @author bage
 *
 */
public class MenuTree implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 菜单属性
	private Menu current = new Menu();
	// 子菜单
	private List<MenuTree> children = new ArrayList<MenuTree>();
	
	// 构造函数
	public MenuTree() {
		super();
	}
	public MenuTree(Menu menu) {
		super();
		this.current = menu;
	}
	public MenuTree(Menu menu, List<MenuTree> children) {
		super();
		this.current = menu;
		this.children = children;
	}
	public Menu getCurrent() {
		return current;
	}
	public void setCurrent(Menu current) {
		this.current = current;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "MenuObject [current=" + current + ", children=" + children + "]";
	}
	public void addChildren(MenuTree childMenu) {
		this.children.add(childMenu);
	}
	
	
}
