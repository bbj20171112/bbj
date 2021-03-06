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
import com.bbj.admin.dictionary.domain.DictionaryTable;
import com.bbj.admin.dictionary.service.DictionaryTableService;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.utils.BBJEntityUtils;


@Controller
@RequestMapping(value={Constants.module_current+"/dictionary/table"})
public class DictionaryTableController {

	@Autowired
	private DictionaryTableService dictionaryTableService;
	
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_current + "/dictionary/dictionaryTable";
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request){
		DictionaryTable bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, bbjEntity);
		return dictionaryTableService.insert(serviceParam );
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteById(@PathVariable("id")String id,HttpServletRequest request){
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyId, id);
		return dictionaryTableService.delete(serviceParam);
	}

	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request){
		DictionaryTable bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		bbjEntity.addAttr("table_name_new", request.getParameter("table_name_new")); // 新的表名
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyEntity, bbjEntity);
		return dictionaryTableService.update(serviceParam);
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object queryById(@PathVariable("id")String id,HttpServletRequest request){
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyId, id);
		return dictionaryTableService.query(serviceParam);
	}

	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object queryByPage(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue=Constants.pageSizeString)int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue,
			HttpServletRequest request
			){
		//BBJEntity curruntBBJEntity = parseBBJEntity(request);
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryTable.class);
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		WhereFilter whereFilter = new WhereFilter("table_name", "like ", "%" + searchValue + "%");
		list.add(whereFilter );
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
		
		map.put("data", dictionaryTableService.queryByPage(serviceParam));
		map.put("recordsTotal", dictionaryTableService.getTotalRow(serviceParam));
		map.put("recordsFiltered", dictionaryTableService.getTotalRow(serviceParam));
		map.put("draw", draw);
		
		return map;
	}
}
