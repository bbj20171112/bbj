
package com.bbj.base.controller.dictionary;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/base/dictionary/demo"})
public class DictionaryDemoController {

	
	@RequestMapping()
	public Object page(HttpServletRequest request){
		return "../framework/dictionary/dictionaryDemo";
	}
}
