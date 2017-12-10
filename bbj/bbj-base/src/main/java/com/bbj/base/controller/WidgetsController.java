package com.bbj.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 组件控制器
 * @author bage
 * 
 *
 */
@Controller
@RequestMapping({"/base/widgets"})
public class WidgetsController {

	/**
	 * 跳转到grid页面
	 * @param request
	 * @return 跳转的页面
	 */
	@RequestMapping({"/grid"})
	public String grid(HttpServletRequest request){
		return "widgets/grid";
	}
	
	/**
	 * 跳转到grid页面
	 * @param request
	 * @return 跳转的页面
	 */
	@RequestMapping({"/grid-extends"})
	public String gridExtends(HttpServletRequest request){
		return "widgets/grid-extends";
	}
	
	/**
	 * 跳转到grid页面
	 * @param request
	 * @return 跳转的页面
	 */
	@RequestMapping({"/grid/query"})
	@ResponseBody
	public Object grid(@RequestParam(name="tagPage",required=false,defaultValue="1") int tagPage,@RequestParam(name="pageSize",required=false,defaultValue="10") int pageSize){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int startId = (tagPage - 1 ) * pageSize;
		int endId = tagPage * pageSize;
		for (int i = startId; i < endId; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", 1 + i + "");
			map.put("desc", "这是数据" + (1 + i));
			list.add(map);
		}
		return list;
	}
	
	
	
	
	
}
