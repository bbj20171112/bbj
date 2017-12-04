package com.bbj.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组件控制器
 * @author bage
 * 
 *
 */
@Controller
@RequestMapping({"/base/widgets"})
public class WidgetsController {

	/**
	 * 跳转到grid页面
	 * @param request
	 * @return 跳转的页面
	 */
	@RequestMapping({"/grid"})
	public String grid(HttpServletRequest request){
		request.setAttribute("test", "abcd");
		return "widgets/grid";
	}
	
}
