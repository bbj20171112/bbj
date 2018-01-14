package com.bbj.test.base.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/base/demo"})
public class DemoContolller {
	
	@Autowired
	DemoDao2 demoDao;
	
	@RequestMapping({"/queryByPage"})
	@ResponseBody
	public Object grid(@RequestParam("tagPage") int tagPage,@RequestParam("pageSize") int pageSize){
		List<DemoDomain2> list = demoDao.queryByPage(tagPage, pageSize);
		return list;
	}
}
