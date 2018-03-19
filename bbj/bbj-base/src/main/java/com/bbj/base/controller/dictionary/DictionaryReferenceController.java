
package com.bbj.base.controller.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.domain.dictionary.DictionaryField;
import com.bbj.base.domain.dictionary.DictionaryReference;
import com.bbj.base.service.dictionary.DictionaryReferenceService;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={"/base/dictionary/reference"})
public class DictionaryReferenceController {

	@Autowired
	private DictionaryReferenceService dictionaryReferenceService;
	
	/**
	 * 增
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request){
		DictionaryReference bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryReference.class);
		return dictionaryReferenceService.insert(bbjEntity );
	}
	

	/**
	 * 删
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteById(@PathVariable("id")String id){
		return dictionaryReferenceService.deleteById(id);
	}

	/**
	 * 改
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request){
		DictionaryReference bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryReference.class);
		return dictionaryReferenceService.update(bbjEntity );
	}
	
	
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
		return "../framework/dictionary/dictionaryReference";
	}
}
