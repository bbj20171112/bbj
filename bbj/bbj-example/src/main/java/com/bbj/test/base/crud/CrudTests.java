package com.bbj.test.base.crud;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SyncScopeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bbj.base.domain.BBJEntity;
import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("application.xml")
public class CrudTests
{

    @Autowired
    private WebApplicationContext wac;
    
    @Test
    public void getAccount() throws Exception {

        @SuppressWarnings("unused")
        DemoDao demoDao = wac.getBean("demoDao", DemoDao.class);
        //BBJEntity h = demoDao.queryById("1");
        DemoDao2 demoDao2 = wac.getBean("demoDao2", DemoDao2.class);

        // 查找
        BBJEntity h2 = demoDao2.queryById("3");
        System.out.println(h2);
       
        // 增加
//        BBJEntity bbjEntity = h2 ;
//        h2.setAttr(BBJEntity.id, "2");
//        demoDao2.insert(bbjEntity );

        // 修改
        //    		h2.setAttr(DemoDomain2.attr3, "val5_new");
        //    		demoDao2.update(h2);
        //    		h2 = demoDao2.queryByPrimaryValue("1");
        //    		System.out.println(h2);

        // 删除
        //demoDao2.deleteById("1");

        System.out.println(demoDao2.queryByPage(2, 2).size());

        //        	
        //                this.mockMvc.perform(get("/accounts/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        //                        .andExpect(status().isOk())
        //                        .andExpect(content().contentType("application/json"))
        //                        .andExpect(jsonPath("$.name").value("Lee"));
        
        System.out.println("------ 带条件查询   -----------------------------------");
        System.out.println(demoDao2.queryByPage(1, 2));
        SqlFilter sqlFilter = new SqlFilter(new DemoDomain2());
        List<WhereFilter> list = new ArrayList<WhereFilter>();
        list.add(new WhereFilter("attr5", SqlFilter.where_opt_eq, "val52"));
		sqlFilter.addWhereFilter(list );
		System.out.println(demoDao2.queryByPage(1, 2,sqlFilter ));
        System.out.println("------ 带条件查询   -----------------------------------");

        
    }

}
