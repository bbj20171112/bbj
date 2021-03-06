package com.bbj.admin.menu.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.admin.menu.domain.Menu;
import com.bbj.admin.menu.service.MenuService;
import com.bbj.base.constant.Constants;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={Constants.module_admin + "/menu/menu"})
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	
	/**
	 * 增
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request){
		Menu menu = BBJEntityUtils.parseFrom(request, Menu.class);
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, menu);
		return menuService.insert(serviceParam );
	}
	
	
	/**
	* 删
	* @param id
	* @return
	*/
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteById(@PathVariable("id")String id,HttpServletRequest request){
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyId, id);
		return menuService.delete(serviceParam);
	}
	
	
	/**
	 * 改
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request){
		Menu menu = BBJEntityUtils.parseFrom(request, Menu.class);
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, menu);
		return menuService.update(serviceParam);
	}
	
	
	/**
	 * 查（单个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id")String id,HttpServletRequest request){
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyId, id);
		return menuService.query(serviceParam);
	}
	
	
	/**
	 * 查（分页）
	 * @param start
	 * @param length
	 * @param draw
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object queryByPage(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue,
			HttpServletRequest request
			){
		
		// 分页查询
		SqlFilter sqlFilter = null;	
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyTagPage, tagPage)
				.addAttr(BBJServiceParam.keyPageSize, length)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		map.put("data", menuService.queryByPage(serviceParam));
		map.put("recordsTotal", menuService.getTotalRow(serviceParam));
		map.put("recordsFiltered", menuService.getTotalRow(serviceParam));
		map.put("draw", draw);
		
		return map;
	}
	
	@RequestMapping(value={"/all"})
	@ResponseBody
	public Object all(){
		return menuService.queryAll();
	}
	
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_admin + "/menu/menu";
	}


}