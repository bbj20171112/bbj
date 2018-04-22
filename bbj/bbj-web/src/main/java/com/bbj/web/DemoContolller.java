package com.bbj.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.domain.BBJResponse;

@Controller
@RequestMapping({"/web/demo"})
public class DemoContolller {
	
	@Autowired
	DemoDao2 demoDao;
	
	@RequestMapping({"/queryByPage"})
	@ResponseBody
	public Object grid(@RequestParam("tagPage") int tagPage,@RequestParam("pageSize") int pageSize){
		List<DemoDomain2> list = demoDao.queryByPage(tagPage, pageSize);
		return list;
	}
	
	@RequestMapping({"/demo"})
	@ResponseBody
	public Object demo(){
		return new DemoDomain2();
	}
	
	@RequestMapping({"/demo2"})
	@ResponseBody
	public Object demo2(){
		return new BBJResponse("213", "测试", new DemoDomain2(),new DemoDomain2());
	}
	
}
