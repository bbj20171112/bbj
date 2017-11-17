package com.bbj.web.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/template"})
public class TemplateController {

	@RequestMapping(value={"/404"})
	public String notFound(){
		System.out.println("404");
		return "template/404";
	}
	@RequestMapping(value={"/500"})
	public String error(){
		System.out.println("500");
		return "template/500";
	}
	@RequestMapping(value={"/css"})
	public String css(){
		System.out.println("css");
		return "template/css";
	}
	@RequestMapping(value={"/javascript"})
	public String javascript(){
		System.out.println("javascript");
		return "template/javascript";
	}
}
