package com.bbj.admin.menu.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbj.admin.menu.dao.MenuDao;
import com.bbj.admin.menu.domain.Menu;
import com.bbj.admin.menu.domain.MenuTree;
import com.bbj.base.domain.SqlFilter;

@Service
public class MenuService {

	
	@Autowired
	private MenuDao menuDao;
	
	
	/**
	 * 增
	 * @param menu
	 * @return
	 */
	public int insert(Menu menu){
		if(menu == null){
			return 0;
		}
		return menuDao.insert(menu);
	}
	
	
	/**
	 * 删
	 * @param id
	 * @return
	 */
	public int deleteById(String id){
		if(id == null){
			return 0;
		}
		return menuDao.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param menu
	 * @return
	 */
	public int update(Menu menu){
		return menuDao.update(menu);
	}
	
	
	/**
	 * 查
	 * @param id
	 * @return
	 */
	public Menu queryById(String id){
		return menuDao.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param tagPage
	 * @param pageSize
	 * @param sqlFilter
	 * @return
	 */
	public List<Menu> queryByPage(int tagPage, int pageSize,SqlFilter sqlFilter){
		return menuDao.queryByPage(tagPage, pageSize, sqlFilter);
	}
	
	
	/**
	 * 获取总数
	 * @param sqlFilter
	 * @return
	 */
	public int getTotalRow(SqlFilter sqlFilter){
		return menuDao.getTotalRow(sqlFilter);
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	 /**
     * 查询所有菜单
     * @return
     */
    public List<MenuTree> queryAll() {
        List<MenuTree> list = new ArrayList<MenuTree>(); 
        list = generateMenuData();
        List<Menu> listDb = menuDao.queryByPage(1, 1000);
        for (int i = 0; i < listDb.size(); i++) {
        	Menu menu = listDb.get(i);
        	MenuTree menuObj = new MenuTree(menu);
            list.add(menuObj);
		}
        return buildMenuTree(list);
    }

    private List<MenuTree> buildMenuTree(List<MenuTree> list) {
        List<MenuTree> roots = new ArrayList<MenuTree>();

        MenuTree childMenu = null;
        MenuTree parentMenu = null;
        for (int i = 0; i < list.size(); i++) {

            childMenu = list.get(i);
            if ("-1".equals(childMenu.getCurrent().getAttr(Menu.upMenuId))) {
                roots.add(childMenu);
            }
            for (int j = 0; j < list.size(); j++) {
                parentMenu = list.get(j);
                if (childMenu.getCurrent().getAttr(Menu.upMenuId).equals(parentMenu.getCurrent().getAttr(Menu.id))) {
                    parentMenu.addChildren(childMenu);
                }
            }
        }
        return roots;

    }

    /**
     * 模拟生成数据
     * @return
     */
    private List<MenuTree> generateMenuData() {
        List<MenuTree> list = new ArrayList<MenuTree>();

        Menu menu = new Menu();
        menu.setAttr(Menu.id, "0");
        menu.setAttr(Menu.upMenuId, "-1");
        menu.setAttr(Menu.menuName, "");
        menu.setAttr(Menu.menuLink, "/template/blank");
        MenuTree menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "1");
        menu.setAttr(Menu.upMenuId, "0");
        menu.setAttr(Menu.menuName, "陆瑞华");
        menu.setAttr(Menu.menuLink, "");// /base/widgets/progress
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "2");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "Alert");
        menu.setAttr(Menu.menuLink, "/base/widgets/alert");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "3");
        menu.setAttr(Menu.upMenuId, "0");
        menu.setAttr(Menu.menuName, "汪建明");
        menu.setAttr(Menu.menuLink, "");///base/widgets/button
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "9");
        menu.setAttr(Menu.upMenuId, "3");
        menu.setAttr(Menu.menuName, "register");
        menu.setAttr(Menu.menuLink, "/base/widgets/register");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "10");
        menu.setAttr(Menu.upMenuId, "3");
        menu.setAttr(Menu.menuName, "login");
        menu.setAttr(Menu.menuLink, "/base/widgets/login");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

//        menu = new Menu();
//        menu.setAttr(Menu.id, "4");
//        menu.setAttr(Menu.upMenuId, "1");
//        menu.setAttr(Menu.menuName, "Layer");
//        menu.setAttr(Menu.menuLink, "/base/widgets/layer");
//        menuObj = new MenuObject(menu);
//        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "5");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "Grid");
        menu.setAttr(Menu.menuLink, "/base/widgets/grid");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "6");
        menu.setAttr(Menu.upMenuId, "0");
        menu.setAttr(Menu.menuName, "刘略");
        menu.setAttr(Menu.menuLink, "");///template/login
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "7");
        menu.setAttr(Menu.upMenuId, "6");
        menu.setAttr(Menu.menuName, "信息框");
        menu.setAttr(Menu.menuLink, "/base/widgets/boxdemo");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "8");
        menu.setAttr(Menu.upMenuId, "6");
        menu.setAttr(Menu.menuName, "卡片列表");
        menu.setAttr(Menu.menuLink, "/base/widgets/cardlistdemo");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "11");
        menu.setAttr(Menu.upMenuId, "6");
        menu.setAttr(Menu.menuName, "echart图表");
        menu.setAttr(Menu.menuLink, "/base/widgets/echartdemo");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "12");
        menu.setAttr(Menu.upMenuId, "6");
        menu.setAttr(Menu.menuName, "富文本编辑器");
        menu.setAttr(Menu.menuLink, "/base/widgets/richtexteditordemo");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "13");
        menu.setAttr(Menu.upMenuId, "6");
        menu.setAttr(Menu.menuName, "Swiper轮播");
        menu.setAttr(Menu.menuLink, "/base/widgets/swiperdemo");
        menuObj = new MenuTree(menu);
        list.add(menuObj);

        menu = new Menu();
        menu.setAttr(Menu.id, "14");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "表维护");
        menu.setAttr(Menu.menuLink, "");///admin/dictionary/table/page
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
        menu = new Menu();
        menu.setAttr(Menu.id, "15");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "Form设计");
        menu.setAttr(Menu.menuLink, "/admin/designer/form");
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
        menu = new Menu();
        menu.setAttr(Menu.id, "16");
        menu.setAttr(Menu.upMenuId, "14");
        menu.setAttr(Menu.menuName, "字段维护");
        menu.setAttr(Menu.menuLink, "/admin/dictionary/field/page");
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
        menu = new Menu();
        menu.setAttr(Menu.id, "17");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "参照值");
        menu.setAttr(Menu.menuLink, "/admin/dictionary/reference/page");
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
//        menu = new Menu();
//        menu.setAttr(Menu.id, "18");
//        menu.setAttr(Menu.upMenuId, "1");
//        menu.setAttr(Menu.menuName, "页面设计");
//        menu.setAttr(Menu.menuLink, "admin/dictionary/new");
//        menuObj = new MenuObject(menu);
//        list.add(menuObj);
        
        menu = new Menu();
        menu.setAttr(Menu.id, "19");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "Grid设计");
        menu.setAttr(Menu.menuLink, "/admin/designer/grid");
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
        menu = new Menu();
        menu.setAttr(Menu.id, "20");
        menu.setAttr(Menu.upMenuId, "1");
        menu.setAttr(Menu.menuName, "Layout设计");
        menu.setAttr(Menu.menuLink, "/admin/designer/layout");
        menuObj = new MenuTree(menu);
        list.add(menuObj);
        
        return list;

    }
}