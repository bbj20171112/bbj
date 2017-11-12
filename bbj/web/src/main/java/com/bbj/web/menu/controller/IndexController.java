package com.bbj.web.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.utils.JsonUtils;

@Controller
@RequestMapping(value={"/index"})
public class IndexController {

	@RequestMapping(value={"/","/index"})
	public String index(){
		System.out.println("IndexController.index() is work");
		return "index";
	}
	
	@RequestMapping(value={"/json"},produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String json(){
		return JsonUtils.toJson(new Temp("12","张三"));
	}
	
}
class Temp{
	String id;
	String name;
	public Temp(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
