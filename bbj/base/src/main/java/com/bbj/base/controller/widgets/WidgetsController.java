package com.bbj.base.controller.widgets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组件控制器
 * @author bage
 *
 */
@Controller
@RequestMapping({"/base/widgets"})
public class WidgetsController {

	@RequestMapping({"/grid"})
	public String grid(){
		return "widgets/grid";
	}
	
}
