package com.bbj.admin.designer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbj.admin.Constants;

@Controller
@RequestMapping(value={Constants.module_current+"/designer/layout"})
public class LayoutController {

	@RequestMapping
	public String newDesign(HttpServletRequest request){
		return Constants.module_current +"/designer/layout";	
	}
	
}
