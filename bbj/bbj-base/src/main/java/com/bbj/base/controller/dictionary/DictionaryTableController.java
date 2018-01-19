package com.bbj.base.controller.dictionary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.dictionary.DictionaryTable;
import com.bbj.base.service.dictionary.DictionaryTableService;

@Controller
@RequestMapping(value={"/base/dictionary/table"})
public class DictionaryTableController {

	@Autowired
	private DictionaryTableService dictionaryTableService;
	
	@RequestMapping(value={"/insert"})
	@ResponseBody
	public Object insert(){
		DictionaryTable bbjEntity = new DictionaryTable();
		return dictionaryTableService.insert(bbjEntity );
	}
	

	@RequestMapping(value={"/deleteById"})
	@ResponseBody
	public Object deleteById(String id){
		return dictionaryTableService.deleteById(id);
	}

	@RequestMapping(value={"/update"})
	@ResponseBody
	public Object update(String id){
		DictionaryTable bbjEntity = new DictionaryTable();
		return dictionaryTableService.update(bbjEntity );
	}
	

	@RequestMapping(value={"/queryByPage"})
	@ResponseBody
	public Object queryByPage(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw

			){
		SqlFilter<DictionaryTable> sqlFilter = null;
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		map.put("data", dictionaryTableService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", dictionaryTableService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", dictionaryTableService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		return map;
	}

	@RequestMapping(value={""})
	public Object index(){
		return "../framework/dictionary/dictionaryTable";
	}
	
}
