package com.bbj.base.controller.designer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/base/designer/layout"})
public class LayoutController {

	@RequestMapping(value={""})
	public String newDesign(HttpServletRequest request){
		return "designer/layout";	
	}
	
}
