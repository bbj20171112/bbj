package com.bbj.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value={"/rl"})
public class RgstAndLoginController {

	@Autowired
	private com.bbj.base.service.RLService RLService;
	
	@RequestMapping(value={"/","/register"})
	public String register(HttpServletRequest request){
		
		return "/base/widgets/register";
	}
	
	@RequestMapping(value={"/login"})
	public String starter(HttpServletRequest request){
		String userName = "";
		String password = "";
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		//模拟数据校验部分
		if ("1".equals(password) && "1".equals(userName)) {
			return "success";
		}
		
		return "false";
	}
	
	@RequestMapping(value={"/json"})
	@ResponseBody
	public String json(){
		System.out.println("IndexController.json() is work " );
		return "你好";
	}
	
	

	@RequestMapping(value={"/header"})
	public String header(){
		return "/menu/header";
	}
	
}
