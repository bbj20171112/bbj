
package com.bbj.base.dictionary.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.constant.Constants;
import com.bbj.base.dictionary.domain.DictionaryField;
import com.bbj.base.dictionary.service.DictionaryFieldService;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.BBJSqlFilter;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;

@Controller
@RequestMapping(value={Constants.module_current+"/dictionary/field"})
public class DictionaryFieldController {

	@Autowired
	private DictionaryFieldService dictionaryFieldService;
	
	/**
	 * 查（不分页）
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public Object query(
			@RequestParam("table_name") String tableName,
			@RequestParam("select_fields") String selectFieldString,
			HttpServletRequest request){
		
		// 分页查询
		SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		WhereFilter whereFilter = new WhereFilter(DictionaryField.tableName, "=",  tableName);
		list.add(whereFilter );
		sqlFilter.addWhereFilter(list );
		int tagPage = 1;
		List<String> selectFields = null;
		if(selectFieldString != null){
			selectFields = Arrays.asList(selectFieldString.split(","));
		}
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(BBJServiceParam.keySelectFields, selectFields )
				.addAttr(BBJServiceParam.keyTagPage, tagPage)
				.addAttr(BBJServiceParam.keyPageSize, 1000)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		return dictionaryFieldService.queryByPage(serviceParam);
	}
	
}
