package com.bbj.base.test;

public class TestDomain {

	public static void main(String[] args) {
		DemoDomain demo = new DemoDomain();
		System.out.println(demo.getTableName());
		System.out.println(demo.getPrimaryAttr());
		System.out.println(demo.getAttrKeys());
	}
	
}
