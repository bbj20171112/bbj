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

import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;
import com.bbj.base.domain.dictionary.DictionaryTable;
import com.bbj.base.service.dictionary.DictionaryTableService;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={"/base/dictionary/table"})
public class DictionaryTableController {

	@Autowired
	private DictionaryTableService dictionaryTableService;
	
	@RequestMapping(value={""})
	public Object index(){
		return "../framework/dictionary/dictionaryTable";
	}

	@RequestMapping(value={"/insert"})
	@ResponseBody
	public Object insert(HttpServletRequest request){
		DictionaryTable bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		return dictionaryTableService.insert(bbjEntity );
	}
	
	@RequestMapping(value={"/getCreateTablePrepareSqlMap"})
	@ResponseBody
	public Object getCreateTablePrepareSqlMap(HttpServletRequest request){
		DictionaryTable table = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		return dictionaryTableService.getCreateTablePrepareSql(table );
	}
	
	@RequestMapping(value={"/deleteById"})
	@ResponseBody
	public Object deleteById(HttpServletRequest request){
		DictionaryTable dictionaryTable = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		return dictionaryTableService.deleteById(dictionaryTable.getAttr(dictionaryTable.getId()));
	}

	@RequestMapping(value={"/update"})
	@ResponseBody
	public Object update(HttpServletRequest request){
		DictionaryTable bbjEntity = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		return dictionaryTableService.update(bbjEntity);
	}

	@RequestMapping(value={"/queryById"})
	@ResponseBody
	public Object queryById(HttpServletRequest request){
		DictionaryTable dictionaryTable = BBJEntityUtils.parseFrom(request, DictionaryTable.class);
		return dictionaryTableService.queryById(dictionaryTable.getId());
	}

	
	@RequestMapping(value={"/queryByPage"})
	@ResponseBody
	public Object queryByPage(HttpServletRequest request,@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue

			){
		//BBJEntity curruntBBJEntity = parseBBJEntity(request);
		SqlFilter<DictionaryTable> sqlFilter = null;//= new SqlFilter<DictionaryTable>(curruntBBJEntity );
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		WhereFilter whereFilter = new WhereFilter("table_name", "like ", "%" + searchValue + "%");
		list.add(whereFilter );
		//sqlFilter.addWhereFilter(list );
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		;

		map.put("data", dictionaryTableService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", dictionaryTableService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", dictionaryTableService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}
}
