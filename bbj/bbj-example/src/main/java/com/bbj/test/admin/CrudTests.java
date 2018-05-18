package com.bbj.test.admin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.bbj.admin.dictionary.dao.DictionaryFieldDao;
import com.bbj.admin.dictionary.domain.DictionaryField;
import com.bbj.base.domain.BBJSqlFilter;
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

        DictionaryFieldDao demoDao = wac.getBean("dictionaryFieldDao", DictionaryFieldDao.class);
        SqlFilter sqlFilter = new BBJSqlFilter(DictionaryField.class);
        List<WhereFilter> list = new ArrayList<WhereFilter>();
        list.add(new WhereFilter(DictionaryField.fieldOrderNumber, "<", "12"));
        list.add(new WhereFilter(DictionaryField.tableId, "=", "1524497478126"));
		sqlFilter.addWhereFilter(list );
		DictionaryField temp = demoDao.queryMaxSmallerThan(sqlFilter );
        System.out.println(temp);
    }

}
