package com.bbj.test.base.crud;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bbj.base.domain.SqlFilter;
import com.bbj.base.domain.WhereFilter;

public class SqlFilterTest {

	
	@Test
	public void testWhereFilter() {
		List<WhereFilter> list = new ArrayList<WhereFilter>();
		list.add(new WhereFilter("attr2", "=", "nihao", "and"));
		list.add(new WhereFilter("attr1", "=", "nihao", "or"));
		list.add(new WhereFilter("attr3", "=", "nihao", "and"));
		System.out.println(new SqlFilter(new DemoDomain2()).addWhereFilter(list ).getSqlString());
	}
	
}
