package com.bbj.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 组件控制器
 * @author bage
 * 
 *
 */
@Controller
@RequestMapping({"/base/widgets" })
public class WidgetsController
{

    /**
     * 跳转到grid页面 
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/grid" })
    public String grid(HttpServletRequest request) {
        return "widgets/grid";
    }

    @RequestMapping({"/button" })
    public String getButton(HttpServletRequest request) {
        request.setAttribute("test", "abcd");
        return "widgets/button";
    }

    @RequestMapping({"/login" })
    public String login(HttpServletRequest request) {
        request.setAttribute("test", "abcd");
        //System.out.println(request.getParameter("userName").toString()+request.getParameter("password").toString());
        return "widgets/login";
    }

    @RequestMapping({"/register" })
    public String register(HttpServletRequest request) {
        request.setAttribute("test", "abcd");
        return "widgets/register-new";
    }

    /**
     * 跳转到grid页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/grid-extends" })
    public String gridExtends(HttpServletRequest request) {
        return "widgets/grid-extends";
    }

    /**
     * 跳转到grid页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/grid/query" })
    @ResponseBody
    public Object grid(@RequestParam(name = "tagPage", required = false, defaultValue = "1") int tagPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        int startId = (tagPage - 1) * pageSize;
        int endId = tagPage * pageSize;
        for (int i = startId; i < endId; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", 1 + i + "");
            map.put("desc", "这是数据" + (1 + i));
            list.add(map);
        }
        return list;
    }

    /**
     * 跳转到alert页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/alert" })
    public String alert(HttpServletRequest request) {
        return "widgets/alert";
    }

    /**
     * 跳转到progress页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/progress" })
    public String progress(HttpServletRequest request) {
        return "widgets/progress";
    }

    /**
     * 跳转到layer页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/layer" })
    public String layer(HttpServletRequest request) {
        return "widgets/layer";
    }

    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/boxdemo" })
    public String boxdemo(HttpServletRequest request) {
        return "widgets/boxdemo";
    }

    /**
     * 跳转到cardlistdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/cardlistdemo" })
    public String cardlistdemo(HttpServletRequest request) {
        return "widgets/cardlistdemo";
    }

    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/echartdemo" })
    public String echartdemo(HttpServletRequest request) {
        return "widgets/echartdemo";
    }

    /**
     * 跳转到richtexteditordemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/richtexteditordemo" })
    public String richtexteditordemo(HttpServletRequest request) {
        return "widgets/richtexteditordemo";
    }

    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/swiperdemo" })
    public String swiperdemo(HttpServletRequest request) {
        return "widgets/swiperdemo";
    }

}
