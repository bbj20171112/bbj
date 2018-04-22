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

import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.admin.dictionary.service.DictionaryFieldService;
import com.bbj.base.constant.Constants;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={Constants.module_admin+"/designer/grid"})
public class GridController {

	@Autowired
	private DictionaryFieldService dictionaryFieldService;
	
	@RequestMapping(value={""})
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
		if(field.getAttr(DictionaryField.table_id) != null && !"".equals(field.getAttr(DictionaryField.table_id))){
			WhereFilter whereFilter = new WhereFilter(DictionaryField.table_id, "=",  field.getAttr(DictionaryField.table_id));
			list.add(whereFilter );
		}
		sqlFilter.addWhereFilter(list );
		
		List<DictionaryField> fields = dictionaryFieldService.queryByPage(1, 100, sqlFilter);
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
				e.put(field.getAttr(DictionaryField.field_name), tagPage + "" + i + "" + j);
			}
			if(!("" + e.get(field.getAttr(DictionaryField.field_name))).contains(searchValue)){
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
