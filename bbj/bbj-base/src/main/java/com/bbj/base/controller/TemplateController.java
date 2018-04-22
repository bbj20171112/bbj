package com.bbj.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbj.base.constant.Constants;

@Controller
@RequestMapping(value={Constants.module_base + "/template"})
public class TemplateController {

	@RequestMapping(value={"/404"})
	public String notFound(){
		System.out.println("404");
		return Constants.module_base + "/template/404";
	}
	@RequestMapping(value={"/500"})
	public String error(){
		System.out.println("500");
		return Constants.module_base + "/template/500";
	}
	@RequestMapping(value={"/css"})
	public String css(){
		System.out.println("css");
		return Constants.module_base + "/template/css";
	}
	@RequestMapping(value={"/javascript"})
	public String javascript(){
		System.out.println("javascript");
		return Constants.module_base + "/template/javascript";
	}
	@RequestMapping(value={"/invoice"})
	public String invoice(){
		System.out.println("invoice");
		return Constants.module_base + "/template/invoice";
	}
	@RequestMapping(value={"/profile"})
	public String profile(){
		System.out.println("profile");
		return Constants.module_base + "/template/profile";
	}
	@RequestMapping(value={"/lockscreen"})
	public String lockscreen(){
		System.out.println("lockscreen");
		return Constants.module_base + "/template/lockscreen";
	}
	@RequestMapping(value={"/login"})
	public String login(){
		System.out.println("login");
		return Constants.module_base + "/template/login";
	}
}
