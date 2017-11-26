package com.bbj.base.test.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbj.base.dao.DemoDao;
import com.bbj.base.dao.DemoDao2;
import com.bbj.base.domain.BBJEntity;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String[] beansFilePathStr = {"application.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(beansFilePathStr ); 	
		DemoDao demoDao = context.getBean("demoDao",DemoDao.class);
		System.out.println(demoDao);
		BBJEntity h = demoDao.queryForBBJEntity("1");
		System.out.println(h);
		
		DemoDao2 demoDao2 = context.getBean("demoDao2",DemoDao2.class);
		BBJEntity h2 = demoDao2.queryForBBJEntity("3");
		System.out.println(h2);
	}
	
}
