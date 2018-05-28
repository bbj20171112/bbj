
package com.bbj.base.dictionary.controller;

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

import com.bbj.base.constant.Constants;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.dictionary.service.DictionaryReferenceService;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;

@Controller
@RequestMapping(value={Constants.module_current+"/dictionary/reference"})
public class DictionaryReferenceController {

	@Autowired
	private DictionaryReferenceService dictionaryReferenceService;
	
	
	/**
	 * 查（单个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id")String id,HttpServletRequest request){
		return dictionaryReferenceService.queryById(id);
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
			@RequestParam(value="length",defaultValue=Constants.pageSizeString)int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue,
			HttpServletRequest request
			){
		
		// 分页查询
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		map.put("data", dictionaryReferenceService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", dictionaryReferenceService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", dictionaryReferenceService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_current + "/dictionary/dictionaryReference";
	}
}
