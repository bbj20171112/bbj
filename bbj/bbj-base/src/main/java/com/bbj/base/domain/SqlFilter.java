package com.bbj.base.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL 过滤
 * @author bage
 *
 */
public class SqlFilter extends BBJObject{

	private static final long serialVersionUID = 1L;
	
	// 操作符
	public static final String where_opt_eq = "="; // 等于号
	public static final String where_opt_like = "like"; // like
	public static final String where_opt_lt = ">"; // 大于
	public static final String where_opt_lqt = ">="; // 大于等于
	public static final String where_opt_st = "<"; // 小于
	public static final String where_opt_sqt = "<="; // 小于等于


	private BBJEntity curruntBBJEntity; // 目标实体
	private StringBuilder whereString = new StringBuilder(); // 过滤语句
	private StringBuilder orderbyString = new StringBuilder(); // 排序语句
	private StringBuilder havingString = new StringBuilder(); // having语句
	private List<Object> listParam = new ArrayList<Object>(); // 参数列表

	/**
	 * 
	 * @param curruntBBJEntity 目标实体,必须设置
	 */
	public SqlFilter(BBJEntity curruntBBJEntity){
		this.curruntBBJEntity = curruntBBJEntity;
	}
	
	/**
	 * 增加一个过滤条件
	 * @param list
	 * @return
	 */
	public SqlFilter addWhereFilter(List<WhereFilter> list){
		for (int i = 0; i < list.size(); i++) {
			WhereFilter whereFilter = list.get(i);
			if(curruntBBJEntity.getAttrKeys().contains(whereFilter.getAttr())){
				if(whereString.length() > 0){
					whereString.append(whereFilter.getConn());
				}
				whereString.append(whereFilter.toString());
				if(where_opt_like.equals(whereFilter.getOpt())){
					listParam.add("%" + whereFilter.getValue() + "%");
				} else {
					listParam.add(whereFilter.getValue());
				}
			}
		}
		return this;
	}

	
	/**
	 * 变成SQL语句
	 * @return
	 */
	public String getSqlString() {
		return whereString.toString() + orderbyString.toString() + havingString.toString();
	}

	/**
	 * 获取参数列表
	 * @return
	 */
	public List<Object> getListParam() {
		return listParam;
	}

	@Override
	public String toString() {
		return "SqlFilter [whereString=" + whereString + ", orderbyString=" + orderbyString + ", havingString="
				+ havingString + ", listParam=" + listParam + "]";
	}

}
