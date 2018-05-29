
package com.bbj.admin.dictionary.controller;

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

import com.bbj.admin.Constants;
import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.admin.dictionary.service.DictionaryFieldService;
import com.bbj.base.domain.BBJServiceParam;
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
	 * 增
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request){
		DictionaryField dictionaryField = BBJEntityUtils.parseFrom(request, DictionaryField.class);
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, dictionaryField);
		return dictionaryFieldService.insert(serviceParam );
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
		return dictionaryFieldService.delete(serviceParam);
	}

	/**
	 * 改
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request){
		String action = request.getParameter("action");
		// 更改顺序
		if("ordernumber".equalsIgnoreCase(action)){
			String id = request.getParameter(DictionaryField.id);
			String type = request.getParameter("type");
			
			BBJServiceParam serviceParam = new BBJServiceParam()
					.addAttr("id", id)
					.addAttr("type", type);
			return dictionaryFieldService.updateOrdernumber(serviceParam);
		}
		
		// 默认进行update
		DictionaryField bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryField.class);
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, bbjEntity);
		return dictionaryFieldService.update(serviceParam );
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
		return dictionaryFieldService.query(serviceParam);
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
		
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyTagPage, tagPage)
				.addAttr(BBJServiceParam.keyPageSize, length)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		map.put("data", dictionaryFieldService.queryByPage(serviceParam));
		map.put("recordsTotal", dictionaryFieldService.getTotalRow(serviceParam));
		map.put("recordsFiltered", dictionaryFieldService.getTotalRow(serviceParam));
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
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyTagPage, tagPage)
				.addAttr(BBJServiceParam.keyPageSize, 1000)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		return dictionaryFieldService.queryByPage(serviceParam);
	}
	
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_current + "/dictionary/dictionaryField";
	}
}
