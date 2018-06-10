
package com.bbj.base.dictionary.controller;

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
import com.bbj.base.dictionary.domain.DictionaryReference;
import com.bbj.base.dictionary.service.DictionaryReferenceService;
import com.bbj.base.domain.BBJServiceParam;
import com.bbj.base.domain.SqlFilter;

@Controller
@RequestMapping(value={Constants.module_current+"/dictionary/reference"})
public class DictionaryReferenceController {

	@Autowired
	private DictionaryReferenceService dictionaryReferenceService;
	
	/**
	 * 查（不分页）
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public Object query(
			@RequestParam("reference_table_name") String referenceTableName,
			@RequestParam("reference_field_name") String referenceFieldName,
			@RequestParam("reference_field_value") String referenceFieldValue,
			HttpServletRequest request){
		
		// 分页查询
		SqlFilter sqlFilter = null;
		int tagPage = 1;
		List<String> referenceFieldValues = null;
		if(referenceFieldValue != null){
			referenceFieldValues = Arrays.asList(referenceFieldValue.split(","));
		}
		BBJServiceParam serviceParam = new BBJServiceParam()
				.addAttr(DictionaryReference.referenceTableName, referenceTableName )
				.addAttr(DictionaryReference.referenceFieldName, referenceFieldName )
				.addAttr(DictionaryReference.referenceFieldValue, referenceFieldValues )
				.addAttr(BBJServiceParam.keyTagPage, tagPage)
				.addAttr(BBJServiceParam.keyPageSize, Constants.pageSizeAll)
				.addAttr(BBJServiceParam.keySqlFilter, sqlFilter);
		
		return dictionaryReferenceService.queryByPage(serviceParam);
	}
	
}
