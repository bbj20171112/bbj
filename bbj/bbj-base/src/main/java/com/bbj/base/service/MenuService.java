package com.bbj.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bbj.base.domain.MenuObject;
import com.bbj.base.utils.JsonUtils;
import com.bbj.base.utils.PrintUtils;

@Service(value = "menuService")
public class MenuService
{

    public static void main(String[] args) {
        List<MenuObject> list = new MenuService().retrieve();
        PrintUtils.println(list);
        System.out.println(JsonUtils.toJson(list));
    }

    /**
     * 查询
     * @return
     */
    public List<MenuObject> retrieve() {
        List<MenuObject> list = generateMenuData();
        return buildMenuTree(list);
    }

    private List<MenuObject> buildMenuTree(List<MenuObject> list) {
        List<MenuObject> roots = new ArrayList<MenuObject>();

        MenuObject childMenu = null;
        MenuObject parentMenu = null;
        boolean[] isFoundParent = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {

            if (isFoundParent[i]) {
                continue;
            }
            childMenu = list.get(i);
            if ("-1".equals(childMenu.getAttributes().get("upId"))) {
                roots.add(childMenu);
            }
            for (int j = 0; j < list.size(); j++) {
                parentMenu = list.get(j);
                if (childMenu.getAttributes().get("upId").equals(parentMenu.getAttributes().get("id"))) {
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
    private List<MenuObject> generateMenuData() {
        List<MenuObject> list = new ArrayList<MenuObject>();

        Map<String, String> attr = new HashMap<String, String>();
        attr.put("id", "0");
        attr.put("upId", "-1");
        attr.put("text", "");
        attr.put("link", "template/blank");
        MenuObject menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "1");
        attr.put("upId", "0");
        attr.put("text", "陆瑞华");
        attr.put("link", "base/widgets/progress");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "2");
        attr.put("upId", "1");
        attr.put("text", "Alert");
        attr.put("link", "base/widgets/alert");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "3");
        attr.put("upId", "0");
        attr.put("text", "汪建明");
        attr.put("link", "base/widgets/button");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "9");
        attr.put("upId", "3");
        attr.put("text", "register");
        attr.put("link", "base/widgets/register");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "10");
        attr.put("upId", "3");
        attr.put("text", "login");
        attr.put("link", "base/widgets/login");
        menu = new MenuObject(attr);
        list.add(menu);

//        attr = new HashMap<String, String>();
//        attr.put("id", "4");
//        attr.put("upId", "1");
//        attr.put("text", "Layer");
//        attr.put("link", "base/widgets/layer");
//        menu = new MenuObject(attr);
//        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "5");
        attr.put("upId", "1");
        attr.put("text", "Grid");
        attr.put("link", "base/widgets/grid");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "6");
        attr.put("upId", "0");
        attr.put("text", "刘略");
        attr.put("link", "template/login");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "7");
        attr.put("upId", "6");
        attr.put("text", "信息框");
        attr.put("link", "base/widgets/boxdemo");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "8");
        attr.put("upId", "6");
        attr.put("text", "卡片列表");
        attr.put("link", "base/widgets/cardlistdemo");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "11");
        attr.put("upId", "6");
        attr.put("text", "echart图表");
        attr.put("link", "base/widgets/echartdemo");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "12");
        attr.put("upId", "6");
        attr.put("text", "富文本编辑器");
        attr.put("link", "base/widgets/richtexteditordemo");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "13");
        attr.put("upId", "6");
        attr.put("text", "Swiper轮播");
        attr.put("link", "base/widgets/swiperdemo");
        menu = new MenuObject(attr);
        list.add(menu);

        attr = new HashMap<String, String>();
        attr.put("id", "14");
        attr.put("upId", "1");
        attr.put("text", "表维护");
        attr.put("link", "base/dictionary/table/page");
        menu = new MenuObject(attr);
        list.add(menu);
        
        attr = new HashMap<String, String>();
        attr.put("id", "15");
        attr.put("upId", "1");
        attr.put("text", "Form设计");
        attr.put("link", "base/designer/form");
        menu = new MenuObject(attr);
        list.add(menu);
        
        attr = new HashMap<String, String>();
        attr.put("id", "16");
        attr.put("upId", "14");
        attr.put("text", "字段维护");
        attr.put("link", "base/dictionary/field/page");
        menu = new MenuObject(attr);
        list.add(menu);
        
        attr = new HashMap<String, String>();
        attr.put("id", "17");
        attr.put("upId", "1");
        attr.put("text", "参照值");
        attr.put("link", "base/dictionary/reference/page");
        menu = new MenuObject(attr);
        list.add(menu);
        
//        attr = new HashMap<String, String>();
//        attr.put("id", "18");
//        attr.put("upId", "1");
//        attr.put("text", "页面设计");
//        attr.put("link", "base/designer/new");
//        menu = new MenuObject(attr);
//        list.add(menu);
        
        attr = new HashMap<String, String>();
        attr.put("id", "19");
        attr.put("upId", "1");
        attr.put("text", "Grid设计");
        attr.put("link", "base/designer/grid");
        menu = new MenuObject(attr);
        list.add(menu);
        
        return list;

    }

}
