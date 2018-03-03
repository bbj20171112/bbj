
package com.bbj.base.controller.dictionary;

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

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.domain.dictionary.DictionaryField;
import com.bbj.base.domain.dictionary.DictionaryTable;
import com.bbj.base.service.dictionary.DictionaryFieldService;

@Controller
@RequestMapping(value={"/base/dictionary/field"})
public class DictionaryFieldController {

	@Autowired
	private DictionaryFieldService dictionaryFieldService;
	
	@RequestMapping(value={"/insert"})
	@ResponseBody
	public Object insert(HttpServletRequest request){
		String field_name = request.getParameter("field_name");
		System.out.println("insert is work..." + field_name);
		return 0;
		//DictionaryTable bbjEntity = new DictionaryTable();
		//return dictionaryTableService.insert(bbjEntity );
	}
	

	@RequestMapping(value={"/deleteById"})
	@ResponseBody
	public Object deleteById(String id){
		return dictionaryFieldService.deleteById(id);
	}

	@RequestMapping(value={"/update"})
	@ResponseBody
	public Object update(String id){
		DictionaryField bbjEntity = new DictionaryField();
		return dictionaryFieldService.update(bbjEntity );
	}
	

	@RequestMapping(value={"/queryByPage"})
	@ResponseBody
	public Object queryByPage(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue

			){
		BBJEntity curruntBBJEntity = new DictionaryTable();
		SqlFilter<DictionaryField> sqlFilter = new SqlFilter<DictionaryField>(curruntBBJEntity );
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
		map.put("data", dictionaryFieldService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", dictionaryFieldService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", dictionaryFieldService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}

	@RequestMapping(value={""})
	public Object index(HttpServletRequest request){
		request.setAttribute("testValue", "testValuevvvv");
		request.getSession().setAttribute("ssss", "dsd");
		return "../framework/dictionary/dictionaryField";
	}
	
}
