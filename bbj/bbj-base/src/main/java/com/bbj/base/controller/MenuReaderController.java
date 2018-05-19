package com.bbj.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.constant.Constants;
import com.bbj.base.service.MenuService;

@Controller
@RequestMapping(value={Constants.module_base + "/menu"})
public class MenuReaderController {

	@Autowired
	private MenuService MenuService;
	
	@RequestMapping(value={"/","/index"})
	public String index(){
		return Constants.module_base + "/menu/index";
	}
	
	@RequestMapping(value={"/starter"})
	public String starter(){
		return Constants.module_base + "//AdminLTE/starter";
	}
	
	@RequestMapping(value={"/json"})
	@ResponseBody
	public String json(){
		System.out.println("IndexController.json() is work " );
		return "你好";
	}
	

	@RequestMapping(value={"/retrieve"})
	@ResponseBody
	public Object retrieve(){
		return MenuService.retrieve();
	}
	

	@RequestMapping(value={"/header"})
	public String header(){
		return Constants.module_base + "/menu/header";
	}
	
}
