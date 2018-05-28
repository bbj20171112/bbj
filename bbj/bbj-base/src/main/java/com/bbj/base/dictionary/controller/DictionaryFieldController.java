
package com.bbj.base.dictionary.controller;

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
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.dictionary.service.DictionaryFieldService;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={Constants.module_current+"/dictionary/field"})
public class DictionaryFieldController {

	@Autowired
	private DictionaryFieldService dictionaryFieldService;
	
	/**
	 * 查（单个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id")String id,HttpServletRequest request){
		return dictionaryFieldService.queryById(id);
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
		
		DictionaryField field = BBJEntityUtils.parseFrom(request, DictionaryField.class);
		// 分页查询
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		if(field.getAttr(DictionaryField.tableId) != null && !"".equals(field.getAttr(DictionaryField.tableId))){
			WhereFilter whereFilter = new WhereFilter(DictionaryField.tableId, "=", field.getAttr(DictionaryField.tableId));
			list.add(whereFilter );
		}
		if(field.getAttr(DictionaryField.fieldName) != null && !"".equals(field.getAttr(DictionaryField.fieldName))){
			WhereFilter whereFilter = new WhereFilter(DictionaryField.fieldName, "like", "%" + searchValue + "%");
			list.add(whereFilter );
		}
		sqlFilter.addWhereFilter(list );
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		map.put("data", dictionaryFieldService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", dictionaryFieldService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", dictionaryFieldService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}
	
	/**
	 * 查（不分页）
	 * @param start
	 * @param length
	 * @param draw
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public Object query(HttpServletRequest request){
		
		DictionaryField field = BBJEntityUtils.parseFrom(request, DictionaryField.class);
		// 分页查询
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		if(field.getAttr(DictionaryField.tableId) != null){
			WhereFilter whereFilter = new WhereFilter(DictionaryField.tableId, "=",  field.getAttr(DictionaryField.tableId));
			list.add(whereFilter );
		}
		sqlFilter.addWhereFilter(list );
		int tagPage = 1;
		return dictionaryFieldService.queryByPage(tagPage, 1000, sqlFilter);
	}
	
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_current + "/dictionary/dictionaryField";
	}
}
