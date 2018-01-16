package com.bbj.base.controller.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.domain.dictionary.DictionaryTable;
import com.bbj.base.service.dictionary.DictionaryTableService;

@Controller
@RequestMapping(value={"/dictionary/table"})
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
	public Object deleteById(String id){
		return dictionaryTableService.deleteById(id);
	}
	
}
