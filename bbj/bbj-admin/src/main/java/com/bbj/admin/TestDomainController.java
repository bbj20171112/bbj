package com.bbj.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.constant.Constants;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.utils.BBJEntityUtils;

@Controller
@RequestMapping(value={Constants.module_admin + "/testDomain"})
public class TestDomainController {

	
	@Autowired
	private TestDomainService testDomainService;
	
	
	/**
	 * 增
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Object insert(HttpServletRequest request){
		TestDomain testDomain = BBJEntityUtils.parseFrom(request, TestDomain.class);
		return testDomainService.insert(testDomain );
	}
	
	
	/**
	* 删
	* @param id
	* @return
	*/
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Object deleteById(@PathVariable("id")String id,HttpServletRequest request){
		return testDomainService.deleteById(id);
	}
	
	
	/**
	 * 改
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request){
		TestDomain testDomain = BBJEntityUtils.parseFrom(request, TestDomain.class);
		return testDomainService.update(testDomain);
	}
	
	
	/**
	 * 查（单个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id")String id,HttpServletRequest request){
		return testDomainService.queryById(id);
	}
	
	
	/**
	 * 查（分页）
	 * @param start
	 * @param length
	 * @param draw
	 * @param searchValue
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Object queryByPage(@RequestParam(value="start",defaultValue="1")int start,
			@RequestParam(value="length",defaultValue="10")int length,
			@RequestParam(value="draw",defaultValue="0")int draw,
			@RequestParam(value="search[value]",defaultValue="")String searchValue,
			HttpServletRequest request
			){
		
		// 分页查询
		SqlFilter sqlFilter = null;	
		Map<String, Object> map = new HashMap<String, Object>();
		int tagPage = start / length;
		if(tagPage < 1){
			tagPage = 1;
		} else {
			tagPage = tagPage + 1;
		}
		map.put("data", testDomainService.queryByPage(tagPage, length, sqlFilter));
		map.put("recordsTotal", testDomainService.getTotalRow(sqlFilter));
		map.put("recordsFiltered", testDomainService.getTotalRow(sqlFilter));
		map.put("draw", draw);
		
		return map;
	}
	
	
	
	@RequestMapping(value="/page")
	public Object page(HttpServletRequest request){
		return Constants.module_admin + "/testDomain";
	}


}
