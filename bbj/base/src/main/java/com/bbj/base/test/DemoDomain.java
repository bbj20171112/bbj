package com.bbj.base.test;

import com.bbj.base.domain.BBJObject;

public class DemoDomain extends BBJObject{

	private static final long serialVersionUID = 1L;

	public static final String tableName = "demoTable";
	
	public static final String attr1 = "id";
	public static final String attr2 = "attr2";
	public static final String attr3 = "attr3";
	
	@Override
	public String initTable() {
		return tableName;
	}

	@Override
	public String[] initAttr() {
		return new String[]{attr1,attr2,attr3};
	}
	
}
