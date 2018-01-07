package com.bbj.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  组件控制器2
 * @作者 liulve
 * @version [版本号, 2018年1月7日]
 */
@Controller
@RequestMapping({"/base/codeshow" })
public class CodeshowController
{
    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/boxdemo" })
    public String boxdemo(HttpServletRequest request) {
        return "codeshow/index?url=boxdemo";
    }

    /**
     * 跳转到cardlistdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/cardlistdemo" })
    public String cardlistdemo(HttpServletRequest request) {
        return "codeshow/index?url=cardlistdemo";
    }

    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/echartdemo" })
    public String echartdemo(HttpServletRequest request) {
        return "codeshow/index?url=echartdemo";
    }

    /**
     * 跳转到richtexteditordemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/richtexteditordemo" })
    public String richtexteditordemo(HttpServletRequest request) {
        return "codeshow/index?url=richtexteditordemo";
    }

    /**
     * 跳转到boxdemo页面
     * @param request
     * @return 跳转的页面
     */
    @RequestMapping({"/swiperdemo" })
    public String swiperdemo(HttpServletRequest request) {
        return "codeshow/index?url=swiperdemo";
    }

}
