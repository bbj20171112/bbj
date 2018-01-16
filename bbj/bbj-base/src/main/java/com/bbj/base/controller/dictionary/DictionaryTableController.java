package com.bbj.base.controller.dictionary;

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
	public Object queryByPage(@RequestParam(value="tagPage",defaultValue="1")int tagPage,@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		SqlFilter<DictionaryTable> sqlFilter = null;
		return dictionaryTableService.queryByPage(tagPage, pageSize, sqlFilter);
	}
	

	@RequestMapping(value={""})
	public Object index(){
		return "../framework/dictionary/dictionaryTable";
	}
	
}
