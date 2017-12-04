package com.bbj.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.utils.JsonUtils;

@Controller
@RequestMapping(value={"/menu"})
public class MenuController {

	@Autowired
	private com.bbj.base.service.MenuService MenuService;
	
	@RequestMapping(value={"/","/index"})
	public String index(){
		return "menu/index";
	}
	
	@RequestMapping(value={"/starter"})
	public String starter(){
		return "/AdminLTE/starter";
	}
	
	@RequestMapping(value={"/json"})
	@ResponseBody
	public String json(){
		System.out.println("IndexController.json() is work " );
		return "你好";
	}
	

	@RequestMapping(value={"/retrieve"})
	@ResponseBody
	public String retrieve(){
		return JsonUtils.toJson(MenuService.retrieve());
	}
	

	@RequestMapping(value={"/header"})
	public String header(){
		return "/menu/header";
	}
	
}
