package com.bbj.base.domain;

import java.util.List;

public interface SqlFilter {

	/**
	 * 增加一个过滤条件
	 * @param list
	 * @return
	 */
	SqlFilter addWhereFilter(List<WhereFilter> list);
	
	/**
	 * 清空条件
	 * @return
	 */
	SqlFilter clear();

	/**
	 * 变成SQL语句
	 * @return
	 */
	String getSqlString();

	/**
	 * 获取参数列表
	 * @return
	 */
	List<Object> getListParam();

	String toString();

}