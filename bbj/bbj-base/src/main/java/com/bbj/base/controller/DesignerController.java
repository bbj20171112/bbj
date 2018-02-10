package com.bbj.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/base/designer"})
public class DesignerController {

	@RequestMapping(value={"/new"})
	public String notFound(){
		return "designer/new";
	}
	
}
