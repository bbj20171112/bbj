package com.bbj.admin.designer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.constant.Constants;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.dictionary.service.DictionaryFieldService;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={Constants.module_admin+"/designer/grid"})
public class GridController {

	@Autowired
	private DictionaryFieldService dictionaryFieldService;
	
	@RequestMapping
	public String newDesign(HttpServletRequest request){
		return Constants.module_admin+"/designer/grid";	
	}
	@RequestMapping(value={"/simulatedata"})
	@ResponseBody
	public Object simulate(
			@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue,
			HttpServletRequest request){
		// 分页查询
		DictionaryField field = BBJEntityUtils.parseFrom(request, DictionaryField.class);
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		if(field.getAttr(DictionaryField.tableName) != null && !"".equals(field.getAttr(DictionaryField.tableName))){
			WhereFilter whereFilter = new WhereFilter(DictionaryField.tableName, "=",  field.getAttr(DictionaryField.tableName));
			list.add(whereFilter );
		}
		sqlFilter.addWhereFilter(list );
		
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keyTagPage, 1)
				.addAttr(BBJServiceParam.keyPageSize, 100)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		List<DictionaryField> fields = dictionaryFieldService.queryByPage(serviceParam);
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		int recordsTotal = 72;
		for (int i = 0; i < length; i++) {
			Map<String, Object> e = new HashMap<String, Object>();
			for (int j = 0; j < fields.size(); j++) {
				field = fields.get(j);
				e.put(field.getAttr(DictionaryField.fieldName), tagPage + "" + i + "" + j);
			}
			if(!("" + e.get(field.getAttr(DictionaryField.fieldName))).contains(searchValue)){
				continue;
			}
			Map<String, Object> ee = new HashMap<String, Object>();
			ee.put("attr", e);
			datas.add(ee);
			if(tagPage > recordsTotal / length){
				if(recordsTotal % length == i + 1){
					break;
				}
			}
		}		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", datas);
		map.put("recordsTotal", recordsTotal);
		map.put("recordsFiltered", recordsTotal);
		map.put("draw", draw);
		return map;
	}
	
}
