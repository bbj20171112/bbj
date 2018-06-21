package com.bbj.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.constant.Constants;
import com.bbj.base.service.MenuService;

@Controller
@RequestMapping(value={Constants.module_base})
public class HomeController {

	@Autowired
	private MenuService MenuService;
	
	@RequestMapping(value={"/","/index"})
	public String index(HttpServletRequest request){
		return Constants.module_base + "/index";
	}
	
	@RequestMapping(value={"/header"})
	public String header(){
		return Constants.module_base + "/header";
	}
	
	@RequestMapping(value={"/footer"})
	public String footer(){
		return Constants.module_base + "/footer";
	}
	
	@RequestMapping(value={"/settings"})
	public String settings(){
		return Constants.module_base + "/settings";
	}
	
	@RequestMapping(value={"/menu"})
	public String menu(){
		return Constants.module_base + "/menu";
	}
	
	@RequestMapping(value={"/starter"})
	public String starter(){
		return Constants.module_base + "/AdminLTE/starter";
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
	

}
