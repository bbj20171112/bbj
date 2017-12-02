package com.bbj.base.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.domain.DemoDomain;

@Controller
@RequestMapping("/base/response")
public class responce {

	@RequestMapping("/test")
	@ResponseBody
	public Object test() {
		System.out.println("test");
		return new DemoDomain();
	}
	
}
