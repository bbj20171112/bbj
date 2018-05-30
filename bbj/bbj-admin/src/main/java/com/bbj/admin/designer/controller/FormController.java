package com.bbj.admin.designer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbj.base.constant.Constants;

@Controller
@RequestMapping(value={Constants.module_admin+"/designer/form"})
public class FormController {

	@RequestMapping
	public String newDesign(HttpServletRequest request){
		return Constants.module_admin+"/designer/form";	
	}
	
}
