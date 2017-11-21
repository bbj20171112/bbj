package com.bbj.web.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbj.base.utils.JsonUtils;
import com.bbj.base.utils.PrintUtils;
import com.bbj.web.menu.domain.Menu;

@Service(value="menuService")
public class MenuService {

	public static void main(String[] args) {
		List<Menu> list = new MenuService().retrieve();
		PrintUtils.println(list);
		System.out.println(JsonUtils.toJson(list));
	}
	/**
	 * 查询
	 * @return
	 */
	public List<Menu> retrieve(){
		List<Menu> list = generateMenuData();
		return buildMenuTree(list);
	}

	private List<Menu> buildMenuTree(List<Menu> list) {
		List<Menu> roots = new ArrayList<Menu>();
		
		Menu childMenu = null;
		Menu parentMenu = null;
		boolean[] isFoundParent = new boolean[list.size()];
		for (int i = 0; i < list.size(); i++) {
			
			if(isFoundParent[i]){
				continue ;
			}
			childMenu = list.get(i);
			if("-1".equals(childMenu.getAttributes().get("upId"))){
				roots.add(childMenu);
			}
			for (int j = 0; j < list.size(); j++) {
				parentMenu = list.get(j);
				if(childMenu.getAttributes().get("upId").equals(parentMenu.getAttributes().get("id"))){
					parentMenu.addChildren(childMenu);
					isFoundParent[j] = true;
				}
			}
		}
		return roots;
		
	}

	/**
	 * 模拟生成数据
	 * @return
	 */
	private List<Menu> generateMenuData() {
		List<Menu> list = new ArrayList<Menu>();
		
		Map<String, String> attr = new HashMap<String, String>();
		attr.put("id", "0");
		attr.put("upId", "-1");
		attr.put("text", "");
		attr.put("link", "template/blank");
		Menu menu = new Menu(attr );
		list.add(menu );
		
		attr = new HashMap<String, String>();
		attr.put("id", "1");
		attr.put("upId", "0");
		attr.put("text", "menu1");
		attr.put("link", "template/500");
		menu = new Menu(attr );
		list.add(menu );
		
		attr = new HashMap<String, String>();
		attr.put("id", "2");
		attr.put("upId", "0");
		attr.put("text", "menu2");
		attr.put("link", "template/invoice");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "3");
		attr.put("upId", "0");
		attr.put("text", "menu3");
		attr.put("link", "template/profile");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "4");
		attr.put("upId", "1");
		attr.put("text", "menu4");
		attr.put("link", "template/lockscreen");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "5");
		attr.put("upId", "1");
		attr.put("text", "menu5");
		attr.put("link", "template/404");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "6");
		attr.put("upId", "4");
		attr.put("text", "menu6");
		attr.put("link", "template/login");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "7");
		attr.put("upId", "6");
		attr.put("text", "menu7");
		menu = new Menu(attr );
		list.add(menu );

		attr = new HashMap<String, String>();
		attr.put("id", "8");
		attr.put("upId", "4");
		attr.put("text", "menu8");
		menu = new Menu(attr );
		list.add(menu );
		
		return list;
		
	}
	
}
