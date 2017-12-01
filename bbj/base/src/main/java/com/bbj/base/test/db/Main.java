package com.bbj.base.test.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbj.base.dao.DemoDao;
import com.bbj.base.dao.DemoDao2;
import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.DemoDomain2;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		String[] beansFilePathStr = {"application.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(beansFilePathStr ); 	
		DemoDao demoDao = context.getBean("demoDao",DemoDao.class);
		//BBJEntity h = demoDao.queryById("1");
		DemoDao2 demoDao2 = context.getBean("demoDao2",DemoDao2.class);
		
		// 查找
		BBJEntity h2 = demoDao2.queryById("3");
		System.out.println(h2);
		// 增加
//		BBJEntity bbjEntity = h2 ;
//		h2.setAttr(BBJEntity.id, "2");
//		demoDao2.insert(bbjEntity );
		
		// 修改
//		h2.setAttr(DemoDomain2.attr3, "val5_new");
//		demoDao2.update(h2);
//		h2 = demoDao2.queryByPrimaryValue("1");
//		System.out.println(h2);
		
		// 删除
		//demoDao2.deleteById("1");
		
		System.out.println(demoDao2.queryByPage(2,2).size());
		
	}
	
}
