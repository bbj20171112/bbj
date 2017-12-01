package com.bbj.base.test;

import com.bbj.base.domain.DemoDomain;

public class TestDomain {

	public static void main(String[] args) {
		DemoDomain demo = new DemoDomain();
		System.out.println(demo.getTableName());
		System.out.println(demo.getAttr(DemoDomain.id));
		System.out.println(demo.getAttrKeys());
	}
	
}
